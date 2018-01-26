import java.sql.*;
import javax.swing.JOptionPane;
public class submit {
    
    int row=0;
    Products k;
    String str[]=new String[8];
    Connection cn=null; 
    ResultSet rs=null;
    Statement st=null;
    public submit(Products p)
    {
        k=p;
    }
    public void add(int s)
    {
      for(int i=0;i<8;i++)
        str[i]=k.table.getModel().getValueAt(s,i).toString();
      try
      {
         
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        int t=st.executeUpdate("insert into PC values('"+str[0]+"','"+str[1]+"','"+str[2]+"','"+str[3]+"','"+str[4]+"','"+str[5]+"','"+str[6]+"','"+str[7]+"')");
        //int t=st.executeUpdate("insert into PCR values('"+str[1]+"','"+str[2]+"','"+str[5]+"','"+str[7]+"','"+str[3]+"'");
        if(t>0)
        JOptionPane.showMessageDialog(null,"Selected data Added");
      
      }
      catch(Exception e){
      System.out.println("bekar ");
      }
    }
    public void load()
    {
       String temo="";
        try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        rs=st.executeQuery("select * from PC where Name like '"+k.t[1].getText()+"%' order by Name asc");
         while(rs.next())
        {   
        k.model.insertRow(row, new Object[]{});
        for(int j=0;j<8;j++)
        {
            System.out.println(rs.getString(j+1));
            k.table.getModel().setValueAt(rs.getString(j+1),row,j);
            
        }
        row++;
        }
        }
      catch(Exception e){
      System.out.println("bekar ");
    }
        
    }
    public void delete()
    {
        String s1=k.table.getModel().getValueAt(k.s,5).toString();
        String s2=k.table.getModel().getValueAt(k.s,1).toString();
        System.out.println(s1+"    "+s2);
         try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        int t=st.executeUpdate("delete from PC where name='"+s2+"'");
        if(t>0)
            JOptionPane.showMessageDialog(null,"Selected data deleted");
            
         }
         catch(Exception e)
         {
             System.out.println("Not done");
         }
    }
    public void update()
    {
        String s1=k.table.getModel().getValueAt(k.s,0).toString();
        String s2=k.table.getModel().getValueAt(k.s,1).toString();
        String s3=k.table.getModel().getValueAt(k.s,2).toString();
        String s4=k.table.getModel().getValueAt(k.s,3).toString();
        String s5=k.table.getModel().getValueAt(k.s,4).toString();
        String s6=k.table.getModel().getValueAt(k.s,5).toString();
        String s7=k.table.getModel().getValueAt(k.s,6).toString();
        String s8=k.table.getModel().getValueAt(k.s,7).toString();
         System.out.println(s1+"    "+s2);
         try{
        Class.forName("com.mysql.jdbc.Driver");
        cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
        st=cn.createStatement(); 
        int t=st.executeUpdate("update PC set Name='"+s2+"',Company='"+s3+"',Quantity='"+s4+"',Address='"+s5+"',ProductType='"+s6+"',Date='"+s7+"',Cost='"+s8+"' where ProductID='"+s1+"'");
        if(t>0)
            JOptionPane.showMessageDialog(null,"Selected data updated");
        else
            JOptionPane.showMessageDialog(null,"Selected data not updated");
            
         }
         catch(Exception e)
         {
             System.out.println(e);
         }
    }
     
    
}
