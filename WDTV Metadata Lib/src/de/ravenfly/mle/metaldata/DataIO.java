package de.ravenfly.mle.metaldata;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import net.xeoh.plugins.base.annotations.Capabilities;
import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.meta.Author;
import net.xeoh.plugins.base.annotations.meta.Version;
import de.ravenfly.mle.metaldata.filemodel.Episode;
import de.ravenfly.mle.modulebase.DataHandler;
import de.ravenfly.mle.modulebase.DataHandlerException;

@Author(name = "Joe Sollich")
@Version(version = 10000)
@PluginImplementation
public class DataIO implements DataHandler<Episode> {

	@Capabilities
	@Override
	public String[] capabilities() {
		return new String[] {"io:load", "io:save"};
	} 

	// "data/Shakugan no Shana.s01e01.xml"
	@Override
	public Episode load(String path) throws DataHandlerException {
		JAXBContext context;

		try {
			context = JAXBContext.newInstance(Episode.class);
		} catch (JAXBException e) {
			throw new DataHandlerException(e);
		}

		Unmarshaller um;
		try {
			um = context.createUnmarshaller();
		} catch (JAXBException e) {
			throw new DataHandlerException(e);
		}

		Episode episode;
		try {
			episode = (Episode) um.unmarshal(new File(path));
		} catch (JAXBException e) {
			throw new DataHandlerException(e);
		}

		return episode;
	}

	@Override
	public void save(Episode model, String path) throws DataHandlerException {

		System.out.println("Path: " + path);
		System.out.println("Model: " + model);
	    JAXBContext context;
		try {
			context = JAXBContext.newInstance(Episode.class);
		} catch (JAXBException e) {
			throw new DataHandlerException(e);
		}
	    Marshaller m;
		try {
			m = context.createMarshaller();
		} catch (JAXBException e) {
			throw new DataHandlerException(e);
		}
	    try {
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		} catch (PropertyException e) {
			throw new DataHandlerException(e);
		}
		
		try {
			m.marshal(model, new File(path));
		} catch (JAXBException e) {
			throw new DataHandlerException(e);
		}
	}
}
