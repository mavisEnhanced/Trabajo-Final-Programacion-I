package juego;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;



import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	
	private Entorno entorno;

	// Variables y métodos propios de cada grupo
	Graphics g;
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState =2;
	
	
	boolean lost = false;
	protected double xOffset,yOffset;
	Player player;
	Enemy Tigre;
	Enemy2 Ave;
	Background fondo2;
	Arbol arbol;
	public int floorValue = 560;
	private Random random = new Random();
	public ArrayList<Bullet> bullets = new ArrayList<>(); 
	public ArrayList<Enemy> enemies = new ArrayList<>();
	
	
	
	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Selva Mono Capuchino - Grupo 10 - v1", 1240, 700);
		// Inicializar lo que haga falta para el juego
		// ...
		player = new Player(20,floorValue);
		fondo2 = new Background();
		Tigre= new Enemy(random.nextInt(1200,1500),floorValue);
		Ave= new Enemy2(1300,200,0);
		enemies.add(Tigre);
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
		
		if(gameState == playState) {
			if(entorno.sePresiono(entorno.TECLA_DELETE)) {gameState = pauseState;}
			if(entorno.estaPresionada(entorno.TECLA_DERECHA)) {player.dx = 2;player.moveRight();}
			if(entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {player.dx = 2;player.moveLeft();}
			if(entorno.sePresiono(entorno.TECLA_ARRIBA)) {player.jumping = true;}
			if(entorno.estaPresionada(entorno.TECLA_ENTER)) {player.shoot();}
			//		for(int i =0; i<bullets.size();i++) {
			//			bullets.get(i).render(entorno);
			//		}
			

			draw();
			spawnEnemies();
			
		}
	}
	
	public void checkCollision() {
		Rectangle r1 = Tigre.getBounds();
		Rectangle r2 = Ave.getBounds();
		Rectangle c = player.getBounds();
		if(r1.intersects(c) || r2.intersects(c) ) {
			System.out.println("Ccollision");
		}
	}
	
	
	public void spawnEnemies() {
		for(int i=0;i<enemies.size();i++) {
			if(enemies.get(i)!=null) {
				enemies.get(i).dibujarEnemy(entorno);
				enemies.get(i).move();
				Rectangle r1  = enemies.get(i).getBounds();
				Rectangle c = player.getBounds();
				if(r1.intersects(c)){
					System.out.println("colision");
					enemies.remove(i);
				}
			}
		}
		
	}
	public void draw() {
		if(gameState == pauseState) {
			entorno.escribirTexto("Pausa", 200, 200);
			if(entorno.sePresiono(entorno.TECLA_DELETE)) {gameState=playState;}
		}else {
			fondo2.draw(entorno);
			fondo2.move();
			player.update();
			player.dibujarJugador(entorno);
		}
	}



	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
