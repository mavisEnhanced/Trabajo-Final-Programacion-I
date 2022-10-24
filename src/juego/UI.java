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
		hud(entorno,juego);
		drawMessage(entorno);
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/DeterminationSansWebRegular-369X.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pointer = Herramientas.cargarImagen("Pointer.gif");
		letra = new Font("Microsoft Yahei", Font.BOLD,60);
	}
		
	public void draw(Entorno e) {
		e.getGraphics().setFont(maruMonica);
		e.getGraphics().setColor(Color.WHITE);
		titulo(e);
	}
	
	public void hud(Entorno e,Juego juego) {
		e.cambiarFont("Microsoft Yahei", 40,Color.white);
		e.getGraphics().setFont(maruMonica);
		heart = Herramientas.cargarImagen("HeartUD.png");
		int i = juego.jugador.vidas;
		switch(i) {
			case 3: e.dibujarImagen(heart,50, 50, 0, 0.1);
			e.dibujarImagen(heart,100, 50, 0, 0.1);
			e.dibujarImagen(heart,150, 50, 0, 0.1);
				break;
			case 2:e.dibujarImagen(heart,50,50, 0, 0.1);
				e.dibujarImagen(heart,100, 50, 0, 0.1);
				break;
			case 1:e.dibujarImagen(heart,50,50, 0, 0.1);
				break;
		}
		
		
		e.cambiarFont("Microsoft Yahei", 30,Color.PINK);
		String text = "Puntos : ";
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
		int messageY = 100;
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
		e.escribirTexto(text, centerText(text, e), 100);
		pointer = Herramientas.cargarImagen("Pointer.gif");
		int x = centerText(text,e) ;
		int y = 100;
		
		e.cambiarFont("Microsoft Yahei",40,Color.WHITE);
		text = "CONTINUAR";
		x = centerText(text,e); 
		y+= 32*6;
	
		e.escribirTexto(text, x, y);
		if(commandNum==0) {
			
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
		text = "OPCIONES";
	
		x = centerText(text,e);
		y+= 32*3;
		e.escribirTexto(text, x, y);
		if(commandNum==1) {
			
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
		text = "SALIR";
		x= centerText(text,e); 
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
			e.escribirTexto(line,centerText(currentDialogue,e), textY);
			textY+=60;
		}
		

		int x = 200;
		int y = 100;
		String text="";
		e.cambiarFont("Microsoft Yahei",40,Color.WHITE);
		text = "SI";
		x = centerText(text,e); 
		y+= 32*6;
	
		e.escribirTexto(text, x, y);
		if(commandNum==0) {
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
		text = "NO";
	
		x = centerText(text,e); 
		y+= 32*3;
		e.escribirTexto(text, x , y);
		if(commandNum==1) {
			
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
		
		
	}
	
	public void optionsScreen(Entorno e,Juego juego) {
		
		e.cambiarFont("Microsoft Yahei",60,Color.WHITE);
		String text = "OPCIONES";
		e.escribirTexto(text, centerText(text,e), 30);
		
		
		int x = centerText(text,e);
		int y = 100;
		
		e.cambiarFont("x12y16pxMaruMonica.ttf",40,Color.WHITE);
		text = "Musica";
		x = centerText(text,e); 
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
		x = centerText(text,e); 
		y+= 32*3;
		e.escribirTexto(text, x, y);
		if(commandNum==1) {
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
		
		
		text = "Controles";
		x = centerText(text,e); 
		y+= 32*3;
		e.escribirTexto(text, x, y);
		if(commandNum==2) {
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
		text = "Atras";
		x= centerText(text,e); 
		y+= 32*3;
		e.escribirTexto(text, x, y);
		if(commandNum==3) {
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
	}
	
	public void gameOver(Entorno e) {
		e.cambiarFont("Microsoft Yahei",60,Color.WHITE);
		e.escribirTexto("GAME OVER", 550, 100);
		String text = "";
		int x = centerText(text,e);
		int y = 100;
		
		
		e.cambiarFont("Microsoft Yahei",40,Color.WHITE);
		text = "Retry";
		x = centerText(text,e); 
		y+= 32*6;
		e.escribirTexto(text, x, y);
	
		if(commandNum==0) {
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
	
		text = "Salir";
		x = centerText(text,e); 
		y+= 32*3;
		e.escribirTexto(text, x , y);
		if(commandNum==1) {
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
		}
		
		e.escribirTexto("Puntaje" + puntos, 550, 200);
	
	}
	public void drawTransition(Entorno e) {
		counter--;
		e.dibujarCirculo(e.ancho()/2, e.alto()/2,counter*10 , Color.MAGENTA);
		if(counter==50) {
			counter = 0;
		}
		counter = 100;
	}
	
	public int centerText(String text,Entorno e) {
		int length= (int)e.getGraphics().getFontMetrics(letra).getStringBounds(text,e.getGraphics()).getWidth();
		int x = e.ancho()/2 - length;
		return x;
	}
	
}
