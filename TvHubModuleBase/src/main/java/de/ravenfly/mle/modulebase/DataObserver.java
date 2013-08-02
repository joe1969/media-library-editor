package de.ravenfly.mle.modulebase;

public interface DataObserver {

	public void openDone();

	public void closeDone();

	public void flushDone();

	public void modifiedDone();
}
