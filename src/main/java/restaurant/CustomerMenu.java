package restaurant;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * View representing all Menu items for customers to make a selection from.
 *
 * @author Mathushan, Manpreet
 */
public class CustomerMenu implements Subject, ViewInterface {

  @FXML
  private Button aboutUsButton;

  @FXML
  private AnchorPane anchorpane;

  @FXML
  private AnchorPane bgAnchorPane;

  @FXML
  private ImageView bgImage;

  @FXML
  private Button cartButton;

  @FXML
  private Label confirmLabel;

  @FXML
  private Button helpBtn;

  @FXML
  private ChoiceBox<String> filterBox;

  @FXML
  private Label filterLbl;

  @FXML
  private Button menuButton;

  @FXML
  private ImageView oaxacaImageView;

  @FXML
  private ImageView productimages;

  @FXML
  private ScrollPane scrollpane;

  @FXML
  private TextField searchbar;

  @FXML
  private Button submitbtn;

  @FXML
  private TextField tablenotxt;

  @FXML
  private TextField totaltxt;

  @FXML
  private Button TrackOrderButton;

  @FXML
  private TextArea userselections;

  @FXML
  private VBox vbox;

  @FXML
  private VBox virtualBox;

  @FXML
  private VBox descriptionBox;

  @FXML
  private TextArea itemDescription;

  @FXML
  private TextField itemName;

  @FXML
  private Button ConfirmQuantityButton;

  @FXML
  private Label ItemLabel;

  @FXML
  private TextField ItemTextField;

  @FXML
  private Label NewQuantityChangeLabel;

  @FXML
  private TextField NewQuantityTextField;

  private String[] Type = {"Show All", "Starters", "Mains", "Desserts", "Drinks"};
  private HashSet<CheckBox> matchingCheckboxes = new HashSet<>();
  private Map<CheckBox, Double> itemCosts = new HashMap<>();
  private Map<CheckBox, Image> itemImages = new HashMap<>();


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
        CheckBox cb = new CheckBox(item.getPrice() + "");
        Hyperlink hl = new Hyperlink(item.getName()); // item name will be clickable for description
        hl.setOnAction(e -> showDescription(item)); // sets behaviour on click

        cb.setGraphic(hl);
        cb.setContentDisplay(ContentDisplay.LEFT); // name set to be left of everything else
        cb.selectedProperty().addListener(
            (observable, oldValue, newValue) -> handleCheckboxClick(cb, oldValue, newValue));
        cb.setUserData(item); // Store the MenuItem object as user data
        vbox.getChildren().add((cb));
        Image itemImage = new Image(item.getImagePath()); // Load image using the image path
        itemImages.put(cb, itemImage); // Store the association between the CheckBox and Image
      }
    }
    scrollpane.setContent(vbox);
    searchbar.setOnAction(e -> handleSearchbarAction());
  }


  private void showDescription(MenuItem item) {
    itemName.setText(item.getName());
    String description = item.getDescription() + "\n\nIngredients: " + item.getIngredients() + "\n"
        + item.getCalories() + "kCal";
    itemDescription.setText(description);
    descriptionBox.setVisible(true);
  }

  private ArrayList<CheckBox> checkedItems = new ArrayList<>();

  private void handleCheckboxClick(CheckBox checkbox, Boolean o, Boolean n) {
    MenuItem item = (MenuItem) checkbox.getUserData();
    double price = Double.parseDouble(checkbox.getText());
    if (n) {
      userselections.appendText(item.getName() + ",");
      totalCost += price;
      totaltxt.setText("£" + Double.toString(totalCost));

      checkedItems.add(checkbox);
      updateImageView(checkbox);

    } else {
      userselections.setText(userselections.getText().replace(item.getName() + ",", ""));
      totalCost -= price;
      totaltxt.setText("£" + Double.toString(totalCost));

      checkedItems.remove(checkbox);
      updateImageView(null);
    }
  }

  private void updateImageView(CheckBox currentCheckbox) {
    if (checkedItems.size() > 0) {
      CheckBox lastChecked =
          currentCheckbox == null ? checkedItems.get(checkedItems.size() - 1) : currentCheckbox;
      MenuItem item = (MenuItem) lastChecked.getUserData();
      String imagePath = item.getImagePath();
      Image image = new Image(getClass().getResource(imagePath).toExternalForm());

      productimages.setImage(image);
      productimages.setFitWidth(139);
      productimages.setFitHeight(110);
      productimages.setPreserveRatio(false);
    } else {
      productimages.setImage(null);
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
    private String id;
    private String itemName;
    private Double price;
    private String description;
    private String ingredients;
    private int calories;
    private String category;
    private boolean inStock;
    private String imagePath;

    public MenuItem(String nameId, String name, Double pr, String descr, String ingr, int c,
        String cat, boolean t, String img) {
      this.id = nameId;
      this.itemName = name;
      this.price = pr;
      this.category = cat;
      this.description = descr;
      this.ingredients = ingr;
      this.calories = c;
      this.inStock = t;
      this.imagePath = img;
    }

    public String getId() {
      return id;
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

    public Double getPrice() {
      return price;
    }

    public String getIngredients() {
      return ingredients;
    }

    public int getCalories() {
      return calories;
    }

    public boolean getAvailability() {
      return inStock;
    }

    public String getImagePath() {
      return imagePath;
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
    String curType = filterBox.getValue();
    ResultSet rs = null;
    if (curType == "Show All") {
      rs = obs.getMenuItems();
    } else {
      rs = obs.getMenuItems(curType);
    }
    try {
      while (rs.next()) {
        String key = rs.getString("item_category").trim().toLowerCase();
        MenuItem toAdd = new MenuItem(rs.getString("item_id"), rs.getString("item_name"),
            rs.getDouble("price"), rs.getString("item_description"), rs.getString("ingredients"),
            rs.getInt("calories"), rs.getString("item_category"), rs.getBoolean("available"),
            rs.getString("image_path"));
        // adds a new value to the list of items. Handles keys that are not present
        map.computeIfAbsent(key, k -> new ArrayList<MenuItem>()).add(toAdd);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return map;
  }


  /**
   * Changes the scene from initial one to food menu view.
   *
   * @param event "customerbtn" button pressed
   * @throws IOException when there is a problem with loading the fxml file
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
   * Handling for if Menu Return switcher is pressed.
   *
   * @param event representing the button press
   * @throws IOException if an IO error occurs.
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
    obs.orderStartup();
    window.setScene(checkout);
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

  @FXML
  void isPressed(ActionEvent event) {
    curOrder = new Order(userselections.getText(), Integer.parseInt(tablenotxt.getText()),
        (float) totalCost);
    notifyObservers(obs);
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource("checkoutPage.fxml"));
    Parent cartParent = null;

    try {
      cartParent = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    Scene checkout = new Scene(cartParent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());
    obs.orderStartup();
    window.setScene(checkout);
    window.show();
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
    Image background = new Image("/images/plata-o-plomo-1.jpeg");
    Image title = new Image("/images/newoaxacaLogo.png");
    oaxacaImageView.setImage(title);
    bgImage.setImage(background);
    filterBox.setItems(FXCollections.observableArrayList(Type));
    filterBox.setValue("Show All");
    filterBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observableValue, String string,
          String string2) {
        initializeAfter();
      }
    });
    initializeAfter();
  }

}
