package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
//TODO continuare documentazione
/**
 * Estensione della classe {@link JPanel}
 * Creazione e gestione della finestra di gioco
 *
 * @author Sottocasa Michele
 * @version 1.0
 * @since 05/07/2022 USA data format
 */
public class GamePanel extends JPanel implements Runnable {

    /**
     * Dimensione in pixel dei tile
     * @since 1.0
     */
    public final int originalTitleSize = 16; //16x16 pixel images
    /**
     * Moltiplicatore di dimensione del tile
     * @since 1.0
     */
    final int scale = 3;
    /**
     * Dimensione finale in pixel del tile dopo la scalatura
     * @since 1.0
     */
    public final int tileSize = originalTitleSize*scale;//48*48 (più visibile in schermi con alta risoluzione)
    /**
     * Dimensione delle colonne visibili nello schermo
     * @since 1.0
     */
    public final int maxScreenCol = 20;
    /**
     * Dimensione delle righe visibili nello schermo
     * @since 1.0
     */
    public final int maxScreenRow = 16;

    /**
     * Larghezza in pixle della finestra, calcolata sulla dimensione dei tile
     * @since 1.0
     */
    public final int screenWidth = tileSize *maxScreenCol;
    /**
     * Altezza in pixle delal finestra, calcolata sulal dimensione dei tile
     * @since 1.0
     */
    public final int screenHeight = tileSize *maxScreenRow;

    //World settings
    /**
     * Totale colonne del mondo
     * @since 1.0
     */
    public final int maxWorldCol = 92;
    /**
     * Totale righe del mondo
     * @since 1.0
     */
    public final int maxWorldRow = 93;
    /**
     * Larghezza in pixel del mondo
     * @since 1.0
     */
    public final int worldWith = tileSize*maxWorldCol;
    /**
     * Altezza in pixel del modno
     * @since 1.0
     */
    public final int worldHeight = tileSize*maxWorldRow;


    final int FPS = 60;
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    //public CollisionChecker collisionChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);

    public GamePanel(){
        this. setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    /**
     *
     */
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     *
     */
    @Override
    public void run() {

        double drawIntervall = 1000000000/FPS; //stampo l'immagine ogni 0.1666 secondi
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) /drawIntervall;
            timer += (currentTime -lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update(){
        player.update();
    }

    /**
     *
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}
