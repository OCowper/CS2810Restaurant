package restaurant;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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


  /**
   * Handling for if the return button is pushed.
   *
   * @param event representing the button push
   * @throws IOException if an IO error occurs.
   */
  public void handleReturnBtn(ActionEvent event) throws IOException {
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("newLandingPage.fxml"));

    Parent cartParent = loader.load();
    Scene checkout = new Scene(cartParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());

    window.setScene(checkout);
    window.show();



  }



}
