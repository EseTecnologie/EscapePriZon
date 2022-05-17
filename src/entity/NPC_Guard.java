package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Guard extends Entity {
    public NPC_Guard(GamePanel gp) {
        super(gp);

        direction = "left";
        speed = 2;

        getGuardImage();
    }

    public void getGuardImage() {
        /*try {
            up1 = ImageIO.read(new File("resources/player/boy_up_1.png"));
            up2 = ImageIO.read(new File("resources/player/boy_up_2.png"));
            down1 = ImageIO.read(new File("resources/player/boy_down_1.png"));
            down2 = ImageIO.read(new File("resources/player/boy_down_2.png"));
            left1 = ImageIO.read(new File("resources/player/boy_left_1.png"));
            left2 = ImageIO.read(new File("resources/player/boy_left_2.png"));
            right1 = ImageIO.read(new File("resources/player/boy_right_1.png"));
            right2 = ImageIO.read(new File("resources/player/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        up1 = setup("resources/npc/npc_up_1");
        up2 = setup("resources/npc/npc_up_2");
        down1 = setup("resources/npc/npc_down_1");
        down2 = setup("resources/npc/npc_down_2");
        left1 = setup("resources/npc/npc_left_1");
        left2 = setup("resources/npc/npc_left_2");
        right1 = setup("resources/npc/npc_right_1");
        right2 = setup("resources/npc/npc_right_2");
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            } else if (i <= 50) {
                direction = "down";
            } else if (i <= 75) {
                direction = "right";
            } else {
                direction = "left";
            }

            actionLockCounter = 0;
        }


    }
}
