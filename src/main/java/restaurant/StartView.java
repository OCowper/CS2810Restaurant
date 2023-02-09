package restaurant;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * View representing the startup screen.
 *
 * @author Mathushan, Manpreet, Irina
 */
public class StartView implements Subject, ViewInterface {

  @FXML
  private Button customerbtn;

  @FXML
  private Button staffbtn;

  @FXML
  private Label titlelbl;


  /**
   * Changes the scene from initial one to food menu view.
   *
   * @param event "customerbtn" button pressed
   * @throws IOException when there is a problem with loading the fxml file
   */
  public void handleCustomerBtn(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("model.fxml"));

    Parent foodMenuparent = loader.load();
    Scene foodMenu = new Scene(foodMenuparent);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    obs.setView(loader.getController());
    window.setScene(foodMenu);
    window.show();

  }

  /**
   * Changes the scene from initial one to food menu view.
   *
   * @param event "staffbtn" button pressed
   * @throws IOException when there is a problem with loading the .fxml file
   */
  public void handleStaffLoginBtn(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("staffLogin.fxml"));
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
}
