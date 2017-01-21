 /**
 * @(#)Mainframe.java
 *
 *
 * @author 
 * @version 1.00 2015/11/16
 */


 //MAIN CLASS OF ZOMBIES GAME; COMPILE TO BEGIN
 //MADE BY DYLAN FOX
 import java.awt.*;
 import javax.swing.*;
 import java.awt.event.*;
 import java.util.ArrayList;
 import java.lang.Math;
 import java.util.Scanner;
 import java.io.File;
 import java.io.FileNotFoundException;

public class Mainframe {
	
	//FINAL WINDOW DIMENSIONS
	public final static int WINDOW_X = 700;
    public final static int WINDOW_Y = 700;
    
    //NUMBER OF WALKERS, RUNNERS, AND GIANTS
    private int nW;
    private int nR;
    private int nG;
    
    //ROUND NUMBER
    private int round;
    
    /* CONSTUCTOR FOR MAINFRAME CLASS
     * FINDS ROUNDATA FILE IN FOLDER AND READS THE ROUND NUMBER AND NUMBER OF ZOMBIES 
     * CALLS RUN() METHOD WITH THE SPECIFIED PARAMETERS
     * 
     * THROWS FILENOTFOUND EXCEPTION IF FILE IS UNABLE TO BE LOCATED 
     */
    public Mainframe() throws InterruptedException
    {
    	try{
			File roundData = new File("RoundData.txt");
    		Scanner chopper = new Scanner(roundData);
    		JOptionPane.showMessageDialog(null, "Ready To Begin?");
    		while(chopper.hasNext())
    		{
	    		round = chopper.nextInt();
	    		nW = chopper.nextInt();
	    		nR = chopper.nextInt();
	  			nG = chopper.nextInt();
	  			
	    		run(round,nW,nR,nG);
    		}
    		JOptionPane.showMessageDialog(null, "Nice Job!");
    		
    	}catch(FileNotFoundException e){
    		System.out.println("File Not Found");
    	}
    }
    
    //CREATES INSTANCE OF MAINFRAME
     public static void main(String[] args) throws InterruptedException
    {

    	Mainframe mothership = new Mainframe();
		
    }
    /*
     * PARAM: ROUND NUMBER, NUMBER OF WALKERS, RUNNERS, AND GIANTS
     * CREATES NEW FRAME AND ZOMBIES OBJECT WITH THE SPECIFIED PARAMETERS 
     */
    public void run(int round, int numWalkers, int numRunners, int numGiants) throws InterruptedException
    {
    	String titleString = "Round " + round;
    	JFrame frame = new JFrame(titleString);
    	Zombies game = new Zombies(this, numWalkers, numRunners, numGiants);
    	frame.setSize(WINDOW_X,WINDOW_Y);
    	frame.setVisible(true);
    	frame.setResizable(false);
    	frame.setLocationRelativeTo(null);
    	frame.add(game);
    	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	
    	JOptionPane.showMessageDialog(null, titleString);
    	game.addInitialZombies();
    	while(game.over == false)
    	{
    		game.checkAndUpdate();
    		game.move();
    		game.repaint();
    		Thread.sleep(20);
    	}
    	frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    
}