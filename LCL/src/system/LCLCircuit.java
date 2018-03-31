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
	private static final int AND = 0;
	private static final int OR = 1;
	private static final int XOR = 2;
	private static final int NAND = 3;
	private static final int NOR = 4;
	private static final int XNOR = 5;
	
    private Circuit testCircuit;
	
	
	private static BufferedImage[] gateImages;
	
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
		
		testCircuit = new Circuit(20,40);
		
	
	}
	

	public void setGates()
	{
		testCircuit.addComponent(GateType.AND, new boolean[] {true,false});
		testCircuit.addComponent(GateType.AND, new boolean[] {false,true});
		testCircuit.addComponent(GateType.AND, new boolean[] {false,true});
	}
	
	public void paint(Graphics g) {
		
		g.setColor(Color.BLUE);
		//for(Rectangle r : dummyGates)
			//g.drawRect(r.x, r.y, r.width, r.height);
		
		testCircuit.drawCircuit(g);
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
	
	
	private static class Circuit {
		
		private ArrayList<LogicalGate> components;
		private int size;
		private int startXPos;
		private int startYPos;
		private final int OFFX = 250;
		private final int OFFY = 33;
		
		
		public Circuit(int startXPos,int startYPos)
		{
			this.startXPos = startXPos;
			this.startYPos = startYPos;
			components = new ArrayList<>();
			size = 0;
		}
		
		public void addComponent(GateType gType,boolean[] inputs)
		{
			Point location = new Point(startXPos + OFFX * size, startYPos + OFFY * size);
			LogicalGate newComponent=null;
			switch(gType)
			{
				case AND: 
					newComponent = new ANDGate(inputs, location, LCLCircuit.gateImages[AND]);
					break;
				default:
					System.out.println("Need to implement the rest of the gates");
					break;
			}
			
			components.add(newComponent);
			
			size++;
		}
		
		public void addComponent(GateType gType,boolean input)
		{
			Point location = new Point(startXPos + OFFX * size, startYPos + OFFY * size);
			LogicalGate newComponent=null;
			boolean[] inputs  = new boolean[] {components.get(size-1).output(),input};
			switch(gType)
			{
				case AND: 
					newComponent = new ANDGate(inputs, location, LCLCircuit.gateImages[AND]);
					break;
				default:
					System.out.println("Need to implement the rest of the gates");
					break;
			}
			components.add(newComponent);
			size ++;
			
		}
		
		public void drawCircuit(Graphics g)
		{
			for(LogicalGate gate : components)
				gate.draw(g);
		}
		
	    
	}
	
}
