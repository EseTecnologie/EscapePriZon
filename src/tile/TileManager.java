package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Passato il path dei tile (immagine) e il paht della mappa (file txt),
 * generati: un array contenente i tile (l'indice corrisponde all'ID del tile) e una matrice la disposizione dei tile
 * I tile, giustamente disposti, vengono poi disegnati su un {@link GamePanel}
 *
 * @author Sottocasa Michele
 * @version 1.0
 * @since 05/02/2022 USA data format
 *
 */
public class TileManager {

    /**
     * Variabile locale del {@link GamePanel} sul quale verranno disegnati i tile
     * Assegnazione nel costruttore
     * @since 1.0
     */
    private GamePanel gp;
    /**
     * Array contenente i tile successivamente importati con il metodo loadTiles()
     * Assegnazione nel costruttore
     * @since 1.0
     */
    public Tile[] tile;
    /**
     * Matrice contenente la mappa successivamente importata nel metodo loadMap()
     * Assegnazione nel costruttore
     * @since 1.0
     */
    public int mapTileNum[][];
    /**
     * {@link BufferedImage} nel quale verrrà salvata l'immagine contenente tutti i tile
     * Assegnazione nel costruttore
     * @since 1.0
     */
    public BufferedImage img;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        int tileQuantity = 120;
        tile = new Tile[tileQuantity];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadTiles("resources/map/EscapePrizonTiles.png");
        loadMap("resources/map/map01.txt");

    }

    /**
     * Metodo per creare e salvare in un apposito array tutti i tile necessari per la generazione della mappa.
     * L'immagine viene suddivisa in quadrati di 16px (dimensione di un singolo tile), che vengono poi
     * salvati come {@link BufferedImage} nell'array tile. L'indice dell'array corrisponde all'ID del
     * singolo tile.
     *
     * @roules L'immagine passata deve contenere i tile su un'unica riga
     *
     *
     * @param path percorso dell'imagine contenente i tile per la generazione della mappa
     *
     * @since 1.0
     */
    public void loadTiles(String path) {
        try{
            img=ImageIO.read(new File(path));
        }catch (IOException e){

        }
        for (int i = 0; i < tile.length; i++) {
            tile[i]=new Tile();

            tile[i].image = img.getSubimage(i * gp.originalTitleSize, 0, gp.originalTitleSize, gp.originalTitleSize);

        }
    }

    /**
     * Metodo per importare da un file txt la disposizione dei tile nello schermo, in altre parole, il file con il
     * layout della mappa. Il file viene poi letto, interrompendo la lettura ad ogni spazio incontrato, salvando la
     * lettura corrente in una cella matrice. Al termine di ogni riga, si procede all'inserimento dei dati nella
     * riga successiva della matrice
     *
     * @roules Il file deve contenere gli ID dei tile devono essere numerici e devono essere separati
     * solamente da unospazio
     *
     * @param path, percorso del file txt contentente la disposizione dei tile
     *
     * @since 1.0
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
     * Metodo per disegnare la mappa sul un pannello {@link Graphics2D}
     *
     * Si procede a disegnare riga per riga la mappa, partendo dall'angolo in alto a sinistra, fino al raggiungimento
     * dell'angolo opposto.
     *
     * Viene disegnato un tile per volta
     *
     * @param g pannello {@link Graphics2D}
     *
     * @since 1.0
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
