package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity     //声明实体类
@Table(name = "Goods")    //建立实体类和表的映射关系
public class Goods implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name", unique = true, nullable = false, length = 20)
    private String name;
    @Column(name="sellId", nullable = false)
    private int sellId;

    @Column(name="postTime")
    private Date postTime;

    @Column(name="price")
    private Double price;

    @Column(name="produceLocation")
    private String produceLocation;

    @Column(name="produceTime")
    private Date produceTime;

    @Column(name="keepTime")
    private Date keepTime;

    //set
    public void setId(int id) {
        this.id = id;
    }

    public Goods(){}

    public Goods(String name, double price, int sellerId){
        this.name = name;
        this.price = price;
        this.sellId = sellerId;
    }

    public Goods(String name, Double price, Date postTime, String produceLocation,Date produceTime, Date keepTime){
        this.name = name;
        this.price = price;
        this.postTime = postTime;
        this.produceLocation = produceLocation;
        this.produceTime = produceTime;
        this.keepTime = keepTime;
    }

    public int getId() {
        return id;
    }

    public int getSellId() {
        return sellId;
    }

    public Double getPrice() {
        return price;
    }

    public Date getPostTime() {
        return postTime;
    }

    public Date getProduceTime() {
        return produceTime;
    }

    public Date getKeepTime() {
        return keepTime;
    }

    public String getProduceLocation() {
        return produceLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setKeepTime(Date keepTime) {
        this.keepTime = keepTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public void setProduceLocation(String produceLocation) {
        this.produceLocation = produceLocation;
    }

    public void setProduceTime(Date produceTime) {
        this.produceTime = produceTime;
    }

    public void setSellId(int sellId) {
        this.sellId = sellId;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    // 保证不同对象
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
