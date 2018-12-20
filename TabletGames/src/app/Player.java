package app;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	final int MARGE = 40;
	int width = this.GetScreenWorkingWidth();
	int height = this.GetScreenWorkingHeight();
	private  int joueur;
	private int widthBarre = 30;
	private int heightBarre = 200;
	private int x;
	private int y = height/4 + heightBarre;
	
	public Player(int joueur){
		this.joueur = joueur;
	}
	
	public int getidJoueur(){
		return this.joueur;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getWidth(){
		return this.widthBarre;
	}
	
	public int getHeight(){
		return this.heightBarre;
	}
	
	public void update(){
		//y = y+Vy;
	} 
	
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		
		if(joueur == 1){
			x = MARGE;
			g.fillRect(x, y, widthBarre, heightBarre);
		}else if(joueur == 2){
			x = width - MARGE - widthBarre;
			g.fillRect(x, y, widthBarre, heightBarre);
		}
		//mur de droite pour le mode 1J gaucher
		else if(joueur == 3){
			x = width - MARGE - widthBarre;
			g.fillRect(x, 0, widthBarre, height);
		}
		//mur de gauche pour le mode 1J Droitier
		else if(joueur == 4){
			x = MARGE;
			g.fillRect(x, 0, widthBarre, height);
		}
		
	}
	
	public void setPositionY(int y){
		this.y = y;
	}
	
	public static int GetScreenWorkingWidth() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	public static int GetScreenWorkingHeight() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}
}
