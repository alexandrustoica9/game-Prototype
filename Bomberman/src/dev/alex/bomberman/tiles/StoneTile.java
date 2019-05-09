package dev.alex.bomberman.tiles;

import dev.alex.bomberman.gfx.Assets;

public class StoneTile extends Tile {

	public StoneTile(int id) {
		super(Assets.stone, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}
