package restaurant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Defines the main view of the program.
 *
 * @author Irina Gubaciova, Mathushan Santhan, Manpreet Kaur
 */
public class MainView implements Subject {

  @FXML
  private AnchorPane anchorpane;

  @FXML
  private Label desserts;

  @FXML
  private Label drinks;

  @FXML
  private CheckBox eight;

  @FXML
  private CheckBox eightteen;

  @FXML
  private CheckBox eleven;

  @FXML
  private CheckBox fifteen;

  @FXML
  private CheckBox five;

  @FXML
  private CheckBox four;

  @FXML
  private CheckBox fourteen;

  @FXML
  private Label mains;

  @FXML
  private CheckBox nine;

  @FXML
  private CheckBox nineteen;

  @FXML
  private CheckBox one;

  @FXML
  private Button rtnbtn;

  @FXML
  private ScrollPane scrollpane;

  @FXML
  private TextField searchbar;

  @FXML
  private ListView<?> searchlist;

  @FXML
  private Label selectionlbl;

  @FXML
  private Separator seperator;

  @FXML
  private CheckBox seven;

  @FXML
  private CheckBox seventeen;

  @FXML
  private CheckBox six;

  @FXML
  private CheckBox sixteen;

  @FXML
  private Label starters;

  @FXML
  private Button submitbtn;

  @FXML
  private Label tablelbl;

  @FXML
  private TextField tablenotxt;

  @FXML
  private CheckBox ten;

  @FXML
  private CheckBox thirteen;

  @FXML
  private CheckBox three;

  @FXML
  private Label titlelbl;

  @FXML
  private Label totallbl;

  @FXML
  private TextField totaltxt;

  @FXML
  private CheckBox twelve;

  @FXML
  private CheckBox twenty;

  @FXML
  private CheckBox two;

  @FXML
  private TextField userselections;

  @FXML
  private VBox vbox;


  private HashSet<CheckBox> matchingCheckboxes = new HashSet<>();
  private Map<CheckBox, Double> itemCosts = new HashMap<>();
  private double totalCost = 0;

  /**
   * Initialises item costs.
   */
  public void initialize() {
    scrollpane.setContent(vbox);
    searchbar.setOnAction(e -> handleSearchbarAction());
    itemCosts.put(one, 8.00);
    itemCosts.put(two, 8.00);
    itemCosts.put(three, 8.00);
    itemCosts.put(four, 8.00);
    itemCosts.put(five, 8.00);
    itemCosts.put(six, 8.00);
    itemCosts.put(seven, 8.00);
    itemCosts.put(eight, 8.00);
    itemCosts.put(nine, 8.00);
    itemCosts.put(ten, 8.00);
    itemCosts.put(eleven, 8.00);
    itemCosts.put(twelve, 8.00);
    itemCosts.put(thirteen, 8.00);
    itemCosts.put(fourteen, 8.00);
    itemCosts.put(fifteen, 8.00);
    itemCosts.put(sixteen, 8.00);
    itemCosts.put(seventeen, 8.00);
    itemCosts.put(eightteen, 8.00);
    itemCosts.put(nineteen, 8.00);
    itemCosts.put(twenty, 8.00);

    for (Node node : vbox.getChildren()) {
      if (node instanceof CheckBox) {
        CheckBox checkbox = (CheckBox) node;
        checkbox.setOnAction(e -> handleCheckboxClick(checkbox));
      }
    }
  }

  private void handleCheckboxClick(CheckBox checkbox) {
    if (checkbox.isSelected()) {
      userselections.appendText(checkbox.getText() + ", ");
      totalCost += itemCosts.get(checkbox);
      totaltxt.setText("£" + Double.toString(totalCost));
    } else {
      userselections.setText(userselections.getText().replace(checkbox.getText() + ", ", ""));
      totalCost -= itemCosts.get(checkbox);
      totaltxt.setText("£" + Double.toString(totalCost));
    }
  }

  private HashSet<CheckBox> previouslySelectedCheckboxes = new HashSet<>();

  @FXML
  private void handleSearchbarAction() {
    String text = searchbar.getText();
    matchingCheckboxes.clear();
    matchingCheckboxes.addAll(previouslySelectedCheckboxes);
    for (Node node : vbox.getChildren()) {
      if (node instanceof CheckBox) {
        CheckBox checkBox = (CheckBox) node;
        if (checkBox.getText().toLowerCase().contains(text.toLowerCase())) {
          checkBox.setSelected(true);
          if (checkBox.isSelected()) {
            matchingCheckboxes.add(checkBox);
            totalCost += itemCosts.get(checkBox);
          } else {
            totalCost -= itemCosts.get(checkBox);
          }
        } else {
          if (!previouslySelectedCheckboxes.contains(checkBox)) {
            checkBox.setSelected(false);
          }
        }
      }
    }
    previouslySelectedCheckboxes.clear();
    previouslySelectedCheckboxes.addAll(matchingCheckboxes);

    StringBuilder selectedCheckboxes = new StringBuilder();
    for (CheckBox checkbox : matchingCheckboxes) {
      selectedCheckboxes.append(checkbox.getText()).append(", ");
    }
    userselections.setText(selectedCheckboxes.toString());
    totaltxt.setText("£" + String.valueOf(totalCost));

  }

  /**
   * Handles when the customer button is pressed.
   *
   * @throws IOException if an IO error occurs
   */
  public void handleCustomerRtnBtn() throws IOException {
    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menu.fxml"));
    Stage window = (Stage) rtnbtn.getScene().getWindow();
    window.setScene(new Scene(root, 600, 400));
  }
  
  @FXML
  void isPressed(ActionEvent event) {
    List<String> orderList = new ArrayList<String>();
    for (CheckBox checkbox : matchingCheckboxes) {
      orderList.add(checkbox.getText());
    }
    curOrder = new Order(orderList, Integer.parseInt(tablenotxt.getText()), (float) totalCost);
    notifyObservers(obs);
  }
  
  public Observer obs;
  private Order curOrder;

  @Override
  public void addObservers(Observer obs) {
    this.obs = obs;
    
  }

  @Override
  public void notifyObservers(Observer obs) {
    obs.update(curOrder);
  }
  
  /**
   * temporary method to confirm the order has made it.
   */
  public void confirmRecieved() {
    titlelbl.setText("recieved");
  }
}
