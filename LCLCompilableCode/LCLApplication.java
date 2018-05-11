import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import compoentsImages.Gates;
public class LCLApplication {


private JFrame frame;//Frame that will contain the circuit panel.
private LCLCircuitPanel circuit;//Panel containing the circuit.
private JScrollPane scrollableCircuit;//Used to make the circuit scrollable.
private CircuitBuilder circuitBuilder;//Used to construct circuits form code.
private Gates gates;//This will hold instantiate all the images for the gates.


public static void main(String[] args)
{
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

	gates = new Gates();//Initializes the Gate Images

	String inputDec = "A=1\nB=0\n";
	String circuitDec = "C=AandB\nD=BorC\nG=AnorBandD\nZ=AnorBxorCandD\n";
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
	frame.add(scrollableCircuit);

}

private void run()
{
	frame.revalidate();
	while(true)
{
		frame.repaint();
		try
		{
			Thread.sleep(1000);
		}catch(Exception e){
			System.out.println("Error in run() method.");
		}
	}

}

}