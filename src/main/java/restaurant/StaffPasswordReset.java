package restaurant;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * View representing the Password Reset screen.
 *
 * @author Mathushan, Manpreet
 */
public class StaffPasswordReset {

  @FXML
  private Pane background;

  @FXML
  private TextField customerId;

  @FXML
  private TextField email;

  @FXML
  private Text promptText;

  @FXML
  private Button resetLink;

  @FXML
  private Button returnbtn;


  /**
   * Handles the go back button.
   *
   * @param event the event of the button press
   * @throws IOException if an IO exception occurs
   */
  @FXML
  public void handleForgotPassReturn(ActionEvent event) throws IOException {
    Parent startViewParent = FXMLLoader.load(getClass().getClassLoader().getResource("FoodMenuView.fxml"));
    Scene startView = new Scene(startViewParent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(startView);
    window.show();
  }

}
