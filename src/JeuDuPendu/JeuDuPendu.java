package JeuDuPendu;

import javax.swing.*;

/**
 * La classe JeuDuPendu est le point d'entrée du jeu. Elle initialise le modèle,
 * la vue et le controleur puis gère les intéractions utilisateurs et la logique
 * du jeu
 */

public class JeuDuPendu {
	private final JeuDuPenduModel model;
	private final JeuDuPenduView view;
	private JeuDuPenduController controller;

	/**
	 * Constructeur de la classe JeuDuPendu. Initialise le modèle, la vue et le
	 * controleur du jeu
	 */

	public JeuDuPendu() {
		// Initialisation du modèle avec un fichier de mots
		this.model = new JeuDuPenduModel("mots.txt");
		// Initialisation de la vue
		this.view = new JeuDuPenduView();
		// Initisalition du controleur avec le modele et la vue
		this.controller = new JeuDuPenduController(model, view);
		// Affichage initial du mot à deviner
		this.view.setMotAffiche(model.getMotAffiche());
		// Ajout d'un écouteur pour le champ de texte
		this.view.addTextFieldListener(e -> verifierLettre());
		// Affichage de la vue
		this.view.setVisible(true);
	}

	/**
	 * Méthode pour vérifier la lettre entrée par l'utilisateur
	 */
	private void verifierLettre() {
		String lettre = view.getLettre(); // Récupération de la lettre entrée par l'utilisateur
		if (lettre.length() == 1 && Character.isLetter(lettre.charAt(0))) { // Vérification de la validité
			boolean correct = model.verifierLettre(lettre.charAt(0)); // Vérification de la lettre dans le mot à deviner
			view.setMotAffiche(model.getMotAffiche()); // Mise à jour de l'affichage du mot à deviner
			if (!correct) {
				// Gérer l'erreur
				view.setErreur("Lettre incorrecte. Erreurs: " + model.getNombreErreurs()); // Affichage du message
			} else {
				view.setErreur(" "); // Effacement du message d'erreur
			}

			if (model.estTermine() || model.getNombreErreurs() >= 10) {
				// vérification de la fin du jeu
				int response = JOptionPane.showConfirmDialog(view,
						model.estTermine() ? "Félicitations ! Vous avez trouvé le mot : " + model.getMotADeviner()
								: "Dommage ! Le mot était : " + model.getMotADeviner() + "Voulez-vous rejouer ? ",
						"Fin de partie", JOptionPane.YES_NO_OPTION); // Affichage du message de fin de partie
				if (response == JOptionPane.YES_OPTION) {
					rejouer(); // rejouer une partie
				} else {
					System.exit(0); // quitter le jeu
				}
			}
		} else {
			// Affichage du message d'erreur pour une lettre invalide
			view.setErreur("Veuillez entrer une lettre.");
		}
		// Effacement du champ de texte
		view.clearTextField();
	}

	/**
	 * Méthode pour rejouer une partie en réinitialisant le modèle et lavue
	 */
	private void rejouer() {
		this.model.choisirMot("mots.txt"); // choix d'un nouveau mot
		this.view.setMotAffiche(model.getMotAffiche()); // Update affichage mot
		this.view.setErreur(""); // Effacement message d'erreur
	}
}
