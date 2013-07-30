package de.ravenfly.mle.gui;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class VideoFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String name = f.getName();
        String extension = name.substring(name.lastIndexOf(".") +1).toLowerCase();
        if (extension != null) {
            if (extension.equals("flv") || extension.equals("mp4") || extension.equals("mkv")) {
            	return true;
            } else {
                return false;
            }
        }
        return false;
	}

	@Override
	public String getDescription() {
		return "Video File (*.flv, *.mp4, *.mkv)";
	}
}
