package restaurant;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Class representing the payment page.
 *
 * @author Mathushan, Manpreet
 */
public class PaymentPage implements Subject, ViewInterface {

  @FXML
  private Text cvv;

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
  private Button emptyButton;

  @FXML
  private Text expiryDate;

  @FXML
  private TextField expiryDateField;

  @FXML
  private ImageView oaxacaImageView;

  @FXML
  private Text nameOnCard;

  @FXML
  private TextField nameOnCardField;

  @FXML
  private Button payNowButton;

  @FXML
  private Text paymentHeading;

  @FXML
  private Button returnButton;

  /**
   * Initialization method.
   */
  public void initialize() {

    Image title = new Image("/images/newoaxacaLogo.png");
    oaxacaImageView.setImage(title);

    // Add event listeners for validation
    cardNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d{0,16}")) {
            cardNumberField.setText(oldValue);
        }
    });
  
    cvvField.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d{0,3}")) {
            cvvField.setText(oldValue);
        }
    });
    
  }

  /**
   * Handling for if the return button is pressed.
   *
   * @param event representing the button push
   * @throws IOException if an IO error occurs
   */
  @FXML
  public void handleReturnBtn(ActionEvent event) throws IOException {
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("checkoutPage.fxml"));
    Parent cartParent = loader.load();
    Scene checkout = new Scene(cartParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());

    window.setScene(checkout);
    window.show();

  }

  /**
   * Handling for if the pay now button is pressed.
   *
   * @param event representing the button push
   * @throws IOException if an IO error occurs
   */
  public void handlepayNowBtn(ActionEvent event) throws IOException {
    // Check if the card number field is valid
    String cardNumber = cardNumberField.getText().trim();
    if (cardNumber.isEmpty() || !cardNumber.matches("\\d{16}")) {
        // If it's not valid, highlight the field in red
        cardNumberField.setStyle("-fx-border-color: red;");
        return;
    }

    // Check if the cvv field is valid
    String cvv = cvvField.getText().trim();
    if (cvv.isEmpty() || !cvv.matches("\\d{3}")) {
        // If it's not valid, highlight the field in red
        cvvField.setStyle("-fx-border-color: red;");
        return;
    }

    // Check if the expiry date field is valid
    String expiryDate = expiryDateField.getText().trim();
    if (expiryDate.isEmpty() || !expiryDate.matches("\\d{2}/\\d{2}")) {
        // If it's not valid, highlight the field in red
        expiryDateField.setStyle("-fx-border-color: red;");
        return;
    }

    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("paymentConfirmation.fxml"));
    Parent paymentConfirmationParent = loader.load();
    Scene paymentConfirmation = new Scene(paymentConfirmationParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());

    window.setScene(paymentConfirmation);
    window.show();
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

  @Override
  public void startup() {

  }

}
