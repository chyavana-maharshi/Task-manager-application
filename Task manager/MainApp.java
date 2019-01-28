package One;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainApp extends Frame{
	static int i=0;
	public static void main(String args[]) throws IOException
	{
		GregorianCalendar c = new GregorianCalendar();
	  	System.out.println(""+c.get(GregorianCalendar.MINUTE)+" hour:"+c.get(GregorianCalendar.HOUR_OF_DAY));
		FileOutputStream fos=new FileOutputStream("Appointments",true);
		fos.close();
	JFrame fr = new JFrame("Event Calendar");
	JButton Add = new JButton("Add Task");
	JButton Del = new JButton("Delete task");
	JButton ser = new JButton("search Task");
	JButton Calendar = new JButton("Calendar");
	fr.setVisible(true);
	fr.setSize(500,500);
	fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JPanel p = new JPanel(new GridLayout(5,1));
	fr.add(p);
	Calendar.addActionListener(new ActionListener(){
	

		public void actionPerformed(java.awt.event.ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				CalendarProject c = new CalendarProject();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	});
	Add.addActionListener(new ActionListener(){
		

		public void actionPerformed(java.awt.event.ActionEvent arg0) {
			// TODO Auto-generated method stub
			
			AddTask c = new AddTask();
			i++;
		}
		
	});
ser.addActionListener(new ActionListener(){
		

		public void actionPerformed(java.awt.event.ActionEvent arg0) {
			// TODO Auto-generated method stub
		SearchTask p=new SearchTask();
		}
		
	});
Del.addActionListener(new ActionListener(){
	
	public void actionPerformed(java.awt.event.ActionEvent arg0){
		DeleteTask r = new DeleteTask();
	}
});
	
	p.add(Add);
	p.add(Del);
	p.add(ser);
	p.add(Calendar);
	JButton edit = new JButton("Edit Task");
	edit.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae)
		{
			EditTask et = new EditTask();
		}
	});
	p.add(edit);
	
new Alaram();
	}
	
	}
