package JeuDuPendu;

import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Dimension;

public class DrawPendu extends JPanel {
	private static final long serialVersionUID = 1L;
	private int nombreErreurs = 0;
	
	public DrawPendu() {
		setPreferredSize(new Dimension(200,200));
	}

	public void setNombreErreurs(int nombreErreurs) {
		this.nombreErreurs = nombreErreurs;
		repaint();
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Exemple de dessin basique, à adapter selon vos besoins
        if (nombreErreurs > 0) g.drawLine(10, 180, 150, 180); // Base
        if (nombreErreurs > 1) g.drawLine(30, 180, 30, 20);   // Poteau
        if (nombreErreurs > 2) g.drawLine(30, 20, 100, 20);   // Traverse
        if (nombreErreurs > 3) g.drawLine(100, 20, 100, 40);  // Corde
        if (nombreErreurs > 4) g.drawOval(90, 40, 20, 20);    // Tête
        if (nombreErreurs > 5) g.drawLine(100, 60, 100, 100); // Corps
        if (nombreErreurs > 6) g.drawLine(100, 70, 80, 50);   // Bras gauche
        if (nombreErreurs > 7) g.drawLine(100, 70, 120, 50);  // Bras droit
        if (nombreErreurs > 8) g.drawLine(100, 100, 80, 130); // Jambe gauche
        if (nombreErreurs > 9) g.drawLine(100, 100, 120, 130); // Jambe droite
	}
}

