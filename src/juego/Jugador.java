package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

import entorno.Entorno;
import entorno.Herramientas;

public class Jugador  {
	
	Juego juego;
	Entorno e;
	
	public int x, y, dx, dy;
	
	public boolean saltando = false;
	public boolean caer = false;
	public double velocidadSalto = 8;
	public double actualVelocidadSalto = velocidadSalto;
	public double maxVelocidadCaida = 5;
	public double actualVelocidadCaida = .1;
	
	Piedra p;
	public Piedra piedras[] = new Piedra[1];
	public int width = 32;
	public int height = 32;
	public boolean puedeDisparar= true;
	public int vidas = 3;
	
	
	public Jugador(int x , int y){
		this.x = x;
		this.y = y;
	}
	public void actualizar(Entorno e) {
		moverDerecha();
		moverIzquierda();
		saltar();
		dibujarJugador(e);
	}
	

	public void moverDerecha() {
		this.x += dx;
	}
	
	public void moverIzquierda() {
		this.x -=dx;
	}
	
	public void saltar() {
		if (saltando) {
			y-= actualVelocidadSalto*2;
			actualVelocidadSalto -= .2;
			if(actualVelocidadSalto <=0) {
				actualVelocidadSalto = velocidadSalto;
				saltando = false;
				caer = true;
			}
		}
		if (caer) {
			y+= actualVelocidadCaida*2;
			if(actualVelocidadCaida < maxVelocidadCaida) {
				actualVelocidadCaida += .1;
			}
			if (y >= 560)
				caer = false;
		}
		if(!caer) {
			actualVelocidadCaida = .2;
		}
	}
	
	public Rectangle jugadorHitBox() {
		return new Rectangle(this.x,this.y,width,height);
	}

	public void disparar() {
		if(puedeDisparar) {
			Piedra p = new Piedra(x,y);
			piedras[0]=p;
			
		}
		puedeDisparar = false;
	}
	
	public void dibujarJugador(Entorno e) {
		//jugador = Herramientas.cargarImagen("upKey.gif");
		e.dibujarRectangulo(x-8, y, width,height,0,Color.magenta);
		//e.dibujarImagen(jugador, x-(width/4), y-40, 0,1);
	}

	
	
	
}
