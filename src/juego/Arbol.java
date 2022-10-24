package juego;

import java.awt.Color;
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
	int arbolGap = 500;
	
	
	Random r = new Random();
	
	public Arbol(int x, int y, Entorno e,double scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
		dibujarArbol(e);
	}
	
	// Dibujar el arbol en pantalla
	public void dibujarArbol(Entorno e) {
		arbol = Herramientas.cargarImagen("arbolpng.png");
		//PARA DEBUG
		e.dibujarRectangulo(x, y, width, height,0, Color.WHITE);
		////////////
		e.dibujarImagen(arbol, x , y+(height/8) , 0,scale);
		rama(e);
	}
	
	public void mover() {
		x-= 5;
		if(x <= -150) {
			x = 1200 + r.nextInt(1000,1500);
			scale= r.nextDouble(0.5,1.2);
			y = 560-(height/2);
		}
		else if(x <=-150 & scale >=0.8) {
			x = 1400 + r.nextInt(900,1300);
			scale= r.nextDouble(0.5,1.2);
			y = 560-(height/2);
		}
	}
	
	public void rama(Entorno e) {
		rama = new Rama(this.x,this.y,width/2,10,e);
		rama.dibujarRama(e);
	}
	
	
	
	
	
}
