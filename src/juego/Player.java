package juego;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

import entorno.Entorno;

public class Player  {
	Juego juego;
	Entorno e;
	public int x,y,dx,dy;
	
	public boolean jumping = false;
	public boolean falling = false ;
	private double jumpSpeed = 8;
	private double currentJumpSpeed = jumpSpeed;
	private double maxFallSpeed = 5;
	private double currentFallSpeed = 1.5;
	
	
	public ArrayList <Bullet> bullets = new ArrayList<>();
	
	public int width = 32;
	public int height = 32;
	
	public Player(int x , int y){
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		moveRight();
		moveLeft();
		jump();
//		shoot();
	}
	
	public void moveRight() {
		this.x += dx;
	}
	
	public void moveLeft() {
		this.x -=dx;
	}
	
	public void jump() {
		if (jumping) {
			y-= currentJumpSpeed*2;
			currentJumpSpeed -= .2;
			if(currentJumpSpeed <=0) {
				currentJumpSpeed = jumpSpeed;
				jumping = false;
				falling =true;
			}
		}
		if (falling) {
			y+= currentFallSpeed*2;
			if(currentFallSpeed < maxFallSpeed) {
				currentFallSpeed += .1;
			}
			if (y >= 560)
				falling=false;
		}
		if(!falling) {
			currentFallSpeed = 1.5;
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,width,height);
	}
	
	public void shoot() {
		Bullet z = new Bullet(x,y);
		juego.bullets.add(z);
	}
	
	public ArrayList <Bullet> getBullets(){
		return bullets;
	}
	
	public void dibujarJugador(Entorno e) {
		e.dibujarRectangulo(x, y,width, height, 0,Color.magenta);
	}
	public boolean intersects(Rectangle r1) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
