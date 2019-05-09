package com.tutorial.main.assets;

import java.awt.image.BufferedImage;

import com.tutorial.main.utils.Util;

public class Assets {

	public static final int WIDTH = 32, HEIGHT = 32;
	
	public static BufferedImage arrowLeft, arrowRight, arrowUp, arrowDown;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(Util.loadImage("/textures/sheet.png"));
	
		//Settings Menu
		arrowUp = sheet.crop(0, 0, WIDTH, HEIGHT);
		arrowDown = sheet.crop(WIDTH, 0, WIDTH, HEIGHT);
		arrowLeft = sheet.crop(WIDTH * 2, 0, WIDTH, HEIGHT);
		arrowRight = sheet.crop(WIDTH * 3, 0, WIDTH, HEIGHT);
	}
	
}
