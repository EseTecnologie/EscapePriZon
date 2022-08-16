package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Sottocasa Michele
 * @version 1.0
 * @class Entity
 * @brief Creazione delle principali variabili e metodi per la gestione di una singola entità
 */
public class Entity extends Thread {
    public GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public boolean[] keys = new boolean[4]; // 0-> red, 1-> green, 2-> white, 3-> purple

    public Entity() {

    }

    /**
     * @param gp Variabile contenente il {@link GamePanel} corrente, sul quale viene visualizzato il gioco
     * @brief Costruttore parametrico
     * @since 1.0
     */
    public Entity(GamePanel gp) {
        this.gp = gp;
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        Arrays.fill(keys, false);
    }

    /**
     * @brief Aggiornamento delle principali logiche di gioco
     * @since 1.0
     */
    public void update() {


        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this, false);
        if (gp.collisionChecker.checkPlayer(this)) {
            gp.aSetter.stopNpc();
            gp.aSetter.setNpc();
            gp.player.setDefaultValues();
            gp.aSetter.setBoots();
        }

        if (!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;

            }
        }

        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1)
                spriteNum = 2;
            else if (spriteNum == 2)
                spriteNum = 1;
            spriteCounter = 0;
        }
    }

    /**
     * Creazione di un file, contenente l'immagine importata. Si procede poi tramite la classe {@link UtilityTool} a
     * ridimensionare l'immagine, per ingrandirla, in modo da renderla visibile più SEMPLICEMENTE
     * @param imagePath path dell'immagine da ridimensionare, esclusa l'estensione
     * @return l'{@link BufferedImage immagine} ridimensionata
     * @brief Importazione e ridimensionamento dell'immagine
     * @since 1.0
     */
    public BufferedImage loadImage(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image;
        try {
            image = ImageIO.read(new File(imagePath + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    /**
     * @param g2 {@link Graphics2D pannello} su cui viene visualizzato il gioco
     * @brief visualizzazione a schermo del mondo e delle varie entità
     * @since 1.0
     */
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > (gp.player.worldX - gp.player.screenX) &&
                worldY + gp.tileSize > (gp.player.worldY - gp.player.screenY) &&
                worldX - gp.tileSize < (gp.player.worldX + gp.player.screenX) &&
                worldY - gp.tileSize < (gp.player.worldY + gp.player.screenY)) {

            switch (direction) {
                case "up" -> {
                    if (spriteNum == 1)
                        image = up1;
                    if (spriteNum == 2)
                        image = up2;
                }
                case "down" -> {
                    if (spriteNum == 1)
                        image = down1;
                    if (spriteNum == 2)
                        image = down2;
                }
                case "left" -> {
                    if (spriteNum == 1)
                        image = left1;
                    if (spriteNum == 2)
                        image = left2;
                }
                case "right" -> {
                    if (spriteNum == 1)
                        image = right1;
                    if (spriteNum == 2)
                        image = right2;
                }
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
