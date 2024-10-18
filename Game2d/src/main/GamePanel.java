package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	//SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; //48 x 48 tile that will be displayed
	public final int maxScreenCol = 16; //16 tiles horizontally
	public final int maxScreenRow= 12;  //12 tiles vertically
	public final int screenWidth = tileSize * maxScreenCol; // 48 x 16 = 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 48 x 12 = 576 pixels
	
	// FPS
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread; // keeps programming running until closed, when thread is started it calls the run method
	Player player = new Player(this,keyH); // instantiate player class
	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.pink);
		this.setDoubleBuffered(true); //improves rendering performance 
		this.addKeyListener(keyH);
		this.setFocusable(true); // this allows the game panel to be 'focused' to recieve key input
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this); // 'this' is gamepanel, we are passing GamePanel through this constructor
		gameThread.start(); // this will run the run method
	}

	@Override	
	public void run() { // update and repaint are called every 0.01666 seconds
		
		double drawInterval = 1000000000/FPS; // 1billion nanoseconds/fps (60) = 0.01666 seconds <- how fast the state refreshes
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) { // as long gamethread exists it loops process written inside these brackets
			
			currentTime = System.nanoTime(); // check current time
			
			delta += (currentTime - lastTime) / drawInterval; //minus lastTime from current time, basically how much time has passed
			// its divided by drawinterval and then added to delta
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) { //when delta = 1 (drawinterval) then we run the update and repaint loop
				// 1 update: update info such as sprite info
				update();
				
				// 2 update: draw screen with updated info
				repaint();	
				delta--; // then delta is reset
				drawCount++;
			}	
			if(timer >= 1000000000) {
				System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	public void update() {
		
		player.update(); // call this method from player class
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); // super means the parents class of the JPanel class
		Graphics2D g2 = (Graphics2D)g;
		tileM.draw(g2); // will draw the background tiles
		player.draw(g2); // call this method from player class, will draw the sprite
		
		g2.dispose();
	}
}




