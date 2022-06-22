package Data;

import javax.persistence.*;
import java.io.Serializable;
@Entity     //声明实体类
@Table(name = "Employee")    //建立实体类和表的映射关系
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="eId")
    private int eId;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "tel")
    private String tel;

    public void setEId(int id) {
        this.eId = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int geteId() {
        return eId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getTel() {
        return tel;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "eid=" + eId + "  name="+name + "  type=" + type + "  birthday "+ birthday + " tel "+ tel;
    }
}
