package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	
	GamePanel gp;
	Tile[] tile;
	int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[10]; // i will use 10 different tiles
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		
		getTileImage();
		loadMap();
	}
	
	public void loadMap() {
		try {
			InputStream is = getClass().getResourceAsStream("/maps/map.txt"); //inputstream imports the text file
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); //br reads the content of the text file
			
			int col = 0;
			int row = 0;
			while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
				
				String line = br.readLine(); // br will read a single line of the text file and put it into a string
				while(col < gp.maxScreenCol) {
					String numbers[] = line.split(" "); //will split a line and get the tile numbers one by one
					int num = Integer.parseInt(numbers[col]); // changes from string into an integer
					mapTileNum[col][row] = num;
					col++;
					
				}
				if(col == gp.maxScreenCol) {
					col = 0;
					row++;
				}
				
			}
			br.close();
			
		}catch(Exception e) {
			
		}
	}
	public void getTileImage() {
		try {
			tile[0] = new Tile(); //instantiate new tile
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile(); //instantiate new tile
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stone.png"));
			
			tile[2] = new Tile(); //instantiate new tile
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	public void draw(Graphics2D g2) {
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
			
			int tileNum = mapTileNum[col][row]; // all map data is stored here
			
			g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			col++; // tile at 0,0 then col++
			x += gp.tileSize;
			
			if(col == gp.maxScreenCol) { //col increasing until end, then start a new column by making row++
				col = 0;
				x = 0;
				row++;
				y += gp.tileSize;
			}
			
		}
	}

}
