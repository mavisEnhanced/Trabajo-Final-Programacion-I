package juego;

import java.awt.Color;

import java.awt.Rectangle;
import java.util.Random;

import entorno.Entorno;

public class Rama {
	

	int x, y;
	int width;
	int height;
	
	public Serpiente serpientes[] = new Serpiente[1];
	
	public Rama(int x, int y, int width,int height,Entorno e) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height= height;
		spawnearSerpiente(e);
	
	}
	
	public void dibujarRama(Entorno e) {
		e.dibujarRectangulo(x, y-10, width, height,0,Color.ORANGE);
	}
	
	public Rectangle hitBox() {
		return new Rectangle(x, y-10, width, height);
	}
	
	public void spawnearSerpiente(Entorno e) {
			serpientes[0] = new Serpiente(x, y);
			draw(e);
	}
	
	public void draw(Entorno e) {
		serpientes[0].dibujarSerpiente(e);
	}
		
}
