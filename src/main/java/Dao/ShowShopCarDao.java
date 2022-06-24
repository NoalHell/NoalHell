package Dao;

import Utils.JpaUtil;
import entity.ShopCar;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;

public class ShowShopCarDao {

    EntityManager entityManager;
    public ShowShopCarDao(){
        this.entityManager = JpaUtil.getEntityManager();
    }

    public ShopCar findByGoodsId(int id) {
        Query query = entityManager.createQuery("select s from ShopCar s where s.goodsId = ?1", ShopCar.class);
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

}
