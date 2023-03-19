package demineur;


import javax.swing.UIManager;

import demineur.demineur.Demineur;

public class Application {

	public static void main(String[] args) {
		
		/*******************************************************************
		 * Ce code définit l'apparence graphique de l'interface utilisateur 
		 * pour qu'elle ressemble à celle du système d'exploitation, 
		 * et affiche une trace de la pile d'exception en cas d'erreur.
		 *******************************************************************/
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		/******************************************************************
		 * demarrer un nouveau jeux avec en parametre :
		 * hauteur : 16,
		 * largeur : 30,
		 * nombre de mines : 99,
		 * niveau : 1 Débutant, 2 Intermediaire, 3 Expert, 4 personaliser
		 *******************************************************************/
		new Demineur(16, 30, 99, 3);
		
	}
}
