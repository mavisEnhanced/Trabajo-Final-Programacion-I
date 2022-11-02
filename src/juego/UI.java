package juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import entorno.Entorno;
import entorno.Herramientas;

public class UI {
	Entorno e;
	Juego juego;
	Font maruMonica;
	Font letra;
	Image pointer,heart,up,enter;
	Image Gameover= Herramientas.cargarImagen("Gameover.png");
	Image Coin = Herramientas.cargarImagen("coin-1.png");
	Image coinAn = Herramientas.cargarImagen("coin.gif");
	Image Score = Herramientas.cargarImagen("score.png");
	Image Banana= Herramientas.cargarImagen("Banana_Peeled.png");
	Image Manzana = Herramientas.cargarImagen("Cherry.png");
	Image Blueberries = Herramientas.cargarImagen("Blueberry.png");
	Image Tigre= Herramientas.cargarImagen("tigre.gif");
	Image Aguila = Herramientas.cargarImagen("aguila.gif");
	Image Serpiente = Herramientas.cargarImagen("serpiente.gif");
	public String currentDialogue= "";
	int tutorial = 0;
	
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	
	public int commandNum = 0;
	public int puntos = 0;
	public int bananas = 0;
	public int blueberries = 0;
	public int manzanas =0;
	
	public UI (Entorno entorno,Juego juego) {
		pointer = Herramientas.cargarImagen("Pointer.gif");
	}
		
	public void TitleScreen(Entorno e) {
		titulo(e);
	}
	
	public void HUD(Entorno e,Juego juego) {
		e.setFont(letra);
		heart = Herramientas.cargarImagen("heart.png");
		int i = juego.jugador.vidas;
		switch(i) {
			case 3: e.dibujarImagen(heart,160, 40, 0, 0.2);
			e.dibujarImagen(heart,210, 40, 0, 0.2);
			e.dibujarImagen(heart,260, 40, 0, 0.2);
				break;
			case 2:e.dibujarImagen(heart,160,40, 0, 0.2);
				e.dibujarImagen(heart,210, 40, 0, 0.2);
				break;
			case 1:e.dibujarImagen(heart,160,40, 0, 0.2);
				break;
		}
		
		Image icon = Herramientas.cargarImagen("border.png");
		Image monkey= Herramientas.cargarImagen("monkey.png");
		Image coin = Herramientas.cargarImagen("coin-1.png");
		Image score = Herramientas.cargarImagen("score.png");
		e.dibujarImagen(coin, 160, 95, 0,4);
		e.dibujarImagen(icon,70,70, 0,8);
		e.dibujarImagen(monkey, 55, 70, 0,0.2);
		
		
		e.dibujarImagen(score, 620, 50, 0,0.05);
		e.cambiarFont("Microsoft Yahei", 40,Color.BLACK);
		String text = "x: ";
		e.escribirTexto(text + puntos,740,65);
		e.cambiarFont("Microsoft Yahei", 40,Color.WHITE);
		e.escribirTexto(text + puntos,742,67);
		
		e.cambiarFont("Microsoft Yahei",40,Color.white);
		e.escribirTexto("x: " + juego.jugador.coin, 185, 110);
		
	}
	
