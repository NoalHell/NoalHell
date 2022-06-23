package UI.controller;

import Dao.GoodsDao;
import Dao.ShopCarDao;
import entity.Goods;
import entity.ShopCar;
import entity.User;
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

public class HomeController implements Initializable {

    Main main;
    @FXML
    TextField sumPriceField; // 总价框
    @FXML
    TextField haveCutField; // 优惠框


    // goods table
    ObservableList<Goods> goodsSource=  FXCollections.observableArrayList();
    @FXML
    public TableView<Goods> goodsTable;

    @FXML
    private TableColumn<Goods, Integer> goodsIdCol;

    @FXML
    private TableColumn<Goods, Integer> sellerIdCol;

    @FXML
    private TableColumn<Goods, String> goodsNameCol;

    @FXML
    private TableColumn<Goods, Double> goodsPriceCol;

    // shopCar table

    ObservableList<ShopCar> shopCarSource=  FXCollections.observableArrayList();
    @FXML
    public TableView<ShopCar> shopCarTable;

    @FXML
    private TableColumn<ShopCar, Integer> shopCarIdCol;

    @FXML
    private TableColumn<ShopCar, Integer> shopCarNumCol;

    @FXML
    private TableColumn<ShopCar, String> shopCarNameCol;

    @FXML
    private TableColumn<ShopCar, Double> shopCarPriceCol;

    public  void setMain(Main main) {
        this.main = main;
    }

    private Goods selectGoodsItem;
    private void initGoodsTable(){
        /*
         * add a listener
         * 为deal表格添加选中行选中变化监听器，以更新消费金额文本框
         */
        goodsTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Goods>() {
            @Override
            public void changed(ObservableValue<? extends Goods> observable, Goods oldValue, Goods newValue) {
                selectGoodsItem = newValue;
            }
        });
        /*
         *dealTable,initialize some data int it;
         *为表格载入数据
         */
        goodsIdCol.setCellValueFactory(new PropertyValueFactory<Goods,Integer>("id"));
        sellerIdCol.setCellValueFactory(new PropertyValueFactory<Goods,Integer>("sellId"));
        goodsNameCol.setCellValueFactory(new PropertyValueFactory<Goods,String>("name"));
        goodsPriceCol.setCellValueFactory(new PropertyValueFactory<Goods,Double>("price"));

//        userTableView.setItems(userSource);
    }

    private void initCarTable(){
        /*
         * add a listener
         * 为deal表格添加选中行选中变化监听器，以更新消费金额文本框
         */
        shopCarTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ShopCar>() {
            @Override
            public void changed(ObservableValue<? extends ShopCar> observable, ShopCar oldValue, ShopCar newValue) {

            }
        });
        /*
         *dealTable,initialize some data int it;
         *为表格载入数据
         */
        shopCarIdCol.setCellValueFactory(new PropertyValueFactory<ShopCar,Integer>("id"));
        shopCarNumCol.setCellValueFactory(new PropertyValueFactory<ShopCar,Integer>("num"));
        shopCarNameCol.setCellValueFactory(new PropertyValueFactory<ShopCar,String>("name"));
//        shopCarPriceCol.setCellValueFactory(new PropertyValueFactory<ShopCar,Double>("price"));
    }

    // buy
    public void buyGoodsAction(ActionEvent actionEvent) {
    }
    // order
    public void addGoodsOrderAction(ActionEvent actionEvent) {
        if(selectGoodsItem==null){
            System.out.println("还没有选择添加的商品");
            return;
        }
        ShopCarDao shopCarDao = new ShopCarDao();
        ShopCar had;
        try {
            had = shopCarDao.findByGoodsId(selectGoodsItem.getId());
        }catch (NoResultException e){
            had = null;
        }
        try {
            if(had == null) {
                ShopCar save = new ShopCar();
                save.setGoodsId(selectGoodsItem.getId());
                save.setNum(1);
                shopCarDao.Insert(save);
            } else {
                int num = had.getNum() +1;
                had.setNum(num);
                shopCarDao.update(had);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //check out
    public void checkOutAction(ActionEvent actionEvent) {
    }
    // 搜索
    public void searchAction(ActionEvent actionEvent) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                getGoodsData();
            }
        }.start();
    }
    //clear
    public void clearAction(ActionEvent actionEvent) {
    }

    // 清空购物车
    public void clearAllOrderAction(ActionEvent actionEvent) {
    }

    public void getGoodsData(){
        GoodsDao goodsDao = new GoodsDao();
        ArrayList<Goods> goods = goodsDao.findAll();
        goodsSource = FXCollections.observableArrayList();
        for(Goods g:goods){
            goodsSource.add(g);
        }
        Platform.runLater(()->{
            goodsTable.setItems(goodsSource);
        });
    }

    public void getCarData(){
        ShopCarDao shopCarDao = new ShopCarDao();
        ArrayList<ShopCar> shopCars = shopCarDao.findAll();
        shopCarSource = FXCollections.observableArrayList();
        for(ShopCar s:shopCars){
            shopCarSource.add(s);
        }
        Platform.runLater(()->{
            shopCarTable.setItems(shopCarSource);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGoodsTable();
        initCarTable();
        new Thread(){
            @Override
            public void run() {
                super.run();
                getGoodsData();
                getCarData();
            }
        }.start();
    }
}
