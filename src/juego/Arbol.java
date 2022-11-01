package juego;

import java.awt.Image;

import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Arbol {
	
	Image arbol;
	Rama rama;
	
	int x,y;
	int width = 200;
	int height= 400;
	double scale;
	Random r = new Random();
	int ramaY = r.nextInt(50,350);
	
	
	public Arbol(int x, int y, Entorno e,Juego juego) {
		this.x = x;
		this.y = y;
		rama(e,juego);
	}
	
	// Dibujar el arbol en pantalla
	public void dibujarArbol(Entorno e,Juego juego) {
		arbol = Herramientas.cargarImagen("arbol.png");
		e.dibujarImagen(arbol,x, y + (height/4) ,0,4);
	}
	
	public void mover() {
		int gap = 600; 
		x-= 5;
		if(x <= -150) {
			x = 1240 + gap;
			ramaY = r.nextInt(100,450);
		}

	}
	
	public void rama(Entorno e,Juego juego) {
		rama = new Rama(this.x,ramaY,100,20,e,juego);
		rama.dibujarRama(e);
		
	}

}
