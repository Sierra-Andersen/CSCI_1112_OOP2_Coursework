/*Author: Sierra Andersen
 * Date: 26 Jan 2023
 * 
 * This pane allows users to view the recipes they have chosen for their weekly menu.
 */
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;

public class PrintMenuPane extends VBox{

	public PrintMenuPane(ArrayList<Recipe> menuAL, String[] weeklyMenu) {
		paintPrintMenu(menuAL, weeklyMenu);
	}

	protected void paintPrintMenu(ArrayList<Recipe> menuAL, String[] weeklyMenu) {
		GridPane menuGP = new GridPane();
		menuGP.setAlignment(Pos.CENTER);
		menuGP.setHgap(10);
		menuGP.setVgap(25);

		Button removeMonBt = new Button("Remove");
		Button removeTueBt = new Button("Remove");
		Button removeWedBt = new Button("Remove");
		Button removeThuBt = new Button("Remove");
		Button removeFriBt = new Button("Remove");
		Button removeSatBt = new Button("Remove");
		Button removeSunBt = new Button("Remove");
		menuGP.add(removeMonBt, 2, 0);
		menuGP.add(removeTueBt, 2, 1);
		menuGP.add(removeWedBt, 2, 2);
		menuGP.add(removeThuBt, 2, 3);
		menuGP.add(removeFriBt, 2, 4);
		menuGP.add(removeSatBt, 2, 5);
		menuGP.add(removeSunBt, 2, 6);


		menuGP.add(new Label("Monday:"), 0, 0);
		menuGP.add(new Label("Tuesday:"), 0, 1);
		menuGP.add(new Label("Wednesday:"), 0, 2);
		menuGP.add(new Label("Thursday:"), 0, 3);
		menuGP.add(new Label("Friday:"), 0, 4);
		menuGP.add(new Label("Saturday:"), 0, 5);
		menuGP.add(new Label("Sunday:"), 0, 6);

		
		Text[] weeklyMenuTxt = new Text[7];
		for(int i = 0; i < weeklyMenu.length; i++ ) {
			weeklyMenuTxt[i] = new Text(weeklyMenu[i]);
			menuGP.add(weeklyMenuTxt[i], 1, i);
		}
		Button optionsMenuBt = new Button("Options Menu");
		getChildren().addAll(menuGP, optionsMenuBt);
		
		removeMonBt.setOnAction(e->{
			menuGP.getChildren().remove(weeklyMenuTxt[0]);
			weeklyMenu[0]="";
		});
		
		removeTueBt.setOnAction(e->{
			menuGP.getChildren().remove(weeklyMenuTxt[1]);
			weeklyMenu[1]="";
		});
		removeWedBt.setOnAction(e->{
			menuGP.getChildren().remove(weeklyMenuTxt[2]);
			weeklyMenu[2]="";
		});
		removeThuBt.setOnAction(e->{
			menuGP.getChildren().remove(weeklyMenuTxt[3]);
			weeklyMenu[3]="";
		});
		removeFriBt.setOnAction(e->{
			menuGP.getChildren().remove(weeklyMenuTxt[4]);
			weeklyMenu[4]="";
		});
		removeSatBt.setOnAction(e->{
			menuGP.getChildren().remove(weeklyMenuTxt[5]);
			weeklyMenu[5]="";
		});
		removeSunBt.setOnAction(e->{
			menuGP.getChildren().remove(weeklyMenuTxt[6]);
			weeklyMenu[6]="";
		});
		
		
		optionsMenuBt.setOnAction(e->{
			CookbookOptionsPane optionPane = new CookbookOptionsPane(menuAL, weeklyMenu);
			getChildren().clear();
			getChildren().add(optionPane);
		});
	}
}