package JeuDuPendu;

import javax.swing.*;

public class JeuDuPendu {
    private final JeuDuPenduModel model;
    private final JeuDuPenduView view;

    public JeuDuPendu() {
        this.model = new JeuDuPenduModel("mots.txt");
        this.view = new JeuDuPenduView();
        JeuDuPenduController controller = new JeuDuPenduController(model,view);
        this.view.setMotAffiche(model.getMotAffiche());
        this.view.addTextFieldListener(e -> verifierLettre());
        this.view.setVisible(true);
    }

    private void verifierLettre() {
        String lettre = view.getLettre();
        if (lettre.length() == 1 && Character.isLetter(lettre.charAt(0))) {
            boolean correct = model.verifierLettre(lettre.charAt(0));
            view.setMotAffiche(model.getMotAffiche());
            if (!correct) {
                // Gérer l'erreur
                view.setErreur("Lettre incorrecte. Erreurs: " + model.getNombreErreurs());
            } else {
                view.setErreur(" ");
            }

            if (model.estTermine()) {
                JOptionPane.showMessageDialog(view, "Félicitations ! Vous avez trouvé le mot : " + model.getMotADeviner());
            } else if (model.getNombreErreurs() >= 10) {
                JOptionPane.showMessageDialog(view, "Dommage ! Le mot était : " + model.getMotADeviner());
            }
        } else {
            view.setErreur("Veuillez entrer une lettre.");
        }
        view.clearTextField();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JeuDuPendu::new);
    }
}

