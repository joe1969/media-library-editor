package de.ravenfly.mle.gui.episode;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import org.osgi.framework.BundleContext;

import de.ravenfly.mle.gui.actions.LoadAction;
import de.ravenfly.mle.gui.actions.ReloadAction;
import de.ravenfly.mle.gui.actions.SaveAction;
import de.ravenfly.mle.gui.episode.EpisodeView;
import de.ravenfly.mle.modulebase.DataContext;
import de.ravenfly.mle.modulebase.DataContextException;
import de.ravenfly.mle.modulebase.filemodel.Episode;

import java.awt.FlowLayout;

public class EpisodePanel extends JPanel {

	private static final long serialVersionUID = 1583025667054246775L;

	private final EpisodeView episodeView;

	private DataContext<Episode> context;

	private LoadAction   loadAction;
	private SaveAction   saveAction;
	private ReloadAction reloadAction;

	public EpisodePanel(BundleContext bundleContext) throws DataContextException{
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		context = DataContext.createContext(Episode.class, bundleContext);

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
