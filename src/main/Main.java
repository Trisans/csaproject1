package main;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        // GAME WINDOW SETUP
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        // NAME
        window.setTitle("Tim Sim");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); // add window to the game

        window.pack(); // sizes window to fit GamePanel

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
