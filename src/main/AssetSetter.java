/**
 * @author federico colombo
 * @version 1.0
 * @file AssetSetter.java
 * @brief gestione della classe AssetSetter per inizializzazione degli oggetti e degli NPC
 */
package main;

import entity.EntityGestions;
import entity.NPC_Guard;
import entity.Player2;
import object.OBJ_Key;
import object.OBJ_boostSpeed;

import java.io.IOException;

/**
 * @author Federico Colombo
 * @version 1.0
 * @class AssetsSetter
 */
public class AssetSetter {
    GamePanel gp;

    /**
     * metodo per assegnare alla classe il componente gamepannel
     *
     * @param gp GamePanel sul quale vengono caricati gli oggetti
     * @brief costruttore parametrico
     * @since 1.0
     */
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * metodo per inizializzare i diversi tipi di chiavi e le varie posizioni
     *
     * @brief setKey metodo per settare le chiavi
     * @since 1.0
     */
    public void setKey() {
        //key
        gp.obj[0] = new OBJ_Key("red");
        gp.obj[0].worldX = 63 * gp.tileSize;
        gp.obj[0].worldY = 64 * gp.tileSize;
        gp.obj[1] = new OBJ_Key("green");
        gp.obj[1].worldX = 43 * gp.tileSize;
        gp.obj[1].worldY = 68 * gp.tileSize;
        gp.obj[2] = new OBJ_Key("purple");
        gp.obj[2].worldX = 11 * gp.tileSize;
        gp.obj[2].worldY = 12 * gp.tileSize;
        gp.obj[3] = new OBJ_Key("white");
        gp.obj[3].worldX = 87 * gp.tileSize;
        gp.obj[3].worldY = 6 * gp.tileSize;
    }

    /**
     * metodo per inizializzare la scarpa per l'aumento di velcità
     *
     * @brief setBoots metodo per settare le scarpe
     * @since 1.0
     */
    public void setBoots() {
        gp.obj[4] = new OBJ_boostSpeed("boostspeed");
        gp.obj[4].worldX = 8 * gp.tileSize;
        gp.obj[4].worldY = 60 * gp.tileSize;
    }

    /**
     * Array con le coordinate x di tutti gli NPC
     *
     * @since 1.0
     */
    int[] x = {68, 45, 58, 49, 68, 58, 49, 68, 79, 79, 79, 33, 22, 28, 58, 35, 28, 9, 14, 8, 30, 40, 70, 20, 3, 15, 33, 86, 86, 84, 81, 63, 52, 36, 18, 5, 17, 25, 14};
    /**
     * Array con le coordinate Y di tutti gli NPC
     *
     * @since 1.0
     */
    int[] y = {29, 29, 36, 44, 44, 52, 59, 59, 51, 37, 23, 34, 34, 45, 70, 67, 56, 56, 48, 32, 22, 14, 12, 24, 30, 8, 7, 12, 36, 67, 80, 85, 79, 74, 80, 83, 72, 66, 63};

    /**
     * metodo per inizializzare gli Npc e la loro pozione
     *
     * @brief setNpc metodo per settare le gli NPC
     * @since 1.0
     */
    public void setNpc() {
        try{
            String c="";
            for (int i=0;i<y.length;i++){
                c+=(x[i]+";"+y[i]+";\n");
            }
            gp.fw.write(c);
            gp.fw.flush();
            gp.fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < x.length; i++) {
            gp.npc[i] = new NPC_Guard(gp);
            gp.npc[i].worldX = gp.tileSize * x[i];
            gp.npc[i].worldY = gp.tileSize * y[i];
            //gp.npc[i].start();
        }
        gp.player2=new Player2(gp);
        gp.player2.worldX=gp.tileSize*68;
        gp.player2.worldY=gp.tileSize*32;
        gp.eg=new EntityGestions(gp);
        gp.eg.start();
    }

    /**
     * metodo per stoppare e cancellare tutti gli NPC
     *
     * @brief spotNpc()
     * @since 1.0
     */
    public void stopNpc() {
        for (int i = 0; i < gp.npc.length; i++) {
            if (gp.npc[i] != null) {
                gp.npc[i].interrupt();
                gp.npc[i] = null;
            }
        }
        gp.player2=null;

        gp.eg.interrupt();
        gp.eg=null;

    }
}
