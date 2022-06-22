package UI;

import Dao.EmployeeDao;
import Dao.WorkMarkDao;
import Data.Employee;
import Data.WorkMark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkMarkFram extends JFrame {

    public WorkMarkFram(){
        this.setSize(300,200);
        this.setLocation(300,400);
        this.setVisible(true);

        TextField workNameTE = new TextField(10);//字符数
        TextField workIndexTE = new TextField(10);//字符数
        TextField timeTE = new TextField(10);//字符数
        TextField workHourTE = new TextField(20);//字符数

        this.setLayout(new GridLayout(6,2));

        this.add(new Label("雇员姓名"));
        this.add(workNameTE);
        this.add(new Label("工作周次"));
        this.add(workIndexTE);
        this.add(new Label("工作日期"));
        this.add(timeTE);
        this.add(new Label("工时数"));
        this.add(workHourTE);

        Button submit = new Button("提交");
        Button cancel = new Button("取消");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WorkMark workMark = new WorkMark();
                workMark.setName(workNameTE.getText());
                workMark.setWeekIndex( Integer.parseInt(workIndexTE.getText()) );

                Date date=null;
                SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date=formatter.parse(timeTE.getText());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                workMark.setTime(date);
                workMark.setWorkHour( Integer.parseInt(workHourTE.getText()));

                WorkMarkDao workMarkDao = new WorkMarkDao();
                workMarkDao.Insert(workMark);
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });

        this.add(submit);
        this.add(cancel);
    }

    private void close (){
        this.dispose();
    }

}


