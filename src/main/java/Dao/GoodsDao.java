package Dao;

import Utils.JpaUtil;
import entity.Goods;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class GoodsDao {
    EntityManager entityManager;
    public GoodsDao(){
        this.entityManager = JpaUtil.getEntityManager();
    }

    public void Insert(Goods goods) {
        entityManager.persist(goods);
    }

    public Goods update(Goods goods) {
        //查询出来需要修改的数据
        Goods e = entityManager.find(Goods.class, goods.getId());
        //修改数据
        Goods merge = entityManager.merge(e);
        System.out.println("数据修改成功后数据" + merge);
        return merge;
    }

    public Goods findByName(String name) {
        Query query = entityManager.createQuery("select u from Goods u where u.name = ?1", Goods.class);
        query.setParameter(1, name);
        return (Goods) query.getSingleResult();
    }

    public ArrayList<Goods> findAll() {
        Query query = entityManager.createQuery("select u from Goods u", Goods.class);
        return (ArrayList<Goods>) query.getResultList();
    }
}
