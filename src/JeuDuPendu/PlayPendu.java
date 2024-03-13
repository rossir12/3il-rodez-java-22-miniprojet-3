package JeuDuPendu;

import javax.swing.SwingUtilities;

/**
 * La classe PlayPendu est la classe principale du jeu du pendu. Elle initialise
 * le modèle, la vue et le contrôleur du jeu, puis demande au joueur de
 * sélectionner un niveau de difficulté.
 */
public class PlayPendu {

	/**
	 * Méthode principale de l'application.
	 * 
	 * @param args Les arguments de la ligne de commande (non utilisés).
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			// Initialisation du modèle, de la vue et du contrôleur
			JeuDuPenduModel model = new JeuDuPenduModel("mots.txt");
			JeuDuPenduView view = new JeuDuPenduView();
			JeuDuPenduController controller = new JeuDuPenduController(model, view);
			// Demande au joueur de sélectionner un niveau de difficulté
			controller.demanderNiveauDifficulte();
		});
	}
}