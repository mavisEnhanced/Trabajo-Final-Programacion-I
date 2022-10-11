package juego;

import java.awt.*;

import entorno.Entorno;

public class Bullet {

	int x,y;
	boolean visible = true;
	private int speed = 2;
	Image img;
	
	
	public Bullet (int startX, int startY) {
		this.x = startX;
		this.y = startY;
		visible= true;
	}
	
	public void tick() {
		x = x + speed;
		if(x>1240) {
			visible = false;
		}
	}
	public void render(Entorno e) {
		e.dibujarCirculo(x, y, 15,Color.red);
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
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,15,15);
	}
}
