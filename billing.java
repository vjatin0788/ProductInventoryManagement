
import java.awt.event.*;
import java.awt.event.ActionListener.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel.*;
import javax.swing.text.*;
import java.sql.*;
import java.util.*;
import java.text.*;

class billing extends JFrame implements ActionListener,ItemListener
{
 
   int x,y,j,Btotal,u;
   static int SNO;
  static  public int rcheck,ccheck;
   static String Check[][]=new String[100][50];
   static String pid[]=new String[100];
    static String td1[]=new String[100]; 
    static String lbl[]=new String[5];
   static int Check1[][]=new int[100][50];
  static int h1=0;
 
  JButton b[]=new JButton[4];
  JComboBox cb[]=new JComboBox[4];  
Connection cn=null;
ResultSet rs=null;
Statement st=null;
Connection cn1=null;
ResultSet rs1=null;
Statement st1=null;
JTextComponent editor;
JTextComponent editor1;
JTextComponent editor2;
boolean hidePopupOnFocusLoss;
String str[]=new String[9];
String str3[]=new String[9];
String str2="";
String str4="";
String str5[]=new String[9];
String str6="";
//String Str2="";
int flag=0,tflag=0,kflag;
String company="",ProductType="",name=""; 
	String label[]={"Bill No.","Dealer Name","Phone","Category","Company","Date","Product Name","Net Total","Quantity","Bill"};
	String bb[]={"New Bill","Generate Bill","Calculate Bill","Add to bill"};
public DefaultTableModel model = new DefaultTableModel();
static public DefaultTableModel model2 = new DefaultTableModel();
public JTable table = new JTable(model);
    JLabel lb[]=new JLabel[15];
 public JTextField td[]=new JTextField[15];
  JScrollPane scrollPane = new JScrollPane(table);
  JCheckBox c=new JCheckBox("auto. Time & Date");
  public void billcall(){
     String st2=""; 
     int i=0;
  try                       
    {
     Class.forName("com.mysql.jdbc.Driver");
     cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
     st=cn.createStatement();
     rs=st.executeQuery("select max(billno) as maxbillno from sbill");
    rs.next();
 
     i=Integer.parseInt(rs.getString(1));
    System.out.println("value of bill="+i);
   st2=Integer.toString(i+1);
    td[0].setText(st2);
  }
  catch(Exception l){
//System.out.println(l);
      i=0;
  st2=Integer.toString(i+1);
 td[0].setText(st2);
  } ;
  }
   billing()
	{
            if(Productlist.flag==1){
         SNO=Productlist.SNO+1;
            Productlist.flag=0;
            }
            else
          SNO=1;
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model.addColumn("S.NO");
        model.addColumn("DESCRITION OF GOODS");
        model.addColumn("QUANTITY");
        model.addColumn("RATE");
        model.addColumn("UNIT");
        model.addColumn("TOTAL");
table.getColumnModel().getColumn(0).setPreferredWidth(50);
table.getColumnModel().getColumn(1).setPreferredWidth(270);
table.getColumnModel().getColumn(2).setPreferredWidth(105);
table.getColumnModel().getColumn(3).setPreferredWidth(95);
table.getColumnModel().getColumn(4).setPreferredWidth(105);
     //JScrollPane scrollPane = new JScrollPane(table);
    setLayout(null);
	 for(int i=0;i<9;i++)
	{
	  if(i<=3)
      b[i]=new JButton(bb[i]);
   
	  if(i<=5)
      td[i]=new JTextField(12);
	 if(i<3)
     cb[i]=new JComboBox();//for 3 combo box                
      
     lb[i]=new JLabel(label[i]);      		  
	 }//end of for loop 1

     int h=0,g=0; 
  for(int i=0;i<7;i++)
		{
	  if(i<5)
  lb[i].setBounds(120,60+h,120,130);  //end of if 1
	  else
    {
  lb[i].setBounds(360,60+g,120,130);
    g=g+120;
	     }        //end of else
  add(lb[i]);                         //(x,y,w,h)
  if(i<3)
			{
  td[i].setBounds(230,115+h,90,20);
      add(td[i]);
			}
  else if(i<4){
  td[i].setBounds(450,115,90,20);
    add(td[i]);
  }
  if(i<2){
  cb[i].setBounds(230,235+h,90,20);
  add(cb[i]);
        }
  else if(i<3)
			{
  cb[i].setBounds(460,235,90,20);
  add(cb[i]);
         }
    scrollPane.setBounds(40,320,700,300);
    add(scrollPane);
    h=h+40;	
b[0].setBounds(110,630,120,25);
b[1].setBounds(110+120,630,120,25);
b[2].setBounds(110+240,630,120,25);
b[3].setBounds(600,275,120,25);
 
lb[7].setBounds(530,575,120,130);
td[4].setBounds(610,630,120,25);
c.setBounds(445,150,150,25);
add(td[4]);
add(b[0]);//for calcualte bill
add(b[2]);
add(lb[7]);
add(b[1]);
add(b[3]); //for add to bill
add(c);
     add(lb[8]);
    lb[8].setBounds(360,220,120,130);
    td[5].setBounds(460,275,90,20);   //for quantity         
   add(td[5]);          
                }
 td[0].setEditable(false);
  billcall();
b[0].addActionListener(this);
b[1].addActionListener(this);
b[3].addActionListener(this);
b[2].addActionListener(this);
c.addItemListener(this);
  //starting of key listener
 cb[1].setEditable(true);
 editor = (JTextComponent) cb[1].getEditor().getEditorComponent(); 
   editor.addKeyListener(new KeyAdapter() {
        
       public void keyReleased(java.awt.event.KeyEvent e) {
       
       for(int i=0;i<9;i++)str[i]="";       
    int i=0;
   try                              //for company
    {
     Class.forName("com.mysql.jdbc.Driver");
     cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
     st=cn.createStatement();
     str2=editor.getText();
     if(tflag>=2){
          if(str4.equals("")&&str6.equals(""))
              tflag=0;
          else if(str4.equals("")||str6.equals(""))
              tflag=1;
          else
         rs=st.executeQuery("select distinct company from pc where company like '"+editor.getText()+"%' "+"and name= '"+str6+"' and ProductType ='"+str4+"' order by Company asc");
       }
      if(tflag==1)
     {
         if(str4.equals("")&&str6.equals(""))
              tflag=0;
       else if(str6.equals(""))
       rs=st.executeQuery("select distinct company from pc where company like '"+editor.getText()+"%' "+"and ProductType ='"+str4+"' order by Company asc");
         else if(str4.equals(""))
         rs=st.executeQuery("select distinct company from pc where company like '"+editor.getText()+"%' "+"and name ='"+str6+"' order by Company asc");
               }
   if(tflag==0){
      rs=st.executeQuery("select distinct company from pc where company like '"+editor.getText()+"%' order by Company asc");
    }
         while(rs.next())
     {
         str[i]=rs.getString(1);        
           i++;   
          
     } //end of while
        cb[1].removeAllItems();
        cb[1].setPopupVisible(false);  
    for(int j=0;j<i;j++)
    {
        cb[1].addItem(str[j]); 
    } 
              }      //end of try                                  
         catch(Exception l){System.out.println(l);}
    editor.setText(str2);   
    cb[1].setPopupVisible(true);      
    
       }// end of key listener  
   }  
              );

   cb[0].setEditable(true);          //for ProductType
   editor1 = (JTextComponent) cb[0].getEditor().getEditorComponent();
   editor1.addKeyListener(new KeyAdapter() {
           
  public void keyReleased(java.awt.event.KeyEvent e) {
       for(int i=0;i<9;i++)str3[i]="";      
    int i=0;
   try                                         
    {
     Class.forName("com.mysql.jdbc.Driver");
     cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
     st=cn.createStatement();
     str4=editor1.getText();
      if(tflag>=2){
          if(str2.equals("")&&str6.equals(""))
              tflag=0;
          else if(str2.equals("")||str6.equals(""))
              tflag=1;
          else
  rs=st.executeQuery("select distinct ProductType from pc where ProductType like '"+editor1.getText()+"%' "+"and name= '"+str6+"' and company ='"+str2+"' order by ProductType asc");
       }
     if(tflag==1)
     {
         if(str2.equals("")&&str6.equals(""))
              tflag=0;
       else if(str6.equals(""))
       rs=st.executeQuery("select distinct ProductType from pc where ProductType like '"+editor1.getText()+"%' "+"and company ='"+str2+"' order by ProductType asc");
       else if(str2.equals(""))
         rs=st.executeQuery("select distinct ProductType from pc where ProductType like '"+editor1.getText()+"%' "+"and name ='"+str6+"' order by ProductType asc");
               }
  if(tflag==0)  
      rs=st.executeQuery("select distinct ProductType from pc where ProductType like '"+editor1.getText()+"%' order by ProductType asc");
     while(rs.next())
     {
         str3[i]=rs.getString(1);    
           i++;   
          } //end of while
        cb[0].removeAllItems();
        cb[0].setPopupVisible(false);  
    for(int j=0;j<i;j++)cb[0].addItem(str3[j]);
 
              }      //end of try                                  
         catch(Exception l){} ;
         editor1.setText(str4); 
    cb[0].setPopupVisible(true);      
   
  }// end of key listener
        }
                
              );
    cb[2].setEditable(true);
   editor2 = (JTextComponent) cb[2].getEditor().getEditorComponent();
   editor2.addKeyListener(new KeyAdapter() {
           
  public void keyReleased(java.awt.event.KeyEvent e) {
       for(int i=0;i<9;i++)str5[i]="";       
    int i=0;
   try                                        //for name
    {
     Class.forName("com.mysql.jdbc.Driver");
     cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
     st=cn.createStatement();
     str6=editor2.getText();
    if(tflag>=2){
          if(str4.equals("")&&str2.equals(""))     
              tflag=0;   
         else if(str4.equals("")||str2.equals(""))
          tflag=1; 
         else 
       rs=st.executeQuery("select distinct name from pc where name like '"+editor2.getText()+"%' "+"and company= '"+str2+"' and ProductType ='"+str4+"' order by name asc");    
            }
     
  if(tflag==1)
     {
        if(str4.equals("")&&str2.equals(""))
              tflag=0;     
        else if(str4.equals(""))
         rs=st.executeQuery("select distinct name from pc where name like '"+editor2.getText()+"%' "+"and company ='"+str2+"' order by name asc");
        else if(str2.equals(""))
         rs=st.executeQuery("select distinct name from pc where name like '"+editor2.getText()+"%' "+"and ProductType ='"+str4+"' order by name asc");
         }
if(tflag==0)
  rs=st.executeQuery("select distinct name from pc where name like '"+editor2.getText()+"%' order by name asc");
     while(rs.next())
     {
         str5[i]=rs.getString(1);   
           i++;   
          } //end of while
        cb[2].removeAllItems();
        cb[2].setPopupVisible(false);  
    for(int j=0;j<i;j++)cb[2].addItem(str5[j]);
              }      //end of try                                  
         catch(Exception l){} 
         editor2.setText(str6); 
       cb[2].setPopupVisible(true);      
  }// end of key listener
        }     
              );

   editor2.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
             
    try{
      Class.forName("com.mysql.jdbc.Driver");
     cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
     st=cn.createStatement();
     rs=st.executeQuery("select * from pc where name ='"+editor2.getText()+"'");
     rs.next();
    if(rs.getString(1).isEmpty())
    {
 
    editor2.setText("");
    }
    else{

        str6=editor2.getText();
        tflag++;
    }
        }
    catch(Exception e1){editor2.setText("");}       
            }});
	editor.addFocusListener(new FocusAdapter() {
           public void focusLost(FocusEvent e) {        
    try{
      Class.forName("com.mysql.jdbc.Driver");
     cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
     st=cn.createStatement();
     rs=st.executeQuery("select * from pc where company ='"+editor.getText()+"'");
     rs.next();
    if(rs.getString(1).isEmpty())
    {

    }
    else{
    str2=editor.getText();
        tflag++;
    }
        }
    catch(Exception e1){editor.setText("");}
            }
        });

 editor1.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {


    try{
  
      Class.forName("com.mysql.jdbc.Driver");
     cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
     st=cn.createStatement();
     rs=st.executeQuery("select * from pc where ProductType ='"+editor1.getText()+"'");
     rs.next();
    if(rs.getString(1).isEmpty())
    {

    }
    else{
    

    str4=editor1.getText();
    tflag++;
    }
        }
    catch(Exception e1){editor1.setText("");} 
            }});
 
        } //end of constructor billing
