package de.ravenfly.mle.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import de.ravenfly.mle.gui.data.DataAction;
import de.ravenfly.mle.modulebase.DataContext;
import de.ravenfly.mle.modulebase.DataObserver;

public class ReloadAction<T> extends DataAction<T> implements DataObserver {

	private static final long serialVersionUID = 2301416988097542239L;

	public ReloadAction() {
		super();

		putValue(NAME, "Reopen");
		putValue(SHORT_DESCRIPTION, "Reopen the file");
		putValue(SMALL_ICON, new ImageIcon(ReloadAction.class.getResource("/icons/small/arrow_refresh.png")));
		putValue(MNEMONIC_KEY, KeyEvent.VK_R);

		setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(context != null && context.isModified()){
			context.open();
		}
	}

	@Override
	public void setContext(DataContext<T> context) {
		super.setContext(context);
		this.context.addDataObserver(this);
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
