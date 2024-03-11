package JeuDuPendu;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class JeuDuPenduView extends JFrame {
    private JLabel labelMot;
    private JTextField textFieldLettre;
    private JLabel labelErreur;

    public JeuDuPenduView() {
        initialiseGUI();
    }

    private void initialiseGUI() {
        setTitle("Jeu du Pendu");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        labelMot = new JLabel("Mot à deviner: ");
        textFieldLettre = new JTextField(20); // Ajustez la taille selon vos besoins
        labelErreur = new JLabel(" ");

        add(labelMot);
        add(textFieldLettre);
        add(labelErreur);

        pack(); // Ajuste la taille de la fenêtre aux composants
    }

    public void setMotAffiche(String mot) {
        labelMot.setText("Mot à deviner: " + mot);
    }

    public String getLettre() {
        return textFieldLettre.getText().trim().toLowerCase();
    }

    public void clearTextField() {
        textFieldLettre.setText("");
    }

    public void setErreur(String erreur) {
        labelErreur.setText(erreur);
    }

    public void addTextFieldListener(ActionListener actionListener) {
        textFieldLettre.addActionListener(actionListener);
    }
    
    public void showMessage(String message) {
    	JOptionPane.showMessageDialog(this, message);
    }
}
