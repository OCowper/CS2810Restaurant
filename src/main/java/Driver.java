import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Driver extends Application {

  /**
   * @author Mathushan, Manpreet, Irina
   */
  public static void main(String[] args) {
    launch(args);

  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FoodMenuView.fxml"));
    Scene scene = new Scene(root, 600, 400); // Creates Scene and Size
    primaryStage.setScene(scene); // Sets the Primary Stage as Scene
    primaryStage.setResizable(false); // Makes GUI not resizeable
    primaryStage.show(); // Displays GUI


  }

}
