package de.ravenfly.mle.modulebase;

import java.awt.image.BufferedImage;

public interface DataHandler<T> {

	public boolean canLoad();
	
	public boolean canSave();

	public T loadInfo(String path) throws DataException;

	public void saveInfo(T model, String path) throws DataException;

	public BufferedImage loadMetathumb(String path) throws DataException;

	public void saveMetathumb(BufferedImage image, String path) throws DataException;
}
