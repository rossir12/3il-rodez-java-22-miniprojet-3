package JeuDuPendu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * La classe JeuDuPenduModel représente le modèle du jeu du pendu Elle gère le
 * choix du mot à deviner, la vérification des lettres proposées, le suivi des
 * erreurs, et d'autres fonctionnalités liées au jeu
 */

public class JeuDuPenduModel {
	private boolean afficherDefinition; // Indique si la définition du mot est affichée
	private int tempsLimite; // Temps limite pour le jeu
	private String motADeviner; // Le mot à deviner
	private StringBuilder motAffiche; // Le mot affiché avec les lettres découvertes
	private int nombreErreurs; // Nombre d'erreurs commises
	private final List<Character> lettresProposees = new ArrayList<>(); // Liste des lettres proposées
	private final List<Character> lettresFausses = new ArrayList<>(); // Liste des lettres fausses

	/**
	 * Constructeur de la classe JeuDuPenduModel. Initialise le modèle en
	 * choisissant un mot à deviner à partir du fichier spécifié.
	 * 
	 * @param filePath Chemin du fichier contenant les mots.
	 */
	public JeuDuPenduModel(String filePath) {
		choisirMot(filePath);
	}

	/**
	 * Méthode pour choisir un mot à deviner à partir du fichier spécifié.
	 * 
	 * @param filePath Chemin du fichier contenant les mots.
	 */
	void choisirMot(String filePath) {
		List<String> lines = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		Collections.shuffle(lines);
		String[] parts = lines.get(0).split(" ", 2);
		motADeviner = parts[0];
		motAffiche = new StringBuilder(motADeviner.length());
		for (int i = 0; i < motADeviner.length(); i++) {
			if (Character.isLetter(motADeviner.charAt(i))) {
				motAffiche.append("_");
			} else {
				motAffiche.append(motADeviner.charAt(i));
			}
		}
		nombreErreurs = 0;
		lettresProposees.clear();
	}

	/**
	 * Méthode pour vérifier si la lettre proposée est correcte et mettre à jour le
	 * mot affiché.
	 * 
	 * @param lettre La lettre proposée par le joueur.
	 * @return true si la lettre est correcte, sinon false.
	 */

	public boolean verifierLettre(char lettre) {
		if (lettresProposees.contains(lettre)) {
			return false;
		}

		lettresProposees.add(lettre);
		boolean lettreTrouvee = false;
		for (int i = 0; i < motADeviner.length(); i++) {
			if (motADeviner.charAt(i) == lettre || !Character.isLetter(motADeviner.charAt(i))) {
				if (motADeviner.charAt(i) == lettre) {
					motAffiche.setCharAt(i, lettre);
					lettreTrouvee = true;
				}
			}
		}
		if (!lettreTrouvee) {
			nombreErreurs++;
			if (!lettresFausses.contains(lettre)) {
				lettresFausses.add(lettre);
			}
		}
		return lettreTrouvee;
	}

	/**
	 * Méthode pour obtenir les lettres fausses déjà proposées.
	 * 
	 * @return Les lettres fausses sous forme d'une chaîne de caractères.
	 */

	public String getLettresFausses() {
		StringBuilder sb = new StringBuilder();
		for (Character lettre : lettresFausses) {
			sb.append(lettre).append(" ");
		}
		return sb.toString().trim();
	}

	/**
	 * Méthode pour obtenir le mot affiché avec les lettres découvertes.
	 * 
	 * @return Le mot affiché.
	 */

	public String getMotAffiche() {
		return motAffiche.toString();
	}

	/**
	 * Méthode pour obtenir le nombre d'erreurs commises.
	 * 
	 * @return Le nombre d'erreurs.
	 */

	public int getNombreErreurs() {
		return nombreErreurs;
	}

	/**
	 * Méthode pour vérifier si le jeu est terminé (le mot a été trouvé ou trop
	 * d'erreurs ont été commises).
	 * 
	 * @return true si le jeu est terminé, sinon false.
	 */

	public boolean estTermine() {
		return motAffiche.toString().equals(motADeviner);
	}

	/**
	 * Méthode pour obtenir le mot à deviner.
	 * 
	 * @return Le mot à deviner.
	 */

	public String getMotADeviner() {
		return motADeviner;
	}

	/**
	 * Méthode pour configurer la difficulté du jeu.
	 * 
	 * @param afficherDefinition Indique si la définition du mot est affichée.
	 * @param tempsLimite        Temps limite pour le jeu.
	 */

	public void setDifficulte(boolean afficherDefinition, int tempsLimite) {
		this.afficherDefinition = afficherDefinition;
		this.tempsLimite = tempsLimite;
	}

	/**
	 * Méthode pour obtenir l'indicateur d'affichage de la définition du mot.
	 * 
	 * @return true si la définition est affichée, sinon false.
	 */

	public boolean getAfficherDefinition() {
		return afficherDefinition;
	}

	/**
	 * Méthode pour obtenir le temps limite pour le jeu.
	 * 
	 * @return Le temps limite.
	 */

	public int getTempsLimite() {
		return tempsLimite;
	}

	/**
	 * Méthode pour réinitialiser le jeu en remettant à zéro les informations et en
	 * choisissant un nouveau mot.
	 */
	public void reinitialiserJeu() {
		lettresProposees.clear();
		nombreErreurs = 0;
		choisirMot("mots.txt");
		lettresFausses.clear();
	}
}
