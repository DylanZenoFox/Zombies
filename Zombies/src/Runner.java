/**
 * @(#)Runner.java
 *
 *
 * @author DYLAN FOX
 * @version 1.00 2015/8/6
 */
 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.Math;
 

public class Runner extends Walker {
	

	/*
	 * A RUNNER IS A SPECIAL TYPE OF ZOMBIE, ABLE TO RUN INSTEAD OF JUST WALK.
	 * LUCKILY THEY ARE VERY EASY TO KILL
	 */
    public Runner(Player main, Zombies game) 
    {
    	super(main,game);
    	setSideLength(20);
    	setSpeed(4);
    	setMaxHealth(100);
    	setColorRange(new Color(0,100,100),new Color(50,50,50), new Color(100,20,20), new Color(120,0,0));
    	setHealth(super.getMaxHealth());
    }
    //SETS THE SIDE LENGTH OF THE RUNNER 
    public void setSideLength(int length)
    {
    	super.setSideLength(length);
    }
    //SETS THE MAX HEALTH
    public void setMaxHealth(int newMaxHealth)
    {
    	super.setMaxHealth(newMaxHealth);
    }
    //SETS THE HEALTH
    public void setHealth(int newHealth)
    {
    	super.setHealth(newHealth);
    }
    //SETS THE SPEED 
    public void setSpeed(int speed)
    {
    	super.setSpeed(speed);
    }
    //SETS THE NEW COLOR RANGE 
    public void setColorRange(Color full,Color some, Color half, Color quarter)
    {
    	super.setColorRange(full,some,half,quarter);
    }
    //RETURNS TYPE OF ZOMBIE
    public String toString()
    {
    	return "Runner";
    }
    
    
    
}