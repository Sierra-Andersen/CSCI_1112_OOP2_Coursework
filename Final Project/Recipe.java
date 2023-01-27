/*
 * Author: Sierra Andersen
 * Created: 17 Nov 2022
 * 
 * This class represents a recipe with ingredients, a cook time (in minutes), the ethnicity of the food, and whether or not the food is served hot.
 */
import java.util.ArrayList;

public class Recipe implements java.io.Serializable {
	private String name;
	public String ethnicity = "none";
	public int cookTime = 0;
	public boolean hot = false;
	public ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();


	Recipe(String name){
		this.name = name;
	}

	Recipe(String name, int cookTime, ArrayList<Ingredient> ingredients, String ethnicity, boolean hot){
		this.name = name;
		this.cookTime = cookTime;
		this.ingredients = ingredients;
		this.ethnicity = ethnicity;
		this.hot = hot;
	}

	public String getName() {
		return name;
	}
	
	public int getIngredientLength() {
		return ingredients.size();
	}

	@Override
	public String toString () {
		return name + " Time to make: " + cookTime + " minutes" ;
	}



}
