package Object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import golf.KeyHandler;
import golf.gamePanel;

public class Clubs {
    gamePanel gp;
    KeyHandler keyH;
    public BufferedImage image, image2, image3, image4;

    public int X, Y;
    


public Clubs(gamePanel gp, KeyHandler keyH) {
    try {
        image = ImageIO.read(getClass().getResourceAsStream("/clubs/Driver.png"));
        image2 = ImageIO.read(getClass().getResourceAsStream("/clubs/Iron.png"));
        image3 = ImageIO.read(getClass().getResourceAsStream("/clubs/Wedge.png"));
        image4 = ImageIO.read(getClass().getResourceAsStream("/clubs/Putter.png"));
        
    }
    catch(Exception e) {
        e.printStackTrace();
    }
    
    this.gp = gp;
    this.keyH = keyH;
    this.X = 8 + gp.tileSize/2;
    this.Y = gp.screenHeight - 10 - gp.tileSize/2;
    
    
}

public void update() {
    
}

public void draw(Graphics2D g2) {
    
        g2.drawImage(image, X, Y, gp.tileSize/2, gp.tileSize/2, null);
        g2.drawImage(image2, X - gp.tileSize/2, Y, gp.tileSize/2, gp.tileSize/2, null);
        g2.drawImage(image3, X + gp.tileSize/2, Y, gp.tileSize/2, gp.tileSize/2, null);
}
}