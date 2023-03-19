package demineur.graphisme;

import java.awt.image.*;
import java.awt.*;

//Cette classe contient tous les graphismes des cases
public class Graphisme {

	public static VolatileImage drapeau, question, questionSelectionnee, mine, explosion, erreur;
	public static VolatileImage[] chiffre = new VolatileImage[9];
	private Color[] couleurs = new Color[8];

	public static Color dessus = new Color(214, 208, 200); // couleur de la case

	public Graphisme(GraphicsConfiguration graphicsConfiguration) {


		/******************************************
		 * les couleurs des chiffres
		 * couleurs[0] = bleu (0, 0, 255)
		 * couleurs[1] = vert (0, 128, 0)
		 * couleurs[2] = rouge (255, 0, 0)
		 * couleurs[3] = bleu foncé (0, 0, 128)
		 * couleurs[4] = rouge foncé (128, 0, 0)
		 * couleurs[5] = turquoise (0, 128, 128)
		 * couleurs[6] = violet (128, 0, 128)
		 * couleurs[7] = noir (0, 0, 0)
		 *******************************************/
		this.couleurs[0] = new Color(0, 0, 255);
		this.couleurs[1] = new Color(0, 128, 0);
		this.couleurs[2] = new Color(255, 0, 0);
		this.couleurs[3] = new Color(0, 0, 128);
		this.couleurs[4] = new Color(128, 0, 0);
		this.couleurs[5] = new Color(0, 128, 128);
		this.couleurs[6] = new Color(128, 0, 128);
		this.couleurs[7] = new Color(0, 0, 0);

		Graphics2D graphics2D;


		
		for (int i = 0; i <= 8; i++) {
			
		    // Créer une image volatile de 16x16 pixels et la stocker dans le tableau "chiffre"
		    chiffre[i] = graphicsConfiguration.createCompatibleVolatileImage(16, 16);
		    
		    // Récupérer le contexte graphique de l'image
		    graphics2D = (Graphics2D) chiffre[i].getGraphics();
		    
		    // Configurer le rendu en activant l'antialiasing, en définissant l'épaisseur du trait, 
		    // la police de caractère et la couleur de fond
		    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    graphics2D.setStroke(new BasicStroke(1.2f));
		    graphics2D.setFont(new java.awt.Font("Monospaced", 1, 15));
		    graphics2D.setColor(new Color(249, 249, 247));
		    graphics2D.fillRect(0, 0, 16, 16);
		    
		    // Si "i" n'est pas égal à zéro, définir la couleur du texte 
		    // en utilisant le tableau "couleurs" à l'indice (i-1)
		    if (i != 0) graphics2D.setColor(couleurs[i - 1]);
		    
		    // Si "i" n'est pas égal à zéro, dessiner le texte "i" dans l'image
		    if (i != 0) graphics2D.drawString("" + i, 4, 12);
		    
		    // Définir la couleur de la ligne de séparation en gris clair
		    graphics2D.setColor(Color.lightGray);
		    
		    // Dessiner deux lignes pour former un rectangle autour de l'image
		    graphics2D.drawLine(0, 0, 0, 15);
		    graphics2D.drawLine(0, 0, 15, 0);
		    
		    // Libérer les ressources du contexte graphique
		    graphics2D.dispose();
		}




		
		// Crée une image volatile de 16x16 pixels qui représente le drapeau d'un pays
		drapeau = graphicsConfiguration.createCompatibleVolatileImage(16, 16);
		// Récupère un objet Graphics2D pour dessiner sur l'image
		graphics2D = (Graphics2D) drapeau.getGraphics();
		// Active l'anti-crénelage pour dessiner des lignes et des formes plus lisses
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// Définit l'épaisseur de la ligne à dessiner
		graphics2D.setStroke(new BasicStroke(1.2f));
		// Remplit le fond de l'image avec la couleur "dessus"
		graphics2D.setColor(dessus);
		graphics2D.fillRect(0, 0, 16, 16);
		// Dessine deux lignes blanches pour représenter la hampe du drapeau
		graphics2D.setColor(Color.white);
		graphics2D.drawLine(0, 0, 0, 15);
		graphics2D.drawLine(0, 0, 15, 0);
		// Dessine une croix noire pour représenter les attaches du drapeau
		graphics2D.setColor(Color.black);
		graphics2D.drawLine(9, 12, 9, 4);
		graphics2D.drawLine(10, 12, 10, 4);
		graphics2D.drawLine(8, 12, 11, 12);
		graphics2D.drawLine(7, 13, 12, 13);
		// Dessine un triangle rouge pour représenter un élément du drapeau
		graphics2D.setColor(Color.red);
		int[] x = {9, 4, 4, 9};
		int[] y = {5, 5, 6, 8};
		graphics2D.fillPolygon(x, y, 4);
		// Libère les ressources utilisées par l'objet Graphics2D
		graphics2D.dispose();

		


		// Crée une image volatile de 16x16 pixels pour représenter l'icône d'erreur
		erreur = graphicsConfiguration.createCompatibleVolatileImage(16, 16);
		// Obtient un objet Graphics2D pour dessiner sur l'image volatile
		graphics2D = (Graphics2D) erreur.getGraphics();
		// Active l'anticrénelage pour améliorer la qualité des traits et des formes dessinées
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// Définit l'épaisseur du trait à 1,2 pixels
		graphics2D.setStroke(new BasicStroke(1.2f));
		// Définit la couleur du fond de l'image à la couleur de dessus spécifiée
		graphics2D.setColor(dessus);
		graphics2D.fillRect(0, 0, 16, 16);
		// Dessine deux lignes blanches diagonales pour former une croix
		graphics2D.setColor(Color.white);
		graphics2D.drawLine(0, 0, 0, 15);
		graphics2D.drawLine(0, 0, 15, 0);
		// Dessine un petit triangle noir en dessous de la croix pour représenter une pointe d'erreur
		graphics2D.setColor(Color.black);
		graphics2D.drawLine(9, 12, 9, 4);
		graphics2D.drawLine(10, 12, 10, 4);
		graphics2D.drawLine(8, 12, 11, 12);
		// Dessine un triangle rouge sur le côté gauche pour indiquer une erreur
		int[] x1 = {9, 4, 4, 9};
		int[] y1 = {5, 5, 6, 8};
		graphics2D.setColor(Color.red);
		graphics2D.fillPolygon(x1, y1, 4);
		// Dessine une croix rouge pour indiquer l'emplacement de l'erreur
		graphics2D.setColor(Color.red);
		graphics2D.drawLine(3, 3, 12, 12);
		graphics2D.drawLine(3, 12, 12, 3);
		// Libère les ressources utilisées par l'objet Graphics2D
		graphics2D.dispose();


	
		
		// Création d'une image volatile de 16 x 16 pixels pour le symbole de la question
		question = graphicsConfiguration.createCompatibleVolatileImage(16, 16);
		// Obtention d'un objet Graphics2D à partir de l'image volatile créée précédemment
		graphics2D = (Graphics2D) question.getGraphics();
		// Paramétrage des options de rendu pour lisser les contours des dessins
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// Définition de l'épaisseur du trait dessiné
		graphics2D.setStroke(new BasicStroke(1.2f));
		// Couleur du fond de l'image
		graphics2D.setColor(dessus);
		// Remplissage de l'image avec la couleur de fond
		graphics2D.fillRect(0, 0, 16, 16);
		// Définition de la couleur des contours
		graphics2D.setColor(Color.white);
		// Dessin des deux traits qui forment un point d'interrogation
		graphics2D.drawLine(0, 0, 0, 15);
		graphics2D.drawLine(0, 0, 15, 0);
		// Définition de la police de caractères pour écrire le symbole '?'
		graphics2D.setFont(new java.awt.Font("Dialog", 1, 13));
		// Couleur de la police de caractères pour écrire le symbole '?'
		graphics2D.setColor(Color.blue);
		// Écriture du symbole '?' en utilisant la police de caractères et la couleur définies précédemment
		graphics2D.drawString("?", 4, 13);
		// Libération des ressources utilisées pour le dessin
		graphics2D.dispose();



		
		// Crée une image volatile pour représenter une question sélectionnée
		questionSelectionnee = graphicsConfiguration.createCompatibleVolatileImage(16, 16);
		// Obtient le contexte graphique 2D de l'image
		graphics2D = (Graphics2D) questionSelectionnee.getGraphics();
		// Active l'anti-crénelage pour des contours lissés
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// Définit l'épaisseur du trait à 1,2 pixels
		graphics2D.setStroke(new BasicStroke(1.2f));
		// Définit la couleur du dessus à partir d'un paramètre de la méthode
		graphics2D.setColor(dessus);
		// Remplit le rectangle avec la couleur du dessus
		graphics2D.fillRect(0, 0, 16, 16);
		// Définit la couleur de la ligne à gris
		graphics2D.setColor(Color.gray);
		// Trace la première ligne de la question
		graphics2D.drawLine(0, 0, 0, 15);
		// Trace la deuxième ligne de la question
		graphics2D.drawLine(0, 0, 15, 0);
		// Définit une police de caractère pour le point d'interrogation
		graphics2D.setFont(new java.awt.Font("Dialog", 1, 12));
		// Définit la couleur du point d'interrogation à bleu
		graphics2D.setColor(Color.blue);
		// Trace le point d'interrogation au centre de l'image
		graphics2D.drawString("?", 5, 12);
		// Libère les ressources utilisées par le contexte graphique
		graphics2D.dispose();


		
	
		// Crée une image volatile de 16x16 pixels pour dessiner la mine
		mine = graphicsConfiguration.createCompatibleVolatileImage(16, 16);
		// Récupère un objet Graphics2D à partir de l'image pour dessiner
		graphics2D = (Graphics2D) mine.getGraphics();
		// Active l'antialiasing pour dessiner des formes plus lisses
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// Définit l'épaisseur de la ligne dessinée à 1.2 pixels
		graphics2D.setStroke(new BasicStroke(1.2f));
		// Dessine deux lignes grises pour représenter la forme de la mine
		graphics2D.setColor(Color.lightGray);
		graphics2D.drawLine(0, 0, 0, 15);
		graphics2D.drawLine(0, 0, 15, 0);
		// Dessine un carré noir pour représenter l'emplacement de l'explosion
		graphics2D.setColor(Color.black);
		graphics2D.fillRect(5, 5, 5, 5);
		// Dessine deux lignes croisées pour représenter les fils de la mine
		graphics2D.drawLine(3, 7, 11, 7);
		graphics2D.drawLine(7, 3, 7, 11);
		// Dessine des points gris pour représenter les connecteurs des fils
		graphics2D.setColor(new Color(128, 128, 128));
		graphics2D.drawLine(2, 7, 2, 7);
		graphics2D.drawLine(4, 6, 4, 6);
		graphics2D.drawLine(4, 8, 4, 8);
		graphics2D.drawLine(4, 4, 4, 4);
		graphics2D.drawLine(4, 10, 4, 10);
		graphics2D.drawLine(6, 4, 6, 4);
		graphics2D.drawLine(6, 10, 6, 10);
		graphics2D.drawLine(7, 2, 7, 2);
		graphics2D.drawLine(7, 12, 7, 12);
		graphics2D.drawLine(8, 4, 8, 4);
		graphics2D.drawLine(8, 10, 8, 10);
		graphics2D.drawLine(10, 4, 10, 4);
		graphics2D.drawLine(10, 10, 10, 10);
		graphics2D.drawLine(10, 6, 10, 6);
		graphics2D.drawLine(10, 8, 10, 8);
		graphics2D.drawLine(12, 7, 12, 7);
		// Dessine un point blanc au centre de la mine pour représenter l'interrupteur
		graphics2D.setColor(Color.white);
		graphics2D.drawLine(6, 6, 6, 6);
		// Libère les ressources utilisées par l'objet Graphics2D
		graphics2D.dispose();




		// Création d'une image volatile de taille 16x16 avec la configuration graphique donnée
		explosion = graphicsConfiguration.createCompatibleVolatileImage(16, 16);
		// Récupération d'un objet Graphics2D pour dessiner sur l'image
		graphics2D = (Graphics2D) explosion.getGraphics();
		// Activation du lissage pour des lignes et formes plus nettes
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// Définition de l'épaisseur du trait à 1.2 pixels
		graphics2D.setStroke(new BasicStroke(1.2f));
		// Remplissage de l'image avec la couleur rouge
		graphics2D.setColor(Color.red);
		graphics2D.fillRect(0, 0, 16, 16);
		// Dessin de deux lignes en couleur brun-rouge
		graphics2D.setColor(new Color(128, 64, 64));
		graphics2D.drawLine(0, 0, 0, 15);
		graphics2D.drawLine(0, 0, 15, 0);
		// Remplissage d'un carré de 5x5 en noir et dessin d'une croix en son centre
		graphics2D.setColor(Color.black);
		graphics2D.fillRect(5, 5, 5, 5);
		graphics2D.drawLine(3, 7, 11, 7);
		graphics2D.drawLine(7, 3, 7, 11);
		// Dessin de différentes lignes en couleur rouge foncé pour former une explosion
		graphics2D.setColor(new Color(128, 0, 0));
		graphics2D.drawLine(2, 7, 2, 7);
		graphics2D.drawLine(4, 6, 4, 6);
		graphics2D.drawLine(4, 8, 4, 8);
		graphics2D.drawLine(4, 4, 4, 4);
		graphics2D.drawLine(4, 10, 4, 10);
		graphics2D.drawLine(6, 4, 6, 4);
		graphics2D.drawLine(6, 10, 6, 10);
		graphics2D.drawLine(7, 2, 7, 2);
		graphics2D.drawLine(7, 12, 7, 12);
		graphics2D.drawLine(8, 4, 8, 4);
		graphics2D.drawLine(8, 10, 8, 10);
		graphics2D.drawLine(10, 4, 10, 4);
		graphics2D.drawLine(10, 10, 10, 10);
		graphics2D.drawLine(10, 6, 10, 6);
		graphics2D.drawLine(10, 8, 10, 8);
		graphics2D.drawLine(12, 7, 12, 7);
		// Dessin d'un point blanc en son centre
		graphics2D.setColor(Color.white);
		graphics2D.drawLine(6, 6, 6, 6);
		// Libération des ressources utilisées par l'objet Graphics2D
		graphics2D.dispose();
	}

}
