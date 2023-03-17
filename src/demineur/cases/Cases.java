package demineur.cases;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import demineur.graphisme.Graphisme;


public class Cases extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	
	/**********************************************************
	 * etat : 
	 * 0 => vide, 
	 * 1 => bombe, 
	 * 2 => vide avec bombe, 
	 * 3 => vide avec nombre de bombes autour, 
	 * 4 => vide avec nombre de bombes autour
	 ***********************************************************/
	private int etatCase = 0; 
	
	private boolean contientMine = false;
	
	private boolean caseSelectionnee = false; 
	
	private boolean caseBloquee = false;
	
	private int bombesAutour = 0;

	private Graphisme graphisme = null; 

	//construction de la case
	public Cases() {
		
		try {
			jbInit();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	private void jbInit() throws Exception {
		
		this.setBackground(Graphisme.dessus);
		
		// taille de la case
		this.setMaximumSize(new Dimension(16, 16)); 
		this.setMinimumSize(new Dimension(16, 16));
		
		this.addMouseListener(this);
		this.setPreferredSize(new Dimension(16, 16));
	}

	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		//Selectionne la case si on clique dessus
		if (e.getModifiersEx() == 16 && etatCase != 1 && etatCase != 2 && !this.caseBloquee) {
			this.caseSelectionnee = true;
			repaint();
		}
	}

	public void mouseReleased(MouseEvent e) {
		//Déselectionne la case si on relâche le clique
		this.caseSelectionnee = false;
		repaint();
	}

	public void mouseEntered(MouseEvent e) {
		//Affiche la case si on passe la souris dessus
		if (e.getModifiersEx() == 16 && this.etatCase != 1 && this.etatCase != 2 && !this.caseBloquee) {
			this.caseSelectionnee = true;
			repaint();
		}
	}

	public void mouseExited(MouseEvent e) {
		//Déselectionne la case si on quitte la souris
		this.caseSelectionnee = false;
		repaint();
	}

	public boolean contientMine() {
		return this.contientMine;
	}

	public int getEtatCase() {
		return this.etatCase;
	}

	public void setEtatCase(int etatCase) {
		this.etatCase = etatCase;
	}

	public void setMine(boolean contientMine) {
		this.contientMine = contientMine;
	}

	public int getBombesAutour() {
		return this.bombesAutour;
	}

	public void setBombesAutour(int bombesAutour) {
		this.bombesAutour = bombesAutour;
	}

	public boolean estSelectionnee() {
		return this.caseSelectionnee;
	}

	public void setCaseSelectionnee(boolean caseSelectionnee) {
		this.caseSelectionnee = caseSelectionnee;
		this.paintComponent(this.getGraphics());
	}

	public void paintComponent(Graphics graphics) {
		
		super.paintComponent(graphics);
		
		if (this.graphisme != null) {
			
			if (!this.caseSelectionnee) { 
				
				if (this.etatCase == 0) {
					graphics.setColor(Color.white); //bordure haut et gauche blanche
					graphics.drawLine(0, 0, 0, 15);
					graphics.drawLine(0, 0, 15, 0);
				}
				else if (this.etatCase == 1) graphics.drawImage(Graphisme.chiffre[this.bombesAutour], 0, 0, null); // chiffre ou blanc
				else if (this.etatCase == 2) graphics.drawImage(Graphisme.drapeau, 0, 0, null); // drapeau
				else if (this.etatCase == 6) graphics.drawImage(Graphisme.erreur, 0, 0, null); // erreur de drapeau
				else if (this.etatCase == 3) graphics.drawImage(Graphisme.question, 0, 0, null); // ?
				else if (this.etatCase == 4) graphics.drawImage(Graphisme.explosion, 0, 0, null); // mine sur fond rouge
				else if (this.etatCase == 5) graphics.drawImage(Graphisme.mine, 0, 0, null); // mine
			
			} else { 
				
				if (this.etatCase == 3) {
					graphics.drawImage(Graphisme.questionSelectionnee, 0, 0, null); // ?
				} else if (this.etatCase != 1) {
					graphics.setColor(Color.gray); //bordure haut et gauche grise
					graphics.drawLine(0, 0, 0, 15);
					graphics.drawLine(0, 0, 15, 0);
				}
				
			}
			
		} else System.out.println("graphics == null");
		
		graphics.setColor(Color.darkGray); //bordure bas et droite
		graphics.drawLine(0, 15, 15, 15);
		graphics.drawLine(15, 0, 15, 15);
		graphics.dispose();
	}

	public void setCaseBloquee(boolean caseBloquee) {
		this.caseBloquee = caseBloquee;
	}

	public boolean estCaseBloquee() {
		return this.caseBloquee;
	}

	public void setGraphisme(Graphisme graphisme) {
		this.graphisme = graphisme;
	}


	public void reset() { 
		
		this.etatCase = 0;
		this.caseSelectionnee = false;
		setMine(false);
		this.setCaseBloquee(false);
		//repaint();
	}

}
