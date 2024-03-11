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
    private JLabel motLabel;
    private JTextField lettreInput;
    private JButton verifierBouton;
    private JLabel lettresProposeesLabel;
    private JPanel penduPanel;
    private JLabel definitionLabel;

    public JeuDuPendu() {
        initialiseJeu();
        initialiseGUI();
    }

    private void initialiseJeu() {
        String[] motEtDefinition = getRandomWord("mots.txt");
        if (motEtDefinition != null) {
            motADeviner = motEtDefinition[0];
            definitionMot = motEtDefinition[1];
            // Transforme le mot à deviner en tirets pour l'affichage
            motLabel.setText(motADeviner.replaceAll(".", "_ "));
            definitionLabel.setText("Définition: " + definitionMot);
        }
    }

    private void initialiseGUI() {
        setTitle("Jeu du Pendu");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel pour le pendu
        penduPanel = new JPanel();
        add(penduPanel, BorderLayout.CENTER);

        // Panel pour les entrées de l'utilisateur
        JPanel inputPanel = new JPanel();
        lettreInput = new JTextField(1);
        verifierBouton = new JButton("Vérifier");
        verifierBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logique pour vérifier la lettre entrée
            }
        });
        inputPanel.add(lettreInput);
        inputPanel.add(verifierBouton);
        add(inputPanel, BorderLayout.SOUTH);

        // Label pour le mot à deviner
        motLabel = new JLabel();
        add(motLabel, BorderLayout.NORTH);

        // Label pour les lettres déjà proposées
        lettresProposeesLabel = new JLabel("Lettres déjà proposées: ");
        add(lettresProposeesLabel, BorderLayout.EAST);

        // Label pour la définition
        definitionLabel = new JLabel();
        add(definitionLabel, BorderLayout.WEST);
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

        Collections.shuffle(lines);
        String randomLine = lines.get(0);
        String[] parts = randomLine.split(" ", 2);
        return parts;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JeuDuPendu jeu = new JeuDuPendu();
            jeu.setVisible(true);
        });
    }
}
