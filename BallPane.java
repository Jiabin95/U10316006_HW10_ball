import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class BallPane extends Pane {
  public final double radius = 20;
  private double [][]xy = {{radius,radius*7},{radius,radius*13},{radius,radius*25},{radius,radius*18},{radius,radius*5}};
  private double [][]dxy = {{8,1},{5,3},{5,5},{1,5},{3,1}};
  private Circle [] circle = {new Circle(xy[0][0], xy[0][1], radius),new Circle(xy[1][0], xy[1][1], radius*1.5),
			new Circle(xy[2][0], xy[2][1], radius*2),new Circle(xy[0][0], xy[0][1], radius),new Circle(xy[0][1], xy[0][1], radius*0.5)};
  private Timeline animation;

  public BallPane() {
    // Set ball color	
    circle[0].setFill(Color.GREEN);
    circle[1].setFill(Color.RED);
    circle[2].setFill(Color.BLUE);
    circle[3].setFill(Color.GRAY);
    circle[4].setFill(Color.BLACK);
	for(int i = 0; i<5; i++){
		this.getChildren().addAll(circle[i]);// Place a ball into this pane

	}				
    // Create an animation for moving the ball
    animation = new Timeline(new KeyFrame(Duration.millis(50), e -> moveBall()));
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play(); // Start animation
  }

  public void play() {
    animation.play();
  }

  public void pause() {
    animation.pause();
  }

  public void increaseSpeed() {
    animation.setRate(animation.getRate() + 0.1);
  }

  public void decreaseSpeed() {
    animation.setRate(
      animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
  }

  public DoubleProperty rateProperty() {
    return animation.rateProperty();
  }
  
  protected void moveBall() {
    // Check boundaries
    for(int i =0; i<5; i++){	
    if (xy[i][0] < radius || xy[i][0] > getWidth() - radius) {
				dxy[i][0] *= -1; // Change ball move direction
			}
			if (xy[i][1] < radius || xy[i][1] > getHeight() - radius) {
				dxy[i][1] *= -1; // Change ball move direction
			}


			// Adjust ball position
			xy[i][0] += dxy[i][0];
			xy[i][1] += dxy[i][1];
			circle[i].setCenterX(xy[i][0]);
			circle[i].setCenterY(xy[i][1]);
  }
}
}
