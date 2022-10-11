package juego;

import java.awt.Color;

import entorno.Entorno;
import entorno.Herramientas;

public class Arbol {

	int x,y;
	int width,height;
	
	public Arbol(int x, int y) {
		this.x = x;
		this.y = y;
		width = 20;
		height = 100;
		
	}
	
	public void draw(Entorno e) {
		e.dibujarRectangulo(x, y,width, height,0,Color.green);
	}
	
}
