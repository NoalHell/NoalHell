import Dao.MyDao;
import entity.User;

public class Test {
    public static void main(String[] args){
        User user = new User();
        user.setUsername("test");
        user.setPassword("123");

        MyDao.insert(user);
    }
}
