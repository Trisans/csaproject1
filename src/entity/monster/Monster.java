package entity.monster;

import entity.Entity;
import entity.astar.Pathfinder;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Monster extends Entity {
    GamePanel gp;
    Pathfinder astar;

    // Stats
    public int damage;
    public int spawnRate;

    public String[][] spriteDirList;
    public BufferedImage[][] spriteList;

    public boolean active = true; // after it hits player, set to inactive for a pause
    public Monster(GamePanel gp) {
        this.gp = gp;
    }
    public Monster() {}

    public void getImages() {
        try {
            for (int i = 0; i < spriteDirList.length; i++) {
                for (int j = 0; j < 2; j++) {
                    spriteList[i][j] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(spriteDirList[i][j])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading images: path(s) could be null [Monster]");
        }
    }
}
