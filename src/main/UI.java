package main;

import java.awt.*;
import java.util.Objects;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40,arial_80;
    public boolean messageOn=false;
    public String message=" ";
    int messageCounter=0;
    Color c;
    public boolean finish=false;

    public UI(GamePanel gp){
        this.gp=gp;
        arial_40=new Font("Arial",Font.BOLD,40);
        arial_80=new Font("Arial",Font.BOLD,80);
    }
    public void showMessage(String text,Font f,Color c){
    message=text;
    messageOn=true;
    this.c=c;
    }
    public void showMessage(String text){

        if(Objects.equals(text, "White Door")){
        finish=true;

        }else{
            message=text;
            messageOn=true;
            this.c=Color.white;
        }
    }
    public void draw(Graphics2D g2){
    this.g2=g2;
    g2.setFont(arial_40);
    g2.setColor(Color.white);
    if(gp.gameState==gp.playState){
        if(finish){
            String text;
            int textLength;
            int x;
            int y;

            g2.setFont(arial_40);
            g2.setColor(Color.white);

            text="you found the exit!";
            textLength=getXforCenterText(text);
            x=gp.screenWidth/2-textLength/2;
            y=gp.screenHeight/2-(gp.tileSize*3);

            g2.drawString(text,x,y);
            g2.setFont(arial_80);
            g2.setColor(Color.yellow);
            text="Congratulations";
            textLength=getXforCenterText(text);
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
    if(gp.gameState==gp.pauseState){
    drawPauseScreen();
    }


    }
    public void drawPauseScreen(){
        String text = "PAUSED";
                int x=getXforCenterText(text);

        int y=gp.screenHeight/2-gp.tileSize;
        g2.setColor(Color.RED);
        g2.drawString(text,x,y);
    }
    public int getXforCenterText(String text){
        int lenght=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x=gp.screenWidth/2-lenght/2;
        return x;
    }
}
