package ca.ucalgary.edu.ensf380;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MapPanel extends JPanel {
    private BufferedImage mapImage;

    public MapPanel() throws IOException {
        mapImage = ImageIO.read(getClass().getResource("/maps/Map.png"));
        if (mapImage == null) {
            throw new IOException("Image file not found: /maps/Map.png");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mapImage != null) {
            g.drawImage(mapImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