	public void titulo(Entorno e) {
		
		String text = "Mono Capuccino";
		int x = 200;
		int y = 100;
		
		e.cambiarFont(text, 60, Color.BLACK);
		e.escribirTexto(text, x, y);
		e.cambiarFont(text, 60, Color.WHITE);
		e.escribirTexto(text, x-3, y-3);
		//SHADOW
		
		
		// BUTTONS/ OPTIONS NAVIGATION
		e.cambiarFont("Microsoft Yahei",40,Color.WHITE);
		text = "JUGAR";
		x = 200; 
		y+= 32*6;
	
		e.escribirTexto(text, x, y);
		if(commandNum==0) {e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
	
		text = "OPCIONES";
		x= 200; 
		y+= 32*3;
		e.escribirTexto(text, x, y);
		if(commandNum==1) {
			
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
		text = "SALIR";
		x= 200; 
		y+= 32*3;
		e.escribirTexto(text, x, y);
		if(commandNum==2) {
			
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
	}
	
	public void addMessage(String text) {
		message.add(0,text);
		messageCounter.add(0);
		
	}
	
	public void textPopUp(String text, int x, int y,Entorno e) {
		e.escribirTexto(text,x,y);
	}

	public void tutorialKeys(int x, int y, Entorno e) {
		up = Herramientas.cargarImagen("upKey.gif");
		enter = Herramientas.cargarImagen("entKey.gif");
		e.dibujarImagen(up, x - 12 , y - 35, 0, 1.5);
		e.dibujarImagen(enter, x + 35, y + 12, 0, 1.5);
		e.escribirTexto("SALTAR", x - 30, y - 60);
		e.escribirTexto("DISPARAR", x + 30, y + 42);
		
	}

	public void drawMessage(Entorno e) {
		int messageX = 30;
		int messageY = 250;
		for (int i =0 ; i < message.size();i++) {
			if( message.get(i)!=null) {
				e.cambiarFont("Microsoft Yahei",25,Color.BLACK);
				textPopUp(message.get(i),messageX,messageY,e);
				e.cambiarFont("Microsoft Yahei",25,Color.WHITE);
				textPopUp(message.get(i), messageX +2, messageY+2,e);
				int counter = messageCounter.get(i) + 1; //messageCounter ++;
				messageCounter.set(i, counter); // set the counter to the array
				messageY += 50;
				if (messageCounter.get(i)>100) {
					messageY-=20;
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
	}
	public void pauseMenu(Entorno e) {
		e.cambiarFont("Microsoft Yahei",60,Color.WHITE);
		String text = "PAUSA";
		e.escribirTexto(text,580 ,100);
		pointer = Herramientas.cargarImagen("Pointer.gif");
		int x = 580;
		int y = 100;
		
		e.cambiarFont("Microsoft Yahei",40,Color.WHITE);
		text = "CONTINUAR";
		x = 580; 
		y+= 32*6;
	
		e.escribirTexto(text, x, y);
		if(commandNum==0) {
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
		text = "OPCIONES";
		x = 580;
		y+= 32*3;
		e.escribirTexto(text, x, y);
		if(commandNum==1) {
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
		text = "SALIR";
		x= 580; 
		y+= 32*3;
		e.escribirTexto(text, x, y);
		if(commandNum==2) {
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
	}

	public void validation(Entorno e) {
		int textX= e.ancho()/8;
		int textY = 100;
		e.cambiarFont("Microsoft Yahei",60,Color.WHITE);
		currentDialogue = "Esta seguro que \ndesea salir del juego?";
		for(String line: currentDialogue.split("\n")) {
			e.escribirTexto(line,textX, textY);
			textY+=60;
		}
		
		int x = 600;
		int y = 100;
		String text="";
		e.cambiarFont("Microsoft Yahei",40,Color.WHITE);
		text = "SI";
		x = 600; 
		y+= 32*6;
	
		e.escribirTexto(text, x, y);
		if(commandNum==0) {
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
		text = "NO";
		x = 600; 
		y+= 32*3;
		e.escribirTexto(text, x , y);
		if(commandNum==1) {
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
	}
	
	public void optionsScreen(Entorno e,Juego juego) {
		e.cambiarFont("Microsoft Yahei",60,Color.WHITE);
		String text = "OPCIONES";
		e.escribirTexto(text, 600, 100);
		int x = 600;
		int y = 100;
		text = "Musica";
		x =600; 
		y+= 32*6;
		e.escribirTexto(text, x, y);
		e.dibujarRectangulo(x + 300, y, 120, 24, 0, Color.white);
		int volumeWidth = 24 * juego.sound.volumeScale;
		e.dibujarRectangulo(x+300, y, volumeWidth, 24, 0, Color.magenta);
		if(commandNum==0) {
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}

		text = "Efectos de Sonido";
		x = 600; 
		y+= 32*3;
		e.escribirTexto(text, x, y);
		if(commandNum==1) {
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
		
		text = "Tutorial";
		x = 600; 
		y+= 32*3;
		e.escribirTexto(text, x, y);
		if(commandNum==2) {
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
		text = "Atras";
		x= 600; 
		y+= 32*3;
		e.escribirTexto(text, x, y);
		if(commandNum==3) {
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
	}
	
	public void tutorial(Entorno e) {
		if(tutorial == 0) {
			int x = e.ancho()/4;
			int y = 120;
			e.cambiarFont("Microsoft Yahei",50,Color.WHITE);
			currentDialogue = "AYUDA A MONO A ESCAPAR \nDE LOS ENEMIGOS";
			for(String line: currentDialogue.split("\n")) {
				e.escribirTexto(line,x, y);
				x +=100;
				y +=70;
			
			}
			
			x = e.ancho()/2 + 200;
			y = e.alto()/2;
			e.cambiarFont("Microsoft Yahei",20,Color.WHITE);
			currentDialogue = "Mono puede lanzar una piedra para \napartar a los enemigos de su camino";
			for(String line: currentDialogue.split("\n")) {
				e.escribirTexto(line,x, y);
				y +=30;
			}
			y+=30;
			currentDialogue = "Impactar a un enemigo otorgara puntos";
			for(String line: currentDialogue.split("\n")) {
				e.escribirTexto(line,x, y);
				y +=30;
			}
			
			x = e.ancho()/4;
			y = 350;
			e.dibujarImagen(Tigre, x, y,0,2.5);
			e.escribirTexto(" 5 PUNTOS", x + 80, y+20);
			y+=150;
			e.dibujarImagen(Aguila, x, y,0,2.5);
			e.escribirTexto(" 7 PUNTOS", x + 80, y+20);
			y+=150;
			e.dibujarImagen(Serpiente, x, y,0,3);
			e.escribirTexto(" 2 PUNTOS", x + 80, y+20);
			e.dibujarImagen(pointer,1150, 650, 0,1);
		}
		
		if(tutorial==1) {
			int x = e.ancho()/8;
			int y = 100;
			e.cambiarFont("Microsoft Yahei",50,Color.WHITE);
			currentDialogue = "MONO PODRA RECOLECTAR FRUTAS \nPARA OBTENER MAS PUNTOS";
			for(String line: currentDialogue.split("\n")) {
				e.escribirTexto(line,x, y);
				x +=100;
				y +=70;
			}
			x = (e.ancho()/4) + 200;
			y = 300;
			e.cambiarFont("Microsoft Yahei",30,Color.WHITE);
			e.dibujarImagen(Banana, x, y, 0,2);
			e.escribirTexto(" 2 PUNTOS", x + 50, y+20);
			y+=100;
			e.dibujarImagen(Manzana, x, y, 0,2);
			e.escribirTexto(" 5 PUNTOS", x + 50, y+20);
			y+=100;
			e.dibujarImagen(Blueberries, x, y, 0,2);
			e.escribirTexto(" 3 PUNTOS", x + 50, y+20);
			e.dibujarImagen(pointer,1150, 650, 0,1);
		}
		if(tutorial==2) {
			int x = e.ancho()/4;
			int y = 100;
			e.cambiarFont("Microsoft Yahei",50,Color.WHITE);
			currentDialogue = "CONSIGUE UN TOTAL DE \n10 MONEDAS PARA ESCAPAR";
			for(String line: currentDialogue.split("\n")) {
				e.escribirTexto(line,x, y);
				x +=50;
				y +=70;
			}

			e.dibujarImagen(coinAn,620,350, 0,4);
			e.dibujarImagen(pointer,1150, 650, 0,1);
		}
		
	}
	public void gameOver(Entorno e,Juego juego) {
		e.dibujarImagen(Gameover,e.ancho()/2, (e.alto()/2)-250,0,1);
		e.cambiarFont("Microsoft Yahei",50,Color.WHITE);

		e.dibujarImagen(Score, e.ancho()/4 - 100, 200, 0,0.05);
		e.escribirTexto("" + puntos,e.ancho()/4 +50 , 220);

		e.dibujarImagen(Coin, e.ancho()/4-100, 300 , 0,4);
		e.escribirTexto("x : " + juego.jugador.coin, e.ancho()/4, 320);

		e.dibujarImagen(Banana, e.ancho()/4-100, 400, 0,2);
		e.escribirTexto("x : " + bananas,e.ancho()/4 ,420);

		e.dibujarImagen(Manzana, e.ancho()/4-100, 500, 0,2);
		e.escribirTexto("x : " + manzanas,e.ancho()/4 , 520);

		e.dibujarImagen(Blueberries, e.ancho()/4-100, 600, 0,2);
		e.escribirTexto("x : " + manzanas,e.ancho()/4 , 620);

		e.cambiarFont("Microsoft Yahei",60,Color.WHITE);
		String text = "";
		
		int x = 500;
		int y = 250;
		e.cambiarFont("Microsoft Yahei",40,Color.WHITE);
		text = "Volver a jugar?";
		e.escribirTexto(text, x, y);

		e.cambiarFont("Microsoft Yahei",40,Color.WHITE);
		text = "Si";
		x = 600;
		y += 150;
		e.escribirTexto(text, x, y);
	
		if(commandNum==0) {
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
	
		text = "No";
		x = 600; 
		y+= 150;
		e.escribirTexto(text, x , y);
		if(commandNum==1) {
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
	
	}

}
