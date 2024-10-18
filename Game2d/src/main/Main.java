package main;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Celestial Paws");
		
		GamePanel gamePanel = new GamePanel ();
		window.add(gamePanel);
		
		window.pack(); // allows window to fit specified size and layouts of subcomponents
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();
	}

}
