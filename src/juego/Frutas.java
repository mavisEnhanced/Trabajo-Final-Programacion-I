package juego;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import entorno.Entorno;

public class Frutas {
	Random random;
	public Image image;
	String name;
	int x;
	int y;
	int width;
	int height;
	
	
	public void dibujar(Entorno e) {
		e.dibujarImagen(image, x, y, 0,2);
	}

	public void mover() {
		x-=2;
	}
	public Rectangle hitbox() {
		return new Rectangle(x,y,width,height);
	}
}
