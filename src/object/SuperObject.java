package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Colombo Federico
 * @version 1.0
 * @class SuperObject
 * @brief Gestione generale degli oggetti generici
 */
public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    /**
     * metodo per disegnare gli oggetti sul {@link GamePanel}
     *
     * @param g2 parametro contenente il pannello grafico sul quale verrà disegnato il {@link entity.Player}
     * @param gp parametro {@link GamePanel} per controllare se gli oggetti sono all'interno del campo visiovo del {@link entity.Player}
     * @brief medoto draw()
     * @since 1.0
     */
    public void draw(Graphics2D g2, GamePanel gp) {

        if (worldX != 0) {
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > (gp.player.worldX - gp.player.screenX) &&
                    worldY + gp.tileSize > (gp.player.worldY - gp.player.screenY) &&
                    worldX - gp.tileSize < (gp.player.worldX + gp.player.screenX) &&
                    worldY - gp.tileSize < (gp.player.worldY + gp.player.screenY)) {

                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
        }


    }
}
