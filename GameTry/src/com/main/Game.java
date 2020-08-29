package com.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	public static final int WIDTH=640,HEIGHT=480;
	public Handler handler;
	private Thread thread;
	private boolean running=false;
	private Random r;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;
	public static boolean paused=false;
	public int PlayerDmg=2;
	
	//1 is normal difficulty
	public int diff=1;
	public enum State{
		Shop,
		Select,
		Menu,
		Settings,
		End,
		Game;
		
	}
	public static State gameState=State.Menu;
	
	public Game() {
		
		hud=new HUD();
		handler=new Handler();
		
		//Everything that needs handler should be below
		shop=new Shop(handler,hud,this);
		spawner=new Spawn(handler,hud,this);
		menu=new Menu(this,handler,hud);
		//audio
		Audio.load();
		Audio.getMusic("Music").loop();;
		
		
		this.addKeyListener(new KeyInput(handler,this));
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		
		new Window(WIDTH, HEIGHT,"Build",this);
		
		r=new Random();
		
		if(gameState==State.Game) {
			
			handler.addObject(new Player(50,50,ID.Player,handler));
		
			handler.addObject(new BasicEnemy(r.nextInt(WIDTH),r.nextInt(HEIGHT), ID.BasicEnemy,handler,this));
			//for(int i=0;i<20;i++) {
			//handler.addObject(new BasicEnemy(r.nextInt(WIDTH),r.nextInt(HEIGHT), ID.BasicEnemy,handler));
			//}
			
			//handler.addObject(new Boss((Game.WIDTH/2)-48,-120,ID.Boss,handler));
		}
		else {
			for(int i=0;i<10;i++) {
				handler.addObject(new MenuParticle(r.nextInt(WIDTH),r.nextInt(HEIGHT), ID.MenuParticle,handler));
			}
		}
	}
	public static float clamp(float var,float min,float max) {
		if(var>=max) return var=max;
		else if(var<=min) return var=min;
		else return var;
	}
	

	private static final long serialVersionUID = 4088146271165387233L;
	
	
	public synchronized void start() {
		thread=new Thread(this);
		thread.start();
		running=true;
	}
	public synchronized void stop() {
		try {
			thread.join();
			running=false;
		}catch(Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}
	public void run() {
		this.requestFocus();
		long lastTime=System.nanoTime();
		double amountOfTicks=60.0;
		double ns=1000000000/amountOfTicks;
		double delta=0;
		long timer=System.currentTimeMillis();
		int frames=0;
		while(running) {
			long now=System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			while(delta>=1) {
				tick();
				delta--;
				
			}
			if(running) render();
			frames++;
			if(System.currentTimeMillis()-timer>1000) {
				timer+=1000;
				System.out.println("FPS: "+ frames);
				frames=0;
			}
		}
		stop();
		
	}	
	private void tick() {
		
		
		
		if(gameState==State.Game) {
			if(!paused) {
				handler.tick();
				hud.tick();
				spawner.tick();
			}
			
		}
		else {
			menu.tick();
			handler.tick();
		}
		if(hud.HEALTH<=0) {
			
			hud.HEALTH=100;
			hud.bounds=0;
			gameState=State.End;
			handler.clear();
		}
		
	}
	String s;
	private void render() {
		BufferStrategy bs=this.getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g=bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(paused) {
			g.drawString("PAUSED", 100, 100);
		}
		if(gameState==State.Game) {
			hud.render(g);
			handler.render(g);
		}
		else if(gameState==State.Shop) {
			shop.render(g);
			handler.render(g);
			hud.render(g);
		}
		else {
			menu.render(g);
			handler.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	public static void main(String args[]) {
		
		new Game();
	}
}
