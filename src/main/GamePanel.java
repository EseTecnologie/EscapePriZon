package main;

import entity.Player;
import object.SuperObject;
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


    final int FPS = 60;
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
   public AssetSetter aSetter=new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public CollisionChecker collisionChecker = new CollisionChecker(this,player);
    public SuperObject obj[]=new SuperObject[40];
    public UI ui=new UI(this);


    //game state
    public int gameState;
    public int titleState=0;
    public final int playState=1;
    public final int pauseState=2;

public void setupGame(){
    aSetter.setObject();
    gameState=titleState;
}
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
    if(gameState==playState){
    player.update();
    }
    if(gameState==pauseState){

    }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        //tile screen
        if(gameState==titleState){
            ui.draw(g2);
        }else{
            //tile
            tileM.draw(g2);
            //object
            for (int i=0;i<obj.length;i++){
                if(obj[i]!=null){
                    obj[i].draw(g2,this);
                }
            }
            //player
            player.draw(g2);
            //UI
            ui.draw(g2);
            g2.dispose();
        }
    }
}
