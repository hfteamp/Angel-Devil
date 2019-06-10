
package All;

import javax.swing.ImageIcon;

public class ChangeImage extends GUI {

	ImageIcon angelicon = new ImageIcon("image/Angel.png");
	ImageIcon devilicon = new ImageIcon("image/Devil.png");

	void lock() {

		if (turn == "Angel") {
			for (int i = 0; i < 7; i++) {
				piecebutton[i].setIcon(angelicon);

			}

		}

		if (turn == "Devil") {
			for (int i = 7; i < 14; i++) {
				piecebutton[i].setIcon(angelicon);
			}

		}
	}

	void unlock() {
		for (int j = 0; j < 14; j++) {

			if (turn == "Angel" && piece[j].state == 0) {
				for (int i = 0; i < 7; i++) {
					piecebutton[i].setIcon(angelicon);

				}

			} else if (turn == "Angel" && piece[j].state == 1) {
				for (int i = 0; i < 7; i++) {
					piecebutton[i].setIcon(devilicon);
				}

			}

			if (turn == "Devil" && piece[j].state == 0) {
				for (int i = 7; i < 14; i++) {
					piecebutton[i].setIcon(angelicon);

				}

			} else if (turn == "Devil" && piece[j].state == 1) {
				for (int i = 7; i < 14; i++) {
					piecebutton[i].setIcon(devilicon);
				}

			}

		}

	}
}
