package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import golf.KeyHandler;
import golf.gamePanel;

public class Player extends Entity{
	gamePanel gp;
	KeyHandler keyH;
	
	
	
	public Player (gamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 5;
		direction = "down"; 
	}
	
	
	public void getPlayerImage() {
		try {
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/pixil-frame-0.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/pixil-frame-1.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/pixil-frame-2.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/pixil-frame-3.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/pixil-frame-4.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/pixil-frame-5.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/pixil-frame-6.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/pixil-frame-7.png"));
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if (keyH.upPressed) {
			direction = "up";
			
			if (y != 0) {
				y -= speed;
			}

		}
		if (keyH.downPressed) {
			direction = "down";
			if (y <= 640) {
				y += speed;
			}

		}
		if (keyH.leftPressed) {
			direction = "left";
			if (x != 0) {
				x -= speed;
			}

		}
		if (keyH.rightPressed) {
			direction = "right";
			
			if (x <= 768) {
				x += speed;
			}
			
		}
		
		spriteCounter++;
		
		if(spriteCounter > 10) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		

		
		
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up" :
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1; 
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
	
}
