package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BasicEnemy extends GameObject{
	Random r=new Random();
	private Handler handler;
	public Game game;
	public int diffMultiplier;
	public BasicEnemy(float x, float y, ID id,Handler handler,Game game) {
		super(x, y, id);
		this. handler=handler;
		this.id=id;
		this.game=game;
		hiddenhp=100;
		diffMultiplier=game.diff;
		velX=r.nextInt(5+5)-5;
		velY=r.nextInt(5+5)-5;
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
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	@Override
	public void tick() {
	
		x+=velX;
		y+=velY;
		//if(diffMultiplier==1) {
			if(y<=0||y>=Game.HEIGHT-32)velY*=-1;
			if(x<=0||x>=Game.WIDTH-32)velX*=-1;
		//}
		/*else {
			if(y<=0||y>=Game.HEIGHT-32) {
				if(velY<0)	velY=-r.nextInt(0+5)-5;
				}
			else {
				velY=r.nextInt(5)+1;
			}
			if(y<=0||y>=Game.HEIGHT-32) {
				if(velX<0)	velY=-r.nextInt(0+5)-5;
				}
			else {
				velX=r.nextInt(5)+1;
			}
		}*/
		handler.addObject(new Trail(x,y,ID.Trail,Color.red,16,16,0.05f,handler));
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
	public void render(Graphics g) {
		
		if(id== ID.Player) {g.setColor(Color.green);}
		else g.setColor(Color.red);
		g.fillRect((int)x,(int)y,20,20);
	}
}
