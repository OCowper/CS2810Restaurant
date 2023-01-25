import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class staffLogin {

    @FXML
    private Button forgotBtn;

    @FXML
    private Label idLbl;

    @FXML
    private TextField idTxt;

    @FXML
    private Button loginBtn;

    @FXML
    private Label passLbl;

    @FXML
    private TextField passwordTxt;

    @FXML
    private Button returnBtn;

    @FXML
    private Label titleLbl;

    public void handleStaffRtnBtn() throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
      Stage window = (Stage) returnBtn.getScene().getWindow();
      window.setScene(new Scene(root, 600, 400));
    }
}
