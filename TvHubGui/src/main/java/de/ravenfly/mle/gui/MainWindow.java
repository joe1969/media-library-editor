package de.ravenfly.mle.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import de.ravenfly.mle.gui.data.DataPanel;
import de.ravenfly.mle.gui.osgi.DataFeaturesFactory;
import de.ravenfly.mle.modulebase.DataException;
import de.ravenfly.mle.modulebase.DataFeatures;

public class MainWindow extends JFrame{

	private final static Logger log = Logger.getLogger(MainWindow.class.getName());
	private static final long serialVersionUID = 9161930539100460062L;

	private JPanel contentPane;

	public static void main(String[] args) {

		System.setProperty("java.util.logging.config.file", "config/logging.properties");
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
		setBounds(100, 100, 1200, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		try {

			DataFeatures features = DataFeaturesFactory.create();
			DataPanel<?> dataPanel = DataPanel.newInstance(features.getModelClass());
			contentPane.add(dataPanel, BorderLayout.CENTER);

		} catch (DataException e) {
			e.printStackTrace();
		}
	}
}
