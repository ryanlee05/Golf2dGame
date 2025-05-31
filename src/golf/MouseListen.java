package golf;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListen implements MouseListener {
	
	private gamePanel gp;

	
	public MouseListen(gamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(!gp.golfBall.hitInProgress) {
			int x = e.getX();
			int y = e.getY();
			
			int screenX = gp.screenWidth / 2; 
	        int screenY = gp.screenHeight / 2;
	        
	        int deltaX = x - screenX;
	        int deltaY = y - screenY;
	        
	        double angleRad = Math.atan2(-deltaY, deltaX);
	        double angleDegrees = Math.toDegrees(angleRad);
	        
	        if(angleDegrees < 0 ) {
	        	angleDegrees += 360;
	        }
	        
	        
			gp.golfBall.setAngle(angleDegrees);
			
		}
		
		

	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseClicked(e);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
