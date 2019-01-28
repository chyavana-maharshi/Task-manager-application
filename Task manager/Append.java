package One;

import java.io.*;


public class Append extends ObjectOutputStream
{
	  public Append(OutputStream out) throws IOException {
		    super(out);
		  }

		
		  protected void writeStreamHeader() throws IOException {
		    // do not write a header, but reset:
		    // this line added after another question
		    // showed a problem with the original
		    reset();
		  }

}
