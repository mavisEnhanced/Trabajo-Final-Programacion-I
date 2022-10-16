package juego;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Serpiente {

	
	Image serpiente;
	//Posicion de spawn y velocidad de movimiento
	public int x;
	public int y;
	public int speed;
	
	Random random = new Random();
	boolean estaVivo = true;

	public Serpiente(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public void dibujarSerpiente(Entorno e) {
		serpiente = Herramientas.cargarImagen("snake.png");
		if(estaVivo)
			//e.dibujarRectangulo(x, y, 40 ,40,0, Color.RED);
			e.dibujarImagen(serpiente, x, y, 0, 0.1);
	}
	
	public Rectangle serpienteHitBox() {
		return new Rectangle(x , y , 40, 40);
	}
	
	
	
	
}
