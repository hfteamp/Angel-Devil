package All;

import javax.swing.*;

public class EndGame extends Gui {
	public static void endGame(String a) {
		gui.mm.stop();
		gui.mm.win();
		String p[] = { "한판 더!", "게임 종료" };
		int result = JOptionPane.showOptionDialog(null, a + "가 승리하였습니다..!", "선택", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, p, p[0]);
		if (result == JOptionPane.CLOSED_OPTION) {
			JOptionPane.showMessageDialog(null, "closed");
		} else if (result == JOptionPane.YES_OPTION) {
			writer.println("계속" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " ");
			writer.flush();
		} else {
			writer.println("게임끝" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " ");
			writer.flush();
		}
	}
}
