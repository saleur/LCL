package system;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

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
	
	//Note: Added output name to method
	public void addFirstComponent(GateType gType,char[] inputNames,boolean[] inputValues,char outputName)
	{
		Point location = new Point(startXPos + OFFX * size, startYPos + OFFY * size);
		LogicalGate newComponent=null;
		switch(gType)
		{
			case AND: 
				newComponent = new ANDGate(inputNames,inputValues, location, Gates.GateImgs[Gates.AND],outputName);
				break;
			case OR:
				newComponent = new ORGate(inputNames,inputValues,location, Gates.GateImgs[Gates.OR],outputName);
				break;
			case NAND:
				newComponent = new NANDGate(inputNames,inputValues,location, Gates.GateImgs[Gates.NAND],outputName);
				break;
			case NOR:
				newComponent = new NORGate(inputNames,inputValues,location, Gates.GateImgs[Gates.NOR],outputName);
				break;
			case XNOR:
				newComponent = new XNORGate(inputNames,inputValues,location, Gates.GateImgs[Gates.XNOR],outputName);
				break;
			case XOR:
				newComponent = new XORGate(inputNames,inputValues,location, Gates.GateImgs[Gates.XOR],outputName);
				break;
			default:
				System.out.println("Invalid gType given");
				break;
		}
		
		components.add(newComponent);
		
		size++;
	}
	
	//Note: Added output name to method
	public void addComponent(GateType gType,char inputName,boolean inputValue,char outputName)
	{
		Point location = new Point(startXPos + OFFX * size, startYPos + OFFY * size);
		LogicalGate newComponent=null;
		char[] inputNames = new char[] {outputName,inputName};//NOTE: Placing 'O' as place holder this needs to be changed later.
		boolean[] inputValues  = new boolean[] {components.get(size-1).output(),inputValue};
		switch(gType)
		{
			case AND: 
				newComponent = new ANDGate(inputNames,inputValues, location, Gates.GateImgs[Gates.AND], outputName);
				break;
			case OR:
				newComponent = new ORGate(inputNames,inputValues,location, Gates.GateImgs[Gates.OR],outputName);
				break;
			case NAND:
				newComponent = new NANDGate(inputNames,inputValues,location, Gates.GateImgs[Gates.NAND],outputName);
				break;
			case NOR:
				newComponent = new NORGate(inputNames,inputValues,location, Gates.GateImgs[Gates.NOR],outputName);
				break;
			case XNOR:
				newComponent = new XNORGate(inputNames,inputValues,location, Gates.GateImgs[Gates.XNOR],outputName);
				break;
			case XOR:
				newComponent = new XORGate(inputNames,inputValues,location, Gates.GateImgs[Gates.XOR],outputName);
				break;
			default:
				System.out.println("Invalid gType given");
				break;
		}
		components.add(newComponent);
		size ++;
		
	}
	
	//iterates for every component, draws the output name if is last component
	public void drawCircuit(Graphics g)
	{
		//for(LogicalGate gate : components)
			//		gate.draw(g);
			
			for(int i = 0; i< components.size(); i++){
				components.get(i).draw(g,false);
				if(i == components.size()-1){
					components.get(i).draw(g, true);
				}	
			}
	}

	public void updateInputs(HashMap<Character,Boolean> inputs) {
		
		for(Character c : inputs.keySet())
		{
			for(int i = 0; i < size;i++)
				components.get(i).updateInputs(c, inputs.get(c));
		}
	}
	

	public void updateOutputs()
	{
		for(int i = 0; i < size; i++)
			components.get(i).updateOutput();//May move this line inside the first for loop in updateInputs method.
	}

	public boolean getCircuitOutput()
	{
		return components.get(size-1).output();
	}
	
	
	public ArrayList<LogicalGate> components(){
		return components;
	}
}
