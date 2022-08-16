package tile;

import java.awt.image.BufferedImage;

/**
 * @author Sottocasa Michele
 * @version 1.0
 * @class Tile
 * @brief dichiarazione degli attributi di ogni singolo tile utilizzai poi in {@link TileManager}
 */
public class Tile {
    public BufferedImage image;
    public boolean collision = false;
}
