package All;

import javax.swing.JOptionPane;

public class Rule extends GUI {
	static int time=15;
	private boolean stopFlag = true;
	 public void go() {
	Thread timerThread = new Thread(new ruletime());
	timerThread.start();
	 }
	
	class ruletime implements Runnable {

        public void run() {
        	while(stopFlag) {
		try{
			
		for(; ; time--) {
			
			writer.println(Integer.toString(time)+":"+" "+":"+" ");
            writer.flush();			
			Thread.sleep(1000);
			if(time==0) {
				if(turn == "P2") {
					writer.println("change"+":"+" "+":"+" ");
		            writer.flush();			
					writer.println("Player1");
		            writer.flush();	
					
				}else {
					writer.println("change"+":"+" "+":"+" ");
		            writer.flush();		
					writer.println("Player2");
		            writer.flush();		
				
				}	
				Rule.time = 16;
				JOptionPane.showMessageDialog(null, "시간이 초과되었습니다.");
			}else if (stopFlag==false) {
				time=15;
				break;
				
			}

		}
	} catch(InterruptedException e) {
		e.printStackTrace();
		stopFlag = false;
		time=15;
	}
        	}
	

        }

	}
    public void stop() {
    	this.stopFlag = false;
    }
    public void start() {
    	this.stopFlag = true;
    }
}
