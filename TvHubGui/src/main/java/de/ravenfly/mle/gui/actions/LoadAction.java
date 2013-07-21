package de.ravenfly.mle.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import de.ravenfly.mle.gui.XmlFileFilter;
import de.ravenfly.mle.gui.data.DataAction;
import de.ravenfly.mle.modulebase.DataContext;
import de.ravenfly.mle.modulebase.DataObserver;

public class LoadAction extends DataAction implements DataObserver {

	private static final long serialVersionUID = 793084547231787173L;

	protected File currentDirectory;

	public LoadAction(DataContext<?> context) {
		super(context);

		currentDirectory = null;

		putValue(NAME, "Open");
		putValue(SHORT_DESCRIPTION, "Open a file");
		putValue(SMALL_ICON, new ImageIcon(LoadAction.class.getResource("/icons/small/folder.png")));
		putValue(MNEMONIC_KEY, KeyEvent.VK_O);

		context.addDataObserver(this);
	}

	public void setCurrentDirectory(String currentDirectory) {
		this.currentDirectory = new File(currentDirectory);
	}

	public void actionPerformed(ActionEvent e) {

		XmlFileFilter filter = new XmlFileFilter();

		final JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(filter);
		fc.setFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(true);
		if(currentDirectory != null){
			fc.setCurrentDirectory(currentDirectory);
		}
		int returnVal = fc.showOpenDialog(parent);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			context.load(file);
        }
	}

	@Override
	public void done() {
	}
}
