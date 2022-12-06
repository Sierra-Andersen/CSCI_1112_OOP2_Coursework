/*
 * Author: Sierra Andersen
 * Date: 05 Dec 2022
 * 
 * This program tests the ClockPane class.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Random;

public class ShowClock extends Application{
	@Override
	public void start(Stage primaryStage) {
		Random r = new Random();
		//Create a random int for the hour
		int randHour = r.nextInt(11);
		//Create a random value for the minute, 0 or 30.
		int randMinute;
		if(r.nextInt(2)==0)
			randMinute = 0;
		else
			randMinute = 30;
			
		//Create a clock that only shows the hour and minute hands.
		ClockPane clock = new ClockPane();
		clock.setHour(randHour);
		clock.setMinute(randMinute);
		clock.setSecondHandVisible(false);
		
		Scene scene = new Scene(clock, 200, 200);
		primaryStage.setTitle("ClockTest");
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}
