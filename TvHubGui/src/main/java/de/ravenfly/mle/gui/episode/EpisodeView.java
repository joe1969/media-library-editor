package de.ravenfly.mle.gui.episode;

import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import de.ravenfly.mle.modulebase.DataContext;
import de.ravenfly.mle.modulebase.filemodel.Episode;

public class EpisodeView extends JTabbedPane {

	private static final long serialVersionUID = 7767489136867383429L;

	private InfoTab infoTab;

	/**
	 * Create the panel.
	 */
	public EpisodeView() {
		infoTab = new InfoTab();
		addTab("Info", infoTab);
		setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = new JPanel();
		addTab("Fanart", panel2);
		setMnemonicAt(1, KeyEvent.VK_2);
	}

	public void setContext(DataContext<Episode> context){
		infoTab.setContext(context);
	}
}
