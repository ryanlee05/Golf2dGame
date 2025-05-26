package Object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import golf.KeyHandler;
import golf.gamePanel;

public class GolfBall {
	public BufferedImage image;
	public int worldX, worldY;
	
	public double velocityX, velocityY;
	
	public double friction;
	gamePanel gp;
	KeyHandler keyH;

	public int hitCount;

	public GolfBall(gamePanel gp, KeyHandler keyH) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/golfball/golfBall.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.gp = gp;
		this.keyH = keyH;

		this.worldX = 20 * gp.tileSize;
		this.worldY = 42 * gp.tileSize;
		
		this.velocityX = 5;
		this.friction = 0.95;

		this.hitCount = 0;

	}

	public void update() {

		
		if(keyH.hitPressed) {
			if(hitCount < 100) {
				worldY -= velocityX;
				velocityX *= friction;
				hitCount++;
			}
			else {
				keyH.hitPressed = false;
			}
			
		}
		

	}

	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

		// only draws tiles around panel to improve efficiency
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
				&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
				&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
				&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			g2.drawImage(image, screenX, screenY, gp.tileSize / 2, gp.tileSize / 2, null);
		}
	}

	// row 42 col 20
}
