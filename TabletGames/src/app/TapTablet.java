package app;

import javax.swing.*;
import java.awt.*;

import javax.swing.border.Border;

import jpen.PenManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;

public class TapTablet extends JFrame implements KeyListener, WindowListener {
	
protected JPanel mainPan;
protected JPanel pano;
protected JLabel nbToucheLbl;
protected JLabel nbRestantLbl;
protected JLabel txt1, txt2;
protected JLabel lblPause;
//zone de jeu carré, valeur 800x800, valeures mises à 720 pour éviter le débordement
protected int miniDim;
protected int maxDim;

protected float nbTouche;
protected float nbRestant;
protected float nbClicFail;
Random rnd;
int temps;
Timer t;

//1 pour facile, 2 pour moyen, 3 pour difficile
private int difficulty;

//TODO : afficher les statistiques en fin de partie

	public TapTablet(int diff){
		
		//création du graphisme
		mainPan = new JPanel();
		mainPan.setLayout(new BoxLayout(mainPan, BoxLayout.Y_AXIS));
		mainPan.setBackground(Color.BLACK);
		//mise en place des variables
		nbRestant = 20;
		nbTouche = 0;
		nbClicFail = 0;
		miniDim = 0;
		maxDim = 850;
		rnd = new Random();
		
		
		difficulty = diff;
		
		afficheHUD();
		afficheJeu();
		popUpInstruction();
		
		
		/*on cr�e un �couteur pour le stylet cependant il ne sert que pour l'option quitter et pause 
		 * via le stylet, le reste du jeu est g�r� avec un clic souris car il n'y a pas besoin d'actions sp�cifiques au stylet ou � la tablette.
		 * ( le stylet simule un clic souris initialement, aucune modifiation n�cessaire)
		 */
		
		PenManager pm = new PenManager(this);
		pm.pen.addListener(new ProcessingPen(this,4)); 
		
		addWindowListener(this);
		 addKeyListener(this);
		 this.setFocusable(true);
	        this.requestFocusInWindow();
	        
		temps = 0;
        t = createTimer ();
        t.start ();
		aleaPt();
		
		this.add(mainPan);
		
		
		pack();
		setLocationRelativeTo(null);
	    this.setVisible(true);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	//-------------------------PARTIE GRAPHIQUE-------------------------
	//fonction pour afficher le HUD
    public void afficheHUD(){
    	
    	Border whiteline = BorderFactory.createLineBorder(Color.white,1);
    	
    	JPanel pan = new JPanel(new BorderLayout());
    	pan.setBorder(whiteline);
    	
    	nbToucheLbl = new JLabel(String.valueOf(nbTouche));
    	nbToucheLbl.setOpaque(true);
    	nbToucheLbl.setPreferredSize(new Dimension( 100, 50 ));
    	nbToucheLbl.setBackground(Color.BLACK);
    	nbToucheLbl.setForeground(Color.WHITE);
    	
    	nbRestantLbl = new JLabel(String.valueOf(nbRestant));
    	nbRestantLbl.setOpaque(true);
    	nbRestantLbl.setPreferredSize(new Dimension( 100, 50 ));
    	nbRestantLbl.setBackground(Color.BLACK);
    	nbRestantLbl.setForeground(Color.WHITE);
    	nbRestantLbl.setHorizontalAlignment(SwingConstants.TRAILING);
    	
    	JPanel po = new JPanel(new BorderLayout());
    	
    	txt1 = new JLabel("Nombre de cercles touches");
    	txt2 = new JLabel("Nombre de cercles restants");
    	
    	txt1.setOpaque(true);
    	txt1.setBackground(Color.BLACK);
    	txt1.setForeground(Color.WHITE);
    	txt1.setHorizontalAlignment(SwingConstants.CENTER);
    	
    	txt2.setOpaque(true);
    	txt2.setBackground(Color.BLACK);
    	txt2.setForeground(Color.WHITE);
    	txt2.setHorizontalAlignment(SwingConstants.CENTER);
    	
    	JLabel vide = new JLabel("Cliquez sur les cercles");
    	vide.setOpaque(true);
    	vide.setBackground(Color.BLACK);
    	vide.setForeground(Color.GREEN);
    	vide.setHorizontalAlignment(SwingConstants.CENTER);
    	
    	
    	po.add(txt1, BorderLayout.WEST);
    	po.add(vide, BorderLayout.CENTER);
    	po.add(txt2, BorderLayout.EAST);
    	
    	pan.add(nbToucheLbl, BorderLayout.WEST);
    	pan.add(po, BorderLayout.CENTER);
    	pan.add(nbRestantLbl, BorderLayout.EAST);
    	mainPan.add(pan);
    }
	
    //fonction pour afficher l'écran de jeu
    public void afficheJeu(){
    	
    	pano = new JPanel(null);
    	pano.setPreferredSize(new Dimension(900,900));
    	pano.setBackground(Color.BLACK);
    	
    	/* Ici, gestion des �venements � la souris effectu�s plut�t qu'� la tablette 
    	 * car il ne requiert pas sp�cialement d'evenements propres � la tablette. ( le stylet simule un clic souris initialement)
    	 */
    	pano.addMouseListener(clicD);
    	
    	 lblPause = new JLabel("jeu en pause");
    	 Font fonPause = new Font ("Arial", Font.BOLD, 20);
    	 lblPause.setFont(fonPause);
    	lblPause.setForeground(Color.GREEN);
    	lblPause.setBackground(Color.BLACK);
		mainPan.add(lblPause);
		lblPause.setVisible(false);
		
    	mainPan.add(pano);
    }
    
    //fonction pour afficher les stats
    public void afficheStat(){
    	
    	t.stop();
    	pano.removeAll();
    	System.out.println("Tap tablet début de jeu");
    	pano.setLayout(new GridLayout (5,2));
    	
    	Border emptyBorder = BorderFactory.createEmptyBorder();
    	
    	JLabel lb1 = new JLabel("Score");
    	int nbT = (int)nbTouche;
    	JLabel lb2 = new JLabel(String.valueOf(nbT));
    	
    	JLabel lb3 = new JLabel("Nombre de clics rates");
    	int nbF = (int)nbClicFail;
    	JLabel lb4 = new JLabel(String.valueOf(nbF));
    	
    	JLabel lb5 = new JLabel("Nombre de cercles touches");
    	JLabel lb6 = new JLabel(String.valueOf(nbT)+"/20");
    	
    	JLabel lb7 = new JLabel("Precision");
    	float prec = nbTouche/(nbClicFail+nbTouche);
    	prec = prec*100;
    	JLabel lb8 = new JLabel(String.valueOf(prec)+"%");
    	
    	lb1.setForeground(Color.WHITE);
    	lb2.setForeground(Color.WHITE);
    	lb3.setForeground(Color.WHITE);
    	lb4.setForeground(Color.WHITE);
    	lb5.setForeground(Color.WHITE);
    	lb6.setForeground(Color.WHITE);
    	lb7.setForeground(Color.WHITE);
    	lb8.setForeground(Color.WHITE);
    	
    	pano.add(lb1);
    	pano.add(lb2);
    	pano.add(lb3);
    	pano.add(lb4);
    	pano.add(lb5);
    	pano.add(lb6);
    	pano.add(lb7);
    	pano.add(lb8);
    	
    	JPanel bo = new JPanel(new BorderLayout());
    	JPanel bu = new JPanel(new BorderLayout());
    	
    	JButton rej = new JButton("Rejouer");
    	JButton quit = new JButton("Quitter TapTablet");
    	
    	rej.setBackground(Color.BLACK);
    	rej.setForeground(Color.GREEN);
    	rej.setBorder(emptyBorder);
    	rej.addActionListener(clicRej);
    	
    	quit.setBackground(Color.BLACK);
    	quit.setForeground(Color.GREEN);
    	quit.setBorder(emptyBorder);
    	quit.addActionListener(clicQuit);
    	
    	bo.add(rej, BorderLayout.CENTER);
    	bu.add(quit, BorderLayout.CENTER);

    	pano.add(bo);
    	pano.add(bu);
    	
    	mainPan.repaint();
    	System.out.println(" Tap Tablet  : fin de partie ");
    }
    
    //------------------------------------------------------------ZONE A FAIRE PAR LE NICOZER------------------------------------------------------------
    private ActionListener clicRej = new ActionListener() {
		 public void actionPerformed(ActionEvent e) {
			 //retour au menu de difficulté
			  setVisible(false);
			  new MenuChoixDifficulte();
		 }
	 };
	 //------------------------------------------------------------ZONE A FAIRE PAR LE NICOZER------------------------------------------------------------
	 private ActionListener clicQuit = new ActionListener() {
		 public void actionPerformed(ActionEvent e) {
			//Mets ici un retour au menu du jeu
			 dispose();
		 }
	 };
	
	//-------------------------PARTIE FONCTIONS-------------------------
	//créer et dessine un point aléatoire
	public void aleaPt(){
		//faire un alea
		int x = rnd.nextInt(maxDim - miniDim + 1) + miniDim;
		int y = rnd.nextInt(maxDim - miniDim + 1) + miniDim;
		
		//créer le point associé
		JButton newPt = new RoundButton("");
		newPt.setBackground(Color.GREEN);
		newPt.addActionListener(clicPt);
		if(difficulty==3){
			newPt.setSize(30, 30);
		}
		else{
			newPt.setSize(50, 50);
		}
		newPt.setLocation(x,y);
		//position des boutons 
		//System.out.println(x+" "+y);
		pano.add(newPt);
		pano.repaint();
	}
	
	 private ActionListener clicPt = new ActionListener() {
		 public void actionPerformed(ActionEvent e) {
			 nbTouche++;
			 nbRestant--;
			 temps=0;
			 nbToucheLbl.setText(String.valueOf(nbTouche));
			 nbRestantLbl.setText(String.valueOf(nbRestant));
			 mainPan.repaint();
			 //efface le panel
			 pano.removeAll();
			 
			 if(nbRestant>0){
				//créer un nouveau point
				 aleaPt(); 
			 }
			 else{
				 afficheStat();
			 }
		 }
	 }; 
	 
	 private MouseListener clicD = new MouseListener() {
	    	@Override
	    	public void mousePressed(MouseEvent e){
	    		nbClicFail++;
	    		//System.out.println(nbClicFail);
	    	}
	    	public void mouseReleased(MouseEvent e){
	    		// TODO Auto-generated method stub
	    	}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	    };
	    
	 private Timer createTimer ()
	    {
	        // Création d'une instance de listener 
	        // associée au timer
	        ActionListener action = new ActionListener ()
	          {
	            // Méthode appelée à chaque tic du timer
	            public void actionPerformed (ActionEvent event)
	            {
	              temps++;
	              switch(difficulty){
	              	case 1:
	             
	              		if(temps==3){
		            		  nbRestant--;
		            		  nbRestantLbl.setText(String.valueOf(nbRestant));
	            		  if(nbRestant>0){
	            			  temps=0;
		            		  //efface le point, en créé un nouveau
		            		  pano.removeAll();
	          				//créer un nouveau point
	          				 aleaPt(); 
	          			 }
	            		  
	            	  }
	              		if(nbRestant==0){
	              			afficheStat();
	              		}
	            	  break;
	              	case 2:
	              		
	              		if(temps==2){
		            		  nbRestant--;
		            		  nbRestantLbl.setText(String.valueOf(nbRestant));
	            		  if(nbRestant>0){
	            			  temps=0;
		            		  //efface le point, en créé un nouveau
		            		 pano.removeAll();

	          				//créer un nouveau point
	          				 aleaPt(); 
	          			 }

	            		  
	            	  }
	              		if(nbRestant==0){
	              			afficheStat();
	              		}
	            	  break;
	              	case 3:
	              		if(temps==2){
		            		  nbRestant--;
		            		  nbRestantLbl.setText(String.valueOf(nbRestant));
	            		  if(nbRestant>0){
	            			  temps=0;
		            		  //efface le point, en créé un nouveau
		            		 pano.removeAll();
		        		  
	          				//créer un nouveau point
	          				 aleaPt(); 
	          			 }
	            		  
	            	  }
	              		if(nbRestant==0){
	              			afficheStat();
	              		}
	            	  break;
	              }
	              
	            }
	          };
	          
	        // Création d'un timer qui génère un tic
	        // chaque secondes
	        return new Timer (1000, action);
	      }

	 public int popUpQuitter(){
		    String[] options = {"Non", "Oui"};
		    JOptionPane jop = new JOptionPane();
		    int choix = jop.showOptionDialog(null,
		            "Souhaitez-vous retourner au menu ?",
		            "Quitter le jeu",
		            JOptionPane.YES_NO_CANCEL_OPTION, 
		            JOptionPane.QUESTION_MESSAGE,
		            null,
		            options,
		            options);
		    return choix;
		}

		public void gestionRetourMenu(int choix){
			//Oui
		   if( choix == 1 ){
		   	System.out.println("Quitter Tap tablet, retour menu");
		   	//fermer le jeu
		   	this.dispose();
		   }
		   //Non
		   else if( choix == 0 ){
			   	System.out.println("Retour au jeu ");
				
		   }
		} 
		
		public void popUpInstruction(){
		    JOptionPane instr = new JOptionPane();
		    instr.showMessageDialog(this,
		            "Touches disponibles (clavier) : \n"
		    		+"- touche espace : lancer le jeu / mettre en pause / reprendre le jeu \n"
		            + "- touche Q : quitter le jeu \n" 
		    		+"Objectif : toucher les points avant qu'ils ne disparaissent ! \n"
		            + "Attention, la partie va commencer... \n" ,
		            "Instructions", 
		            JOptionPane.INFORMATION_MESSAGE);
		    
		}
		
		public Timer getTimer(){
			return this.t;
		}
		
		public JLabel getLlblPause(){
			return this.lblPause;
		}
	 public void keyPressed(KeyEvent e) {
		 if(e.getKeyCode() == KeyEvent.VK_SPACE){
		 
			if(t.isRunning()){
				t.stop();
				lblPause.setVisible(true);
			}else{
				t.start();
				lblPause.setVisible(false);
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_Q){

			if(t.isRunning()){
				t.stop();
				lblPause.setVisible(true);
			}
				int choix = popUpQuitter();
				gestionRetourMenu(choix);
		}
		
	 }
	 
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		t.stop();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}  

}
