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
		
		this.border = BorderFactory.createCompoundBorder(
				BorderFactory.createBevelBorder(
						BevelBorder.RAISED,
						SystemColor.white,
						SystemColor.white,
						new Color(109, 109, 109),
						new Color(156, 156, 156)),
				BorderFactory.createEmptyBorder(0,10,0,10));
		
		panel.setLayout(gridBagLayout);
		
		titre.setFont(new java.awt.Font("Serif", 1, 20));
		titre.setForeground(SystemColor.white);
		titre.setBorder(this.border);
		titre.setPreferredSize(new Dimension(180, 50));
		titre.setText("Evite les mines");
		
		this.auteurs.setFont(new java.awt.Font("Dialog", 0, 18));
		this.auteurs.setForeground(Color.white);
		this.auteurs.setPreferredSize(new Dimension(600, 22));
		this.auteurs.setText("Réalisé par Ibrahima Diagne Seck et Oumou Hawa Diallo");
		
		this.github1.setFont(new java.awt.Font("Dialog", 0, 16));
		this.github1.setForeground(Color.white);
		this.github1.setPreferredSize(new Dimension(450, 16));
		this.github1.setText("https://github.com/ibrahimadiagneseck");
		
		this.github2.setFont(new java.awt.Font("Dialog", 0, 16));
		this.github2.setForeground(Color.white);
		this.github2.setPreferredSize(new Dimension(450, 16));
		this.github2.setText("https://github.com/oumouhawadiallo");
		
		panel.setBackground(SystemColor.activeCaption);
		
		this.fermer.setFont(new java.awt.Font("Dialog", 0, 10));
		this.fermer.setFocusPainted(false);
		this.fermer.setMnemonic('F');
		this.fermer.setText("Fermer");
		this.fermer.addActionListener(this);
		
		this.espace1.setPreferredSize(new Dimension(600, 100));
		this.espace2.setPreferredSize(new Dimension(600, 100));
		
		getContentPane().add(panel);
		
		panel.add(titre, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		
		panel.add(this.auteurs, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		panel.add(this.github1, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		panel.add(this.github2, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		panel.add(this.fermer, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
		
		panel.add(this.espace1, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		panel.add(this.espace2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
	}
	
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}