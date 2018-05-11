package componentsImages;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public final class Gates {
	
	public static BufferedImage[] GateImgs;//NOTE: Might move this to LCL System class.
	
	public static final int AND = 0;
	public static final int OR = 1;
	public static final int XOR = 2;
	public static final int NAND = 3;
	public static final int NOR = 4;
	public static final int XNOR = 5;
	
	public Gates()
	{
		GateImgs = new BufferedImage[6];
		
		try
		{
			GateImgs[AND] = ImageIO.read(this.getClass().getResource("/componentsImages/ANDComponent.png"));
			GateImgs[OR] = ImageIO.read(this.getClass().getResource("/componentsImages/ORComponent.png"));
			GateImgs[XOR] = ImageIO.read(this.getClass().getResource("/componentsImages/XORComponent.png"));
			GateImgs[NAND] = ImageIO.read(this.getClass().getResource("/componentsImages/NANDComponent.png"));
			GateImgs[NOR] = ImageIO.read(this.getClass().getResource("/componentsImages/NORComponent.png"));
			GateImgs[XNOR] = ImageIO.read(this.getClass().getResource("/componentsImages/XNORComponent.png"));
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println("IO File Error in creation of images for gates");
			System.out.println("Look at Gates Contructor for debugging");			
		};
		
	}
	


}
