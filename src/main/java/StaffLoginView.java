import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Handles the staff login page interactions.
 *
 * @author ZLAC183
 *
 */
public class StaffLoginView {

  @FXML
  private Button forgotBtn;

  @FXML
  private Label idLbl;

  @FXML
  private TextField idTxt;

  @FXML
  private Button loginBtn;

  @FXML
  private Label passLbl;

  @FXML
  private TextField passwordTxt;

  @FXML
  private Button returnBtn;

  @FXML
  private Label titleLbl;

  /**
   * Changes the scene from staff login to the initial.
   *
   * @param event "returnBtn" button pressed
   * @throws IOException when there is a problem with loading the .fxml file
   */
  public void handleStaffRtnBtn(ActionEvent event) throws IOException {
    Parent startViewParent = FXMLLoader.load(getClass().getResource("FoodMenuView.fxml"));
    Scene startView = new Scene(startViewParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(startView);
    window.show();
  }
}
