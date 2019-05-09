package dev.alex.bomberman.states;

import java.awt.Graphics;

import dev.alex.bomberman.Handler;
import dev.alex.bomberman.gfx.Assets;
import dev.alex.bomberman.tiles.Tile;
import dev.alex.bomberman.ui.ClickListener;
import dev.alex.bomberman.ui.UIImageButton;
import dev.alex.bomberman.ui.UIManager;

public class MenuState extends State {
	
	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(312, 288, Tile.TILEWIDTH * 2, Tile.TILEHEIGHT, Assets.btn_start, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().getGameState());
			}
		}));
	}
	
	@Override
	public void update() {
		uiManager.update();
	}
	
	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}
	
}
