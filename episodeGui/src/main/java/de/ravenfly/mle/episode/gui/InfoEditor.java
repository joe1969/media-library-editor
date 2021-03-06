package de.ravenfly.mle.episode.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import org.jdesktop.beansbinding.AbstractBindingListener;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.BindingListener;
import org.jdesktop.beansbinding.Bindings;

import de.ravenfly.mle.modulebase.ContextObserver;
import de.ravenfly.mle.modulebase.DataContext;
import de.ravenfly.mle.modulebase.DataObserver;
import de.ravenfly.mle.episode.filemodel.Episode;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

public class InfoEditor extends JPanel implements DataObserver, ContextObserver<Episode> {

	private static final long serialVersionUID = 2208273354176814048L;
	private final static Logger log = Logger.getLogger(InfoEditor.class.getName()); 

	private DataContext<Episode> context;

	private BindingGroup m_bindingGroup;
	private JTextArea actorJTextField;
	private JTextField directorJTextField;
	private JTextField episodeNameJTextField;
	private JSpinner episodeNumberJSlider;
	private JTextField firstairedJTextField;
	private JTextArea genreJTextField;
	private JSpinner idJSlider;
	private JTextArea overviewJTextField;
	private JSpinner runtimeJSlider;
	private JSpinner seasonNumberJSlider;
	private JTextField seriesNameJTextField;
	private JTextField titleJTextField;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;

	private BindingListener bindingListener;

	public InfoEditor() {
		super();

		setOpaque(false);

		log.info("Start Info Editor");

		bindingListener = new AbstractBindingListener(){

			@SuppressWarnings("rawtypes")
			@Override
			public void synced(Binding arg0) {
				context.setModified(true);
			}
		};

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0E-4 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0E-4 };
		setLayout(gridBagLayout);
		
		JLabel idLabel = new JLabel("Id:");
		GridBagConstraints labelGbc_6 = new GridBagConstraints();
		labelGbc_6.anchor = GridBagConstraints.WEST;
		labelGbc_6.insets = new Insets(0, 0, 5, 5);
		labelGbc_6.gridx = 0;
		labelGbc_6.gridy = 0;
		add(idLabel, labelGbc_6);

		idJSlider = new JSpinner();
		GridBagConstraints componentGbc_6 = new GridBagConstraints();
		componentGbc_6.insets = new Insets(0, 0, 5, 5);
		componentGbc_6.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_6.gridx = 1;
		componentGbc_6.gridy = 0;
		add(idJSlider, componentGbc_6);

		JLabel seasonNumberLabel = new JLabel("Season #:");
		GridBagConstraints labelGbc_9 = new GridBagConstraints();
		labelGbc_9.anchor = GridBagConstraints.WEST;
		labelGbc_9.insets = new Insets(0, 5, 5, 5);
		labelGbc_9.gridx = 2;
		labelGbc_9.gridy = 0;
		add(seasonNumberLabel, labelGbc_9);

		seasonNumberJSlider = new JSpinner();
		GridBagConstraints componentGbc_9 = new GridBagConstraints();
		componentGbc_9.insets = new Insets(0, 0, 5, 5);
		componentGbc_9.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_9.gridx = 3;
		componentGbc_9.gridy = 0;
		add(seasonNumberJSlider, componentGbc_9);

		JLabel episodeNumberLabel = new JLabel("Episode #:");
		GridBagConstraints labelGbc_3 = new GridBagConstraints();
		labelGbc_3.anchor = GridBagConstraints.WEST;
		labelGbc_3.insets = new Insets(0, 5, 5, 5);
		labelGbc_3.gridx = 4;
		labelGbc_3.gridy = 0;
		add(episodeNumberLabel, labelGbc_3);

		episodeNumberJSlider = new JSpinner();
		GridBagConstraints componentGbc_3 = new GridBagConstraints();
		componentGbc_3.insets = new Insets(0, 0, 5, 5);
		componentGbc_3.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_3.gridx = 5;
		componentGbc_3.gridy = 0;
		add(episodeNumberJSlider, componentGbc_3);

