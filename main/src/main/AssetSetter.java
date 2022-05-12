package main;

import object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }
    public void setObject(){
        //key
        gp.obj[0]=new OBJ_Key("red");
        gp.obj[0].worldX= 68*gp.tileSize;
        gp.obj[0].worldY=29*gp.tileSize;
        gp.obj[1]=new OBJ_Key("green");
        gp.obj[1].worldX= 69*gp.tileSize;
        gp.obj[1].worldY=29*gp.tileSize;
        gp.obj[2]=new OBJ_Key("purple");
        gp.obj[2].worldX= 70*gp.tileSize;
        gp.obj[2].worldY=29*gp.tileSize;
        gp.obj[3]=new OBJ_Key("white");
        gp.obj[3].worldX= 71*gp.tileSize;
        gp.obj[3].worldY=29*gp.tileSize;



    }

}
