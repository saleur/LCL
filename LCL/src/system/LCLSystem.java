package system;

import java.awt.Graphics;

import javax.swing.JPanel;

public class LCLSystem {
	
	
	
	/**
	 * Main entry point of the LCL system that constructs and draws the logical circuits, as given by the inputs
	 * @param args Will hold a set of input data required to construct the logical circuit. 
	 */
	public static void main(String[] args)
	{
		//NOTE: "args" needs to be used appropriately in the future.
		
		run();
	}
	
	private static void run() {} //NOTE: May be changed to have some input arguments.
	
	
	
	/**
	 * 
	 * Special class used to draw the logical circuit in a JFrame.
	 *
	 */
	private static class LCLPanel extends JPanel
	{
		public void draw(Graphics g) {}
		
	}
	
	
	
	
	

}
