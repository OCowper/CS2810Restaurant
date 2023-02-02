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

public class StaffPasswordReset {

  @FXML
  private Pane Background;

  @FXML
  private TextField CustomerID;

  @FXML
  private TextField Email;

  @FXML
  private Text PromptText;

  @FXML
  private Button ResetLink;

  @FXML
  private Button returnbtn;

  @FXML
  void isPressed(ActionEvent event) {

  }

  public void handleForgotPassReturn(ActionEvent event) throws IOException {
    Parent startViewParent = FXMLLoader.load(getClass().getResource("FoodMenuView.fxml"));
    Scene startView = new Scene(startViewParent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(startView);
    window.show();
  }

}
