package gates;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class AbstractLogicalGate implements LogicalGate{
	
   
    protected Point location;
    protected boolean[] inputs;
    protected boolean output;
    protected BufferedImage gateImg;
    
    public enum GateType {AND,OR,XOR,NAND,NOR,XNOR}
    
   
    
    
    public AbstractLogicalGate(boolean[] inputs,Point location,BufferedImage gateImg)
    {
    	this.inputs = inputs;
    	this.location = location;
    	this.gateImg = gateImg;
    }
    
    public boolean output()
    {
    	return output;
    }
    
    public boolean[] inputs()
    {
    	return inputs;
    }
    
    public Point location()
    {
    	return location;
    }  
    
    public abstract void draw(Graphics g);
    
    
    
    public abstract void updateOutput(boolean[] newInputs);
    
    
    
    
    
    
    
    
}
