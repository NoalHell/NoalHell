package entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity     //声明实体类
@Table(name = "ShopCar")    //建立实体类和表的映射关系

//逻辑删除注解，删除sql变成了update
@SQLDelete(sql = "update ShopCar set deleted = 1 where id = ?")
//where条件带上了逻辑删除条件
@Where(clause = "deleted = 0")
public class ShopCar  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "userId", nullable = false)
    private int userId;
    @Column(name = "statue")
    private int statue = 0;


    //逻辑删除（0 未删除、1 删除）
    private int deleted;

    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<ShopCarItem> goodsItemList = new ArrayList<>();

    public List<ShopCarItem> getGoodsItemList() {
        return goodsItemList;
    }

    public void setGoodsItemList(List<ShopCarItem> goodsItemList) {
        this.goodsItemList = goodsItemList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public ShopCar(){}

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public void addShopCarItem(ShopCarItem shopCarItem){
        this.goodsItemList.add(shopCarItem);
    }

    public int getStatue() {
        return statue;
    }

    public void setStatue(int statue) {
        this.statue = statue;
    }
}