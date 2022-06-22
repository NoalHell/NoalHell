package Dao;

import Data.SellMark;
import Utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SellMarkDao {
    public void Insert(SellMark sellMark) {
        //1.获取事务对象
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        //2.开启事务
        transaction.begin();
        //3.添加操作
        entityManager.persist(sellMark);
        //4.提交事务·
        transaction.commit();
        //5.关闭资源
        entityManager.close();
    }

    public void update(SellMark sellMark) {
        //1. 获取实体类管理器
        EntityManager entityManager = JpaUtil.getEntityManager();
        //获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
        //查询出来需要修改的数据
        SellMark w = entityManager.find(SellMark.class, sellMark.getId());
        //修改数据
        SellMark merge = entityManager.merge(w);
        System.out.println("数据修改成功后数据" + merge);
        //  提交事务
        transaction.commit();
        //关闭链接
        entityManager.close();

    }
    public SellMark selectById(int id){
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //查询id=5的顾客
        SellMark w = entityManager.find(SellMark.class, id);
        System.out.println(w);
        return w;
    }

    public ArrayList<SellMark> getAllSellMark(){
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //查询id=5的顾客
        Query query = entityManager.createNativeQuery("select * from sellmark");
        List rows = query.getResultList();
        ArrayList<SellMark> marks = new ArrayList<>();
        for(Object row : rows){
            SellMark sellMark = new SellMark();
            Object[] cells = (Object[]) row;
            sellMark.setCount((Integer) cells[1]);
            sellMark.setName((String) cells[2]);
            sellMark.setPrice((Float) cells[3]);
            sellMark.setProductName((String) cells[4]);
            sellMark.setTime((Date)cells[5]);
            marks.add(sellMark);
        }
        return marks;
    }

    public ArrayList<SellMark> getSellMarkByCName(String name){
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //查询id=5的顾客
        Query query = entityManager.createNativeQuery("select * from sellmark where name=\'"+name+"\'");
        List rows = query.getResultList();
        ArrayList<SellMark> marks = new ArrayList<>();
        for(Object row : rows){
            SellMark sellMark = new SellMark();
            Object[] cells = (Object[]) row;
            sellMark.setCount((Integer) cells[1]);
            sellMark.setName((String) cells[2]);
            sellMark.setPrice((Float) cells[3]);
            sellMark.setProductName((String) cells[4]);
            sellMark.setTime((Date )cells[5]);
            marks.add(sellMark);
        }
        return marks;
    }

    public ArrayList<SellMark> getSellMarkByTime(String beg, String end) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //查询id=5的顾客
        Query query = entityManager.createNativeQuery("select * from sellmark where time>\'" + beg + "\'and time<\'"+end+"\'");
        List rows = query.getResultList();
        ArrayList<SellMark> marks = new ArrayList<>();
        for (Object row : rows) {
            SellMark sellMark = new SellMark();
            Object[] cells = (Object[]) row;
            sellMark.setCount((Integer) cells[1]);
            sellMark.setName((String) cells[2]);
            sellMark.setPrice((Float) cells[3]);
            sellMark.setProductName((String) cells[4]);
            sellMark.setTime((Date) cells[5]);
            marks.add(sellMark);
        }
        return marks;
    }
}
