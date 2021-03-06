package de.ravenfly.mle.modulebase.gui;

import javax.swing.JTabbedPane;

import de.ravenfly.mle.modulebase.DataContext;
import de.ravenfly.mle.modulebase.ContextObserver;

public abstract class DataTab<T> extends JTabbedPane implements ContextObserver<T> {

	private static final long serialVersionUID = 1961033889314417551L;

	abstract public void setContext(DataContext<T> context);
}
