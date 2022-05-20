/**
 * @author  federico colombo
 * @version 1.0
 * @file UI.java
 *
 * @brief gestione della classe UI per il draw della prima pagina della pagina di pausa e per la visualizzarione dei messaggi
 *
 */
package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Objects;

public class UI {
    /** game panel per avere le informazioni di tutti i componenti*/
    GamePanel gp;
    /** Graphics2D per disegnare*/
    Graphics2D g2;
    /**font standard*/
    Font arial_40,arial_80;
/**variabile di controllo per controllare se c'è qualcosa da scrivere*/
    public boolean messageOn=false;
    /** string contenente i messaggi da visualizzare*/
    public String message=" ";
/**variabile per il conteggio dei secondi nel quale rimane a vista la scritta*/
    int messageCounter=0;
    Color c;
    /**variabile per il controllo se si ha finito il gioco*/
    public boolean finish=false;
    /**variabile per il controllo del click su e giù nella pagina di inizio*/
    public int commandNum=0;
    /**
     @brief costruttore parametrico UI

     metodo imposta il gamePanel e i due font di default
     @param gp gamePannel contenente tutte le informazioni del gioco
     */
    public UI(GamePanel gp){
        this.gp=gp;
        arial_40=new Font("Arial",Font.BOLD,40);
        arial_80=new Font("Arial",Font.BOLD,80);
    }
    /**
     @brief metodo per riceve le informazioni da scrivere

     metodo che riceve come parametro le informazioni che verranò scritte
     @param text scritta da scrivere
     @param c colore da utilizzare per scrivere
     @param f font da usare per scrivere
     */
    public void showMessage(String text,Font f,Color c){
        message=text;
        messageOn=true;
        this.c=c;
    }
    /**
     @brief metodo per riceve le informazioni da scrivere

     metodo che riceve come parametro le informazioni che verranò scritte
     @param text scritta da scrivere
     */
    public void showMessage(String text){

        if(Objects.equals(text, "White Door")){
            finish=true;

        }else{
            message=text;
            messageOn=true;
            this.c=Color.white;
        }
    }
    /**
     @brief metodo draw

     metodo che controlla lo stato del gioco tramite il gamePanel e richiama i metodi che scrivono sul gamePannel
     @param g2 pannello grafico dove si disegna e scrive
     */
    public void draw(Graphics2D g2){
        this.g2=g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        if(gp.gameState==gp.titleState){
            drawTitleScreen();
        }
        if(gp.gameState==gp.playState){
          drawPlayState();
        }
        if(gp.gameState==gp.pauseState){
            drawPauseScreen();
        }


    }
    /**
     @brief metodo drawPlayState

     metodo scrive la le scritte finali quando si esce dalla prigione
     e scrive tutte le scritte notifica che escono sul pannello
     */
    public void drawPlayState(){
        if(finish){
            String text;
            int textLength;
            int x;
            int y;

            g2.setFont(arial_40);
            g2.setColor(Color.white);

            text="you found the exit!";
            textLength=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x=gp.screenWidth/2-textLength/2;
            y=gp.screenHeight/2-(gp.tileSize*3);
            g2.drawString(text,x,y);

            g2.setFont(arial_80);
            g2.setColor(Color.yellow);
            text="Congratulations";
            textLength=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x=gp.screenWidth/2-textLength/2;
            y=gp.screenHeight/2+(gp.tileSize*3);

            g2.drawString(text,x,y);
            gp.gameThread=null;
        }else if(messageOn){
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.setColor(c);
            g2.drawString(message,50,50);
            messageCounter++;
            if(messageCounter>120){
                messageCounter=0;
                messageOn=false;
            }
        }

    }
    /**
     @brief metodo drawTitleScreen

     metodo per la visualizzazione della pagina iniziale
     comprendentr il titolo
     il menu con il testo per far partire la partita e il tasto per chiudere il gioco
     il drawImage per la visualizzazione dell'immagine di sfondo
     */
    public void drawTitleScreen(){
        BufferedImage background=null;
        try{
            background=ImageIO.read(new File("resources/map/escap_cover.jpg"));
        }catch (IOException e){

        }
        g2.drawImage(background,0,gp.tileSize*2, gp.screenWidth,600,null);

        //title
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,72F));
        String text="The Escape Prizon";
        int x=getXforCenterText(text);
        int y=72;
        //shadow
        g2.setColor(new Color(243,114,32));
        g2.drawString(text,x+5,y+5);
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        //menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48f));
        text="Play Game";
        x=getXforCenterText(text);
        y+=gp.tileSize*11;
        g2.drawString(text,x,y);
        if(commandNum==0){
            g2.drawString(">",x-gp.tileSize,y);
        }

        text="Quit";
        x=getXforCenterText(text);
        y+=gp.tileSize*1.5;
        g2.drawString(text,x,y);
        if(commandNum==1){
            g2.drawString(">",x-gp.tileSize,y);
        }
    }
    /**
     @brief metodo drawPauseScreen

     metodo che scrive sul pannello la scritta PAUSED quando il gioco viene messo in pausa schiacciando il testo P
     */

    public void drawPauseScreen(){
        String text = "PAUSED";
        int x=getXforCenterText(text);

        int y=gp.screenHeight/2-gp.tileSize;
        g2.setColor(new Color(243,114,32));
        g2.drawString(text,x,y);
    }
    /**
     @brief metodo getXforCenterText

     metodo per centrale il testo al centro della pagina
     */
    public int getXforCenterText(String text){
        int lenght=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x=gp.screenWidth/2-lenght/2;
        return x;
    }
}