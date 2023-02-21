package restaurant;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class checkoutPage {

    @FXML
    private Button aboutUsButton;

    @FXML
    private AnchorPane bgAnchorPane;

    @FXML
    private ImageView bgImage;

    @FXML
    private Button cartButton;

    @FXML
    private Text heading;

    @FXML
    private Text itemsHeading;

    @FXML
    private ListView<?> itemsListview;

    @FXML
    private ImageView logoImage;

    @FXML
    private Button menuButton;

    @FXML
    private Button placeOrderButton;

    @FXML
    private Text priceHeading;

    @FXML
    private ListView<?> priceListview;

    @FXML
    private Text quantityHeading;

    @FXML
    private ListView<?> quantityListview;

    @FXML
    private Button removeItemButton;

    @FXML
    private TextField totalAmount;

    @FXML
    private Text totalField;

    @FXML
    private VBox virtualBox;

}
