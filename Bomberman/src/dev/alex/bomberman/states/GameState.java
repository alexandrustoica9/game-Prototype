package dev.alex.bomberman.states;

import java.awt.Graphics;

import dev.alex.bomberman.Handler;
import dev.alex.bomberman.entities.creatures.Player;
import dev.alex.bomberman.entities.statics.Bomb;
import dev.alex.bomberman.worlds.World;

public class GameState extends State{
	
	private World world;

	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "resources/worlds/world1.txt");
		handler.setWorld(world);
	}
	
	@Override
	public void update() {
		world.update();
	}
	
	@Override
	public void render(Graphics g) {
		world.render(g);
	}
	
}
