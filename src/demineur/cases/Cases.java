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
	private int etat = 0; 
	
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

	//Selectionne la case si on clique dessus
	public void mousePressed(MouseEvent e) {
		
		if (e.getModifiersEx() == 16 && etat != 1 && etat != 2 && !this.caseBloquee) {
			caseSelectionnee = true;
			repaint();
		}
	}

	//Déselectionne la case si on relâche le clique
	public void mouseReleased(MouseEvent e) {
		caseSelectionnee = false;
		repaint();
	}

	public void mouseEntered(MouseEvent e) {
		//Affiche la case si on passe la souris dessus
		if (e.getModifiersEx() == 16 && etat != 1 && etat != 2 && !this.caseBloquee) {
			caseSelectionnee = true;
			repaint();
		}
	}

	public void mouseExited(MouseEvent e) {
		//Déselectionne la case si on quitte la souris
		caseSelectionnee = false;
		repaint();
	}

	public boolean isMine() {
		return this.contientMine;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public void setMine(boolean contientMine) {
		this.contientMine = contientMine;
	}

	public int getChiffre() {
		return this.bombesAutour;
	}

	public void setChiffre(int bombesAutour) {
		this.bombesAutour = bombesAutour;
	}

	public boolean isSelected() {
		return caseSelectionnee;
	}

	public void setSelected(boolean selected) {
		this.caseSelectionnee = selected;
		this.paintComponent(this.getGraphics());
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (graphisme != null) {
			if (!caseSelectionnee) { // si la case n'est pas sélectionnée
				if (etat == 0) { // si la case est vide
					g.setColor(Color.white); //bordure haut et gauche blanche
					g.drawLine(0, 0, 0, 15);
					g.drawLine(0, 0, 15, 0);
				}
				else if (etat == 1) g.drawImage(Graphisme.chiffre[this.bombesAutour], 0, 0, null); //chiffre ou blanc
				else if (etat == 2) g.drawImage(Graphisme.drapeau, 0, 0, null); //drapeau
				else if (etat == 6) g.drawImage(Graphisme.erreur, 0, 0, null); //erreur de drapeau
				else if (etat == 3) g.drawImage(Graphisme.question, 0, 0, null); //?
				else if (etat == 4) g.drawImage(Graphisme.boum, 0, 0, null); //mine sur fond rouge
				else if (etat == 5) g.drawImage(Graphisme.mine, 0, 0, null); //mine
			}
			else { // si la case est sélectionnée
				if (etat == 3) g.drawImage(Graphisme.questionSel, 0, 0, null); //?
				else if (etat != 1) { // du reste du programme
					g.setColor(Color.gray); //bordure haut et gauche grise
					g.drawLine(0, 0, 0, 15);
					g.drawLine(0, 0, 15, 0);
				}
			}
		}
		//else System.out.println("gr == null");
		g.setColor(Color.darkGray); //bordure bas et droite
		g.drawLine(0, 15, 15, 15);
		g.drawLine(15, 0, 15, 15);
		g.dispose();
	}

	public void setBlocked(boolean blocked) {
		this.caseBloquee = blocked;
	}

	public boolean isBlocked() {
		return this.caseBloquee;
	}

	public void setGraphisme(Graphisme graphisme) {
		this.graphisme = graphisme;
	}


	// remet la case à 0
	public void reset() { 
		this.etat = 0;
		this.caseSelectionnee = false;
		setMine(false);
		setBlocked(false);
		//repaint();
	}

}
