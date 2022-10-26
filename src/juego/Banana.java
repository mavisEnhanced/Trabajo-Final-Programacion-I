package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Banana {

	
	Image banana;
	int x , y;
	
	public Banana(int x, int y) {
		this.x = x;
		this.y =y;
	}
	
	
	public void dibuajarBanana(Entorno e ) {
		
		banana= Herramientas.cargarImagen("Banana_Peeled.png");
		e.dibujarImagen(banana, x, y, 0, 1);
		
	}
	
	
}
