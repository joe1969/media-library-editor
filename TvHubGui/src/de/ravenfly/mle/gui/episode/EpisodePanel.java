package de.ravenfly.mle.gui.episode;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.border.EmptyBorder;

import de.ravenfly.mle.gui.episode.EpisodeView;
import de.ravenfly.mle.gui.episode.LoadAction;
import de.ravenfly.mle.gui.episode.ReloadAction;
import de.ravenfly.mle.gui.episode.SaveAction;

import javax.swing.JButton;

import java.awt.FlowLayout;

public class EpisodePanel extends JPanel {

	private static final long serialVersionUID = 1583025667054246775L;

	private final EpisodeView episodeView;

	private EpisodeContext context;

	private LoadAction   loadAction;
	private SaveAction   saveAction;
	private ReloadAction reloadAction;

	public EpisodePanel() {
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		context = new EpisodeContext();

		loadAction   = new LoadAction(context);
		saveAction   = new SaveAction(context);
		reloadAction = new ReloadAction(context);

		episodeView = new EpisodeView(context);
		add(episodeView, BorderLayout.CENTER);

		JPanel topPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) topPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(topPanel, BorderLayout.NORTH);

		if(context.canLoad()){
			JButton btnLoad = new JButton(loadAction);
			loadAction.setParent(this);
			loadAction.setCurrentDirectory("data");
			topPanel.add(btnLoad);
		}

		if(context.canSave()){
			JButton btnSave = new JButton(saveAction);
			saveAction.setParent(this);
			topPanel.add(btnSave);
		}

		JButton btnClose = new JButton(reloadAction);
		reloadAction.setParent(this);
		topPanel.add(btnClose);
	}
}
