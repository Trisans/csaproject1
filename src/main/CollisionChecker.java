package main;

import entity.Entity;

public class CollisionChecker {
	GamePanel gp;
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		int entityLeftWorldXPx = (int) (entity.worldX_px + 9);
		int entityRightWorldXPx = (int) (entity.worldX_px + 9 + gp.player.solidArea.width);
		int entityTopWorldYPx = (int) (entity.worldY_px + 33);
		int entityBottomWorldYPx = (int) (entity.worldY_px + 33 + gp.player.solidArea.height);
		
		int entityLeftCol = entityLeftWorldXPx / gp.TILE_SIZE;
		int entityRightCol = entityRightWorldXPx / gp.TILE_SIZE;
		int entityTopRow = entityTopWorldYPx / gp.TILE_SIZE;
		int entityBottomRow = entityBottomWorldYPx / gp.TILE_SIZE;
		
		int tileNum1;
		int tileNum2;
		
		
		
		if (entity.isPlayer) {
			if (gp.keyH.upPressed) {
				// if the block 4 pixels ahead of the player is solid, set entity.collisionOn to true
				entityTopRow = ((entityTopWorldYPx - entity.speed) / gp.TILE_SIZE);
				tileNum1 = gp.tileM.map[entityTopRow][entityLeftCol];
				tileNum2 = gp.tileM.map[entityTopRow][entityRightCol];
//				System.out.println(entityLeftCol);
				if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
					gp.player.collisionUp = true;
				}
			} else if (gp.keyH.downPressed) {
				entityBottomRow = ((entityBottomWorldYPx + entity.speed) / gp.TILE_SIZE);
				tileNum1 = gp.tileM.map[entityBottomRow][entityLeftCol];
				tileNum2 = gp.tileM.map[entityBottomRow][entityRightCol];
				if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
					gp.player.collisionDown = true;
				}
			}
			if (gp.keyH.leftPressed) {
				entityLeftCol = ((entityLeftWorldXPx - entity.speed) / gp.TILE_SIZE);
				tileNum1 = gp.tileM.map[entityTopWorldYPx / gp.TILE_SIZE][entityLeftCol];
				tileNum2 = gp.tileM.map[entityBottomWorldYPx / gp.TILE_SIZE][entityLeftCol];
				
				if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
					gp.player.collisionLeft = true;
				}
//				System.out.println(entityTopRow + ", " + entityBottomRow);
			} else if (gp.keyH.rightPressed) {
				entityRightCol = ((entityRightWorldXPx + entity.speed) / gp.TILE_SIZE);
				tileNum1 = gp.tileM.map[entityTopWorldYPx / gp.TILE_SIZE][entityRightCol];
				tileNum2 = gp.tileM.map[entityBottomWorldYPx / gp.TILE_SIZE][entityRightCol];
				
				if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
					gp.player.collisionRight = true;
				}
//				System.out.println(entityTopRow + ", " + entityBottomRow);
			}
		}
//		else {
//			switch (entity.direction) {
//				case "up" -> {
//					// if the block 4 pixels ahead of the player is solid, set entity.collisionOn to true
//					entityTopRow = ((entityTopWorldYPx - entity.speed) / gp.TILE_SIZE);
//					tileNum1 = gp.tileM.map[entityTopRow][entityLeftCol];
//					tileNum2 = gp.tileM.map[entityTopRow][entityRightCol];
////				System.out.println(entityLeftCol);
//					if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
//						entity.collisionOn = true;
//					}
//				}
//				case "down" -> {
//					entityBottomRow = ((entityBottomWorldYPx + entity.speed) / gp.TILE_SIZE);
//					tileNum1 = gp.tileM.map[entityBottomRow][entityLeftCol];
//					tileNum2 = gp.tileM.map[entityBottomRow][entityRightCol];
//					if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
//						entity.collisionOn = true;
//					}
//				}
//				case "left" -> {
//					entityLeftCol = ((entityLeftWorldXPx - entity.speed) / gp.TILE_SIZE);
//					tileNum1 = gp.tileM.map[entityTopRow][entityLeftCol];
//					tileNum2 = gp.tileM.map[entityBottomRow][entityLeftCol];
//					if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
//						entity.collisionOn = true;
//					}
//				}
//				case "right" -> {
//					entityRightCol = ((entityRightWorldXPx + entity.speed) / gp.TILE_SIZE);
//					tileNum1 = gp.tileM.map[entityTopRow][entityRightCol];
//					tileNum2 = gp.tileM.map[entityBottomRow][entityRightCol];
//					if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
//						entity.collisionOn = true;
//					}
//				}
//			}
//		}
		
//		System.out.println(entity.worldX_px / gp.TILE_SIZE + ", " + entity.worldY_px / gp.TILE_SIZE + " - " + tileNum1 +
//				", " + tileNum2 + " - " +
//				gp.tileM.tiles[tileNum1].collision + ", " + gp.tileM.tiles[tileNum2].collision);
		
	}
}
