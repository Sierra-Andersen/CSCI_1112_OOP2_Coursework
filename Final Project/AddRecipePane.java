/* Author: Sierra Andersen
 * Date: 26 Jan 2023
 * 
 * This class opens the Add Recipe Screen. 
 * Users are able to enter the following information about the recipe:
 * Recipe Name
 * Recipe Ethnicity
 * Total Cooktime
 * Food Served Hot or Cold
 * 
 * The user will then be able to click on a button to start adding ingredients.
 */

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Label;


public class AddRecipePane extends VBox{

	public AddRecipePane(ArrayList<Recipe> menuAL, String[] weeklyMenu) {
		paintAddRecipe(menuAL, weeklyMenu);
	}

	protected void paintAddRecipe(ArrayList<Recipe> menuAL, String[] weeklyMenu) {
		//Title for the pane
		Text recipeTitle = new Text("New Recipe");

		//Information to type about the recipe
		TextField recipeNameTF = new TextField();
		Label rNameLbl = new Label("Recipe Name:", recipeNameTF);
		rNameLbl.setContentDisplay(ContentDisplay.RIGHT);

		TextField recipeEthnicityTF = new TextField();
		Label rEthnicityLbl = new Label("Recipe Ethnicity:", recipeEthnicityTF);
		rEthnicityLbl.setContentDisplay(ContentDisplay.RIGHT);

		TextField recipeTimeTF = new TextField();
		Label rTimeLbl= new Label("Time to Make (in minutes):", recipeTimeTF);
		rTimeLbl.setContentDisplay(ContentDisplay.RIGHT);


		//Buttons for hot/cold food
		RadioButton hotRB = new RadioButton("Hot");
		RadioButton coldRB = new RadioButton("Cold");
		ToggleGroup tempTG = new ToggleGroup();
		hotRB.setToggleGroup(tempTG);
		coldRB.setToggleGroup(tempTG);
		HBox tempHB = new HBox(30);
		tempHB.getChildren().addAll(hotRB, coldRB);

		//Button to add ingredients
		Button addIngredientsBt = new Button("Add Ingredients");

		//Organize the layout of the page
		VBox recipeVB = new VBox(30);
		recipeVB.getChildren().addAll(recipeTitle, rNameLbl, rEthnicityLbl, rTimeLbl, tempHB, addIngredientsBt);
		getChildren().add(recipeVB);

		//Store basic information about the recipe then change the pane to add ingredients
		addIngredientsBt.setOnAction(e->{
			//Check if the fields are filled
			if(recipeNameTF.getText().isEmpty() || recipeEthnicityTF.getText().isEmpty() || recipeTimeTF.getText().isEmpty() || tempTG.selectedToggleProperty().isNull().getValue()) {
				Text errorRecipeTxt = new Text("Please complete all the fields above.");
				getChildren().add(errorRecipeTxt);
			}
			else {
				//Store information about the recipe
				Recipe newRecipe = new Recipe(recipeNameTF.getText());
				newRecipe.ethnicity = recipeEthnicityTF.getText();
				newRecipe.cookTime =Integer.parseInt(recipeTimeTF.getText());
				if(hotRB.isSelected()) {
					newRecipe.hot = true;
				}
				if(coldRB.isSelected()) {
					newRecipe.hot = false;
				}

				//Change panes to add ingredients
				menuAL.add(newRecipe);
				AddIngredientsPane ingredientsPane = new AddIngredientsPane(menuAL, weeklyMenu);	

				getChildren().clear();
				getChildren().add(ingredientsPane);
			}
		});
	}

}
