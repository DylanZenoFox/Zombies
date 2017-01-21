 /**
 * @(#)
 * Zombies.java
 *
 * Zombies application
 *
 * @author DYLAN FOX
 * @version 1.00 2015/7/14
 */
 

 import java.awt.*;
 import javax.swing.*;
 import java.awt.event.*;
 import java.util.ArrayList;
 import java.lang.Math;
 
public class Zombies extends JPanel implements KeyListener {
    
	//FINAL SPEED OF ALL BULLETS 
    public final static int BULLET_SPEED = 20;
    
    //CREATES AVATAR IN ZOMBIES GAME
    Player one = new Player(this);
    
    //CREATES ARRAYLIST FOR ALL ZOMBIES AND BULLETS
    private ArrayList<Walker> zombList;
    private ArrayList<Bullet> bullets;
    
    //NUMBER OF WALKERS, RUNNERS, AND GIANTS
    private int numWalkers;
    private int numGiants;
    private int numRunners;
    
    //TRUE IF GAME IS NOT IN SESSION
    public boolean over;
	
    public Mainframe mother;
    
    /*
     * INITIAL CONSTUCTOR FOR ZOMBIE OBJECTS
     * PARAM: MAINFRAME OBJECT, NUMBER OF WALKERS, RUNNERS, AND GIANTS
     */
    public Zombies(Mainframe mother, int nW, int nR, int nG) 
    {
    	this.mother = mother;
    	over = false;
        zombList = new ArrayList<Walker>();
        bullets = new ArrayList<Bullet>();
        numWalkers = nW;
        numGiants = nG;
        numRunners = nR;
    	setFocusable(true);
    	addKeyListener(this);
    	System.out.println("added keyListener");
    }
	
	//MOVES ALL OBJECTS IN THE WINDOW
    public void move()
    {
    	one.move();
    	moveZombies();
    	moveBullets();
    }
    
    //CHECKS THE VARIOUS STATUSES OF DIFFERENT OBJECTS IN ZOMBIES GAME
    public void checkAndUpdate()
    {
    	checkIfWon();
    	checkIfKilled();
    	checkIfShot();
    	checkIntersections();
    	garbageDisposal();
    }
    
