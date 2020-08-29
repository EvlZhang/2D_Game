package com.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.main.Game.State;

public class Handler {
	public int spd=5;
	LinkedList<GameObject> object=new LinkedList<GameObject>();
	public void tick() {
		for(int i=0;i<object.size();i++) {
			GameObject temp=object.get(i);
			dead();
			temp.tick();
		}
	}
	public void render(Graphics g) {
		for(int i=0;i<object.size();i++) {
			GameObject temp=object.get(i);
			
			temp.render(g);
		}
	}
	public void addObject(GameObject o) {
		this.object.add(o);
	}
	public void removeObject(GameObject o) {
		this.object.remove(o);
	}
	public void clear() {
		for(int i=0;i<object.size();i++) {
			GameObject temp=object.get(i);
			if(Game.gameState!=State.End) {
				if(temp.getID()!=ID.Player) {
					removeObject(temp);
					i--;
				}
			}
			else {
				removeObject(temp);
				i--;
			}
			
		}
	}
	public void dead() {
		for(int i=0;i<object.size();i++) {
			GameObject temp=object.get(i);
			if(Game.gameState!=State.End) {
				if(temp.getID()!=ID.Player&&temp.getID()!=ID.PlayerBullet&&temp.getID()!=ID.Trail&&temp.getID()!=ID.BossBullet&&temp.hiddenhp<=0) {
					removeObject(temp);
					i--;
				}
			}
			
			
		}
	
	}
}
