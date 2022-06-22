package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args){
        Frame frame = new Frame("Java大作业");
        frame.setLayout(new GridLayout());
//        设置窗口可见性,无关于窗口大小.
        frame.setVisible(true);
//        设置窗口大小
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {//      结束程序
                System.exit(0);
            }
        });
        Button inputEmployee = new Button("导入雇员信息");
        inputEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InputEmployee();
            }
        });
        Button inputWorkMark = new Button("导入工作记录");
        inputWorkMark.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WorkMarkFram();
            }
        });
        Button querySellMark = new Button("查询销售记录");
        querySellMark.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeSellMarkListFram();
            }
        });
        Button calculateMonthlySalary = new Button("计算月薪谁");
        calculateMonthlySalary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CaculateEmployeeSalary();
            }
        });
        Button queryOther = new Button("其它查询");
        queryOther.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryOther();
            }
        });

        frame.add(inputEmployee);
        frame.add(inputWorkMark);
        frame.add(querySellMark);
        frame.add(calculateMonthlySalary);
        frame.add(queryOther);
        frame.setSize(700, 300);

    }

}
