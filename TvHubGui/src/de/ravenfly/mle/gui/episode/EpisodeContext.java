package de.ravenfly.mle.gui.episode;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import net.xeoh.plugins.base.PluginInformation;
import net.xeoh.plugins.base.PluginInformation.Information;
import net.xeoh.plugins.base.PluginManager;
import net.xeoh.plugins.base.impl.PluginManagerFactory;
import de.ravenfly.mle.metaldata.filemodel.Episode;
import de.ravenfly.mle.modulebase.DataContext;
import de.ravenfly.mle.modulebase.DataHandler;
import de.ravenfly.mle.modulebase.DataHandlerException;
import de.ravenfly.mle.modulebase.DataObserver;

public class EpisodeContext {

	private DataContext<Episode> context;
	private PluginInformation pluginInfo;

	@SuppressWarnings("unchecked")
	public EpisodeContext() {
		super();
		context = new DataContext<Episode>();

		PluginManager pm = PluginManagerFactory.createPluginManager();

		try {
			pm.addPluginsFrom(new URI("classpath://*"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		pluginInfo = pm.getPlugin(PluginInformation.class);
		DataHandler<Episode> dh = pm.getPlugin(DataHandler.class);
		
		String authors = (new ArrayList<String>(pluginInfo.getInformation(Information.AUTHORS, dh))).get(0);
		List<String> capabilities = new ArrayList<String>(pluginInfo.getInformation(Information.CAPABILITIES, dh));
		String classpathOrigin = (new ArrayList<String>(pluginInfo.getInformation(Information.CLASSPATH_ORIGIN, dh))).get(0);
		String version = (new ArrayList<String>(pluginInfo.getInformation(Information.VERSION, dh))).get(0);

		System.out.println("Authors: " + authors);
		System.out.println("Capabilities: " + capabilities);
		System.out.println("Classpath Origin: " + classpathOrigin);
		System.out.println("Version: " + version);

		context.setDatahandler(dh);
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
		List<String> capabilities = new ArrayList<String>(pluginInfo.getInformation(Information.CAPABILITIES, context.getDatahandler()));
		return capabilities.contains("io:load");
	}

	public boolean canSave(){
		List<String> capabilities = new ArrayList<String>(pluginInfo.getInformation(Information.CAPABILITIES, context.getDatahandler()));
		return capabilities.contains("io:save");
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
