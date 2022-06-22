package Dao;

import Data.Employee;
import Data.SellMark;
import Utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao{

    public void Insert(Employee employee) {
        //1.获取事务对象
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        //2.开启事务
        transaction.begin();
        //3.添加操作
        entityManager.persist(employee);
        //4.提交事务·
        transaction.commit();
        //5.关闭资源
        entityManager.close();
    }

    public void update(Employee employee) {
        //1. 获取实体类管理器
        EntityManager entityManager = JpaUtil.getEntityManager();
        //获取事务
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
        //查询出来需要修改的数据
        Employee e = entityManager.find(Employee.class, employee.getId());
        //修改数据
        Employee merge = entityManager.merge(e);
        System.out.println("数据修改成功后数据" + merge);
        //  提交事务
        transaction.commit();
        //关闭链接
        entityManager.close();

    }
    public Employee selectEmployeeById(int id){
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //查询id=5的顾客
        Employee employee = entityManager.find(Employee.class, id);
        System.out.println(employee);
        return employee;
    }

    public Employee getEmpolyeeByEId(int eId){
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //查询id=5的顾客
        Query query = entityManager.createNativeQuery("select * from employee where eId="+eId);
        List rows = query.getResultList();

        Object row = rows.get(0);
        Object[] cells = (Object[]) row;
        Employee employee = new Employee();
        employee.setBirthday((String) cells[1]);
        employee.setEId((Integer) cells[2]);
        employee.setName((String) cells[3]);
        employee.setTel((String) cells[4]);
        employee.setType((String) cells[5]);
        return employee;
    }

}
