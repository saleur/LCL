package gates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class AbstractLogicalGate implements LogicalGate{
	
   
    protected Point location;
    protected boolean[] inputs;
    protected boolean output;
    
    public AbstractLogicalGate(boolean[] inputs,Point location)
    {
    	this.inputs = inputs;
    	this.location = location;
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
    
    public abstract void updateOutput(boolean[] newInputs);
    
    public abstract void draw(Graphics g);
    
    
    
    
    
}
