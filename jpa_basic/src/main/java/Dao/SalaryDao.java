package Dao;

import Data.Salary;
import Data.SellMark;
import Utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class SalaryDao {

    public void Insert(Salary s) {
        //1.获取事务对象
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        //2.开启事务
        transaction.begin();
        //3.添加操作
        entityManager.persist(s);
        //4.提交事务·
        transaction.commit();
        //5.关闭资源
        entityManager.close();
    }

    public void update(Salary s) {
        //1. 获取实体类管理器
        EntityManager entityManager = JpaUtil.getEntityManager();
        //获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
        //查询出来需要修改的数据
        Salary w = entityManager.find(Salary.class, s.getcId());
        //修改数据
        Salary merge = entityManager.merge(w);
        System.out.println("数据修改成功后数据" + merge);
        //  提交事务
        transaction.commit();
        //关闭链接
        entityManager.close();

    }
    public Salary selectById(int id){
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //查询id=5的顾客
        Salary w = entityManager.find(Salary.class, id);
        System.out.println(w);
        return w;
    }

    public Salary getByCName(String name) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createNativeQuery("select * from salary where name = \'" + name+"\'");
        Salary salary = null;
        if(query.getResultList() != null){
            List rows = query.getResultList();
            Object row = rows.get(0);
            salary = new Salary();
            Object[] cells = (Object[]) row;
            salary.setcId((Integer) cells[1]);
            salary.setMoney((Double) cells[2]);
            salary.setName((String) cells[3]);
            salary.setPosition((String) cells[4]);
        }
        return salary;
    }

    public ArrayList<Salary> getAll() {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createNativeQuery("select * from salary");
        List rows = query.getResultList();
        ArrayList<Salary> salaries = new ArrayList<>();
        for (Object row : rows) {
            Salary salary = new Salary();
            Object[] cells = (Object[]) row;
            salary.setcId((Integer) cells[1]);
            salary.setMoney((Float) cells[2]);
            salary.setName((String) cells[3]);
            salary.setPosition((String) cells[4]);

            salaries.add(salary);
        }
        return salaries;
    }
}