    //PAINTS ALL OBJECTS IN GAME
    public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	setBackground(Color.BLACK);
    	Graphics2D g2 = (Graphics2D) g;
    	one.paint(g2);
    	paintZombies(g2);
    	paintBullet(g2);
    	paintScoreBoard(g2);
    }
    
    //CLEARS OFF GARBAGE; IE: BULLETS THAT WOULD GO FOREVER
    public void garbageDisposal()
    {
    	killStrayBullets();
    }
    
    //SPAWNS A BULLET IN A GIVEN DIRECTION AND ADDS IT TO THE ARRAYLIST BULLET
    public void spawnBullet(int index)
    {
    	if(index == 1)
    	{
    		bullets.add(new Bullet(one.getXCoor()+(int)one.getPlayerSize()[1]/3,one.getYCoor(),0,-BULLET_SPEED));
    	}
    	if(index == 2)
    	{
    		bullets.add(new Bullet(one.getXCoor()+(int)one.getPlayerSize()[1]/3,one.getYCoor() + one.getPlayerSize()[0],0,BULLET_SPEED));
    	}
    	if(index == 3)
    	{
    		bullets.add(new Bullet(one.getXCoor()+one.getPlayerSize()[1],one.getYCoor() + (int)one.getPlayerSize()[0]/3,BULLET_SPEED,0));
    	}
    	if(index == 4)
    	{
    		bullets.add(new Bullet(one.getXCoor(),one.getYCoor() + (int)one.getPlayerSize()[0]/3,-BULLET_SPEED,0));
    	}
    	System.out.println("Bullet Spawned");
    }
    
    //IF ANY BULLETS GO OFF THE SCREEN, THEN WE DELETE THEM FROM THE ARRAYLIST BULLETS
    public void killStrayBullets()
    {
    	for(int x = 0; x < bullets.size(); x++)
    	{
    		if(bullets.get(x).getXCoor() > mother.WINDOW_X || bullets.get(x).getXCoor() < 0 || bullets.get(x).getYCoor() > mother.WINDOW_Y || bullets.get(x).getYCoor() < 0)
    		{
    			System.out.println("Killing Bullets");
    			killBullet(bullets.get(x));
    		}
    	}
    }
    
    //REMOVES A BULLET FROM ARRAYLIST BULLET
    public void killBullet(Bullet kill)
    {
    	bullets.remove(kill);
    }
    
    //PAINTS BULLETS 
    public void paintBullet(Graphics2D b)
    {
    	for(int t =0; t < bullets.size(); t++)
    	{
    		bullets.get(t).paint(b);
    	}
    }
    
    //PAINTS THE SCOREBOARD AT THE BOTTOM OF THE SCREEN
    public void paintScoreBoard(Graphics2D s)
    {
    	s.setFont(new Font("Courier New",1,60));
    	s.setColor(Color.GRAY);
    	if(one.getBulletsLeft() != 0)
    	{
    		s.drawString("" + one.getBulletsLeft(),mother.WINDOW_X - 85,mother.WINDOW_Y - 60);
    	}
    	else 
    	{
    		s.setFont(new Font("Courier New",1, 30));
    		s.setColor(Color.RED);
    		s.drawString("RELOAD (SPACE)",mother.WINDOW_X - 300,mother.WINDOW_Y - 60);
    	}
    	
    	
    }
    
    //MOVES BULLETS
    public void moveBullets()
    {
    	for(int t =0; t < bullets.size(); t++)
    	{
    		bullets.get(t).move();
    	}
    }
    
    //ADDS ALL ZOMBIES TO THE STARTING POSITIONS AT BEGINNING OF MATCH
    public void addInitialZombies()
    {
    	for(int x = 0; x < numWalkers; x ++)
    	{
    		zombList.add(new Walker(one, this));
    	}
    	for(int y = 0; y < numGiants;y++)
    	{
    		zombList.add(new Giant(one,this));
    	}
    	for(int z = 0; z < numRunners; z++)
    	{
    		zombList.add(new Runner(one,this));
    	}
    }
    //MOVES ZOMBIES 
    public void moveZombies()
    {
    	for(int y = 0; y < zombList.size(); y++)
    	{
    		zombList.get(y).move();
    	}
    }
    //PAINTS ALL ZOMBIES
    public void paintZombies(Graphics2D z)
    {
    	for(int p = 0; p < zombList.size(); p++)
    	{
    		zombList.get(p).paint(z);
    	}
    }
    //REMOVES ZOMBIE FROM ARRAYLIST ZOMBLIST
    public void killZombie(Walker dead)
    {
    	zombList.remove(dead);
    }
    //CHECKS TO SEE IF TWO ZOMBIES ON SCREEN ARE COLLIDING.
    //IF THEY ARE, THEN STOP ONE OF THEM SO THEY DO NOT OVERLAP INTO ONE BLOB OF 10 ZOMBIES
    public void checkIntersections()
    {
    	for(int c = 0; c< zombList.size(); c++)
    	{
    		for(int q = c+1; q < zombList.size(); q++)
    		{
    			if(zombList.get(c).getBounds().intersects(zombList.get(q).getBounds()) && getFastSlow(zombList.get(c),zombList.get(q))[0].getMovingStatus() == true)
    			{
    				getFastSlow(zombList.get(c),zombList.get(q))[1].stopMoving();
    			}
    		}
    	}
    }
    //CHECKS TO SEE IF ZOMBLIST IS 0;
    //IF SO, YOUVE WON! CONGRATS!
    public void checkIfWon()
    {
    	if(zombList.size() == 0)
    	{
    		JOptionPane.showMessageDialog(null,"Round Finished");
    		over = true;
    	}
    }
    //CHECK TO SEE IF A ZOMBIE HAS BEEN SHOT
    public void checkIfShot()
    {
    	for(int b = 0; b < bullets.size(); b++)
    	{
    		for(int z = 0; z < zombList.size(); z++)
    		{
    			if(bullets.get(b).getBounds().intersects(zombList.get(z).getBounds()))
    			{
    				int reduceHealth = bullets.get(b).getDamage();
    				killBullet(bullets.get(b));
    				zombList.get(z).reduceHealth(reduceHealth);
    				if(zombList.get(z).getHealth() <= 0)
    				{
    					killZombie(zombList.get(z));
    				}
    				return;
    			}	
    		}
    	}
    }
    //CHECK TO SEE IF THE PLAYER HAS BEEN TOUCHED BY A ZOMBIE
    public void checkIfKilled()
    {
    	for(int z =0;z < zombList.size(); z++)
    	{
    		if(zombList.get(z).getBounds().intersects(one.getBounds()))
    		{
    			JOptionPane.showMessageDialog(null, "You've Been Killed \n Game Over");
    			System.exit(1);
    		}
    	}
    }
    //OUT OF 2 WALKERS, RETURNS AN ARRAY OF BOTH WALKERS ORDERED ON SPEED
    public Walker[] getFastSlow(Walker one,Walker two)
    {
    	Walker[] speeds = new Walker[2]; 
    	if(one.getSpeed() < two.getSpeed())
    	{
    		speeds[0] = two;
    		speeds[1] = one;
    	}
    	else 
    	{
    		speeds[0] = one;
    		speeds[1] = two;
    	}
    	return speeds;
    }
    
    //CHECKS IF KEY IS PRESSED
    public void keyPressed(KeyEvent e)
    {
    	one.keyPressed(e);
    }
    
    //CHECKS IF KEY IS RELEASED
    public void keyReleased(KeyEvent e)
    {
    	one.keyReleased(e);
    }
    
    //CHECKS IF KEY IS TYPED
    public void keyTyped(KeyEvent e)
    {
    	one.keyTyped(e);
    }
	}
