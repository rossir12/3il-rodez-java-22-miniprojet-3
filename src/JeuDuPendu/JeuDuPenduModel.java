package JeuDuPendu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JeuDuPenduModel {
	private boolean afficherDefinition;
	private int tempsLimite;
    private String motADeviner;
    private String definitionMot;
    private StringBuilder motAffiche;
    private int nombreErreurs;
    private final List<Character> lettresProposees = new ArrayList<>();

    public JeuDuPenduModel(String filePath) {
        choisirMot(filePath);
    }

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
        definitionMot = parts.length > 1 ? parts[1] : "Aucune définition disponible";
        motAffiche = new StringBuilder(motADeviner.length());
        for (char c : motADeviner.toCharArray()) {
        	if (Character.isLetter(c)) {
        		motAffiche.append("_");
        	} else {
        		motAffiche.append(c);
        	}
        }
        nombreErreurs = 0;
    }

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
        if (!lettreTrouvee && Character.isLetter(lettre)) {
            nombreErreurs++;
        }

        return lettreTrouvee;
    }

    public String getMotAffiche() {
        return motAffiche.toString();
    }

    public int getNombreErreurs() {
        return nombreErreurs;
    }

    public boolean estTermine() {
        return motAffiche.toString().equals(motADeviner);
    }

    public String getMotADeviner() {
        return motADeviner;
    }
    
    public void setDifficulte(boolean afficherDefinition, int tempsLimite) {
    	this.afficherDefinition = afficherDefinition;
    	this.tempsLimite = tempsLimite;
    }
    
    public boolean getAfficherDefinition() {
    	return afficherDefinition;
    }
    
    public int getTempsLimite() {
    	return tempsLimite;
    }
}

