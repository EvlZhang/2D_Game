package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class SmartEnemy extends GameObject{
	Random r=new Random();
	private Handler handler;
	private GameObject player;
	private float velX;
	private float velY;
	public int diffMultiplier;
	public Game game;
	public SmartEnemy(float x, float y, ID id,Handler handler,Game game) {
		super(x, y, id);
		this.handler=handler;
		this.id=id;		
		this.game=game;
		hiddenhp=80;
		diffMultiplier=game.diff;
		for(int i=0;i<handler.object.size();i++) {
			if(handler.object.get(i).getID()==ID.Player) {
				player=handler.object.get(i);
			}
		}
		
		
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	@Override
	public void tick() {
	
		x+=velX;
		y+=velY;
		
		float diffX=x-player.getX();
		float diffY=y-player.getY();
		float distance=(float)Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));
		
		velX=(float) ((-1.0/distance)*diffX);
		velY=(float) ((-1.0/distance)*diffY);
		if(velX<0) {
			velX-=diffMultiplier;
		}
		else {
			velX+=diffMultiplier;
		}
		if(velY<0) {
			velY-=diffMultiplier;
		}
		else {
			velY+=diffMultiplier;
		}
		if(y<=0||y>=Game.HEIGHT-32)velY*=-1;
		if(x<=0||x>=Game.WIDTH-32)velX*=-1;
		
		handler.addObject(new Trail(x,y,ID.Trail,Color.blue,16,16,0.05f,handler));
		collision();
	}
	private void collision() {
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject=handler.object.get(i);
			if(tempObject.getID()==ID.PlayerBullet) {
				if(getBounds().intersects(tempObject.getBounds())) {
					hiddenhp-=game.PlayerDmg;
				}
			}
		}
	}
	@Override
	public void render(Graphics g) {
		
		if(id== ID.Player) {g.setColor(Color.green);}
		else g.setColor(Color.blue);
		g.fillRect((int)x,(int)y,20,20);
	}
}
