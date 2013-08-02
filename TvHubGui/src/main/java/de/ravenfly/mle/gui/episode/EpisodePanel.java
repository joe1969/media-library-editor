package de.ravenfly.mle.gui.episode;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import de.ravenfly.mle.gui.actions.LoadAction;
import de.ravenfly.mle.gui.actions.ReloadAction;
import de.ravenfly.mle.gui.actions.SaveAction;
import de.ravenfly.mle.gui.open.OpenAction;
import de.ravenfly.mle.gui.osgi.DataTabFactory;
import de.ravenfly.mle.gui.osgi.DataFeaturesFactory;
import de.ravenfly.mle.modulebase.DataException;
import de.ravenfly.mle.modulebase.DataFeatures;
import de.ravenfly.mle.modulebase.filemodel.Episode;
import de.ravenfly.mle.modulebase.gui.DataTab;

import java.awt.FlowLayout;

public class EpisodePanel extends JPanel {

	private static final long serialVersionUID = 1583025667054246775L;

	public EpisodePanel() throws DataException{

		setLayout(new BorderLayout(10, 0));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		DataFeatures                 features     = DataFeaturesFactory.create();

		DataContextSelector<Episode> selector     = new DataContextSelector<Episode>();
		final DataTab<Episode>       tab          = DataTabFactory.createDataTab(Episode.class);

		OpenAction                   openAction   = new OpenAction();
		LoadAction<Episode>          loadAction   = new LoadAction<Episode>();
		SaveAction<Episode>          saveAction   = new SaveAction<Episode>();
		ReloadAction<Episode>        reloadAction = new ReloadAction<Episode>();

		openAction.addOpenObserver(selector);

		JPanel topPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) topPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(topPanel, BorderLayout.NORTH);

		add(selector, BorderLayout.WEST);

		add(tab, BorderLayout.CENTER);
		selector.addContextObserver(tab);
		selector.addContextObserver(loadAction);
		selector.addContextObserver(saveAction);
		selector.addContextObserver(reloadAction);

		JButton btnOpen = new JButton(openAction);
		openAction.setParent(this);
		topPanel.add(btnOpen);

		if(features.canLoad()){
			JButton btnLoad = new JButton(loadAction);
			loadAction.setParent(this);
			topPanel.add(btnLoad);
		}

		if(features.canSave()){
			JButton btnSave = new JButton(saveAction);
			saveAction.setParent(this);
			topPanel.add(btnSave);
		}

		JButton btnClose = new JButton(reloadAction);
		reloadAction.setParent(this);
		topPanel.add(btnClose);
	}
}
