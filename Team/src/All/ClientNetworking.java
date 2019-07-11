package All;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientNetworking extends GUI {

	public void go() {

		setUpNetworking();

		Thread readerThread = new Thread(new IncomingReader());
		readerThread.start();

	}

	private void setUpNetworking() {
		try {
			sock = new Socket("127.0.0.1", 5000);
			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(streamReader);
			writer = new PrintWriter(sock.getOutputStream());

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	class IncomingReader implements Runnable {
		public void run() {
			String message;
			try {
				while ((message = reader.readLine()) != null) {
					String[] a = message.split(":");
					incoming.append(message + "\n");

					if (a.length == 6) {
						board[Integer.parseInt(a[2])][Integer.parseInt(a[3])].add(piecebutton[Integer.parseInt(a[4])]);
						if (Integer.parseInt(a[5]) == 1) {
							piecebutton[Integer.parseInt(a[4])]
									.setIcon(new ImageIcon("image/" + turn + "_Devil" + ".png"));
						}
						piecebutton[Integer.parseInt(a[4])].setIcon(new ImageIcon("image/" + turn + ".png"));

						board[Integer.parseInt(a[0])][Integer.parseInt(a[1])].removeAll();
						board[Integer.parseInt(a[0])][Integer.parseInt(a[1])].revalidate();
						board[Integer.parseInt(a[0])][Integer.parseInt(a[1])].repaint();
						piece[Integer.parseInt(a[4])].x = piece[Integer.parseInt(a[4])].x
								- (piece[Integer.parseInt(a[4])].x - Integer.parseInt(a[2]));
						piece[Integer.parseInt(a[4])].y = piece[Integer.parseInt(a[4])].y
								- (piece[Integer.parseInt(a[4])].y - Integer.parseInt(a[3]));

						if (turn == "P2") {
							turn = "P1";
						} else {
							turn = "P2";
						}
					} else if (a.length == 2) {
						board[piece[Integer.parseInt(a[1])].x][piece[Integer.parseInt(a[1])].y].removeAll();
						board[piece[Integer.parseInt(a[1])].x][piece[Integer.parseInt(a[1])].y].revalidate();
						board[piece[Integer.parseInt(a[1])].x][piece[Integer.parseInt(a[1])].y].repaint();
						board[piece[Integer.parseInt(a[1])].x][piece[Integer.parseInt(a[1])].y]
								.add(piecebutton[Integer.parseInt(a[0])]);

						piecebutton[Integer.parseInt(a[0])].setIcon(new ImageIcon("image/" + turn + ".png"));
						board[piece[Integer.parseInt(a[0])].x][piece[Integer.parseInt(a[0])].y].removeAll();
						board[piece[Integer.parseInt(a[0])].x][piece[Integer.parseInt(a[0])].y].revalidate();
						board[piece[Integer.parseInt(a[0])].x][piece[Integer.parseInt(a[0])].y].repaint();

						piece[Integer.parseInt(a[0])].x = piece[Integer.parseInt(a[1])].x;
						piece[Integer.parseInt(a[0])].y = piece[Integer.parseInt(a[1])].y;

						if (turn == "P2") {
							turn = "P1";
						} else {
							turn = "P1";
						}
						/*
						 * board[piece[id].x][piece[id].y].removeAll();
						 * board[piece[id].x][piece[id].y].revalidate();
						 * board[piece[id].x][piece[id].y].repaint();
						 * board[piece[id].x][piece[id].y].add(piecebutton[pid]);
						 * piecebutton[pid].setIcon(new ImageIcon("image/" + pteam + ".png"));
						 * board[piece[pid].x][piece[pid].y].removeAll();
						 * board[piece[pid].x][piece[pid].y].revalidate();
						 * board[piece[pid].x][piece[pid].y].repaint();
						 * 
						 * 
						 * piece[pid].x = piece[id].x; piece[pid].y = piece[id].y; click = 0;
						 * 
						 * if (turn == "P2") { turn = "P1"; } else { turn = "P2"; }
						 */
					}

				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
