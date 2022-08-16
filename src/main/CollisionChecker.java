/**
 * @author federico colombo
 * @version 1.0
 * @file CollisionChecker.java
 * @brief gestione delle collisioni delle verie entita
 */
package main;

import entity.Entity;
import entity.Player;

/**
 * gestione delle collisioni. Viene creato un rettangolo sopra il {@link Player}
 * con il quale verranno controllate le collisioni con le Tile
 *
 * @author Colombo Federico
 * @version 1.0
 * @class CollisionChecker
 * @brief gestione delle collisioni di {@link Player} e {@link entity.NPC_Guard} con gli oggetti
 */

public class CollisionChecker {
    /**
     * {@link GamePanel} per utilizzare tutte le funzioni del GamePanel del gioco
     *
     * @since 1.0
     */
    GamePanel gp;

    /**
     * riceve come parametro il {@link GamePanel} e lo copia nell'attributo locale
     *
     * @param gp passaggio del {@link GamePanel} quando viene inizializzato l'oggetto
     * @brief costruttore con passaggio di parametri
     * @since 1.0
     */
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * crea intorno all'entità un quadrato per controllare la collisione con la tile
     * utilizzando l'attributo direction dell'{@link Entity entità} controlla a seconda della direzione
     * se va in contatto con una tile con la quale può passargli sopra o con la quale si deve bloccare
     * controllo per le chiavi con apertura porte
     *
     * @param entity riceve come parametro l'{@link Entity entità} corrente
     * @brief metodo per il controllo delle collisioni con i {@link tile.Tile tiles}
     * @since 1.0
     */
    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;
        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

                if (checkDoor(tileNum1, tileNum2, entity)) {
                    break;
                }
                if (gp.tileM.tile[tileNum1 - 1].collision || gp.tileM.tile[tileNum2 - 1].collision)
                    entity.collisionOn = true;
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if ((entityLeftCol == 15 && entityBottomRow == 88) || (entityRightCol == 15 && entityBottomRow == 88)) {
                    entity.collisionOn = false;
                    gp.ui.finish = true;
                    break;
                }
                if (checkDoor(tileNum1, tileNum2, entity)) {
                    break;
                }
                if (gp.tileM.tile[tileNum1 - 1].collision || gp.tileM.tile[tileNum2 - 1].collision)
                    entity.collisionOn = true;
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if ((entityLeftCol == 14 && entityBottomRow == 87) || (entityRightCol == 14 && entityBottomRow == 87)) {
                    entity.collisionOn = false;
                    gp.ui.finish = true;
                    break;
                }
                if (checkDoor(tileNum1, tileNum2, entity)) {
                    break;
                }
                if (gp.tileM.tile[tileNum1 - 1].collision || gp.tileM.tile[tileNum2 - 1].collision)
                    entity.collisionOn = true;
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (checkDoor(tileNum1, tileNum2, entity)) {
                    break;
                }
                if (gp.tileM.tile[tileNum1 - 1].collision || gp.tileM.tile[tileNum2 - 1].collision)
                    entity.collisionOn = true;
            }
        }
    }

    /**
     * Si controlla se il player è già in possesso della chiave necessaria per aprire la porta corrente
     * Tramite un ciclo for si scorrono tutti gli ID dei {@link tile.Tile tiles} delle porte. Tramite un for annidato, si scorre
     * l'array contente delle chiavi. Nel caso in cui l'ID della porta sia corretto, e si è in possesso della sua relativa
     * chiave: si rimuove la collisione con la porta, si visualizza a schermo un messaggio indicante l'apertura della pora
     * e si ritorna true
     *
     * @param tileNum1 prima coordinata del tile successivo
     * @param tileNum2 seconda coordinata del tile successivo
     * @param e        {@link Entity entità} corrente
     * @return true se è possibile aprire la porta, altrimenti false (sia se non si ha la chiave, sia se non esiste nessuna
     * porta)
     * @brief controlla la possibilità di aprire una porta
     * @since 1.0
     */
    public boolean checkDoor(int tileNum1, int tileNum2, Entity e) {
        int firstDorTileID = 61;
        int howManyDoor = 4;
        for (int i = firstDorTileID; i < howManyDoor + firstDorTileID; i++) {
            for (int j = 0; j < e.keys.length; j++) {
                if ((tileNum1 == i || tileNum2 == i) && e.keys[j]) {
                    e.collisionOn = false;
                    gp.ui.showMessage("Open Door");
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * crea intorno all'entità un quadrato per controllare la collisione con un {@link Object oggetto}
     * utilizzando l'attributo direction dell'{@link Entity entità} controlla a seconda della direzione
     * se va in contatto con un {@link Object oggetto}
     *
     * @param entity riceve come parametro l'{@link Entity entità} corrente
     * @return indice dell'oggetto, nel caso in cui non vi sia nessun oggetto, si ritorna 999
     * @brief metodo per il controllo delle collisioni con gli oggetti
     * @since 1.0
     */
    public int checkObject(Entity entity, boolean player) {
        int index = 999;
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                //get entity solid area
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //get object solid area
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up" -> {
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                    case "down" -> {
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                    case "left" -> {
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                    case "right" -> {
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;

            }
        }
        return index;
    }

    /**
     * crea intorno all'entità un quadrato per controllare la collisione con il player
     * utilizzando l'attributo direction dell'{@link Entity entità} controlla a seconda della direzione
     * se va in contatto con una player
     *
     * @param entity riceve come parametro l'{@link Entity entità} corrente
     * @return viene ritornato true nel caso in cui si è in collisione con un player, altrimenti false
     * @brief metodo per il controllo delle collisioni con il player
     * @since 1.0
     */
    public synchronized boolean checkPlayer(Entity entity) {
        //get entity solid area
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        //get object solid area
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entity.direction) {
            case "up" -> {
                entity.solidArea.y -= entity.speed + 2;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    return true;
                }
            }
            case "down" -> {
                entity.solidArea.y += entity.speed + 2;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    return true;
                }
            }
            case "left" -> {
                entity.solidArea.x -= entity.speed + 2;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    return true;
                }
            }
            case "right" -> {
                entity.solidArea.x += entity.speed + 2;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    return true;
                }
            }
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        return false;
    }
}
