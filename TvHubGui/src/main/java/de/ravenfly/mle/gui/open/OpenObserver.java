package de.ravenfly.mle.gui.open;

import java.util.List;

import de.ravenfly.mle.modulebase.DataContext;

public interface OpenObserver<T> {

	public void contextOpen(List<DataContext<T>> contextList );

}
