package Dao;

import Data.Employee;
import Data.SellMark;
import Data.WorkMark;
import Utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkMarkDao {

    public void Insert(WorkMark workMark) {
        //1.获取事务对象
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        //2.开启事务
        transaction.begin();
        //3.添加操作
        entityManager.persist(workMark);
        //4.提交事务·
        transaction.commit();
        //5.关闭资源
        entityManager.close();
    }

    public void update(WorkMark workMark) {
        //1. 获取实体类管理器
        EntityManager entityManager = JpaUtil.getEntityManager();
        //获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
        //查询出来需要修改的数据
        WorkMark w = entityManager.find(WorkMark.class, workMark.getId());
        //修改数据
        WorkMark merge = entityManager.merge(w);
        System.out.println("数据修改成功后数据" + merge);
        //  提交事务
        transaction.commit();
        //关闭链接
        entityManager.close();

    }
    public WorkMark selectById(int id){
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //查询id=5的顾客
        WorkMark w = entityManager.find(WorkMark.class, id);
        System.out.println(w);
        return w;
    }

    public ArrayList<WorkMark> getWorkMarkByTime(String beg, String end) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //查询id=5的顾客
        Query query = entityManager.createNativeQuery("select * from workmark where time>\'" + beg + "\'and time<\'"+end+"\'");
        List rows = query.getResultList();
        ArrayList<WorkMark> marks = new ArrayList<>();
        for (Object row : rows) {
            WorkMark w = new WorkMark();
            Object[] cells = (Object[]) row;
            w.setName((String) cells[1]);
            w.setTime((Date) cells[2]);
            w.setWeekIndex((Integer) cells[3]);
            w.setWorkHour((Integer) cells[4]);

            marks.add(w);
        }
        return marks;
    }

}
