package com.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected float x,y;
	protected ID id;
	protected float velX,velY;
	public int hiddenhp;
	//protected Handler handler;
	public GameObject(float x,float y ,ID id) {
		this.x=x;
		this.y=y;
		this.id=id;
		
		
	}
	public abstract void tick();
	public abstract void render(Graphics g);
		
	public void setX(int x) {
		this.x=x;
		
	}
	public abstract Rectangle getBounds();
	
	public void sety(int y) {
		this.y=y;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public ID getID() {
		return id;
	}
	public void setID(ID id) {
		this.id=id;
	}
	public void setVelX(int velX) {
		this.velX=velX;
	}
	public void setVelY(int velY) {
		this.velY=velY;
	}
	public float getVelX() {
		return velX;
	}
	public float getVelY() {
		return velY;
	}
	
	
}
