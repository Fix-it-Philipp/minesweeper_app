import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {

    private BufferedImage backgroundImage;

    public BackgroundPanel(String imagePath) {
        try {
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Zeichne das Hintergrundbild
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(backgroundImage.getWidth(), backgroundImage.getHeight());
    }
}