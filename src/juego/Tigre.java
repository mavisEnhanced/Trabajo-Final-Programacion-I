package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import entorno.Entorno;


public class Tigre {
	Image tigre;
	//Posicion de spawn y velocidad de movimiento
	public int x;
	public int y;
	public int speed;
	
	Random random = new Random();
	boolean estaVivo = true;

	public Tigre(int x,int y, int speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	public void dibujarTigre(Entorno e) {
		
		if(estaVivo)
			e.dibujarRectangulo(x, y, 100,50,0, Color.RED);
			
	}
	
	public void mover() {	
		x = x - speed;
		if(estaVivo && x <=-100) {
			x = random.nextInt(1300,1600);
			y = 560; 
		}
		
		// Cuando llegue a maso menos la mitad, correra 
		else if(estaVivo && x <= 700) {
			x = x - speed * random.nextInt(1,4);
		}
	}
	
	public Rectangle tigreHitBox() {
		return new Rectangle(x , y , 100, 50);
	}
	
}
