package de.ravenfly.wdtv.modulebase;

public interface DataHandler<T> {

	public boolean canLoad();
	
	public boolean canSave();

	public T load(String path) throws DataHandlerException;

	public void save(T model, String path) throws DataHandlerException;
}
