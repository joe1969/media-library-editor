package de.ravenfly.mle.gui.data;

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
import de.ravenfly.mle.modulebase.gui.DataTab;

import java.awt.FlowLayout;

public class DataPanel<T> extends JPanel {

	private static final long serialVersionUID = 1583025667054246775L;

	public static <T> DataPanel<T> newInstance(Class<T> type) throws DataException{
		return new DataPanel<T>();
	}

	private DataPanel() throws DataException{

		setLayout(new BorderLayout(10, 0));
		setBorder(new EmptyBorder(5, 5, 5, 5));

		DataFeatures           features     = DataFeaturesFactory.create();
		DataTab<T>             dataTab      = DataTabFactory.createDataTab();

		DataContextSelector<T> selector     = new DataContextSelector<T>();

		OpenAction<T>          openAction   = new OpenAction<T>();
		LoadAction<T>          loadAction   = new LoadAction<T>();
		SaveAction<T>          saveAction   = new SaveAction<T>();
		ReloadAction<T>        reloadAction = new ReloadAction<T>();

		openAction.addOpenObserver(selector);

		JPanel topPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) topPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(topPanel, BorderLayout.NORTH);

		add(selector, BorderLayout.WEST);

		add(dataTab, BorderLayout.CENTER);
		selector.addContextObserver(dataTab);
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
