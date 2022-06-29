package UI.controller;

import Dao.UserDao;
import entity.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.persistence.PersistenceException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController extends ViewHelper  {
    Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public void init() {

    }

    public Main getMain() {
        return main;
    }

    private Image avatar;
    @FXML
    TextField userNameField;
    @FXML
    TextField passwordField;
    @FXML
    TextField emailField;
    @FXML
    TextField ageField;
    @FXML
    TextField rePasswordField;
    @FXML
    TextField phoneField;


    public void register(ActionEvent actionEvent) {
        String username = userNameField.getText().trim();
        String password = passwordField.getText().trim();
        String rePassword = rePasswordField.getText().trim();
        String email = emailField.getText().trim();
        Integer age = null;
        try {
            age =Integer.parseInt(ageField.getText().trim());
        } catch (NumberFormatException e){

        }
        String phone = phoneField.getText().trim();
        if(username.equals("")||password.equals("")){
            toast("用户名或密码输入为空", 1000);
        } else if(!password.equals(rePassword)){
            toast("两次密码不一致", 1000);
        } else {
            showLoading(main.getStage(), "加载中");
            Integer finalAge = age;
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    String msg = "unknown";
                    try {
                        User user = new User(username, password,email, phone, finalAge);
                        UserDao userDao = new UserDao();
                        userDao.Insert(user);
                        msg = "注册成功！";
                    } catch (PersistenceException e) {
                        e.printStackTrace();
                        msg = "注册失败,已有该用户";
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                        msg = "运行失败";
                    } finally {
                        String finalMsg = msg;
                        Platform.runLater(() -> {
                            toast(finalMsg, 2000);
                            hideLoading();
                        });
                    }
                }
            }.start();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void back(ActionEvent actionEvent) {
        main.toLogin();
    }

    public void chooseAvatar(ActionEvent actionEvent) {

    }
}
