package entity.monster.monsters;

import entity.monster.Monster;
import main.GamePanel;

public class TestMonster extends Monster {


    public TestMonster(int spawnX_px, int spawnY_px, GamePanel gp) {
        super(gp);
        this.setDefaultValues(spawnX_px, spawnY_px);
    }

    private void setDefaultValues(int worldX_px, int worldY_px) {
        this.hp = 2;
        this.speed = 2;
        this.damage = 1;

        this.worldX_px = worldX_px;
        this.worldY_px = worldY_px;
        this.direction = "down";

        spriteDirList = new String[][] {
                {"/monster/testmonster_1.png", "/monster/testmonster_2.png"},
                {"/monster/testmonster_1.png", "/monster/testmonster_2.png"},
                {"/monster/testmonster_1.png", "/monster/testmonster_2.png"},
                {"/monster/testmonster_1.png", "/monster/testmonster_2.png"},
        };
        this.getImages();
    }

}
