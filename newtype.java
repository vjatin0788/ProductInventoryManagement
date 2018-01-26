
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
import javax.swing.event.*;

public class newtype extends JFrame implements ActionListener{
    JTextField t;
    JLabel l;
    JButton b1,b2;
    Products k;
    newtype(Products p)
   {
       k=p;
       setLayout(null);
       l=new JLabel("Enter Type");
       l.setBounds(50,50,100,20);
       l.setFont(new java.awt.Font("Gadugi", 1, 14));
       add(l);
       t=new JTextField();
       t.setBounds(150,50,200,20);
       t.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
       add(t);
       b1=new JButton("ADD");
       b1.setBounds(70,100,70,20);
       add(b1);
       b2=new JButton("Cancel");
       b2.setBounds(150,100,90,20);
       add(b2);
       b1.addActionListener(this);
       b2.addActionListener(this);
   
   }

    @Override
    public void actionPerformed(ActionEvent ae) {
    if(ae.getSource()==b1)
    {
       k.nitem=t.getText();
       JOptionPane.showMessageDialog(null, "Added");
    }
    if(ae.getSource()==b2)
    {
     this.dispose();   
    }
    }
}
