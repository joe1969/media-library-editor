package de.ravenfly.mle.episode.gui;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import de.ravenfly.mle.modulebase.gui.DataTab;

public class Activator implements BundleActivator{

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println(context.getBundle().getSymbolicName() + " startet ...");
		context.registerService(DataTab.class.getName(), new EpisodeView(), null);
		System.out.println(context.getBundle().getSymbolicName() + " gestartet und Dienst registriert.");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println( context.getBundle().getSymbolicName() +  " gestoppt." );		
	}
}
