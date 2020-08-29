package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class FastEnemy extends GameObject{
	Random r=new Random();
	private Handler handler;
	public Game game;
	public FastEnemy(float x, float y, ID id,Handler handler,Game game) {
		super(x, y, id);
		this. handler=handler;
		this.id=id;
		this.game=game;
		hiddenhp=50;
		int diffMultiplier=game.diff;
		velX=r.nextInt(10+10)-10;
		velY=r.nextInt(10+10)-10;
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
		
		if(y<=0||y>=Game.HEIGHT-32)velY*=-1;
		if(x<=0||x>=Game.WIDTH-32)velX*=-1;
		
		handler.addObject(new Trail(x,y,ID.Trail,Color.CYAN,16,16,0.05f,handler));
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
		else g.setColor(Color.CYAN);
		g.fillRect((int)x,(int)y,20,20);
	}
}
