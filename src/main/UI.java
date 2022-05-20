/**
 * @author federico colombo
 * @version 1.0
 * @file UI.java
 * @brief gestione della classe UI per il draw della prima pagina della pagina di pausa e per la visualizzarione dei messaggi
 */
package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Colombo Federico
 * @version 1.0
 * @class UI
 * @brief Gestione e visualizzazione a schermo di messaggi di testo
 */

public class UI {
    /**
     * game panel per avere le informazioni di tutti i componenti
     *
     * @since 1.0
     */
    GamePanel gp;
    /**
     * Graphics2D per disegnare
     *
     * @since 1.0
     */
    Graphics2D g2;
    /**
     * font standard
     *
     * @since 1.0
     */
    Font arial_40, arial_80;
    /**
     * variabile di controllo per controllare se c'è qualcosa da scrivere
     *
     * @since 1.0
     */
    public boolean messageOn = false;
    /**
     * string contenente i messaggi da visualizzare
     *
     * @since 1.0
     */
    public String message = " ";
    /**
     * variabile per il conteggio dei secondi nel quale rimane a vista la scritta
     *
     * @since 1.0
     */
    int messageCounter = 0;
    Color c;
    /**
     * variabile per il controllo se si ha finito il gioco
     *
     * @since 1.0
     */
    public boolean finish = false;
    /**
     * variabile per il controllo del click su e giù nella pagina di inizio
     *
     * @since 1.0
     */
    public int commandNum = 0;

    /**
     * metodo imposta il gamePanel e i due font di default
     *
     * @param gp gamePannel contenente tutte le informazioni del gioco
     * @brief costruttore parametrico UI
     * @since 1.0
     */
    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.BOLD, 40);
        arial_80 = new Font("Arial", Font.BOLD, 80);
    }

    /**
     * metodo che riceve come parametro le informazioni che verranò scritte
     *
     * @param text scritta da scrivere
     * @param c    colore da utilizzare per scrivere
     * @brief metodo per riceve le informazioni da scrivere
     * @since 1.0
     */
    public void showMessage(String text, Color c) {
        message = text;
        messageOn = true;
        this.c = c;
    }

    /**
     * metodo che riceve come parametro le informazioni che verranò scritte
     *
     * @param text scritta da scrivere
     * @brief metodo per riceve le informazioni da scrivere
     * @since 1.0
     */
    public void showMessage(String text) {

        if (Objects.equals(text, "White Door")) {
            finish = true;

        } else {
            message = text;
            messageOn = true;
            this.c = Color.white;
        }
    }

    /**
     * metodo che controlla lo stato del gioco tramite il gamePanel e richiama i metodi che scrivono sul gamePannel
     *
     * @param g2 pannello grafico dove si disegna e scrive
     * @brief metodo draw
     * @since 1.0
     */
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        if (gp.gameState == gp.playState) {
            drawPlayState();
        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }


    }

    /**
     * metodo scrive la le scritte finali quando si esce dalla prigione
     * e scrive tutte le scritte notifica che escono sul pannello
     *
     * @brief metodo drawPlayState
     * @since 1.0
     */
    public void drawPlayState() {
        if (finish) {
            String text;
            int textLength;
            int x;
            int y;

            g2.setFont(arial_40);
            g2.setColor(Color.white);

            text = "you found the exit!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - (gp.tileSize * 3);
            g2.drawString(text, x, y);

            g2.setFont(arial_80);
            g2.setColor(Color.yellow);
            text = "Congratulations";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 3);

            g2.drawString(text, x, y);
            gp.gameThread = null;
        } else if (messageOn) {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.setColor(c);
            g2.drawString(message, 50, 50);
            messageCounter++;
            if (messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }
        }

    }

    /**
     * metodo per la visualizzazione della pagina iniziale
     * comprendentr il titolo
     * il menu con il testo per far partire la partita e il tasto per chiudere il gioco
     * il drawImage per la visualizzazione dell'immagine di sfondo
     *
     * @brief metodo drawTitleScreen
     * @since 1.0
     */
    public void drawTitleScreen() {
        BufferedImage background = null;
        try {
            background = ImageIO.read(new File("resources/map/escap_cover.jpg"));
        } catch (IOException ignored) {

        }
        g2.drawImage(background, 0, gp.tileSize * 2, gp.screenWidth, 600, null);

        //title
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 72F));
        String text = "The Escape Prizon";
        int x = getXforCenterText(text);
        int y = 72;
        //shadow
        g2.setColor(new Color(243, 114, 32));
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48f));
        text = "Play Game";
        x = getXforCenterText(text);
        y += gp.tileSize * 11;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Quit";
        x = getXforCenterText(text);
        y += gp.tileSize * 1.5;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    /**
     * metodo che scrive sul pannello la scritta PAUSED quando il gioco viene messo in pausa schiacciando il testo P
     *
     * @brief metodo drawPauseScreen
     * @since 1.0
     */

    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXforCenterText(text);

        int y = gp.screenHeight / 2 - gp.tileSize;
        g2.setColor(new Color(243, 114, 32));
        g2.drawString(text, x, y);
    }

    /**
     * metodo per centrale il testo al centro della pagina
     *
     * @return centro dell'asse X per centrare il testo
     * @brief metodo getXforCenterText
     * @since 1.0
     */
    public int getXforCenterText(String text) {
        int lenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - lenght / 2;
    }
}