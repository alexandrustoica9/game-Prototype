package com.tutorial.main.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Util {

	public static int sidesCollision(int position, int min, int max) {
		if (position >= max) {
			return max;
		} else if (position <= min) {
			return min;
		} else {
			return position;
		}
	}
	
	public static BufferedImage loadImage(String path) {
		try{
			return ImageIO.read(Util.class.getResource(path));
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	public static int available(int index, int changedValue, int[] keyArray) {
		for (int i = 0; i < keyArray.length; i++) {
			if (keyArray[i] == changedValue && index != i) {
				return i;
			}
		}
		
		return -1;
	}
	
}
