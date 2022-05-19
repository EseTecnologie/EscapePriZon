package object;

import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision=false;
    public int worldX,worldY;
    public Rectangle solidArea=new Rectangle(0,0,48,48);
    public int solidAreaDefaultX=0;
    public int solidAreaDefaultY=0;
    public UtilityTool uTool = new UtilityTool();
/**
 *@brief medoto draw()
 *
 * metodo per disegnare gli oggetti sul GamePanel
 *
 * @param g2 parametro contenente il pannello grafico sul quale verrà disegnato il player
 * @param gp parametro GamePanel per controllare se gli oggetti sono all'interno del campo visiovo del player
     */
    public void draw(Graphics2D g2, GamePanel gp){

            if(worldX!=0){
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                if (worldX + gp.tileSize > (gp.player.worldX - gp.player.screenX) &&
                        worldY + gp.tileSize > (gp.player.worldY - gp.player.screenY) &&
                        worldX - gp.tileSize < (gp.player.worldX + gp.player.screenX) &&
                        worldY - gp.tileSize < (gp.player.worldY + gp.player.screenY)){

                    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);}
            }


    }
}
