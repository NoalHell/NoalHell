package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class QueryOther extends JFrame {

    public QueryOther(){

//        设置窗口可见性,无关于窗口大小.
        this.setVisible(true);
//        设置窗口大小
        this.setSize(500, 200);
//        设置背景颜色
        this.setBackground(new Color(77,77,77));
//        设置初始位置


//        控制窗口大小是否可改变,默认为True,即可以改变。
        this.setResizable(false);
        Button queryEmployeeByEId = new Button("根据身份证号，显示雇员的基本信息");
        queryEmployeeByEId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryEmployeeByEId();
            }
        });
        Button inputWorkMark = new Button("根据指定的日期，显示雇员的周工作记录或月工作记录");
        inputWorkMark.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryOtherFuc2();
            }
        });
        Button querySellMark = new Button("根据指定的日期，显示雇员周或月的薪水信息");
        querySellMark.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            }
        });
        Button calculateMonthlySalary = new Button("根据指定的日期，显示佣金雇员某个月的销售记录。");
        calculateMonthlySalary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryOtherFuc4();
            }
        });

        this.setLayout(new FlowLayout());

        this.add(queryEmployeeByEId);
        this.add(inputWorkMark);
        this.add(querySellMark);
        this.add(calculateMonthlySalary);

    }

}
