package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;
    
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode(); // returns integer value associated with the key pressed

        if (keyCode == KeyEvent.VK_W) { // W key pressed
            upPressed = true;
        }
        if (keyCode == KeyEvent.VK_S) { // S key pressed
            downPressed = true;
        }
        if (keyCode == KeyEvent.VK_A) { // A key pressed
            leftPressed = true;
        }
        if (keyCode == KeyEvent.VK_D) { // D key pressed
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        
        if (keyCode == KeyEvent.VK_W) { // W key released
            upPressed = false;
        }
        if (keyCode == KeyEvent.VK_S) { // S key released
            downPressed = false;
        }
        if (keyCode == KeyEvent.VK_A) { // A key released
            leftPressed = false;
        }
        if (keyCode == KeyEvent.VK_D) { // D key released
            rightPressed = false;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {} // unused
}
