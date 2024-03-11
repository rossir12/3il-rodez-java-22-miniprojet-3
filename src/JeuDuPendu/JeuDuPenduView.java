package JeuDuPendu;

import java.awt.event.ActionListener;
import javax.swing.*;

public class JeuDuPenduView extends JFrame {
    private static final long serialVersionUID = 1L;
	private JLabel labelMot;
    private JTextField textFieldLettre;
    private JLabel labelErreur;
    private DrawPendu drawPendu;
    private JLabel labelDefinition;
    private Timer timer;
    private String definition = "";

    public JeuDuPenduView() {
        initialiseGUI();
        labelDefinition = new JLabel();
        add(labelDefinition);
    }

    private void initialiseGUI() {
        setTitle("Jeu du Pendu");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        drawPendu = new DrawPendu();
        labelMot = new JLabel("Mot à deviner: ");
        textFieldLettre = new JTextField(20); // Ajustez la taille selon vos besoins
        labelErreur = new JLabel(" ");

        add(drawPendu);
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
    
    public void setNombreErreurs(int nombreErreurs) {
    	drawPendu.setNombreErreurs(nombreErreurs);
    }
    
    public void showMessage(String message) {
    	JOptionPane.showMessageDialog(this, message);
    }
    
    public void afficherDefinition(boolean afficher) {
    	if(afficher) {
    		labelDefinition.setText(this.definition);
    	} else {
    		labelDefinition.setText("");
    	}
    }
       
    public void demarrerTimer(int duree) {
    	Timer timer = new Timer(duree, e -> {
    		JOptionPane.showMessageDialog(this, "Le temps est écoulé !");
    	});
    	timer.setRepeats(false);
    	timer.start();
    }
    
    public void setDefinition(String definition) {
    	this.definition = definition;
    }
}
