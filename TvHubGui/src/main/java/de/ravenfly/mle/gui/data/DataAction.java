package de.ravenfly.mle.gui.data;

import java.awt.Component;

import javax.swing.AbstractAction;

public abstract class DataAction<T> extends AbstractAction {

	private static final long serialVersionUID = 4097389453478962201L;

	protected Component parent;
	protected T context;

	public DataAction(T context) {
		super();
		this.context     = context;
		this.parent      = null;
	}

	public void setParent(Component parent) {
		this.parent = parent;
	}
}
