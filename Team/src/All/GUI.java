package All;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class GUI {
	JFrame frame;
	JPanel board[][] = new JPanel[8][8];
	JButton piecebutton[] = new JButton[20];
	JPanel iu;
	JTextArea chat;
	JTextField input;
	JButton submit;
	JPanel deathzone[];
	JButton start;
	int sql = 100;
	int click = 0;
	int pid = 0;
	int px, py;
	String Turn = "Devil";
	String pteam;

	class BoardListener { // implement MouseListener
		int x, y;

		void BoardListener(int x, int y) {

		}

		void mouseClicked(MouseEvent e) {

		}
	}

	class PieceListener { // implement MouseListener
		int x, y;
		int id;
		String team;

		void PieceListener(int x, int y, String team) {

		}

		void ActionPerformed(ActionEvent e) {

		}
	}

	void AddBoard() {

	}

	void AddPiece() {

	}

}