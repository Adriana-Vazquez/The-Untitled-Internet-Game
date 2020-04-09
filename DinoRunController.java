package application.control;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import application.model.Ground;
import application.model.Obstacles;
import application.model.Player;

public class GameController 
{
	private Stage primaryStage;
	private @FXML ImageView dino;
	
	private boolean running = false;
	private boolean gameOver = false;
	private int timer = 60;
	
	Ground ground;
	//Player dino;
	Obstacles obstacles;
	
	/**
	 * Set-up utility for the game view
	 * @param primaryStage
	 */
	public GameController(Stage primaryStage)
	{
		setPrimaryStage(primaryStage);
	}
	
	
	/**
	 * Method to set primary stage field to be passed in the primaryStage arg
	 * @param primaryStage the primary stage to be stored in the field
	 */
	public void setPrimaryStage(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
	}
	
	/**
	 * Gets the primary stage field
	 * @return Stage object representation of the current primary stage
	 */
	public Stage getPrimaryStage()
	{
		return this.primaryStage;
	}
	
	/**
	 * Sets the game Stage and scene in the GUI
	 */
	public void __init()
	{
		try
		{
			String dinoRunView = "/fxmlFiles/DinoRunGame.fxml";
			FXMLLoader loader = new FXMLLoader(getClass().getResource(dinoRunView));
			loader.setController(this);
			Parent root = loader.load();
			Scene scene = new Scene(root,800,800);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
