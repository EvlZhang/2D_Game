package com.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Boss extends GameObject{
	Random r=new Random();
	private Handler handler;
	private int timer=80;
	private int timer2=50;
	private HUD hud;
	public Game game;
	public Boss(float x, float y, ID id,Handler handler,Game game,HUD hud) {
		super(x, y, id);
		this. handler=handler;
		this.hud=hud;
		this.id=id;
		this.game=game;
		hiddenhp=100*hud.getLevel();
		int diffMultiplier=game.diff;
	//	velX=r.nextInt(5+5)-5;
		velX=0;
		velY=2+diffMultiplier;
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	@Override
	public void tick() {
	
		x+=velX;
		y+=velY;
		if(timer<=0) {
			velY=0;
			timer2--;
			if(timer2<=0) {
				if(velX==0) velX=5;
				if(velX>0) {
					velX+=0.005f;
				}
				else if(velX<0){
					velX-=0.005f;
				}
				velX=Game.clamp(velX, -10, 10);
				int spawn=r.nextInt(20);
				if(spawn==0) handler.addObject(new BossBullet(x+48,y+48,ID.BossBullet,handler));
			}
		}
		else {
			timer--;
		}
		
		//if(y<=0||y>=Game.HEIGHT-32)velY*=-1;
		if(x<=0||x>=Game.WIDTH-96)velX*=-1;
		
		handler.addObject(new Trail(x,y,ID.Trail,Color.red,64,64,0.05f,handler));
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
		else g.setColor(Color.red);
		g.fillRect((int)x,(int)y,64,64);
	}
}