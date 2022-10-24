package juego;

import java.awt.Color;
import java.util.ArrayList;

import entorno.Entorno;

public class Ave {

	int x,y;
	int width,heihgt;
	Piedra p;
	
	
	ArrayList <Piedra>piedras = new ArrayList<>();
	
	public Ave(int x , int y) {
		
		this.x = x;
		this.y = y;
	}
	
	public void actualizar(Entorno e) {
		mover(e);
		dibujarAve(e);
		
	}
	public void mover(Entorno e) {
		x -= 1;
		if(x < -100) {
			x = 1300;
		}
		
	}
	
	public void dibujarAve(Entorno e) {
		e.dibujarRectangulo(x, y, 100, 20, 0, Color.RED);
	}
	
	public void disparar() {
		 p = new Piedra(x, y);
		 piedras.add(p);
		}
	}
