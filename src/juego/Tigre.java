package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;


public class Tigre {
	Entorno e;
	Image tigre =Herramientas.cargarImagen("tigre.gif");
	//Posicion de spawn y velocidad de movimiento
	public int x;
	public int y;
	public int speed;
	public int width= tigre.getWidth(e);
	public int height= tigre.getHeight(e);
	
	Random random = new Random();

	public Tigre(int x,int y, int speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	public void dibujarTigre(Entorno e) {
			//e.dibujarRectangulo(x, y,width ,height,0, Color.RED);
			e.dibujarImagen(tigre, x, y, 0,2.5);
	}
	
	public void mover(Entorno e) {	
		x = x - speed;
		if( x <=-100) {
			x = random.nextInt(1300,1600);
			y = 560; 
		}
		// Cuando llegue a maso menos la mitad, correra 
		else if( x <= 700) {
			x = x - speed * random.nextInt(1,4);
		}
	}
	
	public Rectangle tigreHitBox() {
		return new Rectangle(x , y , width, height);
	}
	
}
