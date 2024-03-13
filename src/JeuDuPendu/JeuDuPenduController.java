package JeuDuPendu;

import javax.swing.JOptionPane;

public class JeuDuPenduController {
    private final JeuDuPenduModel model;
    private final JeuDuPenduView view;

    public JeuDuPenduController(JeuDuPenduModel model, JeuDuPenduView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
        setupListeners();
        this.view.setVisible(true);
    }
    
    private void setupListeners() {
    	view.addTextFieldListener(e -> verifierLettre());
    }
    public void verifierLettre() {
        String lettre = view.getLettre();
        if (lettre.length() == 1 && (Character.isLetter(lettre.charAt(0)) || lettre.charAt(0) == '-')) {
            boolean correct = model.verifierLettre(lettre.charAt(0));
            view.setMotAffiche(model.getMotAffiche());
            if (!correct) {
                view.setErreur("Lettre incorrecte. Erreurs: " + model.getNombreErreurs());
                view.setLettresFausses(model.getLettresFausses());
                view.setNombreErreurs(model.getNombreErreurs());
            } else {
                view.setErreur(" ");
            }

            if (model.estTermine()) {
                finDeJeu(true);
            } else if (model.getNombreErreurs() >= 10) {
                finDeJeu(false);
            }
        } else {
            view.setErreur("Veuillez entrer une lettre.");
        }
        view.clearTextField();
    }
        
        private void finDeJeu(boolean gagne) {
            String message = gagne ? "Félicitations ! Vous avez trouvé le mot : " + model.getMotADeviner() + ". Voulez-vous rejouer ?"
                                   : "Dommage ! Le mot était : " + model.getMotADeviner() + ". Voulez-vous rejouer ?";
            int option = JOptionPane.showConfirmDialog(view, message, gagne ? "Vous avez gagné !" : "Vous avez perdu !", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
            	model.reinitialiserJeu();
                demanderNiveauDifficulte();
            } else {
                System.exit(0);
            }
        }
        
        public void configurerDifficulte(String niveau) {
        	switch(niveau) {
        	case "Facile":
        		view.afficherDefinition(true);
        		break;
        	case "Moyen":
        		view.afficherDefinition(false);
        		view.demarrerTimer(300000);
        		break;
        	case "Difficile":
        		view.afficherDefinition(false);
        		view.demarrerTimer(120000);
        		break;
        	}
        }
        
        public void demanderNiveauDifficulte() {
            String[] options = {"Facile", "Moyen", "Difficile"};
            int choix = JOptionPane.showOptionDialog(view, "Choisissez un niveau de difficulté :", "Sélection du niveau", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            if (choix != JOptionPane.CLOSED_OPTION) {
                String niveau = options[choix];
                configurerDifficulte(niveau); // Configurer la difficulté selon le niveau sélectionné
                model.reinitialiserJeu(); // Réinitialiser le jeu
                view.setMotAffiche(model.getMotAffiche()); // Mettre à jour l'affichage du mot à deviner
                view.setNombreErreurs(0); // Réinitialiser le nombre d'erreurs affiché
                view.setErreur(""); // Effacer les messages d'erreur
                view.clearTextField(); // Effacer le champ de texte pour entrer une lettre
                view.setLettresFausses(""); // Effacer les lettres fausses affichées
                view.redessinerPendu(); // Redessiner le pendu
                view.setDefinition(""); // Effacer la définition affichée
            } else {
                System.exit(0);
            }
        }

    }
