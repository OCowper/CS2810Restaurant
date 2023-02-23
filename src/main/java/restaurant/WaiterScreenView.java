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
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * View class representing the waiter screen.
 *
 * @author Mathushan, Manpreet
 */
public class WaiterScreenView implements Subject, ViewInterface {

  @FXML
  private Button activeOrdersButton;

  @FXML
  private Text activeOrdersLabel;

  @FXML
  private Button allOrdersButton;

  @FXML
  private Separator horizontalSeparator1;

  @FXML
  private Line horizontalSeparator2;

  @FXML
  private Button incomingOrders;

  @FXML
  private Text itemsHeading;

  @FXML
  private ListView<String> itemsListView;

  @FXML
  private Button logoutButton;

  @FXML
  private ImageView oaxacaImageView;

  @FXML
  private Button orderConfirmBtn;

  @FXML
  private Text orderNumberHeading;

  @FXML
  private ListView<String> orderNumberListView;

  @FXML
  private Text orderStatusHeading;

  @FXML
  private Button stockButton;

  @FXML
  private Text tableNumberHeading;

  @FXML
  private ListView<String> tableNumberListView;

  @FXML
  private Text totalPriceHeading;

  @FXML
  private ListView<String> totalPriceListView;

  @FXML
  private Separator verticalSeparator1;

  @FXML
  private Separator verticalSeparator2;

  @FXML
  private Separator verticalSeparator3;

  @FXML
  private Separator verticalSeparator4;
  
  @FXML
  private TextField completeTextField;

  public void initialize() {
    Image title = new Image("/images/newoaxacaLogo.png");
    oaxacaImageView.setImage(title);
  }

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
   * 
   * Handling for if a user presses the waiter view button.
   *
   * 
   * 
   * @param event representing the button push
   * 
   * @throws IOException if an IO error occurs
   * 
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
   * 
   * Handling for if the user presses Log Out.
   *
   * 
   * 
   * @param event representing the button press.
   * 
   * @throws IOException if an IO error occurs.
   * 
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
  void handleOrderConfirm(ActionEvent event) {
    obs.update(Integer.parseInt(completeTextField.getText()));
    startup();
  }

  public Observer obs;

  @Override
  public void acceptBoolean(Boolean bool) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addObservers(Observer obs) {
    this.obs = obs;

  }

  @Override
  public void notifyObservers(Observer obs) {
    // TODO Auto-generated method stub

  }

  @FXML
  private void listExit() {
    orderNumberListView.getItems().clear();
    itemsListView.getItems().clear();
    tableNumberListView.getItems().clear();
    totalPriceListView.getItems().clear();
  }

  @FXML
  @Override
  public void startup() {
    listExit();
    ResultSet rs = obs.returnOrders(true);

    try {
      while (rs.next()) {
        orderNumberListView.getItems().add(String.valueOf(rs.getInt(1)));
        itemsListView.getItems().add(rs.getString(2));
        tableNumberListView.getItems().add(String.valueOf(rs.getInt(3)));
        totalPriceListView.getItems().add(String.valueOf(rs.getInt(4)));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }
}
