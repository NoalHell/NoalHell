package UI.controller;

import Dao.GoodsDao;
import Dao.OrderDao;
import Dao.ShopCarDao;
import entity.*;
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
import model.ShowShopCarItemData;

import javax.persistence.NoResultException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    ObservableList<ShowShopCarItemData> showShopCarItemSource=  FXCollections.observableArrayList();
    @FXML
    public TableView<ShowShopCarItemData> shopCarTable;

    @FXML
    private TableColumn<ShowShopCarItemData, Integer> shopCarIdCol;

    @FXML
    private TableColumn<ShowShopCarItemData, Integer> shopCarNumCol;

    @FXML
    private TableColumn<ShowShopCarItemData, String> shopCarNameCol;

    @FXML
    private TableColumn<ShowShopCarItemData, Double> shopCarPriceCol;

    public void setMain(Main main) {
        this.main = main;
        showLoading(main.getStage(), "加载数据中");
        new Thread(){
            @Override
            public void run() {
                super.run();
                try{
                    getGoodsData();
                    getCarData();
                    Platform.runLater(()->{
                        toast("加载成功!", 2000);
                    });
                }catch (Exception e){
                    Platform.runLater(()->{
                        toast("加载失败!", 2000);
                    });
                }finally {
                    Platform.runLater(()->{
                        hideLoading();
                    });
                }

            }
        }.start();
    }

    private Goods selectGoodsItem;
    // 初始化商品表格
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

    // 初始化购物车
    private void initCarTable(){
        /*
         * add a listener
         * 为deal表格添加选中行选中变化监听器，以更新消费金额文本框
         */
        shopCarTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ShowShopCarItemData>() {
            @Override
            public void changed(ObservableValue<? extends ShowShopCarItemData> observable, ShowShopCarItemData oldValue, ShowShopCarItemData newValue) {

            }
        });
        /*
         *dealTable,initialize some data int it;
         *为表格载入数据
         */
        shopCarIdCol.setCellValueFactory(new PropertyValueFactory<ShowShopCarItemData,Integer>("id"));
        shopCarNumCol.setCellValueFactory(new PropertyValueFactory<ShowShopCarItemData,Integer>("num"));
        shopCarNameCol.setCellValueFactory(new PropertyValueFactory<ShowShopCarItemData,String>("name"));
        shopCarPriceCol.setCellValueFactory(new PropertyValueFactory<ShowShopCarItemData,Double>("price"));
    }

    // 购买商品
    public void buyGoodsAction(ActionEvent actionEvent) {
        if(selectGoodsItem==null){
            toast("还没有选择需要购买的商品!", 1000);
            return ;
        }
        showLoading(main.getStage(), "购买中");
        try{
            Order order = new Order();
            ShopCar shopCar =new ShopCar();
            shopCar.setStatue(1);
            shopCar.setUserId(main.getUser().getId());
            // 添加对象之前需要保存
            new ShopCarDao().insert(shopCar);
            order.setShopCar(shopCar);
            order.setPayStatue(0);
            order.setPrice(selectGoodsItem.getPrice());
            order.setUserId(main.getUser().getId());
            OrderDao orderDao = new OrderDao();
            orderDao.insert(order);
            Platform.runLater(()->{
                toast("购买成功!", 1000);
            });
        } catch (Exception e){
            e.printStackTrace();
            Platform.runLater(()->{
                toast("购买失败!原因:"+e.toString(), 1000);
            });
        } finally {
            Platform.runLater(()->{
                hideLoading();
            });
        }

    }
    // 加入购物车
    public void addGoodsOrderAction(ActionEvent actionEvent) {
        if(selectGoodsItem==null){
            toast("还没有选择添加的商品", 1000);
            return;
        }
        ShopCarDao shopCarDao = new ShopCarDao();
        ShopCar had;
        try {
            had = shopCarDao.findOneByUserId(
                    main.getUser().getId()
            );
        }catch (NoResultException e){
            had = null;
        }
        try {
            if(had == null) {
                ShopCar save = new ShopCar();
                save.setUserId(main.getUser().getId());
//                List<Goods> goodsList = new ArrayList<>();
                ShopCarItem shopCarItem = new ShopCarItem();
                shopCarItem.setGoods(selectGoodsItem);
                shopCarItem.setNum(1);
//                goodsList.add(selectGoodsItem);

                save.addShopCarItem(shopCarItem);
                shopCarDao.insert(save);
                notifyCarListChange(shopCarItem);
            } else {
                boolean isHave = false;
                for(int i=0;i< had.getGoodsItemList().size();i++){
                    ShopCarItem item = had.getGoodsItemList().get(i);
                    // 购物车中有
                    if(item.getGoods().getId() == selectGoodsItem.getId()){
                        // 添加一个
                        int num = item.getNum()+1;
                        item.setNum(num);
                        
                        // 更新持久层
                        shopCarDao.update(had);
                        isHave = true;
                        notifyCarListChange(item);
                        break;    
                    }
                }
                if(!isHave){
                    ShopCarItem shopCarItem = new ShopCarItem();
                    shopCarItem.setGoods(selectGoodsItem);
                    shopCarItem.setNum(1);
                    had.addShopCarItem(shopCarItem);
                    shopCarDao.update(had);
                    notifyCarListChange(shopCarItem);
                    
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    // 购买全部
    public void checkOutAction(ActionEvent actionEvent) {
        if(showShopCarItemSource.size()==0){
            toast("购物车中还没有商品",1000);
            return;
        }
        showLoading(main.getStage(), "购买中!");
        try {
            ShopCarDao shopCarDao = new ShopCarDao();

            ShopCar shopCar = new ShopCarDao().findOneByUserId(main.getUser().getId());
            shopCar.setStatue(1);
            shopCarDao.update(shopCar);

            Order order = new Order();
            order.setShopCar(shopCar);
            order.setPayStatue(0);
            order.setUserId(main.getUser().getId());
            order.setPrice(finalCost);

            new OrderDao().insert(order);

            showShopCarItemSource = FXCollections.observableArrayList();
            Platform.runLater(()->{
                toast("购买成功!", 1000);
                shopCarTable.setItems(showShopCarItemSource);
            });
        } catch (Exception e){
            Platform.runLater(()->{
                toast("购买失败!", 1000);
            });
            e.printStackTrace();
        } finally {
            hideLoading();
            Platform.runLater(()->{
                notifyResultNumberChange();
            });
        }
    }

    // 清空购物车
    public void clearAllOrderAction(ActionEvent actionEvent) {
        if(showShopCarItemSource.size()==0){
            toast("还没有添加", 1000);
            return;
        }
        showLoading(main.getStage(), "清空购物车中");
        try {
            ShopCarDao shopCarDao = new ShopCarDao();

            ShopCar shopCar = shopCarDao.findOneByUserId(main.getUser().getId());
            shopCarDao.delete(shopCar);

            showShopCarItemSource = FXCollections.observableArrayList();
            Platform.runLater(()->{
                toast("清空成功!", 1000);
                shopCarTable.setItems(showShopCarItemSource);
            });
        } catch (Exception e){
            Platform.runLater(()->{
                toast("清空失败!", 1000);
            });
            e.printStackTrace();
        } finally {
            hideLoading();
        }
    }

    // 获取商品数据
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

    // 获取购物车数据
    public void getCarData(){
        try {
            ShopCarDao shopCarDao = new ShopCarDao();
            ShopCar shopCar = shopCarDao.findOneByUserId(main.getUser().getId());
            showShopCarItemSource = FXCollections.observableArrayList();
            for(ShopCarItem s:shopCar.getGoodsItemList()){
                ShowShopCarItemData show = new ShowShopCarItemData(s);
                showShopCarItemSource.add(show);
            }
            Platform.runLater(()->{
                shopCarTable.setItems(showShopCarItemSource);
                notifyResultNumberChange();
            });
        } catch (NoResultException e){

        } catch (Exception e){
            e.printStackTrace();
        } finally {

        }
    }

    // 通知购物车列表刷新
    private void notifyCarListChange(ShopCarItem item){
        showShopCarItemSource.removeIf(new Predicate<ShowShopCarItemData>() {
            @Override
            public boolean test(ShowShopCarItemData showShopCarItemData) {
                if(showShopCarItemData.getId()== item.getId()) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        ShowShopCarItemData show = new ShowShopCarItemData(item);
        showShopCarItemSource.add(show);
        shopCarTable.setItems(showShopCarItemSource);
        notifyResultNumberChange();
    }

    private Double finalCost;
    // 通知金额刷新
    private void notifyResultNumberChange(){
        double sum = 0.0;
        for(ShowShopCarItemData e: showShopCarItemSource){
            sum+= e.getPrice()*e.getNum();
        }
        finalCost = sum;
        sumPriceField.setText(sum+"元");
        finalCostField.setText(sum+"元");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initGoodsTable();
        initCarTable();
    }

    // 退出登录
    public void logout(ActionEvent actionEvent) {
        main.toLogin();
    }
    // 跳转到我的中心事件
    public void toMyCenterAction(ActionEvent actionEvent) {
        main.toMyCenter();
    }
    @FXML
    TextField searchField; // 搜索框
    // 搜索商品
    public void searchGoodsAction(ActionEvent actionEvent) {
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
}
