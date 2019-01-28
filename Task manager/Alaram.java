package One;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;

import javax.swing.JOptionPane;

public class Alaram implements Runnable{
	Thread t;
	Alaram()
	{
		//System.out.println("Inside alarm");
		t=new Thread(this,"alarm thread");
		t.start();
	}
public void run()
{
	System.out.println("inside thread");
	
  	while(true)
  	{
  	try
  	{
  		Calendar c = new GregorianCalendar();
  		c = add( c );
  		FileInputStream fin = new FileInputStream("Appointments");
  	    ObjectInputStream oin = new ObjectInputStream(fin);
    try
    {
    	while(true)
    	{
    	Appointment a = (Appointment)oin.readObject();
    	if(Check(a.cal,c))
    	{
    		//System.out.println("Alarm is on");
    		new Audio();
    		JOptionPane.showMessageDialog(null, "Appointment after 15 minutes\n"+Appointment.show(a));
    		Thread.sleep(60000);
    		
    	}
    	System.out.println("Running");
    	}
    }
    catch(EOFException e)
    {
    	
    	oin.close();
    	throw new Exception();
    
    }
  	}
  	catch(Exception e)
  	{
  		
  	}
	}
}

public boolean Check(Calendar cal, Calendar c) 
{
	if((cal.get(GregorianCalendar.DAY_OF_MONTH)==c.get(GregorianCalendar.DAY_OF_MONTH))&&(cal.get(GregorianCalendar.MONTH)==c.get(GregorianCalendar.MONTH))
	&&(cal.get(GregorianCalendar.YEAR)==c.get(GregorianCalendar.YEAR))&&(cal.get(GregorianCalendar.HOUR)==c.get(GregorianCalendar.HOUR))
	&&(cal.get(GregorianCalendar.MINUTE)==c.get(GregorianCalendar.MINUTE)))
	return true;
	else 
		return false;
}

public Calendar add ( Calendar c)
{
	int d = c.get(GregorianCalendar.DAY_OF_MONTH);
	int m = c.get(GregorianCalendar.MONTH);
	int y = c.get(GregorianCalendar.YEAR);
	int h = c.get( GregorianCalendar.HOUR);
	int min = c.get(GregorianCalendar.MINUTE);
	m = m+1;
	min = min + 15;
	if( min > 59 )
	{
		h++;
		min = min-60;
	}
	if( h > 23 )
	{
		d++;
		h = 0;
	}
	if( d > c.getActualMaximum(GregorianCalendar.DAY_OF_MONTH))
	{
		m++ ;
		d = 1;
	}
	if( m > 12 )
	{
		y++;
		m = 1;
	}
	Calendar a = new GregorianCalendar();
	a.set(GregorianCalendar.MINUTE, min);
	a.set(GregorianCalendar.HOUR,h);
	a.set(GregorianCalendar.DAY_OF_MONTH, d);
	a.set(GregorianCalendar.MONTH, m);
	a.set(GregorianCalendar.YEAR, y);
	return a;
}

}
