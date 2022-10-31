package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

import entorno.Entorno;
import entorno.Herramientas;

public class Rama {
	
	Image rama;
	int x, y;
	int width;
	int height;
	//public Serpiente serpientes[] = new Serpiente[1];
	
	public Rama(int x, int y, int width,int height,Entorno e) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height= height;
		//spawnearSerpiente(e);
	}
	
	public void dibujarRama(Entorno e) {
		e.dibujarRectangulo(x, y-10, width, height,0,Color.ORANGE);
		rama = Herramientas.cargarImagen("rama.png");
		e.dibujarImagen(rama, x, y, 0,2);
	}
	
	public Rectangle hitBox() {
		return new Rectangle(x, y-10, width, height);
	}
	
//	public void spawnearSerpiente(Entorno e) {
//		serpientes[0] = new Serpiente(x, y);
//	}
	
}
