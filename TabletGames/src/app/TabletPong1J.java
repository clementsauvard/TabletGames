package app;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TabletPong1J extends JFrame implements KeyListener,WindowListener {
	
	protected int choix;
	final int BALLSIZE = 40;
	int width = this.GetScreenWorkingWidth();
	int height = this.GetScreenWorkingHeight();
	private Pong1JDroitier pong1jd;
	private Pong1JGaucher pong1jg;
	public TabletPong1J(int choix){
		this.choix = choix;
		this.setSize(width, height);
		
		this.setBackground(Color.BLACK);
		this.setResizable(false);
		
		//droitier
		if(choix == 1){
			LancerPartieDroitier();
		}
		//gaucher
		else if(choix == 0){
			LancerPartieGaucher();
		}
		
		popUpInstruction();
		addWindowListener(this);
		 addKeyListener(this);
		 this.setFocusable(true);
	     this.requestFocusInWindow();
		
        //On arrête l'application après la fermeture de notre fenêtre
	     setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}
	
	
	
	public void LancerPartieDroitier(){
		System.out.println("TabletPong 1J lancé: mode droitier");
		pong1jd = new Pong1JDroitier();
		add (pong1jd);
	}
	
	public void LancerPartieGaucher(){
		System.out.println("TabletPong 1J lancé : mode gaucher");
		pong1jg = new Pong1JGaucher();
		add (pong1jg);
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
	   	System.out.println("Quitter Pong 1J, retour menu");
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
	    		+"	- D�placer une barre : slider vers le haut ou vers le bas \n"
	    		+"(si boutons du stylet configur�s comme clic droit/clic centre) : \n "
	    		+"	- bouton centre : lancer le jeu / mettre en pause / reprendre le jeu \n"
	    		+"	- bouton droit : quitter le jeu \n",
	            "Instructions", 
	            JOptionPane.INFORMATION_MESSAGE);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//mode droitier
		if(choix == 1){
			if(e.getKeyCode() == KeyEvent.VK_SPACE){
				if(pong1jd.getTimer().isRunning()){
					pong1jd.getTimer().stop();
				}else{
					pong1jd.getTimer().start();
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_Q){

				if(pong1jd.getTimer().isRunning()){
					pong1jd.getTimer().stop();
				}
					int choix = popUpQuitter();
					gestionRetourMenu(choix);
				
			}
			//mode gaucher
		}else if (choix == 0){
			if(e.getKeyCode() == KeyEvent.VK_SPACE){
				if(pong1jg.getTimer().isRunning()){
					pong1jg.getTimer().stop();
				}else{
					pong1jg.getTimer().start();
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_Q){

				if(pong1jg.getTimer().isRunning()){
					pong1jg.getTimer().stop();
				}
					int choix = popUpQuitter();
					gestionRetourMenu(choix);
				
			}
		}
		
	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyTyped(KeyEvent e) {
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
		if(choix == 1){
			pong1jd.getTimer().stop();
		}else if(choix == 0){
			pong1jg.getTimer().stop();
		}
		

	}

	public static int GetScreenWorkingWidth() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	public static int GetScreenWorkingHeight() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
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
