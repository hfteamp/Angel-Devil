
package All;

import javax.swing.*;

public class END extends GUI {
	public static void game(String a) {
		String p[] = { "계속할것입니까?", "게임끝" };
		int result = JOptionPane.showOptionDialog(null, "둘중에서 고르시오.", "선택", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, p, p[0]);

		if (result == JOptionPane.CLOSED_OPTION) {
			JOptionPane.showMessageDialog(null, "closed");
		} else if (result == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "계속할것입니까?");
			writer.println("계속" + ":");
			writer.flush();
		} else {
			JOptionPane.showMessageDialog(null, "게임끝");
			writer.println("게임끝" + ":");
			writer.flush();
		}

	}

}
