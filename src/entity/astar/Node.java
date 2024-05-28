package entity.astar;

public class Node {
    Node parent;

    public boolean isStart = false;
    public boolean isTarget = false;
    public boolean isWall = false;

    public int row;
    public int col;

    private double g; // start to node
    private double h; // node to target
    private double f = -1; // g + h

    public Node(int row, int col, int role) {
        this.row = row;
        this.col = col;

        if (role == 1) isWall = true;
    }

    public double getFCost(Node start, Node target) {
//        if (f != -1) return f; // memoization to reduce number of sqrt calculations HA GOT YOU YOU STUPID BUG YOU HAVE NO IDEA HOW LONG IT TOOK ME TO FIGURE OUT WHY THIS WAS BREAKING
        g = Math.sqrt(Math.pow(target.row - row, 2) + Math.pow(target.col - col, 2)); // node to target
        h = Math.sqrt(Math.pow(start.row - row, 2) + Math.pow(start.col - col, 2)); // start to node
        f = g + h;
        return f;
    }
}
