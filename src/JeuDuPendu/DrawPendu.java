package JeuDuPendu;

import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Dimension;

public class DrawPendu extends JPanel {
	private int nombreErreurs = 0;
	
	public DrawPendu() {
		setPreferredSize(new Dimension(200,200));
	}

	public void setNombreErreurs(int nombreErreurs) {
		this.nombreErreurs = nombreErreurs;
		repaint();
	}
	
	@0verride
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (nombreErreurs > 0) {
			g.drawLine(10, 190, 190, 190);
		}
		if (nombreErreurs > 1) {
			g.drawLine(100, 190, 100, 50);
		}
	}
}
