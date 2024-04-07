package main.tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
	GamePanel gp;
	public Tile[] tiles;
	public int map[][];
	
	String[] tileDirList = {
		"/tiles/grass_plain.png",       // 0
		"/tiles/brick_gray.png",        // 1
//		"/tiles/water.png",             // 2
//		"/tiles/sand_plain.png",        // 3
//		"/tiles/tree_1.png",            // 4
//		"/tiles/dirt_1.png",            // 5
//		"/tiles/grass_flowered_1.png",  // 6
//		"/tiles/marker.png",            // 7
//		"/tiles/tree_1_sand.png"        // 8
	};
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tiles = new Tile[10];
		map = new int[gp.MAX_WORLD_ROW][gp.MAX_WORLD_COL];
		getTileImage();
		loadMap("/maps/world_min.txt");
	}
	
	public void getTileImage() {
		try {
			// load tiles
			for (int i = 0; i < tileDirList.length; i++) {
				tiles[i] = new Tile();
				tiles[i].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(tileDirList[i])));
			}
			// set collision
			tiles[1].collision = true; // gray brick
		} catch (IOException e) {
			System.out.println("Error loading images: image path(s) could be null. [TileManager]");
		}
	}
	
	public void loadMap(String mapDir) {
		try {
			// import contents of map text file
			InputStream is = getClass().getResourceAsStream(mapDir);
			// read contents of file
			assert is != null;
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			// copy contents to 2D array
			for (int row = 0; row < gp.MAX_WORLD_ROW; row++) {
				String[] line = br.readLine().split(" ");
				for (int col = 0; col < gp.MAX_WORLD_COL; col++) {
					int num = Integer.parseInt(line[col]);
					map[row][col] = num;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		int maxX = gp.player.worldX_px + gp.player.SCREEN_X_PX + gp.TILE_SIZE;
		int minX = gp.player.worldX_px - gp.player.SCREEN_X_PX - gp.TILE_SIZE;
		
		int maxY = gp.player.worldY_px + gp.player.SCREEN_Y_PX + gp.TILE_SIZE;
		int minY = gp.player.worldY_px - gp.player.SCREEN_Y_PX - gp.TILE_SIZE;
		
		for (int worldRow = 0; worldRow < gp.MAX_WORLD_ROW; worldRow++) {
			
			for (int worldCol = 0; worldCol < gp.MAX_WORLD_COL; worldCol++) {
				int tileWorldXPx = worldCol * gp.TILE_SIZE;
				int tileWorldYPx = worldRow * gp.TILE_SIZE;
				
				int tileScreenXPx = tileWorldXPx - gp.player.worldX_px + gp.player.SCREEN_X_PX;
				int tileScreenYPx = tileWorldYPx - gp.player.worldY_px + gp.player.SCREEN_Y_PX;
				
				if (tileWorldXPx > minX && tileWorldXPx < maxX && tileWorldYPx > minY && tileWorldYPx < maxY) {
					BufferedImage toDraw = tiles[map[worldRow][worldCol]].image;
					g2.drawImage(toDraw, tileScreenXPx, tileScreenYPx, gp.TILE_SIZE, gp.TILE_SIZE, null);
				}
			}
		}
	}
}
