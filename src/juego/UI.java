package juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import java.awt.Image;


import java.util.ArrayList;

import entorno.Entorno;
import entorno.Herramientas;

public class UI {
	Graphics2D g2;
	Entorno e;
	Font maruMonica;
	Image pointer,heart;
	
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	
	public int commandNum = 0;
	public int puntos=0;
	public UI (Entorno entorno) {
		hud(entorno);
		drawMessage(entorno);
	
	}
		
	public void draw(Entorno e) {
		titulo(e);
	}
	
	public void hud(Entorno e) {
		e.cambiarFont("x12y16pxMaruMonica.ttf", 40,Color.white);
		heart = Herramientas.cargarImagen("HeartUD.png");
		e.dibujarImagen(heart,50, 30, 0, 0.2);
		String text = "Puntos : ";
		e.escribirTexto(text + puntos,590,50);
	}
	
	public void titulo(Entorno e) {
		
		e.cambiarFont("x12y16pxMaruMonica.ttf", 40,Color.white);
		pointer = Herramientas.cargarImagen("Pointer.gif");
		String text = "Mono Capuccino";
		int x = 200;
		int y = 100;
		
		e.escribirTexto(text, x, y);
		//SHADOW
		e.escribirTexto(text, x+3, y+3);
		
		//MAIN COLOR
		// BUTTONS/ OPTIONS NAVIGATION
		text = "Jugar";
		x = 200; 
		y+= 32*6;
	
		e.escribirTexto(text, x, y);
		if(commandNum==0) {
			
			e.dibujarImagen(pointer, x-32, y-10, 0, 1);
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
	
	public void drawMessage(Entorno e) {
		int messageX= 0;
		int messageY= 100;
		
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

	
}
