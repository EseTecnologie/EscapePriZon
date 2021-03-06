/**
 * @author federico colombo
 * @version 1.0
 * @file KeyHandler.java
 * @brief gestione della classe KeyListener
 * @since 06/05/2022
 */
package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * questa classe controlla gli eventi keyPressed e keyReleased per gestire i comandi per il movimento del player
 *
 * @author Colombo Federico
 * @version 1.0
 * @class KeyHandler
 * @brief classe per la gestione del KeyListener
 */
public class KeyHandler implements KeyListener {

    /**
     * @brief attributi booleani up, down, left e right che vengono settati a true se viene premuto il tasto corrispondente al movimento che deve effettuare
     * @since 1.0
     */
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * questo metodo serve per gestire il click su tastiera, assegna a true l'attributo corrispondente al tasto che viene schiacciato
     * controllando se il tasto cliccato è uno di quelli che servono al programma
     * a seconda dello stato del gioco i tasti servono per effettuare diverse opzioni
     *
     * @param e parametro con tutte le informazioni del tasto che viene cliccato
     * @brief metodo keyPressed che gestisce il click del tasto della tastiera
     * @since 1.0
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                }
                if (gp.ui.commandNum == 1) {
                    System.exit(0);
                }
            }
        }
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_E) {
                gp.gameState = gp.titleState;
            }
        }

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            } else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
        }
        if (code == KeyEvent.VK_ESCAPE) {
            if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
        }
    }

    /**
     * questo metodo serve per gestire il rilascio del tasto della tastiera, assegna a false l'attributo corrispondente al tasto che viene rilasciato
     * controllando se il tasto rilasciato è uno di quelli che servono al programma
     *
     * @param e parametro con tutte le informazioni del tasto che viene cliccato
     * @brief metodo keyReleased che gestisce il rilascio del tasto della tastiera
     * @since 1.0
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
