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
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * View representing the screen used by the kitchen for order purposes.
 *
 * @author Mathushan, Manpreet
 */
public class KitchenScreen implements Subject, ViewInterface {

  @FXML
  private Pane backgroundDisplay;

  @FXML
  private Text inProgressOrders;
  
  @FXML
  private Button menuRtn;

  @FXML
  private ListView<String> inProgressOrdersList;

  @FXML
  private Text newOrders;

  @FXML
  private ListView<String> newOrdersList;

  @FXML
  private Text orderHistory;
  
  @FXML
  private ListView<String> orderHistoryList;

  /**
   * Sets the list of orders.
   */
  @Override
  public void startup() {
    listExit();
    ResultSet newOrders = obs.returnOrders(false);
    ResultSet progressOrders = obs.returnOrders(true);
    try {
      while (newOrders.next()) {
        newOrdersList.getItems().add(String.valueOf(newOrders.getInt(1)));
      }
      while (progressOrders.next()) {
        inProgressOrdersList.getItems().add(String.valueOf(progressOrders.getInt(1)));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    for (int i = 0; i < newOrdersList.getItems().size(); i++) {
      orderHistoryList.getItems().add(newOrdersList.getItems().get(i));
    }
    for (int j = 0; j < inProgressOrdersList.getItems().size(); j++) {
      orderHistoryList.getItems().add(inProgressOrdersList.getItems().get(j));
    }
    
  }
  
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
    //TODO
    
  }

  @Override
  public void acceptBoolean(Boolean bool) {
    // TODO Auto-generated method stub
    
  }
}
