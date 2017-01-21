/**
 * @(#)Giant.java
 *
 *
 * @author DYLAN FOX
 * @version 1.00 2015/8/6
 */
 
 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.Math;
 

public class Giant extends Walker {
	

	/*
	 * A GIANT IS A SPECIAL KIND OF ZOMBIE.  THEY ARE SLOW AND BIG BUT ARE VERY DIFFICULT TO KILL
	 */
    public Giant(Player main, Zombies game) 
    {
    	super(main,game);
    	setSideLength(45);
    	setSpeed(2);
    	setMaxHealth(400);
    	setColorRange(new Color(40,80,0), new Color(70,55,0), new Color(100,30,0),new Color(130,5,0));
    	setHealth(super.getMaxHealth());
    }
    //SETS SIDE LENGTH
    public void setSideLength(int length)
    {
    	super.setSideLength(length);
    }
    //SETS MAX HEALTH
    public void setMaxHealth(int newMaxHealth)
    {
    	super.setMaxHealth(newMaxHealth);
    }
    //SETS HEALTH
    public void setHealth(int newHealth)
    {
    	super.setHealth(newHealth);
    }
    //SETS NEW SPEED 
    public void setSpeed(int speed)
    {
    	super.setSpeed(speed);
    }
    //SETS NEW COLOR RANGE 
    public void setColorRange(Color full, Color most, Color half, Color quarter)
    {
    	super.setColorRange(full,most,half,quarter);
    	System.out.println(quarter);
    }
    //RETURNS TYPE OF ZOMBIE 
    public String toString()
    {
    	return "Giant";
    }
    
    
    
}