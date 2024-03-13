package JeuDuPendu;

import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * La classe JeuDuPenduView représente la vue du jeu du pendu. Elle gère
 * l'affichage des éléments graphiques et des messages destinés à l'utilisateur.
 */

public class JeuDuPenduView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel labelMot; // Étiquette pour afficher le mot à deviner
	private JTextField textFieldLettre; // Champ de texte pour saisir une lettre
	private JLabel labelErreur; // Étiquette pour afficher les messages d'erreur
	private DrawPendu drawPendu; // Composant graphique pour dessiner le pendu
	private JLabel labelDefinition; // Étiquette pour afficher la définition du mot
	private JLabel labelLettresFausses; // Étiquette pour afficher les lettres fausses
	private JeuDuPenduController controller; // Contrôleur associé à la vue

	/**
	 * Constructeur de la classe JeuDuPenduView. Initialise l'interface graphique.
	 */

	public JeuDuPenduView() {
		initialiseGUI();
	}

	/**
	 * Méthode pour initialiser l'interface graphique.
	 */

	private void initialiseGUI() {
		// Config Fenêtre
		setTitle("Jeu du Pendu");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // Layout

		// Composant graphiques
		drawPendu = new DrawPendu();
		labelMot = new JLabel("Mot à deviner: ");
		textFieldLettre = new JTextField(20); // Ajustez la taille selon vos besoins
		labelErreur = new JLabel(" ");
		labelDefinition = new JLabel("Definition");
		labelDefinition.setVisible(false);
		labelLettresFausses = new JLabel("Lettres Fausses : ");

		// Ajout des composants à la fenêtre
		add(drawPendu);
		add(labelMot);
		add(textFieldLettre);
		add(labelErreur);
		add(labelDefinition);
		add(labelLettresFausses);

		pack(); // Ajuste la taille de la fenêtre aux composants
	}

	/**
	 * Méthode pour afficher la boîte de dialogue de sélection du niveau de
	 * difficulté. Elle permet au joueur de choisir entre Facile, Moyen et
	 * Difficile.
	 */

	public void afficherPromptNiveau() {
		SwingUtilities.invokeLater(() -> {
			String[] options = { "Facile", "Moyen", "Difficile" };
			int choix = JOptionPane.showOptionDialog(this, "Choisissez un niveau de difficulté :",
					"Sélection du niveau", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options,
					options[0]);
			if (choix != JOptionPane.CLOSED_OPTION) {
				String niveau = options[choix];
				controller.configurerDifficulte(niveau);
			} else {
				System.exit(0);
			}
		});
	}

	/**
	 * Méthode pour mettre à jour l'affichage du mot à deviner.
	 * 
	 * @param mot Le mot à afficher.
	 */

	public void setMotAffiche(String mot) {
		labelMot.setText("Mot à deviner: " + mot);
	}

	/**
	 * Méthode pour récupérer la lettre saisie par l'utilisateur dans le champ de
	 * texte.
	 * 
	 * @return La lettre saisie.
	 */

	public String getLettre() {
		return textFieldLettre.getText().trim().toLowerCase();
	}

	/**
	 * Méthode pour effacer le contenu du champ de texte.
	 */

	public void clearTextField() {
		textFieldLettre.setText("");
	}

	/**
	 * Méthode pour afficher un message d'erreur.
	 * 
	 * @param erreur Le message d'erreur à afficher.
	 */

	public void setErreur(String erreur) {
		labelErreur.setText(erreur);
	}

	/**
	 * Méthode pour ajouter un écouteur d'événement au champ de texte.
	 * 
	 * @param actionListener L'écouteur d'événement à ajouter.
	 */

	public void addTextFieldListener(ActionListener actionListener) {
		textFieldLettre.addActionListener(actionListener);
	}

	/**
	 * Méthode pour mettre à jour le dessin du pendu en fonction du nombre
	 * d'erreurs.
	 * 
	 * @param nombreErreurs Le nombre d'erreurs commises.
	 */

	public void setNombreErreurs(int nombreErreurs) {
		drawPendu.setNombreErreurs(nombreErreurs);
	}

	/**
	 * Méthode pour afficher un message dans une boîte de dialogue.
	 * 
	 * @param message Le message à afficher.
	 */
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	/**
	 * Méthode pour afficher ou masquer la définition du mot.
	 * 
	 * @param afficher Indique si la définition doit être affichée ou non.
	 */
	public void afficherDefinition(boolean afficher) {
		labelDefinition.setVisible(afficher);
	}

	/**
	 * Méthode pour démarrer un compte à rebours pour le jeu.
	 * 
	 * @param duree La durée du compte à rebours en millisecondes.
	 */
	public void demarrerTimer(int duree) {
		Timer timer = new Timer(duree, e -> {
			JOptionPane.showMessageDialog(this, "Le temps est écoulé !");
			System.exit(0);
		});
		timer.setRepeats(false);
		timer.start();
	}

	/**
	 * Méthode pour définir la définition du mot à afficher.
	 * 
	 * @param definition La définition du mot.
	 */
	public void setDefinition(String definition) {
		labelDefinition.setText(definition);
	}

	/**
	 * Méthode pour afficher les lettres fausses déjà proposées.
	 * 
	 * @param lettres Les lettres fausses sous forme d'une chaîne de caractères.
	 */
	public void setLettresFausses(String lettres) {
		labelLettresFausses.setText("Lettres Fausses : " + lettres);
	}

	/**
	 * Méthode pour définir le contrôleur associé à la vue.
	 * 
	 * @param controller Le contrôleur associé à la vue.
	 */
	public void setController(JeuDuPenduController controller) {
		this.controller = controller;
	}

	/**
	 * Méthode pour redessiner le pendu.
	 */
	public void redessinerPendu() {
		drawPendu.repaint();

	}
}
