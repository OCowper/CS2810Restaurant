package restaurant;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

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
  private ListView<?> inProgressOrdersList;

  @FXML
  private Text newOrders;

  @FXML
  private ListView<String> newOrdersList;

  @FXML
  private Text orderHistory;
  
  @FXML
  private ListView<?> orderHistoryList;

  /**
   * Sets the list of orders.
   *
   * @param orderList current list of orders.
   */
  public void setOrderList(List<String> orderList) {
    newOrdersList.getItems().setAll(orderList);
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
