package All;

import javax.swing.JOptionPane;

public class login extends GUI {

	 public void go() {
	Thread loginThread = new Thread(new logintime());
	loginThread.start();
	 }
	
	class logintime implements Runnable {
        public void run() {
        	new ServerNetworking().go();
        	

	
}
	}
}
