
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
class emplist extends JFrame implements ActionListener{
    Connection cn=null; 
    ResultSet rs=null;
    Statement st=null; 
    JScrollPane p;
    DefaultTableModel model;
    JTable table;
    
    JButton b=new JButton("Find");
    JLabel l=new JLabel("Sort by:");
    Object col[]={"Name","Contact","Gender","Address"};
    String list[]={"all","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    JComboBox c;
    emplist()
    {
        c=new JComboBox(list);
        setLayout(null);
        l.setBounds(0,50,100,20);
        add(l);
        c.setBounds(50,50,50,20);
        add(c);
        b.setBounds(160,50,100,20);
        add(b);
        b.addActionListener(this);
        table=new JTable();
                model=new DefaultTableModel();
                model.setColumnIdentifiers(col);
                table.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
                table.setModel(model);
                //table.setEnabled(false);
                p=new JScrollPane(table);
                p.setBounds(10,100,600,500);
                table.setFillsViewportHeight(true);
                add(p);     
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    Object row[]=new Object[4];
    try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select * from clients where name like '"+c.getSelectedItem()+"%' order by name asc");
        System.out.println("working");
        while(rs.next())          
        {
            
          row[0]=rs.getString(1);
          row[1]=rs.getString(4);
          row[2]=rs.getString(5);
          row[3]=rs.getString(6);
          model.addRow(row);  
            
        }
        }
        catch(Exception er){System.out.println("fail");}
    }
}
class rn1
{
public static void main(String arhs[])
    {
        emplist e=new emplist();
        e.setSize(650,650);
        e.setVisible(true);
    }
}