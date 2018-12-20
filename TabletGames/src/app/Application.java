package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Application extends JFrame {
	private JPanel panelPrincipal;
	private BorderLayout layoutPrincipal;
	
	private JPanel pnlTitre;
	private JLabel lblTitre;
	
	private JPanel panelBtn;
	private GridLayout layoutBtn;
	
	private JButton btnTapTablet;
	private JButton btnTabletPong1J;
	private JButton btnTabletPong2J;
	private JPanel pnlVide;
	private JButton btnQuitter;

	
	public Application(){

		
		panelPrincipal = new JPanel();
		layoutPrincipal = new BorderLayout();
		
		pnlTitre = new JPanel();
		lblTitre = new JLabel("Tablet Games");
		
		panelBtn = new JPanel();
		layoutBtn = new GridLayout(5,1);
		
		btnTapTablet = new JButton("Tap Tablet");
		btnTabletPong1J = new JButton("Tablet Pong - Un joueur");
		btnTabletPong2J = new JButton("Tablet Pong - Deux joueurs");
		pnlVide = new JPanel();
		btnQuitter = new JButton("Quitter");
		//panelBtn.setLayout(new BoxLayout(panelBtn, BoxLayout.Y_AXIS));
		
		btnTapTablet.addActionListener(btnTapTabletlistener);
		btnTabletPong1J.addActionListener(btnPong1Jlistener);
		btnTabletPong2J.addActionListener(btnPong2Jlistener);
		btnQuitter.addActionListener(btnQuitterlistener);
		
		lblTitre.setHorizontalAlignment(JLabel.CENTER);
		lblTitre.setVerticalAlignment(JLabel.CENTER);
		pnlTitre.add(lblTitre);
		
		panelBtn.setLayout(layoutBtn);
		panelBtn.add(btnTapTablet);
		panelBtn.add(btnTabletPong1J);
		panelBtn.add(btnTabletPong2J);
		panelBtn.add(pnlVide);
		panelBtn.add(btnQuitter);
		
		panelPrincipal.setLayout(layoutPrincipal);
		panelPrincipal.add(lblTitre,BorderLayout.NORTH);
		panelPrincipal.add(panelBtn, BorderLayout.CENTER);
		add(panelPrincipal);
		
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		 //On arrête l'application après la fermeture de notre fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
	
private ActionListener btnTapTabletlistener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("appel Tap Tablet");
				//TapTablet tt = new TapTablet();
				MenuChoixDifficulte mcd = new MenuChoixDifficulte();
			}
			   
 };
 
private ActionListener btnPong1Jlistener = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println(" appel Tablet Pong 1J");
			
			 int choix = afficherBoiteDialogue();
				GestionChoixMain(choix);
		}
		   
};

private ActionListener btnPong2Jlistener = new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("appel Tablet Pong 2J");
		TabletPong2J tp2j = new TabletPong2J();
	}
	   
};
 
private ActionListener btnQuitterlistener = new ActionListener() {

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("appel pop up quitter");
		int choix = popUpQuitter();
		
		   if( choix == 1 ){
			   	System.out.println("choix : oui, jeu quitté");
			   		
			   		System.exit(0);
			   }
			   else if( choix == 0 ){
				   	System.out.println("choix : non, retour au menu");
					
			   }
	}
	   
};
 

public int afficherBoiteDialogue(){
    String[] options = {"Gaucher", "Droiter"};
    JOptionPane jop = new JOptionPane();
    int choix = jop.showOptionDialog(null,
            "Avec quelle main souhaitez-vous jouer ?",
            "Choix main",
            JOptionPane.YES_NO_CANCEL_OPTION, 
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options);
    return choix;
}

public int popUpQuitter(){
    String[] options = {"Non", "Oui"};
    JOptionPane jop = new JOptionPane();
    int choix = jop.showOptionDialog(null,
            "Souhaitez-vous quitter le jeu ?",
            "Quitter le jeu",
            JOptionPane.YES_NO_CANCEL_OPTION, 
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options);
    return choix;
}

public void GestionChoixMain(int choix){
   if( choix == 1 ){
   	System.out.println("Droitier");
   	//lancer partie 
   	TabletPong1J tpdroite = new TabletPong1J(choix);
   	//this.dispose();
   }
   else if( choix == 0 ){
	   	System.out.println("Gaucher ");
	   	//lancer partie
		TabletPong1J tpgauche = new TabletPong1J(choix);
		
   }
} 



public static void main(String[] args) {
		Application app = new Application();
	}
}
