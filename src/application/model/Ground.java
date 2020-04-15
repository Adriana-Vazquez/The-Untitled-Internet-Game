package application model;

public class Ground()
{
	/**
	* Constructor that holds the current image and x for position, will be used in an arrayList
	*/
	private class GroundImage
	{
		getImage();
		int x;
	}
	
	public static int GROUND_Y;
	
	private ArrayList<GroundImage> groundImageSet;
	
	/**
	* Method to get the pieces of ground object into an array list
	* @param panelHeight
	*/
	public Ground(int panelHeight)
	{
		GROUND_Y = (int)(panelHeight - .35 * panelHeight);
	
		try
		{
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		groundImageSet = new ArrayList<GroundImage>();
		
		//first ground image
		for(int i=0; i<3; i++)
		{
			GroundImage obj = newGroundImage();
			obj.image = image;
			obj.x = 0;
			groundImageSet.add(obj);
		}
	}
	
	/**
	* Method to move the image of the ground forward and makes it a loop
	*/
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
			previousX = next.X;
		}
		
		if(first.x < -image.getWidth())
		{
			groundImageSet.remove(first);
			first.x = previousX + image.getWidth();
			groundImageSet.add(first);
		}
	}
}
