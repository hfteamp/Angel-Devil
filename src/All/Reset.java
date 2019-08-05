package All;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import All.Gui.BoardListener;
import All.Gui.PieceListener;

public class Reset extends Gui {
	int aa = 0;
	int ii = 0;

	void set() {
		gui.gaming = 0;
		gui.ready.setEnabled(true);
		gui.rule.stop();
		gui.count = 3;
		gui.timercount = 0;
		gui.angelkill = 0;
		gui.devilkill = 0;
		gui.check=0;
		Piece.ii = 0;
		mm.Main();
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				gameboard[x][y].removeAll();
				gameboard[x][y].revalidate();
				gameboard[x][y].repaint();
				if (x < 2) {
					if (y < 3) {
						deathboard[x][y].removeAll();
						deathboard[x][y].revalidate();
						deathboard[x][y].repaint();
						deathbutton[ii].setIcon(new ImageIcon(""));
						deathboard[x][y].add(deathbutton[ii]);
						ii++;
					}
				}
				if (x == 0) {
					gameboard[x][y].add(piecebutton[aa]);
					piece[aa] = new Piece(x, y, aa, 0, "P1");
					aa++;
				}
				if (x == 6) {
					gameboard[x][y].add(piecebutton[aa]);
					piece[aa] = new Piece(x, y, aa, 0, "P2");
					aa++;
				}
			}
		}
		if (who == "P1") {
			for (int cc = 0; cc < 7; cc++) {
				if (piece[cc].state == 1) {
					piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
				} else if (piece[cc].state == 0) {
					piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
				}
			}
		} else if (who == "P2") {
			for (int cc = 7; cc < 14; cc++) {
				if (piece[cc].state == 1) {
					piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
				} else if (piece[cc].state == 0) {
					piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
				}
			}
		}

	}
}
