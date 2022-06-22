package Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity     //声明实体类
@Table(name = "SellMark")    //建立实体类和表的映射关系
public class SellMark implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="productName")
    private String productName;
    @Column(name="name")
    private String name;
    @Column(name="price")
    private float price;
    @Column(name="count")
    private int count;
    @Column(name= "time")
    private Date time;


    public void setName(String name) {
        this.name = name;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getPrice() {
        return price;
    }

    public String getProductName() {
        return productName;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "销售者: " + name +" 时间: " + time +" 产品名称: "+ productName +"价格: " +price + " 数量: "+count;
    }
}
