package restaurant;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
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
  private Label mains;

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
  private Label starters;

  @FXML
  private Button submitbtn;

  @FXML
  private Label tablelbl;

  @FXML
  private TextField tablenotxt;

  @FXML
  private Label titlelbl;

  @FXML
  private Label totallbl;

  @FXML
  private TextField totaltxt;


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
    Map<String, List<MenuItem>> itemsMap = queryItemsFromDb(RestModel.getConnection());
    // populating the menu with item categories and items
    for (String key : itemsMap.keySet()) {
      vbox.getChildren().add(new Label(key));
      for (MenuItem item : itemsMap.get(key)) {
        vbox.getChildren().add(new CheckBox(item.toString()));
      }
    }

    scrollpane.setContent(vbox);
    searchbar.setOnAction(e -> handleSearchbarAction());
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
   * An inner class representing a single item form the menu to be shown to the customer.
   *
   */
  private class MenuItem {
    private String itemName;
    private String price;
    private String category;
    private String description;

    public MenuItem(String name, String pr, String cat, String descr) {
      this.itemName = name;
      this.price = pr;
      this.category = cat;
      this.description = descr;
    }

    public String getCategory() {
      return category;
    }

    @Override
    public String toString() {
      return itemName + " " + price;
    }
  }
  
  /**
   * Queries the database for all available items on the menu, transforms the result into a list of
   * MenuItem classes and returns it. !!!!!!!!!!!!!!!!!!!The transformation code in the try block is
   * a placeholder and needs to be changed according to the database structure!!!!!!!!!!!!!!!!
   *
   * @param connection database conneciton
   * @return a list of menu items represented by a MenuItem class
   */
  private Map<String, List<MenuItem>> queryItemsFromDb(Connection connection) {
    Map<String, List<MenuItem>> map = new HashMap<String, List<MenuItem>>();
    String query = "SELECT ALL FROM Menu";
    try {
      ResultSet rs = Operations.executeQuery(connection, query);
      // ResultSetMetaData rsmd = rs.getMetaData();
      // int columnsNumber = rsmd.getColumnCount();
      while (rs.next()) {
        String key = rs.getString("category").trim().toLowerCase();
        MenuItem toAdd = new MenuItem(rs.getString("cat"), rs.getString("itemName"),
            rs.getString("price"), rs.getString("description"));
        // adds a new value to the list of items. Handles keys that are not present
        map.computeIfAbsent(key, k -> new ArrayList<MenuItem>()).add(toAdd);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;
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
    curOrder = new Order(userselections.getText(), Integer.parseInt(tablenotxt.getText()),
        (float) totalCost);
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
