import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
import javax.swing.event.*;
import java.sql.*;
class ebill extends JFrame implements ActionListener{
    JTable table;
    JScrollPane p;
    DefaultTableModel model;
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
    Object col[]={"Sr.","Product","Company","Quantity","Cost/Pc.","Amount"};
    int s=0;
    Bill1 bi;
    JButton b1;
    Connection cn=null; 
    ResultSet rs=null;
    Statement st=null;
 ebill(Bill1 b)
 {
     bi=b;
     setLayout(null);
     l1=new JLabel("Bill no.:");
     l1.setBounds(100,100,100,20);
     l1.setFont(new java.awt.Font("Gadugi", 1, 14));
     add(l1);
     l2=new JLabel("xxxxxxx");
     l2.setBounds(180,100,100,20);
     add(l2);  
     l3=new JLabel("Client ID:");
     l3.setBounds(350,100,100,20);
     l3.setFont(new java.awt.Font("Gadugi", 1, 14));
     add(l3);
     l4=new JLabel("yyyyyyy");
     l4.setBounds(440,100,100,20);
     add(l4);
     l5=new JLabel("Client Name:");
     l5.setBounds(100,150,100,20);
     l5.setFont(new java.awt.Font("Gadugi", 1, 14));
     add(l5);
     l6=new JLabel("zzzzzzz");
     l6.setBounds(200,150,100,20);
     add(l6);
     l7=new JLabel("Contact:");
     l7.setBounds(350,150,100,20);
     l7.setFont(new java.awt.Font("Gadugi", 1, 14));
     add(l7);
     l8=new JLabel("zzzzzzz");
     l8.setBounds(440,150,100,20);
      add(l8);
     table=new JTable();
     model=new DefaultTableModel();
     model.setColumnIdentifiers(col);
     table.setEnabled(false);
     table.setModel(model);
     p=new JScrollPane(table);
     p.setBounds(10,200,800,500);
     add(p);
     b1=new JButton("Generate");
     b1.setBounds(10,750,100,20);
     add(b1);
     b1.addActionListener(this);
     System.out.println(bi.cm[0].getSelectedItem());
     
 }

    @Override
    public void actionPerformed(ActionEvent e) {
    Object row1[]=new Object[7];
     s=bi.table.getRowCount();
     System.out.println(s);
     model.addRow(row1);
    
     for(int i=0;i<s;i++)
     {
     model.addRow(row1);
     table.getModel().setValueAt(i+1, i, 0);
     table.getModel().setValueAt(bi.table.getModel().getValueAt(i, 0), i, 1);
     table.getModel().setValueAt(bi.table.getModel().getValueAt(i, 1), i, 2);
     table.getModel().setValueAt(bi.table.getModel().getValueAt(i, 4), i, 3);
     table.getModel().setValueAt(bi.table.getModel().getValueAt(i, 2), i,4);
     table.getModel().setValueAt(Integer.parseInt(table.getModel().getValueAt(i, 3).toString())*Integer.parseInt(table.getModel().getValueAt(i, 4).toString()), i,5);
     }
     
    
     l2.setText(bi.t[0].getText());
     l4.setText(bi.cm [0].getSelectedItem().toString());
     l8.setText(bi.t[2].getText());
      try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select * from clients where ID="+bi.cm[0].getSelectedItem());
        rs.next();
        l6.setText(rs.getString(2));
      }
      catch(Exception er){
       System.out.println("fail");
      }
    
    }
    public void run1()
    {
        
    }
}
