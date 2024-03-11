package JeuDuPendu;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JeuDuPendu extends JFrame {

    private String motADeviner;
    private String definitionMot;

    // Constructeur de la classe
    public JeuDuPendu() {
        initialiseJeu();
        initialiseGUI();
    }

    private void initialiseJeu() {
        String[] motEtDefinition = getRandomWord("mots.txt");
        if (motEtDefinition != null) {
            motADeviner = motEtDefinition[0];
            definitionMot = motEtDefinition[1];
        }
    }

    private void initialiseGUI() {
        // Configuration initiale de l'interface graphique Swing
        setTitle("Jeu du Pendu");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Ajoutez vos composants Swing ici
    }

    private String[] getRandomWord(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null; // ou gérer l'erreur autrement
        }

        Collections.shuffle(lines); // Mélange la liste pour obtenir un élément aléatoire
        String randomLine = lines.get(0); // Retourne la première ligne de la liste mélangée
        String[] parts = randomLine.split(" ", 2); // Sépare le mot de sa définition
        return parts; // Retourne le mot et sa définition
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JeuDuPendu jeu = new JeuDuPendu();
            jeu.setVisible(true);
        });
    }
}
