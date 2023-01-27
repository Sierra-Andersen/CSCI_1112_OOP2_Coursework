/*Author: Sierra Andersen
 * Date: 26 Jan 2023
 * 
 * This class opens a pane that allows users to filter recipes.
 * The recipes can be filtered according to the following:
 * Ethnicity
 * Cooktime
 * Temperature
 * Specific Ingredients
 * Ingredient Types (e.g. dairy)
 * Meat/Meatless
 */
import java.util.ArrayList;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;


public class FilterPane extends VBox {
	public FilterPane(ArrayList<Recipe> menuAL, String[] weeklyMenu) {
		paintFilter(menuAL, weeklyMenu);
	}

	public void paintFilter(ArrayList<Recipe> menuAL, String[] weeklyMenu) {
		//Set spacing for the pane.
		setSpacing(20);

		//Title and Subtitle
		Text filterTitle = new Text("Filter Recipes");
		Text filterSubtitle = new Text("More than one option may be selected: ");

		//VBox to keep titles together.
		VBox titlesVB = new VBox(5);
		titlesVB.getChildren().addAll(filterTitle, filterSubtitle);


		//Buttons to set filters
		Button yesBt = new Button("Yes");
		Button noBt = new Button("No");

		//Display titles and buttons
		getChildren().addAll(filterTitle, yesBt, noBt);


		//Generate random recipe and allow to add to weekly menu
		noBt.setOnAction(e->{
			Recipe randomRecipe = randomRecipe(menuAL);
			getChildren().clear();
			getChildren().add(new RandomRecipePane(menuAL, weeklyMenu, randomRecipe));
		});

		yesBt.setOnAction(e->{

			//Checkbox for filter options
			CheckBox ethnicityChk =  new CheckBox("Ethnicity");
			CheckBox timeChk =  new CheckBox("Time");
			CheckBox tempChk =  new CheckBox("Temperature");
			CheckBox ingredientChk =  new CheckBox("Specific Ingredient");
			CheckBox typeChk =  new CheckBox("Ingredient Type");
			CheckBox meatChk =  new CheckBox("Meat/Meatless");

			//Button to select filters
			Button filterBt = new Button("Filter");

			//Add Buttons and Checkboxes
			getChildren().clear();
			getChildren().addAll(titlesVB, ethnicityChk, timeChk, tempChk, ingredientChk, typeChk, meatChk, filterBt);



			filterBt.setOnAction(f1->{
				getChildren().clear();

				//Ethnicity field
				TextField recipeEthnicity = new TextField();
				Label rEthnicity = new Label("Recipe Ethnicity:", recipeEthnicity);
				rEthnicity.setContentDisplay(ContentDisplay.RIGHT);

				//CookTime field
				TextField recipeTime = new TextField();
				Label rTime= new Label("Maximum Time to Make:", recipeTime);
				rTime.setContentDisplay(ContentDisplay.RIGHT);

				//Temperature field
				RadioButton hotRB = new RadioButton("Hot");
				RadioButton coldRB = new RadioButton("Cold");
				ToggleGroup temp = new ToggleGroup();
				hotRB.setToggleGroup(temp);
				coldRB.setToggleGroup(temp);
				HBox tempHB = new HBox(30);
				tempHB.getChildren().addAll(hotRB, coldRB);
				tempHB.setAlignment(Pos.CENTER);

				//Ingredient Name field
				TextField ingredientName = new TextField();
				Label iName = new Label("Ingredient Name:", ingredientName);
				iName.setContentDisplay(ContentDisplay.RIGHT);

				//Keep and Remove Buttons for Ingredient Name
				RadioButton keepIngRB= new RadioButton("Keep Ingredient");
				RadioButton removeIngRB = new RadioButton("Remove Ingredient");
				ToggleGroup removeKeepIngTG = new ToggleGroup();
				HBox keepRemoveIngHB = new HBox(30);
				keepRemoveIngHB.getChildren().addAll(keepIngRB, removeIngRB);

				//Ingredient Type field
				Text typeTitle = new Text("Ingredient Type: ");

				//Keep and Remove Buttons for Ingredient Type
				RadioButton keepTypeRB= new RadioButton("Keep Type");
				RadioButton removeTypeRB = new RadioButton("Remove Type");
				ToggleGroup removeKeepTypeTG = new ToggleGroup();
				HBox keepRemoveTypeHB = new HBox(30);
				keepRemoveTypeHB.getChildren().addAll(keepTypeRB, removeTypeRB);



				//Radio Buttons for selecting Ingredient Type
				ComboBox<String> typesCbo = new ComboBox<String>();
				typesCbo.getItems().addAll("Grain", "Vegetable", "Fruit", "Protein", "Dairy", "Fats/Sweets");

				//Meat field
				CheckBox meatCB = new CheckBox("Meat");
				CheckBox noMeatCB = new CheckBox("Meatless");
				HBox meatHB = new HBox(30);
				meatHB.getChildren().addAll(meatCB, noMeatCB);


				//VBox to organize type of ingredient
				VBox typeVB = new VBox(10);
				typeVB.getChildren().addAll(typeTitle, typesCbo, keepRemoveTypeHB);
				typeVB.setAlignment(Pos.CENTER_LEFT);

				//Filters for displaying filter information entry
				if(ethnicityChk.isSelected()) {
					getChildren().add(rEthnicity);
				}

				if(timeChk.isSelected()) {
					getChildren().add(rTime);
				}

				if(tempChk.isSelected()) {
					getChildren().add(tempHB);
				}

				if(ingredientChk.isSelected()) {
					keepIngRB.setToggleGroup(removeKeepIngTG);
					removeIngRB.setToggleGroup(removeKeepIngTG);
					getChildren().addAll(iName, keepRemoveIngHB);
				}

				if(typeChk.isSelected()) {

					keepTypeRB.setToggleGroup(removeKeepTypeTG);
					removeTypeRB.setToggleGroup(removeKeepTypeTG);
					getChildren().add(typeVB);
				}

				if(meatChk.isSelected()) {
					getChildren().add(meatHB);
				}

				//Button to apply filters
				Button applyBt = new Button("Apply Filters");
				getChildren().add(applyBt);

				applyBt.setOnAction(g1->{
					//Cookbook with filtered recipes
					ArrayList<Recipe>filteredMenu = menuAL;

					//Filters the recipes in the cookbook according to preferences
					if(ethnicityChk.isSelected())
						filteredMenu = ethnicityFilter(filteredMenu, recipeEthnicity.getText());

					if(timeChk.isSelected())
						filteredMenu = cookTimeFilter(filteredMenu, Integer.parseInt(recipeTime.getText()));

					if(tempChk.isSelected())
						filteredMenu = hotFilter(filteredMenu, hotRB.isSelected());

					if(ingredientChk.isSelected())
						filteredMenu = ingredientNameFilter(filteredMenu, ingredientName.getText(), removeIngRB.isSelected());

					if(typeChk.isSelected()) {
						if(typesCbo.getValue().equals("Grain"))
							filteredMenu = ingredientTypeFilter(filteredMenu, Ingredient.FoodType.Grain, removeTypeRB.isSelected());
						else if(typesCbo.getValue().equals("Vegetable"))
							filteredMenu = ingredientTypeFilter(filteredMenu, Ingredient.FoodType.Vegetable, removeTypeRB.isSelected());
						else if(typesCbo.getValue().equalsIgnoreCase("Fruit"))
							filteredMenu = ingredientTypeFilter(filteredMenu, Ingredient.FoodType.Fruit, removeTypeRB.isSelected());
						else if(typesCbo.getValue().equalsIgnoreCase("Protein"))
							filteredMenu = ingredientTypeFilter(filteredMenu, Ingredient.FoodType.Protein, removeTypeRB.isSelected());
						else if(typesCbo.getValue().equalsIgnoreCase("Dairy"))
							filteredMenu = ingredientTypeFilter(filteredMenu, Ingredient.FoodType.Dairy, removeTypeRB.isSelected());
						else if(typesCbo.getValue().equalsIgnoreCase("Fats/Sweets"))
							filteredMenu = ingredientTypeFilter(filteredMenu, Ingredient.FoodType.FatsSweets, removeTypeRB.isSelected());
					}
					if(meatChk.isSelected())
						filteredMenu = meatFilter(filteredMenu, meatCB.isSelected());

					//Buttons to generate a random Recipe
					Button randomRecipeBt = new Button("Random Recipe");
					Recipe filterRecipe = randomRecipe(filteredMenu);
					getChildren().add(randomRecipeBt);

					//Displays options for the selected random recipe
					randomRecipeBt.setOnAction(g2->{
						getChildren().clear();
						getChildren().add(new RandomRecipePane(menuAL, weeklyMenu, filterRecipe));
					});
				});

			});
		});
	}


