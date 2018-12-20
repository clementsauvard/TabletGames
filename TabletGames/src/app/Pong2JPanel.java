package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import jpen.event.*;
import jpen.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Pong2JPanel extends JPanel implements ActionListener {
	
	final int BALLSIZE = 40;
	int width = this.GetScreenWorkingWidth();
	int height = this.GetScreenWorkingHeight();
	Player j1 = new Player(1);
	Player j2 = new Player(2);
	Timer t;
	private JLabel lblscoreJ1;
	private JLabel lblscoreJ2;
	private int scoreJ1;
	private int scoreJ2;
	
	Ball b = new Ball(3); 
	public Pong2JPanel(){
		
		PenManager pm = new PenManager(this);
		pm.pen.addListener(new ProcessingPen(j1, j2,this,1));
		
		this.setBackground(Color.BLACK);
		scoreJ1 = 0;
		scoreJ2 = 0;
		b.setPosition(width/2 - BALLSIZE/2 , height/2);
	
		lblscoreJ1 = new JLabel(scoreJ1 + "  ");
		lblscoreJ2 = new JLabel("  " + scoreJ2);
		lblscoreJ1.setForeground(Color.WHITE);
		lblscoreJ2.setForeground(Color.WHITE);

		Font fontScore = new Font ("Arial", Font.BOLD, 100);
		Font fontPause = new Font ("Arial", Font.BOLD, 30);
		lblscoreJ1.setFont(fontScore);
		lblscoreJ2.setFont(fontScore);
	
		add(lblscoreJ1);
		add(lblscoreJ2);
	
		
	
		//addMouseMotionListener(this);
        this.requestFocusInWindow();
		t = new Timer(50, this);
		t.stop();
		
	}
	  
	private void update(){
		
		b.update();
		j1.update();
		j2.update();
		
		b.checkCollisionXJoueur1(j1);
		b.checkCollisionXJoueur2(j2);
		
		if(b.getX() < 0){
			//Joueur 1 à perdu 
			scoreJ2 ++;
			lblscoreJ2.setText("  " + scoreJ2);
			popUpFinPartie();
			relancerPartie();
		}else if(b.getX() > width - BALLSIZE){
		
			//joueur 2 à perdu
			scoreJ1 ++;
			lblscoreJ1.setText(scoreJ1 + "  ");
			popUpFinPartie();
			relancerPartie();
		}
	}
	
	public void popUpFinPartie(){
		JOptionPane d = new JOptionPane();
		d.showMessageDialog( this, "Termin� ! le score est : \n  Joueur 1 : " +
		scoreJ1 + " \n Joueur 2 : " + scoreJ2 + " \n La partie va recommencer.", 
			      "Partie termin�e", JOptionPane.INFORMATION_MESSAGE);
	}
	public void relancerPartie(){
		t.stop();
		//tout remettre en position initiale
		b.setPosition(width/2 - BALLSIZE/2 , height/2);
		j1.setPositionY(height/4 + j1.getHeight());
		j2.setPositionY(height/4 + j2.getHeight());
	
		
	}
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		j1.paint(g);
		j2.paint(g);
		b.paint(g);
		
		g.setColor(Color.WHITE);
		g.drawLine(width/2, 0, width/2, height);
	}

	
	
	public Timer getTimer(){
		return this.t;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		update();
		repaint();
	}
	
	public static int GetScreenWorkingWidth() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	public static int GetScreenWorkingHeight() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}


//---------------------------------------------------
	
/*
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
			//mouvement du joueur qui est à gauche
			if(e.getX() < WIDTH/3){
				int v = e.getY();
				j1.setPositionY(v); 
			
				if(e.getY() < 0){
					j1.setPositionY(0);
				}
				if(e.getY() + j1.getHeight() > HEIGHT){
					j1.setPositionY(HEIGHT - j1.getHeight());
				}
				//mouvement du joueur qui est à droite 
			}else if (e.getX() > (WIDTH/3) *2 ){
				int v = e.getY();
				j2.setPositionY(v); 
				
				if(e.getY() < 0){
					j2.setPositionY(0);
				}
				if(e.getY() + j2.getHeight() > HEIGHT){
					j2.setPositionY(HEIGHT - j2.getHeight());
				}
			}
		}
*/
/*
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
*/

}
