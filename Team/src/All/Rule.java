package All;


public class Rule extends GUI {
	int a;
	 public void go() {
	Thread timerThread = new Thread(new ruletime());
	timerThread.start();
	
	 }
	
	class ruletime implements Runnable {
        public void run() {
        	
		try{
			
		for(int a=30; a>0; a--) {
			
			writer.println(Integer.toString(a)+":");
            writer.flush();			
			Thread.sleep(1000);
		}
	} catch(InterruptedException e) {
		e.printStackTrace();
	}
	
}
	}
}
