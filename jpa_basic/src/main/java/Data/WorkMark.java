package Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity     //声明实体类
@Table(name = "workmark")    //建立实体类和表的映射关系
public class WorkMark implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="time")
    private Date time;
    @Column(name="weekIndex")
    private int weekIndex;
    @Column(name="workHour")
    private int workHour;

    public void setTime(Date time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeekIndex(int weekIndex) {
        this.weekIndex = weekIndex;
    }

    public void setWorkHour(int workHour) {
        this.workHour = workHour;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getWeekIndex() {
        return weekIndex;
    }

    public int getWorkHour() {
        return workHour;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "name: " + name + "  time:"+time+" weekIndex:"+weekIndex+" workHour"+workHour;
    }
}
