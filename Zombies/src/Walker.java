/**
 * @(#)Walker.java
 *
 *
 * @author DYLAN FOX
 * @version 1.00 2015/8/2
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.Math;
import java.util.Arrays;
 

public class Walker {
	
	//COORDINATES OF ZOMBIES 
	private int x;
	private int y;
	//SPEEDS OF ZOMBIES IN X AND Y DIRECTIONS
	private int dx;
	private int dy;
	//LENGTH OF ZOMBIES 
	private int sideLength;
	//ZOMBIE HEALTH VARIABLES
	private int health;
	private int maxHealth;
	//ZOMBIE SPEED
	private int speed;
	//IS THE ZOMBIE MOVING?
	private boolean moving;
	//RANGE OF COLORS AS ZOMBIES GET HURT
	private Color[] colorRange;
	//GAME AND MAINFRAME OF GAME
	private Player main;
	private Zombies game;
	
	//INITIAL CONSTRUCTOR FOR A BASIC ZOMBIE: THE WALKER
    public Walker(Player main, Zombies game) 
    {
    	this.main = main;
    	this.game = game;
    	colorRange = new Color[4];
    	setSpeed(3);
    	setMaxHealth(200);
    	setHealth(maxHealth);
    	setColorRange(new Color(100,140,100), new Color(180,50,50), new Color(160,0,0), new Color(100,0,0));
 
    	dx = 0;
    	dy = 0;
    	moving = true;
    	
    	setSideLength(30);
    	
    	setInitialCoor();
    }
    //SETS THE DIMENSIONS OF THE SQUARE ZOMBIES 
    public void setSideLength(int length)
    {
    	sideLength = length;
    }
    //SETS THE MAX HEALTH OF THE WALKER
    public void setMaxHealth(int newMaxHealth)
    {
    	maxHealth = newMaxHealth;
    }
    
    //CHANGES THE HEALTH OF THE ZOMBIE TO NEWHEALTH
    public void setHealth(int newHealth)
    {
    	health = newHealth;
    }
    
    //RETURNS THE MAX HEALTH VALUE
    public int getMaxHealth()
    {
    	return maxHealth;
    }
    
    //SETS THE SPEED OF THE ZOMBIE
    public void setSpeed(int speed)
    {
    	this.speed = speed;
    }
    
    /*
     *PARAM: ZOMBIE COLOR WITH FULL HEALTH, THREE QUARTER HEALTH, HALF HEALTH, AND QUARTER HEALTH
     *SETS THE COLOR ARRAY COLORRANGE EQUAL TO THESE PARAMETERS
     */
    public void setColorRange(Color fullHealth,Color mildDamage, Color halfHealth, Color quarterHealth)
    {
    	colorRange[0] = fullHealth;
    	colorRange[1] = mildDamage;
    	colorRange[2] = halfHealth;
    	colorRange[3] = quarterHealth;		
    }
    
    //SETS THE INITIAL COORDINATES OF THE ZOMBIE 
    public void setInitialCoor()
    {
    	int rand = (int)(Math.random()*4)+1;
    	if(rand == 1)
    	{
    		x = -(sideLength);
    		y = (int)(Math.random()*game.mother.WINDOW_Y);
    		System.out.println(y);
    	}
    	if(rand == 2)
    	{
    		x = game.mother.WINDOW_X + sideLength;
    		y = (int)(Math.random()*game.mother.WINDOW_Y);
    	}
    	if(rand == 3)
    	{
    		y = -(sideLength);
    		x = (int)(Math.random()*game.mother.WINDOW_X);
    	}
    	if(rand == 4)
    	{
    		y = game.mother.WINDOW_Y + sideLength;
    		x = (int)(Math.random()*game.mother.WINDOW_X);
    	}
    	System.out.println(x + " " + y);
    }
    
    //ALLOWS THE ZOMBIE TO FIND AND ATTACK THE PLAYER 
    public void findPlayer()
    {
    	if(main.getXCoor() + main.getPlayerSize()[1]/2 < x)
    	{
    		dx = -speed ;
    	}
    	else if(main.getXCoor() - main.getPlayerSize()[1]/2 > x)
    	{
    		dx = speed;
    	}
    	else dx = 0;
    	
    	if(main.getYCoor() + main.getPlayerSize()[0]/2 < y)
    	{
    		dy = -speed;
    	}
    	else if(main.getYCoor() - main.getPlayerSize()[0]/2 > y)
    	{
    		dy = speed;
    	}
    	else dy = 0;
    }
    
    //MOVES THE ZOMBIE
    public void move()
    {	
    	if(moving)
    	{
    		findPlayer();
    		x = x + dx;
    		y = y + dy;
    		
    	}
    	moving = true;
    }
    
	//RETURNS THE HEALTH OF THE ZOMBIE    
    public int getHealth()
    {
    	return health;
    }
    //REDUCES THE HEALTH BY A SPECIFIED AMOUNT
    public void reduceHealth(int reduced)
    {
    	health = health - reduced;
    }
    //STOPS A ZOMBIE FROM MOVING;  THIS MAKES SURE THAT THE ZOMBIES DO NOT CONGLOMERATE INTO ONE OVERLAPPING SQUARE
    public void stopMoving()
    {
    	moving = false;
    }
    
    //RETURNS TRUE IS A ZOMBIE IS MOVING
    public boolean getMovingStatus()
    {
    	return moving;
    }
    
    //RETURNS RECTANGLE WITH HITBOX OF ZOMBIE
    public Rectangle getBounds()
    {
    	return new Rectangle(x,y,sideLength,sideLength);
    }
    //RETURNS SPEED 
    public int getSpeed()
    {
    	return speed;
    }
    //PAINTS ZOMBIES IN WINDOW
    public void paint(Graphics2D g2)
    {
    	if(health > (maxHealth*3)/4)
    	{
    		g2.setColor(colorRange[0]);
    	}
    	else if(health <= (maxHealth*3)/4 && health > maxHealth/2)
    	{
    		g2.setColor(colorRange[1]);
    	}
    	else if(health <= maxHealth/2 && health > maxHealth/4)
    	{
    		g2.setColor(colorRange[2]);
    	}
    	else if(health <= maxHealth/4 && health > 0)
    	{
    		g2.setColor(colorRange[3]);
    	}
    
    	g2.fillRect(x,y,sideLength,sideLength);
    }
    //TOSTRING METHOD RETURNING TYPE OF ZOMBIE 
    public String toString()
    {
    	return "Walker";
    }
}