package entity.astar;

import main.GamePanel;

import java.util.ArrayList;

public class Pathfinder {
    NodeManager nm;
    GamePanel gp;
    public Pathfinder(NodeManager nm, GamePanel gp) {
        this.nm = nm;
        this.gp = gp;
    }

    public String getDir(int rowS, int colS, int rowT, int colT) {
        for (int row = 0; row < nm.map.length; row++) {
            for (int col = 0; col < nm.map[0].length; col++) {
                nm.map[row][col].isTarget = false;
            }
        }

        nm.map[rowT][colT].isTarget = true;
        nm.closed.clear();
        nm.open.clear();
        close(nm.map[rowS][colS]);

        Node current = nm.map[rowS][colS];
        boolean done = false;
        int counter = 0;
        done = addSurroundingToOpen(current);

        while (!done) {
            if (done && counter == 0 || counter > 500) return "no move";
            current = getBest(nm.map[rowS][colS], nm.map[rowT][colT]);
            close(current);
            counter++;
            done = addSurroundingToOpen(current);
        }
        return calcDir(nm.map[rowS][colS], current);
    }

    private void close(Node n) {
        nm.closed.add(n);
        nm.open.remove(n);
    }

    private Node getBest(Node start, Node target) {
        Node best = nm.open.get(0);
        for (int i = 0; i < nm.open.size(); i++) {
            if (nm.open.get(i).getFCost(start, target) < best.getFCost(start, target))
                best = nm.open.get(i);
        }
        return best;
    }
    private boolean addSurroundingToOpen(Node current) { // returns true if target is adjacent
        for (int row = current.row - 1; row < current.row + 2; row++) {
            for (int col = current.col - 1; col < current.col + 2; col++) {
                try {
                    if (!nm.open.contains(nm.map[row][col]) && !nm.closed.contains(nm.map[row][col]) && !nm.map[row][col].isWall
                            && !(row == current.row && col == current.col)) {
                        if (nm.map[row][col].isTarget) return true;
                        nm.open.add(nm.map[row][col]);
                        nm.map[row][col].parent = current;
                    }
                } catch (Exception e) {
                    System.out.println("CAUGHT ERROR: Out of bounds (addSurroundingToOpen)");
                }
            }
        }
        return false;
    }

    private String calcDir(Node start, Node end) { // determines the optimal direction based on the start node and the calculated target node
        // TODO: optimize while loop
        Node target = end;
        if (target == start) return "no move";

        while (true) {
            if (target.parent == start) break;
            target = target.parent;
        }

        if (target.isWall || start.row == target.row && start.col == target.col) return "no move";

        if (start.row == target.row + 1 && start.col == target.col) return "n";
        else if (start.row == target.row + 1 && start.col + 1 == target.col) return "ne";
        else if (start.row == target.row && start.col + 1 == target.col) return "e";
        else if (start.row + 1 == target.row && start.col + 1 == target.col) return "se";
        else if (start.row + 1 == target.row && start.col == target.col) return "s";
        else if (start.row + 1 == target.row && start.col == target.col + 1) return "sw";
        else if (start.row == target.row && start.col == target.col + 1) return "w";
        else if (start.row == target.row + 1 && start.col == target.col + 1) return "nw";
        else return "no move";
    }
}
