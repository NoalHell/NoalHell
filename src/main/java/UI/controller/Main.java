package UI.controller;

import config.StaticResourcesConfig;
import entity.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.URL;

public class Main extends Application {
    private User user;
    private Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        URL url = getClass().getResource("/fxml/Login.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load(url.openStream());

        LoginController loginController = fxmlLoader.getController();
        loginController.setMain(this);
        primaryStage.setTitle("login");

        Scene scene = new Scene(root, StaticResourcesConfig.STAGE_WIDTH, StaticResourcesConfig.STAGE_HEIGHT);

        scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    public void toHome(){
        try{
            HomeController indexController = (HomeController) replaceSceneContent("/fxml/home.fxml");
            this.getStage().setTitle("首页");
            indexController.setMain(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toLogin(){
        try{
            LoginController loginController = (LoginController) replaceSceneContent("/fxml/Login.fxml");
            this.getStage().setTitle("登录");
            loginController.setMain(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toRegister(){
        try {
            RegisterController c = (RegisterController) replaceSceneContent("/fxml/Register.fxml");
            this.getStage().setTitle("注册");
            c.setMain(this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void toMyCenter(){
        try{
            MyCenterController myCenterController = (MyCenterController) replaceSceneContent("/fxml/MyCenter.fxml");
            this.getStage().setTitle("个人中心");
            myCenterController.setMain(this);
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
            AnchorPane page = (AnchorPane) loader.load(in);
            Scene scene = new Scene(page, StaticResourcesConfig.STAGE_WIDTH, StaticResourcesConfig.STAGE_HEIGHT);
            stage.setScene(scene);
            stage.sizeToScene();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("页面加载异常！");
        } finally {
            in.close();
        }
        return (Initializable) loader.getController();
    }

    public Stage getStage() {
        return stage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
