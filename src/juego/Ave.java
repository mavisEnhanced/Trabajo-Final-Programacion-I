package juego;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

import entorno.Entorno;

public class Ave {

	int x,y,r;
	int width,heihgt;
	Piedra p;
	double angulo = 1;
	
	ArrayList <Piedra>piedras = new ArrayList<>();
	
	public Ave(int x , int y, int r) {
		this.x = x;
		this.y = y;
		this.r = r;
		
	}
	
	public void mover(Entorno e,Juego juego) {
		x -= 3;
		if(x < -100 || y >= 560) {
			juego.ui.vfx(x, y, e);
			x = 1300;
			y = 80;
		}
		
	}
	
	public void ataque1() {
		if(x <=600) {
			this.x -= Math.sin(this.angulo)*10;
			this.y += Math.cos(this.angulo)*10;
		}
	}
	
	public void ataque2() {
		if(x<=600) {
			this.x -= Math.sin(this.angulo)*40;
			this.y += Math.cos(this.angulo)*40;
		}
		
	}
	
	
	public void dibujarAve(Entorno e) {
		e.dibujarRectangulo(x, y, 100, 20, 0, Color.RED);
	}
	
	public Rectangle aveHitbox() {
		return new Rectangle(x,y,100,20);
	}
	
	public void disparar() {
		 p = new Piedra(x, y);
		 piedras.add(p);
		}
	}
