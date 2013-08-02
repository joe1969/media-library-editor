package de.ravenfly.mle.metadata;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import de.ravenfly.mle.modulebase.DataFeatures;
import de.ravenfly.mle.modulebase.DataHandler;

public class Activator implements BundleActivator{

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println(context.getBundle().getSymbolicName() + " startet ...");
		context.registerService(DataHandler.class.getName(), new DataIO(), null);
		context.registerService(DataFeatures.class.getName(), new DataFeaturesImpl(), null);
		System.out.println(context.getBundle().getSymbolicName() + " gestartet und Dienst registriert.");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println( context.getBundle().getSymbolicName() +  " gestoppt." );		
	}
}
