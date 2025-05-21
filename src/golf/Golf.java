package golf;

import javax.swing.JFrame;

/**
 * main method for our golf game. Creates the window and runs the game thread.
 * 
 * @author ryanlee05, sagelin
 * @version 21 May 2025
 */
public class Golf {

	/**
	 * main class for golf. creates the simulation, etc.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {

		// establish a new window
		JFrame window = new JFrame("Arise to Golf");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
	

		// create a gamePanel
		gamePanel panel = new gamePanel();

		// add the gamePanel to the window
		window.add(panel);

		window.pack();
		
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		// start a game thread
		panel.startGameThread();
		
		

	}

}
