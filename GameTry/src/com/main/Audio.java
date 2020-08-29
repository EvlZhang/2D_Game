package com.main;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Audio {
	public static Map<String, Sound> soundMap=new HashMap<String, Sound>();
	public static Map<String, Music> musicMap=new HashMap<String, Music>();
	
	public static void load() {
		try {
			soundMap.put("Sound",new Sound("res/click.wav"));
			musicMap.put("Music", new Music("res/Gandalf Sax guy 10 Hours.wav"));
		}	catch(SlickException e) {
			
			e.printStackTrace();
		}
		
	}
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}
 
}
