package gates;


import java.awt.Graphics;
import java.awt.Point;


public interface LogicalGate {
	
	public boolean output();
	
	public boolean[] inputs();
	
	public Point location();	
	
	public void updateInputs(char inputName, boolean inputValue);
			
	public void updateOutput();
	
	public void draw(Graphics g, boolean last);
	
	public char[] inputNames();

	public char outputName();
	 
	public boolean[] inputValues();
	
	
	


}
