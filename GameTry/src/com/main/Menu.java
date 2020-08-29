package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.main.Game.State;

public class Menu extends MouseAdapter{
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r=new Random();
	public Menu(Game game, Handler handler,HUD hud) {
		this.game=game;
		this.handler=handler;
		this.hud=hud;
	}
	public void render(Graphics g) {
		Font fnt=new Font("arial",1,50);
		Font fnt2=new Font("arial",1,30);
		if(game.gameState==State.Menu) {
			
			
			g.setFont(fnt);
			g.setColor(Color.pink);
			g.drawString("Die Hard Square", 125, 50);
			
			g.setFont(fnt2);
			g.setColor(Color.pink);
			g.drawString("Play", 280, 190);
			
			g.setFont(fnt2);
			g.setColor(Color.pink);
			g.drawString("Help", 280, 290);
			g.setFont(fnt2);
			g.setColor(Color.pink);
			g.drawString("Quit", 280, 390);
			//play
			g.setColor(Color.cyan);
			g.drawRect(220,150,200,64);
			//settings
			g.setColor(Color.cyan);
			g.drawRect(220,250,200,64);
			//quit
			g.setColor(Color.cyan);
			g.drawRect(220,350,200,64);
		}
		else if(game.gameState==State.Settings) {
			g.setFont(fnt);
			g.setColor(Color.cyan);
			g.drawString("Help", 245, 50);
			//back
			g.setColor(Color.cyan);
			g.drawRect(220,350,200,64);
			g.setColor(Color.pink);
			g.drawString("Back", 259, 395);
			
			g.setFont(fnt2);
			g.drawString("Use WASD to move", 200, 200);
		}
		else if(game.gameState==State.End) {
			g.setFont(fnt);
			g.setColor(Color.cyan);
			g.drawString("Game Over", 195, 50);
			//back
			g.setColor(Color.cyan);
			g.drawRect(220,350,200,64);
			g.setColor(Color.pink);
			g.drawString("Back", 259, 395);
			
			g.setFont(fnt2);
			g.drawString("Your reached level: "+ hud.getLevel(), 200, 200);
			
		}
		else if(game.gameState==State.Select) {
			
			
			g.setFont(fnt);
			g.setColor(Color.pink);
			g.drawString("Select Difficulty", 140, 50);
			
			g.setFont(fnt2);
			g.setColor(Color.pink);
			g.drawString("Normal", 280, 190);
			
			g.setFont(fnt2);
			g.setColor(Color.pink);
			g.drawString("Die Hard", 280, 290);
			g.setFont(fnt2);
			g.setColor(Color.pink);
			g.drawString("Back", 280, 390);
			
			g.setColor(Color.cyan);
			g.drawRect(220,150,200,64);
			
			g.setColor(Color.cyan);
			g.drawRect(220,250,200,64);
			
			g.setColor(Color.cyan);
			g.drawRect(220,350,200,64);
		}
	}
	public void tick() {
		
	}
	public void mousePressed(MouseEvent e) {
		int mx=e.getX();
		int my=e.getY();
		if(game.gameState==State.Menu) {
			if(mouseOver(mx,my,220,150,200,64)) {
				//play
				//game.gameState=State.Game;
				//handler.addObject(new Player(50,50,ID.Player,handler));
				//handler.clear();
				game.gameState=State.Select;
				Audio.getSound("Sound").play();
				return;
				
		
			}
			else if(mouseOver(mx,my,220,350,200,64)) {
				//quit
				System.exit(1);
			}
			else if(mouseOver(mx,my,220,250,200,64)) {
				game.gameState=State.Settings;
				Audio.getSound("Sound").play();
			}
		}
		
		
		
		
		
		//back
		if(game.gameState==State.Settings) {
			if(mouseOver(mx,my,220,350,200,64)) {
				game.gameState=State.Menu;
				Audio.getSound("Sound").play();
				return;
			}
		}
		if(game.gameState==State.End) {
			if(mouseOver(mx,my,220,350,200,64)) {
				game.gameState=State.Menu;
				Audio.getSound("Sound").play();
				return;
			}
		}
		if(game.gameState==State.Select) {
			if(mouseOver(mx,my,220,150,200,64)) {
				//normal
				game.gameState=State.Game;
				handler.addObject(new Player(50,50,ID.Player,handler));
				handler.clear();
				game.diff=1;
			
				return;
				
		
			}
			else if(mouseOver(mx,my,220,350,200,64)) {
				//back
				game.gameState=State.Menu;
			}
			else if(mouseOver(mx,my,220,250,200,64)) {
				game.gameState=State.Game;
				handler.addObject(new Player(50,50,ID.Player,handler));
				handler.clear();
				game.diff=2;
				
			}
		}
		
		
	}
	public void mouseReleased(MouseEvent e) {
		
	}
	private boolean mouseOver(int mx,int my,int x,int y,int width,int height) {
		if(mx>x&&mx<x+width) {
			if(my>y&&my<y+height) {
				return true;
			}
			else return false;
		}
		else return false;
	}
}
