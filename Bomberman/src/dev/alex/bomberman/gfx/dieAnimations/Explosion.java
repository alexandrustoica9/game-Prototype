package dev.alex.bomberman.gfx.dieAnimations;

import java.awt.Graphics;

import dev.alex.bomberman.Handler;
import dev.alex.bomberman.gfx.Animation;
import dev.alex.bomberman.gfx.Assets;
import dev.alex.bomberman.powerups.DecreaseBombsPowerUp;
import dev.alex.bomberman.powerups.DecreaseRangePowerUp;
import dev.alex.bomberman.powerups.IncreaseBombsPowerUp;
import dev.alex.bomberman.powerups.IncreaseRangePowerUp;
import dev.alex.bomberman.powerups.PowerUp;
import dev.alex.bomberman.tiles.Tile;
import dev.alex.bomberman.utils.Utils;

public class Explosion extends DieAnimation{
	
	private int range[], maxRange;
	private long lastTime, currentTime; // for center
	private long lastTime2, currentTime2; // for sides
	private Handler handler;
	private boolean explosionSides = false;
	
	//Animationss
	private Animation explosionCenter;

	
	public Explosion(Handler handler, float x, float y, int range, int speed) {
		super(x, y, speed);
		this.handler = handler;
		this.range = new int[4];
		for (int i = 0; i < 4; i++) {
			this.range[i] = range;
		}
		
		this.maxRange = range;
		lastTime = System.currentTimeMillis();
		
		explosionCenter = new Animation(speed, Assets.bomb_center_explosion);
	}
	
