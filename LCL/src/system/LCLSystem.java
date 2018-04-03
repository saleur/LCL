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

import componentsImages.Gates;



public class LCLSystem {
	
	
	private JFrame frame; //Frame that will contain the circuit panel.
	private LCLCircuit circuit;//Panel containing the circuit.
	private JScrollPane scrollableCircuit;//Used to make the circuit scrollable.
	
	private CircuitBuilder circuitBuilder;//Used to construct circuits form code.
	
	private Gates gates;

	
	
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
	    
	    gates = new Gates();
	    
	    
		circuit.setPreferredSize(cpDimension);//
		circuit.setLocation(circuit.panelOffset(frame));
		circuit.setVisible(true);//Will create a boolean flag later for this.
		circuit.setBackground(Color.RED);
		
		circuitBuilder = new CircuitBuilder("testCode.txt");
		circuitBuilder.buildCircuit();
		
		
		
		//circuit.setGates();
		circuit.addCircuits(circuitBuilder.getCircuits());
			
	    scrollableCircuit = new JScrollPane(circuit);//With this line the circuit is now scrollable.	 
		
		frame.add(scrollableCircuit);
		 
		
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
	

	
	
	
	
	
	
	
	
	
	
	
	

}
