package main;

import javax.swing.*;
import java.awt.*;

/**
 * Punto di ingresso del programma
 * @author Colobo Federico, Sottocasa Michele
 * @since 04/28/2022
 * @version 1.0
 */
public class Main {
    /**
     *
     * @param args
     */
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Escape PriZon");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
