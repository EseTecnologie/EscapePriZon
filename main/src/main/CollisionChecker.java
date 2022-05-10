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
    public void checkTile(Entity entity){

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
                if (gp.tileM.tile[tileNum1-1].collision || gp.tileM.tile[tileNum2-1].collision)
                    entity.collisionOn = true;
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1-1].collision || gp.tileM.tile[tileNum2-1].collision)
                    entity.collisionOn = true;
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1-1].collision || gp.tileM.tile[tileNum2-1].collision)
                    entity.collisionOn = true;
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1-1].collision || gp.tileM.tile[tileNum2-1].collision)
                    entity.collisionOn = true;
            }
        }
    }
}
