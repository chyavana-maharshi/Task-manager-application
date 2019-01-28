package One;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Appointment implements Serializable {

	public Calendar cal;
	public String sub,mess;
	Appointment(int day , int month , int year , int hour , int minute ,String sub , String mess)
	{
		cal = new GregorianCalendar();
		cal.set(GregorianCalendar.DAY_OF_MONTH,day);
		cal.set(GregorianCalendar.MONTH, month);
		cal.set(GregorianCalendar.YEAR, year);
		cal.set(GregorianCalendar.HOUR_OF_DAY, hour);
		cal.set(GregorianCalendar.MINUTE, minute);
		
		this.sub = sub;
		this.mess = mess;
		System.out.println(""+cal.get(GregorianCalendar.YEAR)+ "\t"+ cal.get(GregorianCalendar.DAY_OF_MONTH)+ "\t"+ cal.get(GregorianCalendar.MONTH));
	}
	Appointment(Appointment a)
	{
		cal = new GregorianCalendar();
		cal.set(GregorianCalendar.DAY_OF_MONTH,a.cal.get(GregorianCalendar.DAY_OF_MONTH));
		cal.set(GregorianCalendar.MONTH,a.cal.get(GregorianCalendar.MONTH) );
		cal.set(GregorianCalendar.YEAR, a.cal.get(GregorianCalendar.YEAR));
		cal.set(GregorianCalendar.HOUR_OF_DAY, a.cal.get(GregorianCalendar.HOUR_OF_DAY));
		cal.set(GregorianCalendar.MINUTE, a.cal.get(GregorianCalendar.MINUTE));
		
		sub = a.sub;
		mess = a.mess;
	}
	public static String show(Appointment b)
	 {
		 return("the scheduled appointment is at\n"+b.cal.get(GregorianCalendar.DAY_OF_MONTH)+" : "+b.cal.get(GregorianCalendar.MONTH)+"  : "+b.cal.get(GregorianCalendar.YEAR)+"\ntime is"+ b.cal.get(GregorianCalendar.HOUR_OF_DAY)+"  :  " + b.cal.get(GregorianCalendar.MINUTE)+"\nthe subject is : \n"+b.sub+"\nthe body of the appointment :  "+b.mess);
	 }
}
