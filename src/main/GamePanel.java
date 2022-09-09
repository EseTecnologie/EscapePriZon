package main;

import Udp.FileCLIENT;
import Udp.ThreadCondivisione;
import entity.Entity;
import entity.EntityGestions;
import entity.Player;
import entity.Player2;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

/**
 * Creazione e gestione della finestra di gioco
 *
 * @author Colombo Federico, Sottocasa Michele
 * @version 1.0
 * @brief Estensione della classe {@link JPanel}
 */
public class GamePanel extends JPanel implements Runnable {

    /**
     * Dimensione in pixel dei tile
     *
     * @since 1.0
     */
    public final int originalTitleSize = 16; //16x16 pixel images
    /**
     * Moltiplicatore di dimensione del tile
     *
     * @since 1.0
     */
    final int scale = 3;
    /**
     * Dimensione finale in pixel del tile dopo la scalatura
     *
     * @since 1.0
     */
    public final int tileSize = originalTitleSize * scale;//48*48 (più visibile in schermi con alta risoluzione)
    /**
     * Dimensione delle colonne visibili nello schermo
     *
     * @since 1.0
     */
    public final int maxScreenCol = 20;
    /**
     * Dimensione delle righe visibili nello schermo
     *
     * @since 1.0
     */
    public final int maxScreenRow = 16;

    /**
     * Larghezza in pixle della finestra, calcolata sulla dimensione dei tile
     *
     * @since 1.0
     */
    public final int screenWidth = tileSize * maxScreenCol;
    /**
     * Altezza in pixle delal finestra, calcolata sulal dimensione dei tile
     *
     * @since 1.0
     */
    public final int screenHeight = tileSize * maxScreenRow;

    //World settings
    /**
     * Totale colonne del mondo
     *
     * @since 1.0
     */
    public final int maxWorldCol = 92;
    /**
     * Totale righe del mondo
     *
     * @since 1.0
     */
    public final int maxWorldRow = 93;


    final int FPS = 60;
    /**
     * Istanza della classe {@link TileManager} per la gestione dei tile e la relativa visualizzazione
     *
     * @since 1.0
     */
    TileManager tileM = new TileManager(this);
    /**
     * Istanza della classe {@link KeyHandler} per la gestione dell'input del giocatore
     *
     * @since 1.0
     */
    KeyHandler keyH = new KeyHandler(this);
    /**
     * Istanza della classe {@link Player} per la gestione del player, viene passata come parametro
     * l'istanza della classe {@link KeyHandler}
     *
     * @since 1.0
     */
    public Player player = new Player(this, keyH);
    public Player2 player2;
    /**
     * Thread per la gestione della finestra di gioco
     *
     * @since 1.0
     */
    Thread gameThread;
    /**
     * Istanza della classe {@link CollisionChecker} per la gestione delle collisioni con gli elementi
     *
     * @since 1.0
     */
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public AssetSetter aSetter = new AssetSetter(this);
    public SuperObject[] obj = new SuperObject[40];

    /**
     * @brief Creazione di un array di NPC
     * @since 1.0
     */
    public Entity[] npc = new Entity[39];
    public UI ui = new UI(this);

    EntityGestions eg=new EntityGestions(this);
    //game state
    public int gameState;
    public int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;

    FileCLIENT fc=new FileCLIENT(this);
    ThreadCondivisione fs=new ThreadCondivisione();

    public void setupGame() {
        aSetter.setKey();
        aSetter.setBoots();
        aSetter.setNpc();
        gameState = titleState;
    }


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        fc.start();
        fs.start();
       // server.start();
    }

    /**
     * @brief Metodo per l'assegnazione del thread e avvio di quest'ultimo
     * @since 1.0
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Override del metodo run del thread gameThread {@code <br>}
     * Gestione della frequenza di aggiornamento delle immagini nella finestra
     *
     * @since 1.0
     */
    @Override
    public void run() {

        double drawIntervall = 1000000000 / FPS; //stampo l'immagine ogni 0.1666 secondi
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawIntervall;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    /**
     * @brief Aggiornamento della posizione del player nella schermata, solo nel caso in cui il gioco non è in pausa
     * @since 1.0
     */
    public void update() {
        if (gameState == playState) {
            //player
            player.update();
            //player 2
            player2.updateclient();
            //npc
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.updateclient();
                }
            }
        }
        eg.updatePlayer();
    }

    /**
     * @param g the Graphics object to protect
     * @brief Metodo per disegnare sul pannello della classe {@link Graphics} tutti i componenti di gioco
     * @since 1.0
     */
    public void paintComponent(Graphics g)  {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //tile screen
        if (gameState == titleState) {
            ui.draw(g2);
        } else {
            //tile
            tileM.draw(g2);
            //object
            for (SuperObject superObject : obj) {
                if (superObject != null) {
                    superObject.draw(g2, this);
                }
            }
            //npc
            for (Entity entity : npc) {
                if (entity != null) {
                    entity.draw(g2);
                }
            }
            //player2
            if(player2!=null)
            player2.draw(g2);
            //player
            player.draw(g2);

            //UI
            ui.draw(g2);
            g2.dispose();
        }
        g2.dispose();
    }
}
