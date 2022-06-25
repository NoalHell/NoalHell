package UI.controller;

import Dao.UserDao;
import entity.User;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javax.persistence.NoResultException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController extends ViewHelper implements Initializable {
//    private final static Logger log = Logger.getLogger(Controller.class);
    private Main main;
    @FXML
    ImageView myAvatar;
    @FXML
    private Label messageLabel;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private TextField userNameFeild;
    @FXML
    private PasswordField passwordField;

    @FXML
    public void loginAction(ActionEvent actionEvent) {
        String username = userNameFeild.getText().trim();
        String password = passwordField.getText().trim();
        if(username.equals("")||password.equals("")){
            animateMessage("用户名或密码输入为空", messageLabel);
        } else {
            messageLabel.setText("正在登录中");
            showLoading();
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    String msg="";
                    boolean isLogin = false;
                    try{
                        UserDao userDao = new UserDao();
                        User user = userDao.findByName(username);
                        if(user!=null){
                            if(user.getPassword().equals(password)){
                                msg = "登录成功";
                                isLogin = true;
                                // 全局变量
                                main.setUser(user);
                            } else {
                                msg = "用户名或密码错误";
                            }
                        } else {
                            msg = "该用户还未注册";
                        }
                    } catch (RuntimeException e){
                        msg = "运行时错误";
                    } finally {
                        String finalMsg = msg;
                        boolean finalIsLogin = isLogin;
                        Platform.runLater(()->{
                            messageLabel.setText(finalMsg);
                            hideLoading();
                            if(finalIsLogin) {
                               main.toHome();
                            }
                        });
                    }
                }
            }.start();


        }
    }

    public void registerAction(ActionEvent actionEvent) {
        String username = userNameFeild.getText().trim();
        String password = passwordField.getText().trim();
        if(username.equals("")||password.equals("")){
            showMessage("用户名或密码输入为空");
        } else {
            showLoading();
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try{
                        User user = new User();
                        user.setUsername(username);
                        user.setPassword(password);
                        UserDao userDao = new UserDao();
                        userDao.Insert(user);
                        Platform.runLater(()->{
                            showMessage("注册成功！");
                        });

                    } catch (RuntimeException e){
                        e.printStackTrace();
                        Platform.runLater(()->{
                            messageLabel.setText("注册失败");
                            showMessage(e.toString());

                        });
                    } finally {
                        Platform.runLater(()->{
                            hideLoading();
                        });
                    }
                }
            }.start();

        }

    }

    public void showMessage(String msg){
        toast(msg, 3000);
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.titleProperty().set("login info");
//        alert.headerTextProperty().set(msg);
//        alert.showAndWait();
    }

    public void showLoading(){
        loginButton.setDisable(true);
        registerButton.setDisable(true);
    }

    public void hideLoading(){
        loginButton.setDisable(false);
        registerButton.setDisable(false);
    }

    public void setMain(Main main){
        this.main = main;
    }

    private String nowUsername;
    private boolean isLoad;
    Image image;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userNameFeild.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                nowUsername = newValue;
                isLoad = false;
            }
        });
        userNameFeild.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(nowUsername!=null && !isLoad){
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            try{
                                User user = new UserDao().findByName(nowUsername);
                                image = new Image(
                                        "https://www.keaidian.com/uploads/allimg/190610/10095245_8.jpeg"
                                );
                                Platform.runLater(()->{
                                    myAvatar.setImage(image);
                                });
                                animateMessage("", messageLabel);
                            } catch (NoResultException e){
                                Platform.runLater(()->{
                                    animateMessage("没有找到该用户", messageLabel);
                                });
                            } finally {
                                isLoad = true;
                            }
                        }
                    }.start();

                    System.out.println(nowUsername);
                }

            }
        });

    }
}
