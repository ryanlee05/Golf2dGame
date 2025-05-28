package golf;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import golf.gamePanel;
import Object.GolfBall;
import java.io.InputStream;

public class UI{
    gamePanel gp;
    Font pixelFont;
    
    public UI(gamePanel gp) {
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/Pixeled.ttf");
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(18f); 
        } catch (Exception e) {
            e.printStackTrace();
            pixelFont = new Font("Monospaced", Font.BOLD, 16); 
        }
    }
    
        
    
    public void drawHoleText(Graphics2D g2) {
        g2.setFont(pixelFont);
        g2.setColor(Color.WHITE);
        g2.drawString("STROKES: " + gp.golfBall.hitCount, gp.screenWidth - 3*gp.tileSize + 30, 40);
    }
    
    
}




