package juego;
import java.util.Random;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	public Entorno entorno;

	// Variables y métodos propios de cada grupo
	public int gameState;
	public final int titleState = 0;
	public final int tutorialState = 1;
	public final int playState = 2;
	public final int pauseState = 3;
	public final int optionsState = 4;
	public final int validateState = 5;
	public final int gameOver = 6;
	boolean tutorial = true;
	
	Jugador jugador;
	Fondo fondo;
	UI ui;
	Sound sound;
	public int alturaSuelo = 560;
	boolean inGame= false;
	
	private Random random = new Random();
	
	public Tigre tigres[] = new Tigre[5];
	public Ave aves[] = new Ave[2];
	public Frutas frutas[] = new Frutas[5];
	public Serpiente serpientes[] = new Serpiente[5];


	
	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Selva Mono Capuchino - Grupo 10 - v1", 1240, 700);
		// Inicializar lo que haga falta para el juego
		//Nivel
		jugador = new Jugador(50,alturaSuelo);
		fondo = new Fondo(entorno,this);
		//Creo Nuevo Jugador
		ui = new UI(entorno,this);
		entorno.setFont(ui.maruMonica);
		//Estado de juego
		gameState = titleState;
		sound = new Sound();
		// Inicia el juego!
		this.entorno.iniciar();
		//playMusic(0);
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
		if(gameState == titleState) {
			ui.TitleScreen(entorno);
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				if(ui.commandNum==0) {gameState = tutorialState;}
				if (ui.commandNum==2) {System.exit(0);}
			}
			if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {ui.commandNum--;
			if(ui.commandNum < 0) {ui.commandNum = 2;}}
			if(entorno.sePresiono(entorno.TECLA_ABAJO)) {ui.commandNum++;if(ui.commandNum > 2) {ui.commandNum = 0;}}
		}
		
		if(gameState == tutorialState) {
			ui.tutorial(entorno);
			if(entorno.sePresiono(entorno.TECLA_DERECHA)) {
				ui.tutorial+=1;
			}
			if(ui.tutorial == 3 && entorno.sePresiono(entorno.TECLA_DERECHA ) && !inGame) {
				gameState = playState;
				ui.tutorial = 0;
			}
			else if(ui.tutorial == 3 && entorno.sePresiono(entorno.TECLA_DERECHA ) && inGame) {
				gameState = optionsState;
				ui.tutorial = 0;
			}
		}
		
		//PAUSE MENU
		if(gameState == pauseState) {
			ui.pauseMenu(entorno);
			
			if(entorno.sePresiono(entorno.TECLA_ESPACIO) && gameState == pauseState) {gameState = playState;}
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				if(ui.commandNum==0) {;gameState = playState;}
				if(ui.commandNum==1) {;gameState = optionsState;}
				if (ui.commandNum==2) {;gameState= validateState;}
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
				if(ui.commandNum == 0 && sound.volumeScale > 0) {sound.volumeScale--;sound.checkVolume();}
			}
			if(entorno.sePresiono(entorno.TECLA_DERECHA)) {
				if(ui.commandNum==0 && sound.volumeScale <5) {sound.volumeScale++;sound.checkVolume();}
			}
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				if(ui.commandNum==2) {gameState=tutorialState;}
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
			ui.gameOver(entorno,this);
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {
				if(ui.commandNum ==0) {
					setDefaultValues(entorno);gameState=playState;}
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
			inGame = true;
			// Eventos del juego
			if(jugador.vidas==0) {
				gameState = gameOver;
				inGame=false;
			}
			
			if(jugador.coin == 10) {
				gameState = gameOver;
				inGame = false;
			}
			
			if(entorno.sePresiono(entorno.TECLA_ALT) && gameState==playState) {gameState = pauseState;}
			if(entorno.estaPresionada(entorno.TECLA_DERECHA)) {jugador.dx = 2;jugador.moverDerecha();}
			if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {jugador.dx = 2;jugador.moverIzquierda();}
			if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {jugador.saltando = true;jugador.salto = true;}
			if(entorno.sePresiono(entorno.TECLA_ABAJO)) {jugador.caer = true;}
			if(entorno.sePresiono(entorno.TECLA_ENTER)) {jugador.disparar();}
			spawnearTigres();
			spawnearAves();
			spawnearFrutas(entorno);

			colisiones();
			renderizar(entorno);

			ui.drawMessage(entorno);
			if(tutorial) {
				ui.tutorialKeys(jugador.x + (jugador.width/8), jugador.y- (jugador.height/2), entorno);
				if(entorno.sePresiono(entorno.TECLA_ARRIBA )) {
					tutorial = false;
				}
			}
		}
		
	}	
	
	
	// CREACION DE ENEMIGOS
	
	//TIGRES
	public void spawnearTigres() {
		for(int i = 0 ; i < tigres.length;i++) {
			if(tigres[i] == null) {
				tigres[i] = new Tigre(random.nextInt(1200,1600), alturaSuelo, random.nextInt(2,5));
			}
		}
	}
	
	public void dibujarTigres(Entorno e) {
		for(int i = 0 ; i < tigres.length;i++) {
			if(tigres[i]!=null) {
				tigres[i].dibujarTigre(e);
				tigres[i].mover(e);
			}
		}
	}
	
	//AGUILA
	public void spawnearAves() {
		for(int i = 0 ; i < aves.length;i++) {
			int r = random.nextInt(0,2);
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
			if(aves[i]!=null && aves[i].r == 0) {
				aves[i].ataque1();
			}
			else if(aves[i]!=null && aves[i].r == 1){
				aves[i].ataque2();
			}
		}
	}

	// COLISIONES CON ENEMIGOS
	public boolean colisionTigre(Entorno e) {
		for(int i = 0 ; i < tigres.length;i++) {
			if(tigres[i]!=null) {
				if(tigres[i].tigreHitBox().intersects(jugador.jugadorHitBox())) {
					tigres[i] = null;
					ui.addMessage("-1 Vida");
					jugador.vidas--;
					return true;
				}
			}
		}
		return false;
	}

	public boolean colisionAve(Entorno e) {
		for(int i = 0 ; i < aves.length;i++) {
			if(aves[i]!=null) {
				if(aves[i].aveHitbox().intersects(jugador.jugadorHitBox())) {
					aves[i] = null;
					ui.addMessage("-1 Vida");
					jugador.vidas--;
					return true;
					}
				else if(aves[i].x < -100 || aves[i].y >=560){
					aves[i] = null;
					}
				}
			}
			return false;
		}

	
	
	// EVENTOS DEL MAPA
	// ARBOLES
	public void dibujarArboles(Entorno e,Juego juego) {
		for (int i = 0; i < fondo.arboles.length;i++) {
			if(fondo.arboles[i]!=null) {
				fondo.arboles[i].dibujarArbol(e,juego);
				fondo.arboles[i].rama(e, juego);
				fondo.arboles[i].mover();
			}
		}
	}
	
	// FRUTAS
	public void spawnearFrutas(Entorno e) {
		for(int i = 0 ; i < frutas.length;i++) {
			int r = random.nextInt(0,7);
			if(frutas[i] == null) {
				if( r >=2) {
					frutas[i] = new Banana();
					frutas[i].x = random.nextInt(1240,1500);
					frutas[i].y = random.nextInt(100,560);
				}
				if(r >2 && r<=4) {
					frutas[i] = new Manzana();
					frutas[i].x = random.nextInt(1240,1500);
					frutas[i].y = random.nextInt(100,400);
				}
				if(r > 4) {
					frutas[i] = new Blueberry();
					frutas[i].x = random.nextInt(1240,1500);
					frutas[i].y = random.nextInt(100,400);
				}
				if(r > 5) {
					frutas[i] = new Moneda();
					frutas[i].x = random.nextInt(1240,1500);
					frutas[i].y = random.nextInt(100,300);
				}
			}
		}
	}
	
	public void dibujarFrutas(Entorno e) {
		for(int i = 0 ; i < frutas.length;i++) {
			if(frutas[i]!=null) {
				frutas[i].dibujar(e);
				frutas[i].mover();	
			}
		}
	}

	// PIEDRAS JUGADOR
	public void dibujarPiedras(Entorno e) {
		if(jugador.piedras[0]!=null) {
			jugador.piedras[0].dibujarPiedra(e);
			jugador.piedras[0].mover();
		}
	}

	//COLISIONES CON OBJETOS DEL MAPA
	// COLISION CON ARBOL
	public void colisionConRama(Entorno e) {	
		for (int i = 0; i < fondo.arboles.length;i++) {
			if(fondo.arboles[i]!=null) {
				// Colision del jugador con la parte de abajo de la rama
				if(jugador.jugadorHitBox().intersects(fondo.arboles[i].rama.hitBox()) 
						&& jugador.saltando == true&& 
						jugador.jugadorHitBox().y >= fondo.arboles[i].rama.hitBox().y) {jugador.saltando = false;jugador.caer = true;jugador.actualVelocidadSalto=jugador.velocidadSalto;}
				// Colision del jugador con la parte de arriba de la rama
				if(fondo.arboles[i].rama.hitBox().intersects(jugador.jugadorHitBox()) && 
					jugador.jugadorHitBox().y <= fondo.arboles[i].rama.hitBox().y) {
						jugador.caer = false;
						jugador.actualVelocidadCaida = .2;
						jugador.actualVelocidadSalto = jugador.velocidadSalto;
						if(jugador.salto) {
							ui.puntos +=1;
							ui.addMessage("PLATAFORMA: + 1");
						}
						if(jugador.x + jugador.width > fondo.arboles[i].rama.x + fondo.arboles[i].rama.width) {
							jugador.caer=true;
						}
				}
			}
		}
	}
	
	
	//COLISION CON FRUTAS
	public void colisionFruta() {
		for(int i = 0 ; i< frutas.length;i++) {
			if(frutas[i]!=null) {
				if(jugador.jugadorHitBox().intersects(frutas[i].hitbox())){
					switch(frutas[i].name) {
					case "banana":
						if(jugador.vidas < 3) {
							ui.addMessage("BANANA : + 1 VIDA");
							jugador.vidas+=1;
							ui.bananas++;
							frutas[i] = null;
							break;
						}
						else {
							ui.addMessage("BANANA : + 2 PUNTOS");
							ui.puntos+=2;
							ui.bananas++;
							frutas[i]=null;
							break;
						}
					case "Manzana":ui.addMessage("MANZANA : + 5 PUNTOS");ui.puntos+=5;ui.manzanas++;frutas[i] = null;break;
					case "Blueberry":ui.addMessage("BlUEBERRY : + 3 PUNTOS");ui.puntos+=3;ui.blueberries++;frutas[i] = null;break;
					case "Moneda":ui.addMessage("MONEDAS : + 1"); jugador.coin+=1; frutas[i] = null;break;
					}
				}
				else if(frutas[i].x <-50) {
					frutas[i]=null;
				}
			}
		}
	}
	
	//COLISION DE LA PIEDRA CONTRA TIGRE
	public boolean piedrasTigre() {
		if(jugador.piedras[0]!=null) {
			if(jugador.piedras[0].x >= 1300) {
				jugador.piedras[0] = null;
				jugador.puedeDisparar=true;
			}
		}
		for(int i = 0 ; i < tigres.length;i++) {
			if(tigres[i]!=null && jugador.piedras[0]!=null) {
				if(tigres[i].tigreHitBox().intersects(jugador.piedras[0].piedraHitbox())) {
					tigres[i] = null;
					jugador.piedras[0]=null;
					jugador.puedeDisparar = true;
					ui.puntos+=5;
					ui.addMessage("+5");
					return true;
				}
			}
		}
		return false;
	}
	//COLISION DE LA PIEDRA CONTRA AVE
	public boolean piedrasAve() {
		if(jugador.piedras[0]!=null) {
			if(jugador.piedras[0].x >= 1300) {
				jugador.piedras[0] = null;
				jugador.puedeDisparar=true;
			}
		}
		for(int i = 0 ; i < aves.length;i++) {
			if(aves[i]!=null && jugador.piedras[0]!=null) {
				if(aves[i].aveHitbox().intersects(jugador.piedras[0].piedraHitbox())) {
					aves[i] = null;
					jugador.piedras[0]=null;
					jugador.puedeDisparar = true;
					ui.puntos+=5;
					ui.addMessage("+5");
					return true;
				}
			}
		}
		return false;
	}
	
	// UTILIDADES
	public void renderizar(Entorno e) {
		fondo.dibujar(e);
		fondo.moverFondo();
		dibujarArboles(e,this);
		dibujarAves(e,this);
		dibujarPiedras(e);
		dibujarTigres(e);
		dibujarFrutas(e);
		jugador.actualizar(e,this);
		ui.HUD(e,this);	
	}
	
	public void colisiones() {
		piedrasTigre();
		 piedrasAve();
		colisionConRama(entorno);
		colisionAve(entorno);
		colisionFruta();
		colisionTigre(entorno);
	}
	
	public void setDefaultValues(Entorno e) {
		jugador.vidas = 3;
		jugador.coin = 0;
		ui.puntos=0;
		ui.bananas=0;
		ui.blueberries=0;
		ui.manzanas=0;
		jugador.y = alturaSuelo;
		ui.message.clear();
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
