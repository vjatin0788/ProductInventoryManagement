import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
class First extends JFrame implements ActionListener
{
notify n;
Products p=new Products();
JMenuBar mb;
JMenu m1,m2,m3,m4,m5;
JMenuItem i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12;
JButton b1,b2,b3;
public void first()
{
n=new notify();
T t=new T(n);
t.start();
  
setLayout(null);
b1=new JButton("Sign Out");
b1.setBounds(10,30,100,20);
add(b1);
mb=new JMenuBar();
mb.setBounds(0,0,1090,20);
add(mb);
b2=new JButton("Search");  
b2.setBounds(1090,0,100,20);
add(b2);
b2.addActionListener(this);
b3=new JButton("Notification");
b3.setBounds(1000,50,100,20);
add(b3);
b3.addActionListener(this);
m1=new JMenu("File  ");
m2=new JMenu("Clients  ");
m3=new JMenu("Products  ");
m4=new JMenu("Employee  ");
m5=new JMenu("Type  ");
mb.add(m1);mb.add(m2);mb.add(m3);mb.add(m4);mb.add(m5);
JMenuItem i1=new JMenuItem("New");
JMenuItem i2=new JMenuItem("Save");
JMenuItem i3=new JMenuItem("Exit");
JMenuItem i4=new JMenuItem("New");
JMenuItem i5=new JMenuItem("Open");
JMenuItem i6=new JMenuItem("Add");
JMenuItem i7=new JMenuItem("Delete");
JMenuItem i8=new JMenuItem("Update");
JMenuItem i9=new JMenuItem("Bill");
JMenuItem i10=new JMenuItem("New Employee");
JMenuItem i11=new JMenuItem("List");
JMenuItem i12=new JMenuItem("Add Type");
m1.add(i1);m1.add(i2);m1.add(i3);
m2.add(i4);m2.add(i5);m2.add(i9);
m3.add(i6);m3.add(i7);m3.add(i8);
m4.add(i10);m4.add(i11);
m5.add(i12);
i1.addActionListener(this);
i2.addActionListener(this);
i3.addActionListener(this);
i4.addActionListener(this);
i5.addActionListener(this);
i6.addActionListener(this);
i7.addActionListener(this);
i8.addActionListener(this);
i9.addActionListener(this);
i10.addActionListener(this);
i11.addActionListener(this);
i12.addActionListener(this);

}
public void actionPerformed(ActionEvent ae)
{
if(ae.getActionCommand().equals("Add"))
{
Products p=new Products();
p.products();
p.setVisible(true);
p.setSize(900,500);
p.b[2].setVisible(false);
p.b[8].setVisible(false);
p.b[6].setVisible(false);

}
if(ae.getActionCommand().equals("Delete"))
{
Products p=new Products();
p.products();
p.setVisible(true);
p.setSize(900,500);
p.b[0].setVisible(false);
p.b[1].setVisible(false);
p.b[2].setVisible(false);
p.b[3].setVisible(false);
p.b[5].setVisible(false);
p.b[7].setVisible(false);

p.t[0].setEnabled(false);
p.t[2].setEnabled(false);
p.t[3].setEnabled(false);
p.t[4].setEnabled(false);
p.t[7].setEnabled(false);
p.cm[1].setEnabled(false);
p.cm[2].setEnabled(false);
p.cm[3].setEnabled(false);
//p.t[].setEnabled(false);
//p.b[6].setVisible(false);

}
if(ae.getActionCommand().equals("Update"))
{

p.products();
p.setVisible(true);
p.setSize(900,500);
//p.b[2].setVisible(false);
p.b[3].setVisible(false);
p.b[6].setVisible(false);
p.b[7].setVisible(false);
p.t[0].setEnabled(false);
p.t[2].setEnabled(false);
p.t[3].setEnabled(false);
p.t[4].setEnabled(false);
p.t[7].setEnabled(false);
p.cm[1].setEnabled(false);
p.cm[2].setEnabled(false);
p.cm[3].setEnabled(false);
//p.t[].setEnabled(false);
//p.b[6].setVisible(false);

}
if(ae.getActionCommand().equals("New"))
{
    
        Clients c=new Clients();
        c.setSize(500,500);
        c.setVisible(true);
}
if(ae.getActionCommand().equals("New Employee"))
{
      Employee e=new Employee();
      e.setSize(600,600);
      e.setVisible(true);
}
if(ae.getActionCommand().equals("Bill"))
{
billing b=new billing();
b.setSize(900,600);
b.setVisible(true);

}
if(ae.getActionCommand().equals("Add Type"))
{
newtype n=new newtype(p);
n.setSize(400,200);
n.setVisible(true);
}
if(ae.getActionCommand().equals("Search"))
{
Productlist pl=new Productlist();
    pl.setVisible(true);
    pl.setSize(820,850);  
}
if(ae.getSource()==b3)
{
    n.setSize(715,620);
    n.setVisible(true); 
}
}
}
class ProInMan
{
public static void main(String arhs[])
{
First f=new First();
f.setVisible(true);
f.setSize(1206,800);
f.first();
}
}