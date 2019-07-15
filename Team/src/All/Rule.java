package All;

import javax.swing.JOptionPane;

public class Rule extends GUI {
	 public void go() {
	Thread timerThread = new Thread(new ruletime());
	timerThread.start();
	 }
	
	class ruletime implements Runnable {
        public void run() {
        	
		try{
			
		for(int a=3; a>=0; a--) {
			
			writer.println(Integer.toString(a)+":"+" "+":"+" ");
            writer.flush();			
			Thread.sleep(1000);
			if(a==0) {
				if (turn == "P2") {
					writer.println("Player1");
		            writer.flush();		
					turn = "P1";
				} else {
					writer.println("Player2");
		            writer.flush();		
					turn = "P2";
				}	
				JOptionPane.showMessageDialog(null, "시간이 초과되었습니다.");
			}
		}
	} catch(InterruptedException e) {
		e.printStackTrace();
	}
	
}
	}
}
