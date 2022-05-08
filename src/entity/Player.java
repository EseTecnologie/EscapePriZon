package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Colombo Federico
 * @since 1.0
 */

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(8, 16, 32, 32);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(new File("src/main/res/player/boy_up_1.png"));
            up2 = ImageIO.read(new File("src/main/res/player/boy_up_2.png"));
            down1 = ImageIO.read(new File("src/main/res/player/boy_down_1.png"));
            down2 = ImageIO.read(new File("src/main/res/player/boy_down_2.png"));
            left1 = ImageIO.read(new File("src/main/res/player/boy_left_1.png"));
            left2 = ImageIO.read(new File("src/main/res/player/boy_left_2.png"));
            right1 = ImageIO.read(new File("src/main/res/player/boy_right_1.png"));
            right2 = ImageIO.read(new File("src/main/res/player/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed) {
            direction = "up";
            simulateWalking();
        } else if (keyH.downPressed) {
            direction = "down";
            simulateWalking();
        } else if (keyH.rightPressed) {
            direction = "right";
            simulateWalking();
        } else if (keyH.leftPressed) {
            direction = "left";
            simulateWalking();
        }
    }

    /**
     *
     * @param g
     */
    public void draw(Graphics2D g) {
        //g.setColor(Color.WHITE);
        //g.fillRect(x,y,gp.titleSize, gp.titleSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1)
                    image = up1;
                if (spriteNum == 2)
                    image = up2;
                break;
            case "down":
                if (spriteNum == 1)
                    image = down1;
                if (spriteNum == 2)
                    image = down2;
                break;
            case "left":
                if (spriteNum == 1)
                    image = left1;
                if (spriteNum == 2)
                    image = left2;
                break;
            case "right":
                if (spriteNum == 1)
                    image = right1;
                if (spriteNum == 2)
                    image = right2;
                break;
        }
        g.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    private void simulateWalking() {
        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1)
                spriteNum = 2;
            else if (spriteNum == 2)
                spriteNum = 1;
            spriteCounter = 0;
        }

        //tile collision
        collisionOn = false;
        //TODO gp.collisionChecker.checkTile(this);

        if (!collisionOn)
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;

            }
    }
}
