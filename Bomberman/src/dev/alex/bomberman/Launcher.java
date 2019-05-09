package dev.alex.bomberman;

public class Launcher {

	public static void main(String[] args) {
		Game game = new Game("Bomberman", 720, 624);
		game.start();
	}
	
}
