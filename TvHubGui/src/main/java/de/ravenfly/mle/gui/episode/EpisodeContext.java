package de.ravenfly.mle.gui.episode;

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
import de.ravenfly.mle.modulebase.filemodel.Episode;

public class EpisodeContext extends DataContext<Episode> {


	public EpisodeContext() {
		super();

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

    		setDatahandler(handler);
    		setModel(new Episode());
			
		} catch (BundleException e){
			e.printStackTrace();
		}
	}
}
