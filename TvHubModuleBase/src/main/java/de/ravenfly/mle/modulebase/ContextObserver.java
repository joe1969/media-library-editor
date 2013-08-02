package de.ravenfly.mle.modulebase;


public interface ContextObserver<T> {
	public void setContext(DataContext<T> context);
}
