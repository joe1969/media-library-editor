package de.ravenfly.wdtv.gui;

import java.io.File;

import javax.swing.filechooser.FileFilter;


public class XmlFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String filename = f.getName();
        String extension = filename.substring(filename.lastIndexOf(".") +1).toLowerCase();
        if (extension != null) {
            if (extension.equals("xml")) {
            	return true;
            } else {
                return false;
            }
        }
        return false;
	}

	@Override
	public String getDescription() {
		return "XML File (*.xml)";
	}


}
