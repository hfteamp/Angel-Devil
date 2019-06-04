package All;

import javax.swing.*;
import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.*;
public class Game {
	JFrame frame;
	JPanel panel[][] = new JPanel[7][7];
	JButton button[] = new JButton[14];
	JPanel panel2;
	int sql = 100;
	int cnt=0;
	int selected = 0; // 이전 말의 번호
    int z,k; //이전 xy 좌표
    String Turn="Devil";
    String who;
    int an=0,de=0; // 임시
    JTextField Angelscore = new JTextField("0");
    JTextField Devilscore = new JTextField("0");
    JLabel label = new JLabel("차례(턴) :");
    JTextField whotturn = new JTextField("Devil");

	ImageIcon icon = new ImageIcon("image/Angel.png");
	ImageIcon icon2 = new ImageIcon("image/Devil.png");
	piece state[] = new piece[14]; //말을 올려 놓을 보드
	String Stinzg="";
sss


	

	
	class MyMouseListener implements MouseListener{   //패널패널패널패널패널패널패널패널패널패널패널패널패널패널패널패널패널패널패널패널패널패널패널패널패널패널패널패널패널패널패널패널패널패널
		int x,y;
		
		MyMouseListener(int a, int b){ //생성자로 내용물 설정
             this.x=a;
             this.y=b;
            

        	 }
		 
		public void mouseClicked(MouseEvent e) {
			
				if(cnt==1) {
					if(((z-1==x||x==z+1)&&(k-1!=y&&y!=k+1))||((k-1==y||y==k+1)&&(z-1!=x&&x!=z+1))) {
						panel[x][y].add(button[selected]);
						button[selected].setIcon(new ImageIcon("image/"+Turn+".png"));
						panel[z][k].removeAll();
						panel[z][k].revalidate();
						panel[z][k].repaint();
						state[selected].i=state[selected].i - (state[selected].i-x);
						state[selected].j=state[selected].j - (state[selected].j-y);
						cnt=0;
	            		if(Turn=="Devil"){
	               			Turn="Angel";
	               			whotturn.setText(Turn);
	               		}else {
	               			Turn="Devil";
	               			whotturn.setText(Turn);
	               		}
						
					}else {
           			 JOptionPane.showMessageDialog(null, "이동할 수 없습니다.");
           			 button[selected].setIcon(new ImageIcon("image/"+Turn+".png"));
           			 cnt=0;
           			 
           		 }
					
					
				}else {
					 JOptionPane.showMessageDialog(null, "말을 먼저 선택해주세요.");
					 cnt=0;
				}
			
	    }

	    @Override
	    public void mousePressed(MouseEvent e) {
	    }

	    @Override
	    public void mouseReleased(MouseEvent e) {
	    }

	    @Override//마우스가 버튼 안으로 들어오면 빨간색으로 바뀜
	    public void mouseEntered(MouseEvent e) {

	    }

	    @Override//마우스가 버튼 밖으로 나가면 노란색으로 바뀜
	    public void mouseExited(MouseEvent e) {

	    }
	    
	}
    
    class ClickListener implements ActionListener{     //말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말말
    	 private int i, j, select; //ij 움질일 말위치 select 움직이는 말번호
    	 String nowturn; // 누구 말?

    	
         ClickListener(int a, int b,int c,String turn){ //생성자로 내용물 설정
             this.i=a;
             this.j=b;
             this.select=c; // 말의 번호
             this.nowturn = turn;
        	 }
           
