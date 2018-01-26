/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.*;
import java.awt.event.ActionListener.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel.*;
import javax.swing.text.*;
import java.sql.*;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/**
 *
 * @author mani
 */
public class gbill extends JFrame implements ActionListener {
 Connection cn=null;
 ResultSet rs=null;
 Statement st=null;
     public DefaultTableModel model1 = new DefaultTableModel();
     public JTable table1 = new JTable(model1);
    JScrollPane scrollPane1 = new JScrollPane(table1);
    public DefaultTableModel model11 = new DefaultTableModel();
    public JTable table11 = new JTable(model11);
    JScrollPane scrollPane111 = new JScrollPane(table11);
    billing obj=new billing();
    int i,j,u,h;
    String load=new String();
    String bb[]={"Save Bill","Print Bill","Exit"};
    String label[]={"Bill No.","Dealer Name","Date","Phone","Ws Wholesale Services pvt.Lmt"};
      String label1[]={"Grand Total","----------------","123213","----------------"};
   JButton b2[]=new JButton[4];
    JLabel lb[]=new JLabel[5];
    JLabel lb1[]=new JLabel[5];
         gbill(){      
             //td[0] bill no
           setLayout(null);
           for(int i=0;i<3;i++)
	{
        
         b2[i]=new JButton(bb[i]); 
         b2[i].setBounds(130+h,650,120,25);
         add(b2[i]);
          h=h+150;
          if(i>=1)
          b2[i].addActionListener(this);
        }
        b2[0].addActionListener(new billing());
        for(int i=0;i<=4;i++){
        lb[i]=new JLabel(label[i]); 
        add(lb[i]);
        }
       for(int i=0;i<4;i++){
        lb1[i]=new JLabel(label1[i]); 
        add(lb1[i]);
        }
        lb[0].setBounds(100,150,170,130);
        lb[1].setBounds(400,150,170,130);
        lb[2].setBounds(100,190,170,130);
        lb[3].setBounds(400,190,170,130);
        lb1[0].setBounds(580,565,170,130);
        lb1[1].setBounds(670,555,170,130);
         lb1[2].setBounds(680,565,170,130);
          lb1[3].setBounds(670,570,170,130);
           lb[4].setBounds(200,70,220,130);
       lb[0].setText("Bill NO: "+ obj.lbl[0]);
        lb[1].setText("Dealer: "+ obj.lbl[1]);
         lb[2].setText("Invoice Date: "+ obj.lbl[3]);
          lb[3].setText("Contact No : "+ obj.lbl[2]);
         lb1[2].setText( obj.lbl[4]);
        scrollPane1.setBounds(40,300,700,300);   
        add(scrollPane1);
        scrollPane111.setBounds(40,100,700,300);   
        add(scrollPane111);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model1.addColumn("S.NO");
        model1.addColumn("DESCRITION OF GOODS");
        model1.addColumn("QUANTITY");
        model1.addColumn("RATE");
        model1.addColumn("UNIT");
        model1.addColumn("TOTAL");
table1.getColumnModel().getColumn(0).setPreferredWidth(50);
table1.getColumnModel().getColumn(1).setPreferredWidth(270);
table1.getColumnModel().getColumn(2).setPreferredWidth(105);
table1.getColumnModel().getColumn(3).setPreferredWidth(95);
table1.getColumnModel().getColumn(4).setPreferredWidth(105);
 for(i=0;i<obj.rcheck;i++)
 {model1.addRow(new Object[]{"","","","","",""});
 for(j=0;j<obj.ccheck;j++){
      if(j==0||j==2||j==3||j==5){
    model1.setValueAt(obj.Check1[i][j],i,j);
               } //end of if
      else{
     model1.setValueAt(obj.Check[i][j],i,j);
      }//en of else
          }         //end of for 1
              }     //end of for 2

         }//end of constructor
public void actionPerformed(ActionEvent e){
if(e.getActionCommand().equals("Print Bill"))
 {
   System.out.println("print bill");  
}//end of if
if(e.getActionCommand().equals("Exit"))
 {
   this.dispose();
}//end of if

}//end of actionPerformed
} //end of class

 
