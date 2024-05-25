package entity.astar;

import main.GamePanel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NodeManager {
    GamePanel gp;

    public ArrayList<Node> open = new ArrayList<Node>();
    public ArrayList<Node> closed = new ArrayList<Node>();
    public int[][] intMap;
    public Node[][] map;

    public NodeManager(GamePanel gp) {
        this.gp = gp;
        intMap = gp.getMap();
        map = new Node[gp.MAX_WORLD_ROW][gp.MAX_WORLD_COL];
        convertMapToNodeMap();
    }

    public void convertMapToNodeMap() {
        for (int row = 0; row < gp.MAX_WORLD_ROW; row++) {
            for (int col = 0; col < gp.MAX_WORLD_COL; col++) {
                assert intMap != null;
                int role = intMap[row][col];
                assert map != null;
                map[row][col] = new Node(row, col, role);
            }
        }
    }
}
