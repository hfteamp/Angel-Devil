package All;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class reset extends GUI {

	void set() {
		int aa = 0;
		int ii = 0;
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {

				if (x == 0) {
					piecebutton[aa].setIcon(P1icon);
					board[x][y].add(piecebutton[aa]);
					aa++;
				}
				if (x == 6) {
					piecebutton[aa].setIcon(P2icon);
					board[x][y].add(piecebutton[aa]);
					aa++;
				}
			}
		}
	}
}
