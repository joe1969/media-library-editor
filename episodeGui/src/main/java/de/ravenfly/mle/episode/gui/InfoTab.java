package de.ravenfly.mle.episode.gui;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.border.EmptyBorder;

import de.ravenfly.mle.modulebase.DataContext;
import de.ravenfly.mle.modulebase.filemodel.Episode;

import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class InfoTab extends JPanel {

	private static final long serialVersionUID = 7455824459886785329L;
	private final ImageEditor imageEditor = new ImageEditor();
	private InfoEditor infoEditor;

	public InfoTab() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{706, 0, 0};
		gridBagLayout.rowHeights = new int[]{300, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		infoEditor = new InfoEditor();
		infoEditor.setBorder(new EmptyBorder(5, 5, 5, 0));
		GridBagConstraints gbc_infoEditor = new GridBagConstraints();
		gbc_infoEditor.gridheight = 2;
		gbc_infoEditor.insets = new Insets(0, 0, 5, 5);
		gbc_infoEditor.fill = GridBagConstraints.BOTH;
		gbc_infoEditor.gridx = 0;
		gbc_infoEditor.gridy = 0;
		add(infoEditor, gbc_infoEditor);
		GridBagConstraints gbc_imageEditor = new GridBagConstraints();
		gbc_imageEditor.insets = new Insets(5, 0, 5, 5);
		gbc_imageEditor.anchor = GridBagConstraints.NORTH;
		gbc_imageEditor.gridx = 1;
		gbc_imageEditor.gridy = 0;
		add(imageEditor, gbc_imageEditor);
	}

	public void setContext(DataContext<Episode> context){
		infoEditor.setContext(context);
		imageEditor.setContext(context);
	}

    public void paintComponent(Graphics g) {
    	super.paintComponent(g);

		try {
			BufferedImage tileImage = ImageIO.read(InfoTab.class.getResource("/background/Background_Paper.jpg"));

	        int width = getWidth();  
	        int height = getHeight();  
	        int imageW = tileImage.getWidth(this);  
	        int imageH = tileImage.getHeight(this);  
	   
	        // Tile the image to fill our area.  
	        for (int x = 0; x < width; x += imageW) {  
	            for (int y = 0; y < height; y += imageH) {  
	                g.drawImage(tileImage, x, y, this);  
	            }  
	        }  
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
