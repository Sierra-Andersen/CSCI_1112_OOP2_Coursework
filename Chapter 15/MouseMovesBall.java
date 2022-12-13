/*
 * Author: Sierra Andersen
 * Date: 07 Dec 2022
 * 
 * This program allows a user to move a ball around the screen.
 */

import javafx.application.Application;
import javafx.stage.Stage; 
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;



public class MouseMovesBall extends Application {
	
	// Overide the start method in the Application class
	@Override 
	public void start(Stage primaryStage) {
		
		//Create a pane here and put it in the scene.
		BallPane ballPane = new BallPane();
		

		// Hold four buttons in an HBox
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		Button btLeft = new Button("Left");
		Button btRight = new Button("Right");
		Button btUp = new Button("Up");
		Button btDown = new Button("Down");
		hBox.getChildren().addAll(btLeft, btRight, btUp, btDown);
		
		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(ballPane);
		borderPane.setBottom(hBox);
		System.out.println(borderPane.getWidth());
		
		btLeft.setOnAction(e->{
			ballPane.moveLeft();
		});
		
		btRight.setOnAction(e->{
			ballPane.moveRight();
		});
		
		btUp.setOnAction(e->{
			ballPane.moveUp();
		});
		
		btDown.setOnAction(e->{
			ballPane.moveDown();
		});

		
		Scene scene = new Scene(borderPane, 500, 500);
		
		System.out.println(borderPane.getWidth());
		//Title, put the scene in, and display stage
		primaryStage.setTitle("MouseMovesBall"); 
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	

	public static void main(String[] args) {
		launch(args);
	}

}
