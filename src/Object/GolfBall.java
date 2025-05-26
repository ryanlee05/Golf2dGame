package Object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import golf.KeyHandler;
import golf.gamePanel;

public class GolfBall {
	gamePanel gp;
	
	KeyHandler keyH;
	
	public BufferedImage image;
	
	public Rectangle solidArea;
	
	public boolean collision;
	
	public int worldX, worldY;
	
	public double velocityX, velocityY;
	
	public boolean playerReady;
	
	public double friction;
	
	public int hitCount;
	
	public boolean hitInProgress;
	
	

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
		
		this.solidArea = new Rectangle(this.worldX, this.worldY, 64, 64);
		
		this.velocityY= 15;
		this.friction = 0.95;

		this.hitCount = 0;
		
		this.collision = true;

	}

	public void update() {
		
		
		
		if(keyH.hitPressed && playerReady) {
		   if(!hitInProgress) {
		       gp.playSoundEffect(2);
		       hitInProgress = true;
		   }
		
			if(velocityY >= 0.5) {
				worldY -= velocityY;
				velocityY *= friction;
				

			}
			else {
				keyH.hitPressed = false;
				velocityY = 15;
				playerReady = false;
				solidArea = new Rectangle(this.worldX, this.worldY, 64, 64);
			}
			
	}
		else {
			keyH.hitPressed = false;
			hitInProgress = false;
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
