/*Author: Sierra Andersen
 * Date: 26 Jan 2023
 * 
 * This class displays a Main Menu where users can choose to load or create a cookbook.
 */
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

public class MainMenuPane extends Pane{
	
	public MainMenuPane(ArrayList<Recipe> menuAL, String[] weeklyMenu) {
		paintMainMenu(menuAL, weeklyMenu);
	}
	
	protected void paintMainMenu(ArrayList<Recipe> menuAL, String[] weeklyMenu){
		//Buttons for loading/creating cookbook
		Button createCookbookBt =new Button("Create Cookbook");
		Button loadCookbookBt =new Button("Load Cookbook");
		
		//Organize buttons
		HBox cookbookHB = new HBox(20);
		cookbookHB.getChildren().addAll(createCookbookBt, loadCookbookBt);
		cookbookHB.setAlignment(Pos.CENTER);
		
		//Graphic for the main page
		Image cookbookImg = new Image("https://media.istockphoto.com/id/1355983795/vector/bookshelf-with-cook-books-recipe-books-shelf-with-books-about-cooking-food-and-drinks-pizza.jpg?s=612x612&w=0&k=20&c=0eTYPpKsnd-BB95UR56t0bBUFv_rHYnW2L885MdyFEg=");
		ImageView cookbookIV = new ImageView(cookbookImg);
		
		cookbookIV.fitHeightProperty().bind(heightProperty().subtract(cookbookHB.heightProperty()));
		cookbookIV.fitWidthProperty().bind(widthProperty());

		//Pane to organize buttons and image
		BorderPane cookbookBP = new BorderPane();
		cookbookBP.setBottom(cookbookHB);
		cookbookBP.setCenter(cookbookIV);
		getChildren().add(cookbookBP);
		
		
		//Make a pane to type the file name to load 
		loadCookbookBt.setOnAction(e->{
			
			//I need to incorporate the uploadList method here somehow
			
			TextField loadFile = new TextField();
			Button load = new Button("Load");
			cookbookBP.getChildren().clear();
			cookbookBP.setCenter(loadFile);
			cookbookBP.setLeft(new Label("File Name: "));
			cookbookBP.setRight(load);
			Text fileName = new Text();

			load.setOnAction(f->{
				fileName.setText(loadFile.getText());
				ArrayList<Recipe> loadedMenuAL = new ArrayList<>();
				try {
					loadedMenuAL = uploadList(menuAL, fileName.getText());
				} 
				catch (ClassNotFoundException e1) {
				} 
				catch (IOException e1) {
					System.out.println("Invalid file type.");
					System.exit(1);
				}
				getChildren().clear();
				CookbookOptionsPane options = new CookbookOptionsPane(loadedMenuAL, weeklyMenu);
				getChildren().add(options);
			});
			
		});
		
		
		//Open the CookbookOptions pane
		createCookbookBt.setOnAction(e->{
			cookbookBP.getChildren().clear();
			CookbookOptionsPane options = new CookbookOptionsPane(menuAL, weeklyMenu);
			cookbookBP.getChildren().add(options);
		});
	}
	
	//Uploads a recipe list from a file
	public static ArrayList<Recipe> uploadList(ArrayList<Recipe> menuAL, String fileName) throws IOException, ClassNotFoundException {
		//Create a file object for the file.
		File f1 = new File(fileName);

		//Tests if the file exists
		if(!f1.exists()) {
			System.out.println("No such file exists.");
			System.exit(1);
		}

		//Loads each recipe from the file
		try(ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f1)));){
			while(true) {
				menuAL.add((Recipe)(input.readObject()));
			}
		}
		catch (EOFException ex) {
			System.out.println("Cookbook uploaded");
		}
		return menuAL;
	}
	
}
