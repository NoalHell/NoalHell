package entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

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

    @Column(name = "goodsId", nullable = false)
    private int goodsId;

    @Column(name = "num", nullable = false)
    private int num;

    //逻辑删除（0 未删除、1 删除）
    private Integer deleted = 0;

//    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinColumn(name="goodsId", referencedColumnName = "Goods.id")
//    private Goods goods;
//
//    public void setGoods(Goods goods) {
//        this.goods = goods;
//    }
//
//    public Goods getGoods() {
//        return goods;
//    }

    @Column(name = "price")
    private double price;
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public void setNum(int num) {
        this.num = num;
    }




    public int getNum() {
        return num;
    }

    public int getId() {
        return id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public int getUserId() {
        return userId;
    }

    public ShopCar(){}

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}