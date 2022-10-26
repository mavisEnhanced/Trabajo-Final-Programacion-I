package juego;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Fondo {
	Entorno entorno;
	Juego juego;
	Image fondo, fondo2,suelo;

	
	public Arbol arboles[] = new Arbol[5];
	
	int x = 620;
	int y = 350;
	
	int x2= 1250;
	int y2= 350;
	
	int suelox= 620;
	
	// Valores para el arbol
	Random r = new Random();
	
	//int arbolGap;
	
	
	public Fondo(Entorno e) {
		arboles(e);
		moverFondo();
		dibujar(e);
	}
	
	//Movimiento del fondo
	public void moverFondo() {
		x -=2;
		x2 -=2;
		if(x == -620) {
			x = 1250;
			
		}
		if(x2== -620) {
			x2= 1250;
		}

	}
	// Crear lista de arboles
	public void arboles(Entorno e) {
							   //x // y 
		arboles[0] = new Arbol(1200,300,e);
		arboles[1] = new Arbol(1400,300,e);
		arboles[2] = new Arbol(1600,300,e);
		arboles[3] = new Arbol(1800,300,e);
		arboles[4] = new Arbol(2000,300,e);
	}
	
	//Dibujar el fondo en pantalla
	public void dibujar(Entorno e) {
		fondo = Herramientas.cargarImagen("fondo.png");
		fondo2 = Herramientas.cargarImagen("fondo2.png");
		suelo = Herramientas.cargarImagen("suelo.png");
		e.dibujarImagen(fondo, 620, 350, 0,1);
		e.dibujarImagen(fondo2,x, y,0,1);
		e.dibujarImagen(fondo2, x2, y2,0,1);
		e.dibujarImagen(suelo,suelox,350, 0,1);
		//e.dibujarImagen(fondo1,x2, y2,0,2.1);
		//e.dibujarImagen(fondo, x, y,0,2.1);
	}
}


	
