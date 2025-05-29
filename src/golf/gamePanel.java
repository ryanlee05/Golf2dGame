package golf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Object.GolfBall;
import Object.Clubs;
import entity.Player;
import tile.TileManager;
import Object.GolfBag;

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
	// WORLD SETTINGS
	public final int maxWorldCol = 54;
	public final int maxWorldRow = 54;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	// FPS
	int FPS = 60;

	Thread gameThread;

	KeyHandler keyH = new KeyHandler();
		
	public Player player = new Player(this, keyH);
	
	public GolfBall golfBall = new GolfBall(this, keyH);
	public Clubs club = new Clubs(this, keyH);
	
	Sound sound = new Sound();
	
	public UI ui = new UI(this);
	
	public GolfBag golfbag = new GolfBag(this);
	

	
	TileManager tileM = new TileManager(this);
	
	public CollisionChecker collisionCheck = new CollisionChecker(this);
	
	//GAME STATE
	public int gameState;
	public final int playState = 1;
	public final int pauseState = 2;
	
	public int holeCount = 1;
	
	



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

		sound.setFile(1);
		sound.play();
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
		
		golfBall.update();
		
		club.update();
		
		
		
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		
		tileM.draw(g2);
		
		player.draw(g2);
		
		golfBall.draw(g2);
		
		ui.drawHoleText(g2);
		
		club.draw(g2);
		golfbag.draw(g2);
		g2.dispose();
		
	

	}
	
	
	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
		
	}
	
	public void stopMusic() {
		sound.stop();
	}
	
	public void playSoundEffect(int i) {
		sound.setFile(i);
		sound.play();
	}

}
