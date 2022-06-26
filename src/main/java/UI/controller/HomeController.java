package UI.controller;

import Dao.GoodsDao;
import Dao.OrderDao;
import Dao.ShopCarDao;
import entity.Goods;
import entity.Order;
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
import javafx.stage.Stage;

import javax.persistence.NoResultException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class HomeController extends ViewHelper implements Initializable {

    Main main;
    @FXML
    TextField sumPriceField; // 预计花费
    @FXML
    TextField haveCutField; // 优惠框
    @FXML
    TextField finalCostField; // 最终花费

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
        shopCarPriceCol.setCellValueFactory(new PropertyValueFactory<ShopCar,Double>("price"));
    }

    // buy
    public void buyGoodsAction(ActionEvent actionEvent) {
        Order order = new Order();
        order.setPayStatue(0);
        order.setPrice(selectGoodsItem.getPrice());
        order.setUserId(main.getUser().getId());
        OrderDao orderDao = new OrderDao();
        orderDao.insert(order);
    }
    // order
    public void addGoodsOrderAction(ActionEvent actionEvent) {
        if(selectGoodsItem==null){
            toast("还没有选择添加的商品", 1000);
            return;
        }
        ShopCarDao shopCarDao = new ShopCarDao();
        ShopCar had;
        try {
            had = shopCarDao.findByGoodsId(
                    main.getUser().getId(),
                    selectGoodsItem.getId()
            );
        }catch (NoResultException e){
            had = null;
        }
        try {
            if(had == null) {
                ShopCar save = new ShopCar();
                save.setUserId(main.getUser().getId());
                save.setGoodsId(selectGoodsItem.getId());
                shopCarDao.Insert(save);
                notifyCarListChange(save);
            } else {
                int num = had.getNum() +1;
                had.setNum(num);
                shopCarDao.update(had);
                notifyCarListChange(had);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //check out
    public void checkOutAction(ActionEvent actionEvent) {
    }
    @FXML
    TextField searchField;
    // 搜索
    public void searchAction(ActionEvent actionEvent) {
        String search = searchField.getText();
        if(search.equals("")){
            toast("请输入需要搜索的商品名称", 1000);
            return;
        }
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
        if(shopCarSource.size()==0){
            toast("还没有添加", 1000);
            return;
        }
        try {
            ShopCarDao shopCarDao = new ShopCarDao();
            for(ShopCar s:shopCarSource){
                shopCarDao.delete(s);
            }
            shopCarSource = FXCollections.observableArrayList();
            Platform.runLater(()->{
                shopCarTable.setItems(shopCarSource);
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getGoodsDataByName(String name){
        GoodsDao goodsDao = new GoodsDao();
        ArrayList<Goods> goods = goodsDao.findByName(name);
        goodsSource = FXCollections.observableArrayList();
        for(Goods g:goods){
            goodsSource.add(g);
        }
        Platform.runLater(()->{
            goodsTable.setItems(goodsSource);
        });
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
        ArrayList<ShopCar> shopCars = shopCarDao.findMyAll(main.getUser().getId());
        shopCarSource = FXCollections.observableArrayList();
        for(ShopCar s:shopCars){
            shopCarSource.add(s);
        }
        Platform.runLater(()->{
            shopCarTable.setItems(shopCarSource);
        });
    }

    private void notifyCarListChange(ShopCar shopCar){
        shopCarSource.removeIf(new Predicate<ShopCar>() {
            @Override
            public boolean test(ShopCar s) {
                if(s.getId()== shopCar.getId()) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        shopCarSource.add(shopCar);
        double sum = 0.0;
        for(ShopCar e: shopCarSource){
            sum+= e.getPrice()*e.getNum();
        }
        sumPriceField.setText(sum+"元");

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

    public void logout(ActionEvent actionEvent) {
        main.toLogin();
    }

    public void changePassword(ActionEvent actionEvent) {
//        Stage secondaryStage=new Stage();
//        Button btn1=new Button();
//        StackPane root1=new StackPane();
//        btn1.setText("欢迎来到第二舞台");
//
//        btn1.setOnAction(new EventHandler<ActionEvent>(){
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("欢迎来到第二舞台");
//            }
//        });
//
//        root1.getChildren().add(btn1);
//        Scene secondaryScene=new Scene(root1,500,250);
//        secondaryStage.setScene(secondaryScene);
//        secondaryStage.setTitle("第二舞台");
//        secondaryStage.show();
    }

    public void toMyCenterAction(ActionEvent actionEvent) {
        main.toMyCenter();
    }
}
