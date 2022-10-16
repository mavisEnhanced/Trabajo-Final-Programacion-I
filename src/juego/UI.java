package juego;

import java.awt.Color;
import java.awt.Font;

import java.awt.Graphics2D;

import java.util.ArrayList;

import entorno.Entorno;

public class UI {
	Graphics2D g2;
	Entorno e;
	Font endFont;
	
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	
	public int commandNum = 0;
	public int puntos=0;
	public UI (Entorno entorno) {
		hud(entorno);
		drawMessage(entorno);
		
		endFont = new Font("Microsoft Yahei",Font.BOLD,80);
	}
	
	public void draw(Entorno e) {
		titulo(e);
	}
	
	public void hud(Entorno e) {
		String text = "Puntos :";
		e.cambiarFont(text, 30, Color.white);
		e.escribirTexto(text + puntos,590,50);
	}
	
	public void titulo(Entorno e) {
		String text = "Mono Capuccino";
		e.setFont(endFont);
		int x = 620 - getXforCenteredText(text,e);
		int y = 100;
		e.cambiarFont(text,60,Color.white);
		e.escribirTexto(text, x, y);
		//SHADOW
		e.cambiarFont(text,60,Color.magenta);
		e.escribirTexto(text, x+3, y+3);
		
		//MAIN COLOR
		// BUTTONS/ OPTIONS NAVIGATION
		text = "Jugar";
		x = 580; 
		y+= 32*5;
		e.cambiarFont(text, 30, Color.white);
		e.escribirTexto(text, x, y);
		if(commandNum==0) {
			e.escribirTexto(">", x-32, y);
		}
		text = "Opciones";
	
		x= 580; 
		y+= 32*2;
		e.escribirTexto(text, x, y);
		if(commandNum==1) {
			e.escribirTexto(">", x-32, y);
		}
		text = "Salir";
		x= 580; 
		y+= 32*2;
		e.escribirTexto(text, x, y);
		if(commandNum==2) {
			e.escribirTexto(">", x-32, y);
		}
	}
	
	public void addMessage(String text) {
		message.add(text);
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
	public int getXforCenteredText(String text,Entorno e) {
		int length = (int)e.getFontMetrics(endFont).getStringBounds(text,g2).getWidth();
		int x = 620 - (length/2);
		return x;
	}
	
}
