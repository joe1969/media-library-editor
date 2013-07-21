package de.ravenfly.mle.gui.data;

import java.awt.Component;

import javax.swing.AbstractAction;

import de.ravenfly.mle.modulebase.DataContext;

public abstract class DataAction extends AbstractAction {

	private static final long serialVersionUID = 4097389453478962201L;

	protected Component parent;
	protected DataContext<?> context;

	public DataAction(DataContext<?> context) {
		super();
		this.context     = context;
		this.parent      = null;
	}

	public void setParent(Component parent) {
		this.parent = parent;
	}
}
