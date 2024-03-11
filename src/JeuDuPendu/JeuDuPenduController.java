package JeuDuPendu;

import javax.swing.JOptionPane;

public class JeuDuPenduController {
    private final JeuDuPenduModel model;
    private final JeuDuPenduView view;

    public JeuDuPenduController(JeuDuPenduModel model, JeuDuPenduView view) {
        this.model = model;
        this.view = view;
        this.view.setMotAffiche(model.getMotAffiche());
        setupListeners();
        this.view.setVisible(true);
    }
    
    private void setupListeners() {
    	this.view.addTextFieldListener(e -> verifierLettre());
    }
    
    private void verifierLettre() {
            String lettre = view.getLettre();
            if (lettre.length() == 1 && (Character.isLetter(lettre.charAt(0)) || lettre.charAt(0) == '-')) {
                boolean correct = model.verifierLettre(lettre.charAt(0));
                view.setMotAffiche(model.getMotAffiche());
                if (!correct) {
                    view.setErreur("Lettre incorrecte. Erreurs: " + model.getNombreErreurs());
                    view.setNombreErreurs(model.getNombreErreurs());
                } else {
                    view.setErreur(" ");
                }

                if (model.estTermine()) {
                    int option = JOptionPane.showConfirmDialog(view, "Félicitations ! Vous avez trouvé le mot : " + model.getMotADeviner());
                    if(option == JOptionPane.YES_OPTION) {
                    	System.exit(0);
                    }
                } else if (model.getNombreErreurs() >= 10) {
                    int option = JOptionPane.showConfirmDialog(view, "Dommage ! Le mot était : " + model.getMotADeviner());
                    if (option == JOptionPane.YES_OPTION) {
                    	System.exit(0);
                    }
                }
            } else {
                view.setErreur("Veuillez entrer une lettre.");
            }
            view.clearTextField();
        };
    }