public void actionPerformed(ActionEvent e){
    if(e.getActionCommand().equals("Generate Bill"))
  {
      model2=model;
      b[2].setEnabled(false);
 str2="";
 str4="";
 str6="";
 editor.setText("");
 editor1.setText("");
 editor2.setText("");
 lbl[0]=td[0].getText(); //bill no
  lbl[1]=td[1].getText();
   lbl[2]=td[2].getText();
    lbl[3]=td[3].getText();
    lbl[4]=td[4].getText();
   gbill obj11=new gbill();
 obj11.setSize(800,800);
 obj11.setVisible(true);
 //obj11.lb[0].setText("Bill NO: "+td1[0]);
 
         }
 if(e.getActionCommand().equals("Calculate Bill"))
 {
     Btotal=0;
     u=0;
   for (int i = 0; i < model.getRowCount(); i++){
    //u=(int)model.getValueAt(i,2)*(int)model.getValueAt(i,3);                                            // getValueAt(row, column)
       u=Integer.parseInt(model.getValueAt(i,2).toString()) *Integer.parseInt(model.getValueAt(i,3).toString());
     model.setValueAt(u,i,5);
    Btotal=u+Btotal; 
   }
  String g=Integer.toString(Btotal);
    rcheck= model.getRowCount();
    ccheck=model.getColumnCount();
 td[4].setText(g);
 td1[3]=td[4].getText();
 for(int i=0;i<rcheck;i++)
 {
 for(int j=0;j<ccheck;j++){
     if(j==0||j==2||j==3||j==5)
     Check1[i][j]=Integer.parseInt(model.getValueAt(i,j).toString());
else
 Check[i][j]=(String)model.getValueAt(i,j);    
 }
 }
  str2="";
 str4="";
 str6="";
 editor.setText("");
 editor1.setText("");
 editor2.setText("");
 }
 if(e.getActionCommand().equals("Add to bill"))
 {
     td1[0]=td[0].getText();//bill no.
     td1[1]=td[1].getText();//deler name
     td1[2]=td[3].getText();   //net total
     td1[3]=td[5].getText(); //quantity
    if(str2.equals("")||str4.equals("")||str6.equals("")||td[5].equals(""))
    { 
        JOptionPane.showMessageDialog(null,String.format("%s","fill all fields"));
    }
    else
       {
           try{
  
      Class.forName("com.mysql.jdbc.Driver");
     cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
     st=cn.createStatement();
     rs=st.executeQuery("select * from pc where ProductType ='"+str4+"' and company ='"+str2+"' and name ='"+str6+"'");
     rs.next();
     pid[flag]=rs.getString(6);
     flag++;
     x=Integer.parseInt(rs.getString(4));
     y=Integer.parseInt(td[5].getText());
     j=Integer.parseInt(rs.getString(8));     
           }
     catch(Exception e2){System.out.println(e);}
 
       String str1=str4+"-"+str2+"-"+str6;
        DefaultTableModel model1 = (DefaultTableModel) table.getModel();
        if(x>=y)
        {
            int w= x-y;
               //SNO++;
        //    int ftotal=y*j;
     model1.addRow(new Object[]{SNO++,str1,y,j,"^PC",""});
 try{ int t=st.executeUpdate("Update pc set quantity ="+w+" where ProductType ='"+str4+"' and company ='"+str2+"' and name ='"+str6+"'");   }
    catch(Exception r){}
      editor.setText("");
      editor1.setText("");
      editor2.setText("");
       str2="";
       str4="";
       str6="";
       td[5].setText("");
       x=0;y=0;w=0;j=0;
        }
        else{
            JOptionPane.showMessageDialog(null,String.format("%s","In suffiecient quantity "));
           JOptionPane.showMessageDialog(null,String.format("%s","Available Quantity ="+x));
        }     
      
       }
 }
if(e.getActionCommand().equals("New Bill"))
 {
    td[4].setText("");
     SNO=1;
     billcall();
   b[2].setEnabled(true);
 model.setRowCount(0);
 gbill obj=new gbill();
 obj.model1.setRowCount(0);
int returnValue =  JOptionPane.showConfirmDialog(null,"Want to make BIll of Same Dealer ?", "Want to make BIll of Same Dealer ?", JOptionPane.YES_NO_OPTION);
if(returnValue==1){
td[1].setText("");
td[2].setText("");
} 
 }//end of if
if(e.getActionCommand().equals("Save Bill"))
 {
try                              //for company
    {
     Class.forName("com.mysql.jdbc.Driver");
     cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
     st=cn.createStatement();
     int t11=st.executeUpdate("insert into sbill values ("+0+",'"+td1[2]+"','"+td1[1]+"',"+td1[3]+")");
     int t=0;
for(int i=0;i<rcheck;i++){
 System.out.println("inf ro");
 System.out.println(td1[3]);  //0-bill no 3-net total 1-dealer name 2-date
  System.out.println(td1[0]);  // product id  varchar//quantity int//bill no int//date date//client varchar//desc varchar//total int//net total 
   System.out.println(td1[1]);
    System.out.println(td1[2]);
  System.out.println((int)model2.getValueAt(0,2));
 // if(td1[0].isEmpty()){System.out.println("yes empty");
 // td1[0]="0";
 //}
 // int t=st.executeUpdate("insert into sbill values ('"+pid[i]+"',"+(int)model2.getValueAt(i,2)+","+0+",'"+td1[2]+"','"+td1[1]+"','"+model2.getValueAt(i,1)+"',"+(int)model2.getValueAt(i,5)+","+td1[3]+")");
   t=st.executeUpdate("insert into svdinfo values ("+td1[0]+",'"+pid[i]+"',"+(int)model2.getValueAt(i,2)+",'"+model2.getValueAt(i,1)+"',"+(int)model2.getValueAt(i,5)+")");
 //int t1=st.executeUpdate("insert into sbill values ('"+model.getValueAt(0,2)+"',"+(int)model.getValueAt(0,2)+",'"+td1[0]+"','"+td1[2]+"','"+td1[1]+"','"+model.getValueAt(0,1)+"',"+(int)model.getValueAt(0,5)+","+td1[3]+")");   
}//end of for
if(t11>0 && t>0){JOptionPane.showMessageDialog(null,String.format("%s","Saved Successfully!!"));}
}
catch(Exception w){System.out.println(w);}
     }//end of Save Bill
}//end of action performed
public void itemStateChanged(ItemEvent r){
java.util.Date dNow= new java.util.Date( );
 SimpleDateFormat ft=new SimpleDateFormat ("dd.MM.yyyy");
      td[3].setText(ft.format(dNow));
}//end of item listener

}// end of class billing 

class project
{
	public static void main(String[] args) 
	{
	billing obj=new billing();
	obj.setSize(800,800);
	obj.setVisible(true);
        }
}