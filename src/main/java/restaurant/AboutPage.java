package restaurant;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AboutPage implements Subject, ViewInterface {

    @FXML
    private Text aboutUsHeading;

    @FXML
    private AnchorPane bgPane;

    @FXML
    private Button cartButton;

    @FXML
    private Button helpButton;

    @FXML
    private ImageView logoImage;

    @FXML
    private Button menuButton;

    @FXML
    private Button returnButton;

    @FXML
    private VBox sidebarVBox;

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
     * Changes the scene from initial one to food menu view.
     *
     * @param event "customerbtn" button pressed
     * @throws IOException when there is a problem with loading the fxml file
     */
    public void handleCustomerBtn(ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("CustomerMenu.fxml"));
      Parent foodMenuparent = loader.load();

      Scene foodMenu = new Scene(foodMenuparent);
      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
      obs.setView(loader.getController());
      obs.orderStartup();
      window.setScene(foodMenu);
      window.show();

    }

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


    public void handleHelpBtn(ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Help.fxml"));
      Parent staffLodinParent = loader.load();
      Scene staffLogin = new Scene(staffLodinParent);

      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
      obs.setView(loader.getController());

      window.setScene(staffLogin);
      window.show();
    }

    /**
     * Handling for if the cart screen switcher is pressed.
     *
     * @param event representing the button press
     * @throws IOException if an IO error occurs.
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