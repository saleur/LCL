package gates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class XNORGate extends AbstractLogicalGate {
	
	public XNORGate(char[] inputNames,boolean[] inputValues, Point location, BufferedImage gateImg) {

		super(inputNames,inputValues, location, gateImg);
		if((inputValues[0] == false && inputValues[1] == false) || (inputValues[0] == true && inputValues[1] == true))
			output = true;
		else
			output = false;
	}



@Override
public void updateOutput() {
	if((inputValues[0] == false && inputValues[1] == false) || (inputValues[0] == true && inputValues[1] == true))
		output = true;
	else
		output = false;	
}



@Override
public void draw(Graphics g) {
	g.drawImage(gateImg,location.x,location.y,null);
	
	String input1, input2;	

	input1 = (inputValues[0]) ? "1" : "0";
	
	input2 = (inputValues[1]) ? "1" : "0";
	
	g.setColor(Color.BLACK);
	
	g.drawString(input1, location.x+20, location.y+30);
	g.drawString(input2, location.x+20, location.y+90);
	
	if(output)
		g.drawString("1", location.x+230, location.y+63);
	else
		g.drawString("0", location.x+230, location.y+63);
	
}

}