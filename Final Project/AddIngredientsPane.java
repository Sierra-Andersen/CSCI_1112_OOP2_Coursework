/*Author: Sierra Andersen
 * Date: 26 Jan 2023
 * 
 * This class is a pane that allows users to add ingredient information to their recipes.
 * The information users can add is as follows:
 * Ingredient Name
 * Ingredient Amount
 * Amount Unit
 * Ingredient Type
 * Ingredient is Meat or Meatless
 */

import java.util.ArrayList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Label;


public class AddIngredientsPane extends VBox {
	public AddIngredientsPane(ArrayList<Recipe> menuAL, String[] weeklyMenu) {
		paintAddIngredients(menuAL, weeklyMenu);
	}

	protected void paintAddIngredients(ArrayList<Recipe> menuAL, String[] weeklyMenu) {
		//Title for the pane
		Text ingredientsTitle = new Text("Add Ingredients");

		//Information to type about the recipe
		TextField ingredientNameTF = new TextField();
		Label iNameLbl = new Label("Ingredient Name:", ingredientNameTF);
		iNameLbl.setContentDisplay(ContentDisplay.RIGHT);
		
		TextField ingredientAmountTF = new TextField();
		Label iAmountLbl = new Label("Ingredient Amount:", ingredientAmountTF);
		iAmountLbl.setContentDisplay(ContentDisplay.RIGHT);

		//VBox to organize information about recipe
		VBox ingredientVB = new VBox(30);
		ingredientVB.getChildren().addAll(ingredientsTitle, iNameLbl);

		//Radio Buttons for food unit
		ComboBox<String> unitCmbo = new ComboBox<String>();
		unitCmbo.getItems().addAll("Lbs", "Whole", "Cups", "Tbsp", "Tsp");
		Label unitLbl = new Label("Units", unitCmbo);
		unitLbl.setContentDisplay(ContentDisplay.LEFT);

		//HBox to put Ingredient Amount with Units
		HBox unitAmountHB = new HBox();
		unitAmountHB.getChildren().addAll(iAmountLbl, unitLbl);

		//Section for ingredient type
		Text typeTitle = new Text("Ingredient Type: ");

		//Radio Buttons for selecting Ingredient Type
		RadioButton grainsRB = new RadioButton("Grain");
		RadioButton vegetablesRB = new RadioButton("Vegetable");
		RadioButton fruitRB = new RadioButton("Fruit");
		RadioButton proteinRB = new RadioButton("Protein");
		RadioButton dairyRB = new RadioButton("Dairy");
		RadioButton fatRB = new RadioButton("Fats/Sweets");
		ToggleGroup typeTG = new ToggleGroup();
		grainsRB.setToggleGroup(typeTG);
		vegetablesRB.setToggleGroup(typeTG);
		fruitRB.setToggleGroup(typeTG);
		proteinRB.setToggleGroup(typeTG);
		dairyRB.setToggleGroup(typeTG);
		fatRB.setToggleGroup(typeTG);

		//Checkbox to set if the protein is a meat, becomes visible when protein is selected
		CheckBox meatCB = new CheckBox("Meat");
		meatCB.setVisible(false);

		proteinRB.setOnAction(e->{
			meatCB.setVisible(true);
		});

		//HBox to organize Protein and Meat
		HBox proteinMeatHB = new HBox(20);
		proteinMeatHB.getChildren().addAll(proteinRB, meatCB);

		//VBox to organize type of ingredient
		VBox typeVB = new VBox(10);
		typeVB.getChildren().addAll(typeTitle, grainsRB, vegetablesRB, fruitRB, proteinMeatHB, dairyRB, fatRB);
		VBox.setMargin(meatCB, new Insets(0, 0, 0, 50));
		typeVB.setAlignment(Pos.CENTER_LEFT);

		//Buttons to add ingredient/finish adding ingredients
		Button addBt = new Button("Add");
		Button doneBt = new Button("Done");

		//HBox to organize buttons
		HBox finishHB = new HBox(20);
		finishHB.getChildren().addAll(addBt,doneBt);
		finishHB.setAlignment(Pos.CENTER);

		//VBox to organize all of the components on this pane
		VBox allVB = new VBox(20);
		allVB.getChildren().addAll(ingredientVB, unitAmountHB, typeVB, finishHB);

		getChildren().add(allVB);

		addBt.setOnAction(e->{
			//Check if ingredient fields are completed.
			if(ingredientNameTF.getText().isEmpty() || ingredientAmountTF.getText().isEmpty() || unitCmbo.getSelectionModel().selectedItemProperty().isNull().getValue() || typeTG.selectedToggleProperty().isNull().getValue()) {
				Text errorIngredientTxt = new Text("\"Please complete all the fields above.\"");
				getChildren().add(errorIngredientTxt);
			}

			else {
				//select the current recipe and create the current ingredient to add to the recipe	
				Recipe currentRecipe = menuAL.get(menuAL.size()-1);
				Ingredient currentIngredient = new Ingredient(ingredientNameTF.getText());

				//Add the ingredient amount
				currentIngredient.amount = Double.parseDouble(ingredientAmountTF.getText());

				//Add if the ingredient is meat
				if(meatCB.isSelected())
					currentIngredient.meat = true;
				else
					currentIngredient.meat = false;

				//Add the ingredient amount unit
				if(unitCmbo.getValue().equals("Lbs"))
					currentIngredient.unit = Ingredient.AmountUnit.Lbs;
				else if(unitCmbo.getValue().equals("Whole"))
					currentIngredient.unit = Ingredient.AmountUnit.Whole;
				else if(unitCmbo.getValue().equals("Cups"))
					currentIngredient.unit = Ingredient.AmountUnit.Cups;
				else if(unitCmbo.getValue().equals("Tbsp"))
					currentIngredient.unit = Ingredient.AmountUnit.Tbsp;
				else if(unitCmbo.getValue().equals("Tsp"))
					currentIngredient.unit = Ingredient.AmountUnit.Tsp;

				//Add the ingredient type
				if(grainsRB.isSelected())
					currentIngredient.type = Ingredient.FoodType.Grain;
				else if(vegetablesRB.isSelected())
					currentIngredient.type = Ingredient.FoodType.Vegetable;
				else if(fruitRB.isSelected())
					currentIngredient.type = Ingredient.FoodType.Fruit;
				else if(proteinRB.isSelected())
					currentIngredient.type = Ingredient.FoodType.Protein;
				else if(dairyRB.isSelected())
					currentIngredient.type = Ingredient.FoodType.Dairy;
				else if(fatRB.isSelected())
					currentIngredient.type = Ingredient.FoodType.FatsSweets;

				//Add the ingredient to the recipe
				currentRecipe.ingredients.add(currentIngredient);

				//Clear TextFields and buttons
				ingredientNameTF.clear();
				ingredientAmountTF.clear();
				unitCmbo.setValue(null);
				typeTG.getSelectedToggle().setSelected(false);		
				meatCB.setVisible(false);
				meatCB.setSelected(false);
			}
		});

		doneBt.setOnAction(e->{
			CookbookOptionsPane options = new CookbookOptionsPane(menuAL, weeklyMenu);
			getChildren().clear();
			getChildren().add(options);
		});

	}
}
