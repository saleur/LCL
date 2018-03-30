package system;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gates.*;

public class LCLCircuit extends JPanel {
	
	private final int AND = 0;
	private final int OR = 1;
	private final int XOR = 2;
	private final int NAND = 3;
	private final int NOR = 4;
	private final int XNOR = 5;
	
	private BufferedImage[] gateImages;
	
	private Rectangle[] dummyGates;
	
	public LCLCircuit()
	{
		super();
		gateImages = new BufferedImage[6];
		
		try
		{
			gateImages[AND] = ImageIO.read(this.getClass().getResource("/componentsImages/ANDComponent.png"));
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println("IO File Error in creation of images for gates");
			System.out.println("Look at LCLCircuit Contructor for debugging");			
		};
		
	
	}
	

	public void setGates(Rectangle[] gates)
	{
		dummyGates = gates;
	}
	
	public void paint(Graphics g) {
		
		g.setColor(Color.BLUE);
		for(Rectangle r : dummyGates)
			g.drawRect(r.x, r.y, r.width, r.height);
		
		g.drawImage(gateImages[AND],20,40,this);
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
