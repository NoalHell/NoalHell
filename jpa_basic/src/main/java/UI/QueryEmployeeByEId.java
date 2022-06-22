package UI;

import Dao.EmployeeDao;
import Data.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryEmployeeByEId extends JFrame {
    public QueryEmployeeByEId(){
        //        设置窗口可见性,无关于窗口大小.
        this.setVisible(true);
//        设置窗口大小
        this.setSize(500, 200);
//        设置背景颜色
        this.setLayout(new GridLayout(6,1));
        TextField EIdTF = new TextField(20);
        this.add(EIdTF);
        Button query = new Button("query");
        query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(EIdTF.getText());
                EmployeeDao employeeDao = new EmployeeDao();
                Employee employee = employeeDao.getEmpolyeeByEId(id);
                showMessage(employee.toString());
            }
        });
        this.add(query);
    }
    private void showMessage(String s){
        this.add(new Label(s));
    }
}
