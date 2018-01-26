import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.text.*;
class Productlist extends JFrame implements ActionListener,KeyListener{
    billing b=new billing();
    int s=0,aa=0,q=0,qa=0;
    int rows[]=new int[100];
    JTextComponent t;
    static int SNO=1,flag=0;
    JButton b1,b2,b3,b4;
    JLabel l1,l2,l3,l4,l5;
    JTextField t1=new JTextField();
    JComboBox c0,c1,c2,c3;
    Connection cn=null; 
    ResultSet rs=null;
    Statement st=null;
    String str[]={"","Below 500","500-5000","5000-15000","above 15000"};
    String col[]={"Product ID","Name","Company","Quantity","Address","Product Type","Date","Cost"};
    JScrollPane p;
    DefaultTableModel model;
    JTable table;
    Productlist()
    {
        
        setLayout(null);
        l5=new JLabel("No.of items in cart:");
        l5.setBounds(0,0,200,20);
        l5.setFont(new java.awt.Font("Gadugi", 1, 14));
        add(l5);
        t1.setBounds(130,0,30,20);
        add(t1);
        t1.setFont(new java.awt.Font("Gadugi", 1, 14));
        t1.setEnabled(false);
        c0=new JComboBox();
        c0.setBounds(350,50,200,20);
        add(c0);
        c0.addItem("");
        c0.setEditable(true);
        t=(JTextComponent)c0.getEditor().getEditorComponent();
        t.addKeyListener(this);
        l1=new JLabel("Type:");
        l1.setBounds(50,100,100,20);
        add(l1);
        b1=new JButton("Search");
        b1.setBounds(560,50,120,20);
        add(b1);
        b1.addActionListener(this);
        c1=new JComboBox();
        c1.setBounds(100,100,200,20);
        add(c1);
        c1.addItem("");
        c1.addActionListener(this);
        
         try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select * from PC");
        while(rs.next())          
        {
               c1.addItem(rs.getString(6));
               
               }
        
        }
      catch(Exception ee){
      System.out.println("bekar1 ");
    }   
        l2=new JLabel("Sort by:");
        l2.setBounds(350,100,100,20);
        add(l2);
        l3=new JLabel("Cost:");
        l3.setBounds(410,100,100,20);
        add(l3);
        c2=new JComboBox(str);
        c2.setBounds(480,100,200,20);
        add(c2);
         l4=new JLabel("Company:");
        l4.setBounds(410,150,100,20);
        add(l4);
        c3=new JComboBox();
        c3.setBounds(480,150,200,20);
        add(c3);
        c3.addItem("");
        try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select * from PC");
        
