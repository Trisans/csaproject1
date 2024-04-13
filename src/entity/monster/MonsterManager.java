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
    Monster m;

    public MonsterManager(GamePanel gp, Pathfinder astar) {
        this.gp = gp;
        this.astar = astar;
    }

    public String nextDir() {
        // astar
        // pathfinder will not make monsters hit walls, so no collision system is needed
        return "eeeee";
    }
    public void updateAll() {
        // IF ACTIVE:
        // get next dir (astar)
        // check if is touching player
        // if touching player, set inactive for 2 sec and change player health
        // calculate new position based on next dir

        // could calculate next dir every time it enters a new tile?
        // save last coords divided by tilesize and then check if current coords are different
        // if same continue moving in same direction
        for (int monsterIndex = 0; monsterIndex < gp.monsters.length; monsterIndex++) {
            if (gp.monsters[monsterIndex].active) { // monster's "active" status is true
                // if on a new tile, update path and direction
                // update position based on direction and speed
                // update sprite
                // if hp <= 0, delete this monster
                // if adjacent to player, deal damage and set to inactive for 2 seconds
                m = gp.monsters[monsterIndex];
                int worldC
                if (m.worldX_px != m.prevWorldX || m.worldY_px != m.prevWorldY) { // on a new tile

                }
            }
        }
    }
    public void drawAll(Graphics2D g2) {
        for (int monsterIndex = 0; monsterIndex < gp.monsters.length; monsterIndex++) {
            if (gp.monsters[monsterIndex].active) { // monster's "active" status is true
                m = gp.monsters[monsterIndex];
                BufferedImage image = null;

                switch (m.direction) {
                    case "up":
                        image = m.spriteList[0][m.spriteNum];
                        break;
                    case "down":
                        image = m.spriteList[1][m.spriteNum];
                        break;
                    case "left":
                        image = m.spriteList[2][m.spriteNum];
                        break;
                    case "right":
                        image = m.spriteList[3][m.spriteNum];
                        break;
                }
                int screenXPx = m.worldX_px - gp.player.worldX_px + gp.player.SCREEN_X_PX;
                int screenYPx = m.worldY_px - gp.player.worldY_px + gp.player.SCREEN_Y_PX;

                g2.drawImage(image, screenXPx, screenYPx, m.width, m.height, null);
            }
        }
    }
}
