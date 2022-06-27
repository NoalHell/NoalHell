package model;

import entity.ShopCar;
import entity.ShopCarItem;

public class ShowShopCarItemData {
    private int id;
    private String name;
    private int num;
    private double price;

    public ShowShopCarItemData(int id, String name, int num, double price){
        this.id = id;
        this.name= name;
        this.num = num;
        this.price = price;
    }

    public ShowShopCarItemData(ShopCarItem shopCarItem){
        this.setShopCar(shopCarItem);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setShopCar(ShopCarItem shopCarItem){
        this.num = shopCarItem.getNum();
        this.id = shopCarItem.getId();
        this.name = shopCarItem.getGoods().getName();
        this.price = shopCarItem.getGoods().getPrice();
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }
}
