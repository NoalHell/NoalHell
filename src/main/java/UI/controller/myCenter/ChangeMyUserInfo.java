package UI.controller.myCenter;

import UI.controller.Main;
import UI.controller.ViewHelper;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangeMyUserInfo extends ViewHelper implements Initializable {
    Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public Main getMain() {
        return main;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
