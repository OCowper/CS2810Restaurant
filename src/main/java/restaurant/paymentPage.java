package restaurant;

import javafx.scene.control.TextField;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class paymentPage implements Subject, ViewInterface {

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
  
  public void initialize(){

    Image title = new Image("/images/newoaxacaLogo.png");
    oaxacaImageView.setImage(title);

  }
  
  public void handleReturnBtn(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("checkoutPage.fxml"));
    Parent cartParent = loader.load();
    Scene checkout = new Scene(cartParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());

    window.setScene(checkout);
    window.show();
    
  }
  public void handlepayNowBtn(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("paymentConfirmation.fxml"));
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
      // TODO Auto-generated method stub
      
    }

}
