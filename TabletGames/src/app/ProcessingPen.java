package app;

import jpen.event.PenAdapter;
import jpen.event.*;

import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import jpen.*;

public class ProcessingPen extends PenAdapter
{

	int width = this.GetScreenWorkingWidth();
	int height = this.GetScreenWorkingHeight();
  boolean bIsDown;
  float prevXPos = -1, prevYPos = -1;
  Player j1;
  Player j2;
  Pong2JPanel pong2j;
  Pong1JGaucher pong1jg;
  Pong1JDroitier pong1jd;
  TapTablet tt;
  
  /* mode : 
   * 1 = tablet pong 2J
   * 2 = tablet pong 1J gaucher
   * 3 = tablet ponf 1J droitier
   * 4 = Tap tablet
   */
  int mode;
  
 
  
  public ProcessingPen(Player j1, Player j2, Pong2JPanel pong2j, int mode) {
	  this.j1 = j1;
	  this.j2 = j2;
	  this.pong2j = pong2j;
	  this.mode = mode;
  }
  
  public ProcessingPen(Player j1, Player j2, Pong1JGaucher pong1jg, int mode) {
	  this.j1 = j1;
	  this.j2 = j2;
	  this.pong1jg = pong1jg;
	  this.mode = mode;
  }
  public ProcessingPen(Player j1, Player j2, Pong1JDroitier pong1jd, int mode) {
	  this.j1 = j1;
	  this.j2 = j2;
	  this.pong1jd = pong1jd;
	  this.mode = mode;
  }
  
  public ProcessingPen(TapTablet tt, int mode) {
	  this.tt = tt;
	  this.mode = mode;
  }

  public void penButtonEvent(PButtonEvent evt)
  {
    
 
    if (evt.pen.getButtonValue(PButton.Type.RIGHT)){
    	System.out.println("Quitter via stylet (RIGHT)");
    	if(mode == 1){
	    	if(pong2j.getTimer().isRunning()){
				pong2j.getTimer().stop();
			}
	    	 TabletPong2J frame = (TabletPong2J) SwingUtilities.getRoot(pong2j);
	    	int choix = frame.popUpQuitter();
			frame.gestionRetourMenu(choix);
	    	
    	}else if (mode == 2){
    		if(pong1jg.getTimer().isRunning()){
				pong1jg.getTimer().stop();
			}
    		TabletPong1J frame = (TabletPong1J) SwingUtilities.getRoot(pong1jg);
	    	int choix = frame.popUpQuitter();
			frame.gestionRetourMenu(choix);
    	}else if (mode == 3){
    		if(pong1jd.getTimer().isRunning()){
				pong1jd.getTimer().stop();
			}
    		TabletPong1J frame = (TabletPong1J) SwingUtilities.getRoot(pong1jd);
	    	int choix = frame.popUpQuitter();
			frame.gestionRetourMenu(choix);
    	}
    }
    
    //mettre en pause via un bouton du stylet
    if (evt.pen.getButtonValue(PButton.Type.CENTER)){
    	System.out.println("Pause via stylet (CENTER)");
    	
    	
    	if(mode == 1){
	    	if(pong2j.getTimer().isRunning()){
				pong2j.getTimer().stop();
			}else{
				pong2j.getTimer().start();
			}
    	}else if (mode == 2){
    		if(pong1jg.getTimer().isRunning()){
				pong1jg.getTimer().stop();
			}else{
				pong1jg.getTimer().start();
			}
    	}else if (mode == 3){
    		if(pong1jd.getTimer().isRunning()){
				pong1jd.getTimer().stop();
			}else{
				pong1jd.getTimer().start();
			}
    	} /*else if (mode == 4){
    		
    	System.out.println("blblblblblblblblbl");
    		if(tt.getTimer().isRunning()){
				tt.getTimer().stop();
				tt.getLlblPause().setVisible(true);
			}else{
				tt.getTimer().start();
				tt.getLlblPause().setVisible(false);
			}
    	} */ 
    }
	
   
  }
  
  
  
  public void penLevelEvent(PLevelEvent evt)
  {
	  //on exclue Tap tablet qui se joue avec une gestion � la souris 
	if(mode != 4){  
	    // See if the pen is down
	    bIsDown = evt.pen.hasPressedButtons();
	   if(evt.pen.getButtonValue(PButton.Type.LEFT)) {
	    if(bIsDown){
	  //mouvement du joueur qui est à gauche
	  		if(evt.pen.getLevelValue(PLevel.Type.X) < width/3){
	  			int v = (int) evt.pen.getLevelValue(PLevel.Type.Y);
	  			j1.setPositionY(v); 
	  		
	  			if(evt.pen.getLevelValue(PLevel.Type.Y) < 0){
	  				j1.setPositionY(0);
	  			}
	  			if(evt.pen.getLevelValue(PLevel.Type.Y) + j1.getHeight() > height){
	  				j1.setPositionY(height - j1.getHeight());
	  			}
	  			//mouvement du joueur qui est à droite 
	  		}else if (evt.pen.getLevelValue(PLevel.Type.X) > (width/3) *2 ){
	  			int v = (int) evt.pen.getLevelValue(PLevel.Type.Y);
	  			j2.setPositionY(v); 
	  			
	  			if(evt.pen.getLevelValue(PLevel.Type.Y) < 0){
	  				j2.setPositionY(0);
	  			}
	  			if(evt.pen.getLevelValue(PLevel.Type.Y) + j2.getHeight() > height){
	  				j2.setPositionY(height - j2.getHeight());
	  			}
	  		}
	    
	    }else if (!bIsDown){
	    	return;
	    }
	    
	  }
	
	} /*else if(mode == 4){
		 tt.nbTouche++;
		 tt.nbRestant--;
		 tt.temps=0;
		 tt.nbToucheLbl.setText(String.valueOf(tt.nbTouche));
		 tt.nbRestantLbl.setText(String.valueOf(tt.nbRestant));
		 tt.mainPan.repaint();
		 //efface le panel
		 tt.pano.removeAll();
		 
		 if(tt.nbRestant>0){
			//créer un nouveau point
			 tt.aleaPt(); 
		 }
		 else{
			 tt.afficheStat();
		 }
	} */
  }
  
  public static int GetScreenWorkingWidth() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	public static int GetScreenWorkingHeight() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}
}
