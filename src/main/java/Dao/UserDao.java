package Dao;

import Utils.JpaUtil;
import com.sun.rowset.internal.Row;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserDao{
    EntityManager entityManager;
    public UserDao(){
        this.entityManager = JpaUtil.getEntityManager();
    }

    public void Insert(User user) {
        entityManager.persist(user);
    }

    public User update(User user) {
        //查询出来需要修改的数据
        User e = entityManager.find(User.class, user.getId());
        //修改数据
        User merge = entityManager.merge(e);
        System.out.println("数据修改成功后数据" + merge);
        return merge;
    }

    public User getByUserName(String username){
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //查询id=5的顾客
        String sql ="select * from myUser where username = ?";
        Query query = entityManager.createNativeQuery("select * from myUser where username = "+"\'"+username+"\'");
        List rows = query.getResultList();
        User user = null;
        if(rows.size()!=0){
            Object row = rows.get(0);
            Object[] cells = (Object[]) row;
            user =new User();
            user.setId((Integer) cells[0]);
            user.setBirthday((Date)  cells[1]);
            user.setEmail((String) cells[2]);
            user.setPassword((String) cells[3]);
            user.setSex((Boolean) cells[4]);
            user.setUsername((String) cells[5]);
        }
        return user;
    }

    public User findByName(String name) {
        Query query = entityManager.createQuery("select u from User u where u.username = ?1", User.class);
        query.setParameter(1, name);
        return (User) query.getSingleResult();
    }

    public ArrayList<User> findAll() {
        Query query = entityManager.createQuery("select u from User u", User.class);
        return (ArrayList<User>) query.getResultList();
    }
}
