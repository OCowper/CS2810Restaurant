import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

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
    private ListView<?> newOrdersList;

    @FXML
    private Text orderHistory;

    @FXML
    private ListView<?> orderHistoryList;

}
