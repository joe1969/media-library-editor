package de.ravenfly.mle.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import de.ravenfly.mle.gui.VideoFileFilter;
import de.ravenfly.mle.gui.data.DataAction;
import de.ravenfly.mle.modulebase.DataContext;
import de.ravenfly.mle.modulebase.DataObserver;

public class LoadAction<T> extends DataAction<T> implements DataObserver {

	private static final long serialVersionUID = 793084547231787173L;

	public LoadAction() {
		super();

		putValue(NAME, "Load");
		putValue(SHORT_DESCRIPTION, "Open a file");
		putValue(SMALL_ICON, new ImageIcon(LoadAction.class.getResource("/icons/small/folder.png")));
		putValue(MNEMONIC_KEY, KeyEvent.VK_O);
	}

	@Override
	public void setContext(DataContext<T> context) {
		super.setContext(context);
		this.context.addDataObserver(this);
	}

	public void actionPerformed(ActionEvent e) {

		VideoFileFilter filter = new VideoFileFilter();
		Preferences userPrefs = Preferences.userNodeForPackage(this.getClass());

		final JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(filter);
		fc.setFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(true);

		File currentDirectory = new File(userPrefs.get("data.directory.last", "."));
		if(currentDirectory != null){
			fc.setCurrentDirectory(currentDirectory);
		}
		int returnVal = fc.showOpenDialog(parent);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			context.setVideofile(file);
			context.open();
			userPrefs.put("data.directory.last", fc.getCurrentDirectory().getAbsolutePath());
        }
	}

	@Override
	public void openDone() {
	}

	@Override
	public void closeDone() {
	}

	@Override
	public void flushDone() {
	}

	@Override
	public void modifiedDone() {
	}
}
