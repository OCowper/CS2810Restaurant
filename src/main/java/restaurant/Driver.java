package restaurant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Starts the application.
 *
 * @author Irina Gubaciova, Mathushan Santhan, Manpreet Kaur, zkac355
 */
public class Driver extends Application {
  

  /**
   * Initialises javafx and launches the window.
   *
   * @param args any command line arguments
   */
  public static void main(String[] args) {
    launch(args);

  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("menu.fxml"));
    Scene scene = new Scene(loader.load(), 600, 400); // Creates Scene and Size
    primaryStage.setScene(scene); //Sets the Primary Stage as Scene
    primaryStage.setResizable(false); //Makes GUI not resizeable
    primaryStage.show(); //Displays GUI
    
    
  }

}
