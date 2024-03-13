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
    private StringBuilder motAffiche;
    private int nombreErreurs;
    private final List<Character> lettresProposees = new ArrayList<>();
    private final List<Character> lettresFausses = new ArrayList<>();

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
        motAffiche = new StringBuilder(motADeviner.length());
        for (int i = 0; i < motADeviner.length(); i++) {
        	if(Character.isLetter(motADeviner.charAt(i))) {
        		motAffiche.append("_");
        	} else {
        		motAffiche.append(motADeviner.charAt(i));
        	}
        }
        nombreErreurs = 0;
        lettresProposees.clear();
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
        if (!lettreTrouvee) {
            nombreErreurs++;
            if(!lettresFausses.contains(lettre)) {
            	lettresFausses.add(lettre);
            }
        }
        return lettreTrouvee;
    }
    public String getLettresFausses() {
    	StringBuilder sb = new StringBuilder();
    	for(Character lettre : lettresFausses) {
    		sb.append(lettre).append(" ");
    	}
    	return sb.toString().trim();
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
    
    public void reinitialiserJeu() {
    	lettresProposees.clear();
    	nombreErreurs =0;
    	choisirMot("mots.txt");
    	lettresFausses.clear();
    }
}

