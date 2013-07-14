package de.ravenfly.mle.modulebase;

import net.xeoh.plugins.base.Plugin;

public interface DataHandler<T> extends Plugin{

	public String[] capabilities();

	public T load(String path) throws DataHandlerException;

	public void save(T model, String path) throws DataHandlerException;
}
