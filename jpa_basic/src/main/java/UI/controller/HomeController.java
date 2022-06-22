package UI.controller;

import Dao.UserDao;
import entity.User;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.text.TabableView;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

public class HomeController implements Initializable {
    Main main;
    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    public TableView<User> userTableView;

    @FXML
    private TableColumn<User, Integer> userIdCol;

    @FXML
    private TableColumn<User, String> userNameCol;

    @FXML
    private TableColumn<User, String> userEmailCol;

    @FXML
    private TableColumn<User, Boolean> userSexCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        initTable();
    }

    public void initTable(){
        /*
         * add a listener
         * 为deal表格添加选中行选中变化监听器，以更新消费金额文本框
         */
        userTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
//                if(newValue!=null){
//                    sumPriceField.setText(newDealData.getSumPrice()+"");
//                    Set<DealDetail> dealDetails = newDealData.getDeal().getDetails();
//                    Iterator<DealDetail> it = dealDetails.iterator();
//                    dealDetailDataSource.clear();
//                    while(it.hasNext()){
//                        dealDetailDataSource.add(new DealDetailDataModel(it.next()));
//                    }
//
//                }
//                else{
//                    sumPriceField.setText("");
//                }
            }
        });
        /*
         *dealTable,initialize some data int it;
         *为表格载入数据
         */
        userIdCol.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        userEmailCol.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        userSexCol.setCellValueFactory(new PropertyValueFactory<User,Boolean>("sex"));

//        userTableView.setItems(userSource);

    }
    ObservableList<User> userSource=  FXCollections.observableArrayList();

    private void getUserDate(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                UserDao userDao = new UserDao();
                for(User user: userDao.getList()){
                    userSource.add(user);
                    System.out.print(user);
                }
                Platform.runLater(()->{
                    userTableView.setItems(userSource);
                });
            }
        }.start();
    }

    public void getData(ActionEvent actionEvent) {
        getUserDate();
    }


}
