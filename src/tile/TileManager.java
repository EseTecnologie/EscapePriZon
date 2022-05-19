//sottocasa program
package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    public BufferedImage img;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        int tileQuantity = 120;
        tile = new Tile[tileQuantity];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        //loadTiles("src/main/res/maps/EscapePrizonTiles.png");
        loadTiles("main/res/maps/tile2.png");
        //loadMap("src/main/res/maps/map01.txt");
        loadMap("main/res/maps/map01.txt");

    }
    public void loadTiles(String path) {
        try{
            img=ImageIO.read(new File(path));
        }catch (IOException e){

        }
        for (int i = 0; i < tile.length; i++) {
            tile[i]=new Tile();

            tile[i].image = img.getSubimage(i * 16, 0, 16, 16);
            if(i==0||i==1||i==2||i==3||i==4||i==5||i==6||i==7||i==8||i==9||i==10||i==11||i==12||i==13||i==14||i==15||i==16||i==17||i==18||i==19||i==20||i==21||i==22||i==23||i==24||i==25||i==26||i==27||i==28||i==29||i==30||i==31||i==32||i==33||i==34||i==35||i==36||i==37||i==38||i==43||i==44||i==45||i==46||i==47||i==48||i==49||i==50||i==51||i==53||i==58||i==59||i==60||i==61||i==62||i==63||i==64||i==66||i==67||i==68||i==73||i==74||i==82||i==88||i==89||i==92||i==93||i==97||i==104||i==107||i==110||i==112||i==119){
                tile[i].collision=true;}
        }
    }

    /**
     *
     * @param path, path del file della mappa
     */
    public void loadMap(String path) {
        try {

            //InputStream is = getClass().getResourceAsStream("src/main/res/maps/map01.txt");
            InputStream is = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param g
     */
    public void draw(Graphics2D g) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;


            if (worldX + gp.tileSize > (gp.player.worldX - gp.player.screenX) &&
                    worldY + gp.tileSize > (gp.player.worldY - gp.player.screenY) &&
                    worldX - gp.tileSize < (gp.player.worldX + gp.player.screenX) &&
                    worldY - gp.tileSize < (gp.player.worldY + gp.player.screenY)){
                g.drawImage(tile[tileNum-1].image, screenX, screenY, gp.tileSize, gp.tileSize, null);}
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
