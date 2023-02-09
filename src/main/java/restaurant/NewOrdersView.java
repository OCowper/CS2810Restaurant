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
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * View representing waiter view of new orders.
 *
 * @author Mathushan, Manpreet
 */
public class NewOrdersView implements ViewInterface, Subject {


  @FXML
  private Button acceptButton;

  @FXML
  private Button activeOrdersButton;

  @FXML
  private Button allOrdersButton;

  @FXML
  private Pane bgPane;

  @FXML
  private Separator horizontalSeparator1;

  @FXML
  private Line horizontalSeparator2;

  @FXML
  private Text itemsHeading;

  @FXML
  private ListView<String> itemsListView;

  @FXML
  private Button menurtn;

  @FXML
  private Text newOrdersHeading;

  @FXML
  private Text orderNumberHeading;

  @FXML
  private ListView<String> orderNumberListView;

  @FXML
  private Text orderStatusHeading;

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

  /**
   * Handles a user pressing the return button.
   *
   * @param event the event representing button push
   * @throws IOException if an IO error occurs.
   */
  @FXML
  public void handleReturnMenuBtn(ActionEvent event) throws IOException {


    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("staffPanel.fxml"));
    Parent startViewParent = loader.load();
    Scene startView = new Scene(startViewParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());
    window.setScene(startView);
    window.show();
  }

  /**
   * Handling for if a user presses the Active Order button.
   *
   * @param event represents the button push.
   * @throws IOException if an IO error occurs.
   */
  @FXML
  public void handleActiveOrderBtn(ActionEvent event) throws IOException {
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("WaiterScreenView.fxml"));
    Parent startViewParent = loader.load();
    Scene startView = new Scene(startViewParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());
    window.setScene(startView);
    window.show();
  }

  /**
   * Handling if a user presses the All Order button.
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
    window.setScene(startView);
    window.show();
  }

  @FXML
  void confirmTopOrder(ActionEvent event) {

  }

  @FXML
  @Override
  public void startup() {
    ResultSet rs = obs.returnOrders();
    
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

  public Observer obs;

  @Override
  public void addObservers(Observer obs) {
    this.obs = obs;

  }

  @Override
  public void notifyObservers(Observer obs) {}

  @Override
  public void acceptBoolean(Boolean bool) {}

}
