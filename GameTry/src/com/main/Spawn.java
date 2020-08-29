package com.main;

import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private int scoreKeep=0;
	private Random r=new Random();
	public Game game;
	public Spawn(Handler handler,HUD hud,Game game) {
		this.game=game;
		this.handler=handler;
		this.hud=hud;
		
	}
	public void tick() {
		scoreKeep++;
		if(scoreKeep>=200) {
			scoreKeep=0;
			hud.setLevel(hud.getLevel()+1);
			//if(hud.getLevel()==2) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.BasicEnemy,handler,game));
			//}
			if(hud.getLevel()%3==0) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.FastEnemy,handler,game));
			}
			if(hud.getLevel()%2==0) {
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.SmartEnemy,handler,game));
			}
			if(hud.getLevel()%10==0) {
				handler.clear();
				handler.addObject(new Boss((Game.WIDTH/2)-48,-120,ID.Boss,handler,game,hud));
		
			}
		}
	}
	
}
