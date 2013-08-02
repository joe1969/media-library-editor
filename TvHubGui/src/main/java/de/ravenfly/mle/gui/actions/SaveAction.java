package de.ravenfly.mle.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import de.ravenfly.mle.gui.data.DataAction;
import de.ravenfly.mle.modulebase.DataContext;
import de.ravenfly.mle.modulebase.DataObserver;

public class SaveAction<T> extends DataAction<T> implements DataObserver{

	private static final long serialVersionUID = 4747773412132701749L;

	public SaveAction() {
		super();

		putValue(NAME, "Save");
		putValue(SHORT_DESCRIPTION, "Save to file");
		putValue(SMALL_ICON, new ImageIcon(SaveAction.class.getResource("/icons/small/disk.png")));
		putValue(MNEMONIC_KEY, KeyEvent.VK_S);

		setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(context != null && context.isModified()){
			context.flush();
		}
	}

	@Override
	public void setContext(DataContext<T> context) {
		super.setContext(context);
		context.addDataObserver(this);
	}

	@Override
	public void openDone() {
		setEnabled(context.isOpen() && context.isModified());
	}

	@Override
	public void closeDone() {
	}

	@Override
	public void flushDone() {
	}

	@Override
	public void modifiedDone() {
		setEnabled(context.isOpen() && context.isModified());
	}
}