	//Removes any recipes that don't have the specified ethnicity
	public static ArrayList<Recipe> ethnicityFilter(ArrayList<Recipe> menu, String ethnicity){
		ArrayList<Recipe> newMenu = new ArrayList<Recipe>();
		for (int i = 0; i < menu.size(); i++) {
			if(menu.get(i).ethnicity.equalsIgnoreCase(ethnicity)) {
				newMenu.add(menu.get(i));
			}	
		}
		return newMenu;
	}

	//Removes any recipes that take longer than the specified cook time.
	public static ArrayList<Recipe> cookTimeFilter(ArrayList<Recipe> menu, int cookTime){
		ArrayList<Recipe> newMenu = new ArrayList<Recipe>();
		for (int i = 0; i < menu.size(); i++) {
			if(menu.get(i).cookTime<= cookTime) 
				newMenu.add(menu.get(i));	
		}
		return newMenu;
	}

	//Removes any recipes that don't have the desired temperature
	public static ArrayList<Recipe> hotFilter(ArrayList<Recipe> menu, boolean hot){
		ArrayList<Recipe> newMenu = new ArrayList<Recipe>();
		for (int i = 0; i < menu.size(); i++) {
			if(menu.get(i).hot== hot)
				newMenu.add(menu.get(i));
		}
		return newMenu;
	}

