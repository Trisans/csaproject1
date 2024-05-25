package entity.monster;

import entity.monster.monsters.TestMonster;
import main.GamePanel;

public class MonsterSpawner {
    GamePanel gp;

    public MonsterSpawner(GamePanel gp) {
        this.gp = gp;
    }

    public void attemptSpawn() {
        // TEST CODE
        Monster test = new TestMonster(25 * gp.TILE_SIZE, 25 * gp.TILE_SIZE);
        for (int i = 0; i < gp.monsters.length; i++)
            if (gp.monsters[i] == null)
                gp.monsters[i] = test;

    }
}
