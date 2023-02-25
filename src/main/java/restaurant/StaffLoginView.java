package restaurant;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Defines the view for staff login.
 *
 * @author Mathushan, Manpreet
 */
public class StaffLoginView implements Subject, ViewInterface {


  @FXML
  private Button forgotBtn;

  @FXML
  private Label idLabel;

  @FXML
  private TextField idTxt;

  @FXML
  private ImageView landingPageImageView;

  @FXML
  private Button loginBtn;

  @FXML
  private ImageView oaxacaImageView;

  @FXML
  private Label passwordLabel;

  @FXML
  private TextField passwordTxt;

  @FXML
  private Button returnButton;

  @FXML
  private Label titleLabel;

  @FXML
  private Label titleLbl;

  /**
   * Initalization method.
   */
  public void initialize() {
    Image background = new Image("/images/plata-o-plomo-1.jpeg");
    Image title = new Image("/images/newoaxacaLogo.png");
    oaxacaImageView.setImage(title);
    landingPageImageView.setImage(background);
  }

  /**
   * Changes the scene from staff login to the initial.
   *
   * @param event "returnBtn" button pressed
   * @throws IOException when there is a problem with loading the .fxml file
   */
  @FXML
  public void handleStaffRtnBtn(ActionEvent event) throws IOException {
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("newLandingPage.fxml"));
    Parent startViewParent = loader.load();
    Scene startView = new Scene(startViewParent, 658, 400);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());
    window.setScene(startView);
    window.show();
  }

  /**
   * Handles pressing the forgot password button.
   *
   * @param event the button press event.
   * @throws IOException if an IO error occurs.
   */
  @FXML
  public void handleforgotBtn(ActionEvent event) throws IOException {
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("StaffPasswordReset.fxml"));
    Parent startViewParent = loader.load();
    Scene startView = new Scene(startViewParent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());
    window.setScene(startView);
    window.show();
  }

  /**
   * Handles pressing the login button.
   *
   * @param event the button press event.
   * @throws IOException if an IO errors occurs.
   */
  @FXML
  public void handleLoginBtn(ActionEvent event) throws IOException {
    notifyObservers(obs);
    if (valid) {
      FXMLLoader loader =
          new FXMLLoader(getClass().getClassLoader().getResource("staffPanel.fxml"));
      Parent startViewParent = loader.load();
      Scene startView = new Scene(startViewParent);
      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
      obs.setView(loader.getController());
      window.setScene(startView);
      window.show();
      valid = false;
    } else {
      titleLbl.setText("Please Try Again");
    }
  }

  public Observer obs;

  public Boolean valid;

  @Override
  public void addObservers(Observer obs) {
    this.obs = obs;

  }

  @Override
  public void notifyObservers(Observer obs) {
    obs.update(idTxt.getText(), passwordTxt.getText());

  }


  @Override
  public void acceptBoolean(Boolean bool) {
    valid = bool;
  }

  @Override
  public void startup() {
    // TODO Auto-generated method stub

  }
}
