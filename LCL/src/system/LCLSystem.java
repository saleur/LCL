package system;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.w3c.dom.css.Rect;



public class LCLSystem {
	
	
	private JFrame frame; //Frame that will contain the circuit panel.
	private LCLCircuit circuit;//Panel containing the circuit.
	private JScrollPane scrollCircuit;
	
	private Rectangle[] dummyGates;//For testing and simulation.
	
	private final int GATEWIDTH = 250;
	private final int GATEHEIGHT = 150;
	private final int GATEMIDY = 30;
	

	
	
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
		//frame.getContentPane().setLayout(null);

		
		
		circuit = new LCLCircuit();
		
		//NOTE: For the parameters of cpDimension some calculation must be done instead of using static numbers.
	    Dimension cpDimension = new Dimension(8000, 8000);//Will define the boundary of the circuitPanel.
	    
		circuit.setPreferredSize(cpDimension);//
		circuit.setLocation(circuit.panelOffset(frame));
		circuit.setVisible(true);//Will create a boolean flag later for this.
		circuit.setBackground(Color.RED);
		
		
		dummyGates = new Rectangle[50];
		
		buildCircuit(dummyGates);
		
		circuit.setGates();
		
			
	    scrollCircuit = new JScrollPane(circuit);//With this line the circuit is now scrollable.	 
		
		frame.add(scrollCircuit);
		 
		
	}
	
	private void run() {
		frame.revalidate();
		
		//circuitPanel.revalidate();
		while(true)
		{
			frame.repaint();
			try
			{
				Thread.sleep(1000);
			}catch (Exception e) {
				System.out.println("Error in run() method.");
			}
		}
		
	} 
	
	/**
	 * Alpha version of the method, will be later modified for logical gates.
	 */
	private void buildCircuit(Rectangle[] gates)
	{
		//NOTE: Assumption of only one circuit to build.
		
		int startX = 20;//Reference starting x-position of circuit.
		int startY = 40;//Reference starting y-position of circuit.
				
		int offX = GATEWIDTH;//Offset for x-position
		int offY = GATEMIDY;//Offset for y-position;		
		
		for(int i = 0; i < gates.length; i++)
			gates[i] = new Rectangle(startX+offX*i,startY+offY*i,GATEWIDTH,GATEHEIGHT);		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
