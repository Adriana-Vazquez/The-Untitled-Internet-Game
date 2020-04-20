package application.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import application.control.GameController;
import application.model.Ground;

public class Player
{
	
	//variables of status of characterIcon
	public static final int STAND_STILL = 1,
				RUNNING = 2,
				JUMPING = 3,
				DIE = 4;
	private final int LEFT_FOOT = 1,
			  RIGHT_FOOT = 2,
			  NO_FOOT = 3;
	
	//variables of current status of characterIcon
	private static int state;
	
	//variable to switch characterIcon feet
	private int foot;
	
	public static String 	stand,
				leftFootDino,
				rightFootDino,
				deadDino;
	
	//constructor which holds characterIcon imagePath to all images
	public Player()
	{
		stand = "/photo/dinoStand.png";
		leftFootDino = "/photo/leftFootDino.png";
		rightFootDino = "/photo/rightFootDino.png";
		deadDino = "/photo/deadDino.png";
		state = 1;
		foot = NO_FOOT;
	}
	
	public void status()
	{
		switch(state)
		{
			case STAND_STILL:
				stand;
				break;
			case RUNNING:
			case JUMPING:
				if(foot == NO_FOOT)
				{
					foot = LEFT_FOOT;
					leftFootDino;
				}
				else if(foot == LEFT_FOOT)
				{
					foot = RIGHT_FOOT;
					rightFootDino;
				}
				else
				{
					foot = LEFT_FOOT;
					leftFootDino;
				}
				break;
			case DIE:
				deadDino;
				break;
		}
	}
	
	public void die()
	{
		state = DIE;
	}
	
	public void run()
	{
		state = RUNNING;
	}
	
}
