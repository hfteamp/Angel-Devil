package All;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GUI extends JFrame {
	static JFrame frame;
	static JFrame frame2;
	static JPanel board[][] = new JPanel[7][7];
	static JPanel board2[][] = new JPanel[2][3];
	static JButton piecebutton[] = new JButton[14];
	static JPanel iu;
	static JPanel iu2;
	static JTextArea chat;
	static JTextField input;
	static JButton submit;
	static JButton savebutton;
	static JRadioButton p1 = new JRadioButton("플레이어1");
	static JRadioButton p2 = new JRadioButton("플레이어2");
	ButtonGroup gp = new ButtonGroup();
	static JButton start = new JButton("준비(Ready)");
	static JButton deathbutton[] = new JButton[6];
	int sql = 100;
	static int click = 0;
	static int pid;
	static int px, py;
	static String pteam;
	static String turn = "P2";
	static Piece piece[] = new Piece[14];
	static Piece p;
	ImageIcon P1icon = new ImageIcon("image/P1.png");
	ImageIcon P2icon = new ImageIcon("image/P2.png");
	int gaming;
	int killed;
	static String who;
	int state;
	static int devilkill = 0;
	static int angelkill = 0;
	/*-----------------------------------------------------------------------------*/
	static JTextArea incoming;
	static JTextField outgoing;
	static JLabel labelturn = new JLabel("player2"); // 태현
	static JLabel nowturn = new JLabel("현재 턴"); // 태현
	static JLabel Iam = new JLabel(); // 태현
	static BufferedReader reader;
	static PrintWriter writer;
	static Socket sock;
	static JLabel timer = new JLabel("timer");
	static int timercount;

	/*-----------------------------------------------------------------------------*/

	class StartListener implements ActionListener { // 시작 전 후 를 나누는 버튼에 리스너
		public void actionPerformed(ActionEvent e) {
			

			if(Piece.count==0) {
			writer.println(who + " | " + "님이 준비(Ready) 하였습니다.");
			writer.flush();
			writer.println(" "+ ":" +" "+ ":" +" "+ ":" +" "+ ":" +" "+ ":" + " ");
			writer.flush();
			JOptionPane.showMessageDialog(null, "상대방이 준비완료되면 바로 시작합니다.");
			if(timercount == 2) {
				Rule rule = new Rule();
				rule.go();
			}
			start.setEnabled(false);
			gaming = 1;
			}else {
				JOptionPane.showMessageDialog(null, "자신의 악마 3마리를 정해주세요");
			}
		}
	}

	class SaveListener implements ActionListener { // 시작 전 후 를 나누는 버튼에 리스너
		public void actionPerformed(ActionEvent e) {
			if (p1.isSelected()) {
				who = "P1";
				Iam.setText("Player1"); // 태현
				frame2.setVisible(false);
				addpiece();
				new ClientNetworking1().go();
			} else if (p2.isSelected()) {
				who = "P2";
				Iam.setText("Player2"); // 태현
				frame2.setVisible(false);
				addpiece();
				new ClientNetworking1().go();
			}
		}
	}

	class BoardListener implements MouseListener {
		int x, y;

		BoardListener(int a, int b) {
			this.x = a;
			this.y = b;
		}

		public void mouseClicked(MouseEvent e) {
			if (gaming == 1)
				piece[pid].move(x, y); // 말이동
			click = 0;

		}

		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override //
		public void mouseEntered(MouseEvent e) {

		}

		@Override //
		public void mouseExited(MouseEvent e) {

		}
	}

	class PieceListener implements ActionListener {
		int x, y, id;
		String team;
		int state;

		PieceListener(int x, int y, int id, int state, String team) {
			this.x = x;
			this.y = y;
			this.id = id; // ���� �ĺ���ȣ
			this.team = team;
			this.state = state;
		}

		public void actionPerformed(ActionEvent e) {

			if (gaming == 1)
				piece[id].kill(); // 말잡기
			if (gaming == 1)
				piece[id].postpiece(); // 말상태저장

			
			if (gaming == 0) {
				if(piece[id].team==who) {
				if(Piece.count!=0) {
				if (piece[id].state == 0) {
					piece[id].trans(id);
					piece[id].state = 1;
					Piece.count--;
				} else if(piece[id].state == 1) {
					piece[id].trans(id);
					piece[id].state = 0;
					Piece.count++;
				}
				}else if (piece[id].state == 0) {
					JOptionPane.showMessageDialog(null, "최대 악마 수는 3마리입니다.");
				}else if(piece[id].state == 1) {
					piece[id].trans(id);
					piece[id].state = 0;
					Piece.count++;
				}
				}else {
					JOptionPane.showMessageDialog(null, "자신의 말만 악마로 변경할수있습니다..!");
				}
			}

		}

	}

	public class SendButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try {
				writer.println(who + " | " + outgoing.getText());
				writer.flush();

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			outgoing.setText("");
			outgoing.requestFocus();
		}
	}

	public void login() {

		gp.add(p1);
		gp.add(p2);
		p1.setBounds(20, 20, 150, 50);
		p2.setBounds(20, 80, 150, 50);

		savebutton = new JButton("시작");
		savebutton.setBounds(250, 50, 100, 50);

		iu2 = new JPanel();
		iu2.setLayout(null);
		iu2.setBackground(Color.GRAY);

		frame2 = new JFrame();
		frame2.setLayout(new BorderLayout());
		frame2.setSize(400, 200);
		frame2.setTitle("Angel and Devil Game");
		frame2.setVisible(true);
		savebutton.addActionListener(new SaveListener());
		iu2.add(savebutton);
		iu2.add(p1);
		iu2.add(p2);
		frame2.add(iu2);
	}

	public void addboard() {
		int aa = 0;
		int ii = 0;
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				if (x < 2) {
					if (y < 3) {
						board2[x][y] = new JPanel();
						if(x%2 == 0 && y%2 == 0 ) {
							board2[x][y].setBackground(Color.red);
						} else if(x % 2 != 0 && y % 2 == 0) {
							board2[x][y].setBackground(Color.yellow);
						}else if (x % 2 == 0 && y % 2 != 0) {
							board2[x][y].setBackground(Color.yellow);
						} else if (x % 2 != 0 && y % 2 != 0) {
							board2[x][y].setBackground(Color.red);
						}
						board2[x][y].setLocation((y + 8) * 90, (x + 8) * 90);
						board2[x][y].setSize(90, 90);
						board2[x][y].setLayout(new FlowLayout());
						frame.add(board2[x][y]);
						if (ii < 6) {
							deathbutton[ii] = new JButton();
							deathbutton[ii].setPreferredSize(new Dimension(80, 80));
							deathbutton[ii].setBorderPainted(false);
							deathbutton[ii].setFocusPainted(false);
							deathbutton[ii].setContentAreaFilled(false);
							board2[x][y].add(deathbutton[ii]);
							ii++;
						}
					}
				}
				board[x][y] = new JPanel();
				if (x % 2 == 0 && y % 2 == 0) {
					board[x][y].setBackground(Color.black);
				} else if (x % 2 != 0 && y % 2 == 0) {
					board[x][y].setBackground(Color.white);
				} else if (x % 2 == 0 && y % 2 != 0) {
					board[x][y].setBackground(Color.white);
				} else if (x % 2 != 0 && y % 2 != 0) {
					board[x][y].setBackground(Color.black);
				}
				if (x == 0) {
					piecebutton[aa].setIcon(P1icon);
					board[x][y].add(piecebutton[aa]);
					piecebutton[aa].addActionListener(new PieceListener(x, y, aa, state, "P1"));
					piece[aa] = new Piece(x, y, aa, 0, "P1");
					aa++;
				}
				if (x == 6) {
					piecebutton[aa].setIcon(P2icon);
					board[x][y].add(piecebutton[aa]);
					piecebutton[aa].addActionListener(new PieceListener(x, y, aa, state, "P2"));
					piece[aa] = new Piece(x, y, aa, 0, "P2");
					aa++;
				}
				board[x][y].setLocation(y * sql, x * sql + 60);
				board[x][y].setSize(sql, sql);
				board[x][y].setLayout(new FlowLayout());
				board[x][y].addMouseListener(new BoardListener(x, y));

				frame.add(BorderLayout.CENTER, board[x][y]);
			}
		}
		start.addActionListener(new StartListener());

		addgui();
	}

	public void addpiece() {
		frame = new JFrame();
		frame.setLayout(new BorderLayout());

		for (int i = 0; i < 14; i++) {
			piecebutton[i] = new JButton();
			piecebutton[i].setPreferredSize(new Dimension(80, 80));
			piecebutton[i].setBorderPainted(false);
			piecebutton[i].setFocusPainted(false);
			piecebutton[i].setContentAreaFilled(false);

		}
		addboard();

	}

	public void addgui() {
		/*---------------------------------------------------------------------*/
		incoming = new JTextArea();
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(false);
		JScrollPane qScroller = new JScrollPane(incoming);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		outgoing = new JTextField();
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new SendButtonListener());
		Iam.setBounds(0, 0, 150, 60); // 태현
		qScroller.setBounds(700, 60, 350, 200); // 태현
		outgoing.setBounds(700, 260, 250, 40); // 태현
		sendButton.setBounds(950, 260, 100, 40); // 태현
		labelturn.setBounds(720, 360, 140, 60); // 태현
		nowturn.setBounds(720, 310, 140, 60); // 태현
		timer.setBounds(900, 350, 140, 60);
		timer.setOpaque(true); // 태현
		timer.setBackground(Color.DARK_GRAY);
		Iam.setOpaque(true); // 태현
		Iam.setBackground(Color.DARK_GRAY); // 태현
		nowturn.setOpaque(true); // 태현
		nowturn.setBackground(Color.DARK_GRAY); // 태현
		labelturn.setOpaque(true); // 태현
		labelturn.setBackground(Color.DARK_GRAY); // 태현
		timer.setFont(new Font("Seif", Font.BOLD, 24)); // 태현
		timer.setForeground(Color.GREEN); // 태현
		labelturn.setFont(new Font("Seif", Font.BOLD, 24)); // 태현
		labelturn.setForeground(Color.GREEN); // 태현
		nowturn.setFont(new Font("Seif", Font.BOLD, 24)); // 태현
		nowturn.setForeground(Color.GREEN); // 태현
		Iam.setFont(new Font("Seif", Font.BOLD, 30)); // 태현
		Iam.setForeground(Color.RED); // 태현
		labelturn.setHorizontalAlignment(JLabel.CENTER); // 태현
		nowturn.setHorizontalAlignment(JLabel.CENTER); // 태현
		Iam.setHorizontalAlignment(JLabel.CENTER); // 태현
		timer.setHorizontalAlignment(JLabel.CENTER); // 태현
		frame.add(timer);
		frame.add(Iam); // 태현
		frame.add(nowturn); // 태현
		frame.add(labelturn); // 태현
		frame.add(qScroller);
		frame.add(outgoing);
		frame.add(sendButton);
		/*--------------------------------------------------------------------------------------*/
		timer.setBounds(850, 350, 140, 60);

		iu = new JPanel();
		iu.setLayout(null);
		iu.setBackground(Color.GRAY);

		start.setBounds(800, 500, 100, 40);
		frame.add(start);
		frame.add(iu);
		frame.setSize(1200, 1200);
		frame.setTitle("Angel and Devil Game");
		frame.setVisible(true);

	}

	public static void main(String[] args) {

		GUI gui = new GUI();
		gui.login();

	}
}
