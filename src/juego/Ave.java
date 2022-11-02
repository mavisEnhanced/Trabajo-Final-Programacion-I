package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Ave {

	Image aguila;
	int x,y,r;
	int width,heihgt;
	double angulo = 1;
	Random random = new Random();

	public Ave(int x , int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
		
	}
	
	public void mover(Entorno e,Juego juego) {
		x -= 3;
		if(x < -100 || y >= 560) {
			x = 1300;
			y = 80;
		}
		
	}
	
	public void ataque1() {
		if(x <= 400) {
			this.x -= Math.sin(this.angulo)*10;
			this.y += Math.cos(this.angulo)*10;
		}
	}
	
	public void ataque2() {
		if(x<=600) {
			this.x -= Math.sin(this.angulo)*20;
			this.y += Math.cos(this.angulo)*20;
		}
		
	}

	public void dibujarAve(Entorno e) {
		aguila = Herramientas.cargarImagen("aguila.gif");
		//e.dibujarRectangulo(x, y, 100, 30, 0, Color.RED);
		e.dibujarImagen(aguila, x, y, 0,2);
	}
	
	public Rectangle aveHitbox() {
		return new Rectangle(x,y,100,30);
	}

}
