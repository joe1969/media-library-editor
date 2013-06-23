package de.ravenfly.mle.modulebase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataContext<T> {

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
}
