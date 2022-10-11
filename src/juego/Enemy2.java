package juego;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

import entorno.Entorno;

public class Enemy2 {
	
	public int x = 1240;
	public int y = 100;
	public double  angulo;
	
	Random random = new Random();
	boolean isAlive = true;
	
	public Enemy2(int x,int y,int angulo) {
		this.x = x;
		this.y = y;
	}
	
	public void girar(double modificador) {
		if(x<=600)
			this.angulo = this.angulo + modificador;
	}
	
	public void dibujarEnemy(Entorno e) {
		if(isAlive)
			e.dibujarTriangulo(x, y, 60, 20, this.angulo, Color.yellow);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x , y ,40,40 );
	}
	public void move() {	
		this.x -= Math.cos(this.angulo)*20;;
		if( x <= 600)
			y += Math.sin(this.angulo)*20;
	}
}
	

