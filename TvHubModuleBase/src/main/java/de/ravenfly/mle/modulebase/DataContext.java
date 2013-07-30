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
	private boolean modified;
	private boolean loaded;
	private T model;
	private DataHandler<T> datahandler;
	private BufferedImage metathumb;
	private String basePath;
	private String baseName;
	private String absoluteName;

	protected List<DataObserver> observers;

	public DataContext() {
		super();
		modified  = false;
		loaded    = false;
		observers = new ArrayList<DataObserver>();
		metathumb = null;
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

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public DataHandler<T> getDatahandler() {
		return datahandler;
	}

	public void setDatahandler(DataHandler<T> datahandler) {
		this.datahandler = datahandler;
	}

	public BufferedImage getMetathumb() {
		return metathumb;
	}

	public void setMetathumb(BufferedImage metathumb) {
		this.metathumb = metathumb;
	}

	public boolean canLoad(){
		return datahandler.canLoad();
	}

	public boolean canSave(){
		return datahandler.canSave();
	}

	public void load(){
		try {
			model = getDatahandler().loadInfo(absoluteName);
			metathumb = getDatahandler().loadMetathumb(absoluteName);

			setLoaded(true);
			setModified(false);

		} catch (DataException ex) {
			log.log(Level.WARNING, "Data Exception", ex);
		}

		fireDone();
	}

	public void save(){
		if(isModified()){
			try {
				getDatahandler().saveInfo(getModel(), getVideofile().getAbsolutePath());
				setModified(false);
			} catch (DataException ex) {
				log.log(Level.WARNING, "Data Exception", ex);
			}
			fireDone();
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

	public void fireDone(){
		for (DataObserver observer : observers) {
			observer.done();
		}
	}

	@Override
	public String toString() {
		return "DataContext [videofile=" + videofile + ", modified=" + modified
				+ ", loaded=" + loaded + ", model=" + model + ", datahandler="
				+ datahandler + ", metathumb=" + metathumb + ", basePath="
				+ basePath + ", baseName=" + baseName + ", absoluteName="
				+ absoluteName + ", observers=" + observers + "]";
	}
}
