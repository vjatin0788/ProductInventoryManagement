import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.text.*;

 class notify extends JFrame implements Runnable {
     T t;
    Connection cn=null; 
    ResultSet rs=null;
    Statement st=null;
    JScrollPane p;
    DefaultTableModel model;
    JTable table;
    Object col[]={"Product ID","Name","Company","Quantity","Address","Product Type","Date","Cost"};
    Object row[]=new Object[8];
    notify()
    {
      setLayout(null);
         table=new JTable();
                model=new DefaultTableModel();
                model.setColumnIdentifiers(col);
                table.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
                table.setModel(model);
                table.setEnabled(false);
                p=new JScrollPane(table);
                p.setBounds(0,0,700,600);
                table.setFillsViewportHeight(true);
                add(p);     
        
    }

    @Override
    public void run() {
     int count=0;
    try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select * from PC");
        while(rs.next())          
        {
         if(Integer.parseInt(rs.getString(4))<5)
         {
             for(int i=0;i<table.getRowCount();i++)
             { if(rs.getString(1).equals(table.getModel().getValueAt(i, 0)))
                 {
                     count++;
                 }
             }
             if(count==0)
             {
                 for(int i=0;i<8;i++)
                 row[i]=rs.getString(i+1);
                 model.addRow(row);
         
             }
         }   
        }
        }
        catch(Exception er){System.out.println("fail");}
    int s=table.getRowCount();
    JOptionPane.showMessageDialog(null,"Number of updates:"+s);
   
    t.resume();
    }
    public void obj(T t)
    {
        this.t=t;
    }
}
class T extends Thread
{
    notify n;
    T(notify n)
    {
        this.n=n;
    }
    public void run()
    {
        try{
        Thread t=new Thread(n);
        n.obj(this);
        t.start();
        sleep(100000); 
        //JOptionPane.showMessageDialog(null,"thread 1");
        T t1=new T(n);
        t1.start();
       }
        catch(Exception e){}
    }
}
class rn11
{
public static void main(String arhs[])
{
    notify n=new notify();
    T t=new T(n);
    t.start();
    n.setSize(800,800);
    n.setVisible(true);
}
}