package UI;

import Dao.SellMarkDao;
import Dao.WorkMarkDao;
import Data.SellMark;
import Data.WorkMark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EmployeeSellMarkListFram extends JFrame {

    public EmployeeSellMarkListFram() {
        this.setSize(400, 500);
        this.setLocation(300, 400);
        this.setVisible(true);

//        SellMark sellMark = new SellMark();
//        sellMark.setName("555");
//        sellMark.setTime("2022-10-10");
//        sellMark.setProductName("test");
//        sellMark.setPrice(15);
//        sellMark.setCount(2);

        this.setLayout(new FlowLayout());
        SellMarkDao sellMarkDao = new SellMarkDao();
//        sellMarkDao.Insert(sellMark);
        ArrayList<SellMark> sellMarks = sellMarkDao.getAllSellMark();

        for (int i = 0; i < sellMarks.size(); i++) {
            this.add(new Label(sellMarks.get(i).toString()));

        }
    }

    private void close (){
        this.dispose();
    }

}


