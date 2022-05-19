package main;

import entity.NPC_Guard;
import object.OBJ_Key;
import object.OBJ_boostSpeed;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }
    public void setObject(){
        //key
        gp.obj[0]=new OBJ_Key("red");
        gp.obj[0].worldX= 63*gp.tileSize;
        gp.obj[0].worldY=64*gp.tileSize;
        gp.obj[1]=new OBJ_Key("green");
        gp.obj[1].worldX= 43*gp.tileSize;
        gp.obj[1].worldY=68*gp.tileSize;
        gp.obj[2]=new OBJ_Key("purple");
        gp.obj[2].worldX= 11*gp.tileSize;
        gp.obj[2].worldY=12*gp.tileSize;
        gp.obj[3]=new OBJ_Key("white");
        gp.obj[3].worldX= 87*gp.tileSize;
        gp.obj[3].worldY=6*gp.tileSize;
    }
    public void setBoots(){
        gp.obj[4]=new OBJ_boostSpeed("boostspeed");
        gp.obj[4].worldX= 8*gp.tileSize;
        gp.obj[4].worldY=60*gp.tileSize;
    }
    int[] x={12,15,50,68,79};
    int[] y={29,29,29,29,29};

    public void setNpc(){
        for (int i=0;i<x.length;i++){
            gp.npc[i] = new NPC_Guard(gp);
            gp.npc[i].worldX = gp.tileSize * x[i];
            gp.npc[i].worldY = gp.tileSize * y[i];
            gp.npc[i].start();
        }
    }

    public void stopNpc(){
        for (int i=0;i<gp.npc.length;i++){
            if(gp.npc[i]!=null) {
                gp.npc[i].interrupt();
                gp.npc[i] = null;
            }
        }

    }
}
