package UI;

import Dao.EmployeeDao;
import Dao.SalaryDao;
import Dao.SellMarkDao;
import Data.Employee;
import Data.Salary;
import Data.SellMark;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CaculateEmployeeSalary extends JFrame {

    public CaculateEmployeeSalary() {
        this.setSize(400, 500);
        this.setLocation(300, 400);

        this.add(new Label("输入需要查询的雇员姓名: "));
        TextField textField = new TextField();
        this.add(textField);
        Button submit = new Button("submit");
        Button cancel = new Button("cancel");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                if(name.equals("")){
                    return;
                }
                SalaryDao salaryDao = new SalaryDao();
                Salary salary = salaryDao.getByCName(name);
                if(salary == null){
                    showMessage("没有该用户");
                    return;
                }
                SellMarkDao sellMarkDao = new SellMarkDao();
                ArrayList<SellMark> sellMarks = sellMarkDao.getSellMarkByCName(name);
                float totalSell = 0;
                for(SellMark sellMark:sellMarks){
                    totalSell += sellMark.getCount()*sellMark.getPrice();
                }
                float[] aimP = {100000,200000};
                double[] addR = {0.1,0.15};
                double reword = salary.getMoney();
                for(int i=0;totalSell>aimP[i]&&i<aimP.length;i++){
                    if(totalSell>aimP[i]){
                        reword += aimP[i]*addR[i];
                        totalSell -= aimP[i];
                    } else {
                        reword += totalSell*addR[i];
                        totalSell=0;
                    }

                }
                if(totalSell>0){
                    reword+= totalSell*0.15;
                }
                showMessage(salary.getName()+"的月薪为: "+ reword);


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
        this.setLayout(new GridLayout(5,1));
        this.setVisible(true);
    }

    private void close (){
        this.dispose();
    }

    private void showMessage(String s){
        this.add(new Label(s));
        this.setVisible(true);
    }


}


