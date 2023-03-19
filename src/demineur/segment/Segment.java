package demineur.segment;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;


/*
 * affigage des chiffres 
 * chaque panneau contient 3 chiffres à 7 segments
 */
public class Segment extends JPanel { 

	private static final long serialVersionUID = 1L;
	
	/**************************************************************
	 * table de verite des segments (7) de chaque chiffre :
	 * 
	 * 3 chiffres à 7 segments :
	 * 
	 * 		 -		 -		 -
	 * 		| |		| |		| |
	 * 		 -		 -		 -
	 * 		| |		| |		| |
	 * 		 - 		 - 		 -
	 * 
	 ************************************************************/
	private boolean[][] chiffres = { 
			
			{true, true, true, true, true, true, false},     // 0
			{false, false, true, false, false, true, false}, // 1
			{false, true, true, true, true, false, true},    // 2
			{false, true, true, false, true, true, true},    // 3
			{true, false, true, false, false, true, true},   // 4
			{true, true, false, false, true, true, true},    // 5
			{true, true, false, true, true, true, true},     // 6
			{false, true, true, false, false, true, false},  // 7
			{true, true, true, true, true, true, true},      // 8
			{true, true, true, false, true, true, true}      // 9
			
	};
	
	private int valeur; 
	
	final private Color affiche = new Color(255,0,0); // segment allumé avec une couleur rouge vif
	final private Color cache = new Color(80,0,0); // segment eteint avec une nuance de rouge plus foncée
	
	private Border border;

	public Segment() {
		
		try {
			jbInit();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			
		}
	}
	
	private void jbInit() throws Exception {
		
		this.border = BorderFactory.createBevelBorder(
									BevelBorder.LOWERED,
									Color.white,
									Color.white,
									Color.darkGray,
									Color.gray);
		
		this.setBackground(Color.black);
		this.setBorder(this.border);
		this.setPreferredSize(new Dimension(49,27));
	}
	
	public int getValeur() {
		return valeur;
	}
	
	public void setValeur(int valeur) {
		
		if (valeur >= 0) {
			
			if (valeur <= 999) {
				this.valeur = valeur;
			} else {
				this.valeur = 999;
			}
			
			repaint();
		}
	}

	public void paintComponent(Graphics graphics) { 
		
		super.paintComponent(graphics);
		
		Graphics2D graphics2D = (Graphics2D) graphics;
		
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		graphics2D.setStroke(new BasicStroke(1.6f));
		
		int[] segments=new int[3];//le chiffre affiché par chaque afficheur
		 
		segments[0] = valeur / 100;
		segments[1] = (valeur / 10) % 10; 
		segments[2] = valeur % 10;
		
		/*
		 * chaque segment
		 */
		for (int i = 0; i < 3; i++) {
			
			int n = segments[i];
			
			if (chiffres[n][0] == true) {
				graphics2D.setColor(this.affiche);
			} else {
				graphics2D.setColor(this.cache);
			}
			
			// segment 1
			graphics2D.drawLine(3+i*15, 3, 3+i*15, 12);
			graphics2D.drawLine(4+i*15, 4, 4+i*15, 11);
			graphics2D.drawLine(5+i*15, 5, 5+i*15, 10);

			if (chiffres[n][1] == true) {
				graphics2D.setColor(this.affiche);
			} else {
				graphics2D.setColor(this.cache);
			}
			
			// segment 2
			graphics2D.drawLine(4+i*15, 2, 13+i*15, 2);
			graphics2D.drawLine(5+i*15, 3, 12+i*15, 3);
			graphics2D.drawLine(6+i*15, 4, 11+i*15, 4);

			if (chiffres[n][2]==true) {
				graphics2D.setColor(this.affiche);
			} else {
				graphics2D.setColor(this.cache);
			}
			
			// segment 3
			graphics2D.drawLine(12+i*15, 5, 12+i*15, 10);
			graphics2D.drawLine(13+i*15, 4, 13+i*15, 11);
			graphics2D.drawLine(14+i*15, 3, 14+i*15, 12);

			if (chiffres[n][3]==true) {
				graphics2D.setColor(this.affiche);
			} else {
				graphics2D.setColor(this.cache);
			}
			
			// segment 4
			graphics2D.drawLine(3+i*15, 14, 3+i*15, 22);
			graphics2D.drawLine(4+i*15, 15, 4+i*15, 21);
			graphics2D.drawLine(5+i*15, 16, 5+i*15, 20);

			if (chiffres[n][4]==true) {
				graphics2D.setColor(this.affiche);
			} else {
				graphics2D.setColor(this.cache);
			}

			// segment 5
			graphics2D.drawLine(6+i*15, 21, 11+i*15, 21);
			graphics2D.drawLine(5+i*15, 22, 12+i*15, 22);
			graphics2D.drawLine(4+i*15, 23, 13+i*15, 23);

			if (chiffres[n][5]==true) {
				graphics2D.setColor(this.affiche);
			} else {
				graphics2D.setColor(this.cache);
			}

			// segment 6
			graphics2D.drawLine(12+i*15, 16, 12+i*15, 20);
			graphics2D.drawLine(13+i*15, 15, 13+i*15, 21);
			graphics2D.drawLine(14+i*15, 14, 14+i*15, 22);
				

			if (chiffres[n][6]==true) {
				graphics2D.setColor(this.affiche);
			} else {
				graphics2D.setColor(this.cache);
			}

			// segment 7
			graphics2D.drawLine(5+i*15, 12, 12+i*15, 12);
			graphics2D.drawLine(4+i*15, 13, 13+i*15, 13);
			graphics2D.drawLine(5+i*15, 14, 12+i*15, 14);
		}
	}
}
