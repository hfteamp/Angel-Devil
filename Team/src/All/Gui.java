package All;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Gui extends JFrame {
	JFrame gameframe; // 게임프레임
	JFrame playerframe; // 플레이정하기프레임
	JFrame serverframe; // 서버클라이언트정하는프레임
	JFrame clientframe; // 클라이언트프레임
	static JPanel gameboard[][] = new JPanel[7][7]; // 게임판
	static JPanel deathboard[][] = new JPanel[2][3]; // 죽었을때 들어가는 패널
	static JButton piecebutton[] = new JButton[14]; // 게임 말
	JPanel gamepanel; // 게임패널
	JPanel playerpanel; // 플레이패널
	JPanel serverpanel; // 서버패널
	JPanel clientpanel; // 클라이언트패널
	JTextField ip; // 클라이언트패널
	JButton playerchoice; // 플레이어 패널
	JRadioButton p1 = new JRadioButton("플레이어1"); //
	JRadioButton p2 = new JRadioButton("플레이어2(선공)");
	JRadioButton server = new JRadioButton("서버");
	JRadioButton client = new JRadioButton("클라이언트");
	ButtonGroup gp = new ButtonGroup(); // 플레이어 버튼 그룹
	ButtonGroup sc = new ButtonGroup(); // 서버와 클라 버튼 그룹
	JButton ready = new JButton("준비(Ready)"); // 게임시작전 준비
	JButton choice = new JButton("선택"); // 서버와 클라 선택
	JButton connect = new JButton("접속"); // 클라에서 접속할 때
	static JButton deathbutton[] = new JButton[6];
	int sql = 100; // 사각형 크기
	static int count = 3;
	static int click = 0;
	static int pid; // 과거의 말 고유번호
	static int px, py; // 과거 좌표
	static String pteam; // 과거 팀
	static String turn = "P2";
	static Piece piece[] = new Piece[14]; // 말 상태
	String ServerIP = "127.0.0.1";
	ImageIcon P1icon = new ImageIcon("image/P1.png");
	ImageIcon P2icon = new ImageIcon("image/P2.png");
	int gaming; // 게임 상태확인
	int killed; // 잡은것 확인
	static String who; // 누구인지 확인
	int state; // 악마인지 천사인지 확인
	static int devilkill = 0;
	static int angelkill = 0;
	/*-----------------------------------------------------------------------------*/
	static JTextArea incoming; // 채팅관련
	static JTextField outgoing;
	static JLabel labelturn = new JLabel("player2");
	static JLabel nowturn = new JLabel("현재 턴");
	static JLabel nowtime = new JLabel("남은 시간");
	static JLabel Iam = new JLabel();
	static BufferedReader reader;
	static PrintWriter writer;
	static Socket sock;
	static JLabel timer = new JLabel("timer");
	static int timercount;
	static Rule rule = new Rule();
	static Gui gui = new Gui();

	/*-----------------------------------------------------------------------------*/
	class readyListener implements ActionListener { // 시작 전 후 를 나누는 버튼에 리스너
		public void actionPerformed(ActionEvent e) {
			if (count == 0) {
				writer.println(who + " | " + "님이 준비(Ready) 하였습니다.");
				writer.flush();
				writer.println(" " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " ");
				writer.flush();
				JOptionPane.showMessageDialog(null, "상대방이 준비완료되면 바로 시작합니다.");
				if (timercount == 2) {
					rule.start();
					rule.go();
				}
				ready.setEnabled(false);
				gaming = 1;
			} else {
				JOptionPane.showMessageDialog(null, "자신의 악마 3마리를 정해주세요");
			}
		}
	}

	class connectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (ip.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "서버 IP 를 입력하세요.");
			} else {
				clientframe.setVisible(false);
				ServerIP = ip.getText();
				login();
			}
		}
	}

	class ChoiceListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (client.isSelected()) {
				serverframe.setVisible(false);
				client();

			} else if (server.isSelected()) {
				serverframe.setVisible(false);
				login();
				Login log = new Login();
				log.go();
			}
		}
	}

	class playerchoiceListener implements ActionListener { // 시작 전 후 를 나누는 버튼에 리스너
		public void actionPerformed(ActionEvent e) {
			if (p1.isSelected()) {
				who = "P1";
				Iam.setText("Player1");
				playerframe.setVisible(false);
				addpiece();
				new ClientNetworking().go();
			} else if (p2.isSelected()) {
				who = "P2";
				Iam.setText("Player2");
				playerframe.setVisible(false);
				addpiece();
				new ClientNetworking().go();
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
			this.id = id;
			this.team = team;
			this.state = state;
		}

		public void actionPerformed(ActionEvent e) {

			if (gaming == 1)
				piece[id].kill(); // 말잡기
			if (gaming == 1)
				piece[id].pastpiece();
			
			if (gaming == 0) {
				if (piece[id].team == who) {
					if (count != 0) {
						if (piece[id].state == 0) {
							piece[id].trans(id);
							piece[id].state = 1;
							count--;
						} else if (piece[id].state == 1) {
							piece[id].trans(id);
							piece[id].state = 0;
							count++;
						}
					} else if (piece[id].state == 0) {
						JOptionPane.showMessageDialog(null, "최대 악마 수는 3마리입니다.");
					} else if (piece[id].state == 1) {
						piece[id].trans(id);
						piece[id].state = 0;
						count++;
					}
				} else {
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

	public void client() {
		clientframe = new JFrame();
		clientframe.setLayout(new BorderLayout());
		clientframe.setSize(400, 200);
		clientframe.setTitle("Angel and Devil Game");
		clientframe.setVisible(true);
		clientpanel = new JPanel();
		clientpanel.setLayout(null);
		clientpanel.setBackground(Color.GRAY);
		ip = new JTextField();
		ip.setBounds(50, 50, 200, 50);
		connect.setBounds(250, 50, 100, 50);
		connect.addActionListener(new connectListener());
		clientpanel.add(connect);
		clientpanel.add(ip);
		clientframe.add(clientpanel);
	}

	public void start() {
		sc.add(server);
		sc.add(client);
		server.setBounds(20, 20, 150, 50);
		client.setBounds(20, 80, 150, 50);

		serverpanel = new JPanel();
		serverpanel.setLayout(null);
		serverpanel.setBackground(Color.GRAY);

		serverframe = new JFrame();
		serverframe.setLayout(new BorderLayout());
		serverframe.setSize(400, 200);
		serverframe.setTitle("Angel and Devil Game");
		serverframe.setVisible(true);

		choice = new JButton("선택");
		choice.setBounds(250, 50, 100, 50);
		choice.addActionListener(new ChoiceListener());

		serverpanel.add(choice);
		serverpanel.add(client);
		serverpanel.add(server);
		serverframe.add(serverpanel);
	}

	public void login() {
		gp.add(p1);
		gp.add(p2);
		p1.setBounds(20, 20, 150, 50);
		p2.setBounds(20, 80, 150, 50);

		playerchoice = new JButton("플레이어선택");
		playerchoice.setBounds(250, 50, 100, 50);

		playerpanel = new JPanel();
		playerpanel.setLayout(null);
		playerpanel.setBackground(Color.GRAY);

		playerframe = new JFrame();
		playerframe.setLayout(new BorderLayout());
		playerframe.setSize(400, 200);
		playerframe.setTitle("Angel and Devil Game");
		playerframe.setVisible(true);

		playerchoice.addActionListener(new playerchoiceListener());

		playerpanel.add(playerchoice);
		playerpanel.add(p1);
		playerpanel.add(p2);
		playerframe.add(playerpanel);
	}

	public void addboard() {
		int aa = 0;
		int ii = 0;
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				if (x < 2) {
					if (y < 3) {
						deathboard[x][y] = new JPanel();
						if (x % 2 == 0 && y % 2 == 0) {
							deathboard[x][y].setBackground(Color.black);
						} else if (x % 2 != 0 && y % 2 == 0) {
							deathboard[x][y].setBackground(Color.DARK_GRAY);
						} else if (x % 2 == 0 && y % 2 != 0) {
							deathboard[x][y].setBackground(Color.DARK_GRAY);
						} else if (x % 2 != 0 && y % 2 != 0) {
							deathboard[x][y].setBackground(Color.black);
						}
						deathboard[x][y].setLocation((y + 8) * 90, ((x + 6) * 90));
						deathboard[x][y].setSize(90, 90);
						deathboard[x][y].setLayout(new FlowLayout());
						gameframe.add(deathboard[x][y]);
						deathbutton[ii] = new JButton();
						deathbutton[ii].setPreferredSize(new Dimension(80, 80));
						deathbutton[ii].setBorderPainted(false);
						deathbutton[ii].setFocusPainted(false);
						deathbutton[ii].setContentAreaFilled(false);
						deathboard[x][y].add(deathbutton[ii]);
						ii++;
					}
				}
				gameboard[x][y] = new JPanel();
				if (x % 2 == 0 && y % 2 == 0) {
					gameboard[x][y].setBackground(Color.black);
				} else if (x % 2 != 0 && y % 2 == 0) {
					gameboard[x][y].setBackground(Color.white);
				} else if (x % 2 == 0 && y % 2 != 0) {
					gameboard[x][y].setBackground(Color.white);
				} else if (x % 2 != 0 && y % 2 != 0) {
					gameboard[x][y].setBackground(Color.black);
				}
				if (x == 0) {
					piecebutton[aa].setIcon(P1icon);
					gameboard[x][y].add(piecebutton[aa]);
					piecebutton[aa].addActionListener(new PieceListener(x, y, aa, state, "P1"));
					piece[aa] = new Piece(x, y, aa, 0, "P1");
					aa++;
				}
				if (x == 6) {
					piecebutton[aa].setIcon(P2icon);
					gameboard[x][y].add(piecebutton[aa]);
					piecebutton[aa].addActionListener(new PieceListener(x, y, aa, state, "P2"));
					piece[aa] = new Piece(x, y, aa, 0, "P2");
					aa++;
				}
				gameboard[x][y].setLocation(y * sql, x * sql + 60);
				gameboard[x][y].setSize(sql, sql);
				gameboard[x][y].setLayout(new FlowLayout());
				gameboard[x][y].addMouseListener(new BoardListener(x, y));
				gameframe.add(BorderLayout.CENTER, gameboard[x][y]);
			}
		}
		ready.addActionListener(new readyListener());
		addgui();
	}

	public void addpiece() {
		gameframe = new JFrame();
		gameframe.setLayout(new BorderLayout());
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
		Iam.setBounds(0, 0, 150, 60);
		qScroller.setBounds(700, 60, 350, 200);
		outgoing.setBounds(700, 260, 250, 40);
		sendButton.setBounds(950, 260, 100, 40);
		labelturn.setBounds(720, 360, 140, 60);
		nowturn.setBounds(720, 310, 140, 60);
		nowtime.setBounds(860, 310, 140, 60);
		timer.setBounds(860, 360, 140, 60);
		timer.setOpaque(true);
		timer.setBackground(Color.DARK_GRAY);
		Iam.setOpaque(true); // 태현
		Iam.setBackground(Color.DARK_GRAY);
		nowturn.setOpaque(true);
		nowturn.setBackground(Color.DARK_GRAY);
		nowtime.setOpaque(true);
		nowtime.setBackground(Color.DARK_GRAY);
		labelturn.setOpaque(true);
		labelturn.setBackground(Color.DARK_GRAY);
		timer.setFont(new Font("Seif", Font.BOLD, 24));
		timer.setForeground(Color.GREEN);
		labelturn.setFont(new Font("Seif", Font.BOLD, 24));
		labelturn.setForeground(Color.GREEN);
		nowturn.setFont(new Font("Seif", Font.BOLD, 24));
		nowturn.setForeground(Color.GREEN);
		nowtime.setFont(new Font("Seif", Font.BOLD, 24));
		nowtime.setForeground(Color.GREEN);
		Iam.setFont(new Font("Seif", Font.BOLD, 30));
		Iam.setForeground(Color.RED);
		labelturn.setHorizontalAlignment(JLabel.CENTER);
		nowturn.setHorizontalAlignment(JLabel.CENTER);
		nowtime.setHorizontalAlignment(JLabel.CENTER);
		Iam.setHorizontalAlignment(JLabel.CENTER);
		timer.setHorizontalAlignment(JLabel.CENTER);
		gameframe.add(timer);
		gameframe.add(Iam);
		gameframe.add(nowturn);
		gameframe.add(nowtime);
		gameframe.add(labelturn);
		gameframe.add(qScroller);
		gameframe.add(outgoing);
		gameframe.add(sendButton);
		/*--------------------------------------------------------------------------------------*/
		gamepanel = new JPanel();
		gamepanel.setLayout(null);
		gamepanel.setBackground(Color.GRAY);
		ready.setBounds(780, 460, 150, 50);
		gameframe.add(ready);
		gameframe.add(gamepanel);
		gameframe.setSize(1100, 900);
		gameframe.setTitle("Angel and Devil Game");
		gameframe.setVisible(true);
	}

	public static void main(String[] args) {
		gui.start();
	}
}
