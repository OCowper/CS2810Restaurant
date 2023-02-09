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
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * View class representing the waiter screen.
 *
 * @author Mathushan, Manpreet
 */
public class WaiterScreenView implements Subject, ViewInterface {

  @FXML
  private Text activeOrdersHeading;

  @FXML
  private Button allOrdersButton;

  @FXML
  private Pane backgroundPane;

  @FXML
  private Separator horizontalSeparator1;

  @FXML
  private Line horizontalSeparator2;

  @FXML
  private Text itemsHeading;

  @FXML
  private ListView<?> itemsListView;

  @FXML
  private Button newOrdersButton;

  @FXML
  private Text orderNumberHeading;

  @FXML
  private ListView<?> orderNumberListView;

  @FXML
  private Button returnbtn;

  @FXML
  private Text tableNumberHeading;

  @FXML
  private ListView<?> tableNumberListView;

  @FXML
  private Text totalPriceHeading;

  @FXML
  private ListView<?> totalPriceListView;

  @FXML
  private Separator verticalSeparator1;

  @FXML
  private Separator verticalSeparator2;

  @FXML
  private Separator verticalSeparator3;

  /**
   * Handling for if the user presses the return button.
   *
   * @param event representing the button push
   * @throws IOException if an IO error occurs
   */
  @FXML
  public void handleReturnMenuBtn(ActionEvent event) throws IOException {
    Parent startViewParent = FXMLLoader.load(getClass().getResource("StaffPanel.fxml"));
    Scene startView = new Scene(startViewParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(startView);
    window.show();
  }
  
  /**
   * Handling if a user presses the New Orders button.
   *
   * @param event representing the button push
   * @throws IOException if an IO error occurs
   */
  @FXML
  public void handleNewOrdersBtn(ActionEvent event) throws IOException {
    Parent startViewParent = FXMLLoader.load(getClass().getResource("NewOrdersView.fxml"));
    Scene startView = new Scene(startViewParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(startView);
    window.show();
  }
  
  /**
   * Handling for if a user presses the All order button.
   *
   * @param event representing the button push
   * @throws IOException if an IO error occurs
   */
  @FXML
  public void handleAllOrderBtn(ActionEvent event) throws IOException {
    Parent startViewParent = FXMLLoader.load(getClass().getResource("KitchenScreen.fxml"));
    Scene startView = new Scene(startViewParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(startView);
    window.show();
  }
  
  public Observer obs;

  @Override
  public void acceptBoolean(Boolean bool) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addObservers(Observer obs) {
    this.obs = obs;
    
  }

  @Override
  public void notifyObservers(Observer obs) {
    // TODO Auto-generated method stub
    
  }
}
