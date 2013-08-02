package de.ravenfly.mle.gui.open;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import de.ravenfly.mle.gui.VideoFileFilter;
import de.ravenfly.mle.gui.osgi.DataContextFactory;
import de.ravenfly.mle.modulebase.DataContext;
import de.ravenfly.mle.modulebase.DataException;
import de.ravenfly.mle.modulebase.filemodel.Episode;

public class OpenAction extends AbstractAction {

	private static final long serialVersionUID = 793084547231787173L;

	protected Component parent;

	private List<OpenObserver<Episode>> observers;

	public OpenAction() {
		super();

		putValue(NAME, "Open");
		putValue(SHORT_DESCRIPTION, "Open a file");
		putValue(SMALL_ICON, new ImageIcon(OpenAction.class.getResource("/icons/small/folder.png")));
		putValue(MNEMONIC_KEY, KeyEvent.VK_O);

		observers = new ArrayList<OpenObserver<Episode>>();
	}

	public void addOpenObserver(OpenObserver<Episode> observer){
		observers.add(observer);
	}

	public void removeOpenObserver(OpenObserver<Episode> observer){
		if(observers.contains(observer)){
			observers.remove(observer);
		}
	}

	public void setParent(Component parent) {
		this.parent = parent;
	}

	public void actionPerformed(ActionEvent e) {

		VideoFileFilter filter = new VideoFileFilter();
		Preferences userPrefs = Preferences.userNodeForPackage(this.getClass());

		final JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(filter);
		fc.setFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(true);
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		File currentDirectory = new File(userPrefs.get("data.directory.last", "."));
		if(currentDirectory != null){
			fc.setCurrentDirectory(currentDirectory);
		}

		int returnVal = fc.showOpenDialog(parent);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File folder = fc.getSelectedFile().isDirectory()?fc.getSelectedFile():new File(fc.getSelectedFile().getParent());

			List<DataContext<Episode>> contextList = new ArrayList<DataContext<Episode>>(); 

			File[] listOfFiles = folder.listFiles(); 
			for (File file : listOfFiles) {
				String ext = file.getName().substring(file.getName().lastIndexOf('.') + 1).toLowerCase();
				if(ext.equals("flv") || ext.equals("mp3") || ext.equals("mkv")){
					try {
						DataContext<Episode> context = DataContextFactory.createContext(Episode.class);
						context.setVideofile(file);
						contextList.add(context);
					} catch (DataException e1) {
						e1.printStackTrace();
					}
				}
			}

			Collections.sort(contextList, contextListComparator());

			for (OpenObserver<Episode> observer : observers) {
				observer.contextOpen(contextList);
			}

			userPrefs.put("data.directory.last", folder.getAbsolutePath());
        }
	}

	protected static Comparator<DataContext<Episode>> contextListComparator() {
		return new Comparator<DataContext<Episode>>() {
			@Override
			public int compare(DataContext<Episode> first, DataContext<Episode> second) {
				return first.getBaseName().compareTo(second.getBaseName());
			}
		};
	}
}
