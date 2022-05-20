/**
 * @author  federico colombo
 * @version 1.0
 * @file Player.java
 *
 * @brief gestione della classe player con estensione all Entity
 *
 */
package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {
    /** GamePanel per aggiornare le informazioni del player */
    GamePanel gp;
    /** KeyHandler per effettuare i movimenti del player */
    KeyHandler keyH;
    /** attributo per dichiarare la posizione sulla corditata X */
    public final int screenX;
    /** attributo per dichiarare la posizione sulla corditata Y */
    public final int screenY;

    /**
     @brief costruttore  del player

     metodo che inizializza la posizione a X=0 e Y=0
     */
    public Player(){
this.screenX=0;
this.screenY=0;
    }
    /**
     @brief costruttore parametrico del player

     metodo che inizializza la posizione dello screen e il retangolo nel quale sara presente il player
     richiama i metodi setDefaultValues per assegnare la posizione del player nel world e il getPlayerImage per
     assegnare le immagini al player
     @param  gp GamePanel sul quale viene caricato il player
     @param  keyH KeyHandler per utilizzare il keylistener
     */
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(16, 24, 16, 16);
        solidAreaDefaultX= solidArea.x;
        solidAreaDefaultY=solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    /**
     @brief metodo setDefaultValues

     metodo per inizializzare i valori di default del player
     le cordinate X e Y
     la velocità
     la direzione di partenza

     */
    public void setDefaultValues() {
        worldX = gp.tileSize * 68;
        worldY = gp.tileSize * 32;
        speed = 4;
        direction = "down";
    }
    /**
     @brief getPlayerImage

     metodo per assegnare le immagini per i movimenti al player passandogli il path della loro posizione

     @throws IOException avviene se le immagini non vengono trovate e questo non fa crasciare il software

     */

    public void getPlayerImage() {
            up1 = setup("resources/player/boy_up_1");
            up2 = setup("resources/player/boy_up_2");
            down1 = setup("resources/player/boy_down_1");
            down2 = setup("resources/player/boy_down_2");
            left1 = setup("resources/player/boy_left_1");
            left2 = setup("resources/player/boy_left_2");
            right1 = setup("resources/player/boy_right_1");
            right2 = setup("resources/player/boy_right_2");
    }
    /**
     @brief medoto update()

     metodo che controlla se vengono premuti i tasti sulla tastiera se i tasti sono
     stati premuti richiama il metodo simulateWalking() per aggiornare la posizione del
     player
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
     @brief metodo pickUpOnject

     metodo per raccogliere gli oggetti che si trovano nella mappa contorllando
     index che viene passato come parametro dal CollisionChecker

     @param  index parametro per controllare i diversi tipi di oggetti che vengono trovati
     */
    public void pickUpOnject(int index){
        if(index!=999){
            String objetName=gp.obj[index].name;
            switch (objetName){
                case "redKey":
                    getKey(0, "Red Key!", index, Color.red);
                    break;
                case "GreenKey":
                    getKey(0, "Green Key!", index, Color.green);
                    break;
                case "PurpleKey":
                    getKey(0, "Purple Key!", index, Color.magenta);
                    break;
                case "WhiteKey":
                    getKey(0, "Wite Key!", index, Color.white);
                    break;
                case "boostspeed":
                    getKey(0, "Speed Boosted!", index, Color.orange);
                    break;

            }
        }
    }

    private void getKey(int keyID, String textToShow, int index, Color color){
        keys[keyID]=true;
        gp.obj[index]=null;
        gp.ui.showMessage(textToShow, new Font("Arial",Font.BOLD,30), color);
    }
    /**
     @brief medoto draw()

     metodo per lo scambio delle immagini del player a seconda
     della della direzione in cui deve andare e disegna l'immagine del player sul
     gamePanel

     @param g parametro contenente il pannello grafico sul quale verrà dedisegnato il player
     */

    public void draw(Graphics2D g) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1)
                    image = up1;
                if (spriteNum == 2)
                    image = up2;
                break;
            case "down":
                if (spriteNum == 1)
                    image = down1;
                if (spriteNum == 2)
                    image = down2;
                break;
            case "left":
                if (spriteNum == 1)
                    image = left1;
                if (spriteNum == 2)
                    image = left2;
                break;
            case "right":
                if (spriteNum == 1)
                    image = right1;
                if (spriteNum == 2)
                    image = right2;
                break;
        }
        g.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    /**
     @brief medoto simulateWalking()

     metodo che chiama il metodo checkTile per controllare la collisione con le tile
     e somma la velocità alla cordinate X e Y per permettere il movimento del player
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
        int objIndex=gp.collisionChecker.checkObject(this,true);
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
