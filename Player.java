package application.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import application.control.GameController;
import application.model.Ground;
import javafx.scene.paint.Color;

public class Player
{
	private static int dinoBaseY, dinoTopY, dinoStartX, dinoEndX;
	private static int dinoTop, dinoBottom, topPoint;
	
	private static boolean topPointReached;
	private static int jumpFactor = 20;
	
	public static final int 
		STAND_STILL = 1,
		RUNNING = 2,
		JUMPING = 3,
		DIE =4;
	
	private final int
		LEFT_FOOT = 1,
		RIGHT_FOOT = 2,
		NO_FOOT = 3;
	
	private static int state;
	private int foot;
	private int score;
	
	static BufferedImage image;
	BufferedImage leftFootDino;
	BufferedImage rightFootDino;
	BufferedImage deadDino;
	
	public Player()
	{
		image = getResourceImage("/photo/player-stand.png");
		leftFootDino = getResourceImage("/photo/player-left.png");
		rightFootDino = getResourceImage("/photo/player-right.png");
		deadDino = getResourceImage("/photo/player-dead.png");
		
		dinoBaseY = Ground.GROUND_Y+5;
		dinoTopY = Ground.GROUND_Y - image.getHeight() + 5;
		dinoStartX = 100;
		dinoEndX = dinoStartX + image.getWidth();
		topPoint = dinoTopY - 120;
		
		state = 1;
		foot = NO_FOOT;
	}
	
	/**
	 * Method to set the image
	 * @param path leads to the image in folder
	 */
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
	
	public void create(Graphics g)
	{
		dinoBottom = dinoTop + image.getHeight();
		
		switch(state)
		{
		case STAND_STILL:
			System.out.println("Stand");
			g.drawImage(image, dinoStartX, dinoTopY, null);
			break;
		case RUNNING:
			if(foot == NO_FOOT)
			{
				foot = LEFT_FOOT;
				g.drawImage(leftFootDino, dinoStartX, dinoTopY, null);
			}
			else if(foot == LEFT_FOOT)
			{
				foot = RIGHT_FOOT;
				g.drawImage(rightFootDino, dinoStartX, dinoTopY, null);
			}
			else
			{
				foot = LEFT_FOOT;
				g.drawImage(leftFootDino, dinoStartX, dinoTopY, null);
			}
			break;
		case JUMPING:
			if(dinoTop > topPoint && !topPointReached)
			{
				g.drawImage(image, dinoStartX, dinoTop -= jumpFactor, null);
				break;
			}
			if(dinoTop >= topPoint && !topPointReached)
			{
				topPointReached = true;
				g.drawImage(image, dinoStartX, dinoTop += jumpFactor, null);
				break;
			}
			if(dinoTop > topPoint && topPointReached)
			{
				if(dinoTopY == dinoTop && topPointReached)
				{
					state = RUNNING;
					topPointReached = false;
					break;
				}
			}
			g.drawImage(image, dinoStartX, dinoTop += jumpFactor, null);
			break;
		case DIE:
			g.drawImage(deadDino, dinoStartX, dinoTop, null);
			break;
		}
	}
	
	/**
	 * Method to keep score
	 */
	public int score()
	{
		return score;
	}
	
	/**
	 * Method for player icon to die
	 */
	public void die()
	{
		state = DIE;
	}
	
	/**
	 * Method for player icon to start moving
	 */
	public void startRunning()
	{
		dinoTop = dinoTopY;
		state = RUNNING;
	}
	
	/**
	 * Method for player icon to jump
	 */
	public void jump()
	{
		dinoTop = dinoTopY;
		topPointReached = false;
		state = JUMPING;
	}
}
