package All;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame{
	static JFrame frame;
	static JPanel board[][]=new JPanel[7][7];
	static JButton piecebutton[]=new JButton[14];
	static JPanel iu;
	static JTextArea chat;
	static JTextField input;
	static JButton submit;
	static JPanel deathzone;
	static JButton start = new JButton("시작");
	int sql=100;
	static int click=0;
	static int pid;
	static int px,py;
	static String pteam;
	static String turn="Devil";
	static Piece piece[] = new Piece[14];
	static Piece p;
	static ImageIcon icon = new ImageIcon("image/Angel.png");
	static ImageIcon icon2 = new ImageIcon("image/Devil.png");
	static int gaming;
		
		
	class StartListener implements ActionListener{  //시작 전 후 를 나누는 버튼에 리스너
		public void actionPerformed(ActionEvent e){
        	gaming=1;
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

        
	
	
	public void addboard() {
		int aa=0;
		for(int x=0; x<7; x++) {
			for(int y=0; y<7; y++) {
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
					piecebutton[aa].setIcon(icon);
					board[x][y].add(piecebutton[aa]);
					piecebutton[aa].addActionListener(new PieceListener(x,y,aa,"Angel"));
					piece[aa] = new Piece(x, y, aa,"Angel");
					aa++;
				}
				if(x==6) {
					piecebutton[aa].setIcon(icon2);
					board[x][y].add(piecebutton[aa]);
					piecebutton[aa].addActionListener(new PieceListener(x,y,aa,"Devil"));
					piece[aa] = new Piece(x, y, aa, "Devil");
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
		iu= new JPanel();
		iu.setLayout(null);
		iu.setBackground(Color.GRAY);
		
		start.setBounds(800, 500, 100, 100);
		frame.add(start);
		frame.add(iu);
		frame.setSize(1000, 1000);
		frame.setTitle("Angel and Devil Game");
		frame.setVisible(true);
		
		
	}
	

	
	public static void main(String[] args) {
		GUI gui=new GUI();
		gui.addpiece();
	}
}