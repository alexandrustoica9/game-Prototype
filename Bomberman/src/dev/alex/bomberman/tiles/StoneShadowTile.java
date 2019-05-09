package dev.alex.bomberman.tiles;

import dev.alex.bomberman.gfx.Assets;

public class StoneShadowTile extends Tile {

	public StoneShadowTile(int id) {
		super(Assets.stoneShadow, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
	
}