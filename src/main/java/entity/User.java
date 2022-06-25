package entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity     //声明实体类
@Table(name = "myUser")    //建立实体类和表的映射关系

//逻辑删除注解，删除sql变成了update
@SQLDelete(sql = "update myUser set deleted = 1 where id = ?")
//where条件带上了逻辑删除条件
@Where(clause = "deleted = 0")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(insertable = false, columnDefinition = "DATETIME DEFAULT NOW()")
//    private Date createTime;
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

    //逻辑删除（0 未删除、1 删除）
    private Integer deleted = 0;

    //set


    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

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

    @OneToMany(mappedBy = "ownerUser",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    //级联保存、更新、删除、刷新;延迟加载。当删除用户，会级联删除该用户的所有文章
    //拥有mappedBy注解的实体类为关系被维护端
    //mappedBy="ownerUserId"中的ownerUserId是Address中的ownerUserId属性
    private List<Address> addresses =new ArrayList<>();

    public ArrayList<Address> getAddresses() {
        return (ArrayList<Address>) addresses;
    }

    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
