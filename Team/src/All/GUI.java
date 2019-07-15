package All;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GUI extends JFrame{
	static JFrame frame;
	static JFrame frame2;
	static JPanel board[][]=new JPanel[7][7];
	static JPanel board2[][] = new JPanel[2][3];
	static JButton piecebutton[]=new JButton[14];
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
	static JButton deathbutton[]= new JButton[6];
	static int click=0;
	static int pid;
	static int px,py;
	static String pteam;
	static String turn="P2";
	static Piece piece[] = new Piece[14];
	static Piece p;
	
	static String who;
	ImageIcon P1icon = new ImageIcon("image/P1.png");
	ImageIcon P2icon = new ImageIcon("image/P2.png");
	int gaming;
	int killed;
	static int deathkill = 0;
	int sql=100;
	
	

/*-----------------------------------------------------------------------------*/
	 static JTextArea incoming;
	 static JTextField outgoing;
	 static BufferedReader reader;
	 static PrintWriter writer;
	 static Socket sock;
	 static JLabel timer= new JLabel("timer");
	 
/*-----------------------------------------------------------------------------*/
		
		
	class StartListener implements ActionListener{  //시작 전 후 를 나누는 버튼에 리스너
		public void actionPerformed(ActionEvent e){
			
			Rule rule=new Rule();
			rule.go();
			
			
			writer.println(who+" | "+"님이 준비(Ready) 하였습니다.");
            writer.flush();
            start.setEnabled(false);
        	gaming=1;
			}
	}
	class SaveListener implements ActionListener{  //시작 전 후 를 나누는 버튼에 리스너
		public void actionPerformed(ActionEvent e){
			if(p1.isSelected()) {
				who = "P1";
				frame2.setVisible(false);
				addpiece();
				new ClientNetworking1().go();
			}else if (p2.isSelected()) {
				who = "P2";
				frame2.setVisible(false);
				addpiece();
				new ClientNetworking1().go();
			}
			}
	}
	
	
	
	class BoardListener implements MouseListener{   
		int x,y;
		
		BoardListener(int a, int b){
             this.x=a;
             this.y=b;
             }
		public void mouseClicked(MouseEvent e) {
			if(gaming==1) piece[pid].move(x,y); // 말이동
			click = 0;
   	 
        }
		public void mousePressed(MouseEvent e) {
	    }

	    @Override
	    public void mouseReleased(MouseEvent e) {
	    }

	    @Override//
	    public void mouseEntered(MouseEvent e) {

	    }

	    @Override//
	    public void mouseExited(MouseEvent e) {

	    }
}
	class PieceListener implements ActionListener{   
		int x, y, id; 
		String team; 

   	
        PieceListener(int x, int y,int id,String team){
            this.x=x;
            this.y=y;
            this.id=id; // ���� �ĺ���ȣ
            this.team = team;
       	 }
        public void actionPerformed(ActionEvent e){
        	
        	if(gaming==1) piece[id].kill(); // 말잡기
        	if(gaming==1) piece[id].postpiece(); // 말상태저장
        	if(gaming==0) piece[id].trans(id); //말변경( 미 구현)
 
			}
			 	}

	
    public class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            try {
                writer.println(who+" | "+outgoing.getText());
                writer.flush();
                
            }
            catch (Exception ex) {
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
		
		iu2= new JPanel();
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
		int aa=0;
		int ii = 0;
		for(int x=0; x<7; x++) {

			for(int y=0; y<7; y++) {
				if(x<2) {
					if(y<3) {
				board2[x][y]=new JPanel();
				board2[x][y].setBackground(Color.black);
				board2[x][y].setLocation((y+8) * 90,(x+8) * 90); 
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
				if(x%2==0&&y%2==0) {
					board[x][y].setBackground(Color.black);
				}else if(x%2!=0&&y%2==0) {
					board[x][y].setBackground(Color.white);
				}else if(x%2==0&&y%2!=0) {
					board[x][y].setBackground(Color.white);
				}else if(x%2!=0&&y%2!=0) {
					board[x][y].setBackground(Color.black);
				}
				if(x==0) {
					piecebutton[aa].setIcon(P1icon);
					board[x][y].add(piecebutton[aa]);
					piecebutton[aa].addActionListener(new PieceListener(x,y,aa,"P1"));
					piece[aa] = new Piece(x, y, aa,0,"P1");
					aa++;
				}
				if(x==6) {
					piecebutton[aa].setIcon(P2icon);
					board[x][y].add(piecebutton[aa]);
					piecebutton[aa].addActionListener(new PieceListener(x,y,aa,"P2"));
					piece[aa] = new Piece(x, y, aa,0,"P2");
					aa++;
				}
				board[x][y].setLocation(y * sql,x* sql+40); 
				board[x][y].setSize(sql, sql);
				board[x][y].setLayout(new FlowLayout());
				board[x][y].addMouseListener(new BoardListener(x,y));
				
				frame.add(BorderLayout.CENTER,board[x][y]);
			}
		}
		start.addActionListener(new StartListener());

		
		
		addgui();
	}
	
	public void addpiece() {
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		
		for(int i=0; i<14; i++) {
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
        qScroller.setBounds(800, 100, 350, 200);
        outgoing.setBounds(800, 300, 250, 40);
        sendButton.setBounds(1050, 300, 100, 40);
        frame.add(qScroller);
        frame.add(outgoing);
        frame.add(sendButton);
        /*--------------------------------------------------------------------------------------*/
		
		
        timer.setBounds(850,350,140,60);
		
	
		
		
		iu= new JPanel();
		iu.setLayout(null);
		iu.setBackground(Color.GRAY);
	
		
		start.setBounds(800, 500, 130, 40);
		frame.add(timer);
		frame.add(start);
		frame.add(iu);
		frame.setSize(1200, 1200);
		frame.setTitle("Angel and Devil Game");
		frame.setVisible(true);
		
		
		
	}
	

	
	public static void main(String[] args) {
	
		GUI gui=new GUI();
		gui.login();
		





		
	}
}