		JLabel runtimeLabel = new JLabel("Runtime:");
		GridBagConstraints labelGbc_8 = new GridBagConstraints();
		labelGbc_8.anchor = GridBagConstraints.WEST;
		labelGbc_8.insets = new Insets(0, 5, 5, 5);
		labelGbc_8.gridx = 6;
		labelGbc_8.gridy = 0;
		add(runtimeLabel, labelGbc_8);

		runtimeJSlider = new JSpinner();
		GridBagConstraints componentGbc_8 = new GridBagConstraints();
		componentGbc_8.insets = new Insets(0, 0, 5, 5);
		componentGbc_8.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_8.gridx = 7;
		componentGbc_8.gridy = 0;
		add(runtimeJSlider, componentGbc_8);

		JLabel firstairedLabel = new JLabel("Firstaired:");
		GridBagConstraints labelGbc_4 = new GridBagConstraints();
		labelGbc_4.anchor = GridBagConstraints.WEST;
		labelGbc_4.insets = new Insets(0, 5, 5, 5);
		labelGbc_4.gridx = 8;
		labelGbc_4.gridy = 0;
		add(firstairedLabel, labelGbc_4);

		firstairedJTextField = new JTextField();
		GridBagConstraints componentGbc_4 = new GridBagConstraints();
		componentGbc_4.insets = new Insets(0, 0, 5, 0);
		componentGbc_4.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_4.gridx = 9;
		componentGbc_4.gridy = 0;
		add(firstairedJTextField, componentGbc_4);

		JLabel titleLabel = new JLabel("Title:");
		GridBagConstraints labelGbc_11 = new GridBagConstraints();
		labelGbc_11.anchor = GridBagConstraints.WEST;
		labelGbc_11.insets = new Insets(0, 0, 5, 5);
		labelGbc_11.gridx = 0;
		labelGbc_11.gridy = 1;
		add(titleLabel, labelGbc_11);

		titleJTextField = new JTextField();
		GridBagConstraints componentGbc_11 = new GridBagConstraints();
		componentGbc_11.gridwidth = 9;
		componentGbc_11.insets = new Insets(0, 0, 5, 0);
		componentGbc_11.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_11.gridx = 1;
		componentGbc_11.gridy = 1;
		add(titleJTextField, componentGbc_11);

		JLabel seriesNameLabel = new JLabel("Series Name:");
		GridBagConstraints labelGbc_10 = new GridBagConstraints();
		labelGbc_10.anchor = GridBagConstraints.WEST;
		labelGbc_10.insets = new Insets(0, 0, 5, 5);
		labelGbc_10.gridx = 0;
		labelGbc_10.gridy = 2;
		add(seriesNameLabel, labelGbc_10);

		seriesNameJTextField = new JTextField();
		GridBagConstraints componentGbc_10 = new GridBagConstraints();
		componentGbc_10.gridwidth = 9;
		componentGbc_10.insets = new Insets(0, 0, 5, 0);
		componentGbc_10.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_10.gridx = 1;
		componentGbc_10.gridy = 2;
		add(seriesNameJTextField, componentGbc_10);

		JLabel episodeNameLabel = new JLabel("Episode Name:");
		GridBagConstraints labelGbc_2 = new GridBagConstraints();
		labelGbc_2.anchor = GridBagConstraints.WEST;
		labelGbc_2.insets = new Insets(0, 0, 5, 5);
		labelGbc_2.gridx = 0;
		labelGbc_2.gridy = 3;
		add(episodeNameLabel, labelGbc_2);

		episodeNameJTextField = new JTextField();
		GridBagConstraints componentGbc_2 = new GridBagConstraints();
		componentGbc_2.gridwidth = 9;
		componentGbc_2.insets = new Insets(0, 0, 5, 0);
		componentGbc_2.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_2.gridx = 1;
		componentGbc_2.gridy = 3;
		add(episodeNameJTextField, componentGbc_2);
		
