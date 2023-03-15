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
	private JPanel enteteJeux = new JPanel();
	private JPanel corpsJeux = new JPanel();

	/*****************************************************************
	 * Le GridBagLayout est un gestionnaire de mise en page 
	 * qui permet de positionner les composants graphiques de manière flexible 
	 * et précise dans un conteneur JPanel.
	 ************************************************************************/
	private GridBagLayout layoutcorpsJeux = new GridBagLayout();

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
	private JMenu help = new JMenu("Aide");

	/************************************************************************************************************
	 * La classe JMenuItem est utilisée pour créer des éléments de menu dans une interface utilisateur graphique. 
	 * ils sont ajoutés à un menu déroulant ou à une barre de menus pour offrir des commandes 
	 * ou des actions spécifiques à l'utilisateur.
	 *************************************************************************************************************/
	private JMenuItem menuNouveau = new JMenuItem("Nouveau");
	private JMenuItem apropos = new JMenuItem("A propos");

	/***************************************************************************************
	 * La classe JCheckBoxMenuItem est utilisée pour créer des éléments 
	 * de menu de case à cocher dans une interface utilisateur graphique.
	 * ils sont ajoutés à un menu déroulant ou à une barre de menus pour offrir des options 
	 * ou des paramètres personnalisables à l'utilisateur.
	 ***************************************************************************************/
	JCheckBoxMenuItem menuFacile = new JCheckBoxMenuItem("Facile");
	JCheckBoxMenuItem menuMoyenne = new JCheckBoxMenuItem("moyenne");
	JCheckBoxMenuItem menuDifficile = new JCheckBoxMenuItem("Difficile");
	JCheckBoxMenuItem menuPersonaliser = new JCheckBoxMenuItem("Personaliser");

	/********************************************************************************
	 * La classe BoxLayout est utilisée pour organiser les composants 
	 * d'une interface utilisateur graphique dans une boîte horizontale ou verticale.
	 * La disposition de niveau LINE_AXIS signifie que les composants sont placés 
	 * dans une ligne horizontale, de gauche à droite, dans le cas présent.
	 *********************************************************************************/
	private BoxLayout layoutenteteJeux = new BoxLayout(enteteJeux, BoxLayout.LINE_AXIS);

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
	 * tableau de cases du jeux
	 *******************************/
	Cases[][] jeux;
	
	/****************************************
	 * les attributs
	 ****************************************/
	private String mines; //chaînes de caractère qui contient la repartition des mines
	private int[][] casesSelectionnees = new int[8][2]; //reperages des cases selectionnees pour les deselectionnes lors du relachement de la sourie
	private Temps temps = new Temps(nombreTemps); //timer sur l'affichage Segment nombreTemps
	private int niveau;

	
	/***************************************************
	 * demarrer un nouveau jeux avec en parametre :
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
		
		jeux = new Cases[this.nombreCasesHauteur][this.nombreCasesLargeur];

		
		/******************************
		 * creation des cases
		 ******************************/
		for(int i = 0; i < this.nombreCasesHauteur; i++) {
			for (int j = 0; j < this.nombreCasesLargeur; j++) {
				jeux[i][j] = new Cases();
			}
		}

		
		/*****************************************
		 * selection du mode choisi dans le JMenu
		 *****************************************/
		if (niveau == 1) menuFacile.setSelected(true);
		if (niveau == 2) menuMoyenne.setSelected(true);
		if (niveau == 3) menuDifficile.setSelected(true);
		if (niveau == 4) menuPersonaliser.setSelected(true);

		
		/*************************************
		 * initialisation du jeux
		 *************************************/
		nouveauJeux();


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
	 * demarrer un nouveau jeux 
	 ****************************************************/
	public void nouveauJeux() {
		
		temps.cancel(); // timer à 0
		bouton.setIcon(cool); // icon initial du bouton
		nombreDrapeauPoses = 0; // nombre de drapeaux poses
		
		this.nombreCasesNonDecouvertes = this.nombreCasesHauteur * this.nombreCasesLargeur;
		
		nombreMines.setValeur(nombreTotolMines);
		nombreTemps.setValeur(0);
		corpsJeux.setVisible(true); //affichage du corps du jeux
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

		
		/*
		 * parametres de chaque case
		 */
		for (int i = 0; i < this.nombreCasesHauteur; i++) {
			
			for (int j = 0; j < this.nombreCasesLargeur; j++) {
				
				jeux[i][j].reset();
				jeux[i][j].removeMouseListener(this); //suppression du listener sur les cases
				jeux[i][j].addMouseListener(this); //ajout du listener sur les cases
				
				if (mines.charAt(i * this.nombreCasesLargeur + j) == '1') 
					jeux[i][j].setMine(true);
			}
		}
		
		/************************************************************************
		 * pour demander à un composant graphique de se redessiner lui-même.
		 * il doit être actualisé à l'écran.
		 ***********************************************************************/
		repaint();

		/*
		 * le nombre de mines autour de chaque case
		 */
		for(int i = 0; i < this.nombreCasesHauteur; i++) {
			
			for(int j = 0; j < this.nombreCasesLargeur; j++) {
				
				if (!jeux[i][j].isMine()) {
					
					int n = 0;
					
					/*
					 * verifier, eventuellement si les cases existent
					 */
					try {
						if (jeux[i - 1][j - 1].isMine()) n++;
					}
					catch (java.lang.ArrayIndexOutOfBoundsException e) {}
					
					try {
						if (jeux[i - 1][j].isMine()) n++;
					}
					catch (java.lang.ArrayIndexOutOfBoundsException e) {}
					
					try {
						if (jeux[i - 1][j + 1].isMine()) n++;
					}
					catch (java.lang.ArrayIndexOutOfBoundsException e) {}
					
					try {
						if (jeux[i][j - 1].isMine()) n++;
					}
					catch (java.lang.ArrayIndexOutOfBoundsException e) {}
					
					try {
						if (jeux[i][j + 1].isMine()) n++;
					}
					catch (java.lang.ArrayIndexOutOfBoundsException e) {}
					
					try {
						if (jeux[i + 1][j - 1].isMine()) n++;
					}
					catch (java.lang.ArrayIndexOutOfBoundsException e) {}
					
					try {
						if (jeux[i + 1][j].isMine()) n++;
					}
					catch (java.lang.ArrayIndexOutOfBoundsException e) {}
					
					try {
						if (jeux[i + 1][j + 1].isMine()) n++;
					}
					catch (java.lang.ArrayIndexOutOfBoundsException e) {}
					
					jeux[i][j].setChiffre(n); //on indique à la case le nombre de mines
				}
			}
		}
	}

	private void jbInit() throws Exception {
		
		bordure = BorderFactory.createEtchedBorder(Color.white, new Color(156, 156, 156));
		
		box2 = Box.createGlue(); //utilises dans le jPanel1 pour la disposition
		box3 = Box.createGlue();
		box1 = Box.createHorizontalStrut(8);
		box1.setSize(5, 50);
		box4 = Box.createHorizontalStrut(8);
		box4.setSize(5, 50);

		this.addWindowListener(this);

		int tailleX = this.nombreCasesLargeur * 16 + 20; //largeur de la fenetre
		int tailleY = this.nombreCasesHauteur * 16 + 20;
		if (tailleX < 160) tailleX = 150; //largeur minimum

		this.setSize(tailleX + 6, tailleY + 50 + 23 + 25); //6=largeur du cadre de la fenetre, 25=hauteur de la barre windows
		this.setTitle("Demineur");
		this.setResizable(false);

		//MENU
		partie.setMnemonic('P');
		menuNouveau.addActionListener(this);
		menuNouveau.setMnemonic('N');
		menuFacile.addActionListener(this);
		menuFacile.setMnemonic('D');
		menuMoyenne.addActionListener(this);
		menuMoyenne.setMnemonic('I');
		menuDifficile.addActionListener(this);
		menuDifficile.setMnemonic('E');
		menuPersonaliser.addActionListener(this);
		menuPersonaliser.setMnemonic('P');
		partie.add(menuNouveau);
		partie.add(new JSeparator());
		partie.add(menuFacile);
		partie.add(menuMoyenne);
		partie.add(menuDifficile);
		partie.add(menuPersonaliser);
		menu.setBorderPainted(false);
		menu.add(partie);
		pause.setMnemonic('a');
		pause.setOpaque(false);
		pause.setFocusPainted(false);
		pause.addActionListener(this);
		menu.add(pause);
		help.setMnemonic('?');
		apropos.addActionListener(this);
		apropos.setMnemonic('A');
		help.add(apropos);
		menu.add(help);
		this.setJMenuBar(menu);

		nombreMines.setMaximumSize(new Dimension(49, 27));
		nombreTemps.setMaximumSize(new Dimension(49, 27));
		bouton.setMaximumSize(new Dimension(25, 25));
		bouton.setMinimumSize(new Dimension(25, 25));
		enteteJeux.setBorder(bordure);
		enteteJeux.setPreferredSize(new Dimension(450, 50));
		enteteJeux.setLayout(layoutenteteJeux);
		corpsJeux.setBorder(bordure);
		corpsJeux.setPreferredSize(new Dimension(tailleX, tailleY));
		corpsJeux.setLayout(layoutcorpsJeux);
		nombreMines.setValeur(nombreTotolMines);
		nombreTemps.setValeur(0);
		bouton.setPreferredSize(new Dimension(25, 25));
		bouton.setFocusPainted(false);
		bouton.setIcon(cool);
		bouton.setMargin(new Insets(0, 0, 0, 0));
		bouton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bouton_actionPerformed(e);
			}
		});
		enteteJeux.add(box1, null);
		enteteJeux.add(nombreMines, null);
		enteteJeux.add(box2, null);
		enteteJeux.add(bouton, null);
		enteteJeux.add(box3, null);
		enteteJeux.add(nombreTemps, null);
		enteteJeux.add(box4, null);
		this.getContentPane().add(enteteJeux, BorderLayout.NORTH);
		this.getContentPane().add(corpsJeux, BorderLayout.CENTER);

		//gr contient les graphismes de toutes les cases
		//on le cre maintenant pour utiliser son GraphicsConfiguration()
		Graphisme gr = new Graphisme(this.getGraphicsConfiguration());

		//placement des cases dans la fenêtre
		for (int i = 0; i < this.nombreCasesHauteur; i++) {
			for (int j = 0; j < this.nombreCasesLargeur; j++) {
				jeux[i][j].setGraphisme(gr); //on indique les graphismes à la cases
				corpsJeux.add(jeux[i][j], new GridBagConstraints(j, i, 1, 1, 0.0, 0.0
						, GridBagConstraints.CENTER, GridBagConstraints.NONE,
						new Insets(0, 0, 0, 0), 0, 0));
			}
		}
	}

	//Retourne le reperage de la case sous le clic de la souris si elle existe
	//(-1,-1) sinon
	public int[] caseClic(int x, int y) {
		int OFFSETX = (int) jeux[0][0].getX() + 3; //3=largeur du cadre de la fenetre
		int OFFSETY = (int) jeux[0][0].getY() + 22; //22=hauteur du cadre de la fenetre
		int posx = -1, posy = -1;
		if (x - OFFSETX >= 0) posx = (x - OFFSETX) / 16;
		if (posx >= this.nombreCasesLargeur) posx = -1;
		if (y - OFFSETY >= 0 && posx != -1) posy = (y - OFFSETY) / 16;
		if (posy >= this.nombreCasesHauteur) posy = -1;
		if (posy == -1) posx = -1;
		int[] retour = {
				posx, posy};
		return retour;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		try {
			int x = (int) ( (JPanel) e.getSource()).getLocation().getX() + e.getX() +
					3; //retourne une exception si on est pas au dessus d'un panneau
			int y = (int) ( (JPanel) e.getSource()).getLocation().getY() + e.getY() +
					22;
			int[] coord = caseClic(x, y); //coordonnees de la case enfoncee enregistrees dans coord
			bouton.setIcon(oups); //bouton

			//si clic droit au dessus d'une case
			if (e.getButton() == MouseEvent.BUTTON3 && coord[1] != -1 && coord[0] != -1) {
				int temp = jeux[coord[1]][coord[0]].getEtat();
				switch (temp) {
				case 0: //affichage d'un drapeau
					jeux[coord[1]][coord[0]].setEtat(2);
					nombreDrapeauPoses++;
					nombreMines.setValeur(nombreTotolMines - nombreDrapeauPoses);
					break;
				case 2: //affichage d'un ?
					jeux[coord[1]][coord[0]].setEtat(3);
					nombreDrapeauPoses--;
					nombreMines.setValeur(nombreTotolMines - nombreDrapeauPoses);
					break;
				case 3: //RAZ
					jeux[coord[1]][coord[0]].setEtat(0);
					break;
				}
				jeux[coord[1]][coord[0]].repaint();
			}

			//si clic gauche, on selectionne les cases autour

			y = coord[1];
			x = coord[0];
			if (e.getButton() == MouseEvent.BUTTON1 && x != -1 && y != -1 &&
					jeux[y][x].getEtat() == 1 && jeux[y][x].getChiffre() != 0) {
				//on enregistre les coordonnees des cases selectionnees
				for (int i = 0; i < 7; i++) {
					for (int j = 0; j < 2; j++) {
						casesSelectionnees[i][j] = -1; //effacement de la memoire
					}
				}
				//essai sur les huit cases autour
				try {
					if (jeux[y - 1][x - 1].getEtat() == 0) {
						jeux[y - 1][x - 1].setSelected(true);
						casesSelectionnees[0][0] = y - 1;
						casesSelectionnees[0][1] = x - 1;
					}
				}
				catch (Exception exc) {}
				try {
					if (jeux[y - 1][x].getEtat() == 0) {
						jeux[y - 1][x].setSelected(true);
						casesSelectionnees[1][0] = y - 1;
						casesSelectionnees[1][1] = x;
					}
				}
				catch (Exception exc) {}
				try {
					if (jeux[y - 1][x + 1].getEtat() == 0) {
						jeux[y - 1][x + 1].setSelected(true);
						casesSelectionnees[2][0] = y - 1;
						casesSelectionnees[2][1] = x + 1;
					}
				}
				catch (Exception exc) {}
				try {
					if (jeux[y][x - 1].getEtat() == 0) {
						jeux[y][x - 1].setSelected(true);
						casesSelectionnees[3][0] = y;
						casesSelectionnees[3][1] = x - 1;
					}
				}
				catch (Exception exc) {}
				try {
					if (jeux[y][x + 1].getEtat() == 0) {
						jeux[y][x + 1].setSelected(true);
						casesSelectionnees[4][0] = y;
						casesSelectionnees[4][1] = x + 1;
					}
				}
				catch (Exception exc) {}
				try {
					if (jeux[y + 1][x - 1].getEtat() == 0) {
						jeux[y + 1][x - 1].setSelected(true);
						casesSelectionnees[5][0] = y + 1;
						casesSelectionnees[5][1] = x - 1;
					}
				}
				catch (Exception exc) {}
				try {
					if (jeux[y + 1][x].getEtat() == 0) {
						jeux[y + 1][x].setSelected(true);
						casesSelectionnees[6][0] = y + 1;
						casesSelectionnees[6][1] = x;
					}
				}
				catch (Exception exc) {}
				try {
					if (jeux[y + 1][x + 1].getEtat() == 0) {
						jeux[y + 1][x + 1].setSelected(true);
						casesSelectionnees[7][0] = y + 1;
						casesSelectionnees[7][1] = x + 1;
					}
				}
				catch (Exception exc) {}
			}
		}
		catch (java.lang.ClassCastException ex) {} //si on est pas au dessus d'un panneau
	}

	public void mouseReleased(MouseEvent e) {

		//Si c'est le premier clic du jeux, on demarre le timer
		if (this.nombreCasesNonDecouvertes == this.nombreCasesHauteur * this.nombreCasesLargeur && e.getButton() == MouseEvent.BUTTON1) {
			temps.cancel();
			temps = new Temps(nombreTemps);
			temps.start();
		}

		try {
			int x = (int) ( (JPanel) e.getSource()).getLocation().getX() + e.getX() +
					3; //retourne une exception si on est pas au dessus d'un panneau
			int y = (int) ( (JPanel) e.getSource()).getLocation().getY() + e.getY() +
					22;
			int[] coord = caseClic(x, y); //coordonnees de la case enfoncee enregistrees dans coord
			bouton.setIcon(cool); //bouton
			if (coord[0] != -1 && coord[1] != -1) { //si on a clique sur une case
				y = coord[1];
				x = coord[0];
				if (e.getButton() == MouseEvent.BUTTON1) { //si on a clique gauche
					decouvre(y, x);
					repaint();
				}
				jeux[y][x].setSelected(false); //on deselectionne la case
				try {
					jeux[casesSelectionnees[0][0]][casesSelectionnees[0][1]].setSelected(false);
				}
				catch (Exception exc) {}
				try {
					jeux[casesSelectionnees[1][0]][casesSelectionnees[1][1]].setSelected(false);
				}
				catch (Exception exc) {}
				try {
					jeux[casesSelectionnees[2][0]][casesSelectionnees[2][1]].setSelected(false);
				}
				catch (Exception exc) {}
				try {
					jeux[casesSelectionnees[3][0]][casesSelectionnees[3][1]].setSelected(false);
				}
				catch (Exception exc) {}
				try {
					jeux[casesSelectionnees[4][0]][casesSelectionnees[4][1]].setSelected(false);
				}
				catch (Exception exc) {}
				try {
					jeux[casesSelectionnees[5][0]][casesSelectionnees[5][1]].setSelected(false);
				}
				catch (Exception exc) {}
				try {
					jeux[casesSelectionnees[6][0]][casesSelectionnees[6][1]].setSelected(false);
				}
				catch (Exception exc) {}
				try {
					jeux[casesSelectionnees[7][0]][casesSelectionnees[7][1]].setSelected(false);
				}
				catch (Exception exc) {}
			}
		}
		catch (java.lang.ClassCastException ex) {} //Si le clic n'est pas au dessus d'un panneau
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	//Methode qui retourne les coordonnees de la case cliquee
	void bouton_actionPerformed(ActionEvent e) {
		if (!pause.isSelected()) nouveauJeux();
	}

	//methode pour decouvrir les cases
	public void decouvre(int y, int x) {
		//Si la case est normale ou avec un ?
		if ( (jeux[y][x].getEtat() == 0 || jeux[y][x].getEtat() == 3) &&
				!jeux[y][x].isMine()) {
			this.nombreCasesNonDecouvertes--; //nombre de cases non decouvertes
			jeux[y][x].setEtat(1); //on indique que la case est decouverte
			if (jeux[y][x].getChiffre() == 0) { // Si le nombre de mines autour est nul, on decouvre les cases autour
				decouvrirPartiel1(x - 1, y - 1);
				decouvrirPartiel1(x - 1, y);
				decouvrirPartiel1(x - 1, y + 1);
				decouvrirPartiel1(x, y - 1);
				decouvrirPartiel1(x, y + 1);
				decouvrirPartiel1(x + 1, y - 1);
				decouvrirPartiel1(x + 1, y);
				decouvrirPartiel1(x + 1, y + 1);
			}
		}

		//Si on est au dessus d'un chiffre
		else if (jeux[y][x].getEtat() == 1 && jeux[y][x].getChiffre() != 0) {
			int n = 0; //on compte le nombre de drapeaux places
			if (decouvrirPartiel2(x - 1, y - 1)) n++;
			if (decouvrirPartiel2(x - 1, y)) n++;
			if (decouvrirPartiel2(x - 1, y + 1)) n++;
			if (decouvrirPartiel2(x, y - 1)) n++;
			if (decouvrirPartiel2(x, y + 1)) n++;
			if (decouvrirPartiel2(x + 1, y - 1)) n++;
			if (decouvrirPartiel2(x + 1, y)) n++;
			if (decouvrirPartiel2(x + 1, y + 1)) n++;

			if (n == jeux[y][x].getChiffre()) { //si il y en a autant que le nombre de mines autour, on decouvre les 8 cases autour par un appel recursif de decouvre(int, int)
				if (decouvrirPartiel3(x - 1, y - 1)) decouvre(y - 1, x - 1);
				if (decouvrirPartiel3(x - 1, y)) decouvre(y, x - 1);
				if (decouvrirPartiel3(x - 1, y + 1)) decouvre(y + 1, x - 1);
				if (decouvrirPartiel3(x, y - 1)) decouvre(y - 1, x);
				if (decouvrirPartiel3(x, y + 1)) decouvre(y + 1, x);
				if (decouvrirPartiel3(x + 1, y - 1)) decouvre(y - 1, x + 1);
				if (decouvrirPartiel3(x + 1, y)) decouvre(y, x + 1);
				if (decouvrirPartiel3(x + 1, y + 1)) decouvre(y + 1, x + 1);
			}
		}

		//Si on clique sur une mine
		else if ( (jeux[y][x].getEtat() == 0 || jeux[y][x].getEtat() == 3) &&
				jeux[y][x].isMine()) {
			temps.cancel(); //fin du timer
			jeux[y][x].setEtat(4); //boum
			bouton.setIcon(boum);
			for (int i = 0; i < this.nombreCasesHauteur; i++) {
				for (int j = 0; j < this.nombreCasesLargeur; j++) {
					jeux[i][j].removeMouseListener(this); //on bloque les cases
					jeux[i][j].setBlocked(true);
					if (! (y == i && x == j) && mines.charAt(i * this.nombreCasesLargeur + j) == '1' &&
							jeux[i][j].getEtat() != 2)

						//si il ya une mine, (recherche par rapport à la chaîne mines
						jeux[i][j].setEtat(5); //on l' affiche
				}
			}
			//on affiche les erreurs
			for (int i = 0; i < this.nombreCasesHauteur; i++) {
				for (int j = 0; j < this.nombreCasesLargeur; j++) {
					if (jeux[i][j].getEtat() == 2 && !jeux[i][j].isMine()) jeux[i][j].
					setEtat(6);
				}
			}
		}
		//Si on gagne, c'est à dire le nombre de cases restantes est egal au nombre de mines restantes
		if (this.nombreCasesNonDecouvertes == nombreTotolMines && !jeux[0][0].isBlocked()) {
			temps.cancel(); //fin du timer
			nombreMines.setValeur(0);
			bouton.setIcon(win);
			for (int i = 0; i < this.nombreCasesHauteur; i++) {
				for (int j = 0; j < this.nombreCasesLargeur; j++) {
					jeux[i][j].removeMouseListener(this); //on bloque les cases
					jeux[i][j].setBlocked(true);
					if (jeux[i][j].isMine()) jeux[i][j].setEtat(2); //on affiche les mines
				}
			}
		}
	}

	//si la case existe, on la decouvre et si necessaire, on appelle le decouvrement des cases autour
	public void decouvrirPartiel1(int x, int y) {
		if (x >= 0 && y >= 0 && x < this.nombreCasesLargeur && y < this.nombreCasesHauteur) {
			if (jeux[y][x].getEtat() == 0 && jeux[y][x].getChiffre() != 0) {
				jeux[y][x].setEtat(1);
				this.nombreCasesNonDecouvertes--;
			}
			if (jeux[y][x].getEtat() == 0 && jeux[y][x].getChiffre() == 0)
				decouvre(y, x); //Si le nombre de mines autour est nul, on decouvre les cases autour
		}
	}

	//verifie si la case existe et si elle porte un drapeau
	public boolean decouvrirPartiel2(int x, int y) {
		if (x >= 0 && y >= 0 && x < this.nombreCasesLargeur && y < this.nombreCasesHauteur) {
			if (jeux[y][x].getEtat() == 2)
				return true;
		}
		return false;
	}

	//verifie si la case existe et si elle n'est pas decouverte ou si elle porte un '?'
	public boolean decouvrirPartiel3(int x, int y) {
		if (x >= 0 && y >= 0 && x < this.nombreCasesLargeur && y < this.nombreCasesHauteur) {
			if (jeux[y][x].getEtat() == 0 || jeux[y][x].getEtat() == 3)
				return true;
		}
		return false;
	}

	public void windowOpened(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		temps.stop(); //stop le timer avant de quitter
		System.exit(0);
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
		try {
			temps.suspend();
		} //pause du timer si il existe!!
		catch (Exception esc) {}
	}

	public void windowDeiconified(WindowEvent e) {
		try {
			temps.resume();
		} //reprise du timer (si il existe!!)
		catch (Exception esc) {}
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}

	//evenements lies au menu
	public void actionPerformed(ActionEvent e) {



		if (e.getSource() == menuNouveau) nouveauJeux();
		else if (e.getSource() == menuFacile && niveau != 1) {
			this.dispose(); // on detruit la fenetre
			System.gc();
			if (niveau == 1) menuFacile.setSelected(true);
			new Demineur(8, 8, 10, 1); //et on en refait une
		}
		else if (e.getSource() == menuFacile && !menuFacile.isSelected())
			menuFacile.setSelected(true);
		else if (e.getSource() == menuMoyenne && niveau != 2) {
			this.dispose(); // on detruit la fenetre
			System.gc();
			if (niveau == 2) menuMoyenne.setSelected(true);
			new Demineur(16, 16, 40, 2);
		}
		else if (e.getSource() == menuMoyenne &&
				!menuMoyenne.isSelected()) menuMoyenne.setSelected(true);
		else if (e.getSource() == menuDifficile && niveau != 3) {
			this.dispose(); // on detruit la fenetre
			System.gc();
			if (niveau == 3) menuDifficile.setSelected(true);
			new Demineur(16, 30, 99, 3);
		}
		else if (e.getSource() == menuDifficile && niveau != 4) menuDifficile.setSelected(true);
		else if (e.getSource() == menuPersonaliser) {
			//un peu particulier : tout est gere par la fenetre de personalisation
			if (niveau == 4) menuPersonaliser.setSelected(true);
			else menuPersonaliser.setSelected(false);
			Personaliser perso = new Personaliser(this, "Paramètres", true, this.nombreCasesHauteur,
					this.nombreCasesLargeur, nombreTotolMines);
			perso.setLocation( (int)this.getLocation().getX() + 20,
					(int)this.getLocation().getY() + 20);
			perso.setVisible(true);
		}
		else if (e.getSource() == pause) {
			if (pause.isSelected()) {
				corpsJeux.setVisible(false);
				temps.suspend();
			}
			else {
				corpsJeux.setVisible(true);
				temps.resume();
			}
		}
		else if (e.getSource() == apropos) {
			Apropos app = new Apropos(this, "Demineur", true);
			app.setLocation( (int)this.getLocation().getX() + 20,
					(int)this.getLocation().getY() + 20);
			app.setVisible(true);
		}
	}
}
