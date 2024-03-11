package JeuDuPendu;

import javax.swing.*;

public class JeuDuPendu {
    private final JeuDuPenduModel model;
    private final JeuDuPenduView view;
    private JeuDuPenduController controller;

    public JeuDuPendu() {
        this.model = new JeuDuPenduModel("mots.txt");
        this.view = new JeuDuPenduView();
        this.controller = new JeuDuPenduController(model,view);
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

            if (model.estTermine() || model.getNombreErreurs() >= 10) {
                int response = JOptionPane.showConfirmDialog(view,
                		model.estTermine() ? "Félicitations ! Vous avez trouvé le mot : " + model.getMotADeviner() : "Dommage ! Le mot était : " + model.getMotADeviner() + "Voulez-vous rejouer ? ",
                		"Fin de partie", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                	rejouer();
                } else {
                	System.exit(0);
                }
        }
        } else {
        	view.setErreur("Veuillez entrer une lettre.");
        }
        view.clearTextField();
    }
    
    private void rejouer() {
    	this.model.choisirMot("mots.txt");
    	this.view.setMotAffiche(model.getMotAffiche());
    	this.view.setErreur("");
    }
}

