package de.ravenfly.mle.modulebase;

public interface DataHandler<T> {

	public boolean canLoad();
	
	public boolean canSave();

	public T load(String path) throws DataException;

	public void save(T model, String path) throws DataException;
}
