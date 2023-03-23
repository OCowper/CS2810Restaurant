package restaurant;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Class representing the checkout page.
 *
 * @author Manpreet, Mathushan
 */
public class CheckoutPage implements Subject, ViewInterface {

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
  private Button helpButton;

  @FXML
  private Text itemsHeading;

  @FXML
  private ListView<String> itemsListview;

  @FXML
  private Button menuButton;

  @FXML
  private ImageView oaxacaImageView;

  @FXML
  private Button placeOrderButton;

  @FXML
  private Text priceHeading;

  @FXML
  private ListView<String> priceListview;

  @FXML
  private Text quantityHeading;

  @FXML
  private ListView<String> quantityListview;

  @FXML
  private Button removeItemButton;

  @FXML
  private TextField totalAmount;

  @FXML
  private Text totalField;

  @FXML
  private Button trackOrderButton;

  @FXML
  private VBox virtualBox;

  /**
   * Initialization method.
   */
  public void initialize() {
    Image background = new Image("/images/plata-o-plomo-1.jpeg");
    Image title = new Image("/images/newoaxacaLogo.png");
    oaxacaImageView.setImage(title);
    bgImage.setImage(background);
  }

  /**
   * Handler for if the customer button is pressed.
   *
   * @param event representing the button press
   * @throws IOException if an IO error occurs
   */
  public void handleCustomerBtn(ActionEvent event) throws IOException {
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("CustomerMenu.fxml"));
    Parent foodMenuparent = loader.load();

    Scene foodMenu = new Scene(foodMenuparent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());
    obs.orderStartup();
    window.setScene(foodMenu);
    window.show();

  }

  /**
   * Handler for if the cart button is pressed.
   *
   * @param event representing the button press
   * @throws IOException if an IO error occurs
   */
  public void handleCartBtn(ActionEvent event) throws IOException {
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
   * Handler for if the checkout button is pressed.
   *
   * @param event representing the button press.
   * @throws IOException if an IO error occurs.
   */
  public void handleCheckoutButton(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("paymentPage.fxml"));
    Parent paymentParent = loader.load();
    Scene payment = new Scene(paymentParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());

    window.setScene(payment);
    window.show();
  }

  /**
   * Handler for when the return button is pressed.
   *
   * @param event represents the button press
   * @throws IOException if an IO error occurs
   */
  public void handleMenuReturn(MouseEvent event) throws IOException {
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("newLandingPage.fxml"));
    Parent paymentParent = loader.load();
    Scene payment = new Scene(paymentParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());

    window.setScene(payment);
    window.show();
  }

  /**
   * Handling for if About Us switcher is pressed.
   *
   * @param event representing the button press
   * @throws IOException if an IO error occurs.
   */
  @FXML
  void handleAboutBtn(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AboutPage.fxml"));
    Parent staffLodinParent = loader.load();
    Scene staffLogin = new Scene(staffLodinParent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());
    window.setScene(staffLogin);
    window.show();
  }

  /**
   * Handling for if Help switcher is pressed.
   *
   * @param event representing the button press
   * @throws IOException if an IO error occurs.
   */
  public void handleHelpBtn(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Help.fxml"));
    Parent staffLodinParent = loader.load();
    Scene staffLogin = new Scene(staffLodinParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());
    obs.orderStartup();
    window.setScene(staffLogin);
    window.show();
  }

  /**
   * Handling for if Track Order switcher is pressed.
   *
   * @param event representing the button press
   * @throws IOException if an IO error occurs.
   */
  @FXML
  void handleTrackBtn(ActionEvent event) throws IOException {
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("CustomerNotificationScreen.fxml"));
    Parent staffLodinParent = loader.load();
    Scene staffLogin = new Scene(staffLodinParent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());
    window.setScene(staffLogin);
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
    listexit();
    ResultSet desc = obs.getLatestOrder();
    String[] itemsList = null;
    try {
      while (desc.next()) {
        itemsList = desc.getString(1).split(",");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    ResultSet price;
    for (int i = 0; i < itemsList.length; i++) {
      itemsListview.getItems().add(itemsList[i]);
      quantityListview.getItems().add("1");
      price = obs.getItemPrice(itemsList[i]);
      try {
        while (price.next()) {
          priceListview.getItems().add(String.valueOf(price.getFloat(1)));
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }

    }
  }

  private void listexit() {
    itemsListview.getItems().clear();
    quantityListview.getItems().clear();
    priceListview.getItems().clear();
    totalAmount.clear();
  }

}
