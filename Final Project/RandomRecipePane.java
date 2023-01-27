/*Author: Sierra Andersen
 * Date: 26 Jan 2023
 * 
 * This class displays the random recipe to the user.
 * The user can then add the recipe to a specific week day.
 * The user can then view the weekly menu or select another random recipe.
 */

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;


public class RandomRecipePane extends Pane {
	public RandomRecipePane(ArrayList<Recipe> menuAL, String[] weeklyMenu, Recipe randomRecipe) {
		paintRandomRecipe(menuAL, weeklyMenu, randomRecipe);
	}

	protected void paintRandomRecipe(ArrayList<Recipe> menuAL, String[] weeklyMenu, Recipe randomRecipe) {
		//Title for the pane
		Text randomTitle = new Text("Random Recipe");

		Text randomRecipeTitle = new Text("Your random recipe is: ");
		Text recipeTxt = new Text(randomRecipe.toString());
		VBox randomRecipeVB = new VBox(15);

		RadioButton mondayRB = new RadioButton("Monday");
		RadioButton tuesdayRB = new RadioButton("Tuesday");
		RadioButton wednesdayRB = new RadioButton("Wednesday");
		RadioButton thursdayRB = new RadioButton("Thursday");
		RadioButton fridayRB = new RadioButton("Friday");
		RadioButton saturdayRB = new RadioButton("Saturday");
		RadioButton sundayRB = new RadioButton("Sunday");

		VBox daysVB = new VBox(15);
		daysVB.getChildren().addAll(mondayRB, tuesdayRB, wednesdayRB, thursdayRB, fridayRB, saturdayRB, sundayRB);

		//Group RadioButtons for days of the week.
		ToggleGroup dayTG = new ToggleGroup();
		mondayRB.setToggleGroup(dayTG);
		tuesdayRB.setToggleGroup(dayTG);
		wednesdayRB.setToggleGroup(dayTG);
		thursdayRB.setToggleGroup(dayTG);
		fridayRB.setToggleGroup(dayTG);
		saturdayRB.setToggleGroup(dayTG);
		sundayRB.setToggleGroup(dayTG);

		//Button to add a recipe to the menu
		Button addToMenuBt = new Button("Add to Menu");
		addToMenuBt.setContentDisplay(ContentDisplay.CENTER);

		//
		randomRecipeVB.getChildren().addAll(randomRecipeTitle, recipeTxt, daysVB, addToMenuBt);
		getChildren().addAll(randomTitle, randomRecipeVB);

		//Creates buttons to select a day of the week
		addToMenuBt.setOnAction(f->{
			//Adds recipe to a day in the weekly menu
			if(mondayRB.isSelected())
				weeklyMenu[0] = recipeTxt.getText();
			else if(tuesdayRB.isSelected())
				weeklyMenu[1] = recipeTxt.getText();
			else if(wednesdayRB.isSelected())
				weeklyMenu[2] = recipeTxt.getText();
			else if(thursdayRB.isSelected())
				weeklyMenu[3] = recipeTxt.getText();
			else if(fridayRB.isSelected())
				weeklyMenu[4] = recipeTxt.getText();
			else if(saturdayRB.isSelected())
				weeklyMenu[5] = recipeTxt.getText();
			else if(sundayRB.isSelected())
				weeklyMenu[6] = recipeTxt.getText();

			//Buttons to select another random recipe or view the menu
			Button viewMenuBt = new Button("View Menu");
			Button anotherRecipeBt = new Button("Another Random Recipe");

			HBox endButtonsHB = new HBox(40);
			endButtonsHB.getChildren().addAll(viewMenuBt, anotherRecipeBt);
			randomRecipeVB.getChildren().addAll(endButtonsHB);
			getChildren().clear();
			getChildren().add(randomRecipeVB);

			//Opens the PrintMenu pane
			viewMenuBt.setOnAction(g->{
				PrintMenuPane menuPane = new PrintMenuPane(menuAL, weeklyMenu);
				getChildren().clear();
				getChildren().add(menuPane);
			});

			//Opens another RandomRecipe pane
			anotherRecipeBt.setOnAction(h->{
				FilterPane filterPane = new FilterPane(menuAL, weeklyMenu);
				getChildren().clear();
				getChildren().add(filterPane);
			});
		});
	}
}

