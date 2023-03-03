package restaurant;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Class representing the landing page.
 *
 * @author Manpreet, Mathushan
 */
public class NewLandingPage implements Subject, ViewInterface {

  @FXML
  private Button aboutUsButton;

  @FXML
  private Button cartButton;
  
  @FXML
  private Button helpBtn;

  @FXML
  private ImageView landingPageImageView;

  @FXML
  private Button menuButton;

  @FXML
  private ImageView oaxacaImageView;

  @FXML
  private Button staffLoginButton;

  /**
   * Initialization method.
   */
  public void initialize() {
    Image background = new Image("/images/plata-o-plomo-1.jpeg");
    Image title = new Image("/images/newoaxacaLogo.png");
    oaxacaImageView.setImage(title);
    landingPageImageView.setImage(background);
  }

  /**
   * Changes the scene from initial one to food menu view.
   *
   * @param event "customerbtn" button pressed
   * @throws IOException when there is a problem with loading the fxml file
   */
  public void handleCustomerBtn(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("model.fxml"));
    Parent foodMenuparent = loader.load();

    Scene foodMenu = new Scene(foodMenuparent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());
    obs.orderStartup();
    window.setScene(foodMenu);
    window.show();

  }

  /**
   * Changes the scene from initial one to food menu view.
   *
   * @param event "staffbtn" button pressed
   * @throws IOException when there is a problem with loading the .fxml file
   */
  public void handleStaffLoginBtn(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("staffLogin.fxml"));
    Parent staffLodinParent = loader.load();
    Scene staffLogin = new Scene(staffLodinParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());

    window.setScene(staffLogin);
    window.show();
  }
  
  public void handleHelpBtn(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Help.fxml"));
    Parent staffLodinParent = loader.load();
    Scene staffLogin = new Scene(staffLodinParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());
    obs.orderStartup();
    window.setScene(staffLogin);
    window.show();
  }

  /**
   * Handling for if the cart screen switcher is pressed.
   *
   * @param event representing the button press
   * @throws IOException if an IO error occurs.
   */
  public void handleCartBtn(ActionEvent event) throws IOException {
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("checkoutPage.fxml"));
    Parent cartParent = loader.load();
    Scene checkout = new Scene(cartParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());

    window.setScene(checkout);
    window.show();
  }

  public Observer obs;

  @Override
  public void addObservers(Observer obs) {
    this.obs = obs;

  }

  @Override
  public void notifyObservers(Observer obs) {}

  @Override
  public void acceptBoolean(Boolean bool) {}

  @Override
  public void startup() {
    // TODO Auto-generated method stub

  }

}
