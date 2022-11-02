package juego;

import java.awt.*;

import entorno.Entorno;

public class Piedra {

	int x,y;
	private int speed = 5;
	Image img;

	public Piedra (int startX, int startY) {
		this.x = startX;
		this.y = startY;
	}
	
	public void mover() {
		x = x + speed;
	}
	
	public void dibujarPiedra(Entorno e) {
		e.dibujarCirculo( x, y, 20,Color.BLUE);
	}
	
	public Rectangle piedraHitbox() {
		return new Rectangle(x, y, 20, 20);
	}

}
