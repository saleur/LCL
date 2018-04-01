package gates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class NANDGate extends AbstractLogicalGate {

	public NANDGate(boolean[] inputs, Point location, BufferedImage gateImg) {
		super(inputs, location, gateImg);
		if(!(inputs[0] == true && inputs[1] == true))
			output = true;
		else
			output = false;
	}

	@Override
	public void updateOutput(boolean[] newInputs) {
		if(!(newInputs[0] == true && newInputs[1] == true))
			output = true;
		else
			output = false;		
	}

	@Override
	public void draw(Graphics g) {
	    	g.drawImage(gateImg,location.x,location.y,null);
	    	
	    	String input1, input2;
	    	
	    	if(inputs[0]){
	    		input1 = "1";
	    	}else{
	    		input1 = "0";
	    	}
	    	
	    	if(inputs[1]){
	    		input2 = "1";
	    	}else{
	    		input2 = "0";
	    	}
	    	
	    	g.setColor(Color.BLACK);
	    	
	    	g.drawString(input1, location.x+40, location.y+30);
	    	g.drawString(input2, location.x+40, location.y+90);
	    	
	    	if(output){
	    		g.drawString("1", location.x+220, location.y+63);
	    	}else{
	    		g.drawString("0", location.x+220, location.y+63);
	    	}
	    	
	    	
	    
		
	}

}
