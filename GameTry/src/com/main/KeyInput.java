package com.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.main.Game.State;

public class KeyInput extends KeyAdapter{
	private Handler handler;
	private boolean uP = false;
	private boolean dP = false;
	private boolean lP = false;
	private boolean rP = false;
	Game game;
	
	public KeyInput(Handler handler,Game game) {
		this.game=game;
		this.handler=handler;
	}
	public void keyPressed(KeyEvent e) {
		
		int key=e.getKeyCode();
		for(int i=0;i<handler.object.size();i++) {
			GameObject temp=handler.object.get(i);
			if(temp.getID()==ID.Player) {
				// key events
				if(key==KeyEvent.VK_W) {
					uP=true;
					temp.setVelY(-handler.spd);
				}
				if(key==KeyEvent.VK_S) {
					dP=true;
					temp.setVelY(handler.spd);
				}
				if(key==KeyEvent.VK_A) {
					lP=true;
					temp.setVelX(-handler.spd);
				}
				if(key==KeyEvent.VK_D) {
					rP=true;
					temp.setVelX(handler.spd);
				}
				if(key==KeyEvent.VK_SPACE) {
					handler.addObject(new PlayerBullet(temp.x,temp.y,ID.PlayerBullet,handler));
				}
				if(game.gameState==State.Game) {
					if(key==KeyEvent.VK_P ){
						Game.paused=!Game.paused;
						
					}
					
				}
				
				if(key==KeyEvent.VK_F) {
					
				
					if(Game.gameState==State.Shop) {
						game.gameState=State.Game;
					}
					else if(Game.gameState==State.Game) {
						Game.gameState=State.Shop;
					}
				}
			}
		}
	}
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		for(int i=0;i<handler.object.size();i++) {
			GameObject temp=handler.object.get(i);
			if(temp.getID()==ID.Player) {
				// key events
				if(key==KeyEvent.VK_W) {
					uP=false;
					if(dP==true) {
						temp.setVelY(handler.spd);
					}
					else {
						temp.setVelY(0);
					}
				}
				if(key==KeyEvent.VK_S) {
					dP=false;
					if(uP==true) {
						temp.setVelY(-handler.spd);
					}
					else {
						temp.setVelY(0);
					}
				}
				if(key==KeyEvent.VK_A) {
					lP=false;
					if(rP==true) {
						temp.setVelX(handler.spd);
					}
					else {
						temp.setVelX(0);
					}
				}
				if(key==KeyEvent.VK_D) {
					rP=false;
					if(lP==true) {
						temp.setVelX(-handler.spd);
					}
					else {
						temp.setVelX(0);
					}
				}
			}
		}
	}
}
