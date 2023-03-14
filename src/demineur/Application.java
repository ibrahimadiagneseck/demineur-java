package demineur;

import javax.swing.*;

import demineur.demineur.Demineur;

public class Application {

	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		/*demarrer un nouveau jeux avec en parametre :
		 * hauteur,
		 * largeur,
		 * nombre de mines,
		 * niveau : 1 debutant, 2 intermediaire, 3 expert, 4 personnalise
		 */
		new Demineur(16,30,99,3);
		
	}
}
