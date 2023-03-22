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
import javafx.scene.input.KeyEvent;
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
   * Initialisation method.
   */
  public void initialize() {

    Image title = new Image("/images/newoaxacaLogo.png");
    oaxacaImageView.setImage(title);

    // Add event listeners for validation of card number, CVV, and name on card
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

    nameOnCardField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("^[a-zA-Z\\s]*$")) {
        nameOnCardField.setText(oldValue);
      }
    });

    // Add listener to automatically add slash after first two numbers are entered in
    // expiryDateField
    expiryDateField.textProperty().addListener((observable, oldValue, newValue) -> {
      String value = newValue.trim();
      if (value.length() == 2 && !oldValue.endsWith("/") && !newValue.endsWith("/")) {
        expiryDateField.setText(value + "/");
      }
      if (value.length() > 5) {
        expiryDateField.setText(oldValue);
      }
    });

    // Add key event filter to expiryDateField to only allow digits
    expiryDateField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
      // Get the character typed
      String character = event.getCharacter();
      // Only allow digits
      if (!"0123456789".contains(character)) {
        event.consume();
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
    boolean isValid = true;

    // Validate the card number
    String cardNumber = cardNumberField.getText().trim();
    if (!cardNumber.matches("^\\d{16}$")) {
      cardNumberField.setStyle("-fx-border-color: red;");
      isValid = false;
    } else {
      cardNumberField.setStyle("");
    }

    // Validate the CVV
    String cvv = cvvField.getText().trim();
    if (!cvv.matches("^\\d{3}$")) {
      cvvField.setStyle("-fx-border-color: red;");
      isValid = false;
    } else {
      cvvField.setStyle("");
    }

    // Validate the expiry date
    String expiryDate = expiryDateField.getText().trim();
    if (!expiryDate.matches("^\\d{2}/\\d{2}$")) {
      expiryDateField.setStyle("-fx-border-color: red;");
      isValid = false;
    } else {
      expiryDateField.setStyle("");
      String[] parts = expiryDate.split("/");
      int month = Integer.parseInt(parts[0]);
      int year = Integer.parseInt(parts[1]);
      if (month < 1 || month > 12 || year < 0 || year > 99) {
        expiryDateField.setStyle("-fx-border-color: red;");
        isValid = false;
      }
    }
    // Validate the name on card
    String nameOnCard = nameOnCardField.getText().trim();
    if (!nameOnCard.matches("^[a-zA-Z\\s]+$")) {
      nameOnCardField.setStyle("-fx-border-color: red;");
      isValid = false;
    } else {
      nameOnCardField.setStyle("");
    }

    if (isValid) {
      // If all fields are valid, switch to the payment confirmation page
      FXMLLoader loader =
          new FXMLLoader(getClass().getClassLoader().getResource("paymentConfirmation.fxml"));
      Parent paymentConfirmationParent = loader.load();
      Scene paymentConfirmation = new Scene(paymentConfirmationParent);

      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
      obs.setView(loader.getController());
      obs.orderStartup();
      window.setScene(paymentConfirmation);
      window.show();
    } else {
      // Highlight invalid fields and stay on the same page
      payNowButton.setStyle("");
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

  @Override
  public void startup() {

  }

}
