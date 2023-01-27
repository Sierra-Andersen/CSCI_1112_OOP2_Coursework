/*
 * Author: Sierra Andersen
 * Modified: 11 Jan 2023
 * 
 * This program has been modified from a previous version to include JavaFX.
 * This is the class that launches the JavaFX GUI.
 */
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class RandomRecipeGUI extends Application {

	@Override
	public void start(Stage primaryStage) {
		ArrayList<Recipe> menuAL = new ArrayList<Recipe>();
		String[] weeklyMenu = new String[7];
			
		MainMenuPane menuPane = new MainMenuPane(menuAL, weeklyMenu);
		
		Scene scene = new Scene(menuPane, 450, 550);
		
		primaryStage.setTitle("Random Recipe Cookbook");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		
		Application.launch(args);
	}

}
