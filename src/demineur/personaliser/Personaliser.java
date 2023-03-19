package demineur.personaliser;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import demineur.demineur.Demineur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Personaliser extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private GridBagLayout gridBagLayout = new GridBagLayout(); 
	
	private JPanel panel = new JPanel();
	private JLabel hauteurjLabel = new JLabel();
	private JLabel largeurjLabel = new JLabel();
	private JLabel minesjLabel = new JLabel();
	
	private JTextField hauteurJTextField = new JTextField();
	private JTextField largeurJTextField = new JTextField();
	private JTextField minesJTextField = new JTextField();
	
	private Border border;
	
	private JButton boutonOk = new JButton();
	private JButton boutonAnnuler = new JButton();

	private int hauteur, largeur, mines;
	
	private Demineur demineur;

	public Personaliser(Frame frame, String title, boolean modal, int hauteur, int largeur, int mines) {
		
		super(frame, title, modal);
		
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.mines = mines;
		this.demineur = (Demineur) frame;
		
		try {
			jbInit();
			pack(); // ajuste la dimension de la fenetre automatiquement
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public Personaliser() {
		this(null, "", true, 16, 30, 99);
	}
	
	private void jbInit() throws Exception {
		
		this.border = BorderFactory.createBevelBorder(
									BevelBorder.LOWERED,
									Color.white,
									Color.white,
									new Color(124, 124, 124),
									new Color(178, 178, 178));
		
		this.panel.setLayout(this.gridBagLayout);
		
		this.hauteurjLabel.setMaximumSize(new Dimension(70, 30));
		this.hauteurjLabel.setMinimumSize(new Dimension(70, 30));
		this.hauteurjLabel.setPreferredSize(new Dimension(70, 30));
		this.hauteurjLabel.setText("Hauteur :");
		
		this.largeurjLabel.setMaximumSize(new Dimension(70, 30));
		this.largeurjLabel.setMinimumSize(new Dimension(70, 30));
		this.largeurjLabel.setPreferredSize(new Dimension(70, 30));
		this.largeurjLabel.setToolTipText("");
		this.largeurjLabel.setText("Largeur :");
		
		this.minesjLabel.setMaximumSize(new Dimension(70, 30));
		this.minesjLabel.setMinimumSize(new Dimension(70, 30));
		this.minesjLabel.setPreferredSize(new Dimension(70, 30));
		this.minesjLabel.setText("Mines :");
		
		this.hauteurJTextField.setBorder(this.border);
		this.hauteurJTextField.setMinimumSize(new Dimension(40, 21));
		this.hauteurJTextField.setPreferredSize(new Dimension(40, 21));
		this.hauteurJTextField.setText("" + this.hauteur);
		
		this.largeurJTextField.setBorder(this.border);
		this.largeurJTextField.setMinimumSize(new Dimension(40, 21));
		this.largeurJTextField.setPreferredSize(new Dimension(40, 21));
		this.largeurJTextField.setText("" + this.largeur);
		
		this.minesJTextField.setBorder(this.border);
		this.minesJTextField.setMinimumSize(new Dimension(40, 21));
		this.minesJTextField.setPreferredSize(new Dimension(40, 21));
		this.minesJTextField.setText("" + this.mines);
		
		this.boutonOk.setMaximumSize(new Dimension(70, 27));
		this.boutonOk.setMinimumSize(new Dimension(70, 27));
		this.boutonOk.setPreferredSize(new Dimension(70, 27));
		this.boutonOk.setMnemonic('O');
		this.boutonOk.setText("OK");
		this.boutonOk.addActionListener(this);
		
		this.boutonAnnuler.setMaximumSize(new Dimension(70, 27));
		this.boutonAnnuler.setMinimumSize(new Dimension(70, 27));
		this.boutonAnnuler.setPreferredSize(new Dimension(70, 27));
		this.boutonAnnuler.setMargin(new Insets(2, 10, 2, 10));
		this.boutonAnnuler.setMnemonic('A');
		this.boutonAnnuler.setText("Annuler");
		this.boutonAnnuler.addActionListener(this);
		
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		getContentPane().add(panel);
		
		this.panel.add(this.hauteurjLabel,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		this.panel.add(this.largeurjLabel,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		this.panel.add(this.minesjLabel,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		this.panel.add(this.hauteurJTextField,   new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 20), 0, 0));
		
		this.panel.add(this.largeurJTextField,   new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 20), 0, 0));
		
		this.panel.add(this.minesJTextField,   new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 20), 0, 0));
		
		this.panel.add(this.boutonOk,  new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		this.panel.add(this.boutonAnnuler,  new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.boutonOk) {
			
			try {
				
				int nHauteur = Integer.parseInt(this.hauteurJTextField.getText());
				
				//restrictions sur les dimensions et le nombre de mines
				if (nHauteur < 1) nHauteur = 1;
				if (nHauteur > 40) nHauteur = 40;
				
				int nLargeur = Integer.parseInt(this.largeurJTextField.getText());
				
				if (nLargeur < 1) nLargeur = 1;
				if (nLargeur > 40) nLargeur = 40;
				
				int nMines = Integer.parseInt(this.minesJTextField.getText());
				
				if (nMines < 0) nMines = 0;
				if (nMines > nLargeur*nHauteur) nMines = nLargeur*nHauteur;
				
				demineur.dispose();// ferme la fenetre 
				
				System.gc();
				
				new Demineur(nHauteur, nLargeur, nMines, 4);//lance le jeu
			}
			catch (Exception exc) {//si erreur de saisie
				this.setTitle("Valeurs incorrectes");
			}
		}
		
		if (e.getSource() == this.boutonAnnuler) {
			this.setVisible(false);
		}
	}
}