package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import golf.KeyHandler;
import golf.gamePanel;

public class Player extends Entity {
    gamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    public Player(gamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 16;
        solidArea.y = 30;
        solidArea.width = 34;
        solidArea.height = 24;

        setDefaultValues();
        getPlayerImage();
    }


    public void setDefaultValues() {
        worldX = gp.tileSize * 20;
        worldY = gp.tileSize * 43;
        speed = 5;
        direction = "down";
    }


    public void getPlayerImage() {
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream(
                "/player/sage_player1.png.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream(
                "/player/sage_player2.png.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream(
                "/player/sage_player7.png.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream(
                "/player/sage_player8.png.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream(
                "/player/sage_player5.png.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream(
                "/player/sage_player6.png.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream(
                "/player/sage_player3.png.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream(
                "/player/sage_player4.png.png"));
            setDriver = ImageIO.read(getClass().getResourceAsStream(
                "/player/player_driver.png"));
            setIron = ImageIO.read(getClass().getResourceAsStream(
                "/player/player_iron.png"));
            setWedge = ImageIO.read(getClass().getResourceAsStream(
                "/player/player_wedge.png"));
            setPutter = ImageIO.read(getClass().getResourceAsStream(
                "/player/player_putter.png"));
            bsDriver = ImageIO.read(getClass().getResourceAsStream(
                "/player/bs_driver.png"));

            bsIron = ImageIO.read(getClass().getResourceAsStream(
                "/player/bs_iron.png"));

            bsWedge = ImageIO.read(getClass().getResourceAsStream(
                "/player/bs_wedge.png"));

            bsPutter = ImageIO.read(getClass().getResourceAsStream(
                "/player/bs_Putter.png"));
            finishDriver = ImageIO.read(getClass().getResourceAsStream(
                "/player/full_driver.png"));
            finishIron = ImageIO.read(getClass().getResourceAsStream(
                "/player/full_Iron.png"));
            finishWedge = ImageIO.read(getClass().getResourceAsStream(
                "/player/full_Wedge.png"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void update() {

        // CHECK TILE COLLISION
        collisionOn = false;
        gp.collisionCheck.checkTile(this);

        // checks to see if the player is at the ball

        if (gp.collisionCheck.checkBall(this)) {

            gp.golfBall.playerReady = true;

            gp.golfBall.swingState = 1;

            worldX = gp.golfBall.worldX - 35;
            worldY = gp.golfBall.worldY - 30;
        }

        if (!gp.golfBall.playerReady) {

            if (collisionOn == false) {
                if (keyH.upPressed) {
                    direction = "up";
                    worldY -= speed;
                    moving = true;

                }
                else if (keyH.downPressed) {
                    direction = "down";
                    worldY += speed;
                    moving = true;

                }
                else if (keyH.leftPressed) {
                    direction = "left";
                    worldX -= speed;
                    moving = true;

                }
                else if (keyH.rightPressed) {
                    direction = "right";
                    worldX += speed;
                    moving = true;
                }

            }
            else {
                gp.playSoundEffect(0);
                if (collisionDirection.equals("up")) {
                    worldY += speed;
                    if (keyH.downPressed) {
                        direction = "down";
                        worldY += speed;
                        moving = true;

                    }
                    if (keyH.leftPressed) {
                        direction = "left";
                        worldX -= speed;
                        moving = true;
                    }
                    if (keyH.rightPressed) {
                        direction = "right";
                        worldX += speed;
                        moving = true;
                    }
                }
                else if (collisionDirection.equals("down")) {
                    worldY -= speed;
                    if (keyH.upPressed) {
                        direction = "up";
                        worldY -= speed;
                        moving = true;
                    }
                    if (keyH.leftPressed) {
                        direction = "left";
                        worldX -= speed;
                        moving = true;
                    }
                    if (keyH.rightPressed) {
                        direction = "right";
                        worldX += speed;
                        moving = true;
                    }
                }
                else if (collisionDirection.equals("left")) {
                    worldX += speed;
                    if (keyH.upPressed) {
                        direction = "up";
                        worldY -= speed;
                        moving = true;
                    }
                    if (keyH.downPressed) {
                        direction = "down";
                        worldY += speed;
                        moving = true;
                    }
                    if (keyH.rightPressed) {
                        direction = "right";
                        worldX += speed;
                        moving = true;
                    }

                }
                else if (collisionDirection.equals("right")) {
                    worldX -= speed;
                    if (keyH.upPressed) {
                        direction = "up";
                        worldY -= speed;
                        moving = true;
                    }
                    if (keyH.leftPressed) {
                        direction = "left";
                        worldX -= speed;
                        moving = true;
                    }
                    if (keyH.downPressed) {
                        direction = "down";
                        worldY += speed;
                        moving = true;
                    }
                }

            }
        }

        if (moving) {
            spriteCounter++;
        }

        moving = false;

        if (spriteCounter > 11) {
            if (spriteNum == 1) {
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
        String currentClub = gp.club.clubNames[gp.club.currentIndex];

        if (gp.golfBall.swingState == 1) {
            if (currentClub.equals("Driver")) {
                image = setDriver;
            }
            else if (currentClub.equals("Iron")) {
                image = setIron;
            }
            else if (currentClub.equals("Wedge")) {
                image = setWedge;
            }
            else {
                image = setPutter;
            }

        }
        else if (gp.golfBall.swingState == 2) {
            if (currentClub.equals("Driver")) {
                image = bsDriver;
            }
            else if (currentClub.equals("Iron")) {
                image = bsIron;
            }
            else if (currentClub.equals("Wedge")) {
                image = bsWedge;
            }
            else {
                image = bsPutter;
            }
        }
        else if (gp.golfBall.swingState == 3) {
            if (currentClub.equals("Driver")) {
                image = finishDriver;
            }
            else if (currentClub.equals("Iron")) {
                image = finishIron;
            }
            else if (currentClub.equals("Wedge")) {
                image = finishWedge;
            }
            else {
                image = bsPutter;
            }
        }
        else {

            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    break;
            }
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}
