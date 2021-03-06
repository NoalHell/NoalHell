package Dao;

import Utils.JpaUtil;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;

public class MyDao {
    protected EntityManager entityManager;
    public MyDao(){
        this.entityManager = JpaUtil.getEntityManager();
    }

    protected  <E extends Serializable> void insert(E data){
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

    protected  <E extends Serializable> E update(E data){
        //1. 获取实体类管理器
        EntityManager entityManager = JpaUtil.getEntityManager();
        //获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
        //修改数据
        E merge = entityManager.merge(data);
        System.out.println("数据修改成功后数据" + merge);
        //  提交事务
        transaction.commit();
        //关闭链接
        entityManager.close();
        return  merge;
    }
}
