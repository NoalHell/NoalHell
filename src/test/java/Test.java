import Dao.*;
import entity.Address;
import entity.Goods;
import entity.ShopCar;
import entity.User;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args){

//        User user = new User();
//        user.setUsername("zyq");
//        user.setPassword("123");
//        ArrayList<Address> addresses = new ArrayList<>();
//        Address address = new Address("zyq", "10086", "kunming");
//        addresses.add(address);
//        user.setAddresses(addresses);
//        UserDao userDao = new UserDao();
//        userDao.Insert(user);

//        UserDao userDao = new UserDao();
//        User user = userDao.findByName("123");
//        System.out.println(user);
//
//        ArrayList<User> users = userDao.findAll();
//        users.size();

        Goods goods = new Goods();
        goods.setName("test");
        goods.setPrice(100.0);
        goods.setProduceLocation("昆明");

        GoodsDao goodsDao = new GoodsDao();
        goodsDao.Insert(goods);

    }
}
