package gates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class XORGate extends AbstractLogicalGate {

	public XORGate(char[] inputNames,boolean[] inputValues, Point location, BufferedImage gateImg, char outputName) {
		super(inputNames,inputValues, location, gateImg,outputName);
		if((inputValues[0] == true && inputValues[1] == false) || (inputValues[0] == false && inputValues[1] == true))
			output = true;
		else
			output = false;
	}

	@Override
	public void updateOutput() {
		if((inputValues[0] == true && inputValues[1] == false) || (inputValues[0] == false && inputValues[1] == true))
			output = true;
		else
			output = false;		
	}

	@Override
	public void draw(Graphics g, boolean last) {
		g.drawImage(gateImg,location.x,location.y,null);
		
		String input1, input2;

    	input1 = (inputValues[0]) ? "1" : "0";
    	
    	input2 = (inputValues[1]) ? "1" : "0";
    	
    	g.setColor(Color.BLUE);
    	
    	g.drawString(Character.toString(inputNames[0]), location.x+5, location.y+10);
       	g.setColor(Color.BLACK);
		g.drawString(input1, location.x+20, location.y+30);
		
		g.setColor(Color.BLUE);
		g.drawString(Character.toString(inputNames[1]), location.x+5, location.y+70);
		g.setColor(Color.BLACK);
		g.drawString(input2, location.x+20, location.y+90);
		
		if(output)
			g.drawString("1", location.x+220, location.y+63);
		else
			g.drawString("0", location.x+220, location.y+63);
		
		if(last){
			g.setColor(Color.BLUE);
    		g.drawString(Character.toString(outputName), location.x+240,  location.y+43);
		}
	}

}