	//Removes any recipes that have the specified ingredient
	public static ArrayList<Recipe> ingredientNameFilter(ArrayList<Recipe> menu, String iName, boolean remove){
		ArrayList<Recipe> newMenu = new ArrayList<Recipe>();
		if(!remove) {
			for (int i = 0; i < menu.size(); i++) {
				for(int j = 0; j<menu.get(i).ingredients.size(); j++) {
					if(menu.get(i).ingredients.get(j).getName().equalsIgnoreCase(iName))
						newMenu.add(menu.get(i));		
				}
			}
		}

		else {
			for (int i = 0; i < menu.size(); i++) {
				boolean found = false;
				for(int j = 0; j<menu.get(i).ingredients.size(); j++) {

					if(menu.get(i).ingredients.get(j).getName().equalsIgnoreCase(iName))
						found = true;
				}
				if(!found)
					newMenu.add(menu.get(i));
			}
		}
		return newMenu;
	}

	//Removes any recipes that have the ingredients of the specified type
	public static ArrayList<Recipe> ingredientTypeFilter(ArrayList<Recipe> menu, Ingredient.FoodType type, boolean remove){
		ArrayList<Recipe> newMenu = new ArrayList<Recipe>();

		if(!remove) {
			for (int i = 0; i < menu.size(); i++) {
				for(int j = 0; j<menu.get(i).ingredients.size(); j++) {
					if(menu.get(i).ingredients.get(j).type==type) {
						newMenu.add(menu.get(i));

						//Stops searching for matching ingredients and moves to the next recipe.
						break;
					}
				}
			}
		}
		else {
			for (int i = 0; i < menu.size(); i++) {
				boolean found = false;
				for(int j = 0; j<menu.get(i).ingredients.size(); j++) {

					if(menu.get(i).ingredients.get(j).type==type)
						found = true;
				}
				if(!found)
					newMenu.add(menu.get(i));
			}
		}
		return newMenu;
	}

	//Removes any recipes that don't match the meat preference of the user
	public static ArrayList<Recipe> meatFilter(ArrayList<Recipe> menu, boolean meat){
		ArrayList<Recipe> newMenu = new ArrayList<Recipe>();

		if(meat) {
			for (int i = 0; i < menu.size(); i++) {
				for(int j = 0; j<menu.get(i).ingredients.size(); j++) {

					if(menu.get(i).ingredients.get(j).meat)
						newMenu.add(menu.get(i));
				}
			}
		}

		else {
			for (int i = 0; i < menu.size(); i++) {
				boolean found = false;
				for(int j = 0; j<menu.get(i).ingredients.size(); j++) {

					if(menu.get(i).ingredients.get(j).meat)
						found = true;
				}
				if(!found)
					newMenu.add(menu.get(i));
			}
		}
		return newMenu;
	}

	//Selects a Random Recipe
	public static Recipe randomRecipe(ArrayList<Recipe> menuAL) {

		Recipe randomRecipe = new Recipe("");
		Random random1 = new Random();
		int randIndex = random1.nextInt(menuAL.size());
		randomRecipe = menuAL.get(randIndex);
		return randomRecipe;
	}
}
