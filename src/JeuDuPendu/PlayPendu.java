package JeuDuPendu;

import javax.swing.SwingUtilities;

public class PlayPendu {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JeuDuPenduModel model = new JeuDuPenduModel("mots.txt");
			JeuDuPenduView view = new JeuDuPenduView();
			new JeuDuPenduController(model, view);
			view.setVisible(true);
		});
	}
}