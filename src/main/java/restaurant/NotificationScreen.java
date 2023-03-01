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
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NotificationScreen implements ViewInterface, Subject {

    @FXML
    private Button NotificationsButton;

    @FXML
    private Button aboutUsButton111;

    @FXML
    private Button activeOrdersButton;

    @FXML
    private Button allOrdersButton;

    @FXML
    private Separator horizontalSeparator1;

    @FXML
    private Line horizontalSeparator2;

    @FXML
    private Button incomingOrders;

    @FXML
    private Text issueHeading;

    @FXML
    private ListView<?> issueListView;

    @FXML
    private Button logoutButton;

    @FXML
    private Text notificationsLabel;

    @FXML
    private ImageView oaxacaImageView;

    @FXML
    private Text orderNumberHeading;

    @FXML
    private ListView<?> orderNumberListView;

    @FXML
    private Text orderStatusHeading;

    @FXML
    private Button resolvedBtn;

    @FXML
    private TextField resolvedTextField;

    @FXML
    private Button stockButton;

    @FXML
    private Separator verticalSeparator1;

    @FXML
    private Separator verticalSeparator4;

    /**
     * Handling for if the user presses the View New Order button.
     *
     * @param event representing the button press 
     * @throws IOException If an IO error occurs
     */

    @FXML

    public void handleNewOrderViewBtn(ActionEvent event) throws IOException {

      FXMLLoader loader =

          new FXMLLoader(getClass().getClassLoader().getResource("NewOrdersView.fxml"));

      Parent startViewParent = loader.load();

      Scene startView = new Scene(startViewParent);

      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

      obs.setView(loader.getController());

      obs.orderStartup();

      window.setScene(startView);

      window.show();

    }

    @FXML
    void handleIssueResolved(ActionEvent event) {

    }

    /**
     * Handling for if a user presses the waiter view button.
     *
     * @param event representing the button push
     * @throws IOException if an IO error occurs
     */

    @FXML

    public void handleWaiterViewBtn(ActionEvent event) throws IOException {

      FXMLLoader loader =

          new FXMLLoader(getClass().getClassLoader().getResource("WaiterScreenView.fxml"));

      Parent startViewParent = loader.load();

      Scene startView = new Scene(startViewParent);

      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

      obs.setView(loader.getController());

      obs.orderStartup();

      window.setScene(startView);

      window.show();

    }



    /**
     * Handling for if the user presses Log Out.
     *
     * @param event representing the button press.
     * @throws IOException if an IO error occurs
     */
    @FXML
    public void handleLogOutBtn(ActionEvent event) throws IOException {

      FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("staffLogin.fxml"));

      Parent startViewParent = loader.load();

      Scene startView = new Scene(startViewParent);

      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

      obs.setView(loader.getController());

      window.setScene(startView);

      window.show();

    }



    /**
     * Handling for if the all order switcher is pressed.
     *
     * @param event representing the button push
     * @throws IOException if an IO error occurs
     */
    @FXML
    public void handleAllOrderBtn(ActionEvent event) throws IOException {

      FXMLLoader loader =

          new FXMLLoader(getClass().getClassLoader().getResource("KitchenScreen.fxml"));

      Parent startViewParent = loader.load();

      Scene startView = new Scene(startViewParent);

      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

      obs.setView(loader.getController());
      obs.orderStartup();

      window.setScene(startView);

      window.show();

    }
    
    public void handleNotificationsBtn(ActionEvent event) throws IOException {

      FXMLLoader loader =

          new FXMLLoader(getClass().getClassLoader().getResource("NotificationScreen.fxml"));

      Parent startViewParent = loader.load();

      Scene startView = new Scene(startViewParent);

      Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

      obs.setView(loader.getController());
      obs.orderStartup();

      window.setScene(startView);

      window.show();

    }

    
    
    public Observer obs;

    @Override

    public void addObservers(Observer obs) {

      this.obs = obs;

    }



    @Override

    public void notifyObservers(Observer obs) {

    }



    @Override

    public void acceptBoolean(Boolean bool) {

    }



    @Override

    public void startup() {

      // TODO Auto-generated method stub

    }


}
