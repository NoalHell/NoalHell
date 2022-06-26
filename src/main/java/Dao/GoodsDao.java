package Dao;

import Utils.JpaUtil;
import entity.Goods;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class GoodsDao extends MyDao{
    public GoodsDao(){
    }

    public void Insert(Goods goods) {
        insert(goods);
    }

    public Goods update(Goods goods) {
        return super.update(goods);
    }

    public ArrayList<Goods> findByName(String name) {
        Query query = entityManager.createQuery("select u from Goods u where u.name = ?1", Goods.class);
        query.setParameter(1, name);
        return (ArrayList<Goods>) query.getResultList();
    }

    public ArrayList<Goods> findAll() {
        Query query = entityManager.createQuery("select u from Goods u", Goods.class);
        return (ArrayList<Goods>) query.getResultList();
    }
}
