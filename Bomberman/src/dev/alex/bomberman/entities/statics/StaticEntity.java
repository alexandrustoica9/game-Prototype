package dev.alex.bomberman.entities.statics;

import dev.alex.bomberman.Handler;
import dev.alex.bomberman.entities.Entity;

public abstract class StaticEntity extends Entity{
	
	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}
	
}
