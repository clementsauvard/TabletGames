package app;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	
	final int BALLSIZE = 40, MARGE_ERREUR = 25;
	
	int width = this.GetScreenWorkingWidth();
	int height = this.GetScreenWorkingHeight();
	
	private int x = 600;
	private int y = 350;
	
	private int Vx = -22;
	private int Vy = -22;
	
	private int configuration;
	/* configuration : 
	 * 1  = mode un joueur Droitier
	 * 2 = mode un joueur Gaucher
	 * 3 = mode Deux joueurs
	 */
	
	public Ball(int config){
		this.configuration = config;
	}
	
	public void changerDirectionX(){
		Vx = -Vx;
	}
	public void changerDirectionY(){
		Vy = -Vy;
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void update(){
		x = x + Vx;
		y = y + Vy;
		
		//mode un joueur droitier
		if(configuration == 1) {
			if(x < 40 + 30){
				changerDirectionX();
			}
			
			if(y < 0){
				changerDirectionY();
			}else if( y > height - BALLSIZE - MARGE_ERREUR){
				changerDirectionY();
			}
		}
		//mode un joueur gaucher
		else if (configuration == 2){
			 /*if(x < 0){
				changerDirectionX();	
			}else if(x > WIDTH - BALLSIZE){
				changerDirectionX();
			}	*/
			if(x > width - 30 - 40 -BALLSIZE){
				changerDirectionX();
			}
			
			if(y < 0){
				changerDirectionY();
			}else if( y > height - BALLSIZE - MARGE_ERREUR){
				changerDirectionY();
			}
		}
		//mode deux joueurs
		else if(configuration == 3){
				
			if(y < 0){
				changerDirectionY();
			}else if( y > height - BALLSIZE - MARGE_ERREUR){
				changerDirectionY();
			}
		}	
	}
	
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		g.fillOval(x, y, BALLSIZE, BALLSIZE);
	}
	
	
	public int getX(){
		return this.x;
	}
	public void checkCollisionXJoueur1(Player p ){
		
		if(this.x > p.getX() && this.x < p.getX() + p.getWidth()){
			if(this.y > p.getY() && this.y < p.getY() + p.getHeight()){
				//la balle est entrée en collision avec la barre du joueur 1 en X
				System.out.println("collision entre la balle et le joueur " + p.getidJoueur() + "en X");
				changerDirectionX();
			}
		}
	}
	
	public void checkCollisionYJoueur1(Player p ){
		
		if(this.x > p.getX() && this.x < p.getX() + p.getWidth()){
			if(this.y > p.getY() && this.y < p.getY() + p.getHeight()){
				//la balle est entrée en collision avec la barre du joueur 1 en Y
				System.out.println("collision entre la balle et le joueur " + p.getidJoueur() + "en Y");
				changerDirectionX();
			}
		}
	}
	
	public void checkCollisionXJoueur2(Player p ){
		
		if(this.x > p.getX() - p.getWidth() - MARGE_ERREUR){
			if(this.y > p.getY() && this.y < p.getY() + p.getHeight()){
				//la balle est entrée en collision avec la barre du joueur 2 en X 
				System.out.println("collision entre la balle et le joueur " + p.getidJoueur() +  "en X");
				changerDirectionX();
			}
		}
	}
	
public void checkCollisionYJoueur2(Player p ){
		
		if(this.x > p.getX() && this.x < p.getX() + p.getWidth()){
			if(this.y > p.getY() && this.y < p.getY() + p.getHeight()){
				//la balle est entrée en collision avec la barre du joueur 2 en Y
				System.out.println("collision entre la balle et le joueur " + p.getidJoueur() + "en Y");
				changerDirectionX();
			}
		}
	}

public static int GetScreenWorkingWidth() {
    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
}

public static int GetScreenWorkingHeight() {
    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
}
}
