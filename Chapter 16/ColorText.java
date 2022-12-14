/*
 * Author: Sierra Andersen
 * Date: 14 Dec 2022
 * 
 * This program uses Javafx to allow a user to move a text and change its color.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ColorText extends Application {
	
	//Text for the middle
	protected Text text = new Text(50, 50, "Programming is fun!");
	
	
	protected BorderPane getPane() {
		
		//Create HBoxes for the buttons
		HBox paneButtons = new HBox(20);
		HBox paneRadioButtons = new HBox(20);
		
		//Create RadioButtons for the selecting the color		
		RadioButton rbPink = new RadioButton("Pink");
		RadioButton rbPurple = new RadioButton("Purple");
		RadioButton rbBlack = new RadioButton("Black");
		RadioButton rbBlue = new RadioButton("Blue");
		RadioButton rbGreen = new RadioButton("Green");
		
		//Group the buttons
		ToggleGroup group = new ToggleGroup();
		rbPink.setToggleGroup(group);
		rbPurple.setToggleGroup(group);
		rbBlack.setToggleGroup(group);
		rbBlue.setToggleGroup(group);
		rbGreen.setToggleGroup(group);
		
		//Add radio buttons to the pane
		paneRadioButtons.getChildren().addAll(rbPink, rbPurple, rbBlack, rbBlue, rbGreen);
		paneRadioButtons.setAlignment(Pos.CENTER);
		
		//Create buttons to move text
		Button btLeft = new Button("<--");
		Button btRight = new Button("-->");
		paneButtons.getChildren().addAll(btLeft, btRight);
		paneButtons.setAlignment(Pos.CENTER);
		
		//Create a pane for the text
		Pane paneText = new Pane();
		paneText.getChildren().add(text);
		
		//Put all the panes together
		BorderPane paneAll = new BorderPane();
		paneAll.setBottom(paneButtons);
		paneAll.setCenter(paneText);
		paneAll.setTop(paneRadioButtons);
		
		//Change the text position with the buttons
		btLeft.setOnAction(e-> {
			if((text.getX()-10) > paneAll.getMinWidth())
				text.setX(text.getX()-10);	
		});
		
		btRight.setOnAction(e-> {
			System.out.println(paneAll.getWidth());
			System.out.println(text.getX());
			if(text.getX()+115 < paneAll.getWidth())
				text.setX(text.getX()+10);
		});
		
		//Change the text color with the Radio buttons
		
		rbPink.setOnAction(e->{
			if(rbPink.isSelected())
				text.setFill(Color.DEEPPINK);
		});
		
		rbPurple.setOnAction(e->{
			if(rbPurple.isSelected())
				text.setFill(Color.MEDIUMPURPLE);
		});
		
		
		rbBlack.setOnAction(e->{
			if(rbBlack.isSelected())
				text.setFill(Color.BLACK);
		});
		
		
		rbBlue.setOnAction(e->{
			if(rbBlue.isSelected())
				text.setFill(Color.MEDIUMBLUE);
		});
		
		
		rbGreen.setOnAction(e->{
			if(rbGreen.isSelected())
				text.setFill(Color.FORESTGREEN);
		});
		
		return paneAll;
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		Scene scene = new Scene(getPane(), 450, 200);
		primaryStage.setTitle("Color Text");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);

	}

}
