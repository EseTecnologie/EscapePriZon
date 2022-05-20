package main;

import javax.swing.*;

/**
 * Punto di ingresso del programma con inizializzazione dei metodi necessari
 * @class Main
 * @author Colobo Federico, Sottocasa Michele
 * @since 04/28/2022
 * @version 1.0
 */
public class Main {
    /** 
     metodo main per l'inizializzazione degli oggetti

     metodo main per mette la creazione degli oggetti con l'inizializzazione del Frame e del GamePanel, settare il Title del Frame
     e codice per stoppare tutti i Thread in funzione quando verrà chiuso il Frame
     @param  args contiene gli argomenti della riga di comando forniti come una matrice di oggetti
      * @since 1.0

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
