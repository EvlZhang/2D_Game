package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class PlayerBullet extends GameObject{
	Random r=new Random();
	private Handler handler;
	public PlayerBullet(float x, float y, ID id,Handler handler) {
		super(x, y, id);
		this. handler=handler;
		this.id=id;
		velX=0;
		velY=-5;
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick() {
	
		x+=velX;
		y+=velY;
		
		//if(y<=0||y>=Game.HEIGHT-32)velY*=-1;
		//if(x<=0||x>=Game.WIDTH-32)velX*=-1;
		if(y<=0||y>=Game.HEIGHT) handler.removeObject(this);
		handler.addObject(new Trail(x,y,ID.Trail,Color.pink,16,16,0.05f,handler));
	}

	
	public void render(Graphics g) {
		
		if(id== ID.Player) {g.setColor(Color.green);}
		else g.setColor(Color.pink);
		g.fillRect((int)x,(int)y,20,20);
	}
}