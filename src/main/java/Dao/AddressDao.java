package Dao;

import entity.Address;

import javax.management.Query;
import java.util.ArrayList;

public class AddressDao extends MyDao {
    public void insert(Address address){
        super.insert(address);
    }

    public void update(Address address){
        super.update(address);
    }
//
//    public ArrayList<Address> findMyAll(int userId){
//        Query query = entityManager.createQuery("select a from Address a where a.")
//    }
}
