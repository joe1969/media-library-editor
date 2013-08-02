package de.ravenfly.mle.gui.osgi;

import java.util.logging.Logger;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import de.ravenfly.mle.modulebase.DataException;
import de.ravenfly.mle.modulebase.gui.DataTab;

public class DataTabFactory {

	private final static Logger log = Logger.getLogger(DataTabFactory.class.getName()); 

	public static <T> DataTab<T> createDataTab() throws DataException{

		BundleContext bundleContext = ModuleFactory.getInstance().getBundleContext();

		final ServiceReference<?> reference = bundleContext.getServiceReference(DataTab.class.getName());
		@SuppressWarnings("unchecked")
		final DataTab<T> handler = (DataTab<T>) bundleContext.getService(reference);

		if(handler != null ){
			log.fine("Konsument-ServiceTracker liest DataTab: " + handler);
        }

		return handler;
	}
}
