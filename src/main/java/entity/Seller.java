package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity     //声明实体类
@Table(name = "Seller")    //建立实体类和表的映射关系
public class Seller implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    public Seller(){}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}