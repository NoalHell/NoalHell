package Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity     //声明实体类
@Table(name = "Salary")    //建立实体类和表的映射关系
public class Salary implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="cId")
    private int cId;
    @Column(name="name")
    private String name;
    @Column(name="money")
    private double money;
    @Column(name="position")
    private String position;

    public void setName(String name) {
        this.name = name;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getMoney() {
        return money;
    }

    public int getcId() {
        return cId;
    }

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }


}
