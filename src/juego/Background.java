package juego;
import java.awt.Image;
import java.util.ArrayList;

import entorno.Entorno;
import entorno.Herramientas;

public class Background {
	Juego juego;
	Image fondo;
	Arbol arbol;
	public ArrayList<Arbol> arboles = new ArrayList<>();
	
	int width =1240;
	int height=600;
	
	int x=1300;
	int y =0;
	
	int arbolX = 1300;
	int arbolY = 560;
	int x2=0;
	int y2=0;
	Entorno entorno;
	public Background() {
	}
	public void move() {
		arbolX-=2;
		x -=1;
		x2 -=1;
		if(x ==0 && x2 ==-1240) {
			x=1240;
			x2=0;
		}
		if(arbolX ==0) {
			arbolX = 1300;
		}
	}
	
	public void draw(Entorno e) {
		fondo = Herramientas.cargarImagen("crop.jpg");
		e.dibujarImagen(fondo, x, y,0,1);
		e.dibujarImagen(fondo, x2, y2,0,1);
		arbol = new Arbol(arbolX, arbolY);
		arbol.draw(e);
	}
}

	
