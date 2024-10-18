package entity;

import java.awt.image.BufferedImage;

public class Entity {  // this will be the super/parent class of the player/character classes

	public int x, y;
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, still;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
}
