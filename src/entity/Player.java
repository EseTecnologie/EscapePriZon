/**
 * @author federico colombo
 * @version 1.0
 * @file Player.java
 * @brief gestione della classe player con estensione all Entity
 */
package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Colombo Federico
 * @version 1.0
 * @class Player
 * @brief Gestione dei movimenti del player
 */
public class Player extends Entity {
    /**
     * GamePanel per aggiornare le informazioni del player
     *
     * @since 1.0
     */
    GamePanel gp;
    /**
     * KeyHandler per effettuare i movimenti del player
     *
     * @since 1.0
     */
    KeyHandler keyH;
    /**
     * attributo per dichiarare la posizione sulla corditata X
     *
     * @since 1.0
     */
    public final int screenX;
    /**
     * attributo per dichiarare la posizione sulla corditata Y
     *
     * @since 1.0
     */
    public final int screenY;

    /**
     * metodo che inizializza la posizione dello screen e il retangolo nel quale sara presente il player
     * richiama i metodi setDefaultValues per assegnare la posizione del player nel world e il getPlayerImage per
     * assegnare le immagini al player
     *
     * @param gp   GamePanel sul quale viene caricato il player
     * @param keyH KeyHandler per utilizzare il keylistener
     * @brief costruttore parametrico del player
     * @since 1.0
     */
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(16, 24, 16, 16);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    /**
     * metodo per inizializzare i valori di default del player
     * le cordinate X e Y
     * la velocità
     * la direzione di partenza
     *
     * @brief metodo setDefaultValues
     * @since 1.0
     */
    public void setDefaultValues() {
        worldX = gp.tileSize * 68;
        worldY = gp.tileSize * 32;
        speed = 4;
        direction = "down";
    }

    /**
     * metodo per assegnare le immagini per i movimenti al player passandogli il path della loro posizione
     *
     * @brief getPlayerImage
     * @since 1.0
     */

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(new File("resources/player/boy_up_1.png"));
            up2 = ImageIO.read(new File("resources/player/boy_up_2.png"));
            down1 = ImageIO.read(new File("resources/player/boy_down_1.png"));
            down2 = ImageIO.read(new File("resources/player/boy_down_2.png"));
            left1 = ImageIO.read(new File("resources/player/boy_left_1.png"));
            left2 = ImageIO.read(new File("resources/player/boy_left_2.png"));
            right1 = ImageIO.read(new File("resources/player/boy_right_1.png"));
            right2 = ImageIO.read(new File("resources/player/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * metodo che controlla se vengono premuti i tasti sulla tastiera se i tasti sono
     * stati premuti richiama il metodo simulateWalking() per aggiornare la posizione del player
     *
     * @brief medoto update()
     * @since 1.0
     */


    public void update() {
        if (keyH.upPressed) {
            direction = "up";
            simulateWalking();
        } else if (keyH.downPressed) {
            direction = "down";
            simulateWalking();
        } else if (keyH.rightPressed) {
            direction = "right";
            simulateWalking();
        } else if (keyH.leftPressed) {
            direction = "left";
            simulateWalking();
        }
    }

    /**
     * metodo per raccogliere gli oggetti che si trovano nella mappa contorllando
     * index che viene passato come parametro dal CollisionChecker
     *
     * @param index parametro per controllare i diversi tipi di oggetti che vengono trovati
     * @brief metodo pickUpOnject
     * @since 1.0
     */
    public void pickUpOnject(int index) {
        if (index != 999) {
            String objetName = gp.obj[index].name;
            switch (objetName) {
                case "redKey" -> getKey(0, "Red Key!", index, Color.red);
                case "GreenKey" -> getKey(1, "Green Key!", index, Color.green);
                case "PurpleKey" -> getKey(2, "Purple Key!", index, Color.magenta);
                case "WhiteKey" -> getKey(3, "Wite Key!", index, Color.white);
                case "boostspeed" -> {
                    speed += 2;
                    gp.obj[index] = null;
                    gp.ui.showMessage("Speed Boosted!", Color.orange);
                }
            }
        }
    }

    /**
     * @param keyID      l'ID della chiave ottenuta
     * @param textToShow il testo da visualizzare a schermo
     * @param index      l'indice dell'oggetto
     * @param color      il colore del testo da visualizzare a schermo
     * @brief viene segnato nell'array delle chiavi, quale chiave è stata presa e viene rimossa la visualizzazione della
     * chiave ottenuta
     * @since 1.0
     */
    private void getKey(int keyID, String textToShow, int index, Color color) {
        keys[keyID] = true;
        gp.obj[index] = null;
        gp.ui.showMessage(textToShow, color);
    }

    /**
     * metodo per lo scambio delle immagini del player a seconda
     * della della direzione in cui deve andare e disegna l'immagine del player sul
     * gamePanel
     *
     * @param g parametro contenente il pannello grafico sul quale verrà dedisegnato il player
     * @brief medoto draw()
     * @since 1.0
     */

    public void draw(Graphics2D g) {
        BufferedImage image = null;

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
        g.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    /**
     * metodo che chiama il metodo checkTile per controllare la collisione con le tile
     * e somma la velocità alla cordinate X e Y per permettere il movimento del player
     *
     * @brief medoto simulateWalking()
     * @since 1.0
     */
    private void simulateWalking() {
        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1)
                spriteNum = 2;
            else if (spriteNum == 2)
                spriteNum = 1;
            spriteCounter = 0;
        }

        //tile collision
        collisionOn = false;
        gp.collisionChecker.checkTile(this);

        //check objet collision
        int objIndex = gp.collisionChecker.checkObject(this, true);
        pickUpOnject(objIndex);

        if (!collisionOn)
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;

            }
    }
}
