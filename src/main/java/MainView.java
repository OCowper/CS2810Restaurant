import java.util.HashSet;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainView {

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
  private ScrollPane scrollpane;

  @FXML
  private TextField searchbar;

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
  
  public void initialize() {
    scrollpane.setContent(vbox);
    searchbar.setOnAction(e -> handleSearchbarAction());
  }

  @FXML
  private void handleSearchbarAction() {
      String text = searchbar.getText();
      matchingCheckboxes.clear();
      for (Node node : vbox.getChildren()) {
          if (node instanceof CheckBox) {
              CheckBox checkBox = (CheckBox) node;
              if (checkBox.getText().toLowerCase().contains(text.toLowerCase())) {
                  checkBox.setSelected(true);
                  if (checkBox.isSelected()) {
                      matchingCheckboxes.add(checkBox);
                  }
              } else {
                  checkBox.setSelected(false);
              }
          }
      }
      StringBuilder selectedCheckboxes = new StringBuilder();
      for (CheckBox checkbox : matchingCheckboxes) {
          selectedCheckboxes.append(checkbox.getText()).append(", ");
      }
      userselections.setText(selectedCheckboxes.toString());
  }


}
