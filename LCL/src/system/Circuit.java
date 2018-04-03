package system;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import componentsImages.Gates;
import gates.ANDGate;
import gates.LogicalGate;
import gates.NANDGate;
import gates.NORGate;
import gates.ORGate;
import gates.XNORGate;
import gates.XORGate;
import gates.AbstractLogicalGate.GateType;


public class Circuit {
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
	
	public void addFirstComponent(GateType gType,boolean[] inputs)
	{
		Point location = new Point(startXPos + OFFX * size, startYPos + OFFY * size);
		LogicalGate newComponent=null;
		switch(gType)
		{
			case AND: 
				newComponent = new ANDGate(inputs, location, Gates.GateImgs[Gates.AND]);
				break;
			case OR:
				newComponent = new ORGate(inputs,location, Gates.GateImgs[Gates.OR]);
				break;
			case NAND:
				newComponent = new NANDGate(inputs,location, Gates.GateImgs[Gates.NAND]);
				break;
			case NOR:
				newComponent = new NORGate(inputs,location, Gates.GateImgs[Gates.NOR]);
				break;
			case XNOR:
				newComponent = new XNORGate(inputs,location, Gates.GateImgs[Gates.XNOR]);
				break;
			case XOR:
				newComponent = new XORGate(inputs,location, Gates.GateImgs[Gates.XOR]);
				break;
			default:
				System.out.println("Invalid gType given");
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
				newComponent = new ANDGate(inputs, location, Gates.GateImgs[Gates.AND]);
				break;
			case OR:
				newComponent = new ORGate(inputs,location, Gates.GateImgs[Gates.OR]);
				break;
			case NAND:
				newComponent = new NANDGate(inputs,location, Gates.GateImgs[Gates.NAND]);
				break;
			case NOR:
				newComponent = new NORGate(inputs,location, Gates.GateImgs[Gates.NOR]);
				break;
			case XNOR:
				newComponent = new XNORGate(inputs,location, Gates.GateImgs[Gates.XNOR]);
				break;
			case XOR:
				newComponent = new XORGate(inputs,location, Gates.GateImgs[Gates.XOR]);
				break;
			default:
				System.out.println("Invalid gType given");
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
