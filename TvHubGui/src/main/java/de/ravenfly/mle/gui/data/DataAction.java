package de.ravenfly.mle.gui.data;

import java.awt.Component;

import javax.swing.AbstractAction;

import de.ravenfly.mle.modulebase.ContextObserver;
import de.ravenfly.mle.modulebase.DataContext;

public abstract class DataAction<T> extends AbstractAction  implements ContextObserver<T>{

	private static final long serialVersionUID = 4097389453478962201L;

	protected Component parent;
	protected DataContext<T> context;

	public DataAction() {
		super();
		this.parent      = null;
	}
	
	public void setParent(Component parent) {
		this.parent = parent;
	}

	@Override
	public void setContext(DataContext<T> context) {
		this.context     = context;
	}
}
