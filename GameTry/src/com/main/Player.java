package com.main;
import java.util.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;
import java.util.TimerTask;
public class Player extends GameObject{
	Random r=new Random();
	Handler handler;
	private int interval;
	public Timer t;
	public Player(float x, float y, ID id,Handler handler) {
		
		super(x, y, id);
		this. handler=handler;
		// TODO Auto-generated constructor stub
		velX=0;
		velY=0;
		t=new Timer();
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
		x+=velX;
		y+=velY;
		x=Game.clamp(x, 0, Game.WIDTH-32);
		y=Game.clamp(y,0,Game.HEIGHT-80);
		
		
		collision();
		handler.addObject(new Trail(x,y,ID.Trail,Color.white,32,32,0.1f,handler));
		
		
		/*
		 * t.scheduleAtFixedRate(new TimerTask() {
		 * 
		 * @Override public void run() {
		 * 
		 * 
		 * } }, 1000,5000); t.cancel();
		 */
				
		
	}
	private void collision() {
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject=handler.object.get(i);
			if(tempObject.getID()==ID.SmartEnemy||tempObject.getID()==ID.BasicEnemy||tempObject.getID()==ID.BossBullet||tempObject.getID()==ID.FastEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH-=0.5;
				}
			}
		}
	}
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d= (Graphics2D) g;
		g.setColor(Color.cyan);
		g2d.draw(getBounds());
		if(id== ID.Player) {g.setColor(Color.white);}
		else g.setColor(Color.red);
		g.fillRect((int)x,(int)y,32,32);
	}


}
