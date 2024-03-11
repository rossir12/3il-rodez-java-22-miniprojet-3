package JeuDuPendu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JeuDuPendu extends JFrame {

    private String motADeviner;
    private String definitionMot;
    private int nombreErreurs;
    private JLabel labelMot;
    private JPanel penduPanel;

    public JeuDuPendu() {
        initialiseJeu();
        initialiseGUI();
    }

    private void initialiseJeu() {
        String[] motEtDefinition = getRandomWord("mots.txt");
        if (motEtDefinition != null) {
            motADeviner = motEtDefinition[0];
            definitionMot = motEtDefinition[1];
            nombreErreurs = 0;
        }
    }

    private void initialiseGUI() {
        setTitle("Jeu du Pendu");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Affichage du mot à deviner
        labelMot = new JLabel("Mot à deviner: " + "_ ".repeat(motADeviner.length()));
        add(labelMot, BorderLayout.NORTH);

        // Panel pour dessiner le pendu
        penduPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dessinerPendu(g);
            }
        };
        add(penduPanel, BorderLayout.CENTER);
        penduPanel.setPreferredSize(new Dimension(400, 300));
    }

    private void dessinerPendu(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int baseY = 250;
        switch (nombreErreurs) {
            case 1:
                g2d.drawLine(100, baseY, 200, baseY); // Base
                break;
            case 2:
                g2d.drawLine(150, baseY, 150, baseY - 150); // Poteau
                break;
            case 3:
                g2d.drawLine(150, baseY - 150, 200, baseY - 150); // Traverse
                break;
            case 4:
                g2d.drawLine(200, baseY - 150, 200, baseY - 130); // Corde
                break;
            case 5:
                g2d.drawOval(190, baseY - 130, 20, 20); // Tête
                break;
            case 6:
                g2d.drawLine(200, baseY - 110, 200, baseY - 70); // Corps
                break;
            case 7:
                g2d.drawLine(200, baseY - 100, 180, baseY - 120); // Bras gauche
                break;
            case 8:
                g2d.drawLine(200, baseY - 100, 220, baseY - 120); // Bras droit
                break;
            case 9:
                g2d.drawLine(200, baseY - 70, 180, baseY - 50); // Jambe gauche
                break;
            case 10:
                g2d.drawLine(200, baseY - 70, 220, baseY - 50); // Jambe droite
                // Ajoutez ici des conditions supplémentaires si nécessaire
                break;
        }
    }

    private String[] getRandomWord(String filePath) {
        // Méthode inchangée pour lire aléatoirement un mot et sa définition depuis le fichier
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JeuDuPendu jeu = new JeuDuPendu();
            jeu.setVisible(true);
        });
    }
}
