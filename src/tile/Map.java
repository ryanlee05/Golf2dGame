package tile;

import java.awt.image.BufferedImage;
import golf.gamePanel;

public class Map extends TileManager{
    gamePanel gp;
    BufferedImage worldMap[];
    public boolean miniMapOn = false;
    
    
    public Map(gamePanel gp) {
        super(gp);
        this.gp = gp;
    }
    
    public void createWorldMap() {
        
    }

}