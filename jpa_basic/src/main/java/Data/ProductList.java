package Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity     //声明实体类
@Table(name = "ProductList")    //建立实体类和表的映射关系
public class ProductList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="money")
    private float money;



    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(float money) {
        this.money = money;
    }


    public float getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

}
