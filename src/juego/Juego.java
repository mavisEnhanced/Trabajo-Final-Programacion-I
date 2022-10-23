package juego;
import java.awt.Graphics2D;
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
	UI ui;
	Graphics2D g2;
	public int alturaSuelo = 560;
	private Random random = new Random();
	public int xAleatoria = random.nextInt(1200,1600);
	
	
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
		ui = new UI(entorno);
		//Enemigos
		
		//Estado de juego
		gameState = titleState;
		
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
			colisionConRama(entorno);
			colisionConEnemigo();
			piedras();
			//colisionConSerpiente();
			ui.drawMessage(entorno);
		}
		
		
		if(gameState==titleState) {
			ui.draw(entorno);
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				if(ui.commandNum==0) {
					gameState=playState;
				}
				if (ui.commandNum==2) {
					System.exit(0);
				}
			}
			if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {
				ui.commandNum--;
				if(ui.commandNum < 0) {
					ui.commandNum = 2;
				}
			}
			if(entorno.sePresiono(entorno.TECLA_ABAJO)) {
				ui.commandNum++;
				if(ui.commandNum > 2) {
					ui.commandNum = 0;
				}
			}
	
		}
			
	}	
	
	
	// Eventos con enemigos
	// Crar enemigos
	public void spawnearTigres() {
		contadorEliminados = 0;
		tigres[0]= new Tigre(random.nextInt(1200,1600), alturaSuelo, random.nextInt(2,4));
		tigres[1]= new Tigre(random.nextInt(1200,1600), alturaSuelo, random.nextInt(2,4));
		tigres[2]= new Tigre(random.nextInt(1200,1600), alturaSuelo, random.nextInt(2,4));
		tigres[3]= new Tigre(random.nextInt(1200,1600), alturaSuelo, random.nextInt(2,4));
		tigres[4]= new Tigre(random.nextInt(1200,1600), alturaSuelo, random.nextInt(2,4));
	}
	// Dibujar los enemigos
	public void dibujarTigres(Entorno e) {
		for(int i = 0 ; i < tigres.length;i++) {
			if(tigres[i]!=null) {
				tigres[i].dibujarTigre(e);
				tigres[i].mover(e);
			}
		}
	}
	
	
	// Colision con enemigo
	public boolean colisionConEnemigo() {
		for(int i = 0 ; i < tigres.length;i++) {
			if(tigres[i]!=null) {
				if(tigres[i].tigreHitBox().intersects(jugador.jugadorHitBox())) {
					tigres[i] = null;
					ui.puntos++;
					ui.addMessage("+1");
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
		for (int i = 0; i < fondo.arboles.length;i++) {
			if(fondo.arboles[i]!=null) {
				// Colision del jugador con la parte de abajo de la rama
				if(jugador.jugadorHitBox().intersects(fondo.arboles[i].rama.hitBox()) 
						&& jugador.saltando==true) {jugador.saltando = false;jugador.caer = true;}
				// Colision del jugador con la parte de arriba de la rama
				if(fondo.arboles[i].rama.hitBox().intersects(jugador.jugadorHitBox()) && 
					jugador.jugadorHitBox().y <= fondo.arboles[i].rama.hitBox().y) {
						jugador.saltando=false;
						jugador.caer = false;
						if(jugador.x + jugador.width > fondo.arboles[i].rama.x + fondo.arboles[i].rama.width ) {jugador.caer=true;}
				}
			}
		}
	}
	
	public void dibujarPiedras(Entorno e) {
		if(jugador.piedras[0]!=null) {
			jugador.piedras[0].dibujarPiedra(e);
			jugador.piedras[0].mover();
		}
		
	}
	
	public boolean piedras() {
		
		if(jugador.piedras[0]!=null) {
			if(jugador.piedras[0].x >= 1300) {
				jugador.piedras[0]=null;
				jugador.puedeDisparar=true;
			}
		}
		for(int i = 0 ; i < tigres.length;i++) {
			if(tigres[i]!=null && jugador.piedras[0]!=null) {
				if(tigres[i].tigreHitBox().intersects(jugador.piedras[0].piedraHitbox())) {
					tigres[i] = null;
					jugador.piedras[0]=null;
					jugador.puedeDisparar=true;
					ui.puntos++;
					ui.addMessage("+1");
					contadorEliminados++;
					return true;
				}
			}
		}
		return false;
	}

	// Utilidades 
	public void renderizar(Entorno e) {
		fondo.dibujar(entorno);
		dibujarArboles(entorno);
		dibujarTigres(entorno);
		dibujarPiedras(e);
		fondo.moverFondo();
		jugador.actualizar(e);
		ui.hud(e);	
	}



	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
