package dev.alex.bomberman.tiles;

import dev.alex.bomberman.gfx.Assets;

public class CrateTile extends Tile{

	public CrateTile(int id) {
		super(Assets.crate, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
	
}
