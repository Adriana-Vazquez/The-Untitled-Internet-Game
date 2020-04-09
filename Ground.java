package application.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

public class Ground 
{
	private class GroundImage
	{
		BufferedImage image;
		int x;
	}
	
	public static int GROUND_Y;
	
	private BufferedImage image;
	
	private ArrayList<GroundImage> groundImageSet;
	
	public Ground(int panelHeight)
	{
		GROUND_Y = (int)(panelHeight - .025 * panelHeight);
		
		image = getResourceImage("/photo/ground.png");
		
		groundImageSet = new ArrayList<GroundImage>();
		
		for(int i = 0; i < 3; i++)
		{
			GroundImage obj = new GroundImage();
			obj.image = image;
			obj.x = 0;
			groundImageSet.add(obj);
		}
	}
	
	public void update()
	{
		Iterator<GroundImage> looper = groundImageSet.iterator();
		GroundImage first = looper.next();
		
		first.x -= 10;
		
		int previousX = first.x;
		
		while(looper.hasNext())
		{
			GroundImage next = looper.next();
			next.x = previousX + image.getWidth();
			previousX = next.x;
		}
		
		if(first.x < -image.getWidth())
		{
			groundImageSet.remove(first);
			first.x = previousX + image.getWidth();
			groundImageSet.add(first);
		}
	}
	
	public void create(Graphics g)
	{
		for(GroundImage img : groundImageSet)
		{
			g.drawImage(img.image, (int)img.x, GROUND_Y, null);
		}
	}
	
	
	public BufferedImage getResourceImage(String path)
	{
		BufferedImage img = null;
		try
		{
			img = ImageIO.read(getClass().getResource(path));
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		return img;
	}
}
