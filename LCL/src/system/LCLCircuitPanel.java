package system;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LCLCircuitPanel extends JPanel {
	
	    
    private ArrayList<Circuit> circuits;
	
	
	public LCLCircuitPanel()
	{
		super();
		circuits = null;
	
	}
	
	public void addCircuits(ArrayList<Circuit> circuits)
	{
		this.circuits = circuits;
	}

	public void setPreferredSize(int numOfCircuits)
	{
		Dimension pDimensions = new Dimension(4000*numOfCircuits, 4000*numOfCircuits);
		super.setPreferredSize(pDimensions);
	}



	public void paint(Graphics g) {		
		
		if(circuits!=null)
			for(Circuit c : circuits)
				c.drawCircuit(g);
	}
	
	
	public Point panelOffset(JFrame frame)
	{
		String osName = System.getProperty("os.name").toLowerCase();
		
		int panelOffX,panelOffY;
		
		if(osName.contains("linux"))
		{
			panelOffX = 50;
			panelOffY = 50;
		}
		else if(osName.contains("mac os"))
		{
			panelOffX = 0;
			panelOffY = 50;
		}
		else
		{
			panelOffX = 0;
			panelOffY = 0;
		}
		
		return new Point(frame.getX()-panelOffX,frame.getY()-panelOffY);
	}
	
	
	
	
}
