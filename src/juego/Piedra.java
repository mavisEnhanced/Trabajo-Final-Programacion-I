package juego;

import java.awt.*;

import entorno.Entorno;

public class Piedra {

	int x,y;
	boolean visible = true;
	private int speed = 5;
	Image img;
	private double angulo;
	
	
	public Piedra (int startX, int startY) {
		this.x = startX;
		this.y = startY;
		visible = true;
	}
	
	public void mover() {
		x = x + speed;
		if(x > 1240) {
			visible = false;
		}
	}
	

	
	public void dibujarPiedra(Entorno e) {
		e.dibujarCirculo( x, y, 20,Color.BLUE);
	}
	

	public void girar(double modificador) 
	{
		this.angulo = this.angulo + modificador;
		if(this.angulo < 0) {
			this.angulo +=2*Math.PI;
		}
        if(this.angulo > 2*Math.PI) {
        	this.angulo -=2*Math.PI;
        }
			
	}
	
	public void moverAdelante() {
		this.y+=2;
		if (this.y > 300) {
			this.x -= Math.cos(this.angulo)*5;
		}
	}
	

	public Rectangle piedraHitbox() {
		return new Rectangle(x, y, 20, 20);
	}

	
	
	
}
