import javafx.application.Application;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.collections.ObservableList;


public class RectangleAnimation extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Create a pane and scene
		Pane pane = new Pane();
		Scene scene = new Scene(pane, 200, 200);

		//Create a pentagon
		Polygon polygon = new Polygon();
		polygon.setFill(Color.DODGERBLUE);
		polygon.setStroke(Color.DODGERBLUE);
		double centerX = pane.getWidth()/2;
		double centerY = pane.getHeight()/2;
		double radius = Math.min(pane.getWidth(), pane.getHeight())*.4;

		ObservableList<Double> list = polygon.getPoints();
		int s = 5;

		//Add points to the polygon list
		for(int i = 0; i < s; i++) {
			list.add(centerX + radius * Math.cos(2*i*Math.PI/s) );
			list.add(centerY - radius * Math.sin(2*i*Math.PI/s) );
		}

		//Create a rectangle
		double width = pane.getWidth()/4;
		double height = pane.getHeight()/10;
		Rectangle rectangle = new Rectangle(0, 0, width, height);
		rectangle.setFill(Color.SEAGREEN);
		rectangle.setStroke(Color.SEAGREEN);


		//Add the pentagon and rectangle to the pane
		pane.getChildren().addAll(polygon, rectangle);

		//Create a PathTransition
		PathTransition pt = new PathTransition();
		pt.setDuration(Duration.millis(4500));
		pt.setPath(polygon);
		pt.setNode(rectangle);
		pt.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pt.setCycleCount(Timeline.INDEFINITE);

		//Create a FadeTransition

		FadeTransition ft = new FadeTransition(Duration.millis(4500), rectangle);
		ft.setFromValue(1.0);
		ft.setToValue(0.1);
		ft.setCycleCount(Timeline.INDEFINITE);
		ft.setAutoReverse(true);


		polygon.setOnMouseClicked(e-> {

			if(pt.getStatus()==Animation.Status.RUNNING) {
				pt.pause();
				ft.pause();
			}
			else {
				pt.play();
				ft.play();
			}

		});

		//Set the stage
		primaryStage.setTitle("Rectangle Animation");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}



	public static void main(String[] args) {
		launch(args);
	}

}