        while(rs.next())          
        {
               c3.addItem(rs.getString(3));
               
               }
        
        }
      catch(Exception ee){
      System.out.println("bekar 2");
    }
                table=new JTable();
                model=new DefaultTableModel();
                model.setColumnIdentifiers(col);
                table.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
                table.setModel(model);
                //table.setEnabled(false);
                p=new JScrollPane(table);
                p.setBounds(10,250,600,300);
                table.setFillsViewportHeight(true);
                add(p);     
         b2=new JButton("Done");
         b2.setBounds(0,0,100,20);
         add(b2);
         b2.addActionListener(this);
         b3=new JButton("Add To Cart");
         b3.setBounds(0,150,100,20);
         add(b3);
         b3.addActionListener(this);
         b4=new JButton("Cancel");
         b4.setBounds(0,350,100,20);
         add(b4);
         b4.addActionListener(this);
         
         
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    Object row[]=new Object[8];
    int x=model.getRowCount();
    if(!c1.getSelectedItem().toString().equals("") && c2.getSelectedItem().toString().equals("") && c3.getSelectedItem().toString().equals(""))
    {
        for(int j=0;j<x;j++)model.removeRow(j);
        try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select * from PC where ProductType='"+c1.getSelectedItem()+"' order by Name asc");
        while(rs.next())          
        {
          for(int i=0;i<8;i++)
          {
              
          row[i]=rs.getString(i+1);
          }
          model.addRow(row);  
            }
        c1.setSelectedItem("");
        c2.setSelectedItem("");
        c3.setSelectedItem("");
        
        }
        catch(Exception er){System.out.println("fail");}
                
    }
    else if(!c1.getSelectedItem().toString().equals("") && c2.getSelectedItem().toString().equals("") && !c3.getSelectedItem().toString().equals(""))
    {
        for(int j=0;j<x;j++)model.removeRow(j);
        try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select * from PC where ProductType='"+c1.getSelectedItem()+"' and Company='"+c3.getSelectedItem()+"' order by Name asc");
                
        while(rs.next())          
        {
          for(int i=0;i<8;i++)
          {
              
          row[i]=rs.getString(i+1);
          }
          model.addRow(row);  
            }
        c1.setSelectedItem("");
        c2.setSelectedItem("");
        c3.setSelectedItem("");
        
        }
        catch(Exception er){System.out.println("fail");}
                
    }
    else if(!c1.getSelectedItem().toString().equals("") && !c2.getSelectedItem().toString().equals("") && !c3.getSelectedItem().toString().equals(""))
    {
        for(int j=0;j<x;j++)model.removeRow(j);
        if(c2.getSelectedItem().toString().equals("Below 500"))
        {
              try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select * from PC where ProductType='"+c1.getSelectedItem()+"' and Company='"+c3.getSelectedItem()+"' and Cost<500 order by Name asc");
                
        while(rs.next())          
        {
          for(int i=0;i<8;i++)
          {
              
          row[i]=rs.getString(i+1);
          }
          model.addRow(row);  
            }
        
        }
        catch(Exception er){System.out.println("fail");}
        }
        else if(c2.getSelectedItem().toString().equals("500-5000"))
        {
              try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select * from PC where ProductType='"+c1.getSelectedItem()+"' and Company='"+c3.getSelectedItem()+"' and Cost>=500 and Cost<5000 order by Name asc");
                
        while(rs.next())          
        {
          for(int i=0;i<8;i++)
          {
         
          row[i]=rs.getString(i+1);
          }
          model.addRow(row);  
            }
        
        }
        catch(Exception er){System.out.println("fail");}
        }
        if(c2.getSelectedItem().toString().equals("5000-15000"))
        {
          try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select * from PC where ProductType='"+c1.getSelectedItem()+"' and Company='"+c3.getSelectedItem()+"' and Cost>=5000 and Cost<15000 order by Name asc");
                
        while(rs.next())          
        {
          for(int i=0;i<8;i++)
          {
            
          row[i]=rs.getString(i+1);
          }
          model.addRow(row);  
            }
        
        }
        catch(Exception er){System.out.println("fail");}
        
        }
        if(c2.getSelectedItem().toString().equals("above 15000"))
        {
          try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select * from PC where ProductType='"+c1.getSelectedItem()+"' and Company='"+c3.getSelectedItem()+"' and Cost>=15000  order by Name asc");
                
        while(rs.next())          
        {
          for(int i=0;i<8;i++)
          {
             
          row[i]=rs.getString(i+1);
          }
          model.addRow(row);  
            }
        
        }
        catch(Exception er){System.out.println("fail");}
        
        }
        c1.setSelectedItem("");
        c2.setSelectedItem("");
        c3.setSelectedItem("");
        
    }
    else if(!c0.getSelectedItem().toString().equals(""))
    {
      try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        System.out.println(c0.getSelectedItem());
        rs=st.executeQuery("select * from PC where Name='"+c0.getSelectedItem()+"' order by Name asc");
        c0.removeAllItems();
        while(rs.next())          
        {
          for(int i=0;i<8;i++)
          {
              
          row[i]=rs.getString(i+1);
          }
          model.addRow(row);  
            }
        c0.setSelectedItem("");
        }
        catch(Exception er){System.out.println("fail");}
          
    }
    else if(e.getSource()==b2)
    {
       
       aa=0;
        t1.setText(Integer.toString(aa));
       b.setVisible(true);
       b.setSize(900,600);
      /* b.object(b);
       b.l1.setText("plist");
       b.t[4].setVisible(false);
       b.cm[1].setVisible(false);
       b.cm[2].setVisible(false);
       b.cm[3].setVisible(false);
       b.l[4].setVisible(false);
       b.l[5].setVisible(false);
       b.l[6].setVisible(false);
       b.l[8].setVisible(false);
       */
    }
    else if(e.getActionCommand().equals("Add To Cart"))
    {
        Object row1[]=new Object[6];
        s=table.getSelectedRow();
        q=Integer.parseInt( JOptionPane.showInputDialog("Enter the no. of Quantities"));
        qa=Integer.parseInt(table.getModel().getValueAt(s,3).toString());
        if(q>qa)
        {
            JOptionPane.showMessageDialog(null,"Quantity not available");
        }
        else
        {
           
        
       b.model.addRow(row1);
      System.out.println(table.getModel().getValueAt(s, 1)); //swetaer
      System.out.println(table.getModel().getValueAt(s, 2)); //mantacarlo
      System.out.println(table.getModel().getValueAt(s, 7)); //cost
      System.out.println(table.getModel().getValueAt(s, 3)); //quantity in database
      String st=table.getModel().getValueAt(s, 2)+"-"+table.getModel().getValueAt(s, 1);
       b.table.getModel().setValueAt(SNO++, aa, 0);
       flag=1;
       b.table.getModel().setValueAt(/*table.getModel().getValueAt(s, 2)*/st, aa, 1);
       b.table.getModel().setValueAt(table.getModel().getValueAt(s, 7), aa, 3);
       b.table.getModel().setValueAt("^PC", aa, 4);
       //b.table.getModel().setValueAt(/*table.getModel().getValueAt(s, 3)*/q, aa, 2);
       b.table.getModel().setValueAt(q, aa, 2);
      // b.table.getModel().setValueAt(Integer.parseInt(table.getModel().getValueAt(s,3).toString())-q, aa,2);
       
      
       aa++;
       t1.setText(Integer.toString(aa));
        }
          
    }
    else if(e.getSource()==b4)
    {
        this.dispose();  
    }
    }
    

    @Override
    public void keyTyped(KeyEvent e) {
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
   
    }

    @Override
    public void keyReleased(KeyEvent e) {
   String text=t.getText();
          try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select * from PC where Name like '"+text+"%' order by Name asc");
        c0.removeAllItems(); 
        while(rs.next())          
        {
               c0.addItem(rs.getString(2));
               }
        c0.setPopupVisible(true);
        }
      catch(Exception ee){
      System.out.println("bekar ");
    }
        t.setText(text);
    }
    
}
class m
{
    public static void main(String args[])
    {
    Productlist pl=new Productlist();
    pl.setVisible(true);
    pl.setSize(700,700);        
}
}