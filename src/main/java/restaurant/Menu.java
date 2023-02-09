package restaurant;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * Defines the menu view.
 *
 * @author Irina Gubaciova, Mathushan Santhan, Manpreet Kaur
 */
public class Menu {

  @FXML
  private Button customerbtn;

  @FXML
  private Button staffbtn;

  @FXML
  private Label titlelbl;

  /**
   * Handles pressing the customer button.
   *
   * @throws IOException if an IO error occurs
   */
  public void handleCustomerBtn() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("model.fxml"));
    Stage window = (Stage) customerbtn.getScene().getWindow();
    Scene scene = new Scene(loader.load(), 600, 400);
    new RestController(loader.getController());
    window.setScene(scene);

  }

  /**
   * Handles pressing the staff login button.
   *
   * @throws IOException if an IO error occurs
   */
  public void handleStaffLoginBtn() throws IOException {
    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("staffLogin.fxml"));
    Stage window = (Stage) customerbtn.getScene().getWindow();
    window.setScene(new Scene(root, 600, 400));
  }
}
