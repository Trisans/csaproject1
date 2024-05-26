package entity.monster;

import entity.astar.Pathfinder;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.ArrayList;

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

    public void attemptSpawn(ArrayList<String> species) {
        gp.ms.attemptSpawn();
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
            if (gp.monsters[monsterIndex] != null && gp.monsters[monsterIndex].active) { // monster's "active" status is true
                // if on a new tile, update path and direction
                // update position based on direction and speed
                // update sprite
                // if hp <= 0, delete this monster
                // if adjacent to player, deal damage and set to inactive for 2 seconds
                m = gp.monsters[monsterIndex];
                int worldX = (int) (m.worldX_px / gp.TILE_SIZE);
                int worldY = (int) (m.worldY_px / gp.TILE_SIZE);
                if (worldX != m.prevWorldX || worldY != m.prevWorldY) { // on a new tile
//                    m.nextDir = astar.getDir(worldY, worldX, // call pathfinder
//                            (int) (gp.player.worldY_px / gp.TILE_SIZE),
//                            (int) (gp.player.worldX_px / gp.TILE_SIZE));
                    m.nextDir = "se";

                }
                // update previous position
                m.prevWorldX = worldX;
                m.prevWorldY = worldY;
                // direction
                // speed
                // move
                // sprite
                double tempSpeed = m.speed;
                if (!m.nextDir.equals("no move")) {
                    // update direction
                    char cardinal = m.nextDir.charAt(0);
                    switch (cardinal) {
                        case 'n': m.direction = "up"; break;
                        case 's': m.direction = "down"; break;
                        case 'e': m.direction = "right"; break;
                        case 'w': m.direction = "left"; break;
                    }

                    // update speed if necessary
                    if (m.nextDir.length() == 2) { // if monster is moving diagonally
                        // reduce monster speed so moving diagonally does not increase speed
                        tempSpeed = m.speed / Math.sqrt(2);
                    }

                    // update position
                    switch (m.nextDir) {
                        // Cardinal
                        case "n": m.worldY_px -= tempSpeed; break;
                        case "s": m.worldY_px += tempSpeed; break;
                        case "e": m.worldX_px += tempSpeed; break;
                        case "w": m.worldX_px -= tempSpeed; break;
                        // Diagonal
                        case "ne":
                            m.worldY_px -= tempSpeed;
                            m.worldX_px += tempSpeed; break;
                        case "se":
                            m.worldY_px += tempSpeed;
                            m.worldX_px += tempSpeed; break;
                        case "sw":
                            m.worldY_px += tempSpeed;
                            m.worldX_px -= tempSpeed; break;
                        case "nw":
                            m.worldY_px -= tempSpeed;
                            m.worldX_px -= tempSpeed; break;

                        default:
                            System.out.println("wth"); break; // wth
                    }

                    // update sprite
                    m.spriteCounter++;
                    if (m.spriteCounter > 3) { // change sprite every 10 frames because update is called 60 times per second
                        m.spriteNum++;
                        m.spriteNum = (m.spriteNum % 2);
                        m.spriteCounter = 0;
                    }
                }
            }
        }

        // attempt to spawn in a new monster, 60 times per second
//        this.attemptSpawn(null);
    }
    public void drawAll(Graphics2D g2) {
        for (int monsterIndex = 0; monsterIndex < gp.monsters.length; monsterIndex++) {
            if (gp.monsters[monsterIndex] != null && gp.monsters[monsterIndex].active) { // monster's "active" status is true
                m = gp.monsters[monsterIndex];
                BufferedImage image = null;

                switch (m.direction) {
                    case "down":
                        image = m.spriteList[0][m.spriteNum];
                        break;
                    case "up":
                        image = m.spriteList[1][m.spriteNum];
                        break;
                    case "left":
                        image = m.spriteList[2][m.spriteNum];
                        break;
                    case "right":
                        image = m.spriteList[3][m.spriteNum];
                        break;
                }
                int screenXPx = (int) (m.worldX_px - gp.player.worldX_px + gp.player.SCREEN_X_PX);
                int screenYPx = (int) (m.worldY_px - gp.player.worldY_px + gp.player.SCREEN_Y_PX);

                g2.drawImage(image, screenXPx, screenYPx, m.width, m.height, null);
            }
        }
    }
}
