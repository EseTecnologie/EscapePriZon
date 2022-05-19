/**
 * @author  federico colombo
 * @version 1.0
 * @file  Main.java
 *
 * @brief dichiarazione del Jframe e creazione del nuovo gamePanel.
 *
 */
package main;

import javax.swing.*;
/**
 * @class Main
 *
 * @brief classe per l'inizializzazione dei  metodi necessari.
 *
 * la classe per la gestione del main per l'avvio del programma
 */
public class Main {


    /**
     @brief metodo main per l'inizializzazione degli oggetti

     metodo main per mette la creazione degli oggetti con l'inizializzazione del Frame e del GamePanel, settare il Title del Frame
     e codice per stoppare tutti i Thread in funzione quando verrà chiuso il Frame
     @param  args contiene gli argomenti della riga di comando forniti come una matrice di oggetti

     */
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("escape prizon");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}