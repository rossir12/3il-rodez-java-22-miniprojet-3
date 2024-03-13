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
    private JLabel labelLettresFausses;
    private JeuDuPenduController controller;

    public JeuDuPenduView() {
        initialiseGUI();
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
        labelDefinition = new JLabel("Definition");
        labelDefinition.setVisible(false);
        labelLettresFausses = new JLabel("Lettres Fausses : ");
        
        add(drawPendu);
        add(labelMot);
        add(textFieldLettre);
        add(labelErreur);
        add(labelDefinition);
        add(labelLettresFausses);

        pack(); // Ajuste la taille de la fenêtre aux composants
    }

    public void afficherPromptNiveau() {
        SwingUtilities.invokeLater(() -> {
            String[] options = {"Facile", "Moyen", "Difficile"};
            int choix = JOptionPane.showOptionDialog(this, "Choisissez un niveau de difficulté :", "Sélection du niveau", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (choix != JOptionPane.CLOSED_OPTION) {
                String niveau = options[choix];
                controller.configurerDifficulte(niveau);
            } else {
                System.exit(0);
            }
        });
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
    		labelDefinition.setVisible(afficher);
    }
       
    public void demarrerTimer(int duree) {
    	Timer timer = new Timer(duree, e -> {
    		JOptionPane.showMessageDialog(this, "Le temps est écoulé !");
    		System.exit(0);
    	});
    	timer.setRepeats(false);
    	timer.start();
    }
    
    public void setDefinition(String definition) {
    	labelDefinition.setText(definition);
    }
    
    public void setLettresFausses(String lettres) {
    	labelLettresFausses.setText("Lettres Fausses : " + lettres);
    }
    
    public void setController(JeuDuPenduController controller) {
    	this.controller = controller;
    }

	public void redessinerPendu() {
		drawPendu.repaint();
		
	}
}
