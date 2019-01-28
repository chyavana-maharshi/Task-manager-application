package One;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AddTask extends Component {
	Appointment a;
	int m,y, hour , minute ,d ;
	String mess = "",sub ="";
	
	AddTask(){
		final JFrame jf = new JFrame("Add Task");
		jf.setVisible(true);
		jf.setSize(500,500);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		GregorianCalendar gc = new GregorianCalendar();
		JPanel p1 = new JPanel( new GridLayout(2,1,30,50));//outer panel
		JPanel p = new JPanel(new GridLayout(4,1,50,50));//inner panel
		JPanel dp = new JPanel(new GridLayout(1,4));
	    jf.add(p1);
	    p1.add(p);
	    p.add(dp);//Date panel
		JLabel l1 = new JLabel("Date");
	    final JComboBox mon;
		final JComboBox year;
		final JComboBox day;
	    
	     mon = new JComboBox();
	    for(int i=1;i<=12;i++)
	    {    
	    	Integer j = new Integer(i);
	    	mon.addItem(j);
	    }
	    mon.setEditable(false);
	    mon.setSelectedItem(2);
	    
	    
	     year = new JComboBox();
	    for(int i=1994;i<=2050;i++)
	    {    
	    	Integer j = new Integer(i);
	    	year.addItem(j);
	    }
	    year.setEditable(false);
	    year.setSelectedItem(gc.get(GregorianCalendar.YEAR));
		mon.setSelectedItem(gc.get(GregorianCalendar.MONTH));
	    day = CreateDayComboBox( mon , year);
		dp.add(l1);
		dp.add(day);
		dp.add(mon);
		dp.add(year);//date panel ends
		
		
		
		JPanel tp = new JPanel(new GridLayout(1,3));//time
		p.add(tp);
		JLabel l2 = new JLabel("Time");
		final JComboBox hr = new JComboBox();
		final JComboBox min = new JComboBox();
		for( int i = 0 ; i<=23 ; i++)
		{
			Integer j = new Integer( i );
			hr.addItem(j);
		}
		for( int i = 0 ; i < 60 ;i++)
		{
			Integer j = new Integer( i);
			min.addItem(j);
		}
		tp.add(l2);
		tp.add(hr);
		tp.add(min);
		JPanel sp = new JPanel(new GridLayout(1,2));//subject
		JLabel tit = new JLabel("Subject");
	    JTextField tf = new JTextField("");
	    p.add(sp);
	    sp.add(tit);
	    sp.add(tf);
	    
	    //adding the entered Subject to the appropriate variable
	    tf.addKeyListener(new KeyListener(){
	    	public void keyPressed(KeyEvent e){
	    		
	    	}
	    	public void keyReleased(KeyEvent e){
	    		
	    	}
	    	public void keyTyped(KeyEvent e){
	    		char c = e.getKeyChar();
	    		if( c == 8)
	   	    	sub = sub.substring(0,sub.length()-1);	
	    		else
	    			sub = sub + c;
	    	}
	    }
	    );
	    
	    JPanel mp = new JPanel(new GridLayout(1,2));//message
		p.add(mp);
	    JLabel msg = new JLabel("Message");
	    JTextField tf2 = new JTextField("");
	    
	    //adding the entered message to the appropriate variable
	    tf2.addKeyListener(new KeyListener(){
	    	public void keyPressed(KeyEvent e){
	    		
	    	}
	    	public void keyReleased(KeyEvent e){
	    		
	    	}
	    	public void keyTyped(KeyEvent e){
	    		char c = e.getKeyChar();
	    		if( c == 8)
	    		mess = mess.substring(0,mess.length()-1);	
	    		else
	    			mess = mess + c;	
	    	}
	    }
	    );
	   
	    mp.add(msg);
	    mp.add(tf2);
		
	    JButton q = new JButton("Submit");
		q.setSize(20,20);
	    q.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				SubmitClicked(mon , year , hr, min , day);
				jf.setVisible(false);
				System.out.println(""+ sub + mess);
		}
	    });
		p1.add(q);
		
	}
	    
	  public void SubmitClicked(JComboBox mon ,JComboBox year, JComboBox hr ,JComboBox min ,JComboBox day){
		//action performed when submit is pressed	
			 getInfoCombo( mon , "MONTH" );
			 getInfoCombo( year , "YEAR");
			 getInfoCombo( hr , "HOUR");
			 getInfoCombo( min , "MINUTE");
			 getInfoCombo( day , "DAY");
			 
			 //Creating an appointment
			 a = new Appointment( d , m , y ,hour, minute , sub , mess );
			 
			 //writing to file
			 WriteToFile( "Appointments", a );
			}
	
	
	public void getInfoCombo( JComboBox b, String s)
	{
		if( s.equals("MONTH"))
		{
			m = Integer.parseInt(b.getSelectedItem().toString());
		}
		else if( s.equals("DAY"))
			d = Integer.parseInt(b.getSelectedItem().toString());
		else if( s.equals("YEAR"))
		{
			y = Integer.parseInt(b.getSelectedItem().toString());
		}
		else if( s.equals("HOUR"))
		{
			hour = Integer.parseInt(b.getSelectedItem().toString());
		}
		else if( s.equals("MINUTE"))
		{
			minute = Integer.parseInt(b.getSelectedItem().toString());
		}
	
	}
	public JComboBox CreateDayComboBox ( JComboBox month , JComboBox year)
	{
		int x , y;
		y = Integer.parseInt(year.getSelectedItem().toString());
		JComboBox day = new JComboBox();
		if( (x = Integer.parseInt(month.getSelectedItem().toString())) < 8)
		{
			if( x%2 == 0 && x != 2 )
			{
				for( int i = 1 ; i  < 31; i++ )
					day.addItem(new Integer(i));
			}
			else if( x%2 != 0)
			{
				for( int i = 1 ; i<32 ; i++)
						day.addItem(new Integer(i));
			}
			else
			{
				if( y%4 != 0)
					for( int i =1 ; i<30 ; i++)
						day.addItem(new Integer(i));
				else
					for( int i = 1 ; i<29 ; i++)
						day.addItem(new Integer(i));
			}
		}
		else
		{

			if( x%2 == 0  )
			{
				for( int i = 1 ; i  < 32; i++ )
					day.addItem(new Integer(i));
			}
			else
			{
				for( int i = 1 ; i<31 ; i++)
						day.addItem(new Integer(i));
			}
			
		}
		return day;
		
	}
	
	public void WriteToFile( String filename , Appointment app)
	{
		try{
		FileOutputStream fos = new FileOutputStream(filename,true);
		ObjectOutputStream oos;
		File f = new File( filename);
		if( f.length() == 0)
		{
			oos = new ObjectOutputStream(fos);
		}
		else 
			oos = new Append(fos);
		oos.writeObject(app);
		fos.close();
		}
		catch( Exception e)
		{
		}
		
	}

}
