package One;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class EditTask extends Component{
	Calendar cal;
	static JFrame fr;
	static JPanel jp , datepanel, timepanel , selectpanel;
	JComboBox day,month,year,hour,min , select;
	JButton submit;
	static JFrame fr1;
	static Appointment t = new Appointment(0,0,0,0,0,"","");
	static String str ="", choice;
	EditTask(){
		fr = new JFrame("Edit Task");
		jp = new JPanel(new GridLayout(4,1,10,10));
		fr.setSize(400, 400);
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fr.add(jp);
		 
		//first grid
		datepanel = new JPanel( new GridLayout(1,5,30,100));
		JLabel datelabel = new JLabel("Date");
		datepanel.add(datelabel);
		day = new JComboBox();
		for( int i = 1 ; i<32 ; i++)
			day.addItem(i);
		datepanel.add(day);
		 month = new JComboBox();
		for( int i = 1; i<13 ;i++)
			month.addItem(i);
		month.setSelectedItem(7);
		datepanel.add(month);
		year = new JComboBox();
		for( int i = 1984; i<2050 ;i++)
			year.addItem(i);
		year.setSelectedItem(2014);
		datepanel.add(year);
		jp.add(datepanel);
		
		//second grid
		timepanel = new JPanel( new GridLayout(1,3,30,100));
		JLabel timelabel = new JLabel("Time");
		timepanel.add(timelabel);
		hour = new JComboBox();
		for(int i = 0 ; i<24 ; i++)
			hour.addItem(i);
		timepanel.add(hour);
		min = new JComboBox();
		for( int i = 0 ;i <60; i++)
			min.addItem(i);
		timepanel.add(min);
		jp.add(timepanel);
		
		//the field to be modified
		selectpanel = new JPanel( new GridLayout(1,2,30,100));
		JLabel selectlabel = new JLabel ( "Field");
		selectpanel.add(selectlabel);
		select = new JComboBox();
		select.addItem("date");
		select.addItem("time");
		select.addItem("subject");
		select.addItem("message");
		selectpanel.add(select);
		jp.add(selectpanel);
		
		//submit button
		submit = new JButton("SUBMIT");
		jp.add(submit);
		
		//Add action listener 
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				SubmitClicked();fr.setVisible(false);
			}
		});
	}
	
	public void SubmitClicked()
	{
		boolean flag = false;
		cal = new GregorianCalendar();
		cal.set(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(day.getSelectedItem().toString()));
		cal.set(GregorianCalendar.MONTH, Integer.parseInt(month.getSelectedItem().toString()));
		cal.set(GregorianCalendar.YEAR, Integer.parseInt(year.getSelectedItem().toString()));
		cal.set(GregorianCalendar.HOUR_OF_DAY, Integer.parseInt(hour.getSelectedItem().toString()));
		cal.set(GregorianCalendar.MINUTE, Integer.parseInt(min.getSelectedItem().toString()));
		
		
		Appointment temp = null;
		try{
			//System.out.println("1");
		FileInputStream fis = new FileInputStream("Appointments");
		ObjectInputStream ois =new ObjectInputStream(fis);
		FileOutputStream fos = new FileOutputStream("temp");
		ObjectOutputStream oos= new ObjectOutputStream(fos);
		File v= new File("temp");
		while(true)
		{
			if(v.length()==0)
				oos = new ObjectOutputStream(fos);
			else
				oos = new Append(fos);
			temp = (Appointment)ois.readObject();
			if( calequal(temp.cal,cal))
			{
				t = new Appointment(temp);
				flag = true;
				continue;
			}
			oos.writeObject(temp);
			
		}
		}
		catch(EOFException e)
		{
			//System.out.println("1");
			File f = new File("Appointments");
			if(f.length() == 0)
				{
				JOptionPane.showMessageDialog(fr, "No Appointments");
				fr.setVisible(false);
				}
			else if(!flag){
			JOptionPane.showMessageDialog(fr, "Appointment not found");
			fr.setVisible(false);
			}
			else
			{
				fr1 = new JFrame();
				fr1.setSize(300, 300);
				fr1.setVisible(true);
			
				choice = select.getSelectedItem().toString();
				ChangeField();
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public boolean calequal(Calendar a, Calendar b)
	{ 
		return (a.get(GregorianCalendar.DAY_OF_MONTH)==b.get(GregorianCalendar.DAY_OF_MONTH))&&(a.get(GregorianCalendar.MONTH)==b.get(GregorianCalendar.MONTH))&&(a.get(GregorianCalendar.YEAR)==b.get(GregorianCalendar.YEAR))&&(a.get(GregorianCalendar.HOUR)==b.get(GregorianCalendar.HOUR))&&(a.get(GregorianCalendar.MINUTE)==b.get(GregorianCalendar.MINUTE));
	}
	public void ChangeField()
	{
		
		System.out.println(choice);
		if( choice.equals("date"))
		{
			JPanel panel = new JPanel(new GridLayout(2,1));
			fr1.add(panel);
			
			JPanel dp = new JPanel( new GridLayout(1,4));
			JLabel l = new JLabel("New Date");
			dp.add(l);
			
			final JComboBox dayc = new JComboBox();
			for( int i = 1 ; i<32 ; i++)
				dayc.addItem(i);
			dp.add(dayc);
			 final JComboBox monthc = new JComboBox();
			for( int i = 1; i<13 ;i++)
				monthc.addItem(i);
			monthc.setSelectedItem(7);
			dp.add(monthc);
			final JComboBox yearc = new JComboBox();
			for( int i = 1984; i<2050 ;i++)
				yearc.addItem(i);
			yearc.setSelectedItem(2014);
			dp.add(yearc);
			panel.add(dp);
			
			JButton b = new JButton("OK");
			panel.add(b);
			 
			b.addActionListener( new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					t.cal.set(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(dayc.getSelectedItem().toString()));
					t.cal.set(GregorianCalendar.MONTH, Integer.parseInt(monthc.getSelectedItem().toString()));
					t.cal.set(GregorianCalendar.YEAR, Integer.parseInt(yearc.getSelectedItem().toString()));
					OKClicked();
					fr1.setVisible(false);fr.setVisible(false);
				}
			});
			
		}
		else if(choice.equals("time"))
		{
			JPanel panel = new JPanel(new GridLayout(2,1));
			fr1.add(panel);
			
			JPanel tp = new JPanel( new GridLayout(1,3,30,100));
			JLabel l = new JLabel("New Time");
			tp.add(l);
			final JComboBox h = new JComboBox();
			for(int i = 0 ; i<24 ; i++)
				h.addItem(i);
			tp.add(h);
			final JComboBox m = new JComboBox();
			for( int i = 0 ;i <60; i++)
				m.addItem(i);
			tp.add(m);
			panel.add(tp);
			
			JButton b = new JButton("OK");
			panel.add(b);
			b.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					t.cal.set(GregorianCalendar.HOUR_OF_DAY, Integer.parseInt(h.getSelectedItem().toString()));
					t.cal.set(GregorianCalendar.MINUTE, Integer.parseInt(m.getSelectedItem().toString()));
					OKClicked();
					fr.setVisible(false);
				}
			});
		}
		else if( choice.equals("subject"))
		{
			System.out.println(""+1);
			JPanel p = new JPanel(new GridLayout(2,1));
			fr1.add(p);
			JPanel text = new JPanel( new GridLayout(1,2));
			JLabel l = new JLabel("New Subject");
			text.add(l);
			//System.out.println(""+2);
			JTextField sub = new JTextField();
			text.add(sub);
			p.add(text);
			System.out.println(""+2);
			sub.addKeyListener( new KeyListener(){
				public void keyPressed(KeyEvent e)
				{}
				public void keyReleased(KeyEvent e)
				{}
				public void keyTyped(KeyEvent e)
				{
					char c = e.getKeyChar();
		    		if( c == 8)
		   	    	str = str.substring(0,str.length()-1);	
		    		else
		    			str = str + c;
				}
			});
			
			JButton b = new JButton("OK");
			b.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					t.sub = new String(str);
					OKClicked();
					fr1.setVisible(false);
				}
			});
			p.add(b);

		}
		else{
			JPanel p = new JPanel(new GridLayout(2,1));
			fr1.add(p);
			JPanel text = new JPanel(new GridLayout( 1,2));
			p.add(text);
			JLabel l = new JLabel("New Message");
			text.add(l);
			
			JTextField sub = new JTextField();
			text.add(sub);
			sub.addKeyListener( new KeyListener(){
				public void keyPressed(KeyEvent e)
				{}
				public void keyReleased(KeyEvent e)
				{}
				public void keyTyped(KeyEvent e)
				{
					char c = e.getKeyChar();
		    		if( c == 8)
		   	    	str = str.substring(0,str.length()-1);	
		    		else
		    			str = str + c;
				}
			});
			
			JButton b = new JButton("OK");
			p.add(b);
			b.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					t.mess =new String(str);
					OKClicked();
					fr1.setVisible(false);
				}
			});
		}
		
	}
	
	public void OKClicked()
	{
		try{
			FileOutputStream fos = new FileOutputStream("temp",true);
			ObjectOutputStream oos;
			File fl =new File("temp");
			if(fl.length()==0)
				oos = new ObjectOutputStream(fos);
			else
				oos = new Append(fos);
			oos.writeObject(t);
			fos.close();
			FileOutputStream p = new FileOutputStream("Appointments");
			p.write(new String().getBytes());
			p.close();
			
			FileChannel ip = new FileInputStream("temp").getChannel();
			FileChannel op = new FileOutputStream("Appointments").getChannel();
			op.transferFrom(ip, 0, ip.size());
			
			ip.close();
			op.close();
			
			
			p = new FileOutputStream("temp");
			p.write(new String().getBytes());
			JOptionPane.showMessageDialog(fr1, "Edited!!");
			fr1.setVisible(false);
			}
			catch(Exception ex)
			{
				
			}

	}
}
