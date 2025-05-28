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

	// collision "tile"
	public boolean collision;

	// location of ball within the world
	public int worldX, worldY;

	// how fast the ball will go in x and y vectors
	public double velocityX, velocityY;

	// collided with ball (at ball position)
	public boolean playerReady;

	// deterioration of ball speed
	public double friction;

	// while the ball is moving
	public boolean hitInProgress;

	// Either at the ball, swinging in the air, follow through.
	public int swingState;

	// club being used to calculate velocity
	public String club;

	// angle at which the ball needs to point towards
	public double angle;

	public long startTime;

	public boolean swingOnce;
	//tracks how many times the ball has been hit
	public int hitCount;
	/**
	 * GolfBall constructor. creates the image of the ball, sets its position within
	 * the world, and sets variables such as friction, swing state, etc.
	 * 
	 * @param gp   game panel that controls all classes
	 *
	 * @param keyH KeyHandler that takes in input (in this case "H" is used)
	 */
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

		this.friction = 0.95;

		this.collision = true;

		this.swingState = 0;

		this.club = "driver";

		this.swingOnce = true;
		hitCount = 0;
	}

	public void update() {

		long delay = 500;

		if (keyH.hitPressed && playerReady && !hitInProgress) {

			hitInProgress = true;
			
			keyH.hitPressed = false;

			angle = 70;

			// if driver is being used, set velocity to 65
			if (club.equals("driver")) {
				velocityX = (int) 65 * Math.cos(Math.toRadians(angle));
				velocityY = (int) 65 * Math.sin(Math.toRadians(angle));
			}

			// if iron, set velocity to 50
			else if (club.equals("iron")) {
				velocityX = (int) 50 * Math.cos(Math.toRadians(angle));
				velocityY = (int) 50 * Math.sin(Math.toRadians(angle));
			}

			else if (club.equals("wedge")) {
				velocityX = (int) 30 * Math.cos(Math.toRadians(angle));
				velocityY = (int) 30 * Math.sin(Math.toRadians(angle));
			}

			else if (club.equals("putter")) {
				velocityX = (int) 15 * Math.cos(Math.toRadians(angle));
				velocityY = (int) 15 * Math.sin(Math.toRadians(angle));
			}

			startTime = System.currentTimeMillis();

		}

		if (hitInProgress) {

			swingState = 2;

			long currTime = System.currentTimeMillis();

			if (currTime - startTime >= delay && swingOnce) {
				gp.playSoundEffect(2);
				swingOnce = false;
				hitCount++;
				
			}
			
			if(!swingOnce) {
				swingState = 3;
				if (velocityY >= 0.5) {
					worldY -= velocityY;
					velocityY *= friction;
				}
				if (velocityX >= 0.5) {
					worldX += velocityX;
					velocityX *= friction;
				}

				else {

					playerReady = false;
					hitInProgress = false;
					swingState = 0;
					solidArea = new Rectangle(this.worldX, this.worldY, 64, 64);
					swingOnce = true;

				}
			}
			
		} else {
			keyH.hitPressed = false;
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
