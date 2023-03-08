package restaurant;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Help implements Subject, ViewInterface {

  @FXML
  private CheckBox EmergencyIssueBox;

  @FXML
  private Button HelpBtn;

  @FXML
  private Label IssuesLbl;

  @FXML
  private CheckBox MenuIssueBox;

  @FXML
  private CheckBox PaymentIssueBox;

  @FXML
  private Label TableNumberLbl;

  @FXML
  private TextField TableNumberTxt;

  @FXML
  private Button aboutUsButton;

  @FXML
  private AnchorPane bgAnchorPane;

  @FXML
  private ImageView bgImage;

  @FXML
  private Button cartButton;

  @FXML
  private Button helpBtn;

  @FXML
  private Text helpTitle;

  @FXML
  private Button menuButton;

  @FXML
  private ImageView oaxacaImageView;

  @FXML
  private CheckBox tabelIssueBox;
  
  @FXML
  private Button TrackOrderButton;

  @FXML
  private VBox virtualBox;
  
  @FXML
  private Label confirmLabel;

  public void startup() {
    Image background = new Image("/images/plata-o-plomo-1.jpeg");
    Image title = new Image("/images/newoaxacaLogo.png");
    oaxacaImageView.setImage(title);
    bgImage.setImage(background);
  }

  /**
   * Changes the scene from initial one to food menu view.
   *
   * @param event "customerbtn" button pressed
   * @throws IOException when there is a problem with loading the fxml file
   */
  public void handleCustomerBtn(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("CustomerMenu.fxml"));
    Parent foodMenuparent = loader.load();

    Scene foodMenu = new Scene(foodMenuparent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());
    obs.orderStartup();
    window.setScene(foodMenu);
    window.show();

  }

  public void handleMenuReturn(MouseEvent event) throws IOException {
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("newLandingPage.fxml"));
    Parent paymentParent = loader.load();
    Scene payment = new Scene(paymentParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());

    window.setScene(payment);
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
  
  @FXML
  void handleAboutBtn(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AboutPage.fxml"));
    Parent staffLodinParent = loader.load();
    Scene staffLogin = new Scene(staffLodinParent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());
    window.setScene(staffLogin);
    window.show();
  }
  
  @FXML
  void handleTrackBtn(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("CustomerNotificationScreen.fxml"));
    Parent staffLodinParent = loader.load();
    Scene staffLogin = new Scene(staffLodinParent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());
    window.setScene(staffLogin);
    window.show();
  }

  private HelpRequest curRequest;
  
  @FXML
  void handleCallHelp(ActionEvent event) {
    if (tabelIssueBox.isSelected()) {
      curRequest = new HelpRequest(Integer.parseInt(TableNumberTxt.getText()), Request.TABLE);
      notifyObservers(obs);
    }
    if (MenuIssueBox.isSelected()) {
      curRequest = new HelpRequest(Integer.parseInt(TableNumberTxt.getText()), Request.MENU);
      notifyObservers(obs);
    }
    if (PaymentIssueBox.isSelected()) {
      curRequest = new HelpRequest(Integer.parseInt(TableNumberTxt.getText()), Request.PAYMENT);
      notifyObservers(obs);
    }
    if (EmergencyIssueBox.isSelected()) {
      curRequest = new HelpRequest(Integer.parseInt(TableNumberTxt.getText()), Request.EMERGENCY);
      notifyObservers(obs);
    }
    confirmLabel.setText("Help Request Sent");
  }

  public Observer obs;

  @Override
  public void addObservers(Observer obs) {
    this.obs = obs;

  }

  @Override
  public void notifyObservers(Observer obs) {
    obs.update(curRequest);
  }

  @Override
  public void acceptBoolean(Boolean bool) {}


}
