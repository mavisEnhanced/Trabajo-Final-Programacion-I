package juego;

import java.awt.Color;

import java.awt.Rectangle;

import entorno.Entorno;

public class Rama {
	

	int x, y;
	int width;
	int height;
	public Serpiente serpientes[] = new Serpiente[2];
	
	
	public Rama(int x, int y, int width,int height,Entorno e) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height= height;
		spawnearSerpiente(e);
		draw(e);
	
	}
	
	public void dibujarRama(Entorno e) {
		e.dibujarRectangulo(x, y-10, width, height,0,Color.ORANGE);
	}
	
	public Rectangle hitBox() {
		return new Rectangle(x, y-10, width, height);
	}
	
	public void spawnearSerpiente(Entorno e) {
			serpientes[0] = new Serpiente(x, y);
			serpientes[1] = new Serpiente(x, y);
	}
	
	public void draw(Entorno e) {
		for (int i = 0; i < serpientes.length;i++) {
			if(serpientes[i]!=null) {
				serpientes[i].dibujarSerpiente(e);
			}
		}
	}
	
}
