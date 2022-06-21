package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity     //声明实体类
@Table(name = "myUser")    //建立实体类和表的映射关系
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="username", unique = true, nullable = false, length = 20)
    private String username;

    @Column(name="password", nullable = false, length = 20)
    private String password;

    @Column(name="sex")
    private boolean sex;

    @Column(name="birthday")
    private Date birthday;

    @Column(name="email", length = 20)
    private String email;

    //set
    public void setId(int id) {
        this.id = id;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    //get
    public int getId() {
        return id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
