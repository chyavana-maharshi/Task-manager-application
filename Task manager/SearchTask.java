package One;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchTask extends Component {

SearchTask()
{    
	final JFrame jf=new JFrame("Search");
	jf.setVisible(true);
	jf.setSize(500,500);
	jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	JPanel p=new JPanel(new GridLayout(3,1));
	jf.add(p);
	JButton da=new JButton("search by date");
	JButton sub=new JButton("search by subject");
	JButton bod=new JButton("search by message body");
	p.add(da);
	p.add(sub);
	p.add(bod);
	da.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
			Taskmaker.daysearch();
		}
	});
	sub.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{    
			Taskmaker p=new Taskmaker();
			p.subjectsearch();
			jf.setVisible(false);
		}
	});
	bod.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			try {
				Taskmaker p=new Taskmaker();
				p.msgsearch();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
}
}
class Taskmaker
{  String msg1="",subject="";
	
	static void daysearch()
	{
		final JFrame jp=new JFrame("searchingtool");
		jp.setVisible(true);
		jp.setSize(500,500);
		jp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel p=new JPanel(new GridLayout(2,1,30,50));//outer panel
		jp.add(p);
		JPanel p1=new JPanel(new GridLayout(4,1,10,10));//inner day panel
		p.add(p1);
		JLabel dat=new JLabel("enter the day,month,year");
		p1.add(dat);
		final JComboBox da=new JComboBox();
		for(int i=1;i<=31;i++)
		{
			da.addItem(i);
		}
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
		 JButton sub=new JButton("submit");
		 p.add(sub);
		 sub.addActionListener(new ActionListener()
		 {
			 boolean flag = false;
			public void actionPerformed(ActionEvent e)throws NullPointerException
			{  FileInputStream fos = null;
			try {
				fos = new FileInputStream("Appointments");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(fos);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				int c= Integer.parseInt(da.getSelectedItem().toString());
				int d=Integer.parseInt(mon.getSelectedItem().toString());
				int f=Integer.parseInt(year.getSelectedItem().toString());
				Appointment a;
				 try{
					 while(true)
					 {
					a=(Appointment)ois.readObject();
					if(a.cal.get(GregorianCalendar.DAY_OF_MONTH)==c&&a.cal.get(GregorianCalendar.MONTH)==d&&a.cal.get(GregorianCalendar.YEAR)==f)
					{
						flag = true; 
						JOptionPane.showMessageDialog(null, "the appointments is present\n"+Appointment.show(a));
					}
					 }
					
				 }
				
				 catch(Exception p)
				 {
					  File f1 =new File("Appointments");
						 if(f1.length()==0)
						 JOptionPane.showMessageDialog(null, "no appointments on the date");
						 if(flag == false)
							 JOptionPane.showMessageDialog(null, "no appointments on the date");
					 try {
						fos.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 }
				 jp.setVisible(false);
			}
				
				 });
				
		 	
	}
	void subjectsearch()
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
		final JTextField sub=new JTextField("");
		 sub.addKeyListener(new KeyListener(){
		    	public void keyPressed(KeyEvent e){
		    		
		    	}
		    	public void keyReleased(KeyEvent e){
		    		
		    	}
		    	public void keyTyped(KeyEvent e){
		    		char c = e.getKeyChar();
		    		if( c == 8)
		    		subject = subject.substring(0,subject.length()-1);	
		    		else
		    			subject = subject + c;	
		    	}
		    }
		    );
		p1.add(sub);
		JButton sub1=new JButton("submit");
		 p.add(sub1);
		 sub1.addActionListener(new ActionListener()
		 {
			 boolean flag =false;
			 public void actionPerformed(ActionEvent e)throws NullPointerException 
			 {
				 FileInputStream fos = null;
				try {
					fos = new FileInputStream("Appointments");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 ObjectInputStream ois = null;
				try {
					ois = new ObjectInputStream(fos);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Appointment a;
				 try{
					 while(true)
					 {
						a=(Appointment)ois.readObject();
						if(a.sub.equals(subject))
						{
							flag = true; 
							JOptionPane.showMessageDialog(null, "the appointments is present\n"+Appointment.show(a));
						}
					 }
					
					 }
					 catch(Exception p)
					 {   File f =new File("Appointments");
						 if(f.length()==0)
						 JOptionPane.showMessageDialog(null, "no appointments on the date");
						 if(flag == false)
							 JOptionPane.showMessageDialog(null, "no appointments on the date");
						 try {
							fos.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					 }
			 }

		 });
		 	
	}
	 void msgsearch()throws IOException
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
		JLabel mes=new JLabel("message");
		p1.add(mes);
		final JTextField mbod=new JTextField("");
		p1.add(mbod);
		 mbod.addKeyListener(new KeyListener(){
		    	public void keyPressed(KeyEvent e){
		    		
		    	}
		    	public void keyReleased(KeyEvent e){
		    		
		    	}
		    	public void keyTyped(KeyEvent e){
		    		char c = e.getKeyChar();
		    		if( c == 8)
		    		msg1 = msg1.substring(0,msg1.length()-1);	
		    		else
		    			msg1 = msg1 + c;	
		    	}
		    }
		    );
		 
		System.out.println(""+msg1);
		JButton sub1=new JButton("submit");
		 p.add(sub1);
		 sub1.addActionListener(new ActionListener()
		 {
			 public void actionPerformed(ActionEvent e)throws NullPointerException
			 {  boolean flag = false;
				 FileInputStream fos = null;
				try {
					fos = new FileInputStream("Appointments");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 ObjectInputStream ois = null;
				try {
					ois = new ObjectInputStream(fos);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Appointment a;
				System.out.println(""+msg1);
				 try{
					 while(true)
					 {
						a=(Appointment)ois.readObject();
						if(a.mess.equals(msg1))
						{
							 JOptionPane.showMessageDialog(null, "the appointments is present\n"+Appointment.show(a));
							 flag = true;
						}
					 }
				
					 }
					 catch(Exception p)
					 {
						  File f =new File("Appointments");
							 if(f.length()==0)
							 JOptionPane.showMessageDialog(null, "no appointments on the date");
							 if( flag == false)
								 JOptionPane.showMessageDialog(null, "no appointments on the date");
						 try {
							fos.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					 }jp.setVisible(false);
					 }

		 });
		 	
	}
}

 
		 
		 
 

