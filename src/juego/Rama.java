package juego;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Rama {
	
	Image rama;
	int x, y;
	int width;
	int height;
	public Serpiente serpientes[] = new Serpiente[1];
	Random random= new Random();
	int r = random.nextInt(2);
	
	public Rama(int x, int y, int width,int height,Entorno e,Juego juego) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height= height;
		spawnearSerpiente(e);
		colisionConSerpiente(e,juego);
		piedrasSerpiente(juego);
	}
	
	public void dibujarRama(Entorno e) {
		//e.dibujarRectangulo(x , y-5, width, height,0,Color.ORANGE);
		rama = Herramientas.cargarImagen("rama.png");
		e.dibujarImagen(rama, x, y, 0,3);
		dibujarSerpiente(e);
	}
	
	public Rectangle hitBox() {
		return new Rectangle(x, y-5, width, height+20);
	}
	
	public void spawnearSerpiente(Entorno e) {
		serpientes[0] = new Serpiente(x, y);
	}
	
	
	public void dibujarSerpiente(Entorno e) {
		if(serpientes[0] != null) {
			serpientes[0].dibujarSerpiente(e);
		}
	}
	
	public void colisionConSerpiente(Entorno e,Juego juego) {
		if(serpientes[0]!=null) {
				if((juego.jugador.jugadorHitBox().intersects(serpientes[0].serpienteHitBox()))) {
					serpientes[0] = null;
					System.out.println("col");
				}
			}
		}
	
	public boolean piedrasSerpiente(Juego juego) {
		if(juego.jugador.piedras[0]!=null) {
			if(juego.jugador.piedras[0].x >= 1300) {
				juego.jugador.piedras[0] = null;
				juego.jugador.puedeDisparar=true;
			}
		}
		if(serpientes[0]!=null && juego.jugador.piedras[0]!=null) {
			if(serpientes[0].serpienteHitBox().intersects(juego.jugador.piedras[0].piedraHitbox())) {
				serpientes[0] = null;
				juego.jugador.piedras[0]=null;
				juego.jugador.puedeDisparar = true;
				juego.ui.puntos+=2;
				juego.ui.addMessage("+2");
				return true;
			}
		}
		return false;
	}
	}
