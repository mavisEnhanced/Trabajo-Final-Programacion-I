package juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import entorno.Entorno;
import entorno.Herramientas;

public class UI {
	Entorno e;
	Juego juego;
	Font maruMonica;
	Font letra;
	Image pointer,heart,up,enter;
	public String currentDialogue= "";
	
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	
	public int commandNum = 0;
	public int puntos=0;
	int counter = 100;
	
	public UI (Entorno entorno,Juego juego) {

		try {
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		e.dibujarImagen(icon,70,70, 0,8);
		e.dibujarImagen(monkey, 55, 70, 0,0.2);
		e.cambiarFont("Microsoft Yahei", 50,Color.BLACK);
		String text = "PUNTOS: ";
		e.escribirTexto(text + puntos,590,50);
	}
	
	public void titulo(Entorno e) {
		e.setFont(maruMonica);
		e.setFont(e.getFont().deriveFont(Font.PLAIN,60));
		
		String text = "Mono Capuccino";
		int x = 200;
		int y = 100;
		e.escribirTexto(text, x, y);
		
		e.cambiarFont(text, 60, Color.MAGENTA);
		e.escribirTexto(text, x-3, y-3);
		//SHADOW
		
		
		// BUTTONS/ OPTIONS NAVIGATION
		e.cambiarFont("Microsoft Yahei",40,Color.WHITE);
		text = "Jugar";
		x = 200; 
		y+= 32*6;
	
		e.escribirTexto(text, x, y);
		if(commandNum==0) {e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
	
		text = "Opciones";
		x= 200; 
		y+= 32*3;
		e.escribirTexto(text, x, y);
		if(commandNum==1) {
			
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
		text = "Salir";
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
	
	public void vfx(int x ,int y,Entorno e) {
		Image humo = Herramientas.cargarImagen("humo.gif");
		e.dibujarImagen(humo,x, y, 0,1);
	}
	
	public void clawEffect(int x,int y,Entorno e) {
		Image claw = Herramientas.cargarImagen("claw.gif");
		e.dibujarImagen(claw,x, y, 0,5);
	}
	
	
	public void tutorialKeys(int x, int y, Entorno e) {
		up = Herramientas.cargarImagen("upKey.gif");
		enter = Herramientas.cargarImagen("entKey.gif");
		e.dibujarImagen(up, x - 12 , y - 35, 0, 1.5);
		e.dibujarImagen(enter, x + 35, y + 12, 0, 1.5);
		e.cambiarFont("x12y16pxMaruMonica.ttf",20,Color.WHITE);
		e.escribirTexto("SALTAR", x - 30, y - 60);
		e.escribirTexto("DISPARAR", x + 30, y + 42);
		
	}
	
	
	public void drawMessage(Entorno e) {
		int messageX = 30;
		int messageY = 250;
		for (int i =0 ; i < message.size();i++) {
			if( message.get(i)!=null) {
				textPopUp(message.get(i),messageX,messageY+1,e);
				textPopUp(message.get(i), messageX, messageY,e);
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
		e.escribirTexto(text,600 , 100);
		pointer = Herramientas.cargarImagen("Pointer.gif");
		int x = 600 ;
		int y = 100;
		
		e.cambiarFont("Microsoft Yahei",40,Color.WHITE);
		text = "CONTINUAR";
		x = 600; 
		y+= 32*6;
	
		e.escribirTexto(text, x, y);
		if(commandNum==0) {
			
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
		text = "OPCIONES";
	
		x = 600;
		y+= 32*3;
		e.escribirTexto(text, x, y);
		if(commandNum==1) {
			
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
		text = "SALIR";
		x= 600; 
		y+= 32*3;
		e.escribirTexto(text, x, y);
		if(commandNum==2) {
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
	}
	
	
	public void validation(Entorno e) {
		int textY =100;
		e.cambiarFont("Microsoft Yahei",60,Color.WHITE);
		currentDialogue = "Esta seguro que \ndesea salir del juego?";
		for(String line: currentDialogue.split("\n")) {
			e.escribirTexto(line,600, textY);
			textY+=60;
		}
		

		int x = 200;
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
		e.escribirTexto(text, 600, 30);
		
		
		int x = 600;
		int y = 100;
		
		e.cambiarFont("x12y16pxMaruMonica.ttf",40,Color.WHITE);
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
		e.cambiarFont("x12y16pxMaruMonica.ttf",40,Color.WHITE);
		text = "Efectos de Sonido";
		x = 600; 
		y+= 32*3;
		e.escribirTexto(text, x, y);
		if(commandNum==1) {
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
		
		
		text = "Controles";
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
	
	public void gameOver(Entorno e) {
		Image gameover= Herramientas.cargarImagen("Gameover.png");
		e.dibujarImagen(gameover,e.ancho()/2, (e.alto()/2)-250,0,1);
		e.cambiarFont("Microsoft Yahei",60,Color.WHITE);
		String text = "";
		int x = 500;
		int y = 350;
		
		e.cambiarFont("Microsoft Yahei",40,Color.WHITE);
		text = "Quiere volver a jugar?";
		e.escribirTexto(text, x, y);
		
		
		e.cambiarFont("Microsoft Yahei",40,Color.WHITE);
		text = "Si";
		x = 600;
		y += 100;
		e.escribirTexto(text, x, y);
	
		if(commandNum==0) {
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
	
		text = "No";
		x = 600; 
		y+= 100;
		e.escribirTexto(text, x , y);
		if(commandNum==1) {
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
		
		e.escribirTexto("Puntaje " + puntos, 550, 250);
	}
	public void drawTransition(Entorno e) {
		counter--;
		e.dibujarCirculo(e.ancho()/2, e.alto()/2,counter*10 , Color.MAGENTA);
		if(counter==50) {
			counter = 0;
		}
		counter = 100;
	}
	
	
}
