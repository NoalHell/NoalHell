import Dao.MyDao;
import Dao.ShopCarDao;
import Dao.UserDao;
import entity.ShopCar;
import entity.User;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args){
//        User user = new User();
//        user.setUsername("test");
//        user.setPassword("123");
//        MyDao.insert(user);

        UserDao userDao = new UserDao();
        User user = userDao.findByName("123");
        System.out.println(user);

        ArrayList<User> users = userDao.findAll();
        users.size();
        

    }
}
