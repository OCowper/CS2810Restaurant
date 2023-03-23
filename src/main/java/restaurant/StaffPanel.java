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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



/**
 * View representing the Staff Panel.
 *
 * @author Mathushan, Manpreet
 */

public class StaffPanel implements ViewInterface, Subject {

  @FXML
  private Button AssignTableNumberButton;

  @FXML
  private Label CurrentTablesLabel;

  @FXML
  private Button NotifictionsButton;

  @FXML
  private Button logoutBtn;

  @FXML
  private Button newOrderViewBtn;

  @FXML
  private Label titelLbl;

  @FXML
  private Button waiterViewBtn;

  @FXML
  private ListView<String> TabelNumberListView;
  

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
   * Handling for if the user presses the Notifications.
   *
   * @param event representing the button press
   * @throws IOException If an IO error occurs
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
   * Handling for if the user presses the Stock Button.
   *
   * @param event representing the button press
   * @throws IOException If an IO error occurs
   */
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
  
  /**
   * Handling for if Menu Return switcher is pressed.
   *
   * @param event representing the button press
   * @throws IOException if an IO error occurs.
   */
  public void handleMenuReturn(MouseEvent event) throws IOException {
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("staffPanel.fxml"));
    Parent paymentParent = loader.load();
    Scene payment = new Scene(paymentParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());

    window.setScene(payment);
    window.show();
  }


  @FXML
  void handleAssign(ActionEvent event) {
    ResultSet rs = obs.getTables();
    try {
      while (rs.next()) {
        TabelNumberListView.getItems().add(String.valueOf(rs.getInt(1)));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
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

