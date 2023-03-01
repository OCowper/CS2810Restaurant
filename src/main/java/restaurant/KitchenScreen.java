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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * View representing the screen used by the kitchen for order purposes.
 *
 * @author Mathushan, Manpreet, zkac355
 */
public class KitchenScreen implements Subject, ViewInterface {

  @FXML
  private Button activeOrdersButton;

  @FXML
  private Button allOrdersButton;

  @FXML
  private Text cancelHistoryLabel;

  @FXML
  private ListView<String> cancelHistoryList;

  @FXML
  private Text inProgressOrders;

  @FXML
  private ListView<String> inProgressOrdersList;

  @FXML
  private Button incomingOrders;

  @FXML
  private Button logoutButton;

  @FXML
  private Text newOrders;

  @FXML
  private ListView<String> newOrdersList;

  @FXML
  private ImageView oaxacaImageView;

  @FXML
  private Text orderHistory;

  @FXML
  private ListView<String> orderHistoryList;

  @FXML
  private Button stockButton;

  /**
   * Initialization method.
   */
  public void initialize() {
    Image title = new Image("/images/newoaxacaLogo.png");
    oaxacaImageView.setImage(title);
  }

  /**
   * Sets the list of orders.
   */
  @Override
  public void startup() {
    listExit();
    ResultSet newOrders = obs.returnOrders(false, false);
    ResultSet progressOrders = obs.returnOrders(true, false);
    ResultSet completedOrders = obs.returnOrders(true, true);
    ResultSet cancelledOrders = obs.returnOrders(false, true);
    try {
      while (newOrders.next()) {
        newOrdersList.getItems().add(String.valueOf(newOrders.getInt(1)));
      }
      while (progressOrders.next()) {
        inProgressOrdersList.getItems().add(String.valueOf(progressOrders.getInt(1)));
      }
      while (completedOrders.next()) {
        orderHistoryList.getItems().add(String.valueOf(completedOrders.getInt(1)));
      }
      while (cancelledOrders.next()) {
        cancelHistoryList.getItems().add(String.valueOf(cancelledOrders.getInt(1)));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Handler for if the switch to new orders button is pressed.
   *
   * @param event representing the button push
   * @throws IOException if an IO error occurs
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
   * @throws IOException if an IO error occurs.
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
   * Handling for if the switcher to the all orders screen is pressed.
   *
   * @param event representing the button push.
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
  
  @FXML
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

  @FXML
  private void listExit() {
    newOrdersList.getItems().clear();
    inProgressOrdersList.getItems().clear();
    orderHistoryList.getItems().clear();
  }

  public Observer obs;

  @Override
  public void addObservers(Observer obs) {
    this.obs = obs;

  }

  @Override
  public void notifyObservers(Observer obs) {
    // TODO

  }

  @Override
  public void acceptBoolean(Boolean bool) {
    // TODO Auto-generated method stub

  }
}
