package All;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GUI extends JFrame{
	static JFrame frame;
	static JPanel board[][]=new JPanel[7][7];
	static JPanel board2[][] = new JPanel[2][3];
	static JButton piecebutton[]=new JButton[14];
	static JPanel iu;
	static JTextArea chat;
	static JTextField input;
	static JButton submit;
	static JButton savebutton;
	static JButton loadbutton;
	static JButton resetbutton;
	static JButton lockbutton;
	static JButton unlockbutton;
	static JButton start = new JButton("시작");
	static JButton deathbutton[]= new JButton[5];
	int sql=100;
	static int click=0;
	static int pid;
	static int px,py;
	static String pteam;
	static String turn="P2";
	static Piece piece[] = new Piece[14];
	static Piece p;
	static ChangeImage c = new ChangeImage();
	static ImageIcon P1icon = new ImageIcon("image/P1.png");
	static ImageIcon P2icon = new ImageIcon("image/P2.png");
	static int gaming;
	static int killed;
	static String who;

/*-----------------------------------------------------------------------------*/
	 static JTextArea incoming;
	 static JTextField outgoing;
	 static BufferedReader reader;
	 static PrintWriter writer;
	 static Socket sock;
		
	 
/*-----------------------------------------------------------------------------*/
		
		
	class StartListener implements ActionListener{  //시작 전 후 를 나누는 버튼에 리스너
		public void actionPerformed(ActionEvent e){
        	gaming=1;
			}
	}
	class SaveListener implements ActionListener{  //시작 전 후 를 나누는 버튼에 리스너
		public void actionPerformed(ActionEvent e){
			
			}
	}
	class LoadListener implements ActionListener{  //시작 전 후 를 나누는 버튼에 리스너
		public void actionPerformed(ActionEvent e){
        	
			}
	}
	class ResetListener implements ActionListener{  //시작 전 후 를 나누는 버튼에 리스너
		public void actionPerformed(ActionEvent e){
        	
			}
	}
	class LockListener implements ActionListener{  //시작 전 후 를 나누는 버튼에 리스너
		public void actionPerformed(ActionEvent e){
			c.lock();
			}
	}
	class UnlockListener implements ActionListener{  //시작 전 후 를 나누는 버튼에 리스너
		public void actionPerformed(ActionEvent e){
        	c.unlock();
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
        	
        	if(gaming==1) piece[id].kill(x,y); // 말잡기
        	if(gaming==1) piece[id].postpiece(); // 말상태저장
        	if(gaming==0) piece[id].trans(id); //말변경( 미 구현)
 
			}
			 	}

	
    public class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            try {
                writer.println(outgoing.getText());
                writer.flush();
                
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }
        
	
	
	public void addboard() {
		int aa=0;
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
		savebutton = new JButton("save");
		loadbutton = new JButton("load");
		resetbutton = new JButton("reset");
		lockbutton = new JButton("lock");
		unlockbutton= new JButton("unloack");
		
		savebutton.addActionListener(new SaveListener());
		loadbutton.addActionListener(new LoadListener());
		resetbutton.addActionListener(new ResetListener());
		lockbutton.addActionListener(new LockListener());
		unlockbutton.addActionListener(new UnlockListener());
		
		
		iu= new JPanel();
		iu.setLayout(null);
		iu.setBackground(Color.GRAY);
	
		savebutton.setBounds(900, 100, 100, 40);
		loadbutton.setBounds(900, 140, 100, 40);
		resetbutton.setBounds(900, 180, 100, 40);
		lockbutton.setBounds(900, 220, 100, 40);
		unlockbutton.setBounds(900, 260, 100, 40);
		start.setBounds(800, 500, 100, 40);
		frame.add(start);
		frame.add(iu);
		frame.setSize(1200, 1200);
		frame.setTitle("Angel and Devil Game");
		frame.setVisible(true);
		
		
	}
	

	
	public static void main(String[] args) {
		GUI gui=new GUI();
		gui.addpiece();
		System.out.println(who);
			new ClientNetworking1().go();
			System.out.println(who);




		
	}
}