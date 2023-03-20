package restaurant;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * View representing StockPage of available items/ add items.
 *
 * @author Mathushan, Manpreet
 */
public class StockPage implements ViewInterface, Subject {

  @FXML
  private ListView<String> mainListView;

  @FXML
  private ListView<String> drinksListView;

  @FXML
  private ListView<String> starterListView;

  @FXML
  private ListView<String> dessertsListView;

  @FXML
  private Button activeOrderButton;

  @FXML
  private Text addProduct;

  @FXML
  private Button addItemBtn;

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
  private Text drinksHeading;


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
  private Button stockButton;

  @FXML
  private Text stockHeading;

  @FXML
  private Button toggleButton;

  @FXML
  private Separator verSeparator;

  /**
   * Loads Oaxaca logo.
   */
  public void initializeAfter() {

    Image title = new Image("/images/newoaxacaLogo.png");
    logoImage.setImage(title);
  }
  
  /**
   * Handling for when the toggle order button is pressed.
   *
   * @param event representing the button push
   */
  @FXML
  public void handleToggleButton(ActionEvent event) {
    String selectedItem = starterListView.getSelectionModel().getSelectedItem();
    if (selectedItem == null) {
      selectedItem = mainListView.getSelectionModel().getSelectedItem();
    }
    if (selectedItem == null) {
      selectedItem = drinksListView.getSelectionModel().getSelectedItem();
    }
    if (selectedItem == null) {
      selectedItem = dessertsListView.getSelectionModel().getSelectedItem();
    }
    obs.toggleItemStock(selectedItem);
    startup();
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


  /**
   * Handling for if the user presses Notification button.
   *
   * @param event representing the button press.
   * @throws IOException if an IO error occurs
   */
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

  /**
   * Handling for if the user presses stockPage button.
   *
   * @param event representing the button press.
   * @throws IOException if an IO error occurs
   */
  public void handleStockBtn(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("stockPage.fxml"));
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
    listExit();
    ResultSet starters = obs.getMenuType(ItemType.STARTER);
    ResultSet mains = obs.getMenuType(ItemType.MAIN);
    ResultSet drinks = obs.getMenuType(ItemType.DRINK);
    ResultSet desserts = obs.getMenuType(ItemType.DESSERT);
    try {
      while (starters.next()) {
        starterListView.getItems().add(starters.getString(1));
      }
      while (mains.next()) {
        mainListView.getItems().add(mains.getString(1));
      }
      while (drinks.next()) {
        drinksListView.getItems().add(drinks.getString(1));
      }
      while (desserts.next()) {
        dessertsListView.getItems().add(desserts.getString(1));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    initializeAfter();

  }

  private void listExit() {
    starterListView.getItems().clear();
    mainListView.getItems().clear();
    drinksListView.getItems().clear();
    dessertsListView.getItems().clear();
  }

}
