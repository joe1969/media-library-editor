package de.ravenfly.mle.gui.episode;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class FanartTab extends JPanel {

	private static final long serialVersionUID = -4721399435045368888L;

	/**
	 * Create the panel.
	 */
	public FanartTab() {
		setLayout(new BorderLayout(0, 0));

		JLabel label = new JLabel("Fanart");
		add(label, BorderLayout.CENTER);

	}

    public void paintComponent(Graphics g) {
    	super.paintComponent(g);

		try {
			BufferedImage tileImage = ImageIO.read(FanartTab.class.getResource("/background/Background_Paper.jpg"));

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
