package main;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Sottocasa Michele
 * @version 1.0
 * @class UtilityTool
 * @brief extra tools
 */
public class UtilityTool {

    public UtilityTool() {
    }

    /**
     *
     * @param original {@link BufferedImage} a dimensioni originali
     * @param width {@link Integer} larghezza dell'immagine
     * @param height {@link Integer} altezza dell'immagine
     * @return {@link BufferedImage} immagine ingrandita
     * @since 1.0
     */
    public BufferedImage scaleImage(BufferedImage original, Integer width, Integer height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }
}
