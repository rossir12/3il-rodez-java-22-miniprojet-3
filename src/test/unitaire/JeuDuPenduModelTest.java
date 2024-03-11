package test.unitaire;

import org.junit.jupiter.api.Test;
import JeuDuPendu.JeuDuPenduModel;
import static org.junit.jupiter.api.Assertions.*;

class JeuDuPenduModelTest {

    @Test
    void testErreurIncrementee() {
        JeuDuPenduModel model = new JeuDuPenduModel("mots.txt");
        int erreursInitiales = model.getNombreErreurs();

        // Simuler une lettre incorrecte
        model.verifierLettre('x');

        assertEquals(erreursInitiales + 1, model.getNombreErreurs());
    }

    @Test
    void testPenduCompleteAvecMaxErreurs() {
        JeuDuPenduModel model = new JeuDuPenduModel("mots.txt");

        // Simuler le maximum d'erreurs
        for (int i = 0; i < 10; i++) {
            model.verifierLettre('x');
        }

        assertTrue(model.getNombreErreurs() >= 10);
    }
}
