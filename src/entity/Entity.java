package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
	
	public boolean isPlayer = false;

	public int hp;
	public double worldX_px, worldY_px;
	public double prevWorldX = -1;
	public double prevWorldY = -1;
	public int speed;
	
	public String direction;

	public int spriteCounter = 0;
	public int spriteNum = 1;

	public void damage(int amount) {
		this.hp -= amount;
	}

}
