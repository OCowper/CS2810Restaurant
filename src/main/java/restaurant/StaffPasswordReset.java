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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * View representing the Password Reset screen.
 *
 * @author Mathushan, Manpreet
 */
public class StaffPasswordReset implements Subject, ViewInterface {

  @FXML
  private Pane background;

  @FXML
  private TextField customerId;

  @FXML
  private TextField email;

  @FXML
  private ImageView oaxacaImageView;

  @FXML
  private Text promptText;

  @FXML
  private Button resetLink;

  @FXML
  private Button returnbtn;


  public void initialize(){
    Image title = new Image("/images/newoaxacaLogo.png");
    oaxacaImageView.setImage(title);
  }
  
  /**
   * Handles the go back button.
   *
   * @param event the event of the button press
   * @throws IOException if an IO exception occurs
   */
  @FXML
  public void handleForgotPassReturn(ActionEvent event) throws IOException {
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("staffLogin.fxml"));
    Parent startViewParent = loader.load();
    Scene startView = new Scene(startViewParent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());
    window.setScene(startView);
    window.show();
  }

  public Observer obs;

  @Override
  public void addObservers(Observer obs) {
    this.obs = obs;

  }


  @Override
  public void notifyObservers(Observer obs) {
    // TODO Auto-generated method stub

  }


  @Override
  public void acceptBoolean(Boolean bool) {
    // TODO Auto-generated method stub

  }


  @Override
  public void startup() {
    // TODO Auto-generated method stub
    
  }

}
