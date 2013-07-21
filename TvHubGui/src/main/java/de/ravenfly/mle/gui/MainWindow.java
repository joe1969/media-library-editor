package de.ravenfly.mle.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;

import de.ravenfly.mle.gui.episode.EpisodePanel;
import de.ravenfly.mle.modulebase.DataContextException;

public class MainWindow extends JFrame{

	private final static Logger log = Logger.getLogger(MainWindow.class.getName());
	private static final long serialVersionUID = 9161930539100460062L;

	private JPanel contentPane;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		System.setProperty("java.util.logging.config.file", "logging.properties");
		try {
			LogManager.getLogManager().readConfiguration();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (UnsupportedLookAndFeelException e) {
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
	    
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {

		log.info("Start App");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 867, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		try {
			BundleContext bundleContext = initModules();
			EpisodePanel episodePanel = new EpisodePanel(bundleContext);
			contentPane.add(episodePanel, BorderLayout.CENTER);
		} catch (BundleException e) {
			e.printStackTrace();
		} catch (DataContextException e) {
			e.printStackTrace();
		}
	}

	private BundleContext initModules() throws BundleException{

		FrameworkFactory frameworkFactory = ServiceLoader.load(FrameworkFactory.class).iterator().next();
		Map<String, String> config = new HashMap<String, String>();
		config.put(Constants.FRAMEWORK_SYSTEMPACKAGES_EXTRA, "org.osgi.framework,de.ravenfly.mle.modulebase,de.ravenfly.mle.modulebase.filemodel,javax.xml.bind");
		Framework framework = frameworkFactory.newFramework(config);
		framework.start();

		BundleContext bundleContext = framework.getBundleContext();
		List<Bundle> installedBundles = new LinkedList<Bundle>();

		installedBundles.add(bundleContext.installBundle("file:../WDTV Metadata Lib/target/wdtvMetadata-1.0.0-SNAPSHOT.jar"));

		for (Bundle bundle : installedBundles) {
			if (bundle.getHeaders().get(Constants.FRAGMENT_HOST) == null){
				bundle.start();
			}
		}

		return bundleContext;
	}
}
