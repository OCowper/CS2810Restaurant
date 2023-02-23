package restaurant;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Class representing the payment confirmation page.
 *
 * @author Mathushan, Manpreet
 */
public class PaymentConfirmation implements Subject, ViewInterface {

  @FXML
  private AnchorPane bgAnchorpane;

  @FXML
  private VBox bgVbox;

  @FXML
  private Button emptyButton;

  @FXML
  private ImageView oaxacaImageView;

  @FXML
  private Label orderLabel;

  @FXML
  private Label ordernumberLabel;

  @FXML
  private Label orderplacedLabel;

  @FXML
  private Text paymentHeading;

  @FXML
  private Button returnButton;

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
