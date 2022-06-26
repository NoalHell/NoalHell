package UI.controller;

import config.StaticResourcesConfig;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class MyCenterController extends ViewHelper implements Initializable {
    @FXML
    ImageView myAvatar;
    @FXML
    AnchorPane mainContainer;

    private Main main;
    public void changePassword(ActionEvent actionEvent) {
        try{
            toChangePassword();
        } catch (Exception e){
            e.printStackTrace();
        }
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

    private void toChangePassword() throws Exception {
        try{
            ChangePasswordController c = (ChangePasswordController)replaceSceneContent("/fxml/ChangePassword.fxml");
            c.setMain(main);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Initializable replaceSceneContent(String fxml) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        try {
            Parent parent = loader.load();
            mainContainer.getChildren().add(parent);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("页面加载异常！");
        } finally {
            in.close();
        }
        return (Initializable) loader.getController();
    }
}
