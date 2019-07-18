package All;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Piece extends GUI {
	int x, y, id, state;
	String team;
	int ascore, dscore;
	static int count = 3, count2 = 0;
	static int ii = 0;

	Piece(int x, int y, int id, int state, String team) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.state = state;
		this.team = team;
	}

	void postpiece() {
		if (click == 0) {
			if (who == turn && who == team) {
				px = this.x;
				py = this.y;
				pid = this.id;
				pteam = who;
				click++;
				/*
				 * if (state == 1) { piecebutton[pid].setIcon(new ImageIcon("image/" + pteam +
				 * "_Devil" + "_clik.png")); }
				 */
				piecebutton[pid].setIcon(new ImageIcon("image/" + pteam + "_clik.png"));

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
				} catch (Exception ex) {
					ex.printStackTrace();
				} // <--------------------------------------------- 네트워크

			} else {
				JOptionPane.showMessageDialog(null, "이동범위 밖입니다!");

				/*
				 * if (state == 1) { piecebutton[pid].setIcon(new ImageIcon("image/" + turn +
				 * "_Devil" + ".png")); }
				 */
				piecebutton[pid].setIcon(new ImageIcon("image/" + turn + ".png"));
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
				piecebutton[pid].setIcon(new ImageIcon("image/" + pteam + ".png"));
			} else if (((px - 1 == x || x == px + 1) && (py == y) || ((py - 1 == y || y == py + 1) && (px == x)))) {
				try { // <--------------------------------------------- 네트워크
					writer.println(pid + ":" + id);
					writer.flush();
					if (who == "P1") { // 태현
						writer.println("Player2"); // 태현
						writer.flush(); // 태현
						piece[id].killtrans();
					} else {// 태현
						writer.println("Player1"); // 태현
						writer.flush(); // 태현
						piece[id].killtrans();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} // <--------------------------------------------- 네트워크

				click = 0;
				killed = 1;

			} else {
				JOptionPane.showMessageDialog(null, "너무 멀리 있어 잡을수 없습니다!!");
				piecebutton[pid].setIcon(new ImageIcon("image/" + turn + ".png"));

			}

			/*
			 * if (state == 1) { piecebutton[pid].setIcon(new ImageIcon("image/" + turn +
			 * "_Devil" + ".png")); } piecebutton[pid].setIcon(new ImageIcon("image/" + turn
			 * + ".png"));
			 */
		}

	}

	void win(int id, int x, int y) {

	}

	void trans(int id) {

		piecebutton[id].setIcon(new ImageIcon("image/" + turn + "_Devil" + ".png"));

	}

	public void killtrans() {
	if(state==0) {
		if(team.equals("P1")) {
			deathbutton[ii].setIcon(new ImageIcon("image/" + "P1" + ".png"));
		}
		else {
			deathbutton[ii].setIcon(new ImageIcon("image/" + "P2" + ".png"));
		}
		System.out.println(ii);
		++ii;
	}
	else if(state==1) {
		deathbutton[ii].setIcon(new ImageIcon("image/" + "Devil" + ".png"));
		System.out.println(ii);
		++ii;
		++deathkill;
		if(deathkill==3) {
			JOptionPane.showMessageDialog(null, "악마 3마리를 잡으셔서 게임에서 졌습니다");
			
		}
	}
}
}