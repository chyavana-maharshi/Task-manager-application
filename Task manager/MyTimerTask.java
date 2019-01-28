package One;

import java.net.*;
import java.io.*;
import java.util.*;

public class MyTimerTask extends TimerTask
{

	@Override
	public void run() {
		Runtime r = Runtime.getRuntime();
		Process p = null;
		
		try{
			p = r.exec("sound.mp3");
		}
		catch(Exception e)
		{
			System.out.println("Alarm sound could not played");
		}

	}

}
