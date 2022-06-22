package UI;

import Dao.EmployeeDao;
import Data.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputEmployee extends JFrame {

    public InputEmployee(){
        this.setSize(300,200);
        this.setLocation(300,400);
        this.setVisible(true);

        TextField birthdayTE = new TextField(10);//字符数
        TextField eIdTE = new TextField(10);//字符数
        TextField nameTE = new TextField(20);//字符数
        TextField telTE = new TextField(20);//字符数
        TextField typeTE = new TextField(20);//字符数

        this.setLayout(new GridLayout(6,2));

        this.add(new Label("雇员编号"));
        this.add(eIdTE);
        this.add(new Label("雇员名称"));
        this.add(nameTE);
        this.add(new Label("雇员生日"));
        this.add(birthdayTE);
        this.add(new Label("雇员电话"));
        this.add(telTE);
        this.add(new Label("雇员类型"));
        this.add(typeTE);

        Button submit = new Button("submit");
        Button cancel = new Button("cancel");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee employee = new Employee();
                employee.setName(nameTE.getText());
                employee.setEId( Integer.parseInt( eIdTE.getText()));
                employee.setType(typeTE.getText());
                employee.setTel(telTE.getText());
                employee.setBirthday(birthdayTE.getText());

                EmployeeDao employeeDao = new EmployeeDao();
                employeeDao.Insert(employee);
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


