package de.ravenfly.mle.gui.osgi;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import de.ravenfly.mle.modulebase.DataContext;
import de.ravenfly.mle.modulebase.DataException;
import de.ravenfly.mle.modulebase.DataHandler;

public class DataContextFactory {

	private final static Logger log = Logger.getLogger(DataContextFactory.class.getName()); 

	public static <T> DataContext<T> createContext(Class<T> clazz) throws DataException{

		BundleContext bundleContext = ModuleFactory.getInstance().getBundleContext();

		DataContext<T> dataContext = new DataContext<T>();

		final ServiceReference<?> reference = bundleContext.getServiceReference(DataHandler.class.getName());
		@SuppressWarnings("unchecked")
		final DataHandler<T> handler = (DataHandler<T>) bundleContext.getService(reference);

		if(handler != null ){
			log.fine("Konsument-ServiceTracker liest DataHandler: " + handler);
        }

		dataContext.setDatahandler(handler);

		try {
			dataContext.setModel(clazz.newInstance());
		} catch (InstantiationException e) {
			log.log(Level.WARNING, "Instantiation Exception", e);
			throw new DataException(e);
		} catch (IllegalAccessException e) {
			log.log(Level.WARNING, "Illegal Access Exception", e);
			throw new DataException(e);
		}

		return dataContext;
	}
}
