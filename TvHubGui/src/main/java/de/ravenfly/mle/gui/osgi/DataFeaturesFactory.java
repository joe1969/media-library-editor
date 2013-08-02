package de.ravenfly.mle.gui.osgi;

import java.util.logging.Logger;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import de.ravenfly.mle.modulebase.DataException;
import de.ravenfly.mle.modulebase.DataFeatures;

public class DataFeaturesFactory {

	private final static Logger log = Logger.getLogger(DataFeaturesFactory.class.getName()); 

	public static DataFeatures create() throws DataException{

		BundleContext bundleContext = ModuleFactory.getInstance().getBundleContext();

		final ServiceReference<?> reference = bundleContext.getServiceReference(DataFeatures.class.getName());
		final DataFeatures features = (DataFeatures) bundleContext.getService(reference);

		if(features != null ){
			log.fine("Konsument-ServiceTracker liest DataFeatures: " + features);
        }

		return features;
	}
}
