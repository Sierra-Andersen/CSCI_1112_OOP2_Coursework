/*
 * Author: Sierra Andersen
 * Modified: 11 Jan 2023
 * 
 * This class represents an ingredient in a recipe. The ingredient has a name, a type, and whether or not it is meat.
 * Types:
 * 1. Grains
 * 2. Vegetable
 * 3. Fruit
 * 4. Protein
 * 5. Dairy
 * 6. Fats/Oils/Sweets
 */

public class Ingredient implements java.io.Serializable {
	private String name;
	enum FoodType {Grain, Vegetable, Fruit, Protein, Dairy, FatsSweets};
	FoodType type;
	public boolean meat = false;
	public double amount;
	enum AmountUnit {Lbs, Whole, Cups, Tbsp, Tsp};
	AmountUnit unit;
	
	
	Ingredient(String name){
		this.name = name;
	}
	
	Ingredient(String name, FoodType type, boolean meat, double amount, AmountUnit unit){
		this.name = name;
		this.type = type;
		this.meat = meat;
		this.amount = amount;
		this.unit = unit;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name + ", " + type.toString();
	}

}
