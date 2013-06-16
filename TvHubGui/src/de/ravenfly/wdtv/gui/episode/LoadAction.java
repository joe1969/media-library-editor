package de.ravenfly.wdtv.gui.episode;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import de.ravenfly.wdtv.gui.XmlFileFilter;
import de.ravenfly.wdtv.gui.data.DataAction;
import de.ravenfly.wdtv.modulebase.DataObserver;

public class LoadAction extends DataAction<EpisodeContext> implements DataObserver {

	private static final long serialVersionUID = 793084547231787173L;

	protected File currentDirectory;

	public LoadAction(EpisodeContext context) {
		super(context);

		currentDirectory = null;

		putValue(NAME, "Open");
		putValue(SHORT_DESCRIPTION, "Open a file");
		putValue(SMALL_ICON, new ImageIcon(LoadAction.class.getResource("/rescources/icons/small/folder.png")));
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
