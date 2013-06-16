package de.ravenfly.wdtv.gui.episode;

import java.io.File;

import de.ravenfly.wdtv.metaldata.DataIO;
import de.ravenfly.wdtv.metaldata.filemodel.Episode;
import de.ravenfly.wdtv.modulebase.DataContext;
import de.ravenfly.wdtv.modulebase.DataHandlerException;
import de.ravenfly.wdtv.modulebase.DataObserver;

public class EpisodeContext {

	private DataContext<Episode> context;

	public EpisodeContext() {
		super();
		context = new DataContext<Episode>();

		context.setDatahandler(new DataIO());
		context.setModel(new Episode());
	}

	public EpisodeContext(DataContext<Episode> context) {
		super();
		this.context = context;
	}
	
	public Episode getEpisode(){
		return context.getModel();
	}

	public DataContext<Episode> getContext() {
		return context;
	}

	public void setContext(DataContext<Episode> context) {
		this.context = context;
	}

	public boolean canLoad(){
		return context.getDatahandler().canLoad();
	}

	public boolean canSave(){
		return context.getDatahandler().canSave();
	}

	public boolean isLoaded() {
		return context.isLoaded();
	}

	public void setModified(boolean modified){
		context.setModified(modified);
	}

	public boolean isModified(){
		return context.isModified();
	}

	public void addDataObserver(DataObserver observer){
		context.addDataObserver(observer);
	}

	public void load(){
		if(context != null && context.isModified()){
			load(context.getFile());
		}
	}

	public void load(File file){

		context.setFile(file);

		try {
			Episode model = context.getDatahandler().load(file.getAbsolutePath());
			context.setModel(model);
			context.setLoaded(true);
			context.setModified(false);
		} catch (DataHandlerException ex) {
			ex.printStackTrace();
		}

		context.fireDone();
	}

	public void save(){
		if(context != null && context.isModified()){
			System.out.println("My Save Data Context: " + context);
			try {
				context.getDatahandler().save(context.getModel(), context.getFile().getAbsolutePath());
				context.setModified(false);
			} catch (DataHandlerException ex) {
				ex.printStackTrace();
			}
			context.fireDone();
		}
	}
}
