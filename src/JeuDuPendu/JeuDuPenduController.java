package JeuDuPendu;

import java.awt.event.ActionListener;

public class JeuDuPenduController {
    private JeuDuPenduModel model;
    private JeuDuPenduView view;

    public JeuDuPenduController(JeuDuPenduModel model, JeuDuPenduView view) {
        this.model = model;
        this.view = view;
        this.view.addTextFieldListener(verifierLettre());
        initView();
    }

    private void initView() {
        view.setMotAffiche(model.getMotAffiche());
        view.setVisible(true);
    }

    private ActionListener verifierLettre() {
        return e -> {
            String lettre = view.getLettre();
            if (lettre.length() == 1 && Character.isLetter(lettre.charAt(0))) {
                boolean correct = model.verifierLettre(lettre.charAt(0));
                view.setMotAffiche(model.getMotAffiche());
                if (!correct) {
                    view.setErreur("Lettre incorrecte. Erreurs: " + model.getNombreErreurs());
                } else {
                    view.setErreur(" ");
                }

                if (model.estTermine()) {
                    view.showMessage("Félicitations ! Vous avez trouvé le mot : " + model.getMotADeviner());
                } else if (model.getNombreErreurs() >= 10) {
                    view.showMessage("Dommage ! Le mot était : " + model.getMotADeviner());
                }
            } else {
                view.setErreur("Veuillez entrer une lettre.");
            }
            view.clearTextField();
        };
    }
}

