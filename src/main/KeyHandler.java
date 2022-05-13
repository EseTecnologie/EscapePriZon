/**
 * @author  federico colombo
 * @version 1.0
 * @file  KeyHandler.java
 * @since 06/05/2022
 * @brief gestione della classe KeyListener
 *
 */
package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * @class KeyHandler
 *
 * @brief classe per la gestione del KeyListener
 *
 * questa classe controlla gli eventi keyPressed e keyReleased per gestire i comandi per il movimento del player
 */
public class KeyHandler implements KeyListener {

    /** attributi booleani up, down, left e right che vengono settati a true se viene premuto il tasto corrispondente al movimento che deve effettuare*/
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    //DEBUG
    boolean checkDrawTime;
    GamePanel gp;
    public KeyHandler(GamePanel gp){
        this.gp=gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    /**
     @brief metodo keyPressed che gestisce il click del tasto della tastiera

    questo metodo serve per gestire il click su tastiera, assegna a true l'attributo corrispondente al tasto che viene schiacciato
     controllando se il tasto cliccato è uno di quelli che servono al programma
     @param  e parametro con tutte le informazioni del tasto che viene cliccato
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed=true;
        }
        if(code == KeyEvent.VK_P){
            if(gp.gameState==gp.playState){
                gp.gameState=gp.pauseState;
            }else if(gp.gameState==gp.pauseState){
    gp.gameState=gp.playState;
            }
        }

        //DEBUG
        if(code == KeyEvent.VK_T){
            if(checkDrawTime == false){
                checkDrawTime = true;
            }else if (checkDrawTime == true){
                checkDrawTime = false;
            }
        }
    }
    /**
     @brief metodo keyReleased che gestisce il rilascio del tasto della tastiera

     questo metodo serve per gestire il rilascio del tasto della tastiera, assegna a false l'attributo corrispondente al tasto che viene rilasciato
     controllando se il tasto rilasciato è uno di quelli che servono al programma
     @param  e parametro con tutte le informazioni del tasto che viene cliccato
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
