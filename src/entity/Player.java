package entity;

import main.CollisionChecker;
import main.GamePanel;
import main.KeyHandler;
import org.w3c.dom.css.Rect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;
	public int SCREEN_X_PX;
	public int SCREEN_Y_PX;

	public BufferedImage[][] spriteList = new BufferedImage[4][4];
	public String[][] spriteDirList = {
			{"/player/little_guy_up_1.png", "/player/little_guy_up_2.png", "/player/little_guy_up_3.png"},
			{"/player/little_guy_down_1.png", "/player/little_guy_down_2.png", "/player/little_guy_down_3.png"},
			{"/player/little_guy_left_1.png", "/player/little_guy_left_2.png", "/player/little_guy_left_3.png"},
			{"/player/little_guy_right_1.png", "/player/little_guy_right_2.png", "/player/little_guy_right_3.png"}
	};

	public Rectangle solidArea;

	public boolean collisionOn = false;
	// player-specific
	public boolean collisionUp = false;
	public boolean collisionDown = false;
	public boolean collisionLeft = false;
	public boolean collisionRight = false;
	public boolean isMoving;

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		worldX_px = gp.TILE_SIZE * 10;
		worldY_px = gp.TILE_SIZE * 10;
		SCREEN_X_PX = gp.MAX_SCREEN_COL * (gp.TILE_SIZE / 2) - (gp.TILE_SIZE / 2);
		SCREEN_Y_PX = gp.MAX_SCREEN_ROW * (gp.TILE_SIZE / 2) - (gp.TILE_SIZE / 2);
		speed = 4;
		direction = "down";
		isMoving = false;
		solidArea = new Rectangle(SCREEN_X_PX + 9, SCREEN_Y_PX + 33, 30, 12);
		hp = 5;
		this.isPlayer = true;
	}
	
	public void getPlayerImage() {
		try {
			for (int i = 0; i < 4; i++) {
				spriteList[i][0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(spriteDirList[i][0])));
				spriteList[i][1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(spriteDirList[i][1])));
				spriteList[i][2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(spriteDirList[i][0])));
				spriteList[i][3] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(spriteDirList[i][2])));
			}
		} catch (IOException e) {
			System.out.println("Could not load images" + e);
		}
	}
	
	public void update() {
		// TODO: update player's worldX/Y and prevWorldX/Y
		double tempSpeed = speed;
		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			// if any movement keys are being pressed
			isMoving = true;
			if ((keyH.upPressed || keyH.downPressed) && (keyH.leftPressed || keyH.rightPressed)) { // moving diagonally
				// reduce player speed so moving diagonally does not increase speed
				tempSpeed = speed / Math.sqrt(2);
			}
//			System.out.println(tempSpeed);
			// update player direction
			if (keyH.upPressed) {
				direction = "up";
			} else if (keyH.downPressed) {
				direction = "down";
			}
			else if (keyH.leftPressed) {
				direction = "left";
				
			} else if (keyH.rightPressed) {
				direction = "right";
			}
			
			// check collision
			collisionUp = false;
			collisionDown = false;
			collisionLeft = false;
			collisionRight = false;
			gp.cChecker.checkTile(this);
			
			// move player
			if (keyH.upPressed && !collisionUp) {
				worldY_px -= tempSpeed;
			} else if (keyH.downPressed && !collisionDown) {
				worldY_px += tempSpeed;
			}
			if (keyH.leftPressed && !collisionLeft) {
				worldX_px -= tempSpeed;
			} else if (keyH.rightPressed && !collisionRight) {
				worldX_px += tempSpeed;
			}
			if (isMoving) {
				// update sprite
				spriteCounter++;
				if (spriteCounter > 3) { // change sprite every 10 frames because update is called 60 times per second
					spriteNum = (spriteNum % 3);
					spriteNum++;
					spriteCounter = 0;
				}
			}
		} else { isMoving = false; }
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		if (!isMoving) spriteNum = 0;
		switch (direction) {
			case "up":
				image = spriteList[0][spriteNum]; break;
			case "down":
				image = spriteList[1][spriteNum]; break;
			case "left":
				image = spriteList[2][spriteNum]; break;
			case "right":
				image = spriteList[3][spriteNum]; break;
		}
		g2.drawImage(image, SCREEN_X_PX, SCREEN_Y_PX, gp.TILE_SIZE, gp.TILE_SIZE, null);
	}
}
