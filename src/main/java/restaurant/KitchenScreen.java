package restaurant;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * @author Mathushan, Manpreet
 */
public class KitchenScreen {

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

  public void setOrderList(List<String> orderList) {
    newOrdersList.getItems().setAll(orderList);
  }
}
