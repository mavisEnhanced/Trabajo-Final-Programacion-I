package juego;
import java.util.ArrayList;
import java.util.Random;



import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	public Entorno entorno;

	// Variables y métodos propios de cada grupo
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState =2;
	
	
	boolean lost = false;
	Jugador jugador;
	Fondo fondo;
	Arbol arbol;
	Tigre t;
	public int alturaSuelo = 560;
	private Random random = new Random();
	public int xAleatoria = random.nextInt(1200,1600);
	public int speed = random.nextInt(2,6);
	
	
	public int puntos=0;
	
	public Tigre tigres[] = new Tigre[5];
	
	public int contadorEnemigos = 0;
	public int contadorEliminados = 0;
	
	
	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Selva Mono Capuchino - Grupo 10 - v1", 1240, 700);
		// Inicializar lo que haga falta para el juego
		
		//Nivel
		fondo = new Fondo(entorno);
		//Creo Nuevo Jugador
		jugador = new Jugador(20,alturaSuelo);
		
		//Enemigos
		
		//Estado de juego
		gameState = playState;
		
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {
		
		// Estado del juego
		if(gameState == playState) {
			// Eventos del juego
			if(entorno.sePresiono(entorno.TECLA_DELETE)) {gameState = pauseState;}
			if(entorno.estaPresionada(entorno.TECLA_DERECHA)) {jugador.dx = 2;jugador.moverDerecha();}
			if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {jugador.dx = 2;jugador.moverIzquierda();}
			if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {jugador.saltando = true;}
			if(entorno.estaPresionada(entorno.TECLA_ENTER)) {jugador.disparar();}

			
			if(contadorEnemigos < 5|| contadorEliminados>3) {
				spawnearTigres();
				contadorEnemigos++;
			}
			
			renderizar(entorno);
			dibujarArboles(entorno);
			colisionConRama(entorno);
			dibujarTigres(entorno);
			piedras(entorno);
			colisionConEnemigo();
		}
	}	
	
	
	// Eventos con enemigos
	// Crar enemigos
	public void spawnearTigres() {
		contadorEliminados = 0;
		tigres[0]= new Tigre(random.nextInt(1200,1600), alturaSuelo, speed);
		tigres[1]= new Tigre(random.nextInt(1200,1600), alturaSuelo, speed);
		tigres[2]= new Tigre(random.nextInt(1200,1600), alturaSuelo, speed);
		tigres[3]= new Tigre(random.nextInt(1200,1600), alturaSuelo, speed);
		tigres[4]= new Tigre(random.nextInt(1200,1600), alturaSuelo, speed);
	}
	// Dibujar los enemigos
	public void dibujarTigres(Entorno e) {
		for(int i = 0 ; i < tigres.length;i++) {
			if(tigres[i]!=null) {
				tigres[i].dibujarTigre(e);
				tigres[i].mover();
			}
		}
	}
	
	// Colision con enemigo
	public boolean colisionConEnemigo() {
		for(int i = 0 ; i < tigres.length;i++) {
			if(tigres[i]!=null) {
				if(jugador.jugadorHitBox().intersects(tigres[i].tigreHitBox()) || 
					jugador.getBounds().intersects(tigres[i].tigreHitBox())) {
					tigres[i]= null;
					contadorEliminados++;
					return true;
				}
			}
		}
		return false;
	}
	
	
	// Eventos con elementos del mapa
	// Dibujar arboles en pantalla
	public void dibujarArboles(Entorno e) {
		for (int i=0; i < fondo.arboles.length;i++) {
			if(fondo.arboles[i]!=null) {
				fondo.arboles[i].dibujarArbol(e);
				fondo.arboles[i].mover();
			}
		}
	}
	
	// Colision con rama del arbol // corregir bugs
	public void colisionConRama(Entorno e) {	
		for (int i = 0; i<fondo.arboles.length;i++) {
			// Colision del jugador con la parte de abajo de la rama
			if(jugador.jugadorHitBox().intersects(fondo.arboles[i].ramaHitBox(e)) 
					&& jugador.saltando==true)
			{System.out.println("colision con rama");jugador.saltando = false;jugador.caer = true;}
			// Colision del jugador con la parte de arriba de la rama
			if(jugador.jugadorHitBox().intersects((fondo.arboles[i].ramaHitBox(e)))) {
				if(jugador.y <= fondo.arboles[i].ramaHitBox(e).y) 
				{jugador.caer = false;System.out.println("colision con rama");}
			}
			// Jugador fuera de la Hitbox de la rama (VALOR X)
			else if(!jugador.jugadorHitBox().intersects(fondo.arboles[i].ramaHitBox(e)) && jugador.saltando == false){
				if(jugador.y <= 560) {jugador.caer = true;}
			}
		}
	}
	

	public void piedras(Entorno e) {
		for(int i =0; i < jugador.piedras.length;i++) {
			if(jugador.piedras[i]!=null) {
				jugador.piedras[i].dibujarPiedra(e);
				jugador.piedras[i].mover();
			}
		}
	}


	// Utilidades // falta terminar
	public void renderizar(Entorno e) {
		if(gameState == pauseState) {
			entorno.escribirTexto("Pausa", 200, 200);
			if(entorno.sePresiono(entorno.TECLA_DELETE)) {gameState=playState;}
		}else {
			fondo.dibujar(entorno);
			fondo.moverFondo();
			jugador.actualizar(e);
		}
	}



	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