		JLabel directorLabel = new JLabel("Director:");
		GridBagConstraints labelGbc_1 = new GridBagConstraints();
		labelGbc_1.anchor = GridBagConstraints.WEST;
		labelGbc_1.insets = new Insets(0, 0, 5, 5);
		labelGbc_1.gridx = 0;
		labelGbc_1.gridy = 4;
		add(directorLabel, labelGbc_1);

		directorJTextField = new JTextField();
		GridBagConstraints componentGbc_1 = new GridBagConstraints();
		componentGbc_1.gridwidth = 5;
		componentGbc_1.insets = new Insets(0, 0, 5, 5);
		componentGbc_1.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_1.gridx = 1;
		componentGbc_1.gridy = 4;
		add(directorJTextField, componentGbc_1);

		JLabel genreLabel = new JLabel("Genre:");
		GridBagConstraints labelGbc_5 = new GridBagConstraints();
		labelGbc_5.anchor = GridBagConstraints.NORTHWEST;
		labelGbc_5.insets = new Insets(0, 5, 5, 5);
		labelGbc_5.gridx = 6;
		labelGbc_5.gridy = 4;
		add(genreLabel, labelGbc_5);

		JLabel actorLabel = new JLabel("Actor:");
		GridBagConstraints labelGbc_0 = new GridBagConstraints();
		labelGbc_0.anchor = GridBagConstraints.NORTHWEST;
		labelGbc_0.insets = new Insets(0, 0, 5, 5);
		labelGbc_0.gridx = 0;
		labelGbc_0.gridy = 5;
		add(actorLabel, labelGbc_0);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 5;
		add(scrollPane, gbc_scrollPane);

		actorJTextField = new JTextArea();
		scrollPane.setViewportView(actorJTextField);

		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 2;
		gbc_scrollPane_1.gridwidth = 3;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.gridx = 7;
		gbc_scrollPane_1.gridy = 4;
		add(scrollPane_1, gbc_scrollPane_1);

		genreJTextField = new JTextArea();
		scrollPane_1.setViewportView(genreJTextField);

		JLabel overviewLabel = new JLabel("Overview:");
		GridBagConstraints labelGbc_7 = new GridBagConstraints();
		labelGbc_7.anchor = GridBagConstraints.NORTHWEST;
		labelGbc_7.insets = new Insets(0, 0, 0, 5);
		labelGbc_7.gridx = 0;
		labelGbc_7.gridy = 6;
		add(overviewLabel, labelGbc_7);

		scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridwidth = 9;
		gbc_scrollPane_2.gridx = 1;
		gbc_scrollPane_2.gridy = 6;
		add(scrollPane_2, gbc_scrollPane_2);

