package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject{
	Random r=new Random();
	
	private Handler handler;
	private int red=r.nextInt(255);
	private int green=r.nextInt(255);
	private int blue=r.nextInt(255);
	private Color color;
	
	public MenuParticle(float x, float y, ID id,Handler handler) {
		super(x, y, id);
		this. handler=handler;
		this.id=id;
		
		velX=r.nextInt(10+10)-10;
		velY=r.nextInt(10+10)-10;
		
		color=new Color(red,green,blue);
		
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	@Override
	public void tick() {
	
		x+=velX;
		y+=velY;
		
		if(y<=0||y>=Game.HEIGHT-32)velY*=-1;
		if(x<=0||x>=Game.WIDTH-32)velX*=-1;
		
		handler.addObject(new Trail(x,y,ID.Trail,color,16,16,0.05f,handler));
	}

	@Override
	public void render(Graphics g) {
		
		if(id== ID.Player) {g.setColor(Color.green);}
		else g.setColor(color);
		g.fillRect((int)x,(int)y,20,20);
	}
}