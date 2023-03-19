package demineur.demineur;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import demineur.apropos.Apropos;
import demineur.cases.Cases;
import demineur.graphisme.Graphisme;
import demineur.personaliser.Personaliser;
import demineur.segment.Segment;
import demineur.temps.Temps;

public class Demineur extends JFrame implements MouseListener, WindowListener, ActionListener {

	private static final long serialVersionUID = 1L;

	/****************************************************************
	 * Ces objets JPanel peuvent être personnalisés 
	 * en y ajoutant des composants graphiques, tels que des boutons, 
	 * des étiquettes ou d'autres éléments d'interface utilisateur.
	 *****************************************************************/
	private JPanel entetecasesJeux = new JPanel();
	private JPanel corpscasesJeux = new JPanel();

	/*****************************************************************
	 * Le GridBagLayout est un gestionnaire de mise en page 
	 * qui permet de positionner les composants graphiques de manière flexible 
	 * et précise dans un conteneur JPanel.
	 ************************************************************************/
	private GridBagLayout layoutcorpscasesJeux = new GridBagLayout();

	/****************************************************************************************
	 * créer un nouveau bouton de niveau JButton, il sera initialement créé sans texte ni icône, 
	 * mais peut être personnalisé par la suite en ajoutant du texte ou une icône, 
	 * en modifiant sa couleur ou sa taille, en y ajoutant des événements de clic, etc.
	 *****************************************************************************************/
	private JButton bouton = new JButton();

	/****************************************************************************************
	 * pour définir une bordure autour d'un composant graphique, 
	 * il peut être personnalisée en définissant différents styles, couleurs et épaisseurs. 
	 ****************************************************************************************/
	private Border bordure;

	/*********************************************************************************
	 * objets Segment pour afficher le nombre de mines restantes ou le temps écoulé. 
	 * personnalisés pour afficher différents chiffres 
	 * et sont généralement mis à jour régulièrement à mesure que le jeu progresse.
	 *********************************************************************************/
	private Segment nombreMines = new Segment();
	private Segment nombreTemps = new Segment();

	/****************************************************************************************************************
	 * La classe JMenuBar est utilisée pour créer une barre de menus 
	 * dans une interface utilisateur graphique.
	 * elle peut contenir des éléments de menu tels que des menus déroulants, 
	 * des éléments de menu et des raccourcis clavier pour exécuter des commandes ou des actions dans l'application.
	 ****************************************************************************************************************/
	private JMenuBar menu = new JMenuBar();

	/*********************************************************************************************************
	 * La classe JCheckBox est utilisée pour créer une case à cocher dans une interface utilisateur graphique.
	 * Lorsqu'une case à cocher est cochée, l'application peut exécuter une commande ou une action spécifique.
	 ***********************************************************************************************************/
	private JCheckBox pause = new JCheckBox("Pause");

	/*****************************************************************************************************
	 * La classe JMenu est utilisée pour créer un menu déroulant dans une interface utilisateur graphique.
	 *****************************************************************************************************/
	private JMenu partie = new JMenu("Partie");
	private JMenu aide = new JMenu("Aide");

	/************************************************************************************************************
	 * La classe JMenuItem est utilisée pour créer des éléments de menu dans une interface utilisateur graphique. 
	 * ils sont ajoutés à un menu déroulant ou à une barre de menus pour offrir des commandes 
	 * ou des actions spécifiques à l'utilisateur.
	 *************************************************************************************************************/
	private JMenuItem menuNouveau = new JMenuItem("Nouveau");
	private JMenuItem apropos = new JMenuItem("A propos");
	private JMenuItem menuSauvegarder = new JMenuItem("Sauvegarder");
	private JMenuItem menuStatistiques = new JMenuItem("Statistiques");

	/***************************************************************************************
	 * La classe JCheckBoxMenuItem est utilisée pour créer des éléments 
	 * de menu de case à cocher dans une interface utilisateur graphique.
	 * ils sont ajoutés à un menu déroulant ou à une barre de menus pour offrir des options 
	 * ou des paramètres personnalisables à l'utilisateur.
	 ***************************************************************************************/
	JCheckBoxMenuItem menuDebutant = new JCheckBoxMenuItem("Débutant");
	JCheckBoxMenuItem menuIntermediaire = new JCheckBoxMenuItem("Intermédiaire");
	JCheckBoxMenuItem menuExpert = new JCheckBoxMenuItem("Expert");
	JCheckBoxMenuItem menuPersonaliser = new JCheckBoxMenuItem("Personaliser");

	/********************************************************************************
	 * La classe BoxLayout est utilisée pour organiser les composants 
	 * d'une interface utilisateur graphique dans une boîte horizontale ou verticale.
	 * La disposition de niveau LINE_AXIS signifie que les composants sont placés 
	 * dans une ligne horizontale, de gauche à droite, dans le cas présent.
	 *********************************************************************************/
	private BoxLayout layoutentetecasesJeux = new BoxLayout(entetecasesJeux, BoxLayout.LINE_AXIS);

