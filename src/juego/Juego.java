package juego;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;



import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	public Entorno entorno;

	// Variables y métodos propios de cada grupo
	public int gameState;
	public final int titleState = 0;
	public final int tutorialState=1;
	public final int playState = 2;
	public final int pauseState =3;
	public final int optionsState=4;
	public final int validateState=5;
	public final int gameOver=6;
	boolean tutorial = true;

	Jugador jugador;
	Fondo fondo;
	Arbol arbol;
	UI ui;
	Graphics2D g2;
	public int alturaSuelo = 560;
	private Random random = new Random();
	public int xAleatoria = random.nextInt(1200,1600);
	
	
	public Tigre tigres[] = new Tigre[5];
	public Ave aves[] = new Ave[2];
	public ArrayList<Piedra> bullets = new ArrayList<>();
	
	
	Ave ave;
	public int contadorEnemigos = 0;
	public int contadorEliminados = 0;
	public int radius= 1;
	public int maxRadius= 1200;
	double angle = 10;

	
	Sound sound;
	
	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Selva Mono Capuchino - Grupo 10 - v1", 1240, 700);
		// Inicializar lo que haga falta para el juego
		//Nivel
		fondo = new Fondo(entorno);
		//Creo Nuevo Jugador
		jugador = new Jugador(50,alturaSuelo);
		ui = new UI(entorno,this);
		//Estado de juego
		gameState = titleState;
		sound = new Sound();
		//playMusic(0);
		
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
		
		//MAIN MENU
		if(gameState==titleState) {
			ui.draw(entorno);
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				if(ui.commandNum==0) {gameState=playState;}
				if (ui.commandNum==2) {System.exit(0);}
			}
			if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {ui.commandNum--;
			if(ui.commandNum < 0) {ui.commandNum = 2;}}
			if(entorno.sePresiono(entorno.TECLA_ABAJO)) {ui.commandNum++;if(ui.commandNum > 2) {ui.commandNum = 0;}}
		}
		
		if(gameState== tutorialState) {
			//ui.tutorial()
		}
		
		//PAUSE MENU
		if(gameState == pauseState) {
			
			ui.pauseMenu(entorno);
			if(entorno.sePresiono(entorno.TECLA_ESPACIO) && gameState == pauseState) {gameState = playState;}
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				if(ui.commandNum==0) {gameState=playState;}
				if(ui.commandNum==1) {gameState = optionsState;}
				if (ui.commandNum==2) {gameState=validateState;}
			}
			if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {ui.commandNum--;
				if(ui.commandNum < 0) {ui.commandNum = 2;}
			}
			if(entorno.sePresiono(entorno.TECLA_ABAJO)) {ui.commandNum++;
				if(ui.commandNum > 2) {ui.commandNum = 0;}
			}
		}
		
		//OPTIONS MENU
		if(gameState == optionsState) {
			ui.optionsScreen(entorno,this);
			if(entorno.sePresiono(entorno.TECLA_IZQUIERDA)) {
				if(ui.commandNum==0 && sound.volumeScale > 0) {sound.volumeScale--;sound.checkVolume();}
			}
			if(entorno.sePresiono(entorno.TECLA_DERECHA)) {
				if(ui.commandNum==0 && sound.volumeScale <5) {sound.volumeScale++;sound.checkVolume();}
			}
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				if(ui.commandNum==1) {}
				if (ui.commandNum==3) {ui.commandNum=0; gameState = pauseState;}
			}
			if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {ui.commandNum--;
				if(ui.commandNum < 0) {ui.commandNum = 3;}
			}
			if(entorno.sePresiono(entorno.TECLA_ABAJO)) {ui.commandNum++;
				if(ui.commandNum > 3) {ui.commandNum = 0;}
			}
		}
		
		// EXTRA
		if(gameState==validateState) {
			ui.validation(entorno);
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				if(ui.commandNum ==0) {System.exit(0);}
				if(ui.commandNum==1) {ui.commandNum =0; gameState = pauseState;}
			}
			if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {
				ui.commandNum--;
				if(ui.commandNum < 0) {ui.commandNum = 1;}
			}
			if(entorno.sePresiono(entorno.TECLA_ABAJO)) {
				ui.commandNum++;
				if(ui.commandNum > 1) {ui.commandNum = 0;}
			}
		}
		
		// GAME OVER
		if (gameState == gameOver) {
			ui.gameOver(entorno);
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				if(ui.commandNum ==0) {setDefaultValues();gameState=playState;ui.drawTransition(entorno);}
				if(ui.commandNum==1) {System.exit(0);}
			}
			if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {
				ui.commandNum--;
				if(ui.commandNum < 0) {ui.commandNum = 1;}
			}
			if(entorno.sePresiono(entorno.TECLA_ABAJO)) {
				ui.commandNum++;
				if(ui.commandNum > 1) {ui.commandNum = 0;}
			}
		}
		
		// JUEGO
		if(gameState == playState) {
			// Eventos del juego
			if(jugador.vidas==0) {
				gameState = gameOver;
			}
			if(entorno.sePresiono(entorno.TECLA_ALT) && gameState==playState) {gameState = pauseState;}
			if(entorno.estaPresionada(entorno.TECLA_DERECHA)) {jugador.dx = 2;jugador.moverDerecha();}
			if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {jugador.dx = 2;jugador.moverIzquierda();}
			if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {jugador.saltando = true;}
			if(entorno.estaPresionada(entorno.TECLA_ENTER)) {jugador.disparar();}
	
			
			spawnearTigres();
			spawnearAves();
			renderizar(entorno);
			dibujarAves(entorno,this);
			
//			if ( radius < maxRadius) {
//				radius+=10;
//				dibujarCirculo(entorno,radius);
//			}
//			if(radius >= maxRadius) {
//				radius=1;
//			}
			colisionConRama(entorno);
			colisionConEnemigo(entorno);
			colisionConAve(entorno);
		
			piedras();
			colisionConSerpiente(entorno);
			ui.drawMessage(entorno);
			if(tutorial) {
				ui.tutorialKeys(jugador.x + (jugador.width/8), jugador.y- (jugador.height/2), entorno);
				if(entorno.sePresiono(entorno.TECLA_ARRIBA ) || entorno.sePresiono(entorno.TECLA_ENTER)) {
					tutorial = false;
				}
			}
		}

	}	
	
	
	// Eventos con enemigos
	// Crar enemigos
	public void spawnearTigres() {
		//MEJOR SPAWN
		for(int i = 0 ; i < tigres.length;i++) {
			if(tigres[i]==null) {
				tigres[i] = new Tigre(random.nextInt(1200,1600), alturaSuelo, random.nextInt(2,4));
			}
		}
		
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
	
	public void spawnearAves() {
		for(int i = 0 ; i < aves.length;i++) {
			int r = random.nextInt(0,1);
			if(aves[i] == null) {
				aves[i] = new Ave(1240,80,r);
			}
		}
	}
	
	public void dibujarAves(Entorno e,Juego juego) {
		for(int i = 0 ; i < aves.length;i++) {
			if(aves[i]!=null) {
				aves[i].dibujarAve(e);
				aves[i].mover(e,juego);
			}
			if(aves[i].r == 0) {
				aves[i].ataque1();
			}
			else if(aves[i].r == 1){
				aves[i].ataque2();
			}
		}
	}
	
	public void dibujarCirculo(Entorno e,int radius) {
		e.dibujarCirculo(620, 350, radius, Color.black);
	}
		
		
	
	public void colisionConAve(Entorno e) {
		for(int i = 0 ; i < aves.length;i++) {
			if(aves[i]!=null) {
				if(aves[i].aveHitbox().intersects(jugador.jugadorHitBox())) {
					ui.vfx(aves[i].x, aves[i].y,e);
					aves[i] = null;
					//jugador.vidas--;
					ui.addMessage("-1 Vida");
					contadorEliminados++;
					}
				}
			}
		}
	
	
	// Colision con enemigo
	public boolean colisionConEnemigo(Entorno e) {
		for(int i = 0 ; i < tigres.length;i++) {
			if(tigres[i]!=null) {
				if(tigres[i].tigreHitBox().intersects(jugador.jugadorHitBox())) {
					ui.vfx(tigres[i].x, tigres[i].y,e);
					tigres[i] = null;
					jugador.vidas--;
					ui.addMessage("-1 Vida");
					contadorEliminados++;
					return true;
				}
			}
		}
		return false;
	}
	
	
	public void colisionConSerpiente(Entorno e) {
		for (int i = 0; i < fondo.arboles.length;i++) {
			if(fondo.arboles[i].rama.serpientes!=null) {}
				
		}
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
						&& jugador.saltando==true) {jugador.saltando = false;jugador.caer = true;jugador.actualVelocidadSalto=jugador.velocidadSalto;}
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
			if(jugador.piedras[0].x >= 1300) {jugador.piedras[0] = null;jugador.puedeDisparar=true;}
		}
		for(int i = 0 ; i < tigres.length;i++) {
			if(tigres[i]!=null && jugador.piedras[0]!=null) {
				if(tigres[i].tigreHitBox().intersects(jugador.piedras[0].piedraHitbox())) {
					ui.vfx(tigres[i].x, tigres[i].y, entorno);
					
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
		fondo.moverFondo();
		dibujarArboles(entorno);
		dibujarTigres(entorno);
		dibujarPiedras(e);
		//dibujarPiedrasAve(e);
		jugador.actualizar(e);
		ui.hud(e,this);	
	}
	
	public void setDefaultValues() {
		jugador.vidas = 3;
		jugador.y = alturaSuelo;
		ui.puntos=0;
		ui.message.clear();
		contadorEnemigos=0;
		contadorEliminados=0;
		
		
	}
	
	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
