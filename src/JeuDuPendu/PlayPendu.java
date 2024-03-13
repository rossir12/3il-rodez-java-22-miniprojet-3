package JeuDuPendu;

import javax.swing.SwingUtilities;

public class PlayPendu {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JeuDuPenduModel model = new JeuDuPenduModel("mots.txt");
			JeuDuPenduView view = new JeuDuPenduView();
			JeuDuPenduController controller = new JeuDuPenduController(model, view);
			controller.demanderNiveauDifficulte();
		});
	}
}