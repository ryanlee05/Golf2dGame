package golf;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class gamePanel extends JPanel{
	
	
	
	final int originalTileSize = 32;  // 32x32 tile size
	final int scale = 4;
	
	final int tileSize = originalTileSize * scale;
	final int maxScreenCol = 14;
	final int maxScreenRow = 6;
	
	final int screenWidth = tileSize * maxScreenCol; // 32 * 4 * 16
	final int screenHeight = tileSize * maxScreenRow; //32 * 4 * 12
	
	public gamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.GREEN.darker().darker());
		this.setDoubleBuffered(true);
		
	}
	
	
}
