package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Arbol {
	Image arbol;

	Random r = new Random();
	
	int arbolGap = 500;
	//Posicion del arbol
	int x,y;
	double scale;
	//Rama 
	int width = 150;
	int height= 10;
	
	
	public Serpiente serpientes[] = new Serpiente[5];
	
	
	public Arbol(int x, int y, Entorno e,double scale) {
		this.x = x;
		this.y = y;
		dibujarArbol(e);
		this.scale = scale;
	}
	
	// Dibujar el arbol en pantalla
	public void dibujarArbol(Entorno e) {
		arbol = Herramientas.cargarImagen("arbolpng.png");
		int height = arbol.getHeight(e)/4;
		e.dibujarImagen(arbol,x,y-height,0,scale);
		rama(e);
		spawnearSerpientes(e);
		dibujarSerpientes(e);
	}
	
	public void mover() {
		x-= 5;
		if(x <= -150) {
			x = 1200 + r.nextInt(700,900);
			y = 560;
		}
		else if(x <=-150 & scale >=0.8) {
			x = 1400 + r.nextInt(900,1300);
			y = 200;

		}

	}
	
	public void rama(Entorno e) {
		e.dibujarRectangulo(this.x,this.y-arbol.getHeight(e)/4,width,height,0,Color.YELLOW);
		ramaHitBox(e);
	}
	public void spawnearSerpientes(Entorno e) {
		serpientes[0]= new Serpiente(this.x,ramaHitBox(e).y);
		serpientes[1]= new Serpiente(this.x,ramaHitBox(e).y);
		serpientes[2]= new Serpiente(this.x,ramaHitBox(e).y);
		serpientes[3]= new Serpiente(this.x,ramaHitBox(e).y);
		serpientes[4]= new Serpiente(this.x,ramaHitBox(e).y);
	}
	public Rectangle ramaHitBox(Entorno e) {
		Rectangle h = new Rectangle(this.x-75,this.y-arbol.getHeight(e)/4,width,height);
		//e.dibujarRectangulo(this.x,this.y-arbol.getHeight(e)/4-20,width,height,0,Color.red);	
		return h;
	}
	
	public int getramaY() {
		return this.y-arbol.getHeight(null)/4-20;
	}
	
	public void dibujarSerpientes(Entorno e) {
		for (int i =0;i<serpientes.length;i++) {
			if(serpientes[i]!=null) {
				serpientes[i].dibujarSerpiente(e);
			}
		}
	}
	
}
