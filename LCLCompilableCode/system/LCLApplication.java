package system;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import componentsImages.Gates;
import system.*;
import javax.swing.JToolBar;
import javax.swing.JButton;

public class LCLApplication {


private JFrame frame;//Frame that will contain the circuit panel.
private static JFrame editFrame;//Frame that will contain the edit panel.
private static LCLCircuitPanel circuit;//Panel containing the circuit.
private JScrollPane scrollableCircuit;//Used to make the circuit scrollable.
private static CircuitBuilder circuitBuilder;//Used to construct circuits form code.
private Gates gates;//This will hold instantiate all the images for the gates.
private JToolBar toolBar;
private JButton btnEditCircuit;

private static String inputDec;
private static String circuitDec;



public static void main(String[] args)
{
	LCLApplication app = new LCLApplication();
	app.setup();
	app.run();
}

private void setup()
{
	frame = new JFrame("LCL Circuit");
	frame.setSize(1280,720);
	frame.setLocation(50,50);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);

	gates = new Gates();//Initializes the Gate Images

	inputDec = "A=1\nB=0\n";
    circuitDec = "C=AandB\nD=BorC\nG=AnorBandD\nW=AnorBxorCandD\n";
	//Build circuits
	circuitBuilder = new CircuitBuilder(inputDec, circuitDec);
	circuitBuilder.buildCircuits();
	//Build the LCLCircuitPanel
	circuit = new LCLCircuitPanel();
	circuit.setPreferredSize(circuitBuilder.getNumOfCircuits());
	circuit.setLocation(circuit.panelOffset(frame));
	circuit.setVisible(true);
	circuit.addCircuits(circuitBuilder.getCircuits());
	//Create scrollable panel for the LCLCircuitPanel
	scrollableCircuit = new JScrollPane(circuit);
	frame.getContentPane().add(scrollableCircuit);
	
	toolBar = new JToolBar();
	scrollableCircuit.setColumnHeaderView(toolBar);
	
	btnEditCircuit = new JButton("Edit Circuit");
	toolBar.add(btnEditCircuit);
	
	btnEditCircuit.addActionListener(new ActionListener()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	    	setupInput(inputDec, circuitDec);
	    	
	    }
	});

}

private void setupInput(String inputDec, String circuitDec){
	editFrame = new JFrame("LCL Circuit");
	editFrame.setSize(500,325);
	editFrame.setLocation(50,50);
	//editFrame.setDefaultCloseOperation(JFrame.;
	editFrame.setVisible(true);
	
	LCLInputPanel inputsPanel = new LCLInputPanel(inputDec,circuitDec);
	inputsPanel.setVisible(true);
	editFrame.getContentPane().add(inputsPanel);
	
}

//Method called after the user presses the run button when editing the circuit
public static void editCircuit(String inputs, String circuit) throws Exception{
	try {
		
		circuitBuilder.buildDynamicCircuits(inputs, circuit);
		updateCircuitPanel();
		
		//Updates the inputs and circuit
		circuitDec = circuit;
		inputDec = inputs;
		
		//close frame after successfully updated the circuit
		editFrame.dispatchEvent(new WindowEvent(editFrame, WindowEvent.WINDOW_CLOSING));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		throw new Exception(e.getMessage());
	}
}

//Updates the circuit panel
public static void updateCircuitPanel(){
	circuit.addCircuits(circuitBuilder.getCircuits());
	
}



private void run()
{
	frame.revalidate();
	while(true)
{
		frame.repaint();
		try
		{
			Thread.sleep(2000);
		}catch(Exception e){
			System.out.println("Error in run() method.");
		}
	}

}

}