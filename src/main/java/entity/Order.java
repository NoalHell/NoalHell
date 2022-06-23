package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity     //声明实体类
@Table(name = "myOrder")    //建立实体类和表的映射关系
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="userId", nullable = false)
    private int userId;

    @Column(name="cartId", nullable = false)
    private int cartId;

    @Column(name="payStatue")
    private int payStatue;

    @Column(name="price")
    private Double price;

    @Column(name="remark")
    private String remark;

    public Order(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void setPayStatue(int payStatue) {
        this.payStatue = payStatue;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public int getCartId() {
        return cartId;
    }

    public int getPayStatue() {
        return payStatue;
    }

    public int getUserId() {
        return userId;
    }

    public String getRemark() {
        return remark;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
