package entity;

import main.GamePanel;

import java.util.Random;

/**
 * @author Sottocasa Michele
 * @version 1.0
 * @class NPC_Guard
 * @brief Estensione della classe {@link Entity}, nella quale si implementa il funzionamento degli NPC Guardia
 */
public class NPC_Guard extends Entity {
    /**
     * @param gp Variabile contenente il {@link GamePanel} corrente, sul quale viene visualizzato il gioco
     * @brief Costruttore parametrico
     * @since 1.0
     */
    public NPC_Guard(GamePanel gp) {
        super(gp);

        direction = "left";
        speed = 3;

        getGuardImage();
    }

    /**
     * @brief Caricamento delle immagini dell'NPC dai files
     * @since 1.0
     */
    public void getGuardImage() {
        up1 = loadImage("resources/npc/npc_up_1");
        up2 = loadImage("resources/npc/npc_up_2");
        down1 = loadImage("resources/npc/npc_down_1");
        down2 = loadImage("resources/npc/npc_down_2");
        left1 = loadImage("resources/npc/npc_left_1");
        left2 = loadImage("resources/npc/npc_left_2");
        right1 = loadImage("resources/npc/npc_right_1");
        right2 = loadImage("resources/npc/npc_right_2");
    }

    /**
     * viene creato un loop infinito, nel quale ogni secondo gli NPC cambiano direzione randomicamente
     *
     * @brief override del metodo run
     * @since 1.0
     */
    @Override
    public void run() {
        while (true) {
            changeDirection();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                //throw new RuntimeException(e);
            }
        }
    }

    /**
     * Viene generato un numero casuale compreso tra 1 e 100, per aumentare la probabilità di avere un numero
     * differente. Nel caso in cui la posizione generata sia identica a quella precedente, si ripete la generazione. Viene
     * poi cambiato il valore della variabile 'direction' con la nuova direzione dell'NPC
     *
     * @brief selezione randomica della prossima mossa
     * @since 1.0
     */
    public void changeDirection() {
        String newDirection;
        do {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                newDirection = "up";
            } else if (i <= 50) {
                newDirection = "down";
            } else if (i <= 75) {
                newDirection = "right";
            } else {
                newDirection = "left";
            }
        } while (direction.equals(newDirection));
        direction = newDirection;

    }
}
