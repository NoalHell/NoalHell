package Dao;

import Data.Employee;
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

    public void Insert(User user) {
        //1.获取事务对象
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        //2.开启事务
        transaction.begin();
        //3.添加操作
        entityManager.persist(user);
        //4.提交事务·
        transaction.commit();
        //5.关闭资源
        entityManager.close();
    }

    public void update(User user) {
        //1. 获取实体类管理器
        EntityManager entityManager = JpaUtil.getEntityManager();
        //获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
        //查询出来需要修改的数据
        Employee e = entityManager.find(Employee.class, user.getId());
        //修改数据
        Employee merge = entityManager.merge(e);
        System.out.println("数据修改成功后数据" + merge);
        //  提交事务
        transaction.commit();
        //关闭链接
        entityManager.close();

    }
    public User selectEmployeeById(int id){
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //查询id=5的顾客
        User user = entityManager.find(User.class, id);
        System.out.println(user);
        return user;
    }

    public User getByUserName(String username){
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //查询id=5的顾客
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

    public ArrayList<User> getList(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //查询id=5的顾客
        Query query = entityManager.createNativeQuery("select * from myUser");
        List rows = query.getResultList();
        ArrayList<User> users = new ArrayList<>();
        if(rows.size()!=0){
            for(Object row:rows){
                Object[] cells = (Object[]) row;
                User user =new User();
                user.setId((Integer) cells[0]);
                user.setBirthday((Date)  cells[1]);
                user.setEmail((String) cells[2]);
                user.setPassword((String) cells[3]);
                user.setSex((Boolean) cells[4]);
                user.setUsername((String) cells[5]);
                users.add(user);
            }

        }
        return users;
    }
}
