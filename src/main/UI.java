package main;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class UI {
    GamePanel gp;
    Font arial_40;

    BufferedImage[] uiImages = new BufferedImage[10];
    String[] imageDirList = {
        "/UI/heart.png" // 0
    };

    public UI (GamePanel gp) {
        this.gp = gp;
        //this.heart = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/UI/heart.png")));
        getImages();
        arial_40 = new Font("Arial", Font.PLAIN, 40);
    }

    public void getImages() {
        try {
            for (int i = 0; i < imageDirList.length; i++) {
                uiImages[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imageDirList[i])));
            }
        } catch (IOException e) {
            System.out.println("Error loading images: image path(s) could be null. [UI]");
        }
    }
    public void draw(Graphics2D g2) {
        // Draw hearts
        for (int x = 10; x < gp.player.hp * 50; x+=50) {
            g2.drawImage(uiImages[0], x, 20, gp.TILE_SIZE, gp.TILE_SIZE, null);
        }

//        g2.setFont(arial_40);
//        g2.setColor(Color.white);
//        g2.drawString("UI Text Here", gp.player.hp * 50 + 30, 50);
    }
}
