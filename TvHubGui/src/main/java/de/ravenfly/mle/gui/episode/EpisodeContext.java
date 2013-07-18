package de.ravenfly.mle.gui.episode;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;

import de.ravenfly.mle.modulebase.DataContext;
import de.ravenfly.mle.modulebase.DataHandler;
import de.ravenfly.mle.modulebase.DataHandlerException;
import de.ravenfly.mle.modulebase.DataObserver;
import de.ravenfly.mle.modulebase.filemodel.Episode;

public class EpisodeContext {

	private DataContext<Episode> context;

	public EpisodeContext() {
		super();
		context = new DataContext<Episode>();

		try {
			FrameworkFactory frameworkFactory = ServiceLoader.load(FrameworkFactory.class).iterator().next();
			Map<String, String> config = new HashMap<String, String>();
			config.put(Constants.FRAMEWORK_SYSTEMPACKAGES_EXTRA, "org.osgi.framework,de.ravenfly.mle.modulebase,de.ravenfly.mle.modulebase.filemodel,javax.xml.bind");
			Framework framework = frameworkFactory.newFramework(config);
			framework.start();

			BundleContext bundleContext = framework.getBundleContext();
			List<Bundle> installedBundles = new LinkedList<Bundle>();

			installedBundles.add(bundleContext.installBundle("file:../WDTV Metadata Lib/target/wdtvMetadata-1.0.0-SNAPSHOT.jar"));

			for (Bundle bundle : installedBundles) {
				if (bundle.getHeaders().get(Constants.FRAGMENT_HOST) == null){
					bundle.start();
				}
			}

			final ServiceReference<?> reference = bundleContext.getServiceReference(DataHandler.class.getName());
			@SuppressWarnings("unchecked")
			final DataHandler<Episode> handler = (DataHandler<Episode>) bundleContext.getService(reference);

			if(handler != null ){
            	System.out.println( "Konsument-ServiceTracker liest DataHandler: " + handler);
            }

    		context.setDatahandler(handler);
    		context.setModel(new Episode());
			
		} catch (BundleException e){
			e.printStackTrace();
		}
	}

	public EpisodeContext(DataContext<Episode> context) {
		super();
		this.context = context;
	}
	
	public Episode getEpisode(){
		return context.getModel();
	}

	public DataContext<Episode> getContext() {
		return context;
	}

	public void setContext(DataContext<Episode> context) {
		this.context = context;
	}

	public boolean canLoad(){
		return context.getDatahandler().canLoad();
	}

	public boolean canSave(){
		return context.getDatahandler().canSave();
	}

	public boolean isLoaded() {
		return context.isLoaded();
	}

	public void setModified(boolean modified){
		context.setModified(modified);
	}

	public boolean isModified(){
		return context.isModified();
	}

	public void addDataObserver(DataObserver observer){
		context.addDataObserver(observer);
	}

	public void load(){
		if(context != null && context.isModified()){
			load(context.getFile());
		}
	}

	public void load(File file){

		context.setFile(file);

		try {
			Episode model = context.getDatahandler().load(file.getAbsolutePath());
			context.setModel(model);
			context.setLoaded(true);
			context.setModified(false);
		} catch (DataHandlerException ex) {
			ex.printStackTrace();
		}

		context.fireDone();
	}

	public void save(){
		if(context != null && context.isModified()){
			System.out.println("My Save Data Context: " + context);
			try {
				context.getDatahandler().save(context.getModel(), context.getFile().getAbsolutePath());
				context.setModified(false);
			} catch (DataHandlerException ex) {
				ex.printStackTrace();
			}
			context.fireDone();
		}
	}
}
