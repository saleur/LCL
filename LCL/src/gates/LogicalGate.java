package gates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public interface LogicalGate {
	
	public boolean output();
	
	public boolean[] inputs();
	
	public Point location();	
	
	public Color color();
	
	public void update();
	
	public void draw(Graphics g);
	


}