	@Override
	public void update() {
		currentTime += System.currentTimeMillis() - lastTime;
		if (currentTime > speed * explosionCenter.getLength()) {
			explosionSides = true;
		} else {
			lastTime2 = System.currentTimeMillis();
			explosionCenter.update();
		}
		lastTime = System.currentTimeMillis();
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(explosionCenter.getCurrentFrame(), (int) x, (int) y, Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
		if (explosionSides) {
			currentTime2 += System.currentTimeMillis() - lastTime2;
			if (currentTime2 > 300) {
				stop();
			}
			sideExplosions(g);
			lastTime2 = System.currentTimeMillis();
		}
	}
	
	public void sideExplosions(Graphics g) {
		for (int i = 1; i <= range[0]; i++) {
			if ((int) x + Tile.TILEWIDTH * i < handler.getWorld().getWidth() * Tile.TILEWIDTH) {//Right Explosion	
				if ((handler.getWorld().getTiles()[(int) x / Tile.TILEWIDTH + i][(int) y / Tile.TILEHEIGHT] != 2) &&
						(handler.getWorld().getTiles()[(int) x / Tile.TILEWIDTH + i][(int) y / Tile.TILEHEIGHT] != 3)) {
					if (i == maxRange) {
						g.drawImage(Assets.bomb_right_explosion, (int) x + Tile.TILEWIDTH * i, (int) y, Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
					} else {
						g.drawImage(Assets.horizontal_fill_explosion, (int) x + Tile.TILEWIDTH * i, (int) y, Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
					}
					if (handler.getWorld().getTiles()[(int) x / Tile.TILEWIDTH + i][(int) y / Tile.TILEHEIGHT] == 4) {
						handler.getWorld().getTiles()[(int) x / Tile.TILEWIDTH + i][(int) y / Tile.TILEHEIGHT] = 0;
						range[0] = i;
						if (Utils.checkDropChance()) {
							generateRandomPowerUp(i);
						}
						//handler.getWorld().getPowerUpManager().addPowerUp(new IncreaseRangePowerUp(handler, (int) x + Tile.TILEWIDTH * i, (int) y));
						break;
					}
				} else {
					break;
				}
			}
		}
		
		for (int i = 1; i <= range[1]; i++) {
			if ((int) x - Tile.TILEWIDTH * i > 0) {//Left Explosion
				if ((handler.getWorld().getTiles()[(int) x / Tile.TILEWIDTH - i][(int) y / Tile.TILEHEIGHT] != 2) &&
						(handler.getWorld().getTiles()[(int) x / Tile.TILEWIDTH - i][(int) y / Tile.TILEHEIGHT] != 3)) {
					if (i == maxRange) {
						g.drawImage(Assets.bomb_left_explosion, (int) x - Tile.TILEWIDTH * i, (int) y, Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
					} else {
						g.drawImage(Assets.horizontal_fill_explosion, (int) x - Tile.TILEWIDTH * i, (int) y, Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
					}
					if (handler.getWorld().getTiles()[(int) x / Tile.TILEWIDTH - i][(int) y / Tile.TILEHEIGHT] == 4) {
						handler.getWorld().getTiles()[(int) x / Tile.TILEWIDTH - i][(int) y / Tile.TILEHEIGHT] = 0;
						range[1] = i;
						if (Utils.checkDropChance()) {
							generateRandomPowerUp(i);
						}
						break;
					}
				} else {
					break;
				}
			}
		}
		
		for (int i = 1; i <= range[2]; i++) {
			if ((int) y - Tile.TILEHEIGHT * i > 0) {//Up Explosion
				if ((handler.getWorld().getTiles()[(int) x / Tile.TILEWIDTH][(int) y / Tile.TILEHEIGHT - i] != 2) &&
						(handler.getWorld().getTiles()[(int) x / Tile.TILEWIDTH][(int) y / Tile.TILEHEIGHT - i] != 3)) {
					if (i == maxRange) {
						g.drawImage(Assets.bomb_up_explosion, (int) x, (int) y - Tile.TILEHEIGHT * i, Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
					} else {
						g.drawImage(Assets.vertical_fill_explosion, (int) x, (int) y - Tile.TILEHEIGHT * i, Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
					}
					if (handler.getWorld().getTiles()[(int) x / Tile.TILEWIDTH][(int) y / Tile.TILEHEIGHT - i] == 4) {
						handler.getWorld().getTiles()[(int) x / Tile.TILEWIDTH][(int) y / Tile.TILEHEIGHT - i] = 0;
						range[2] = i;
						if (Utils.checkDropChance()) {
							generateRandomPowerUp(i);
						}
						break;
					}
				} else {
					break;
				}
			}
		}
		
		for (int i = 1; i <= range[3]; i++) {
			if ((int) y + Tile.TILEWIDTH * i < handler.getWorld().getHeight() * Tile.TILEHEIGHT) {//Down Explosion
				if ((handler.getWorld().getTiles()[(int) x / Tile.TILEWIDTH][(int) y / Tile.TILEHEIGHT + i] != 2) &&
						(handler.getWorld().getTiles()[(int) x / Tile.TILEWIDTH][(int) y / Tile.TILEHEIGHT + i] != 3)) {
					if (i == maxRange) {
						g.drawImage(Assets.bomb_down_explosion, (int) x, (int) y + Tile.TILEHEIGHT * i, Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
					} else {
						g.drawImage(Assets.vertical_fill_explosion, (int) x, (int) y + Tile.TILEHEIGHT * i, Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
					}
					if (handler.getWorld().getTiles()[(int) x / Tile.TILEWIDTH][(int) y / Tile.TILEHEIGHT + i] == 4) {
						handler.getWorld().getTiles()[(int) x / Tile.TILEWIDTH][(int) y / Tile.TILEHEIGHT + i] = 0;
						range[3] = i;
						
						break;
					}
				} else {
					break;
				}
			}
		}
		
	}
	
	public void generateRandomPowerUp(int i) {
		int r = (int) (Math.random() * PowerUp.getNumberOfPowerUps()) + 1;
		
		if (r == 1) {
			handler.getWorld().getPowerUpManager().addPowerUp(new IncreaseRangePowerUp(handler, (int) x + Tile.TILEWIDTH * i, (int) y));
		} else if (r == 2) {
			handler.getWorld().getPowerUpManager().addPowerUp(new DecreaseRangePowerUp(handler, (int) x + Tile.TILEWIDTH * i, (int) y));
		} else if (r == 3) {
			handler.getWorld().getPowerUpManager().addPowerUp(new IncreaseBombsPowerUp(handler, (int) x + Tile.TILEWIDTH * i, (int) y));
		} else if (r == 4) {
			handler.getWorld().getPowerUpManager().addPowerUp(new DecreaseBombsPowerUp(handler, (int) x + Tile.TILEWIDTH * i, (int) y));
		}
	}
	
	@Override
	public void stop() {
		active = false;
	}
	
}
