/*Author: Sierra Andersen
 * Date: 26 Jan 2023
 * 
 * This class opens a menu with 5 buttons that users can select
 * Add Recipe
 * Select Random Recipe
 * Print Menu
 * Save Cookbook
 * Exit
 */

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.geometry.Insets;

public class CookbookOptionsPane extends Pane {
	public CookbookOptionsPane(ArrayList<Recipe> menuAL, String[] weeklyMenu) {
		paintCookbookOptions(menuAL, weeklyMenu);
	}

	protected void paintCookbookOptions(ArrayList<Recipe> menuAL, String[] weeklyMenu) {

		//Option buttons for the menu
		Button add =new Button("Add Recipe");
		Button select = new Button("Select Random Recipe");
		Button print = new Button("Print Menu");
		Button save = new Button("Save Cookbook");
		Button exit = new Button("Exit");

		//Format buttons to be the same size
		final int BUTTON_WIDTH = 141;
		add.setMaxWidth(BUTTON_WIDTH);
		select.setMaxWidth(BUTTON_WIDTH);
		print.setMaxWidth(BUTTON_WIDTH);
		save.setMaxWidth(BUTTON_WIDTH);
		exit.setMaxWidth(BUTTON_WIDTH);

		//Put buttons in a Vbox
		VBox buttonsVB = new VBox(20);
		buttonsVB.getChildren().addAll(add, select, print, save, exit);
		//buttonsVB.setAlignment(Pos.CENTER);
		buttonsVB.setPadding(new Insets(15, 15, 15, 100));
		getChildren().add(buttonsVB);

		//Create AddRecipe Pane when the add button is clicked
		add.setOnAction(e->{
			getChildren().clear();
			AddRecipePane recipePane = new AddRecipePane(menuAL, weeklyMenu);
			getChildren().add(recipePane);
		});

		//Create Filter Pane when the select button is clicked
		select.setOnAction(e->{
			if(menuAL.size()<1) {
				Text errorMenuSizeTxt = new Text("There are no recipes in the cookbook! Click \"add recipe\" first.");
				buttonsVB.getChildren().add(errorMenuSizeTxt);
				getChildren().clear();
				getChildren().add(buttonsVB);
			}
			else {
				getChildren().clear();
				FilterPane filterPane = new FilterPane(menuAL, weeklyMenu);
				getChildren().add(filterPane);
			}
		});

		//Create PrintMenu Pane when the print button is clicked
		print.setOnAction(e->{
			getChildren().clear();
			PrintMenuPane printPane = new PrintMenuPane(menuAL, weeklyMenu);
			getChildren().add(printPane);
		});

		//Call saveList method when the save button is clicked
		save.setOnAction(e->{
			try {
				saveList(menuAL, weeklyMenu);
			} catch (IOException e1) {
			}
		});

		exit.setOnAction(e->{
			System.exit(0);
		});


	}

	//Saves a recipe list
	public void saveList(ArrayList<Recipe> menuAL, String[] weeklyMenu) throws IOException {
		//Create the display for saving a file
		getChildren().clear();
		Text saveFileTitle = new Text("Save Cookbook");

		TextField fileNameTF = new TextField();
		Label fileNameLbl = new Label("Type the name of the file:", fileNameTF);
		fileNameLbl.setContentDisplay(ContentDisplay.RIGHT);

		Button saveBt = new Button("Save");
		Button backBt = new Button("Back");
		HBox buttonsHB = new HBox(20);
		buttonsHB.getChildren().addAll(backBt, saveBt);

		VBox saveTitleVB = new VBox(10);
		saveTitleVB.getChildren().addAll(saveFileTitle, fileNameLbl, buttonsHB);
		getChildren().add(saveTitleVB);

		//Go back to the CookbookOptionsPane when the button is clicked
		backBt.setOnAction(e->{
			CookbookOptionsPane optionsPane = new CookbookOptionsPane(menuAL, weeklyMenu);
			getChildren().clear();
			getChildren().add(optionsPane);
		});

		//Save the file when the save button is clicked
		saveBt.setOnAction(e->{
			String fileName = fileNameTF.getText();
			File cookbookFile = new File(fileName);

			//Check if the file already exists
			if(cookbookFile.exists()) {
				Text fileExists = new Text("This file already exists. Do you want to replace it? (Y/N)");
				Button replaceYesBt = new Button("Yes");
				Button replaceNoBt = new Button("No");

				//Display information about an existing file.
				saveTitleVB.getChildren().addAll(fileExists, replaceYesBt, replaceNoBt);
				getChildren().clear();
				getChildren().add(saveTitleVB);


				replaceYesBt.setOnAction(f->{
					//Write recipes to file
					try(ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(cookbookFile)));){
						for(int i = 0; i < menuAL.size(); i++) {
							output.writeObject(menuAL.get(i));

						}
					} 
					catch (IOException f1) {
					}

					//Takes the user back to the main menu
					MainMenuPane mainPane = new MainMenuPane(menuAL, weeklyMenu);
					getChildren().clear();
					getChildren().add(mainPane);
				});

				//Takes the user back to the save file screen
				replaceNoBt.setOnAction(f->{
					getChildren().clear();
					try {
						saveList(menuAL, weeklyMenu);
					} 
					catch (IOException f1) {
					}
				});

			}

			//Write recipes to file
			else{
				try(ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(cookbookFile)));){
					for(int i = 0; i < menuAL.size(); i++) {
						output.writeObject(menuAL.get(i));
					}
				} 
				catch (IOException e1) {
				}

				//Takes the user back to the main menu
				MainMenuPane mainPane = new MainMenuPane(menuAL, weeklyMenu);
				getChildren().clear();
				getChildren().add(mainPane);
			}

		});

	}
}
