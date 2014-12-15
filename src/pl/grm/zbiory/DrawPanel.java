package pl.grm.zbiory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	private static final long	serialVersionUID	= 1L;
	private IntersectionCounter	rownania;
	
	public void setRownania(IntersectionCounter rownania) {
		this.rownania = rownania;
	}
	
	/**
	 * Create the panel.
	 */
	public DrawPanel() {}
	
	public void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLUE);
		for (Line line : rownania.lines) {
			g2d.drawLine((int) line.x1, (int) line.y1, (int) line.x2, (int) line.y2);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (rownania != null) {
			doDrawing(g);
		}
	}
}
