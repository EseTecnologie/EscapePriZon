/**
 * @author  federico colombo
 * @version 1.0
 * @file  CollisionChecker.java
 *
 * @brief gestione delle collisioni delle verie entita
 *
 */
package main;

import entity.Entity;
import entity.Player;

/**
 * @class CollisionChecker
 *
 * @brief classe per la gestione delle collisioni del player con gli oggetti
 *
 * la classe per la gestione delle collisioni, viene creato un retangolo sopra al player
 * con il quale verranno controllate le collisioni con le Tile
 */

public class CollisionChecker {
    /** GamePanel gp per utillizzare tutte le funzioni del GamePanel del gioco */
    GamePanel gp;
    /**
     @brief costruttore con passaggio di parametri

     riceve come parametro il GamePanel e lo copia del attributo locale
     @param  gp passaggio del GamePanel quando viene inizializzato l'oggetto
     */
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    /**
     @brief metodo per il controllo delle collisioni

     crea intorno all'entità un quadrato per controllare la collisione controlo la tile
     utilizzando al'attributo direction del entity controlla a seconda della direzione
     se va in contatto con una tile con la quale può passargli sopra o con la quale si deve bloccare

     @param  entity riceve come parametro il
     */
    public void checkTile(Entity entity, Player p){

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;
        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(tileNum1==61||tileNum2==61){
                    if(p.redKey==true){
                        entity.collisionOn = false;
                        gp.ui.showMessage("Open Door");
                        break;
                    }
                }else  if(tileNum1==62||tileNum2==62){
                    if(p.greenKey){
                        entity.collisionOn = false;
                        gp.ui.showMessage("Open Door");
                        break;
                    }
                }else  if(tileNum1==63||tileNum2==63){
                    if(p.purpleKey){
                        entity.collisionOn = false;
                        gp.ui.showMessage("Open Door");
                        break;
                    }
                }else  if(tileNum1==64||tileNum2==64){
                    if(p.whiteKey){
                        entity.collisionOn = false;
                        gp.ui.finish=true;
                        break;
                    }
                }
                if (gp.tileM.tile[tileNum1-1].collision || gp.tileM.tile[tileNum2-1].collision)
                    entity.collisionOn = true;
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(tileNum1==61||tileNum2==61){
                    if(p.redKey==true){
                        entity.collisionOn = false;
                        gp.ui.showMessage("Open Door");
                        break;
                    }
                }else  if(tileNum1==62||tileNum2==62){
                    if(p.greenKey){
                        entity.collisionOn = false;
                        gp.ui.showMessage("Open Door");
                        break;
                    }
                }else  if(tileNum1==63||tileNum2==63){
                    if(p.purpleKey){
                        entity.collisionOn = false;
                        gp.ui.showMessage("Open Door");
                        break;
                    }
                }else  if(tileNum1==64||tileNum2==64){
                    if(p.whiteKey){
                        entity.collisionOn = false;
                        gp.ui.finish=true;
                        break;
                    }
                }
                if (gp.tileM.tile[tileNum1-1].collision || gp.tileM.tile[tileNum2-1].collision)
                    entity.collisionOn = true;
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(tileNum1==61||tileNum2==61){
                    if(p.redKey==true){
                        entity.collisionOn = false;
                        gp.ui.showMessage("Open Door");
                        break;
                    }
                }else  if(tileNum1==62||tileNum2==62){
                    if(p.greenKey){
                        entity.collisionOn = false;
                        gp.ui.showMessage("Open Door");
                        break;
                    }
                }else  if(tileNum1==63||tileNum2==63){
                    if(p.purpleKey){
                        entity.collisionOn = false;
                        gp.ui.showMessage("Open Door");
                        break;
                    }
                }else  if(tileNum1==64||tileNum2==64){
                    if(p.whiteKey){
                        entity.collisionOn = false;
                        gp.ui.finish=true;
                        break;
                    }
                }
                if (gp.tileM.tile[tileNum1-1].collision || gp.tileM.tile[tileNum2-1].collision)
                    entity.collisionOn = true;
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(tileNum1==61||tileNum2==61){
                    if(p.redKey==true){
                        entity.collisionOn = false;
                        gp.ui.showMessage("Open Door");
                        break;
                    }
                }else  if(tileNum1==62||tileNum2==62){
                    if(p.greenKey){
                        entity.collisionOn = false;
                        gp.ui.showMessage("Open Door");
                        break;
                    }
                }else  if(tileNum1==63||tileNum2==63){
                    if(p.purpleKey){
                        entity.collisionOn = false;
                        gp.ui.showMessage("Open Door");
                        break;
                    }
                }else  if(tileNum1==64||tileNum2==64){
                    if(p.whiteKey){
                        entity.collisionOn = false;
                        gp.ui.finish=true;
                        break;
                    }
                }
                if (gp.tileM.tile[tileNum1-1].collision || gp.tileM.tile[tileNum2-1].collision)
                    entity.collisionOn = true;
            }
        }
    }
    public int checkObject(Entity entity,boolean player){
        int index=999;
        for (int i=0;i<gp.obj.length;i++){
            if(gp.obj[i]!=null){
                //get entity solid area
                entity.solidArea.x=entity.worldX+entity.solidArea.x;
                entity.solidArea.y=entity.worldY+entity.solidArea.y;
                //get object solid area
                gp.obj[i].solidArea.x=gp.obj[i].worldX+gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y=gp.obj[i].worldY+gp.obj[i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y-=entity.speed;
                            if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                                if(gp.obj[i].collision==true){
                                    entity.collisionOn=true;
                                }
                                if(player==true){
                                    index=i;
                                }
                            }
                        break;
                    case "down":
                        entity.solidArea.y+=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision==true){
                                entity.collisionOn=true;
                            }
                            if(player==true){
                                index=i;
                            }
                        }
                        break;
                        case "left":
                            entity.solidArea.x-=entity.speed;
                            if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                                if(gp.obj[i].collision==true){
                                    entity.collisionOn=true;
                                }
                                if(player==true){
                                    index=i;
                                }
                            }
                        break;
                        case "right":
                            entity.solidArea.x+=entity.speed;
                            if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                                if(gp.obj[i].collision==true){
                                    entity.collisionOn=true;
                                }
                                if(player==true){
                                    index=i;
                                }
                            }
                        break;
                }
                entity.solidArea.x=entity.solidAreaDefaultX;
                entity.solidArea.y= entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x=gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y=gp.obj[i].solidAreaDefaultY;

            }
        }
        return index;
    }
}