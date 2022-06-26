package UI.controller.myCenter;

import Dao.OrderDao;
import Dao.OrderDao;
import Dao.ShopCarDao;
import UI.controller.Main;
import UI.controller.ViewHelper;
import entity.Order;
import entity.Order;
import entity.ShopCar;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.NoResultException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class OrderListController extends ViewHelper implements Initializable {

    Main main;

    // Order table
    ObservableList<Order> orderSource =  FXCollections.observableArrayList();
    @FXML
    public TableView<Order> orderTable;

    @FXML
    private TableColumn<Order, Integer> orderIdCol;

    @FXML
    private TableColumn<Order, Integer> orderPayStatueCol;

    @FXML
    private TableColumn<Order, Double> orderPriceCol;

    @FXML
    private TableColumn<Order, String> orderRemarkCol;

    public void setMain(Main main) {
        this.main = main;
        new Thread(){
            @Override
            public void run() {
                super.run();
                try{
                    OrderDao orderDao = new OrderDao();
                    orderSource.addAll(orderDao.findMyAllById(main.getUser().getId()));
                    Platform.runLater(()->{
                        orderTable.setItems(orderSource);
                    });
                } catch (Exception e){
                    e.printStackTrace();
                }


            }
        }.start();

    }

    private Order selectOrderItem;
    private void initOrderTable(){
        /*
         * add a listener
         * 为deal表格添加选中行选中变化监听器，以更新消费金额文本框
         */
        orderTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Order>() {
            @Override
            public void changed(ObservableValue<? extends Order> observable, Order oldValue, Order newValue) {
                selectOrderItem = newValue;
            }
        });
        /*
         *dealTable,initialize some data int it;
         *为表格载入数据
         */
        orderIdCol.setCellValueFactory(new PropertyValueFactory<Order,Integer>("id"));
        orderPayStatueCol.setCellValueFactory(new PropertyValueFactory<Order,Integer>("payStatue"));
        orderRemarkCol.setCellValueFactory(new PropertyValueFactory<Order,String>("remark"));
        orderPriceCol.setCellValueFactory(new PropertyValueFactory<Order,Double>("price"));

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initOrderTable();
    }

}
