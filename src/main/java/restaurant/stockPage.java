package restaurant;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class stockPage implements ViewInterface, Subject{

  @FXML
  private Button activeOrderButton;

  @FXML
  private Text addProduct;

  @FXML
  private Button allOrdersButton;

  @FXML
  private AnchorPane bgPane;

  @FXML
  private VBox categoryField;

  @FXML
  private TextField descriptionField;

  @FXML
  private Text dessertsHeading;

  @FXML
  private VBox dessertsVbox;

  @FXML
  private Text drinksHeading;

  @FXML
  private VBox drinksVbox;

  @FXML
  private Separator horSeparator;

  @FXML
  private Button incomingOrderButton;

  @FXML
  private ImageView logoImage;

  @FXML
  private Button logoutButton;

  @FXML
  private Text mainsHeading;

  @FXML
  private VBox mainsVbox;

  @FXML
  private TextField nameField;

  @FXML
  private Text nameOfNewProduct;

  @FXML
  private Text newProductCategory;

  @FXML
  private Text newProductDescription;

  @FXML
  private Text newProductPrice;

  @FXML
  private Button notificationsButton;

  @FXML
  private TextField priceField;

  @FXML
  private VBox sidebarVbox;

  @FXML
  private Text starterHeading;

  @FXML
  private VBox starterVbox;

  @FXML
  private Button stockButton;

  @FXML
  private Text stockHeading;

  @FXML
  private Separator verSeparator;

  
  public void initialize() {

    Image title = new Image("/images/newoaxacaLogo.png");
    logoImage.setImage(title);
  }
  
  /**
   * Handling for if the user presses the View New Order button.
   *
   * @param event representing the button press
   * @throws IOException If an IO error occurs
   */

  @FXML

  public void handleNewOrderViewBtn(ActionEvent event) throws IOException {

    FXMLLoader loader =

        new FXMLLoader(getClass().getClassLoader().getResource("NewOrdersView.fxml"));

    Parent startViewParent = loader.load();

    Scene startView = new Scene(startViewParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    obs.setView(loader.getController());

    obs.orderStartup();

    window.setScene(startView);

    window.show();

  }



  /**
   * Handling for if a user presses the waiter view button.
   *
   * @param event representing the button push
   * @throws IOException if an IO error occurs
   */

  @FXML

  public void handleWaiterViewBtn(ActionEvent event) throws IOException {

    FXMLLoader loader =

        new FXMLLoader(getClass().getClassLoader().getResource("WaiterScreenView.fxml"));

    Parent startViewParent = loader.load();

    Scene startView = new Scene(startViewParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    obs.setView(loader.getController());

    obs.orderStartup();

    window.setScene(startView);

    window.show();

  }



  /**
   * Handling for if the user presses Log Out.
   *
   * @param event representing the button press.
   * @throws IOException if an IO error occurs
   */
  @FXML
  public void handleLogOutBtn(ActionEvent event) throws IOException {

    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("staffLogin.fxml"));

    Parent startViewParent = loader.load();

    Scene startView = new Scene(startViewParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    obs.setView(loader.getController());

    window.setScene(startView);

    window.show();

  }



  /**
   * Handling for if the all order switcher is pressed.
   *
   * @param event representing the button push
   * @throws IOException if an IO error occurs
   */
  @FXML
  public void handleAllOrderBtn(ActionEvent event) throws IOException {

    FXMLLoader loader =

        new FXMLLoader(getClass().getClassLoader().getResource("KitchenScreen.fxml"));

    Parent startViewParent = loader.load();

    Scene startView = new Scene(startViewParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    obs.setView(loader.getController());
    obs.orderStartup();

    window.setScene(startView);

    window.show();

  }

  public void handleNotificationsBtn(ActionEvent event) throws IOException {

    FXMLLoader loader =

        new FXMLLoader(getClass().getClassLoader().getResource("NotificationScreen.fxml"));

    Parent startViewParent = loader.load();

    Scene startView = new Scene(startViewParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    obs.setView(loader.getController());
    obs.orderStartup();

    window.setScene(startView);

    window.show();

  }
  
  public void handleStockBtn(ActionEvent event) throws IOException {

    FXMLLoader loader =

        new FXMLLoader(getClass().getClassLoader().getResource("stockPage.fxml"));

    Parent startViewParent = loader.load();

    Scene startView = new Scene(startViewParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    obs.setView(loader.getController());
    obs.orderStartup();

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



  }



  @Override

  public void acceptBoolean(Boolean bool) {



  }



  @Override

  public void startup() {

    // TODO Auto-generated method stub



  }
  
}
