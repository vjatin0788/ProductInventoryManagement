


import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
public class Clients extends JFrame implements ActionListener,FocusListener
{
    Connection cn=null; 
    ResultSet rs=null;
    Statement st=null;
    String str[]={"ID","Name","Contact no","Occupation","Address","Ok","Back"};
    JTextField[] t=new JTextField[7];
    JLabel[] l=new JLabel[7];
    JButton b[]=new JButton[2];
    JTextArea a=new JTextArea();
    Clients()
    {
        setLayout(null);
        int u=100,U=200,v=100;
     for(int i=0;i<7;i++)
     {
         if(i<5)
         {
         l[i]=new JLabel(str[i]);
         l[i].setFont(new java.awt.Font("Gadugi", 1, 14));
       
         l[i].setBounds(u,v,100,20);
         add(l[i]);
         }
         if(i<4)
         {
         t[i]=new JTextField();
         t[i].setBounds(U,v,200,20);
         t[i].setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
         add(t[i]);
         }
         if(i==4)
         { 
             
             a.setBounds(U,v,200,70);
             a.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
             add(a);
         }  
         if(i==5)
         {
             v=v+100;
             b[0]=new JButton(str[5]);
             b[0].setBounds(120,v,70,20);
             add(b[0]);
             b[1]=new JButton(str[6]);
             b[1].setBounds(200,v,70,20);
             add(b[1]);
             b[0].addActionListener(this);
             b[1].addActionListener(this);
            
         }
         v=v+40; 
         }
         t[0].addFocusListener(this);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("Ok"))
        {
        try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        int t1=st.executeUpdate("insert into clients values('"+t[0].getText()+"','"+t[1].getText()+"','"+t[2].getText()+"','"+t[3].getText()+"','"+a.getText()+"')");
        if(t1>0)
            JOptionPane.showMessageDialog(null,"Selected data Added");
      }
      catch(Exception e){
      System.out.println("bekar ");
      }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
    
    }

    @Override
    public void focusLost(FocusEvent e) {
     try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select * from clients");
        while(rs.next())
        {    
        if(rs.getString(1).equals(t[0].getText()))
        {    JOptionPane.showMessageDialog(null,"data already exist");t[0].setText("");break;
        }
      }
     }
      catch(Exception er){
      System.out.println("bekar ");
    }
}
}
class rn
{
    public static void main(String ags[])
    {
        Clients c=new Clients();
                c.setSize(600,600);
                c.setVisible(true);
    }
}
