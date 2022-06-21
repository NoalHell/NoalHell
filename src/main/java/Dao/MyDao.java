package Dao;

import Utils.JpaUtil;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;

public class MyDao {
    public static<E extends Serializable> void insert(E data){
        //1.获取事务对象
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        //2.开启事务
        transaction.begin();
        //3.添加操作
        entityManager.persist(data);
        //4.提交事务·
        transaction.commit();
        //5.关闭资源
        entityManager.close();
    }

    public static<E extends Serializable> void Update(E data){
        //1. 获取实体类管理器
        EntityManager entityManager = JpaUtil.getEntityManager();
        //获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
        //查询出来需要修改的数据
        E e = (E) entityManager.find(data.getClass(), data);
        //修改数据
        E merge = entityManager.merge(e);
        System.out.println("数据修改成功后数据" + merge);
        //  提交事务
        transaction.commit();
        //关闭链接
        entityManager.close();
    }
}