	/*****************************************************************
	 * Ces variables peuvent contenir n'importe quel composant Swing, 
	 * tel qu'un bouton, une zone de texte ou une étiquette. 
	 ******************************************************************/
	private Component box2;
	private Component box3;
	private Component box1;
	private Component box4;

	/**********************************************************************************
	 * Les icônes sont des images utilisées dans les applications Swing 
	 * pour donner des informations visuelles ou pour décorer l'interface utilisateur. 
	 * Ces icônes peuvent être chargées à partir de fichiers image 
	 * ou créées à l'aide de dessins personnalisés.
	 ************************************************************************************/
	private Icon cool, oups, boum, win;

	/****************************************
	 * les attributs
	 ****************************************/
	public int nombreDrapeauPoses = 0;
	private int nombreTotolMines; 
	private int nombreCasesLargeur;
	private int nombreCasesHauteur;
	private int nombreCasesNonDecouvertes;

	/*******************************
	 * tableau de cases du casesJeux
	 *******************************/
	Cases[][] casesJeux;

	/****************************************
	 * les attributs
	 ****************************************/
	private String mines; //chaînes de caractère qui contient la repartition des mines
	private int[][] casesSelectionnees = new int[8][2]; //reperages des cases selectionnees pour les deselectionnes lors du relachement de la sourie
	private Temps temps = new Temps(nombreTemps); //timer sur l'affichage Segment nombreTemps
	private int niveau;


