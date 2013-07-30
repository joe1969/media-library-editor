package de.ravenfly.mle.metadata;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import de.ravenfly.mle.modulebase.DataException;
import de.ravenfly.mle.modulebase.DataHandler;
import de.ravenfly.mle.modulebase.filemodel.Episode;

public class DataIO implements DataHandler<Episode>{

    @Override
	public boolean canLoad() {
		return true;
	}

	@Override
	public boolean canSave() {
		return true;
	}

	// "data/Shakugan no Shana.s01e01.xml"
	@Override
	public Episode load(String path) throws DataException {
		JAXBContext context;

		try {
			context = JAXBContext.newInstance(Episode.class);
		} catch (JAXBException e) {
			throw new DataException(e);
		}

		Unmarshaller um;
		try {
			um = context.createUnmarshaller();
		} catch (JAXBException e) {
			throw new DataException(e);
		}

		Episode episode;
		try {
			episode = (Episode) um.unmarshal(new File(path));
		} catch (JAXBException e) {
			throw new DataException(e);
		}

		return episode;
	}

	@Override
	public void save(Episode model, String path) throws DataException {

		System.out.println("Path: " + path);
		System.out.println("Model: " + model);
	    JAXBContext context;
		try {
			context = JAXBContext.newInstance(Episode.class);
		} catch (JAXBException e) {
			throw new DataException(e);
		}
	    Marshaller m;
		try {
			m = context.createMarshaller();
		} catch (JAXBException e) {
			throw new DataException(e);
		}
	    try {
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		} catch (PropertyException e) {
			throw new DataException(e);
		}
		
		try {
			m.marshal(model, new File(path));
		} catch (JAXBException e) {
			throw new DataException(e);
		}
	}
}
