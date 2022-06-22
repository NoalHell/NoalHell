package UI.controller;

import Dao.UserDao;
import entity.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
//    private final static Logger log = Logger.getLogger(Controller.class);
    Main main;
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
            messageLabel.setText("用户名或密码输入为空");
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
                        User user = userDao.getByUserName(username);
                        if(user!=null){
                            if(user.getPassword().equals(password)){
                                msg = "登录成功";
                                isLogin = true;
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
                        showMessage("注册成功！");
                    } catch (RuntimeException e){
                        Platform.runLater(()->{
                            messageLabel.setText("注册失败");
                            showMessage(e.toString());
                            hideLoading();
                        });

                    }
                }
            }.start();

        }

    }

    public void showMessage(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.titleProperty().set("login info");
        alert.headerTextProperty().set(msg);
        alert.showAndWait();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
