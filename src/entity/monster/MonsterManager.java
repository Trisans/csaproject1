package entity.monster;

import entity.astar.Pathfinder;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;

public class MonsterManager {
    /*
    spawner class
        will initially create one monster for testing (hardcoded)
        add monster to gp list of monsters


    update every monster
        go through every monster in gp list and call its update method
            update will get the next dir IF the player OR the monster is on a new tile
            it will then update the monsters position by an increment dependent on the speed value


    draw every monster

    */
    private GamePanel gp;
    private Pathfinder astar;

    public MonsterManager(GamePanel gp, Pathfinder astar) {
        this.gp = gp;
        this.astar = astar;
    }

    public String nextDir() {
        // astar
        // pathfinder will not make monsters hit walls, so no collision system is needed
        return "eeeee";
    }
    public void updateAll(Graphics2D g2) {
        // IF ACTIVE:
        // get next dir (astar)
        // check if is touching player
        // if touching player, set inactive for 2 sec and change player health
        // calculate new position based on next dir

        // could calculate next dir every time it enters a new tile?
        // save last coords divided by tilesize and then check if current coords are different
        // if same continue moving in same direction
    }
    public void drawAll(Graphics2D g2) {
        for (int monsterIndex = 0; monsterIndex < gp.monsters.length; monsterIndex++) {
            BufferedImage image = null;
            Monster m = gp.monsters[monsterIndex];
            switch (m.direction) {
                case "up":
                    image = m.spriteList[0]; break;
                case "down":
                    image = m.spriteList[1]; break;
                case "left":
                    image = m.spriteList[2]; break;
                case "right":
                    image = m.spriteList[3]; break;
            }
            g2.drawImage(image, SCREEN_X_PX, SCREEN_Y_PX, gp.TILE_SIZE, gp.TILE_SIZE, null);
        }
    }
}
