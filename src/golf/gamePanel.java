package golf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * creates the gamepanel that will be used for the game
 */
public class gamePanel extends JPanel implements Runnable {

	final int originalTileSize = 32; // 32x32 tile size
	final int scale = 4;

	final int tileSize = originalTileSize * scale;
	final int maxScreenCol = 7;
	final int maxScreenRow = 6;

	final int screenWidth = tileSize * maxScreenCol; // 32 * 4 * 7
	final int screenHeight = tileSize * maxScreenRow; // 32 * 4 * 12

	// FPS
	int FPS = 60;

	Thread gameThread;

	KeyHandler keyH = new KeyHandler();

	// set players default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 5;

	public gamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.GREEN.darker().darker());
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);

	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();

	}

	@Override
	public void run() {

		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		long drawCount = 0;
		

		while (gameThread != null) {

			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			
			lastTime = currentTime;
			
			
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}

			

		}
	}

	public void update() {
		if (keyH.upPressed) {

			if (playerY != 0) {
				playerY -= playerSpeed;
			}

		}
		if (keyH.downPressed) {
			if (playerY <= 640) {
				playerY += playerSpeed;
			}

		}
		if (keyH.leftPressed) {
			if (playerX != 0) {
				playerX -= playerSpeed;
			}

		}
		if (keyH.rightPressed) {
			if (playerX <= 768) {
				playerX += playerSpeed;
			}
			
		}
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.white);

		g2.fillOval(playerX, playerY, tileSize, tileSize);

		g2.dispose();

	}

}
