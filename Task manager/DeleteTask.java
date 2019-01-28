package One;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeleteTask extends Component {


	DeleteTask()
	{
		final JFrame jf=new JFrame("Delete");
		jf.setVisible(true);
		jf.setSize(500,500);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel p=new JPanel(new GridLayout(2,1));
		jf.add(p);
		JButton da=new JButton("Delete by date and time");
		JButton sub=new JButton("Delete by subject");
		
		p.add(da);
	        da.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				final JFrame jp=new JFrame("Deletingtool");
				jp.setVisible(true);
				jp.setSize(500,500);
				jp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				JPanel p=new JPanel(new GridLayout(2,1,10,10));//outer panel
				jp.add(p);
				JPanel p1=new JPanel(new GridLayout(6,1,10,10));//inner day panel
				p.add(p1);
				JLabel dat=new JLabel("enter the day,month,year,hour,minute");
				p1.add(dat);
				final JTextField da=new JTextField("day");
				da.setSize(15, 15);
				p1.add(da);
				final JComboBox mon=new JComboBox();
				for(int i=1;i<=12;i++)
				{
					Integer j=new Integer(i);
					mon.addItem(j);	
				}
				p1.add(mon);
				final JComboBox year=new JComboBox();
				 for(int i=1994;i<=2050;i++)
				    {    
				    	Integer j = new Integer(i);
				    	year.addItem(j);
				    }
				 p1.add(year);
				 final JComboBox hour = new JComboBox();
				 for(int i = 0;i<=23;i++)
				 {
					 hour.addItem(Integer.valueOf(i));
				 }
				 p1.add(hour);
				 final JComboBox min = new JComboBox();
				 for(int i = 0 ;i<60;i++)
				 {
					 min.addItem(new Integer(i));
				 }
				 p1.add(min);
				 JButton sub=new JButton("submit");
				 p.add(sub);
				 sub.addActionListener(new ActionListener(){

					public void actionPerformed(ActionEvent arg0) {
						try {
							SubmitClicked(da,mon,year,min,hour);jp.setVisible(false);
							
						} catch (Exception e) {
							
							e.printStackTrace();
						}
						jf.setVisible(false);
						
					}
				 });
				 
					
				
			}
	        });
			p.add(sub); 
	        sub.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						final JFrame jp=new JFrame("searchingtool");
						jp.setVisible(true);
						jp.setSize(500,500);
						jp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						JPanel p=new JPanel(new GridLayout(2,1,30,50));//outer panel
						jp.add(p);
						jp.add(p);
						JPanel p1=new JPanel(new GridLayout(1,2,10,10));//inner day panel
						p.add(p1);
						JLabel suu=new JLabel("subject");
						p1.add(suu);
						final JTextField sub=new JTextField();
						p1.add(sub);
						JButton sub1=new JButton("submit");
						 p.add(sub1);
						 sub1.addActionListener(new ActionListener()
						 {
							 public void actionPerformed(ActionEvent e)
							 {
								 try {
									SubmitClicked2(sub);
									jp.setVisible(false);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								 jp.setVisible(false);
							 }

							
						 });
							 
						
					}
				
		
		});
		}
			

					private void SubmitClicked(JTextField da, JComboBox mon,
							JComboBox year, JComboBox min, JComboBox hour) throws Exception {
						int d,m,y,h,mi;
						d = Integer.parseInt(da.getText().toString());
						System.out.println(""+d);
						m = Integer.parseInt(mon.getSelectedItem().toString());
						System.out.println(""+m);
						y = Integer.parseInt(year.getSelectedItem().toString());
						System.out.println(""+y);
						h = Integer.parseInt(hour.getSelectedItem().toString());
						System.out.println(""+h);
						mi = Integer.parseInt(min.getSelectedItem().toString());
						System.out.println("this one"+mi);
						boolean flag = true;
						flag = Search(d,m,y,h,mi,flag);
						if(!flag)
							JOptionPane.showMessageDialog(null,"Appointment Found and Deleted");
						else
							JOptionPane.showMessageDialog(null,"Appointment Not Found");
					}

					private boolean Search(int d, int m, int y, int h, int mi, boolean flag) throws Exception {
						FileInputStream fin = new FileInputStream("Appointments");
						ObjectInputStream oin = new ObjectInputStream(fin);
						  FileOutputStream fout = new FileOutputStream("temp");
						  ObjectOutputStream oout = new ObjectOutputStream(fout);
						try
						{
							while(true)
							{
								Appointment a;
								a = (Appointment)oin.readObject();
								Calendar c = a.cal;
								int m1 = c.get(GregorianCalendar.MONTH);
							    int y1 = c.get(GregorianCalendar.YEAR);
								int d1 = c.get(GregorianCalendar.DAY_OF_MONTH);
								int h1 = c.get(GregorianCalendar.HOUR_OF_DAY);
								System.out.println(""+h1);
								int mi1 = c.get(GregorianCalendar.MINUTE);
								System.out.println(""+mi1);
								if(!(m1==m&&d1==d&&y1==y&&h1==h&&mi1==mi))
								{
							
									oout.writeObject(a);
								
								}
								else flag = false;
							}
						}
					    
						catch(EOFException e)
						{
							System.out.println("inside Exception");
							fout.close();
							 fout = new FileOutputStream("Appointments");
				         	  fout.write(new String().getBytes());
				         	  FileChannel in = new FileInputStream("temp").getChannel();
				         	  FileChannel out = new FileOutputStream("Appointments").getChannel();
				         	  out.transferFrom(in, 0, in.size());
				         	  fout = new FileOutputStream("temp");
				         	  fout.write(new String().getBytes());fout.close();
				               in.close();out.close();
						}
						return flag;
						
					}
					private void SubmitClicked2(JTextField sub) throws Exception
					{
						String s = sub.getText();
						FileInputStream fin = new FileInputStream("Appointments");
						ObjectInputStream oin = new ObjectInputStream(fin);
					    FileOutputStream fout = new FileOutputStream("temp");
					    ObjectOutputStream oout = new ObjectOutputStream(fout);
					    System.out.println(""+s);
						boolean flag = true;
						
						try
						{
							while(true)
							{   
								Appointment y = (Appointment)oin.readObject();
								System.out.println(""+y.sub);
								if(!s.equals(y.sub))
								{
									
									oout.writeObject(y);
								}else flag =false;
							}
						}
						catch(Exception e)
						{
							System.out.println("inside Exception");
							fout.close();
							 fout = new FileOutputStream("Appointments");
				         	  fout.write(new String().getBytes());
				         	  FileChannel in = new FileInputStream("temp").getChannel();
				         	  FileChannel out = new FileOutputStream("Appointments").getChannel();
				         	  out.transferFrom(in, 0, in.size());
				         	  fout = new FileOutputStream("temp");
				         	  fout.write(new String().getBytes());fout.close();
				               in.close();out.close();
						}
						if(!flag)
							JOptionPane.showMessageDialog(null,"Appointment Found and Deleted");
						else
							JOptionPane.showMessageDialog(null,"Appointment Not Found");
						
					}
				
			
	
		
	
}
	
	
