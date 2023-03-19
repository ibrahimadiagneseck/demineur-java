package demineur.apropos;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.*;


public class Apropos extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JPanel panel = new JPanel();
	
	private JLabel titre = new JLabel();
	
	private JLabel auteurs = new JLabel();
	
	private JLabel github1 = new JLabel();
	private JLabel github2 = new JLabel();
	
	private JLabel espace1 = new JLabel();
	private JLabel espace2 = new JLabel();
	
	private JButton fermer = new JButton();
	
	private GridBagLayout gridBagLayout = new GridBagLayout();
	
	private Border border;

	public Apropos(Frame frame, String title, boolean modal) {
		
		super(frame, title, modal);
		
		/*
		 *  la méthode "jbInit()" initialise un objet 
		 *  ou une interface graphique, puis appelle la méthode "pack()" 
		 *  pour dimensionner l'interface graphique.
		 */
		try {
			jbInit();
			pack();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	public Apropos() {
		this(null, "", false);
	}
	
	private void jbInit() throws Exception {
		
		// Crée un bordure composée de deux bordures: une bordure en relief et une bordure vide
		this.border = BorderFactory.createCompoundBorder(
			BorderFactory.createBevelBorder(
				BevelBorder.RAISED,
				SystemColor.white,
				SystemColor.white,
				new Color(109, 109, 109),
				new Color(156, 156, 156)),
			BorderFactory.createEmptyBorder(0,10,0,10));
			
		// Configure le layout du panneau
		this.panel.setLayout(gridBagLayout);
			
		// Configure le texte du titre, la police et la couleur
		this.titre.setFont(new java.awt.Font("Serif", 1, 20));
		this.titre.setForeground(SystemColor.white);
		// Ajoute la bordure au titre
		this.titre.setBorder(this.border);
		this.titre.setPreferredSize(new Dimension(180, 50));
		this.titre.setText("Evite les mines");
			
		// Configure le texte des auteurs, la police, la couleur et la taille
		this.auteurs.setFont(new java.awt.Font("Dialog", 0, 18));
		this.auteurs.setForeground(Color.white);
		this.auteurs.setPreferredSize(new Dimension(600, 22));
		this.auteurs.setText("Réalisé par Ibrahima Diagne Seck et Oumou Hawa Diallo");
			
		// Configure le texte du lien GitHub1, la police, la couleur et la taille
		this.github1.setFont(new java.awt.Font("Dialog", 0, 16));
		this.github1.setForeground(Color.white);
		this.github1.setPreferredSize(new Dimension(450, 16));
		this.github1.setText("https://github.com/ibrahimadiagneseck");
			
		// Configure le texte du lien GitHub2, la police, la couleur et la taille
		this.github2.setFont(new java.awt.Font("Dialog", 0, 16));
		this.github2.setForeground(Color.white);
		this.github2.setPreferredSize(new Dimension(450, 16));
		this.github2.setText("https://github.com/oumouhawadiallo");
			
		// Configure la couleur de fond du panneau
		this.panel.setBackground(SystemColor.activeCaption);
			
		// Configure le bouton Fermer, la police, le texte et l'action à effectuer lorsque le bouton est cliqué
		this.fermer.setFont(new java.awt.Font("Dialog", 0, 10));
		this.fermer.setFocusPainted(false);
		this.fermer.setMnemonic('F');
		this.fermer.setText("Fermer");
		this.fermer.addActionListener(this);
			
		// Configure des espaces vides pour ajouter de l'espace entre les composants
		this.espace1.setPreferredSize(new Dimension(600, 100));
		this.espace2.setPreferredSize(new Dimension(600, 100));
		
		// Ajout du panel au content pane de la fenêtre
		getContentPane().add(panel);

		// Ajout du composant titre au panel avec des contraintes de placement spécifiées
		this.panel.add(titre, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		
		this.panel.add(this.auteurs, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		this.panel.add(this.github1, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		this.panel.add(this.github2, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		this.panel.add(this.fermer, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
		
		this.panel.add(this.espace1, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		this.panel.add(this.espace2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
	}
	
	/*
	 * actionPerformed qui est déclenchée lorsqu'un événement est déclenché, 
	 * comme un clic sur un bouton. Cette méthode cache la fenêtre 
	 * actuelle en appelant setVisible(false). Cela signifie que la 
	 * fenêtre sera cachée mais pas détruite et pourra être affichée 
	 * à nouveau plus tard si nécessaire.
	 */
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}