package de.ravenfly.mle.metaldata;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import de.ravenfly.mle.metaldata.filemodel.Episode;

public class XMLTest {

	public Episode readXML() throws JAXBException, FileNotFoundException{
		JAXBContext context = JAXBContext.newInstance(Episode.class);
		Unmarshaller um = context.createUnmarshaller();
		Episode episode = (Episode) um.unmarshal(new File("data/Shakugan no Shana.s01e01.xml"));
		return episode;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XMLTest test = new XMLTest();
		try {
			Episode episode = test.readXML();
			System.out.println("Eppisode: " + episode);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		} 
	}

}
