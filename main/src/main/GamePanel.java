package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTitleSize = 16; //16x16 pixel images
    final int scale = 3;

    public final int tileSize = originalTitleSize*scale;//48*48 (più visibile in schermi con alta risoluzione)
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize *maxScreenCol;
    public final int screenHeight = tileSize *maxScreenRow;

    //World settings
    public final int maxWorldCol = 92;
    public final int maxWorldRow = 93;
    public final int worldWith = tileSize*maxWorldCol;
    public final int worldHeight = tileSize*maxWorldRow;


    final int FPS = 60;
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
   public CollisionChecker collisionChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);

    public GamePanel(){
        this. setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

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
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}
