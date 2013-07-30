package de.ravenfly.mle.gui.osgi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;

public class ModuleFactory {

	private final static Logger log = Logger.getLogger(ModuleFactory.class.getName());
	private static ModuleFactory instance = null;

	private BundleContext context;

	public static synchronized ModuleFactory getInstance() {
		if (instance == null) {
			instance = new ModuleFactory();
		}
		return instance;
	}

	private ModuleFactory(){

		FrameworkFactory frameworkFactory = ServiceLoader.load(FrameworkFactory.class).iterator().next();
		Map<String, String> config = new HashMap<String, String>();
		config.put(Constants.FRAMEWORK_STORAGE_CLEAN, Constants.FRAMEWORK_STORAGE_CLEAN_ONFIRSTINIT);

		List<String> packages = new ArrayList<String>();

		packages.add("javax.xml.bind");
		packages.add("org.jdesktop.beansbinding");
		packages.add("org.osgi.framework");
		packages.add("de.ravenfly.mle.modulebase");
		packages.add("de.ravenfly.mle.modulebase.filemodel");
		packages.add("de.ravenfly.mle.modulebase.gui");

		StringBuilder buffer = new StringBuilder();
		int i = 0;
		for (String string : packages) {
			buffer.append(i++ > 0?",":"");
			buffer.append(string);
		}

		config.put(Constants.FRAMEWORK_SYSTEMPACKAGES_EXTRA, buffer.toString());
		Framework framework = frameworkFactory.newFramework(config);
		try {
			framework.start();
		} catch (BundleException e) {
			log.log(Level.SEVERE, "Bundle Exception on Start Farmework", e);
		}

		context = framework.getBundleContext();
		List<Bundle> installedBundles = new LinkedList<Bundle>();

		try {
			installedBundles.add(context.installBundle("file:../WDTV Metadata Lib/target/wdtvMetadata-1.0.0-SNAPSHOT.jar"));
			installedBundles.add(context.installBundle("file:../episodeGui/target/episodeGui-1.0.0-SNAPSHOT.jar"));
		} catch (BundleException e) {
			log.log(Level.SEVERE, "Bundle Exception on install Bundles", e);
		}

		for (Bundle bundle : installedBundles) {
			if (bundle.getHeaders().get(Constants.FRAGMENT_HOST) == null){
				try {
					bundle.start();
				} catch (BundleException e) {
					log.log(Level.SEVERE, "Bundle Exception on start Bundle", e);
				}
			}
		}
	}

	public BundleContext getContext() {
		return context;
	}
}
