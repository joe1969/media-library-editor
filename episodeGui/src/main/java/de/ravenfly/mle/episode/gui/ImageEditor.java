package de.ravenfly.mle.episode.gui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import de.ravenfly.mle.modulebase.ContextObserver;
import de.ravenfly.mle.modulebase.DataContext;
import de.ravenfly.mle.modulebase.DataObserver;
import de.ravenfly.mle.modulebase.filemodel.Episode;

public class ImageEditor extends JPanel implements DataObserver, ContextObserver<Episode> {

	//private static final int WIDTH = 680;
	//private static final int HEIGHT = 1000;
	private static final int WIDTH = 170;
	private static final int HEIGHT = 250;

	private static final String TEXT = "No Image";

	private static final long serialVersionUID = 6365370309490345859L;

	private DataContext<Episode> context;
	private JLabel image;

	public ImageEditor() {
		setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new EmptyBorder(5, 5, 5, 5)));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{70, 0};
		gridBagLayout.rowHeights = new int[]{15, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		image = new JLabel(TEXT);
		Dimension size = new Dimension(WIDTH, HEIGHT); 
		image.setSize(size);
		image.setPreferredSize(size);
		image.setMaximumSize(size);
		image.setMinimumSize(size);
		image.setVerticalAlignment(JLabel.CENTER);
		image.setHorizontalAlignment(JLabel.CENTER);

		GridBagConstraints gbc_lblImage = new GridBagConstraints();
		gbc_lblImage.anchor = GridBagConstraints.NORTH;
		gbc_lblImage.gridx = 0;
		gbc_lblImage.gridy = 0;
		add(image, gbc_lblImage);
	}

	@Override
	public void setContext(DataContext<Episode> context) {
		this.context = context;
		context.addDataObserver(this);
	}

	@Override
	public void openDone() {
		try {
			BufferedImage img = context.getMetathumb();
			if(img != null){
				ImageIcon icon = new ImageIcon(resizeImage(img, WIDTH, HEIGHT));
				image.setIcon(icon);
				image.setText(null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void closeDone() {
		image.setIcon(null);
		image.setText(TEXT);
	}

	@Override
	public void flushDone() {
	}

	@Override
	public void modifiedDone() {
	}

	private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) throws IOException {

		BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();
		return resizedImage;
	 }

}