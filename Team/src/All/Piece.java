package All;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Piece extends GUI {
	int x, y, id, state;
	String team;
	int ascore, dscore;
	static int count = 3, count2 = 0;
	static int ii = 0;
	int cc;
	END END = new END();

	Piece(int x, int y, int id, int state, String team) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.state = state;
		this.team = team;
	}
void save() {
	px = this.x;
	py = this.y;
	String pturn = this.turn;
}
	void postpiece() {
		if (click == 0) {
			if (who == turn && who == team) {
				px = this.x;
				py = this.y;
				pid = this.id;
				pteam = who;
				click++;
				if (state == 1) {
					piecebutton[pid].setIcon(new ImageIcon("image/" + pteam + "_Devil" + "_clik.png"));
				}

				else {
					piecebutton[pid].setIcon(new ImageIcon("image/" + pteam + "_clik.png"));
				}

			} else if (killed == 1) {
				killed = 0;

			} else {
				JOptionPane.showMessageDialog(null, "순서가 아닙니다.");
			}

		} else
			click = 0;

	}

	public void move(int x, int y) {
		

		if (click == 1) {
			if (((px - 1 == x || x == px + 1) && (py == y) || ((py - 1 == y || y == py + 1) && (px == x)))) {
				try { // <--------------------------------------------- 네트워크

					writer.println(px + ":" + py + ":" + x + ":" + y + ":" + pid);
					writer.flush();
				

					if (who == "P1") { // 태현
						writer.println("Player2"); // 태현
						writer.flush(); // 태현
					} else { // 태현
						writer.println("Player1"); // 태현
						writer.flush(); // 태현
					}
					if(who == "P1") {
						for(cc=0;cc<7;cc++) {
							if(piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							}else if(piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					}else if(who == "P2"){
						for(cc=7;cc<14;cc++) {
							if(piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							}else if(piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}							
					}
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				} // <--------------------------------------------- 네트워크

			} else {
				JOptionPane.showMessageDialog(null, "이동범위 밖입니다!");
				if(who == "P1") {
					for(cc=0;cc<7;cc++) {
						if(piece[cc].state == 1) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
						}else if(piece[cc].state == 0) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
						}
					}
				}else if(who == "P2"){
					for(cc=7;cc<14;cc++) {
						if(piece[cc].state == 1) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
						}else if(piece[cc].state == 0) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
						}							
				}
				}
				click = 0;
			}

		} else {
			JOptionPane.showMessageDialog(null, "말을 먼저 선택해주세요!");
			click = 0;
		}

	}

	void kill() {

		if (click == 1) {
			if (team == who) {
				JOptionPane.showMessageDialog(null, "자신의 말은 잡을수 없습니다.");
				if(who == "P1") {
					for(cc=0;cc<7;cc++) {
						if(piece[cc].state == 1) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
						}else if(piece[cc].state == 0) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
						}
					}
				}else if(who == "P2"){
					for(cc=7;cc<14;cc++) {
						if(piece[cc].state == 1) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
						}else if(piece[cc].state == 0) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
						}							
				}
				}
			} else if (((px - 1 == x || x == px + 1) && (py == y) || ((py - 1 == y || y == py + 1) && (px == x)))) {
				try { // <--------------------------------------------- 네트워크
					writer.println(pid + ":" + id);
					writer.flush();
					if (who == "P1") { // 태현
						writer.println("Player2"); // 태현
						writer.flush(); // 태현
						piece[id].killtrans();
						for(cc=0;cc<7;cc++) {
							if(piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							}else if(piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					} else {// 태현
						writer.println("Player1"); // 태현
						writer.flush(); // 태현
						piece[id].killtrans();
						for(cc=7;cc<14;cc++) {
							if(piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							}else if(piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}							
					}}}
					
					
					
					 catch (Exception ex) {
					ex.printStackTrace();
				} // <--------------------------------------------- 네트워크

				click = 0;
				killed = 1;

			} else {
				JOptionPane.showMessageDialog(null, "너무 멀리 있어 잡을수 없습니다!!");
				if(who == "P1") {
					for(cc=0;cc<7;cc++) {
						if(piece[cc].state == 1) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
						}else if(piece[cc].state == 0) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
						}
					}
				}else if(who == "P2"){
					for(cc=7;cc<14;cc++) {
						if(piece[cc].state == 1) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
						}else if(piece[cc].state == 0) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
						}							
				}
				}

			}
		}

	}
	public void win(int state) {
		
		switch (state) {
		case 1:
			for(cc=0;cc<7;cc++) {
			if (who == "P1") {
				if (piece[cc].x == 6 && piece[cc].y == 0) {
					click=0;
					JOptionPane.showMessageDialog(null, "1악마는 이동할 수 없습니다.");
					re();
				} else if (piece[cc].x == 6 && piece[cc].y == 6) {
					JOptionPane.showMessageDialog(null, "2악마는 이동할 수 없습니다.");
					re();
				}
				}
			}
				for(cc=7;cc<14;cc++) {
				if (who == "P2") {
					if (piece[cc].x == 0 && piece[cc].y == 0) {
						JOptionPane.showMessageDialog(null, "3악마는 이동할 수 없습니다.");
						re();
					} else if (piece[cc].x == 0 && piece[cc].y == 6) {
						JOptionPane.showMessageDialog(null, "4악마는 이동할 수 없습니다.");
						re();
					}
				}
				}
			

		case 0:
			for(cc=0;cc<7;cc++) {
			if (who == "P1") {
				if (piece[cc].x == 6 &&piece[cc].y == 0) {
					JOptionPane.showMessageDialog(null, "5P1이 이겼습니다.");
					END.game(who);
				} else if (piece[cc].x == 6 && piece[cc].y == 6) {
					JOptionPane.showMessageDialog(null, "6P1이 이겼습니다.");
					END.game(who);
				}
			}
			}
			for(cc=7;cc<14;cc++) {
			if (who == "P2") {
				if (piece[cc].x ==0 && piece[cc].y == 0) {
					JOptionPane.showMessageDialog(null, "7P2가 이겼습니다.");
					END.game(who);
				} else if (piece[cc].x == 0 && piece[cc].y == 6) {
					JOptionPane.showMessageDialog(null, "8P2가 이겼습니다.");
					END.game(who);
				}
			}
			}
				
		}
	}
	void re() {

			piecebutton[id].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
		
	}

	void trans(int id) {
		if(piece[id].state == 0) {

		writer.println(id+":"+"1"+":"+" "+":"+" "); // 태현
		writer.flush(); // 태현
		piecebutton[id].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
		}else if(piece[id].state == 1){
			writer.println(id+":"+"0"+":"+" "+":"+" "); // 태현
			writer.flush(); // 태현
			piecebutton[id].setIcon(new ImageIcon("image/" + who +  ".png"));
		}

	}

	public void killtrans() {
	if(state==0) {
		if(team.equals("P1")) {
			deathbutton[ii].setIcon(new ImageIcon("image/" + "P1" + ".png"));
		}
		else {
			deathbutton[ii].setIcon(new ImageIcon("image/" + "P2" + ".png"));
		}
		++ii;
		++angelkill;
		if(angelkill==4) {
			JOptionPane.showMessageDialog(null, "천사 4마리를 잡으셔서 게임에서 졌습니다");
			END.game(turn);
		}
	}
	else if(state==3) {
		deathbutton[ii].setIcon(new ImageIcon("image/" + who +"_Devil" + ".png"));
		System.out.println(ii);
		++ii;
		++devilkill;
		if(devilkill==1) {
			if(who.equals("P1")) {
				writer.println("플레이어2"+":"+" "+":"+" "+":"+" "+":"+" "+":"+" "+":"+" "+":"+" "); // 태현
				writer.flush(); // 태현
			}else {
				writer.println("플레이어1"+":"+" "+":"+" "+":"+" "+":"+" "+":"+" "+":"+" "+":"+" "); // 태현
				writer.flush(); // 태현
			}
			
		}
	}
}
}