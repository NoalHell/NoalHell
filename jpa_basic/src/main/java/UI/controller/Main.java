package UI.controller;

import config.StaticResourcesConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.URL;

public class Main extends Application {

    Stage stage;
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
        primaryStage.setScene(new Scene(root, StaticResourcesConfig.STAGE_WIDTH, StaticResourcesConfig.STAGE_HEIGHT));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    public void toHome(){

        try{
            HomeController homeController = (HomeController) replaceSceneContent("/fxml/sample.fxml");
            homeController.setMain(this);
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
            GridPane page = (GridPane) loader.load(in);
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
}
