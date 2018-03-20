package system;

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class LCLSystem {
	
	
	private JFrame frame; //Frame that will contain the circuit panel.
	private LCLPanel circuitPanel;//Panel containing the circuit.
	
	private int panelOffX;//Offset for the panel's x-position
	private int panelOffY;//Offset for the panel's y-position
	
	
	/**
	 * Main entry point of the LCL system that constructs and draws the logical circuits, as given by the inputs
	 * @param args Will hold a set of input data required to construct the logical circuit. 
	 */
	public static void main(String[] args)
	{
		//NOTE: "args" needs to be used appropriately in the future.
		LCLSystem sys = new LCLSystem();
		sys.setup();
		sys.run();
	}
	
	private void setup()
	{
		frame = new JFrame("LCL Circuit");
		frame.setSize(1280,720);
		frame.setLocation(50,50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		String osName = System.getProperty("os.name").toLowerCase();		
		if(osName.contains("linux"))
		{
			panelOffX = 50;
			panelOffY = 50;
		}
		else if(osName.contains("mac os"))
		{
			panelOffX = 0;
			panelOffY = 50;
		}
		
		
		circuitPanel = new LCLPanel();
		circuitPanel.setSize(1280,720);
		circuitPanel.setLocation(frame.getX()-panelOffX,frame.getY()-panelOffY);
		circuitPanel.setVisible(true);//Will create a boolean flag later for this.
		circuitPanel.setBackground(Color.RED);
		frame.getContentPane().add(circuitPanel);
		
		frame.add(circuitPanel);
		
		
		
	}
	
	private void run() {
		frame.revalidate();
		circuitPanel.revalidate();
		while(true)
		{
			frame.repaint();
		}
		
	} //NOTE: May be changed to have some input arguments.
	
	
	
	/**
	 * 
	 * Special class used to draw the logical circuit in a JFrame.
	 *
	 */
	private static class LCLPanel extends JPanel
	{
		/*public void paint(Graphics g) {
			
			g.setColor(Color.BLUE);
			g.drawRect(50, 100, 40, 20);
		}*/
		
	}
	
	
	
	
	

}