	/***************************************************
	 * demarrer un nouveau casesJeux avec en parametre :
	 * hauteur,
	 * largeur,
	 * nombre de mines,
	 * niveau 
	 * 
	 * niveau == 1 -> Facile
	 * niveau == 2 -> Moyenne
	 * niveau == 3 -> Difficile
	 * niveau == 4 -> Personaliser
	 ****************************************************/
	public Demineur(int hauteur, int largeur, int mines, int niveau) {

		this.nombreCasesHauteur = hauteur;
		this.nombreCasesLargeur = largeur;
		this.nombreCasesNonDecouvertes = this.nombreCasesHauteur * this.nombreCasesLargeur;
		this.nombreTotolMines = mines;
		this.niveau = niveau;

		casesJeux = new Cases[this.nombreCasesHauteur][this.nombreCasesLargeur];


		/******************************
		 * creation des cases
		 ******************************/
		for(int i = 0; i < this.nombreCasesHauteur; i++) {
			for (int j = 0; j < this.nombreCasesLargeur; j++) {
				casesJeux[i][j] = new Cases();
			}
		}


		/*****************************************
		 * selection du mode choisi dans le JMenu
		 *****************************************/
		if (niveau == 1) menuDebutant.setSelected(true);
		if (niveau == 2) menuIntermediaire.setSelected(true);
		if (niveau == 3) menuExpert.setSelected(true);
		if (niveau == 4) menuPersonaliser.setSelected(true);


		/*************************************
		 * initialisation du casesJeux
		 *************************************/
		nouveaucasesJeux();


		/*
		 * Le code appelle une méthode pour initialiser des composants graphiques, 
		 * rend visible une fenêtre et défini le focus sur un bouton. 
		 * S'il y a une exception, celle-ci est imprimée sur la sortie standard.
		 */
		try {
			jbInit();
			this.setVisible(true);
			bouton.requestFocus();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/***************************************************
	 * demarrer un nouveau casesJeux 
	 ****************************************************/
	public void nouveaucasesJeux() {

		temps.cancel(); // timer à 0
		bouton.setIcon(cool); // icon initial du bouton
		nombreDrapeauPoses = 0; // nombre de drapeaux poses

		this.nombreCasesNonDecouvertes = this.nombreCasesHauteur * this.nombreCasesLargeur;

		nombreMines.setValeur(nombreTotolMines);
		nombreTemps.setValeur(0);
		corpscasesJeux.setVisible(true); //affichage du corps du casesJeux
		pause.setSelected(false);


		/*
		 * la chaîne mines, 1 = mine, 0 = rien
		 */
		mines = "";


		/*
		 * on charge la chaîne mines de 1 (nombre de 1 == nombre de mines)
		 */
		for(int i = 0; i < nombreTotolMines; i++) mines = mines + "1";


		/*
		 * boucle pour ajouter des "0" à la chaîne jusqu'à ce qu'elle atteigne le nombre de cases total
		 * nombre de 1 + nombre de 0 = nombre de cases total
		 */
		while (mines.length() < this.nombreCasesHauteur * this.nombreCasesLargeur) {

			// Choix aléatoire d'une position dans la chaîne
			int i = (int) (Math.random() * (mines.length() + 1));

			// Insertion d'un "0" à la position choisie
			mines = mines.substring(0, i) + "0" + mines.substring(i);
		}



		/**********************************
		 * parametres de chaque case
		 **********************************/
		for (int i = 0; i < this.nombreCasesHauteur; i++) {

			for (int j = 0; j < this.nombreCasesLargeur; j++) {

				// Réinitialiser l'état de la case
				casesJeux[i][j].reset();

				// Retirer tout écouteur de souris actuel sur la case
				casesJeux[i][j].removeMouseListener(this);

				// Ajouter un nouvel écouteur de souris pour la case
				casesJeux[i][j].addMouseListener(this); 

				// Vérifier si cette case contient une mine en utilisant la chaîne de caractères 'mines'
				if (mines.charAt(i * this.nombreCasesLargeur + j) == '1') 
					// Si la case contient une mine, marquer cette case comme contenant une mine
					casesJeux[i][j].setMine(true);
			}
		}



		/************************************************************************
		 * pour demander à un composant graphique de se redessiner lui-même.
		 * il doit être actualisé à l'écran.
		 ***********************************************************************/
		repaint();


		/***********************************************************************
		 * le nombre de mines autour de chaque case.
		 * le nombre de mines adjacentes à chaque case dans une grille de jeu
		 ***********************************************************************/
		for(int i = 0; i < this.nombreCasesHauteur; i++) {

			for(int j = 0; j < this.nombreCasesLargeur; j++) {

				// Vérifier si la case actuelle ne contient pas de mine
				if (!casesJeux[i][j].contientMine()) {

					int n = 0; // Initialiser un compteur de mines adjacentes

					/*
					 * Vérifier la présence de mines dans les cases voisines
					 * Utilisons un try catch car ces cases voisines n'existent pas forcement.
					 */
					try {
						if (casesJeux[i - 1][j - 1].contientMine()) n++;
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {}

					try {
						if (casesJeux[i - 1][j].contientMine()) n++;
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {}

					try {
						if (casesJeux[i - 1][j + 1].contientMine()) n++;
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {}

					try {
						if (casesJeux[i][j - 1].contientMine()) n++;
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {}

					try {
						if (casesJeux[i][j + 1].contientMine()) n++;
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {}

					try {
						if (casesJeux[i + 1][j - 1].contientMine()) n++;
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {}

					try {
						if (casesJeux[i + 1][j].contientMine()) n++;
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {}

					try {
						if (casesJeux[i + 1][j + 1].contientMine()) n++;
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {}

					// Mettre à jour la case actuelle avec le nombre de mines adjacentes
					casesJeux[i][j].setBombesAutour(n);
				}
			}
		}
	}


	private void jbInit() throws Exception {

		// Création d'une bordure gravée en blanc avec une couleur gris clair
		bordure = BorderFactory.createEtchedBorder(Color.white, new Color(0, 0, 255));

		// Création de boîtes "glue" pour le placement des éléments dans la fenêtre
		box1 = Box.createHorizontalStrut(8); // Création d'un espace horizontal de 8 pixels
		box1.setSize(10, 150); // Définition de la taille de la boîte
		
		box2 = Box.createGlue();
		box3 = Box.createGlue();
		
		box4 = Box.createHorizontalStrut(8);
		box4.setSize(5, 50);

		// Ajout d'un écouteur d'événements de fermeture de la fenêtre
		this.addWindowListener(this);

		// Définition de la taille de la fenêtre en fonction du nombre de cases et de leur taille
		int tailleX = this.nombreCasesLargeur * 16 + 290; //largeur de la fenetre
		int tailleY = this.nombreCasesHauteur * 16 + 230;
		if (tailleX < 160) tailleX = 150; //largeur minimum

		// Définition de la taille de la fenêtre, du titre et de son non-redimensionnement
		this.setSize(tailleX + 6, tailleY + 50 + 23 + 25); //6=largeur du cadre de la fenetre, 25=hauteur de la barre windows
		this.setTitle("Jeux du Démineur");
		this.setResizable(false);

		/*********************************************************************************
		 * Création d'un menu avec des éléments associés à des écouteurs d'événements.
		 * Ce mnémonique est 'P', ce qui signifie que l'utilisateur peut accéder 
		 * rapidement au sous-menu "Partie" en appuyant sur la touche Alt et la lettre 'P'.
		 ***********************************************************************************/
		
		menuNouveau.addActionListener(this);
		menuNouveau.setMnemonic('N');
		
		menuDebutant.addActionListener(this);
		menuDebutant.setMnemonic('F');
		
		menuIntermediaire.addActionListener(this);
		menuIntermediaire.setMnemonic('M');
		
		menuExpert.addActionListener(this);
		menuExpert.setMnemonic('D');
		
		menuPersonaliser.addActionListener(this);
		menuPersonaliser.setMnemonic('P');
		
		partie.setMnemonic('N');
		
		partie.add(menuNouveau);
		partie.add(new JSeparator());
		partie.add(menuDebutant);
		partie.add(menuIntermediaire);
		partie.add(menuExpert);
		partie.add(new JSeparator());
		partie.add(menuSauvegarder);
		partie.add(menuStatistiques);
		partie.add(new JSeparator());
		partie.add(menuPersonaliser);
		
		/*
		 * une propriété booléenne qui permet de spécifier si 
		 * la bordure de l'objet doit être peinte. 
		 * En définissant cette propriété à "false", 
		 * la bordure de l'objet "menu" ne sera pas affichée.
		 */
		menu.setBorderPainted(false);
		
		menu.add(partie);
		menu.add(pause);
		menu.add(aide);
		
		pause.setMnemonic('P');
		pause.setOpaque(false);
		pause.setFocusPainted(false);
		pause.addActionListener(this);
		
		aide.add(apropos);
		aide.setMnemonic('A');
		
		apropos.addActionListener(this);
		apropos.setMnemonic('A');
		
		/***************************************************************************************************
		 * La méthode "setJMenuBar" permet de définir le menu de la barre de menu pour la fenêtre courante.
		 ***************************************************************************************************/
		this.setJMenuBar(menu);

		
		/*****************************************************************************
		 * définition de la taille et du style de plusieurs éléments graphiques.
		 * cadrant nombre de mines et cadrant temps ecoulé
		 **************************************************************************/
		nombreMines.setMaximumSize(new Dimension(49, 27)); // définit la taille maximale du composant "nombreMines"
		nombreTemps.setMaximumSize(new Dimension(49, 27)); // définit la taille maximale du composant "nombreTemps"
		
		bouton.setText("Rejouer");
		bouton.setFont(new Font("Arial", Font.BOLD, 12));
		bouton.setForeground(Color.BLACK);
		bouton.setMaximumSize(new Dimension(40, 40)); // définit la taille maximale du composant "bouton"
		bouton.setMinimumSize(new Dimension(40, 40)); // définit la taille minimale du composant "bouton"
		
		entetecasesJeux.setBorder(bordure); // définit la bordure pour le conteneur "entetecasesJeux"
		entetecasesJeux.setPreferredSize(new Dimension(550, 100)); // définit la taille préférée pour le conteneur "entetecasesJeux"
		entetecasesJeux.setLayout(layoutentetecasesJeux); // définit le layout pour le conteneur "entetecasesJeux"
		
		corpscasesJeux.setBorder(bordure); // définit la bordure pour le conteneur "corpscasesJeux"
		corpscasesJeux.setPreferredSize(new Dimension(tailleX, tailleY)); // définit la taille préférée pour le conteneur "corpscasesJeux"
		corpscasesJeux.setLayout(layoutcorpscasesJeux); // définit le layout pour le conteneur "corpscasesJeux"
		
		nombreMines.setValeur(nombreTotolMines); // définit la valeur du composant "nombreMines" à "nombreTotalMines"
		nombreTemps.setValeur(0); // définit la valeur du composant "nombreTemps" à 0
		
		bouton.setPreferredSize(new Dimension(100, 100)); // définit la taille préférée du composant "bouton"
		bouton.setFocusPainted(false); // désactive l'affichage de la bordure autour du bouton lorsqu'il est cliqué
		bouton.setIcon(cool); // définit l'icône du bouton à "cool"
		bouton.setMargin(new Insets(0, 0, 0, 0)); // définit la marge interne du bouton à 0
		bouton.addActionListener(new java.awt.event.ActionListener() { // ajoute un écouteur d'événements au bouton
			public void actionPerformed(ActionEvent e) {
				bouton_actionPerformed(e); // appelle la méthode "bouton_actionPerformed()" lorsque le bouton est cliqué
			}
		});

		// Ajout des éléments graphiques pour l'en-tête de la fenêtre
		entetecasesJeux.add(box1, null); // Ajout d'une boîte vide
		entetecasesJeux.add(nombreMines, null); // Ajout d'un label pour afficher le nombre de mines restantes
		entetecasesJeux.add(box2, null); // Ajout d'une boîte vide
		entetecasesJeux.add(bouton, null); // Ajout du bouton pour recommencer le jeu
		entetecasesJeux.add(box3, null); // Ajout d'une boîte vide
		entetecasesJeux.add(nombreTemps, null); // Ajout d'un label pour afficher le temps écoulé
		entetecasesJeux.add(box4, null); // Ajout d'une boîte vide
		
		this.getContentPane().add(entetecasesJeux, BorderLayout.NORTH); // Ajout de l'en-tête à la partie supérieure de la fenêtre
		this.getContentPane().add(corpscasesJeux, BorderLayout.CENTER); // Ajout du plateau de jeu à la partie centrale de la fenêtre

		// Création de l'objet Graphisme pour contenir les graphismes de toutes les cases
		Graphisme graphisme = new Graphisme(this.getGraphicsConfiguration());

		// Placement des cases dans la fenêtre
		for (int i = 0; i < this.nombreCasesHauteur; i++) {
			
			for (int j = 0; j < this.nombreCasesLargeur; j++) {
				
				casesJeux[i][j].setGraphisme(graphisme); // On assigne les graphismes pour chaque case
				
				// On ajoute la case à la grille de la fenêtre
				corpscasesJeux.add(
						casesJeux[i][j], 
						new GridBagConstraints(j, i, 1, 1, 0.0, 0.0, 
								GridBagConstraints.CENTER, 
								GridBagConstraints.NONE, 
								new Insets(0, 0, 0, 0), 0, 0)
				); 
			}
		}
	}

	// Retourne le reperage de la case sous le clic de la souris si elle existe
	public int[] caseClic(int x, int y) {
		
		// Définir le décalage (OFFSET) de la grille de jeu à partir de la position de la première case
		int OFFSETX = (int) casesJeux[0][0].getX() + 3; // 3 = largeur du cadre de la fenetre
		int OFFSETY = (int) casesJeux[0][0].getY() + 22; // 22 = hauteur du cadre de la fenetre
		
		// Initialiser les variables pour les coordonnées de la case cliquée
		int posx = -1, posy = -1;
		
		// Calculer la position x de la case cliquée
		if (x - OFFSETX >= 0) {
			posx = (x - OFFSETX) / 16; // 16 = largeur d'une case
		}
		
		// Vérifier si la position x est en dehors de la grille de jeu
		if (posx >= this.nombreCasesLargeur) {
			posx = -1;
		}
		
		// Calculer la position y de la case cliquée
		if (y - OFFSETY >= 0 && posx != -1) {
			posy = (y - OFFSETY) / 16; // 16 = hauteur d'une case
		}
		
		// Vérifier si la position y est en dehors de la grille de jeu
		if (posy >= this.nombreCasesHauteur) {
			posy = -1;
		}
		
		// Si la case cliquée n'est pas dans la grille de jeu, renvoyer [-1, -1]
		if (posy == -1) {
			posx = -1;
		}
		
		// Renvoyer les coordonnées de la case cliquée sous forme de tableau
		int[] retour = {posx, posy};
		
		return retour;
	}


	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		
		try {
			
			int x = (int) ((JPanel) e.getSource()).getLocation().getX() + e.getX() + 3; //retourne une exception si on est pas au dessus d'un panneau
			int y = (int) ((JPanel) e.getSource()).getLocation().getY() + e.getY() + 22;
			
			int[] coordonnee = caseClic(x, y); //coordonnees de la case enfoncee enregistrees dans coord
			
			bouton.setIcon(oups); 

			//si clic droit au dessus d'une case
			if (e.getButton() == MouseEvent.BUTTON3 && coordonnee[1] != -1 && coordonnee[0] != -1) {
				
				int temp = casesJeux[coordonnee[1]][coordonnee[0]].getEtatCase();
				
				switch (temp) {
				
					case 0: //affichage d'un drapeau
						casesJeux[coordonnee[1]][coordonnee[0]].setEtatCase(2);
						nombreDrapeauPoses++;
						nombreMines.setValeur(nombreTotolMines - nombreDrapeauPoses);
						break;
					case 2: //affichage d'un ?
						casesJeux[coordonnee[1]][coordonnee[0]].setEtatCase(3);
						nombreDrapeauPoses--;
						nombreMines.setValeur(nombreTotolMines - nombreDrapeauPoses);
						break;
					case 3: 
						casesJeux[coordonnee[1]][coordonnee[0]].setEtatCase(0);
						break;
				}
				
				casesJeux[coordonnee[1]][coordonnee[0]].repaint();
			}

			//si clic gauche, on selectionne les cases autour
			y = coordonnee[1];
			x = coordonnee[0];
			
			if (e.getButton() == MouseEvent.BUTTON1 
					&& x != -1 
					&& y != -1 
					&& casesJeux[y][x].getEtatCase() == 1 
					&& casesJeux[y][x].getBombesAutour() != 0) {
				
				//on enregistre les coordonnees des cases selectionnees
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 2; j++) {
						casesSelectionnees[i][j] = -1; //effacement de la memoire
					}
				}
				
				//essai sur les huit cases autour
				try {
					if (casesJeux[y - 1][x - 1].getEtatCase() == 0) {
						casesJeux[y - 1][x - 1].setCaseSelectionnee(true);
						casesSelectionnees[0][0] = y - 1;
						casesSelectionnees[0][1] = x - 1;
					}
				} catch (Exception exc) {}
				
				try {
					if (casesJeux[y - 1][x].getEtatCase() == 0) {
						casesJeux[y - 1][x].setCaseSelectionnee(true);
						casesSelectionnees[1][0] = y - 1;
						casesSelectionnees[1][1] = x;
					}
				} catch (Exception exc) {}
				
				try {
					if (casesJeux[y - 1][x + 1].getEtatCase() == 0) {
						casesJeux[y - 1][x + 1].setCaseSelectionnee(true);
						casesSelectionnees[2][0] = y - 1;
						casesSelectionnees[2][1] = x + 1;
					}
				} catch (Exception exc) {}
				
				try {
					if (casesJeux[y][x - 1].getEtatCase() == 0) {
						casesJeux[y][x - 1].setCaseSelectionnee(true);
						casesSelectionnees[3][0] = y;
						casesSelectionnees[3][1] = x - 1;
					}
				} catch (Exception exc) {}
				
				try {
					if (casesJeux[y][x + 1].getEtatCase() == 0) {
						casesJeux[y][x + 1].setCaseSelectionnee(true);
						casesSelectionnees[4][0] = y;
						casesSelectionnees[4][1] = x + 1;
					}
				} catch (Exception exc) {}
				
				try {
					if (casesJeux[y + 1][x - 1].getEtatCase() == 0) {
						casesJeux[y + 1][x - 1].setCaseSelectionnee(true);
						casesSelectionnees[5][0] = y + 1;
						casesSelectionnees[5][1] = x - 1;
					}
				} catch (Exception exc) {}
				
				try {
					if (casesJeux[y + 1][x].getEtatCase() == 0) {
						casesJeux[y + 1][x].setCaseSelectionnee(true);
						casesSelectionnees[6][0] = y + 1;
						casesSelectionnees[6][1] = x;
					}
				} catch (Exception exc) {}
				
				try {
					if (casesJeux[y + 1][x + 1].getEtatCase() == 0) {
						casesJeux[y + 1][x + 1].setCaseSelectionnee(true);
						casesSelectionnees[7][0] = y + 1;
						casesSelectionnees[7][1] = x + 1;
					}
				} catch (Exception exc) {}
			}
			
		} catch (java.lang.ClassCastException ex) {} //si on est pas au dessus d'un panneau
	}

	public void mouseReleased(MouseEvent e) {

		//Si c'est le premier clic du casesJeux, on demarre le timer
		if (this.nombreCasesNonDecouvertes == this.nombreCasesHauteur * this.nombreCasesLargeur && e.getButton() == MouseEvent.BUTTON1) {
			temps.cancel();
			temps = new Temps(nombreTemps);
			temps.start();
		}

		try {
			
			int x = (int) ( (JPanel) e.getSource()).getLocation().getX() + e.getX() + 3; //retourne une exception si on est pas au dessus d'un panneau
			int y = (int) ( (JPanel) e.getSource()).getLocation().getY() + e.getY() + 22;
			
			int[] coord = caseClic(x, y); //coordonnees de la case enfoncee enregistrees dans coord
			
			bouton.setIcon(cool);
			
			if (coord[0] != -1 && coord[1] != -1) { //si on a clique sur une case
				
				y = coord[1];
				x = coord[0];
				
				if (e.getButton() == MouseEvent.BUTTON1) { //si on a clique gauche
					decouvre(y, x);
					repaint();
				}
				
				casesJeux[y][x].setCaseSelectionnee(false); //on deselectionne la case
				
				try {
					casesJeux[casesSelectionnees[0][0]][casesSelectionnees[0][1]].setCaseSelectionnee(false);
				} catch (Exception exc) {}
				
				try {
					casesJeux[casesSelectionnees[1][0]][casesSelectionnees[1][1]].setCaseSelectionnee(false);
				} catch (Exception exc) {}
				
				try {
					casesJeux[casesSelectionnees[2][0]][casesSelectionnees[2][1]].setCaseSelectionnee(false);
				} catch (Exception exc) {}
				
				try {
					casesJeux[casesSelectionnees[3][0]][casesSelectionnees[3][1]].setCaseSelectionnee(false);
				} catch (Exception exc) {}
				
				try {
					casesJeux[casesSelectionnees[4][0]][casesSelectionnees[4][1]].setCaseSelectionnee(false);
				} catch (Exception exc) {}
				
				try {
					casesJeux[casesSelectionnees[5][0]][casesSelectionnees[5][1]].setCaseSelectionnee(false);
				} catch (Exception exc) {}
				
				try {
					casesJeux[casesSelectionnees[6][0]][casesSelectionnees[6][1]].setCaseSelectionnee(false);
				} catch (Exception exc) {}
				
				try {
					casesJeux[casesSelectionnees[7][0]][casesSelectionnees[7][1]].setCaseSelectionnee(false);
				} catch (Exception exc) {}
			}
			
		} catch (java.lang.ClassCastException ex) {} //Si le clic n'est pas au dessus d'un panneau
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	//Methode qui retourne les coordonnees de la case cliquee
	void bouton_actionPerformed(ActionEvent e) {
		if (!pause.isSelected()) nouveaucasesJeux();
	}

	//methode pour decouvrir les cases
	public void decouvre(int y, int x) {
		
		//Si la case est normale ou avec un ?
		if ((casesJeux[y][x].getEtatCase() == 0 || casesJeux[y][x].getEtatCase() == 3) 
				&& !casesJeux[y][x].contientMine()) {
			
			this.nombreCasesNonDecouvertes--; //nombre de cases non decouvertes
			
			casesJeux[y][x].setEtatCase(1); //on indique que la case est decouverte
			
			// Si le nombre de mines autour est nul, on decouvre les cases autour
			if (casesJeux[y][x].getBombesAutour() == 0) { 
				decouvrirPartiel1(x - 1, y - 1);
				decouvrirPartiel1(x - 1, y);
				decouvrirPartiel1(x - 1, y + 1);
				decouvrirPartiel1(x, y - 1);
				decouvrirPartiel1(x, y + 1);
				decouvrirPartiel1(x + 1, y - 1);
				decouvrirPartiel1(x + 1, y);
				decouvrirPartiel1(x + 1, y + 1);
			}
		} else if (casesJeux[y][x].getEtatCase() == 1 && casesJeux[y][x].getBombesAutour() != 0) { // Si on est au dessus d'un chiffre
			
			int n = 0; //on compte le nombre de drapeaux places
			
			if (decouvrirPartiel2(x - 1, y - 1)) n++;
			if (decouvrirPartiel2(x - 1, y)) n++;
			if (decouvrirPartiel2(x - 1, y + 1)) n++;
			if (decouvrirPartiel2(x, y - 1)) n++;
			if (decouvrirPartiel2(x, y + 1)) n++;
			if (decouvrirPartiel2(x + 1, y - 1)) n++;
			if (decouvrirPartiel2(x + 1, y)) n++;
			if (decouvrirPartiel2(x + 1, y + 1)) n++;

			// si il y en a autant que le nombre de mines autour, 
			// on decouvre les 8 cases autour par un appel recursif de decouvre(int, int)
			if (n == casesJeux[y][x].getBombesAutour()) { 
				if (decouvrirPartiel3(x - 1, y - 1)) decouvre(y - 1, x - 1);
				if (decouvrirPartiel3(x - 1, y)) decouvre(y, x - 1);
				if (decouvrirPartiel3(x - 1, y + 1)) decouvre(y + 1, x - 1);
				if (decouvrirPartiel3(x, y - 1)) decouvre(y - 1, x);
				if (decouvrirPartiel3(x, y + 1)) decouvre(y + 1, x);
				if (decouvrirPartiel3(x + 1, y - 1)) decouvre(y - 1, x + 1);
				if (decouvrirPartiel3(x + 1, y)) decouvre(y, x + 1);
				if (decouvrirPartiel3(x + 1, y + 1)) decouvre(y + 1, x + 1);
			}
		
		} else if ((casesJeux[y][x].getEtatCase() == 0 || casesJeux[y][x].getEtatCase() == 3) 
				&& casesJeux[y][x].contientMine()) { //Si on clique sur une mine
			
			temps.cancel(); //fin du timer
			
			casesJeux[y][x].setEtatCase(4); //boum
			
			bouton.setIcon(boum);
			
			for (int i = 0; i < this.nombreCasesHauteur; i++) {
				
				for (int j = 0; j < this.nombreCasesLargeur; j++) {
					
					casesJeux[i][j].removeMouseListener(this); //on bloque les cases
					casesJeux[i][j].setCaseBloquee(true);
					
					if (!(y == i && x == j) 
							&& mines.charAt(i * this.nombreCasesLargeur + j) == '1' 
							&& casesJeux[i][j].getEtatCase() != 2) {
						//si il ya une mine, (recherche par rapport à la chaîne mines
						casesJeux[i][j].setEtatCase(5); //on l' affiche
					}

						
				}
			}
			
			//on affiche les erreurs
			for (int i = 0; i < this.nombreCasesHauteur; i++) {
				
				for (int j = 0; j < this.nombreCasesLargeur; j++) {
					
					if (casesJeux[i][j].getEtatCase() == 2 && !casesJeux[i][j].contientMine()) {
						casesJeux[i][j].setEtatCase(6);
					}
				}
			}
		}
		
		
		//Si on gagne, c'est à dire le nombre de cases restantes est egal au nombre de mines restantes
		if (this.nombreCasesNonDecouvertes == nombreTotolMines && !casesJeux[0][0].estCaseBloquee()) {
			
			temps.cancel(); //fin du timer
			
			nombreMines.setValeur(0);
			
			bouton.setIcon(win);
			
			for (int i = 0; i < this.nombreCasesHauteur; i++) {
				
				for (int j = 0; j < this.nombreCasesLargeur; j++) {
					
					casesJeux[i][j].removeMouseListener(this); //on bloque les cases
					casesJeux[i][j].setCaseBloquee(true);
					
					if (casesJeux[i][j].contientMine()) {
						casesJeux[i][j].setEtatCase(2); //on affiche les mines
					}
				}
			}
		}
	}

	
	//si la case existe, on la decouvre et si necessaire, on appelle le decouvrement des cases autour
	public void decouvrirPartiel1(int x, int y) {
		
		if (x >= 0 && y >= 0 && x < this.nombreCasesLargeur && y < this.nombreCasesHauteur) {
			
			if (casesJeux[y][x].getEtatCase() == 0 && casesJeux[y][x].getBombesAutour() != 0) {
				
				casesJeux[y][x].setEtatCase(1);
				this.nombreCasesNonDecouvertes--;
			}
			
			if (casesJeux[y][x].getEtatCase() == 0 && casesJeux[y][x].getBombesAutour() == 0) {
				decouvre(y, x); //Si le nombre de mines autour est nul, on decouvre les cases autour
			}
		}
	}

	//verifie si la case existe et si elle porte un drapeau
	public boolean decouvrirPartiel2(int x, int y) {
		
		if (x >= 0 && y >= 0 && x < this.nombreCasesLargeur && y < this.nombreCasesHauteur) {
			if (casesJeux[y][x].getEtatCase() == 2) return true;
		}
		
		return false;
	}

	//verifie si la case existe et si elle n'est pas decouverte ou si elle porte un '?'
	public boolean decouvrirPartiel3(int x, int y) {
		
		if (x >= 0 && y >= 0 && x < this.nombreCasesLargeur && y < this.nombreCasesHauteur) {
			if (casesJeux[y][x].getEtatCase() == 0 || casesJeux[y][x].getEtatCase() == 3) return true;
		}
		
		return false;
	}

	public void windowOpened(WindowEvent e) {}

	public void windowClosing(WindowEvent e) {
		temps.stop(); //stop le timer avant de quitter
		System.exit(0);
	}

	public void windowClosed(WindowEvent e) {}

	public void windowIconified(WindowEvent e) {
		
		try {
			//pause du timer si il existe!!
			temps.suspend();
		} catch (Exception esc) {}
	}

	public void windowDeiconified(WindowEvent e) {
		
		try {
			//reprise du timer (si il existe!!)
			temps.resume();
		} catch (Exception esc) {}
	}

	public void windowActivated(WindowEvent e) {}

	public void windowDeactivated(WindowEvent e) {}

	//evenements lies au menu
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == menuNouveau) {
			nouveaucasesJeux();
			
		} else if (e.getSource() == menuDebutant && niveau != 1) {
			
			this.dispose(); // on detruit la fenetre
			System.gc();
			if (niveau == 1) menuDebutant.setSelected(true);
			new Demineur(8, 8, 10, 1); //et on en refait une
			
		} else if (e.getSource() == menuDebutant && !menuDebutant.isSelected()) {
			
			menuDebutant.setSelected(true);
			
		} else if (e.getSource() == menuIntermediaire && niveau != 2) {
			
			this.dispose(); // on detruit la fenetre
			System.gc();
			if (niveau == 2) menuIntermediaire.setSelected(true);
			new Demineur(16, 16, 40, 2);
			
		} else if (e.getSource() == menuIntermediaire && !menuIntermediaire.isSelected()) {
			
			menuIntermediaire.setSelected(true);
			
		} else if (e.getSource() == menuExpert && niveau != 3) {
			
			this.dispose(); // on detruit la fenetre
			System.gc();
			if (niveau == 3) menuExpert.setSelected(true);
			new Demineur(16, 30, 99, 3);
			
		} else if (e.getSource() == menuExpert && niveau != 4) {
			
			menuExpert.setSelected(true);
			
		} else if (e.getSource() == menuPersonaliser) {
			
			//un peu particulier : tout est gere par la fenetre de personalisation
			if (niveau == 4) {
				
				menuPersonaliser.setSelected(true);
				
			} else {
				
				menuPersonaliser.setSelected(false);
				
			}
			
			Personaliser personaliser = new Personaliser(this, "Paramètres", true, this.nombreCasesHauteur,
					this.nombreCasesLargeur, nombreTotolMines);
			
			personaliser.setLocation( (int)this.getLocation().getX() + 20, (int)this.getLocation().getY() + 20);
			
			personaliser.setVisible(true);
			
		} else if (e.getSource() == pause) {
			
			if (pause.isSelected()) {
				
				corpscasesJeux.setVisible(false);
				temps.suspend();
				
			} else {
				
				corpscasesJeux.setVisible(true);
				temps.resume();
				
			}
			
		} else if (e.getSource() == apropos) {
			
			Apropos app = new Apropos(this, "Demineur", true);
			
			app.setLocation( (int)this.getLocation().getX() + 20, (int)this.getLocation().getY() + 20);
			
			app.setVisible(true);
		}
	}
}
