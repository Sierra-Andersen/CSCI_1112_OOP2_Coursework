/*
 * Author: Sierra Andersen
 * Date: 07 Dec 2022
 * 
 * This class is a subclass of Pane. It creates a ball that is movable with the arrow keys.
 */

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;


public class BallPane extends Pane {
	public double radius = (20);
	private double x = radius, y = radius;
	private Circle c = new Circle(x, y, radius);

	public BallPane() {
		c.setStroke(Color.BLACK);
		c.setFill(Color.BLUE);
		getChildren().add(c);
	}
		
		public void moveLeft() {
			if(!(c.getCenterX() - radius < 0))
				c.setCenterX(c.getCenterX()-10);
		}

		public void moveRight() {
			if(c.getCenterX() + radius < getWidth())
				c.setCenterX(c.getCenterX()+10);
		}

		public void moveUp() {
			if(!(c.getCenterY() - radius < 0))
				c.setCenterY(c.getCenterY()-10);
		}

		public void moveDown() {
			if(c.getCenterY() + radius < getHeight())
				c.setCenterY(c.getCenterY()+10);
		}

}
