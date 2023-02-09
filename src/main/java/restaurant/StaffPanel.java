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
import javafx.stage.Stage;

/**
 * View representing the Staff Panel.
 *
 * @author Mathushan, Manpreet
 */
public class StaffPanel implements ViewInterface, Subject {

  @FXML
  private Button logoutBtn;

  @FXML
  private Button newOrderViewBtn;

  @FXML
  private Label titelLbl;

  @FXML
  private Button waiterViewBtn;

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
   * @throws IOException if an IO error occurs.
   */
  @FXML
  public void handleLogOutBtn(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("StaffLogin.fxml"));
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

  }

  @Override
  public void acceptBoolean(Boolean bool) {

  }

  @Override
  public void startup() {
    // TODO Auto-generated method stub

  }

}
