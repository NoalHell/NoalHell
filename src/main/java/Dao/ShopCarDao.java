package Dao;

import Utils.JpaUtil;
import entity.ShopCar;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ShopCarDao extends MyDao{

    public ShopCarDao() {

    }

    public void delete(ShopCar shopCar){
        shopCar.setDeleted(1);
        this.update(shopCar);
//        //1. 获取实体类管理器
//        EntityManager entityManager = JpaUtil.getEntityManager();
//        //获取事务
//        EntityTransaction transaction = entityManager.getTransaction();
//        //开启事务
//        transaction.begin();
//        //查询出来需要修改的数据
//        //修改数据
//        shopCar.setDeleted(1);
//        ShopCar merge = entityManager.merge(shopCar);
//        System.out.println("数据修改成功后数据" + merge);
//        //  提交事务
//        transaction.commit();
//        //关闭链接
//        entityManager.close();
    }

    public void Insert(ShopCar shopCar) {
        this.insert(shopCar);
//        //1.获取事务对象
//        EntityManager entityManager = JpaUtil.getEntityManager();
//        EntityTransaction transaction = entityManager.getTransaction();
//        //2.开启事务
//        transaction.begin();
//        //3.添加操作
//        entityManager.persist(shopCar);
//        //4.提交事务·
//        transaction.commit();
//        //5.关闭资源
//        entityManager.close();
    }

    public ShopCar update(ShopCar shopCar) {
        return super.update(shopCar);
        //1. 获取实体类管理器
//        EntityManager entityManager = JpaUtil.getEntityManager();
//        //获取事务
//        EntityTransaction transaction = entityManager.getTransaction();
//        //开启事务
//        transaction.begin();
//        //查询出来需要修改的数据
//        //修改数据
//        ShopCar merge = entityManager.merge(shopCar);
//        System.out.println("数据修改成功后数据" + merge);
//        //  提交事务
//        transaction.commit();
//        //关闭链接
//        entityManager.close();
//        //查询出来需要修改的数据
//        return merge;
    }



    public ShopCar findByGoodsId(int userId,int goodsId) {
        Query query = entityManager.createQuery("select s from ShopCar s where s.userId =?1 and s.goodsId = ?2", ShopCar.class);
        query.setParameter(1, userId);
        query.setParameter(2, goodsId);
        return (ShopCar) query.getSingleResult();
    }

    public ShopCar findOneByUserId(int id) {
        Query query = entityManager.createQuery("select s from ShopCar s where s.userId = ?1", ShopCar.class);
        query.setParameter(1, id);
        return (ShopCar) query.getSingleResult();
    }

    public ShopCar findOneById(int id) {
        Query query = entityManager.createQuery("select s from ShopCar s where s.id = ?1", ShopCar.class);
        query.setParameter(1, id);
        return (ShopCar) query.getSingleResult();
    }

    public ShopCar findByName(String name) {
        Query query = entityManager.createQuery("select s from ShopCar s where s.name = ?1", ShopCar.class);
        query.setParameter(1, name);
        return (ShopCar) query.getSingleResult();
    }

    public ArrayList<ShopCar> findAll() {
        Query query = entityManager.createQuery("select u from ShopCar u", ShopCar.class);
        return (ArrayList<ShopCar>) query.getResultList();
    }

    public ArrayList<ShopCar> findMyAll(int id) {
        Query query = entityManager.createQuery("select s from ShopCar s where s.userId=?1", ShopCar.class);
        query.setParameter(1, id);
        return (ArrayList<ShopCar>) query.getResultList();
    }

}

