package juego;

import java.awt.Image;

import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Arbol {
	
	Image arbol;
	Rama rama;
	
	//ARBOL VALUES
	int x,y;
	int width = 200;
	int height= 400;
	double scale;
	Random r = new Random();
	int ramaY = r.nextInt(100,500);
	
	
	public Arbol(int x, int y, Entorno e) {
		this.x = x;
		this.y = y;
		dibujarArbol(e);
	}
	
	// Dibujar el arbol en pantalla
	public void dibujarArbol(Entorno e) {
		arbol = Herramientas.cargarImagen("arbol.png");
		////////////
		e.dibujarImagen(arbol,x, y + (height/4) ,0,4);
		rama(e);
	}
	
	public void mover() {
		int gap = 1240; 
		x-= 5;
		if(x <= -150) {
			x = gap + r.nextInt(300,600);
			rama.y = r.nextInt(50,350);
			gap+=200;
		}
		if (gap >= 300) {
			gap = 1240;
		}

	}
	
	public void rama(Entorno e) {
		rama = new Rama(this.x,ramaY,width/2,10,e);
		rama.dibujarRama(e);
	}
	
	
	
	
	
}
