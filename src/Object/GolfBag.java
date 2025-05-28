package Object;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import golf.gamePanel;

public class GolfBag{
    
    gamePanel gp;
    public BufferedImage image;
    
    
    public GolfBag(gamePanel gp) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/golfbag/golf_bag.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.gp = gp;
        
    }
    
    
    
    public void draw(Graphics2D g2) {
        int X = gp.player.screenX - gp.tileSize;
        int Y = gp.player.screenY + 10;
        
        if(gp.golfBall.playerReady) {
            g2.drawImage(image, X, Y, gp.tileSize, gp.tileSize, null);
        }
    }
}