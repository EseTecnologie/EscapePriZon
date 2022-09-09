package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Player2 extends Entity{
    /**
     * GamePanel per aggiornare le informazioni del player
     *
     * @since 1.0
     */
    GamePanel gp;

    /**
     * metodo che inizializza la posizione dello screen e il retangolo nel quale sara presente il player
     * richiama i metodi setDefaultValues per assegnare la posizione del player nel world e il getPlayerImage per
     * assegnare le immagini al player
     *
     * @param gp   GamePanel sul quale viene caricato il player
     * @brief costruttore parametrico del player
     * @since 1.0
     */
    public Player2(GamePanel gp) {
        super(gp);
        this.gp = gp;
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
}
