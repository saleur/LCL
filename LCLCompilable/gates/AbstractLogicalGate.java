package gates;



import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class AbstractLogicalGate implements LogicalGate{
	
   
    protected Point location;
    protected char[] inputNames;
    protected boolean[] inputValues;
    protected boolean output;
    protected char outputName;
    protected BufferedImage gateImg;
    
    
    
    public enum GateType {AND,OR,XOR,NAND,NOR,XNOR}
    
   
    
    //Note: Added outputName to constructor
    public AbstractLogicalGate(char[] inputNames,boolean[] inputValues,Point location,BufferedImage gateImg, char outputName)
    {
    	this.inputNames = inputNames;
    	this.inputValues = inputValues;
    	this.location = location;
    	this.gateImg = gateImg;
    	this.outputName = outputName;
    }
    
    public char[] inputNames(){
    	return inputNames;
    }
    
    public boolean[] inputValues(){
    	return inputValues;
    }
    
    public boolean output()
    {
    	return output;
    }
    
    public boolean[] inputs()
    {
    	return inputValues;
    }
    
    public char outputName(){
    	return outputName;
    }
    
    public Point location()
    {
    	return location;
    }  
    
    public void updateInputs(char inputName, boolean inputValue) 
    {
    	for(int i = 0; i < 2; i++)
			if(inputNames[i] == inputName)
				inputValues[i] = inputValue;
    }
    

    
    public abstract void draw(Graphics g, boolean last);
    
    
    
    public abstract void updateOutput();
    
    
    
    
    
    
    
    
}
