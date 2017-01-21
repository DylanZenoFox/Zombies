/**
 * @(#)Bullet.java
 *
 *
 * @author DYLAN FOX
 * @version 1.00 2015/8/4
 */
 
import java.awt.*;
import javax.swing.*;

public class Bullet {
	
	//FINAL SIZE OF A BULLET
	private final static int BULLET_SIZE = 10;
	//COORDINATES OF THE BULLET
	private int x;
	private int y;
	//SPEED OF THE BULLET
	private int dx;
	private int dy;
	//DAMAGE BULLET GIVES 
	private int damage;
	//COLOR OF BULLET
	private Color defaultColor;

	/*
	 *PARAM: INITIAL SPAWN COORDINATES, DIRECTION THAT BULLET WILL BE TRAVELING IN
	 */
    public Bullet(int spawnedX, int spawnedY, int spawnedDx, int spawnedDy) 
    {
    	x = spawnedX;
    	y = spawnedY;
    	dx = spawnedDx;
    	dy = spawnedDy;
    	defaultColor = Color.RED;
    	
    	damage = 50;
    }
    
    //PAINTS BULLETS 
    public void paint(Graphics2D g)
    {
    	g.setColor(defaultColor);
    	g.fillRect(x,y,BULLET_SIZE,BULLET_SIZE);
    }
    //RETURNS X COORDINATE
    public int getXCoor()
    {
    	return x;
    }
    //RETURNS Y COORDINATE
    public int getYCoor()
    {
    	return y;
    }
    //RETURNS BULLET DAMAGE
    public int getDamage()
    {
    	return damage;
    }
    
    //MOVES BULLET
    public void move()
    {
    	x = x + dx;
    	y = y + dy;
    }
    
    //RETURNS RECTANGLE REPRESENTING HITBOX OF BULLET
    public Rectangle getBounds()
    {
    	return new Rectangle(x,y,BULLET_SIZE,BULLET_SIZE);
    }
}