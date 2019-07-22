package All;
import javax.swing.*;

public class END extends GUI{
 public static void game(String a) {
  String p[] = {"한판 더!","게임 종료"};
  int result=JOptionPane.showOptionDialog(null,a+"가 승리하였습니다..!","선택",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,p,p[0]);
 
  	if(result==JOptionPane.CLOSED_OPTION) {
  		JOptionPane.showMessageDialog(null, "closed");
  		}else if(result==JOptionPane.YES_OPTION) {
  			writer.println("계속"+":"+" "+":"+" "+":"+" "+":"+" "+":"+" "+":"+" ");
  			writer.flush();
  		}else {
  			writer.println("게임끝"+":"+" "+":"+" "+":"+" "+":"+" "+":"+" "+":"+" ");
  			writer.flush();
  		}
 
 
 	}

}
 