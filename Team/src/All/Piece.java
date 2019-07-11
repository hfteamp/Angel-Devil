package All;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Piece extends GUI {
	int x, y, id, state;
	String team;
	int ascore, dscore;
	static int count=0,count2=0;
	Piece(int x,int y,int id,int state,String team) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.state=state;
		this.team = team;
	}
	void postpiece() {
		if (click == 0) {
			if (team == turn) {
				px = this.x;
				py = this.y;
				pid = this.id;
				pteam = this.team;
				click++;	
				piecebutton[pid].setIcon(new ImageIcon("image/" + pteam + "_clik.png"));


				
			}else if(killed==1){
				killed = 0;
				
			}else {
				JOptionPane.showMessageDialog(null, "순서가 아닙니다.");
			}
    	}else
    		click=0;



	}

	public void move(int x, int y) {
		
		if (click == 1) {
			if (((px - 1 == x || x == px + 1) && (py - 1 != y && y != py + 1))
					|| ((py - 1 == y || y == py + 1) && (px - 1 != x && x != px + 1))) {
	            try {                            // <--------------------------------------------- 네트워크
	                writer.println(px+":"+py+":"+x+":"+y+":"+pid);
	                writer.flush();              
	            }
	            catch (Exception ex) {
	                ex.printStackTrace();
	            } 								// <--------------------------------------------- 네트워크

			


			} else {
				JOptionPane.showMessageDialog(null, "범위 아웃");
				piecebutton[pid].setIcon(new ImageIcon("image/" + turn + ".png"));
				click = 0;

			}

		} else {
			JOptionPane.showMessageDialog(null, "말을 먼저 선택");
			click = 0;
		} 
		
		
	

	}

	void kill(int x, int y) {

	if (click == 1) {
				if (team == turn) {
					JOptionPane.showMessageDialog(null, "자신의 말은 잡을수 없습니다.");
					piecebutton[pid].setIcon(new ImageIcon("image/" + pteam + ".png"));
					click = 0;
				} else if (((px - 1 == x || x == px + 1) && (py - 1 != y && y != py + 1))
						|| ((py - 1 == y || y == py + 1) && (px - 1 != x && x != px + 1))) {
		            try {                            // <--------------------------------------------- 네트워크
		                writer.println(pid+":"+id);
		                writer.flush();              
		            }
		            catch (Exception ex) {
		                ex.printStackTrace();
		            } 								// <--------------------------------------------- 네트워크

		            click = 0;
		            killed = 1;
					
		
				
				} else {
					JOptionPane.showMessageDialog(null, "이동범위 벗어났습니다.");
					piecebutton[pid].setIcon(new ImageIcon("image/" + turn + ".png"));
					
				}

			}
		

	}

	void trans(int id) {
		
	
		/*if(id<7) {
			if(count<3) {
		piecebutton[id].setIcon(new ImageIcon("image/" + "Devil" + ".png"));
		count++;
			}
		}else {
			if(count2<3) {
			piecebutton[id].setIcon(new ImageIcon("image/" + "Angel" + ".png"));
			count2++;
			}
		}*/
		

	}
}
