package juego;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

import entorno.Entorno;

public class Enemy {
	
	//POSICION DE SPAWN
	public int x = 1240;
	public int y = 560;
	
	Random random = new Random();

	boolean isAlive = true;
	
	public Enemy(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public void dibujarEnemy(Entorno e) {
		if(isAlive)
			e.dibujarCirculo(x, y,30, Color.CYAN);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x , y ,40,40 );
	}
	
	public void move() {	
		x = x - 3;
		if(isAlive && x <=-100) {
			x = 1240;
			y = 560; 
		}
		
		else if(x <= 500) {
			x = x - 4;
		}
	}
}
