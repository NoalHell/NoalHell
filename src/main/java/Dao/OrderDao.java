package Dao;

import entity.Order;

import javax.persistence.Query;
import java.util.ArrayList;


public class OrderDao extends MyDao {
    public void insert(Order order){
        super.insert(order);
    }

    public void update(Order order){
        super.update(order);
    }

    public Order findById(int id){
        Query query = entityManager.createQuery("select o from Order o where o.id =?1", Order.class);
        query.setParameter(1, id);
        return(Order) query.getSingleResult();
    }

    public ArrayList<Order> findMyAllByUserId(int userId){
        Query query = entityManager.createQuery("select o from Order o where o.userId =?1", Order.class);
        query.setParameter(1, userId);
        return (ArrayList<Order>) query.getResultList();
    }
}
