package golf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

/**
 * creates the gamepanel that will be used for the game
 */
public class gamePanel extends JPanel implements Runnable {

	final int originalTileSize = 32; // 32x32 tile size
	final int scale = 2;

	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 14;
	public final int maxScreenRow = 12;

	public final int screenWidth = tileSize * maxScreenCol; // 32 * 2 * 12
	public final int screenHeight = tileSize * maxScreenRow; // 32 * 2 * 14

	// FPS
	int FPS = 60;

	Thread gameThread;

	KeyHandler keyH = new KeyHandler();
	
	Player player = new Player(this, keyH);
	
	TileManager tileM = new TileManager(this);

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
		player.update();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		
		tileM.draw(g2);

		player.draw(g2);

		g2.dispose();
		
	

	}

}
