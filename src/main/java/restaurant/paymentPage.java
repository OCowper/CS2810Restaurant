package restaurant;

import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class paymentPage {

    @FXML
    private Text CVV;

    @FXML
    private AnchorPane bgAnchorpane;

    @FXML
    private VBox bgVbox;

    @FXML
    private Text cardNumber;

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField cvvField;

    @FXML
    private Text expiryDate;

    @FXML
    private TextField expiryDateField;

    @FXML
    private ImageView logo;

    @FXML
    private Text nameOnCard;

    @FXML
    private TextField nameOnCardField;

    @FXML
    private Button payNowButton;

    @FXML
    private Text paymentHeading;

}
