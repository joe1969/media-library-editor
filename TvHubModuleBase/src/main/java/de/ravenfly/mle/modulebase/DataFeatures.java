package de.ravenfly.mle.modulebase;

public interface DataFeatures {

	public boolean canLoad();

	public boolean canSave();

	public String getModelClassName();

	public Class<?> getModelClass();
}
