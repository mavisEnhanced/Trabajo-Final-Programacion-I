package juego;

import java.awt.*;

import entorno.Entorno;

public class Piedra {

	int x,y;
	boolean visible = true;
	private int speed = 2;
	Image img;
	
	
	public Piedra (int startX, int startY) {
		this.x = startX;
		this.y = startY;
		visible= true;
	}
	
	public void mover() {
		x = x + speed;
		if(x>1240) {
			visible = false;
		}
	}
	public void dibujarPiedra(Entorno e) {
		e.dibujarCirculo(x, y, 10,Color.yellow);
	}

	public Rectangle piedraHitbox() {
		return new Rectangle(x, y, 10, 10);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean getVisible() {
		return visible;
	}
	
	
	
}