		overviewJTextField = new JTextArea();
		scrollPane_2.setViewportView(overviewJTextField);
		overviewJTextField.setWrapStyleWord(true);
		overviewJTextField.setLineWrap(true);
	}

	@Override
	public void setContext(DataContext<Episode> context){
		this.context = context;
		context.addDataObserver(this);
	}

	@Override
	public void openDone() {
		if (context.isOpen()) {
			if (!context.isModified()) {

				if (m_bindingGroup != null) {
					m_bindingGroup.unbind();
					m_bindingGroup = null;
				}
				m_bindingGroup = initDataBindings();
				m_bindingGroup.addBindingListener(bindingListener);
			}
			updateUI();
		}
	}

	@Override
	public void closeDone() {
		
		if (m_bindingGroup != null) {
			m_bindingGroup.removeBindingListener(bindingListener);
			m_bindingGroup.unbind();
			m_bindingGroup = null;
		}
	}

	@Override
	public void flushDone() {
	}

	@Override
	public void modifiedDone() {
	}

	protected BindingGroup initDataBindings() {

		Episode episode = context.getModel();

		BeanProperty<Episode, String> directorProperty = BeanProperty.create("director");
		BeanProperty<JTextField, String> textProperty_1 = BeanProperty.create("text");
		AutoBinding<Episode, String, JTextField, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, episode, directorProperty, directorJTextField, textProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<Episode, String> episodeNameProperty = BeanProperty.create("episodeName");
		BeanProperty<JTextField, String> textProperty_2 = BeanProperty.create("text");
		AutoBinding<Episode, String, JTextField, String> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, episode, episodeNameProperty, episodeNameJTextField, textProperty_2);
		autoBinding_2.bind();
		//
		BeanProperty<Episode, String> firstairedProperty = BeanProperty.create("firstaired");
		BeanProperty<JTextField, String> textProperty_3 = BeanProperty.create("text");
		AutoBinding<Episode, String, JTextField, String> autoBinding_4 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, episode, firstairedProperty, firstairedJTextField, textProperty_3);
		autoBinding_4.bind();
		//
		BeanProperty<Episode, String> overviewProperty = BeanProperty.create("overview");
		BeanProperty<JTextArea, String> textProperty_5 = BeanProperty.create("text");
		AutoBinding<Episode, String, JTextArea, String> autoBinding_7 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, episode, overviewProperty, overviewJTextField, textProperty_5);
		autoBinding_7.bind();
		//
		BeanProperty<Episode, String> seriesNameProperty = BeanProperty.create("seriesName");
		BeanProperty<JTextField, String> textProperty_6 = BeanProperty.create("text");
		AutoBinding<Episode, String, JTextField, String> autoBinding_10 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, episode, seriesNameProperty, seriesNameJTextField, textProperty_6);
		autoBinding_10.bind();
		//
		BeanProperty<Episode, String> titleProperty = BeanProperty.create("title");
		BeanProperty<JTextField, String> textProperty_7 = BeanProperty.create("text");
		AutoBinding<Episode, String, JTextField, String> autoBinding_11 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, episode, titleProperty, titleJTextField, textProperty_7);
		autoBinding_11.bind();
		//
		BeanProperty<Episode, Integer> episodeBeanProperty = BeanProperty.create("seasonNumber");
		BeanProperty<JSpinner, Object> jSpinnerBeanProperty = BeanProperty.create("value");
		AutoBinding<Episode, Integer, JSpinner, Object> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, episode, episodeBeanProperty, seasonNumberJSlider, jSpinnerBeanProperty);
		autoBinding_3.bind();
		//
		BeanProperty<Episode, Integer> episodeBeanProperty_1 = BeanProperty.create("id");
		BeanProperty<JSpinner, Object> jSpinnerBeanProperty_1 = BeanProperty.create("value");
		AutoBinding<Episode, Integer, JSpinner, Object> autoBinding_6 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, episode, episodeBeanProperty_1, idJSlider, jSpinnerBeanProperty_1);
		autoBinding_6.bind();
		//
		BeanProperty<Episode, Integer> episodeBeanProperty_2 = BeanProperty.create("episodeNumber");
		BeanProperty<JSpinner, Object> jSpinnerBeanProperty_2 = BeanProperty.create("value");
		AutoBinding<Episode, Integer, JSpinner, Object> autoBinding_8 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, episode, episodeBeanProperty_2, episodeNumberJSlider, jSpinnerBeanProperty_2);
		autoBinding_8.bind();
		//
		BeanProperty<Episode, Integer> episodeBeanProperty_3 = BeanProperty.create("runtime");
		BeanProperty<JSpinner, Object> jSpinnerBeanProperty_3 = BeanProperty.create("value");
		AutoBinding<Episode, Integer, JSpinner, Object> autoBinding_9 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, episode, episodeBeanProperty_3, runtimeJSlider, jSpinnerBeanProperty_3);
		autoBinding_9.bind();
		//
		BindingGroup bindingGroup = new BindingGroup();
		//
		bindingGroup.addBinding(autoBinding_1);
		bindingGroup.addBinding(autoBinding_2);
		bindingGroup.addBinding(autoBinding_4);
		bindingGroup.addBinding(autoBinding_7);
		bindingGroup.addBinding(autoBinding_10);
		bindingGroup.addBinding(autoBinding_11);
		bindingGroup.addBinding(autoBinding_3);
		bindingGroup.addBinding(autoBinding_6);
		bindingGroup.addBinding(autoBinding_8);
		bindingGroup.addBinding(autoBinding_9);
		return bindingGroup;
	}
}
