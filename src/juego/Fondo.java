package juego;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Fondo {
	Entorno entorno;
	Juego juego;
	Image fondo;
	Image fondo1;
	Image fondo2;
	Image suelo;

	
	public Arbol arboles[] = new Arbol[5];
	
	int x = 1900;
	int y = -100;
	
	int x2= 500;
	int y2= -100;
	
	int suelox=40;
	
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
		if(x == -700) {
			x = 1800;
			
		}
		if(x2== -700) {
			x2= 1800;
		}

	}
	// Crear lista de arboles
	public void arboles(Entorno e) {
							   //x // y //Escala
		arboles[0] = new Arbol(1200,400,e,r.nextDouble(0.5,1.2));
		arboles[1] = new Arbol(1400,400,e,r.nextDouble(0.5,1.2));
		arboles[2] = new Arbol(1600,400,e,r.nextDouble(0.5,1.2));
		arboles[3] = new Arbol(1800,400,e,r.nextDouble(0.5,1.2));
		arboles[4] = new Arbol(2000,400,e,r.nextDouble(0.5,1.2));
	}
	
	//Dibujar el fondo en pantalla
	public void dibujar(Entorno e) {
		fondo = Herramientas.cargarImagen("Layer_0006_4.png");
		fondo1 = Herramientas.cargarImagen("Layer_0003_6.png");
		fondo2= Herramientas.cargarImagen("jgbk.png");
		suelo = Herramientas.cargarImagen("Spring3Long1_0.png");
		//e.dibujarImagen(fondo2, 600, 200, 0, 3);
		//e.dibujarImagen(fondo1,x, y,0,2.1);
		//e.dibujarImagen(fondo, x2, y2,0,2.1);
		//e.dibujarImagen(fondo1,x2, y2,0,2.1);
		//e.dibujarImagen(fondo, x, y,0,2.1);
		//e.dibujarImagen(suelo,suelox,700, 0, 0.3);
	}
}


	