             public void actionPerformed(ActionEvent e){ //select = 현재 클릭( 내말 ) selected = 이전 클릭
            	 if(cnt==0) {
            		 if(nowturn==Turn) {
               		z=state[select].i;
               		k=state[select].j;
               		selected=state[select].number;
               		who=nowturn;
               		button[selected].setIcon(new ImageIcon("image/"+nowturn+"_clik.png"));
               		cnt++;
            		 }else {
            			 JOptionPane.showMessageDialog(null, "당신 턴이 아닙니다.");
            		 }
            	 }else if(cnt==1) {
            		 if(who==nowturn) {
            			 JOptionPane.showMessageDialog(null, "자신의 말은 잡을수 없습니다.");
            			 button[selected].setIcon(new ImageIcon("image/"+Turn+".png"));
            			 cnt=0;
            		 }else if(((z-1==state[select].i||state[select].i==z+1)&&(k-1!=state[select].j&&state[select].j!=k+1))||((k-1==state[select].j||state[select].j==k+1)&&(z-1!=state[select].i&&state[select].i!=z+1))) {
            			 
            		    panel[state[select].i][state[select].j].removeAll();
						panel[state[select].i][state[select].j].revalidate();
						panel[state[select].i][state[select].j].repaint();
						panel[state[select].i][state[select].j].add(button[selected]);
						button[selected].setIcon(new ImageIcon("image/"+Turn+".png"));
						panel[state[selected].i][state[selected].j].removeAll();
						panel[state[selected].i][state[selected].j].revalidate();
						panel[state[selected].i][state[selected].j].repaint();

						state[selected].i=state[select].i;
						state[selected].j=state[select].j;
						cnt=0;
						if(who=="Angel") {
							an++;
							Angelscore.setText(Integer.toString(an));
						}else {
							de++;
							Devilscore.setText(Integer.toString(de));
						}
 	            		if(Turn=="Devil"){
 	               			Turn="Angel";
 	               			whotturn.setText(Turn);
 	               		}else {
 	               			Turn="Devil";
 	               			whotturn.setText(Turn);
 	               		}
            		 }else {
            			 JOptionPane.showMessageDialog(null, "이동할 수 없습니다.");
            			 button[selected].setIcon(new ImageIcon("image/"+Turn+".png"));
               			 cnt=0;
            		 }
 					
            		 
            	 }
            			 
            		 }
            	 }
            	 
            	
            
        
    
	public void GUI() {
		int aa=0;
		panel2 = new JPanel();
		frame = new JFrame();
		for(int a=0;a<14;a++) {
			button[a] = new JButton();	
			
			button[a].setPreferredSize(new Dimension(80, 80));
			button[a].setBorderPainted(false); // 버튼 테두리설정
			button[a].setFocusPainted(false);  // 버튼 영역 배경 표시 설정
			button[a].setContentAreaFilled(false); //포커스 표시 설정
		}
		for(int x=0;x<7;x++) {
			for(int y=0;y<7;y++) {	

				panel[x][y] = new JPanel();
				
				 if(x%2==0&&y%2==0) {
	    	           panel[x][y].setBackground(Color.black);
	    	            }else if(x%2!=0&&y%2==0) {
	    	            	panel[x][y].setBackground(Color.white);
	    	            }else if(x%2==0&&y%2!=0) {
	    	            	panel[x][y].setBackground(Color.white);
	    	                }else if(x%2!=0&&y%2!=0) {
	    	               panel[x][y].setBackground(Color.black);
	    	                }
				 
				 if(x==0) {
					    button[aa].setIcon(icon);
						panel[x][y].add(button[aa]);
						button[aa].addActionListener(new ClickListener(x,y,aa,"Angel"));
						state[aa] = new piece(x,y,aa);
						aa++;
					}if(x==6) {
						button[aa].setIcon(icon2);
						panel[x][y].add(button[aa]);
						button[aa].addActionListener(new ClickListener(x,y,aa,"Devil"));
						state[aa] = new piece(x,y,aa);
						aa++;
					}
					
				panel[x][y].setLocation(y * sql,x* sql+40); 
				panel[x][y].setSize(100, 100);
				panel[x][y].setLayout(new FlowLayout());
				panel[x][y].addMouseListener(new MyMouseListener(x,y));
				
				
				frame.add(panel[x][y]);


			}
		}
		panel2.setLayout(null);
		panel2.setBackground(Color.gray);
		Angelscore.setHorizontalAlignment(JTextField.CENTER);
		Angelscore.setFont(new Font("Serif", Font.BOLD,30));
		Angelscore.setEnabled(false);
		Angelscore.setBounds(750,40, 100, 50);
		panel2.add(Angelscore);
		Devilscore.setHorizontalAlignment(JTextField.CENTER);
		Devilscore.setFont(new Font("Serif", Font.BOLD,30));
		Devilscore.setEnabled(false);
		Devilscore.setBounds(750,690, 100, 50);
		panel2.add(Devilscore);
		whotturn.setHorizontalAlignment(JTextField.CENTER);
		whotturn.setFont(new Font("Serif", Font.BOLD,30));
		whotturn.setEnabled(false);
		whotturn.setBounds(400,770, 100, 50);
		panel2.add(whotturn);
		label.setHorizontalAlignment(JTextField.RIGHT);
		label.setFont(new Font("Serif", Font.BOLD,30));
		label.setBounds(170,745, 200, 100);
		panel2.add(label);
		frame.add(panel2);
		frame.setSize(900, 900);
		frame.setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		new Game();


	}
	Game(){
		GUI();	
	}

}
