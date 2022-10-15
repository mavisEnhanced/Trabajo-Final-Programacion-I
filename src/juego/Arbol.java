package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Arbol {
	
	Image img;
	private Random random = new Random();
	
	//Posicion del arbol
	int x,y;

	public Arbol(int x, int y, Entorno e) {
		this.x = x;
		this.y = y;
		dibujarArbol(e);
	}
	
	// Dibujar el arbol en pantalla
	public void dibujarArbol(Entorno e) {
		img = Herramientas.cargarImagen("arbolpng.png");
		int height = img.getHeight(e)/2;
		e.dibujarImagen(img,x,y-height,0,0.5);
		rama(e);
	}
	
	public void mover() {
		x-= 2;
		if(x <= -150) {
			x = 1240 + random.nextInt(300,500);
		}
	}
	
	
	public void rama(Entorno e) {
		int height= img.getHeight(e)/2;
		e.dibujarRectangulo(x, y-height,200,10,0,Color.YELLOW);
		ramaHitBox(e);
	}
	
	public Rectangle ramaHitBox(Entorno e) {
		int height= img.getHeight(e)/2;
		Rectangle h = new Rectangle(x-100,y-height,200,10);
			return h;
	}


	
}
