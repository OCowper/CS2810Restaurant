package restaurant;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Defines the main view of the program.
 *
 * @author ZLAC183, Mathushan Santhan, Manpreet Kaur
 */
public class FoodMenuView implements Subject, ViewInterface {

  @FXML
  private Label confirmLabel;
  
  @FXML
  private AnchorPane anchorpane;

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
  private ImageView titlelbl;

  @FXML
  private Label totallbl;

  @FXML
  private TextField totaltxt;


  @FXML
  private TextField userselections;

  @FXML
  private VBox vbox;

  @FXML
  private VBox descriptionBox;

  @FXML
  private TextArea itemDescription;

  @FXML
  private TextField itemName;


  private HashSet<CheckBox> matchingCheckboxes = new HashSet<>();
  private Map<CheckBox, Double> itemCosts = new HashMap<>();
  private double totalCost = 0;

  /**
   * Puts the item from database on to the menu view.
   */
  public void initializeAfter() {
    Map<String, List<MenuItem>> itemsMap = queryItemsFromDb();
    // populating the menu with item categories and items
    for (String key : itemsMap.keySet()) {
      vbox.getChildren().add(new Label(key));
      for (MenuItem item : itemsMap.get(key)) {
        CheckBox cb = new CheckBox(item.getPrice());
        Hyperlink hl = new Hyperlink(item.getName()); // item name will be clickable for description
        hl.setOnAction(e -> showDescription(item)); // sets behaviour on click

        cb.setGraphic(hl);
        cb.setContentDisplay(ContentDisplay.LEFT); // name set to be left of everything else
        cb.selectedProperty().addListener(
            (observable, oldValue, newValue) -> handleCheckboxClick(cb, oldValue, newValue));
        vbox.getChildren().add((cb));
      }
    }
    scrollpane.setContent(vbox);
    searchbar.setOnAction(e -> handleSearchbarAction());
  }

  private void showDescription(MenuItem item) {
    itemName.setText(item.getName());
    itemDescription.setText(item.getDescription());
    descriptionBox.setVisible(true);
  }

  private void handleCheckboxClick(CheckBox checkbox, Boolean o, Boolean n) {
    String item = ((Hyperlink) checkbox.getGraphic()).getText();
    double price = Double.parseDouble(checkbox.getText());
    if (n) {
      userselections.appendText(item + ",");
      totalCost += price;
      totaltxt.setText("£" + Double.toString(totalCost));
    } else {
      userselections.setText(userselections.getText().replace(item + ",", ""));
      totalCost -= price;
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

    public String getDescription() {
      return description;
    }

    public String getName() {
      return itemName;
    }

    public String getPrice() {
      return price;
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
   * @return a list of menu items represented by a MenuItem class
   */
  private Map<String, List<MenuItem>> queryItemsFromDb() {
    Map<String, List<MenuItem>> map = new HashMap<String, List<MenuItem>>();
    ResultSet rs = obs.getMenuItems();
    // ResultSetMetaData rsmd = rs.getMetaData();
    // int columnsNumber = rsmd.getColumnCount();
    try {
      while (rs.next()) {
        String key = rs.getString("item_type").trim().toLowerCase();
        MenuItem toAdd = new MenuItem(rs.getString("item_name"), rs.getString("item_num"),
            rs.getString("item_type"), rs.getString("item_description"));
        // adds a new value to the list of items. Handles keys that are not present
        map.computeIfAbsent(key, k -> new ArrayList<MenuItem>()).add(toAdd);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return map;
  }

  /**
   * Handles when the return button is pressed.
   *
   * @param event the button press event
   * @throws IOException if an IO error occurs
   */
  public void handleCustomerRtnBtn(ActionEvent event) throws IOException {
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("newLandingPage.fxml"));
    Parent startViewParent = loader.load();
    Scene startView = new Scene(startViewParent);
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());
    window.setScene(startView);
    window.show();

  }

  @FXML
  void isPressed(ActionEvent event) {
    curOrder = new Order(userselections.getText(), Integer.parseInt(tablenotxt.getText()),
        (float) totalCost);
    notifyObservers(obs);
    confirmLabel.setText("confirmed!");
  }

  public Observer obs;
  private Order curOrder;

  @Override
  public void addObservers(Observer obs) {
    this.obs = obs;

  }


  /**
   * Closes(hides) the item description box when the user presses close button.
   *
   * @param event button click
   */
  @FXML
  void closeDescription(ActionEvent event) {
    descriptionBox.setVisible(false);
  }

  @Override
  public void notifyObservers(Observer obs) {
    obs.update(curOrder);
  }

  @Override
  public void acceptBoolean(Boolean bool) {}

  @Override
  public void startup() {

    initializeAfter();
  }
}
