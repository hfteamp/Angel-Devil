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
	                	String []a = message.split(":");     	
	                    incoming.append(message + "\n");
	                    
	                    board[Integer.parseInt(a[2])][Integer.parseInt(a[3])].add(piecebutton[Integer.parseInt(a[4])]);
	                    
	    				piecebutton[Integer.parseInt(a[4])].setIcon(new ImageIcon("image/" + turn + ".png"));
	    				
	    				board[Integer.parseInt(a[0])][Integer.parseInt(a[1])].removeAll();
	    				board[Integer.parseInt(a[0])][Integer.parseInt(a[1])].revalidate();
	    				board[Integer.parseInt(a[0])][Integer.parseInt(a[1])].repaint();
	    				piece[Integer.parseInt(a[4])].x = piece[Integer.parseInt(a[4])].x - (piece[Integer.parseInt(a[4])].x - Integer.parseInt(a[2]));
	    				piece[Integer.parseInt(a[4])].y = piece[Integer.parseInt(a[4])].y - (piece[Integer.parseInt(a[4])].y - Integer.parseInt(a[3]));
	    				
	    				if (turn == "Devil") {
	    					turn = "Angel";
	    				} else {
	    					turn = "Devil";
	    				}

	                    System.out.println(a[0]+a[1]+a[2]);
	                }
	            } catch (IOException ex)
	            {
	                ex.printStackTrace();
	            }
	        }
	    }
	    

}
