package UI.controller;

import Dao.UserDao;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangePasswordController extends ViewHelper implements Initializable {
    private Main main;

    @FXML
    private TextField oldPasswordField;				//旧密码文本框
    @FXML
    private TextField newPasswordField;				//新密码文本框
    @FXML
    private TextField repetNewPasswordField;		//重复密码文本框
    @FXML
    private Label messageLabel;						//显示信息的标签


    /**
     * 关闭这个窗口。
     * @param event
     */
    @FXML
    public void closeAction(ActionEvent event){
        //LocalManager.getApplication().getModalStage().close();
    }

    /**
     * 提交内容，更改密码。
     * @param event
     */
    @FXML
    public void submitAction(ActionEvent event){
        String oldPassword = oldPasswordField.getText().trim();
        String newPassword = newPasswordField.getText().trim();
        String repetNewPassword = repetNewPasswordField.getText().trim();
        //判空
        if(oldPassword.equals("")||newPassword.equals("")||repetNewPasswordField.equals("")){
            animateMessage("密码为空", messageLabel);
            return;
        }
        //判断两次新密码是否一致
        else if(!newPassword.equals(repetNewPassword)){
            animateMessage("密码不一致", messageLabel);
            return;
        }
        else{
            UserDao userDao = new UserDao();
            User user = userDao.findByName(main.getUser().getUsername());
            if(user.getPassword().equals(oldPassword)){
                try{
                    user.setPassword(newPassword);
                    userDao.Insert(user);
                    animateMessage("修改成功",messageLabel);
                } catch (Exception e){
                    e.printStackTrace();
                    animateMessage("修改失败",messageLabel);
                }
            } else{
                animateMessage("旧密码错误", messageLabel);
            }
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }
}
