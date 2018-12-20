package app;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import jpen.PenManager;

//import jpen.*;
//import jpen.event.*;

public class Pong1JDroitier extends JPanel implements ActionListener{

	final int BALLSIZE = 40, MARGE = 40;
	int width = this.GetScreenWorkingWidth();
	int height = this.GetScreenWorkingHeight();
	Player j1 = new Player(2);
	Player murGauche = new Player(4);
	Timer t;
	private JLabel lblscoreJ1;
	private int scoreJ1;
	Ball b = new Ball(1);
	

	
	public Pong1JDroitier(){
		this.setBackground(Color.BLACK);
		scoreJ1 = 0;
		b.setPosition(width/2 - BALLSIZE/2 , height/2);
	
		lblscoreJ1 = new JLabel("    " + scoreJ1 );
		lblscoreJ1.setForeground(Color.WHITE);

		Font fontScore = new Font ("Arial", Font.BOLD, 100);
		Font fontPause = new Font ("Arial", Font.BOLD, 30);
		lblscoreJ1.setFont(fontScore);
	
		add(lblscoreJ1);
	
		
		
	
		//addMouseMotionListener(this);
        
       PenManager pm = new PenManager(this);
		pm.pen.addListener(new ProcessingPen(murGauche, j1,this,3));   
        
		t = new Timer(50, this);
		t.stop();
	}
	
	private void update(){
		b.update();
		j1.update();
		murGauche.update();
		
		
		b.checkCollisionXJoueur2(j1);
		
		if(b.getX() < MARGE + murGauche.getWidth()){
			//on augmente le score
			scoreJ1 ++;
			lblscoreJ1.setText( "    " + scoreJ1);
		}else if(b.getX() > width-BALLSIZE){	
			//Le joueur a perdu
			JOptionPane d = new JOptionPane();
			d.showMessageDialog( this, "Terminé ! votre score est : " + scoreJ1 + " . \n La partie va recommencer.", 
			      "Partie terminée", JOptionPane.INFORMATION_MESSAGE);
			relancerPartie();
		}
	}
	
	public void relancerPartie(){
		t.stop();
		b.setPosition(width/2 - BALLSIZE/2 , height/2);
		j1.setPositionY(200);
		scoreJ1 = 0;
		lblscoreJ1.setText("    " + scoreJ1);
		
		
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		j1.paint(g);
		murGauche.paint(g);
		b.paint(g);
		
		g.setColor(Color.WHITE);
		g.drawLine(width/2, 0, width/2, height);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		update();
		repaint();
	}

	

	public Timer getTimer(){
		return this.t;
	}

	public static int GetScreenWorkingWidth() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	public static int GetScreenWorkingHeight() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}
	
// ---------------------------------------------------- 
	


 /*
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int v = e.getY();
		j1.setPositionY(v); 
		
		if(e.getY() < 0){
			j1.setPositionY(0);
		}
		if(e.getY() + j1.getHeight() > HEIGHT){
			j1.setPositionY(HEIGHT - j1.getHeight());
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	*/
	
	 /*public void penButtonEvent(PButtonEvent evt)
	  {
	    // See if the pen is down
	    if(evt.pen.hasPressedButtons()){
	    	System.out.println("stylet utilisÃ©");
	    } 
	    
	  } */

}
