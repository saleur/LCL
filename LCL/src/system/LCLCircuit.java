package system;

import java.awt.Color;
import gates.AbstractLogicalGate.GateType;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gates.*;

public class LCLCircuit extends JPanel {
	
	//These constants work as indexes for the gateImages array.
	//NOTE: Might move this to LCLSystem class.
	protected static final int AND = 0;
	protected static final int OR = 1;
	protected static final int XOR = 2;
	protected static final int NAND = 3;
	protected static final int NOR = 4;
	protected static final int XNOR = 5;
	
    private Circuit testCircuit;
    
    private ArrayList<Circuit> circuits;
	
	
	protected static BufferedImage[] gateImages;//NOTE: Might move this to LCL System class.
	
	private Rectangle[] dummyGates;
	
	public LCLCircuit()
	{
		super();
		gateImages = new BufferedImage[6];
		
		try
		{
			gateImages[AND] = ImageIO.read(this.getClass().getResource("/componentsImages/ANDComponent.png"));
			gateImages[OR] = ImageIO.read(this.getClass().getResource("/componentsImages/ORComponent.png"));
			gateImages[XOR] = ImageIO.read(this.getClass().getResource("/componentsImages/XORComponent.png"));
			gateImages[NAND] = ImageIO.read(this.getClass().getResource("/componentsImages/NANDComponent.png"));
			gateImages[NOR] = ImageIO.read(this.getClass().getResource("/componentsImages/NORComponent.png"));
			gateImages[XNOR] = ImageIO.read(this.getClass().getResource("/componentsImages/XNORComponent.png"));
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println("IO File Error in creation of images for gates");
			System.out.println("Look at LCLCircuit Contructor for debugging");			
		};
		
		
		
	
	}
	
	public void addCircuits(ArrayList<Circuit> circuits)
	{
		this.circuits = circuits;
	}
	

	public void setGates()
	{
		testCircuit.addFirstComponent(GateType.XOR, new boolean[] {false,true});
		testCircuit.addComponent(GateType.XNOR,false);
		testCircuit.addComponent(GateType.NOR, true);
		testCircuit.addComponent(GateType.AND, false);

	}
	
	public void paint(Graphics g) {
		
		g.setColor(Color.BLUE);
		//for(Rectangle r : dummyGates)
			//g.drawRect(r.x, r.y, r.width, r.height);
		
		//testCircuit.drawCircuit(g);
		
		for(Circuit c : circuits)
			c.drawCircuit(g);
	}
	
	public Point panelOffset(JFrame frame)
	{
		String osName = System.getProperty("os.name").toLowerCase();
		
		int panelOffX,panelOffY;
		
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
		else
		{
			panelOffX = 0;
			panelOffY = 0;
		}
		
		return new Point(frame.getX()-panelOffX,frame.getY()-panelOffY);
	}
	
	
	
	
}
