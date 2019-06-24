package All;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientNetworking extends GUI {

	 public void go() {

	        setUpNetworking();
	        
	        Thread readerThread = new Thread(new IncomingReader());
	        readerThread.start();

	        
	    }
	 
	  private void setUpNetworking() {
	        try {
	            sock = new Socket("127.0.0.1", 5000);
	            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
	            reader = new BufferedReader(streamReader);
	            writer = new PrintWriter(sock.getOutputStream());
	            System.out.println("networking established");
	        }
	        catch(IOException ex)
	        {
	            ex.printStackTrace();
	        }
	    }
	    

	    
	    class IncomingReader implements Runnable {
	        public void run() {
	            String message;
	            try {
	                while ((message = reader.readLine()) != null) {
	                    System.out.println("client read " + message);
	                    incoming.append(message + "\n");
	                }
	            } catch (IOException ex)
	            {
	                ex.printStackTrace();
	            }
	        }
	    }
	    

}
