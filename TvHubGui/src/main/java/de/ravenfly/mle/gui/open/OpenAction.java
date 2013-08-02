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

public class OpenAction<T> extends AbstractAction {

	private static final long serialVersionUID = 793084547231787173L;

	protected Component parent;

	private List<OpenObserver<T>> observers;

	public OpenAction() {
		super();

		putValue(NAME, "Open");
		putValue(SHORT_DESCRIPTION, "Open a file");
		putValue(SMALL_ICON, new ImageIcon(OpenAction.class.getResource("/icons/small/folder.png")));
		putValue(MNEMONIC_KEY, KeyEvent.VK_O);

		observers = new ArrayList<OpenObserver<T>>();
	}

	public void addOpenObserver(OpenObserver<T> observer){
		observers.add(observer);
	}

	public void removeOpenObserver(OpenObserver<T> observer){
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

			List<DataContext<T>> contextList = new ArrayList<DataContext<T>>(); 

			File[] listOfFiles = folder.listFiles(); 
			for (File file : listOfFiles) {
				String ext = file.getName().substring(file.getName().lastIndexOf('.') + 1).toLowerCase();
				if(ext.equals("flv") || ext.equals("mp3") || ext.equals("mkv")){
					try {
						DataContext<T> context = DataContextFactory.createContext();
						context.setVideofile(file);
						contextList.add(context);
					} catch (DataException e1) {
						e1.printStackTrace();
					}
				}
			}

			Comparator<DataContext<T>> c = contextListComparator();
			Collections.sort(contextList, c);

			for (OpenObserver<T> observer : observers) {
				observer.contextOpen(contextList);
			}

			userPrefs.put("data.directory.last", folder.getAbsolutePath());
        }
	}

	protected static <T> Comparator<DataContext<T>> contextListComparator() {
		return new Comparator<DataContext<T>>() {
			@Override
			public int compare(DataContext<T> first, DataContext<T> second) {
				return first.getBaseName().compareTo(second.getBaseName());
			}
		};
	}
}
