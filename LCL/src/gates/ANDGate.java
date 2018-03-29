package gates;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

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
		 
	}

}
