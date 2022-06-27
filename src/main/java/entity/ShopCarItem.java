package entity;

import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity     //声明实体类
@Table(name = "ShopCarItem")    //建立实体类和表的映射关系

//逻辑删除注解，删除sql变成了update
@SQLDelete(sql = "update ShopCariTEM set deleted = 1 where id = ?")
//where条件带上了逻辑删除条件

public class ShopCarItem  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }
    @ManyToOne
    @JoinColumn(name="goods_id")
    private Goods goods;

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Goods getGoods() {
        return goods;
    }

    @Column(name = "num")
    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
