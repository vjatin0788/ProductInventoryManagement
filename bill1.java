
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
import javax.swing.event.*;
import java.util.Date;
class Bill1 extends JFrame implements KeyListener,ActionListener,FocusListener
{  
    int gr=0;
    Bill1 bi;
    int s=0;
    int ll=0,flag=0,k=0;
    String text="";
    String temp[];//companies
    String temp1[];//type
    String temp2[];//products
    String temp3[];//rate
    String temp4[];//quantity
    Connection cn=null; 
    ResultSet rs=null;
    Statement st=null;
    JLabel[] l=new JLabel[10];
    JTextField[] t=new JTextField[6];
    JComboBox[] cm=new JComboBox[4];
    JButton b1,b2,b3; 
    JTable table;
    JScrollPane p;
    DefaultTableModel model;
    JTextComponent t1,t2,t3,t4;
    Component cc;
    static JLabel l1=new JLabel(""); 
    Object col[]={"Product Name","Company","Rate/pc.","Quantity","Quantity reqd","remaining"};
    Bill1()
    {
        setLayout(null);
        int u=100,U=200,v=100; 
        l1.setBounds(0,0,100,20);
        add(l1);
        for(int i=0;i<8;i++)
        {
            if(i==0)
            {
            l[0]=new JLabel("Bill no.");
            l[0].setFont(new java.awt.Font("Gadugi", 1, 14));
            l[0].setBounds(u,v,100,20);
            add(l[0]);
            t[0]=new JTextField();
            t[0].setBounds(200,v,200,20);
            t[0].setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
            add(t[0]);
            t[0].addFocusListener(this);
            l[1]=new JLabel("Date");
            l[1].setFont(new java.awt.Font("Gadugi", 1, 14));
            l[1].setBounds(410,v,100,20);
            add(l[1]);
            t[1]=new JTextField();
            t[1].setBounds(500,v,200,20);
            t[1].setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
            add(t[1]);
            String date=Integer.toString(new Date().getDate())+" "+Integer.toString(new Date().getMonth())+" "+Integer.toString(new Date().getYear());
            t[1].setText(date);
            t[1].setEditable(false);
            }
            if(i==1)
            {
            l[2]=new JLabel("Client ID");
            l[2].setFont(new java.awt.Font("Gadugi", 1, 14));
            l[2].setBounds(u,v,100,20);
            add(l[2]);
            cm[0]=new JComboBox();
            cm[0].setBounds(U,v,200,20);
            add(cm[0]);
            cm[0].setEditable(true);
            cc=cm[0].getEditor().getEditorComponent();
            t1=(JTextComponent)cc;
           t1.addKeyListener(new KeyAdapter() {
         public void ActionPerformed(ActionEvent e){}  
       public void keyReleased(java.awt.event.KeyEvent e) {
       text=t1.getText();
       try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select * from clients where ID like '"+t1.getText()+"%' order by ID asc");
        cm[0].removeAllItems(); 
        while(rs.next())          
        {
               cm[0].addItem(rs.getString(1));
               }
        
        }
      catch(Exception ee){
      System.out.println("bekar ");
    }
          
      t1.setText(text);        
            
        }
           
           });
            l[8]=new JLabel("Quantity");
            l[8].setFont(new java.awt.Font("Gadugi", 1, 14));
            l[8].setBounds(410,v,100,20);
            add(l[8]);
            t[4]=new JTextField();
            t[4].setBounds(500,v,200,20);
            t[4].setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
            add(t[4]);
            t[4].addFocusListener(this);
            }
            
            if(i==2)
            {
            l[3]=new JLabel("Contact");
            l[3].setFont(new java.awt.Font("Gadugi", 1, 14));
            l[3].setBounds(u,v,100,20);
            add(l[3]);
            t[2]=new JTextField();
            t[2].setBounds(U,v,200,20);
            t[2].setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
            add(t[2]);
                
                
            }
            if(i==3)
            {
            l[4]=new JLabel("Company");
            l[4].setFont(new java.awt.Font("Gadugi", 1, 14));
            l[4].setBounds(u,v,100,20);
            add(l[4]);
                cm[1]=new JComboBox();
                cm[1].setBounds(200,v,200,20);
                add(cm[1]);
                cm[1].addItem("");
                cm[1].setEditable(true);
                cc=cm[1].getEditor().getEditorComponent();
                t2=(JTextComponent)cc;
                t2.addKeyListener(new KeyAdapter() {
         public void ActionPerformed(ActionEvent e){}  
       public void keyReleased(java.awt.event.KeyEvent e) {
       text=t2.getText();
        if(cm[2].getSelectedItem().toString().equals("") && cm[3].getSelectedItem().toString().equals(""))
        {
         try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select * from PC where Company like '"+text+"%' order by Company asc");
        cm[1].removeAllItems(); 
        while(rs.next())          
        {
        if(rs.last())
        {
          ll=rs.getRow();
         break;
        }
        }
       System.out.println(ll);
       for(int i=1;i<=ll;i++)
       {
           rs.previous();
       }
       System.out.println(rs.getRow());
      
       temp=new String[ll] ;//companies
      for(int i=0;i<ll;i++)
            {
             temp[i]="";   
            }
      
       while(rs.next())          
        {
            
        for(int i=0;i<ll;i++)
            {
                
                if(rs.getString(3).equals(temp[i]))
                {
                    flag=1;
                }
               
            }
            if(flag==0)
            {
                cm[1].addItem(rs.getString(3));
                
            }
            flag=0;
            temp[k]=rs.getString(3);
            k++;
        }
       t2.setText(text);    
         }
         catch(Exception ee){}  
        }
      else if(!cm[2].getSelectedItem().toString().equals("") && !cm[3].getSelectedItem().toString().equals(""))
       {
       try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select * from PC where Company like '"+text+"%' and ProductType='"+cm[2].getSelectedItem()+"' and Name='"+cm[3].getSelectedItem()+"' order by Company asc");
        cm[1].removeAllItems(); 
        while(rs.next())          
        {
        if(rs.last())
        {
          ll=rs.getRow();
         break;
        }
        }
       System.out.println(ll);
       for(int i=1;i<=ll;i++)
       {
           rs.previous();
       }
      
      
       temp=new String[ll] ;//companies
    for(int i=0;i<ll;i++)
            {
             temp[i]="";   
            }
      
       while(rs.next())          
        {
            
        for(int i=0;i<ll;i++)
            {
                
                if(rs.getString(3).equals(temp[i]))
                {
                    flag=1;
                }
                
            }
            if(flag==0)
            {
                cm[1].addItem(rs.getString(3));
                
            }
            flag=0;
            temp[k]=rs.getString(3);
            k++;
        }
        }
         catch(Exception ee){}  
       }
        else if(!cm[2].getSelectedItem().toString().equals("") && cm[3].getSelectedItem().toString().equals(""))
        {
            try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select * from PC where Company like '"+text+"%' and ProductType='"+cm[2].getSelectedItem()+"' order by Company asc");
        cm[1].removeAllItems(); 
        while(rs.next())          
        {
        if(rs.last())
        {
          ll=rs.getRow();
         break;
        }
        }
      
       for(int i=1;i<=ll;i++)
       {
           rs.previous();
       }
      
      
       temp=new String[ll] ;//companies
      
      for(int i=0;i<ll;i++)
            {
             temp[i]="";   
             }
      
       while(rs.next())          
        {
            
        for(int i=0;i<ll;i++)
            {
                
                if(rs.getString(3).equals(temp[i]))
                {
                    flag=1;
                }
              
            }
            if(flag==0)
            {
                cm[1].addItem(rs.getString(3));
                
            }
            flag=0;
            temp[k]=rs.getString(3);
            k++;
        }
        }
         catch(Exception ee){} 
            
            
        
        }
        else if(cm[2].getSelectedItem().toString().equals("") && !cm[3].getSelectedItem().toString().equals(""))
        {
            try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select * from PC where Company like '"+text+"%' and Name='"+cm[3].getSelectedItem()+"' order by Company asc");
        cm[1].removeAllItems(); 
        while(rs.next())          
        {
        if(rs.last())
        {
          ll=rs.getRow();
         break;
        }
        }
      
       for(int i=1;i<=ll;i++)
       {
           rs.previous();
       }
      
      
       temp=new String[ll] ;//companies
       
      for(int i=0;i<ll;i++)
            {
             temp[i]="";   
             }
      
       while(rs.next())          
        {
            
        for(int i=0;i<ll;i++)
            {
                
                if(rs.getString(3).equals(temp[i]))
                {
                    flag=1;
                }
               
            }
            if(flag==0)
            {
                cm[1].addItem(rs.getString(3));
                
            }
            flag=0;
            temp[k]=rs.getString(3);
            k++;
        }
        }
         catch(Exception ee){} 
            
            
        
        }
              t2.setText(text);    
      
      }             
     }
    );
     l[5]=new JLabel("Type");
            l[5].setFont(new java.awt.Font("Gadugi", 1, 14));
            l[5].setBounds(410,v,100,20);
            add(l[5]);
                cm[2]=new JComboBox();
                cm[2].setBounds(500,v,200,20);
                add(cm[2]);
                cm[2].addItem("");
                cm[2].setEditable(true);
                cc=cm[2].getEditor().getEditorComponent();
                t3=(JTextComponent)cc;
                t3.addKeyListener(new KeyAdapter() {
         public void ActionPerformed(ActionEvent e){}  
       public void keyReleased(java.awt.event.KeyEvent e) {
       text=t3.getText();
       if(!cm[1].getSelectedItem().toString().equals("") && cm[3].getSelectedItem().toString().equals(""))
       { 
       try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select distinct * from PC where ProductType like '"+text+"%' and Company='"+cm[1].getSelectedItem()+"' order by ProductType asc");
        cm[2].removeAllItems(); 
        while(rs.next())          
        {
               cm[2].addItem(rs.getString(6));
               }
        
        }
      catch(Exception ee){
      System.out.println("bekar ");
    }
          
            }
       else if(cm[1].getSelectedItem().toString().equals("") && !cm[3].getSelectedItem().toString().equals(""))
       {
          try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select distinct * from PC where ProductType like '"+text+"%' and Name='"+cm[3].getSelectedItem()+"' order by ProductType asc");
        cm[2].removeAllItems(); 
        while(rs.next())          
        {
               cm[2].addItem(rs.getString(6));
               }
        
        }
      catch(Exception ee){
      System.out.println("bekar ");
    }   
           
           
       }
       else if(cm[1].getSelectedItem().toString().equals("") && cm[3].getSelectedItem().toString().equals(""))
       {
           
            try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select distinct * from PC where ProductType like '"+text+"%' order by ProductType asc");
        cm[2].removeAllItems(); 
        while(rs.next())          
        {
               cm[2].addItem(rs.getString(6));
               
               }
        
        }
      catch(Exception ee){
      System.out.println("bekar ");
    }   
   }
         else if(!cm[1].getSelectedItem().toString().equals("") && !cm[3].getSelectedItem().toString().equals(""))
       {
           System.out.println("hello");
            try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select distinct * from PC where ProductType like '"+text+"%' and Company='"+cm[1].getSelectedItem()+"' and Name='"+cm[3].getSelectedItem()+"' order by ProductType asc");
        cm[2].removeAllItems(); 
        while(rs.next())          
        {
               cm[2].addItem(rs.getString(6));
               
               }
        
        }
      catch(Exception ee){
      System.out.println("bekar ");
    }   
   }
       t3.setText(text);        
      
      }
     }        
   );
            
            }
            if(i==4)
            {
            l[6]=new JLabel("Select Product");
            l[6].setFont(new java.awt.Font("Gadugi", 1, 14));
            l[6].setBounds(u,v,100,20);
            add(l[6]);
                cm[3]=new JComboBox();
                cm[3].setBounds(U,v,200,20);
                add(cm[3]);
                cm[3].setEditable(true);
                cc=cm[3].getEditor().getEditorComponent();
                cm[3].addItem("");
                t4=(JTextField)cc;
        t4.addKeyListener(new KeyAdapter() {
         public void ActionPerformed(ActionEvent e){}  
       public void keyReleased(java.awt.event.KeyEvent e) {
       text=t4.getText();
       if(!cm[1].getSelectedItem().toString().equals("") && !cm[2].getSelectedItem().toString().equals(""))
       {
        try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select distinct * from PC where Name like '"+text+"%' and Company='"+cm[1].getSelectedItem()+"' and ProductType='"+cm[2].getSelectedItem()+"' order by Name asc");
        cm[3].removeAllItems(); 
        while(rs.next())          
        {
               cm[3].addItem(rs.getString(2));
               }
        
        }
      catch(Exception ee){
      System.out.println("bekar ");
    }
           }
      else if(cm[1].getSelectedItem().toString().equals("") && cm[2].getSelectedItem().toString().equals(""))
       {
       try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select distinct * from PC where Name like '"+text+"%' order by Name asc");
        cm[3].removeAllItems(); 
        while(rs.next())          
        {
               cm[3].addItem(rs.getString(2));
               }
        
        }
      catch(Exception ee){
      System.out.println("bekar ");
    }
       }
       else if(cm[1].getSelectedItem().toString().equals("") && !cm[2].getSelectedItem().toString().equals(""))
       {
          try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select distinct * from PC where Name like '"+text+"%' and ProductType='"+cm[2].getSelectedItem()+"' order by Name asc");
        cm[3].removeAllItems(); 
        while(rs.next())          
        {
               cm[3].addItem(rs.getString(2));
               }
        
        }
      catch(Exception ee){
      System.out.println("bekar ");
    }   
       }
        else if(!cm[1].getSelectedItem().toString().equals("") && cm[2].getSelectedItem().toString().equals(""))
       {
        try
        {
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select distinct * from PC where Name like '"+text+"%' and Company='"+cm[1].getSelectedItem()+"' order by Name asc");
        cm[3].removeAllItems(); 
        while(rs.next())          
        {
               cm[3].addItem(rs.getString(2));
               }
        
         }
        catch(Exception qe)
        {} 
       }
       
       t4.setText(text);        
       
        }
        }        
        );          
            }
            if(i==5)
            {
                table=new JTable();
                model=new DefaultTableModel();
                model.setColumnIdentifiers(col);
                table.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
                table.setModel(model);
                
                p=new JScrollPane(table);
                p.setBounds(50,v,800,150);
                table.setFillsViewportHeight(true);
                add(p);
            }
            if(i==6)
            {
                v=v+150;
            l[7]=new JLabel("Net");
            l[7].setFont(new java.awt.Font("Gadugi", 1, 14));
            l[7].setBounds(600,v,100,20);
            add(l[7]);
            t[3]=new JTextField();
            t[3].setBounds(650,v,200,20);
            t[3].setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
            add(t[3]); 
            b3=new JButton("Add");
            b3.setBounds(200,v,100,20);
            add(b3);
            b3.addActionListener(this);
            b1=new JButton("Calculate");
            b1.setBounds(400,v,100,20);
            add(b1);
            b1.addActionListener(this);
            }
            if(i==7)
            {
            
            b2=new JButton("Save");
            b2.setBounds(750,v,100,20);
            add(b2);
            b2.addActionListener(this);
            }
            v=v+40;
        }
    
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
      int q=0,rq=0,u=0;
      Object row[]=new Object[6]; 
      s=table.getSelectedRow();
      if(ae.getSource()==b3)
      {
        row[0]=cm[3].getSelectedItem();
        row[1]=cm[1].getSelectedItem(); 
        
        
        try
        {
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select distinct * from PC where Name like '"+cm[3].getSelectedItem()+"' and Company='"+cm[1].getSelectedItem()+"' and ProductType='"+cm[2].getSelectedItem()+"'");
        rs.next();
        row[2]=rs.getString(8);
        q=Integer.parseInt(rs.getString(4).toString());
        row[3]=rs.getString(4);
        if(Integer.parseInt(t[4].getText())<q)
        {
         row[4]=t[4].getText();
         rq=q-(Integer.parseInt(t[4].getText()));
        row[5]=rq;
        model.addRow(row);
       }
        else 
        {
            model.addRow(row);
            JOptionPane.showMessageDialog(null,"Quantity not available");
            String qq=JOptionPane.showInputDialog("Enter the quantity?");
            table.getModel().setValueAt(qq,table.getRowCount()-1, 4);
            rq=q-(Integer.parseInt(qq));
            table.getModel().setValueAt(rq,table.getRowCount()-1, 5);
            }
       }
        catch(Exception qe)
        {}   
        
        try
        {
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        
        u=st.executeUpdate("update PC set Quantity='"+rq+"' where Name='"+cm[3].getSelectedItem()+"' and Company='"+cm[1].getSelectedItem()+"'");
        if(u>0)
        JOptionPane.showMessageDialog(null,"Selected data updated");
         }
        catch(Exception qe)
        {
           System.out.println("fail"); 
        } 
        
        cm[1].removeAllItems();
        cm[1].addItem("");
        cm[2].removeAllItems();
        cm[2].addItem("");
        cm[3].removeAllItems();
        cm[3].addItem("");
      
       }
      if(ae.getActionCommand().equals("Calculate"))
      {
       int total=0;
       gr=table.getRowCount();
       
        for(int i=0;i<gr;i++)
       {
         if(table.getModel().getValueAt(i,4).toString().equals("")) 
         {
             JOptionPane.showMessageDialog(null,"Quantity not added enter again");
             model.removeRow(i);
             break;
         }
         total=Integer.parseInt(table.getModel().getValueAt(i,2).toString())*Integer.parseInt(table.getModel().getValueAt(i,4).toString())+total;   
       }
      
      t[3].setText(Integer.toString(total));
      }
      if(ae.getSource()==b2)
      {
          System.out.println(l1.getText());
         if(l1.getText().equals("plist"))
        { 
           int rw=bi.table.getRowCount();
          ebill e=new ebill(bi);
          e.setSize(850, 800);
          e.setVisible(true);
          System.out.println("plist");
          for(int i=0;i<rw;i++)
          {
               try
        {
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        
        int up=st.executeUpdate("update PC set Quantity='"+table.getModel().getValueAt(i, 5)+"' where Name='"+table.getModel().getValueAt(i, 0)+"' and Company='"+table.getModel().getValueAt(i, 1)+"'");
        if(up>0)
        JOptionPane.showMessageDialog(null,"Selected data updated");
         }
        catch(Exception qe)
        {
           System.out.println("fail"); 
        }
          }
        }
         else
         {
         ebill e=new ebill(this);
         e.setSize(850, 800);
         e.setVisible(true);
         System.out.println("direct");
         
        }
       
         
      }
    }
    public void object(Bill1 b)
    {
        bi=b;
    }

    @Override
    public void focusGained(FocusEvent e) {
   
    }

    @Override
    public void focusLost(FocusEvent e) {
    
    }
    }