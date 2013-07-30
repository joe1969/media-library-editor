package de.ravenfly.mle.modulebase;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class DataContext<T> {
	
	private final static Logger log = Logger.getLogger(DataContext.class.getName()); 

	private File file;
	private boolean modified;
	private boolean loaded;
	private T model;
	private DataHandler<T> datahandler;
	private BufferedImage metathumb;
	private String basePath;
	private String baseName;

	protected List<DataObserver> observers;

	public DataContext() {
		super();
		modified  = false;
		loaded    = false;
		observers = new ArrayList<DataObserver>();
		metathumb = null;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
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

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public boolean canLoad(){
		return datahandler.canLoad();
	}

	public boolean canSave(){
		return datahandler.canSave();
	}

	public void load(){
		if(isModified()){
			load(getFile());
		}
	}

	public void load(File file){

		setFile(file);

		try {
			String absoluteName = file.getAbsolutePath();
			String fname = file.getName();

			basePath = file.getParentFile().getAbsolutePath();
			baseName = fname.substring(0, fname.lastIndexOf('.'));
			log.fine("BasePath: " + basePath + ", BaseName: " + baseName);

			model = getDatahandler().load(absoluteName);

			setLoaded(true);
			setModified(false);
			setMetathumb(loadMetathumb(file));

		} catch (DataException ex) {
			log.log(Level.WARNING, "Data Exception", ex);
		}

		fireDone();
	}

	public void save(){
		if(isModified()){
			try {
				getDatahandler().save(getModel(), getFile().getAbsolutePath());
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

	public void fireDone(){
		for (DataObserver observer : observers) {
			observer.done();
		}
	}

	@Override
	public String toString() {
		return "DataContext [file=" + file + ", modified=" + modified
				+ ", loaded=" + loaded + ", model=" + model + ", datahandler="
				+ datahandler + ", observers=" + observers + "]";
	}

	private BufferedImage loadMetathumb(File xmlfile){

		String file = xmlfile.getAbsolutePath();
		File metathumb = new File(file.substring(0, file.lastIndexOf('.'))+".metathumb");

		BufferedImage img = null;
		try {
			img = ImageIO.read(metathumb);
		} catch (IOException e) {
			log.log(Level.WARNING, "IO Exception", e);
		}

		return img;
	}
}
