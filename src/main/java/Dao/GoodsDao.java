package Dao;

import Utils.JpaUtil;
import entity.Goods;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class GoodsDao {

    public void Insert(Goods user) {
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

    public void update(Goods goods) {
        //1. 获取实体类管理器
        EntityManager entityManager = JpaUtil.getEntityManager();
        //获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
        //查询出来需要修改的数据
        Goods g = entityManager.find(Goods.class, goods.getId());
        //修改数据
        Goods merge = entityManager.merge(g);
        System.out.println("数据修改成功后数据" + merge);
        //  提交事务
        transaction.commit();
        //关闭链接
        entityManager.close();

    }
    public User selectById(int id){
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //查询id=5的顾客
        User user = entityManager.find(User.class, id);
        System.out.println(user);
        return user;
    }

    public User getByName(String name){
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //查询id=5的顾客
        String sql ="select * from myUser where username = ?";
        Query query = entityManager.createNativeQuery("select * from Goods where name = "+"\'"+name+"\'");
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

//    public ArrayList<Goods> getList(){
//        EntityManager entityManager = JpaUtil.getEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        //查询id=5的顾客
//        Query query = entityManager.createNativeQuery("select * from Goods");
//        List rows = query.getResultList();
//        ArrayList<Goods> goods = new ArrayList<>();
//        if(rows.size()!=0){
//            for(Object row:rows){
//                Object[] cells = (Object[]) row;
//                Goods goods1 =new Goods(
//                        (Integer) cells[0],
//
//                );
//                //user.setId((Integer) cells[0]);
////                user.setBirthday((Date)  cells[1]);
////                user.setEmail((String) cells[2]);
////                user.setPassword((String) cells[3]);
////                user.setSex((Boolean) cells[4]);
////                user.setUsername((String) cells[5]);
//                users.add(user);
//            }
//
//        }
//        return goods;
//    }
}
