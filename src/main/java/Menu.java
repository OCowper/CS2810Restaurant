import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Menu {

    @FXML
    private Button customerbtn;

    @FXML
    private Button staffbtn;

    @FXML
    private Label titlelbl;

    public void handleCustomerBtn() throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("model.fxml"));
      Stage window = (Stage) customerbtn.getScene().getWindow();
      window.setScene(new Scene(root, 600, 400));
    }
}
