package All;
import javax.swing.*;
public class END {
	void game(String a) {
		int result = JOptionPane.showConfirmDialog(null,"계속할것입니까?","게임 끝",JOptionPane.YES_NO_OPTION);
		
		if(result == JOptionPane.CLOSED_OPTION) {
			
		}else if(result == JOptionPane.YES_OPTION) {
			System.exit(0);
			GUI gui = new GUI();
			gui.login();
			
		}else {
			
		}
	}

}
