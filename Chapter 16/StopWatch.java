/*
 * Author: Sierra Andersen
 * Date: 14 Dec 2022
 * 
 * This program simulates a stopwatch with user input for the seconds.
 */


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;


public class StopWatch extends Application {


	//Overrides the start method from the Application Class
	@Override
	public void start(Stage primaryStage) {

		//Create a TextField and place it in a pane
		TextField tf = new TextField();
		Pane pane = new Pane();
		pane.getChildren().add(tf);

		AudioClip audio = new AudioClip("http://liveexample.pearsoncmg.com/common/audio/anthem/anthem0.mp3");

		//Create a handler for changing the text of the textfield.
		EventHandler<ActionEvent> eventHandler = e-> {
			tf.setEditable(false);
			int startTime = Integer.parseInt(tf.getText());

			tf.setText(--startTime+"");


			if(startTime == 0) {
			audio.play();
			}
		};

		tf.setOnAction(e->{
			Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000),eventHandler));
			animation.setCycleCount(Integer.parseInt(tf.getText()));
			animation.play();

		});





		//Sets the scene and shows the stage
		Scene scene = new Scene(pane, 300, 100);
		primaryStage.setTitle("Stop Watch");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}
