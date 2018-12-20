package app;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TabletPong2J extends JFrame implements KeyListener, WindowListener {
	
	int width = this.GetScreenWorkingWidth();
	int height = this.GetScreenWorkingHeight();
	Pong2JPanel pong2j;
	//initialiser le jeu
	public TabletPong2J(){
		
		System.out.println("Tablet pong 2J lancé");
		this.setSize(width, height);
		this.setBackground(Color.BLACK);
		this.setResizable(false);
		
		pong2j = new Pong2JPanel();
		add (pong2j);
		popUpInstruction();
		 addKeyListener(this);
		 addWindowListener(this);
		 this.setFocusable(true);
	        this.requestFocusInWindow();
		 //On arrÃªte l'application aprÃ¨s la fermeture de notre fenÃªtre
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true); 
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
	   	System.out.println("Quitter Pong 2J, retour menu");
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
	    		+"	- touche espace : lancer le jeu / mettre en pause / reprendre le jeu \n"
	            +"	- touche Q : quitter le jeu \n" 
	    		+"Stylet : \n"
	    		+"	- Déplacer une barre : slider vers le haut ou vers le bas \n"
	    		+"(si boutons du stylet configurés comme clic droit/clic centre) : \n "
	    		+"	- bouton centre : lancer le jeu / mettre en pause / reprendre le jeu \n"
	    		+"	- bouton droit : quitter le jeu \n",
	            "Instructions", 
	            JOptionPane.INFORMATION_MESSAGE);
	}
	

	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			if(pong2j.getTimer().isRunning()){
				pong2j.getTimer().stop();
			}else{
				pong2j.getTimer().start();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_Q){

			if(pong2j.getTimer().isRunning()){
				pong2j.getTimer().stop();
			}
				int choix = popUpQuitter();
				gestionRetourMenu(choix);
			
;			}
	}
	
	public static int GetScreenWorkingWidth() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	public static int GetScreenWorkingHeight() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
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
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		pong2j.getTimer().stop();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
