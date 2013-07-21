package de.ravenfly.mle.modulebase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class DataContext<T> {
	
	private final static Logger log = Logger.getLogger(DataContext.class.getName()); 

	private File file;
	private boolean modified;
	private boolean loaded;
	private T model;
	private DataHandler<T> datahandler;

	protected List<DataObserver> observers;

	public DataContext() {
		super();
		modified  = false;
		loaded    = false;
		observers = new ArrayList<DataObserver>();
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
			model = getDatahandler().load(file.getAbsolutePath());
			setLoaded(true);
			setModified(false);
		} catch (DataHandlerException ex) {
			log.log(Level.WARNING, "Data Handler Exception", ex);
			ex.printStackTrace();
		}

		fireDone();
	}

	public void save(){
		if(isModified()){
			try {
				getDatahandler().save(getModel(), getFile().getAbsolutePath());
				setModified(false);
			} catch (DataHandlerException ex) {
				log.log(Level.WARNING, "Data Handler Exception", ex);
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

	public static <T> DataContext<T> createContext(Class<T> clazz, BundleContext bundleContext) throws DataContextException{
		
		DataContext<T> dataContext = new DataContext<T>();

		final ServiceReference<?> reference = bundleContext.getServiceReference(DataHandler.class.getName());
		@SuppressWarnings("unchecked")
		final DataHandler<T> handler = (DataHandler<T>) bundleContext.getService(reference);

		if(handler != null ){
			log.fine("Konsument-ServiceTracker liest DataHandler: " + handler);
        }

		dataContext.setDatahandler(handler);
		try {
			dataContext.setModel(clazz.newInstance());
		} catch (InstantiationException e) {
			log.log(Level.WARNING, "Instantiation Exception", e);
			throw new DataContextException(e);
		} catch (IllegalAccessException e) {
			log.log(Level.WARNING, "Illegal Access Exception", e);
			throw new DataContextException(e);
		}

		return dataContext;
	}
}
