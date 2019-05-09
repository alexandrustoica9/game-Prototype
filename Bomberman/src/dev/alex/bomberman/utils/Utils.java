package dev.alex.bomberman.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import dev.alex.bomberman.Handler;
import dev.alex.bomberman.powerups.PowerUp;

public class Utils {	
	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null) {
				builder.append(line + "\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return builder.toString();
	}
	
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static boolean checkDropChance() {
		int r = (int) (Math.random() * 2) + 1;
		
		if (r == 2) {
			return true; 
		} else {
			return false;
		}
	}
}