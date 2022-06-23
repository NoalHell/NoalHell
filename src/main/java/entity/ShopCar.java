package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity     //声明实体类
@Table(name = "ShopCar")    //建立实体类和表的映射关系
public class ShopCar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "userId", nullable = false)
    private int name;

    @Column(name = "goodsId", nullable = false)
    private int goodsId;

    @Column(name = "num", nullable = false)
    private int num;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(int name) {
        this.name = name;
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

    public int getName() {
        return name;
    }

    public ShopCar(){}

}