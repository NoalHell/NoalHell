package UI.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MyCenterController extends ViewHelper implements Initializable {
    @FXML
    ImageView myAvatar;

    private Main main;
    public void changePassword(ActionEvent actionEvent) {
    }

    public void listDefault(MouseEvent mouseEvent) {
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                Platform.runLater(()->{
                    myAvatar.setImage(new Image("https://ts1.cn.mm.bing.net/th/id/R-C.29a84eb867bf75b5327e7df3b1a7e32c?rik=iW9zjAJwqTB%2fdA&riu=http%3a%2f%2ftupian.qqw21.com%2farticle%2fUploadPic%2f2019-7%2f201971622263482217.jpeg&ehk=W4G6YV7SJ1LFEFGJ3r%2bsC66stsnts%2bGu%2b7tsCcMPWGA%3d&risl=&pid=ImgRaw&r=0&sres=1&sresct=1"));
                });
            }
        }.start();
    }

    public void back(ActionEvent actionEvent) {
        main.toHome();
    }
}
