package gates;

import java.awt.Color;
import java.awt.Point;

public interface LogicalGate {
	
	public boolean output();
	
	public boolean[] inputs();
	
	public Point location();	
	
	public Color color();
	


}
