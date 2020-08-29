package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter{
	private Handler handler;
	private int B1=100;
	private int B2=100;
	private int B3=100;
	private HUD hud;
	private Game game;
	public Shop(Handler handler,HUD hud,Game game) {
		this.handler=handler;
		this.hud=hud;
		this.game=game;
	}
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.setFont(new Font("arial",0,48));
		g.drawString("Shop",Game.WIDTH/2-100,50);
		
		
		g.setFont(new Font("arial",0,12));
		g.drawString("Upgrade Health",110,120);
		g.drawString("Cost: "+B1,110,140);
		g.drawRect(100, 100, 100, 80);
		
		g.setFont(new Font("arial",0,12));
		g.drawString("Upgrade Speed",260,120);
		g.drawString("Cost: "+B2,260,140);
		g.drawRect(250, 100, 100, 80);
		
		g.setFont(new Font("arial",0,12));
		g.drawString("Upgrade Damage",410,120);
		g.drawString("Cost: "+B3,410,140);
		g.drawRect(400, 100, 100, 80);
		
		g.drawString("Score: "+hud.getScore(),Game.WIDTH/2-50,300);
	}
	public void mousePressed(MouseEvent e) {
		int mx=e.getX();
		int my=e.getY();
		
		if(mx>=100&&mx<=200) {
			if(my>=100&&my<=180) {
				//upgrade 1
				if(hud.getScore()>=B1) {
					hud.setScore(hud.getScore()-B1);
					B1+=100;
					hud.bounds+=50;
					hud.HEALTH+=50/2;
				}
			}
		}
		if(mx>=250&&mx<=350) {
			if(my>=100&&my<=180) {
				//upgrade 2
				if(hud.getScore()>=B2) {
					hud.setScore(hud.getScore()-B2);
					B2+=100;
					handler.spd+=2;
					
				}
			}
		}
		if(mx>=400&&mx<=500) {
			if(my>=100&&my<=180) {
				//upgrade 3
				if(hud.getScore()>=B3) {
					game.PlayerDmg+=1;
					hud.setScore(hud.getScore()-B3);
					B3+=100;
					
				}
			}
		}
	}
	
	
}
