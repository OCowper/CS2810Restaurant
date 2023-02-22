package restaurant;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class checkoutPage implements Subject, ViewInterface {

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
  private Button menuButton;

  @FXML
  private ImageView oaxacaImageView;

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

  public void initialize(){
    Image background = new Image ("/images/plata-o-plomo-1.jpeg");
    Image title = new Image("/images/title.png");
    oaxacaImageView.setImage(title);
    bgImage.setImage(background);
  }
  
  /**
   * @param event
   * @throws IOException
   */
  public void handleCustomerBtn(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("model.fxml"));
    Parent foodMenuparent = loader.load();
    
    Scene foodMenu = new Scene(foodMenuparent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();  
    obs.setView(loader.getController());
    obs.orderStartup();
    window.setScene(foodMenu);
    window.show();

  }
  
  public void handleCartBtn(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("checkoutPage.fxml"));
    Parent cartParent = loader.load();
    Scene checkout = new Scene(cartParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());

    window.setScene(checkout);
    window.show();
  }
  
  public void handleCheckoutButton(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("paymentPage.fxml"));
    Parent paymentParent = loader.load();
    Scene payment = new Scene(paymentParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());

    window.setScene(payment);
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
