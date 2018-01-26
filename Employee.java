
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
class Employee extends JFrame implements ActionListener,FocusListener
{
    
    Connection cn=null; 
    ResultSet rs=null;
    Statement st=null;
    String str[]={"Name","Password","ReType pass","Contact","Gender","Address"};
    private JTextField[] t=new JTextField[10];
    JLabel[] l=new JLabel[10];
    JButton b[]=new JButton[2];
    JTextArea a=new JTextArea(); 
    private JPasswordField[] p=new JPasswordField[10];
    JRadioButton r[]=new JRadioButton[2];
    ButtonGroup bg=new ButtonGroup();
    public Employee()
    {
        setLayout(null);
        int u=100,U=200,v=100;
        for(int i=0;i<8;i++)
        {
            if(i<6)
            {
            l[i]=new JLabel(str[i]);
            l[i].setFont(new java.awt.Font("Gadugi", 1, 14));
            l[i].setBounds(u,v,200,20);
            add(l[i]);
            }
            if(i==2 || i==1)
            {
                p[i]=new JPasswordField();
                p[i].setBounds(U,v,200,20);
                p[i].setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
                add(p[i]);
                p[i].addFocusListener(this);
            }
            if(i==0 || i==3) 
            {    
            t[i]=new JTextField();
            t[i].setBounds(U,v,200,20);
            t[i].setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
            add(t[i]);
            }
            if(i==2)
            {
                l[6]=new JLabel();
                l[6].setBounds(410,v,200,20);
                add(l[6]);
            }
            if(i==4)
            {
                r[0]=new JRadioButton("Male");
                r[0].setBounds(U,v,100,20);
                add(r[0]);
                r[1]=new JRadioButton("Female");
                r[1].setBounds(300,v,100,20);
                add(r[1]);
                bg.add(r[0]);
                bg.add(r[1]);
                
            }
            if(i==5)
            {
                a.setBounds(U,v,200,70);
                a.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
                add(a);
            }
            if(i==7)
            {
                b[0]=new JButton("OK");
                b[0].setBounds(200,v,90,20);
                b[0].setEnabled(false);
                add(b[0]);
                b[0].addActionListener(this);
                b[1]=new JButton("Cancel");
                b[1].setBounds(310,v,90,20);
                add(b[1]);
                b[1].addActionListener(this);
            }
            v=v+40;
            }
        }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        String temp="";
        if(r[0].isSelected()==true)
        {
            temp=r[0].getActionCommand();
        }
        else if(r[1].isSelected()==true)
        {
            temp=r[1].getActionCommand();
        }
        if(ae.getActionCommand().equals("OK"))
        {
              
            try{
         Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        int tt=st.executeUpdate("insert into emp values('"+t[0].getText()+"','"+p[1].getText()+"','"+p[2].getText()+"','"+t[3].getText()+"','"+temp+"','"+a.getText()+"')");
        if(tt>0)
            JOptionPane.showMessageDialog(null,"Employee Added");
      }
      catch(Exception e){
      System.out.println("bekar ");
      }
        
        }
        else if(ae.getActionCommand().equals("Cancel"))
        {
            this.dispose();
        }
        }

    @Override
    public void focusGained(FocusEvent e) {
    
    
    }

    @Override
    public void focusLost(FocusEvent e) {
            if(e.getSource()==p[2])
        {
   if(p[2].getText().equals(p[1].getText()))
            {
                l[6].setText("Password matched");
                b[0].setEnabled(true);
            }
   else 
   {
       l[6].setText("Password does not match");
       b[0].setEnabled(false);
   }
        }
    
    }
        }

 
    

   

