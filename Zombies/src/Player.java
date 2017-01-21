/**
 * @(#)Player.java
 *
 *
 * @author 
 * @version 1.00 2015/8/2
 */


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

 
public class Player{
	
	//FINAL DIMENSIONS OF THE AVATAR
	private final static int PLAYER_HEIGHT = 30;
	private final static int PLAYER_WIDTH = 30;
	
	//COORDINATES OF PLAYER
	private int x = 0;
	private int y = 0;
	//SPEEDS IN THE 4 CARDINAL DIRECTIONS
	private int dxR = 0;
	private int dxL = 0;
	private int dyU = 0;
	private int dyD = 0;
	//SIZE OF CLIP
	private int clipSize = 10;
	//BULLETS LEFT IN CLIP
	private int bulletsLeft = clipSize;
	//PLAYER SPEED 
	private int playerSpeed = 6;
	//IS PLAYER READY TO FIRE?
	private boolean canFire = true;
	private Zombies game;
	
	
	//CONSTRUCTOR FOR THE PLAYER'S AVATAR DURING GAME
    public Player(Zombies game)
    {
    	this.game = game;
    	x = game.mother.WINDOW_X/2 - PLAYER_WIDTH/2;
    	y = game.mother.WINDOW_Y/2 - PLAYER_HEIGHT/2;
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
    //RETURNS THE SIZE OF THE PLAYER
    public int[] getPlayerSize()
    {
    	int[] size = new int[2];
    	size[0] = PLAYER_HEIGHT;
    	size[1] = PLAYER_WIDTH;
    	
    	return size;
    }
    //PAINTS THE PLAYER
    public void paint(Graphics2D g2)
    {
    	g2.setColor(Color.BLUE);
    	g2.fillRect(x,y,PLAYER_WIDTH,PLAYER_HEIGHT);
    }
    //MOVES THE PLAYER
    public void move()
    {
    	if(dxR - dxL + x >= 0 && dxR - dxL + x <= game.mother.WINDOW_X - PLAYER_WIDTH - 7)
    	{
    		x = dxR - dxL + x;
    	}
    	if(dyD - dyU + y >= 0 && dyD - dyU + y <= game.mother.WINDOW_Y - PLAYER_HEIGHT - 35)
    	{
    		y = dyD - dyU +y;
    	} 
    }
    //PLAYER IS NOT ABLE TO FIRE BULLETS 
    public void coolingDown()
    {
    	canFire = false;
    }
    //PLAYER CAN FIRE BULLETS 
    public void gunHot()
    {
    	canFire = true;
    }
    //ONE LESS BULLET IN THE CLIP
   	public void reduceBulletsLeft()
   	{
   		bulletsLeft --;
   	}
   	//REFILLS THE CLIP TO MAX CAPACITY
   	public void reload()
   	{
   	//	Timer timer = new Timer(3000,new ActionListener() {
   		//	public void actionPerformed(ActionEvent a){
   		//		bulletsLeft = clipSize;
   		//		timer.stop();
   		
   	//	});
   	//	timer.setInitialDelay(3000)
   	//	timer.start();
   	
   	//Thread.sleep(3000);
  		  bulletsLeft = clipSize;
    
   	}
   	//RETURNS THE AMOUNT OF BULLETS LEFT IN CLIP
   	public int getBulletsLeft()
   	{
   		return bulletsLeft;
   	}
   	//RETURNS RECTANGLE REPRESENTING HITBOX OF PLAYER
    public Rectangle getBounds()
    {
    	return new Rectangle(x,y,PLAYER_HEIGHT,PLAYER_WIDTH);
    }
    //THESE HAPPEN WHEN KEY IS PRESSED 
    public void keyPressed(KeyEvent e)
    {
    	 //SWAD KEYS TRIGGER MOTION 
    	 if(e.getKeyCode() == KeyEvent.VK_S)
    	 {
    		dyD = playerSpeed;
    	 }
    	 if(e.getKeyCode() == KeyEvent.VK_W)
    	 {
    	  	dyU = playerSpeed;
    	 }
    	 if(e.getKeyCode() == KeyEvent.VK_A)
    	 {
    	   	dxL = playerSpeed;
    	 }
    	 if(e.getKeyCode() == KeyEvent.VK_D)
    	 {
    	  	dxR = playerSpeed;
    	 }
    	 //UP, DOWN, LEFT, AND RIGHT KEYS SHOOT BULLETS IN EACH DIRECTION
    	 if(e.getKeyCode() == KeyEvent.VK_UP)
    	 {
    	 	if(canFire == true && bulletsLeft !=0)
    	 	{
    	 		game.spawnBullet(1);
    	 		reduceBulletsLeft();
    	  		coolingDown();
    	 	}
    	 }   	
    	 if(e.getKeyCode() == KeyEvent.VK_DOWN)
    	 {
    	 	if(canFire == true && bulletsLeft != 0)
    	 	{
    	 		game.spawnBullet(2);
    	 		reduceBulletsLeft();
    	  		coolingDown();
    	 	}
    	 }   	
    	 if(e.getKeyCode() == KeyEvent.VK_RIGHT)
    	 {
    	 	if(canFire == true && bulletsLeft != 0)
    	 	{
    	 		game.spawnBullet(3);
    	 		reduceBulletsLeft();
    	   		coolingDown();
    	 	}
    	 }   	
    	 if(e.getKeyCode() == KeyEvent.VK_LEFT)
    	 {
    	 	if(canFire == true && bulletsLeft != 0)
    	 	{
    	 		game.spawnBullet(4);
    	 		reduceBulletsLeft();
    	   		coolingDown();
    	 	}
    	 }
    	 //SPACE BUTTON RELOADS
    	 if(e.getKeyCode() == KeyEvent.VK_SPACE)
    	 {
    	 	reload();
    	 }   	
    }
     
    public void keyTyped(KeyEvent e){}
    
    //THESE HAPPEN WHEN ANY KEYS ARE RELEASED 
    public void keyReleased(KeyEvent e)
    {
    	//WHEN SWAD BUTTONS ARE RELEASED, SET SPEED TO 0
    	if(e.getKeyCode() == KeyEvent.VK_A)
    	{
    		dxL = 0;
    	}
    	if(e.getKeyCode() == KeyEvent.VK_D)
    	{
    		dxR = 0;
    	}
    	if(e.getKeyCode() == KeyEvent.VK_W)
    	{
    		dyU = 0;
    	}
    	if(e.getKeyCode() == KeyEvent.VK_S)
    	{
    		dyD = 0;
    	}
    	//WHEN FIRING BUTTONS ARE RELEASED, ALLOWS BUTTON TO BE PRESSED AGAIN.  
    	//THIS MAKES SURE THERE IS NO PRESSING DOWN RAPID FIRE
    	if(e.getKeyCode() == KeyEvent.VK_UP)
    	{
    		gunHot();
    	}
    	if(e.getKeyCode() == KeyEvent.VK_DOWN)
    	{
    		gunHot();
    	}
    	if(e.getKeyCode() == KeyEvent.VK_LEFT)
    	{
    		gunHot();
    	}
    	if(e.getKeyCode() == KeyEvent.VK_RIGHT)
    	{
    		gunHot();
    	}
    	
    }
 }