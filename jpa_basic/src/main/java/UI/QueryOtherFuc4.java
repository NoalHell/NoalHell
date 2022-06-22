package UI;

import Dao.SellMarkDao;
import Data.SellMark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class QueryOtherFuc4 extends JFrame {

    public QueryOtherFuc4(){

//        设置窗口可见性,无关于窗口大小.
        this.setVisible(true);
//        设置窗口大小
        this.setSize(500, 200);
//        设置背景颜色
        this.setBackground(new Color(77,77,77));
//        设置初始位置

        TextField textFieldBeg = new TextField(50);
        TextField textFieldEnd = new TextField(50);

        this.add(new Label("开始时间: "));
        this.add(textFieldBeg);

        this.add(new Label("结束时间: "));
        this.add(textFieldEnd);

        Button queryBtn = new Button("查询");
        queryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SellMarkDao sellMarkDao = new SellMarkDao();
                ArrayList<SellMark> sellMarks = sellMarkDao.getSellMarkByTime(textFieldBeg.getText(),textFieldEnd.getText());
                for(SellMark s:sellMarks){
                    showMessage(s.toString());
                }
            }
        });
        this.add(queryBtn);
        this.setLayout(new GridLayout(7,1));


    }
    private void showMessage(String s){
        this.add(new Label(s));
    }

}
