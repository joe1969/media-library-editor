package de.ravenfly.mle.modulebase;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataContext<T> {
	
	private final static Logger log = Logger.getLogger(DataContext.class.getName()); 

	private File videofile;
	private String basePath;
	private String baseName;
	private String absoluteName;

	private DataHandler<T> datahandler;

	private boolean modified;
	private boolean open;
	private T model;
	private BufferedImage metathumb;

	protected List<DataObserver> observers;

	public DataContext() {
		super();
		observers = new ArrayList<DataObserver>();
		close();
	}

	public File getVideofile() {
		return videofile;
	}

	public void setVideofile(File videofile) {
		this.videofile = videofile;

		String name = videofile.getName();

		basePath = videofile.getParentFile().getAbsolutePath();
		baseName = name.substring(0, name.lastIndexOf('.'));
		absoluteName = basePath + "/" + baseName;
		log.fine("BasePath: " + basePath + ", BaseName: " + baseName + ", AbsoluteName: " + absoluteName);
	}

	public String getBasePath() {
		return basePath;
	}

	public String getBaseName() {
		return baseName;
	}

	public String getAbsoluteName() {
		return absoluteName;
	}

	public DataHandler<T> getDatahandler() {
		return datahandler;
	}

	public void setDatahandler(DataHandler<T> datahandler) {
		this.datahandler = datahandler;
	}

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
		if(modified){
			for (DataObserver observer : observers) {
				observer.modifiedDone();
			}
		}
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public BufferedImage getMetathumb() {
		return metathumb;
	}

	public void setMetathumb(BufferedImage metathumb) {
		this.metathumb = metathumb;
	}

	public void open(){
		try {
			model     = getDatahandler().loadInfo(absoluteName);
			metathumb = getDatahandler().loadMetathumb(absoluteName);

			open      = true;
			modified  = false;

			for (DataObserver observer : observers) {
				observer.openDone();
			}

		} catch (DataException ex) {
			log.log(Level.WARNING, "Data Exception", ex);
		}
	}

	public void close(){
		modified  = false;
		open      = false;
		model     = null;
		metathumb = null;

		for (DataObserver observer : observers) {
			observer.closeDone();
		}
	}

	public void flush(){
		if(modified){
			try {

				getDatahandler().saveInfo(getModel(), getVideofile().getAbsolutePath());
				modified = false;

				for (DataObserver observer : observers) {
					observer.flushDone();
				}

			} catch (DataException ex) {
				log.log(Level.WARNING, "Data Exception", ex);
			}
		}
	}

	public void addDataObserver(DataObserver observer){
		observers.add(observer);
	}

	public void removeDataObserver(DataObserver observer){
		if(observers.contains(observer)){
			observers.remove(observer);
		}
	}
	
	@Override
	public String toString() {
		return "DataContext [videofile=" + videofile + ", modified=" + modified
				+ ", loaded=" + open + ", model=" + model + ", datahandler="
				+ datahandler + ", metathumb=" + metathumb + ", basePath="
				+ basePath + ", baseName=" + baseName + ", absoluteName="
				+ absoluteName + ", observers=" + observers + "]";
	}
}
