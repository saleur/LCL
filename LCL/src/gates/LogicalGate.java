package gates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import gates.AbstractLogicalGate.GateType;


public interface LogicalGate {
	
	public boolean output();
	
	public boolean[] inputs();
	
	public Point location();	
	
	public void updateOutput(boolean[] newInputs);
	
	public void draw(Graphics g);
	
	
	


}
