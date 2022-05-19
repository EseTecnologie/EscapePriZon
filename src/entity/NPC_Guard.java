package entity;

import main.GamePanel;

import java.io.IOException;
import java.util.Random;

public class NPC_Guard extends Entity {
    public NPC_Guard(GamePanel gp) {
        super(gp);

        direction = "left";
        speed = 3;

        getGuardImage();
    }

    public void getGuardImage() {
        up1 = setup("resources/npc/npc_up_1");
        up2 = setup("resources/npc/npc_up_2");
        down1 = setup("resources/npc/npc_down_1");
        down2 = setup("resources/npc/npc_down_2");
        left1 = setup("resources/npc/npc_left_1");
        left2 = setup("resources/npc/npc_left_2");
        right1 = setup("resources/npc/npc_right_1");
        right2 = setup("resources/npc/npc_right_2");
    }

    @Override
    public void run() {
        while(true){
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
            try {
                sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }
}