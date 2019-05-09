package dev.alex.bomberman.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int WIDTH = 16, HEIGHT = 16;
	
	//Map Tiles
	public static BufferedImage dirt, grass, stone, stoneShadow, crate;
	
	//Players
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage player_stay;
	
	//Bomb Stuff
	public static BufferedImage[] bomb;
	public static BufferedImage[] bomb_center_explosion;
	public static BufferedImage bomb_left_explosion, bomb_right_explosion, bomb_up_explosion, bomb_down_explosion, horizontal_fill_explosion, vertical_fill_explosion;
	
	//GUI
	public static BufferedImage[] btn_start;
	
	//PowerUps
	public static BufferedImage increaseRange, decreaseRange;
	public static BufferedImage increaseBombs, decreaseBombs;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
	
		dirt = sheet.crop(0, HEIGHT * 13, WIDTH, HEIGHT);
		grass = sheet.crop(WIDTH, HEIGHT * 13, WIDTH, HEIGHT);
		stone = sheet.crop(WIDTH * 10, HEIGHT * 18, WIDTH, HEIGHT);
		stoneShadow = sheet.crop(WIDTH * 10, HEIGHT * 17, WIDTH, HEIGHT);
		crate = sheet.crop(WIDTH * 9, HEIGHT * 13, WIDTH, HEIGHT);
	
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_right = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_down[0] = sheet.crop(WIDTH * 2, HEIGHT * 17, WIDTH, HEIGHT);
		player_down[1] = sheet.crop(WIDTH * 3, HEIGHT * 17, WIDTH, HEIGHT);
		player_up[0] = sheet.crop(WIDTH * 8, HEIGHT * 17, WIDTH, HEIGHT);
		player_up[1] = sheet.crop(WIDTH * 9, HEIGHT * 17, WIDTH, HEIGHT);
		player_right[0] = sheet.crop(WIDTH * 5, HEIGHT * 17, WIDTH, HEIGHT);
		player_right[1] = sheet.crop(WIDTH * 7, HEIGHT * 17, WIDTH, HEIGHT);
		player_left[0] = sheet.crop(WIDTH * 15, HEIGHT * 17, WIDTH, HEIGHT);
		player_left[1] = sheet.crop(WIDTH * 15, HEIGHT * 18, WIDTH, HEIGHT);
		player_stay = sheet.crop(WIDTH, HEIGHT * 17, WIDTH, HEIGHT);
	
		bomb = new BufferedImage[3];
		bomb[0] = sheet.crop(WIDTH * 11, HEIGHT * 15, WIDTH, HEIGHT);
		bomb[1] = sheet.crop(WIDTH * 11, HEIGHT * 16, WIDTH, HEIGHT);
		bomb[2] = sheet.crop(WIDTH * 11, HEIGHT * 17, WIDTH, HEIGHT);
		bomb_center_explosion = new BufferedImage[4];
		bomb_center_explosion[0] = sheet.crop(WIDTH * 14, HEIGHT * 16, WIDTH, HEIGHT);
		bomb_center_explosion[1] = sheet.crop(WIDTH * 14, HEIGHT * 17, WIDTH, HEIGHT);
		bomb_center_explosion[2] = sheet.crop(WIDTH * 14, HEIGHT * 18, WIDTH, HEIGHT);
		bomb_center_explosion[3] = sheet.crop(WIDTH * 2, HEIGHT * 18, WIDTH, HEIGHT);
		bomb_left_explosion = sheet.crop(0, HEIGHT * 18, WIDTH, HEIGHT);
		bomb_right_explosion = sheet.crop(WIDTH * 3, HEIGHT * 18, WIDTH, HEIGHT);
		bomb_up_explosion = sheet.crop(WIDTH * 14, HEIGHT * 13, WIDTH, HEIGHT);
		bomb_down_explosion = sheet.crop(WIDTH * 14, HEIGHT * 15, WIDTH, HEIGHT);
		vertical_fill_explosion = sheet.crop(WIDTH * 14, HEIGHT * 14, WIDTH, HEIGHT);
		horizontal_fill_explosion = sheet.crop(WIDTH, HEIGHT * 18, WIDTH, HEIGHT);
		
		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(WIDTH * 15, HEIGHT * 16, WIDTH * 2, HEIGHT);
		btn_start[1] = sheet.crop(WIDTH * 15, HEIGHT * 15, WIDTH * 2, HEIGHT);
		
		increaseRange = sheet.crop(WIDTH * 15, 0, WIDTH, HEIGHT);
		decreaseRange = sheet.crop(WIDTH * 16, 0, WIDTH, HEIGHT);
		increaseBombs = sheet.crop(WIDTH * 15, HEIGHT, WIDTH, HEIGHT);
		decreaseBombs = sheet.crop(WIDTH * 16, HEIGHT, WIDTH, HEIGHT);
	}

	public static int getWidth() {
		return WIDTH;
	}

	public static int getHeight() {
		return HEIGHT;
	}
}
