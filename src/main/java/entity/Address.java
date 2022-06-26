package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Address")

public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    @Column(name = "name", length = 50)
    private String name;
    @Column(name = "tel", length = 20)
    private String tel;
    @Column(name = "remark", length = 300)
    private String remark;
    @Column(name="address", length = 100)
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }


    public String getRemark() {
        return remark;
    }

    public String getTel() {
        return tel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Address(String name, String tel, String address){
        this.name = name;
        this.tel = tel;
        this.address= address;
    }
    public Address(){}
}
