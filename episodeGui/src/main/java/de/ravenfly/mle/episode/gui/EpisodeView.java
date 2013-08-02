package de.ravenfly.mle.episode.gui;

import java.awt.event.KeyEvent;

import de.ravenfly.mle.modulebase.DataContext;
import de.ravenfly.mle.modulebase.filemodel.Episode;
import de.ravenfly.mle.modulebase.gui.DataTab;

public class EpisodeView extends DataTab<Episode> {

	private static final long serialVersionUID = 7767489136867383429L;

	private InfoTab infoTab;
	private FanartTab fanartTab;

	/**
	 * Create the panel.
	 */
	public EpisodeView() {
		infoTab = new InfoTab();
		addTab("Info", infoTab);
		setMnemonicAt(0, KeyEvent.VK_1);

		fanartTab = new FanartTab();
		addTab("Fanart", fanartTab);
		setMnemonicAt(1, KeyEvent.VK_2);
	}

	@Override
	public void setContext(DataContext<Episode> context){
		infoTab.setContext(context);
	}
}
