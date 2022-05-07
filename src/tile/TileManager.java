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
        loadTiles("resources/map/EscapePrizonTiles.png");
        loadMap("resources/map/map01.txt");

    }
    public void loadTiles(String path) {
        try{
            img=ImageIO.read(new File(path));
        }catch (IOException e){

        }
        for (int i = 0; i < tile.length; i++) {
            tile[i]=new Tile();

            tile[i].image = img.getSubimage(i * 16, 0, 16, 16);

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
                    worldY - gp.tileSize < (gp.player.worldY + gp.player.screenY))
                g.drawImage(tile[tileNum-1].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
