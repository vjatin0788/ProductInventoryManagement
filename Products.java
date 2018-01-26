import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
class Products extends JFrame implements ActionListener
{
    
submit aa; //submit class object declaration
String dmy="";
int s,flag=0;
//String array containing Labels and item 
String la[]={"Product ID","Name","Company","Quantity","Address","Product Type","Date","Cost"},bu[]={"Add","Cancel","Update","Delete"},ru[]={"Type_1","Type_2","Type_3","Type_4"};
String month[]={"January","Febuary","March","April","May","June","July","Augest","September","October","November","December"},year[]={"2015","2016","2017"};
static String nitem="";
String date[]=new String[31];
JButton[] b=new JButton[10];
JLabel[] l=new JLabel[11];
JLabel[] l1=new JLabel[10];
JTextField[] t=new JTextField[11];
JComboBox[] cm=new JComboBox[4];
JTable table;
JScrollPane p;
DefaultTableModel model;
int u=50,U=150,v=50,V=50,W=100,w=100,x=20,X=20;
Products()
{

for(int i=1;i<=30;i++)
{date[i-1]=Integer.toString(i);}

Object col[]={"Product ID","Name","Company","Quantity","Address","Product Type","Date","Cost"};

table=new JTable();

model=new DefaultTableModel();
model.setColumnIdentifiers(col);
table.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
table.setModel(model);

//table.setFont(new java.awt.Font("Gadugi", 1, 14));
}
public void products()
{
aa=new submit(this);
setLayout(null);
for(int lt=0;lt<7;lt++)
{
l[lt]=new JLabel(la[lt]);
l[lt].setBounds(u,v,w,x);
l[lt].setFont(new java.awt.Font("Gadugi", 1, 14));
if(lt<7)
add(l[lt]);
t[lt]=new JTextField();
t[lt].setBounds(U,V,W,X);

t[lt].setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
if(lt<5)
add(t[lt]);
if(lt==0)
{
l[7]=new JLabel(la[7]);
l[7].setFont(new java.awt.Font("Gadugi", 1, 14));
l[7].setBounds(480,v,w,x);
t[7]=new JTextField();
t[7].setBounds(580,V,W,X);

t[7].setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
add(l[7]);
 add(t[7]);
}
if(lt==5)
{
cm[0]=new JComboBox(ru);
cm[0].setBounds(U,V,W,X);
add(cm[0]);
cm[0].addItem(nitem);
}
if(lt==6)
{
cm[1]=new JComboBox(date);
cm[1].setBounds(U,V,50,20);
add(cm[1]);
cm[2]=new JComboBox(month);
cm[2].setBounds(210,V,100,20);
add(cm[2]);
cm[3]=new JComboBox(year);
cm[3].setBounds(320,V,100,20);
add(cm[3]);
}
if(lt==0 || lt==2 || lt==4 || lt==6)
{
u=U+110;
U=u+110;
}
if(lt==1 || lt==3 || lt==5 || lt==7)
{
v=v+40;
V=V+40;
u=50;U=150;
}
}
u=80;v=v+40;
for(int bt=0;bt<5;bt++)
{
 if(bt<4)   
 {
     b[bt]=new JButton(bu[bt]);
 
b[bt].setBounds(u,v,80,20);
add(b[bt]);
b[bt].addActionListener(this);
 }
 else if(bt==4)
 {
     b[8]=new JButton("Load");
     b[8].setBounds(u,v,80,20);
     add(b[8]);
     b[8].addActionListener(this);
  }
 u=u+90;
}
v=v+80;
p=new JScrollPane(table);
p.setBounds(50,v,800,100);
table.setFillsViewportHeight(true);
add(p);
v=v+120;
b[5]=new JButton("Edit");
b[6]=new JButton("Delete");
b[5].setBounds(200,v,100,20);
b[6].setBounds(310,v,100,20);
b[7]=new JButton("Submit");
b[7].setBounds(420,v,100,20);
add(b[5]);
add(b[6]);
add(b[7]);
b[5].addActionListener(this);
b[6].addActionListener(this);
b[7].addActionListener(this);

}
public void actionPerformed(ActionEvent ae)
{
Object row[]=new Object[8]; 
if(ae.getActionCommand().equals("Add"))
{
    if(t[0].getText().equals("") || t[1].getText().equals("") || t[2].getText().equals("") || t[3].getText().equals("") || t[4].getText().equals(""))
    {
        JOptionPane.showMessageDialog(null, "Please enter reqd. fields");
    }
        
else if(flag==1)
{
    dmy=cm[1].getSelectedItem()+" "+cm[2].getSelectedItem()+" "+cm[3].getSelectedItem();
for(int i=0;i<5;i++)
{
table.getModel().setValueAt(t[i].getText(),s,i);
}
table.getModel().setValueAt(cm[0].getSelectedItem(),s,5);
table.getModel().setValueAt(dmy,s,6);
table.getModel().setValueAt(t[7].getText(),s,7);

flag=0;
t[0].setEnabled(false);
t[1].setEnabled(false);
t[2].setEnabled(false);
t[3].setEnabled(false);
t[4].setEnabled(false);
t[5].setEnabled(false);
t[7].setEnabled(false);
cm[0].setEnabled(false);
cm[1].setEnabled(false);
cm[2].setEnabled(false);
cm[3].setEnabled(false);
}
else
{
for(int i=0;i<5;i++)
{
row[i]=t[i].getText();
}
dmy=cm[1].getSelectedItem()+" "+cm[2].getSelectedItem()+" "+cm[3].getSelectedItem();
row[5]=cm[0].getSelectedItem();
row[6]=cm[1].getSelectedItem()+" "+cm[2].getSelectedItem()+" "+cm[3].getSelectedItem();
row[7]=t[7].getText();
model.addRow(row);

t[0].setEnabled(false);
t[1].setEnabled(false);
t[2].setEnabled(false);
t[3].setEnabled(false);
t[4].setEnabled(false);
t[5].setEnabled(false);
t[7].setEnabled(false);
cm[0].setEnabled(false);
cm[1].setEnabled(false);
cm[2].setEnabled(false);
cm[3].setEnabled(false);
}
for(int i=0;i<5;i++)
{
t[i].setText("");
}
t[7].setText("");
cm[0].setSelectedItem("Type 1");
cm[1].setSelectedItem("1");
cm[2].setSelectedItem("January");
cm[3].setSelectedItem("2015");

}
//delete
 if(ae.getSource()==b[3])
{
s=table.getSelectedRow();
if(s>=0)
model.removeRow(s);
t[0].setEnabled(true);
t[1].setEnabled(true);
t[2].setEnabled(true);
t[3].setEnabled(true);
t[4].setEnabled(true);
t[5].setEnabled(true);
t[7].setEnabled(true);
cm[0].setEnabled(true);
cm[1].setEnabled(true);
cm[2].setEnabled(true);
cm[3].setEnabled(true);
}

if(ae.getActionCommand().equals("Edit"))
{
int s=table.getSelectedRow();
if(s>=0)
{
for(int i=0;i<5;i++)
{
t[i].setText(table.getModel().getValueAt(s,i).toString());
}
t[7].setText(table.getModel().getValueAt(s,7).toString());
cm[0].setSelectedItem(table.getModel().getValueAt(s,5).toString());
flag=1;
String temp=table.getModel().getValueAt(s,6).toString()+";";
String temp1[]=new String[4];
temp1[0]="";
int count=0;
for(int j=0;j<temp.length();j++)
{
char c=temp.charAt(j);
if(c==' ' && count==0)
{
temp1[1]=temp1[0];
count++;
}
if(c==' ' && count==1)
{
temp1[2]=temp1[0];
count++;
}
if(c==';')
{
temp1[3]=temp1[0];
}
else
temp1[0]=temp1[0]+c;
}
System.out.println(temp1[1]);

cm[1].setSelectedItem(temp1[1]);
cm[2].setSelectedItem(temp1[2]);
cm[3].setSelectedItem(temp1[3]);
//enabling ttextfield
t[0].setEnabled(true);
t[1].setEnabled(true);
t[2].setEnabled(true);
t[3].setEnabled(true);
t[4].setEnabled(true);
t[5].setEnabled(true);
t[7].setEnabled(true);
cm[0].setEnabled(true);
cm[1].setEnabled(true);
cm[2].setEnabled(true);
cm[3].setEnabled(true);
}
}

if(ae.getActionCommand().equals("Submit"))
{
s=table.getSelectedRow();
aa.add(s);

//enabling textfield
t[0].setEnabled(true);
t[1].setEnabled(true);
t[2].setEnabled(true);
t[3].setEnabled(true);
t[4].setEnabled(true);
t[5].setEnabled(true);
t[7].setEnabled(true);
cm[0].setEnabled(true);
cm[1].setEnabled(true);
cm[2].setEnabled(true);
cm[3].setEnabled(true);
}

if(ae.getActionCommand().equals("Load"))
{
aa.load();
    
}
if(ae.getSource()==b[6])
{
s=table.getSelectedRow();
aa.delete();
model.removeRow(s);    
}

if(ae.getActionCommand().equals("Update"))
{
    
s=table.getSelectedRow();  
aa.update();
}
}
}