package gates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;


public class ANDGate extends AbstractLogicalGate {
	
	
	public ANDGate(boolean[] inputs,Point location,BufferedImage gateImg)
	{
		super(inputs,location,gateImg);
		if(inputs[0] == true && inputs[1] == true)
			output = true;
		else
			output = false;
		
	}


	@Override
	public void updateOutput(boolean[] newInputs) {
		if(inputs[0] == true && inputs[1] == true)
			output = true;
		else
			output = false;		
	}


	@Override
	public void draw(Graphics g) {
		g.drawImage(gateImg,location.x,location.y,null);
    	
    	String input1, input2;
    	    
    	input1 = (inputs[0]) ? "1" : "0";
    	
    	input2 = (inputs[1]) ? "1" : "0";    	
    	
    	g.setColor(Color.BLACK);
    	
    	g.drawString(input1, location.x+40, location.y+30);
    	g.drawString(input2, location.x+40, location.y+90);
    	
    	if(output)
    		g.drawString("1", location.x+200, location.y+63);
    	else
    		g.drawString("0", location.x+200, location.y+63);
    	
		
	}


}
