package All;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Piece extends Gui {
	int x, y, id, state;
	String team;
	static int ii = 0;
	int cc;
	EndGame END = new EndGame();

	Piece(int x, int y, int id, int state, String team) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.state = state;
		this.team = team;
	}

	void pastpiece() {
		if (click == 0) {
			if (who == turn && who == team) {
				px = this.x;
				py = this.y;
				pid = this.id;
				pstate = this.state;
				pteam = who;
				click++;
				if (state == 1) {
					piecebutton[pid].setIcon(new ImageIcon("image/" + pteam + "_Devil" + "_clik.png"));
				} else {
					piecebutton[pid].setIcon(new ImageIcon("image/" + pteam + "_clik.png"));
				}
			} else if (check == 2) {
				JOptionPane.showMessageDialog(null, "악마는 이동할 수 없습니다.");
				check = 0;
			} else if (killed != 1) {
				JOptionPane.showMessageDialog(null, "순서가 아닙니다.");
			}
			if (killed == 1) {
				killed = 0;
			}

		} else
			click = 0;
	}

	public void move(int x, int y) {
		if (click == 1) {
			if (((px - 1 == x || x == px + 1) && (py == y) || ((py - 1 == y || y == py + 1) && (px == x)))) {
				try {
					writer.println(px + ":" + py + ":" + x + ":" + y + ":" + pid);
					writer.flush();
					if (who == "P1") {
						writer.println("Player2");
						writer.flush();
					} else {
						writer.println("Player1");
						writer.flush();
					}
					if (who == "P1") {
						for (cc = 0; cc < 7; cc++) {
							if (piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							} else if (piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					} else if (who == "P2") {
						for (cc = 7; cc < 14; cc++) {
							if (piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							} else if (piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "이동범위 밖입니다!");
				if (who == "P1") {
					for (cc = 0; cc < 7; cc++) {
						if (piece[cc].state == 1) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
						} else if (piece[cc].state == 0) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
						}
					}
				} else if (who == "P2") {
					for (cc = 7; cc < 14; cc++) {
						if (piece[cc].state == 1) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
						} else if (piece[cc].state == 0) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
						}
					}
				}

			}
		} else if (check == 2) {
			JOptionPane.showMessageDialog(null, "악마는 이동할 수 없습니다.");
			check = 0;

		} else if (check == 1) {
			check = 0;
		} else {
			JOptionPane.showMessageDialog(null, "말을 먼저 선택해주세요!");
		}
		click = 0;
	}

	void kill() {

		if (click == 1) {
			if(team==who||piece[id].x==piece[id].px ) {
			
				if (who == "P1") {
					for (cc = 0; cc < 7; cc++) {
						if (piece[cc].state == 1) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
						} else if (piece[cc].state == 0) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
						}
					}
				} else if (who == "P2") {
					for (cc = 7; cc < 14; cc++) {
						if (piece[cc].state == 1) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
						} else if (piece[cc].state == 0) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
						}
					}
				}
				click=0;
			}
		else if (((px - 1 == x || x == px + 1) && (py == y) || ((py - 1 == y || y == py + 1) && (px == x)))) {
				try {
					writer.println(pid + ":" + id);
					writer.flush();
					if (who == "P1") {
						writer.println("Player2");
						writer.flush();
						piece[id].killtrans();
						for (cc = 0; cc < 7; cc++) {
							if (piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							} else if (piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					} else {
						writer.println("Player1");
						writer.flush();
						piece[id].killtrans();
						for (cc = 7; cc < 14; cc++) {
							if (piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							} else if (piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				click = 0;
				killed = 1;
			} else {
				JOptionPane.showMessageDialog(null, "너무 멀리 있어 잡을수 없습니다!!");
				if (who == "P1") {
					for (cc = 0; cc < 7; cc++) {
						if (piece[cc].state == 1) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
						} else if (piece[cc].state == 0) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
						}
					}
				} else if (who == "P2") {
					for (cc = 7; cc < 14; cc++) {
						if (piece[cc].state == 1) {
							piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
						} else if (piece[cc].state == 0) {
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
			if (who == "P1") {
				if ((piece[id].x == 6 && piece[id].y == 0)) {
					if (who == "P1") {
						for (cc = 0; cc < 7; cc++) {
							if (piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							} else if (piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					} else if (who == "P2") {
						for (cc = 7; cc < 14; cc++) {
							if (piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							} else if (piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					}
					click = 0;
					check = 2;
					break;
				} else if (piece[id].x == 6 && piece[id].y == 6) {
					if (who == "P1") {
						for (cc = 0; cc < 7; cc++) {
							if (piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							} else if (piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					} else if (who == "P2") {
						for (cc = 7; cc < 14; cc++) {
							if (piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							} else if (piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					}
					click = 0;
					check = 2;
					break;
				}
			}
			if (who == "P2") {
				if (piece[id].x == 0 && piece[id].y == 0) {
					if (who == "P1") {
						for (cc = 0; cc < 7; cc++) {
							if (piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							} else if (piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					} else if (who == "P2") {
						for (cc = 7; cc < 14; cc++) {
							if (piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							} else if (piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					}
					click = 0;
					check = 2;
					break;
				} else if (piece[id].x == 0 && piece[id].y == 6) {
					if (who == "P1") {
						for (cc = 0; cc < 7; cc++) {
							if (piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							} else if (piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					} else if (who == "P2") {
						for (cc = 7; cc < 14; cc++) {
							if (piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							} else if (piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					}
					click = 0;
					check = 2;
					break;
				}
			}

		case 0:
			if (who == turn && click != 0) {
				if (who == "P1") {
					if (piece[id].x == 6 && piece[id].y == 0) {
						check++;
						piece[id].kill();
						if (who.equals("P1") && devilkill!=3) {
							writer.println("플레이어1" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":"
									+ " " + ":" + " ");
							writer.flush();
						} else if(devilkill!=3) {
							writer.println("플레이어2" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":"
									+ " " + ":" + " ");
							writer.flush();
						}
					} else if (piece[id].x == 6 && piece[id].y == 6) {
						check++;
						piece[id].kill();
						if (who.equals("P1") && devilkill!=3) {
							writer.println("플레이어1" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":"
									+ " " + ":" + " ");
							writer.flush();
						} else if(devilkill!=3) {
							writer.println("플레이어2" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":"
									+ " " + ":" + " ");
							writer.flush();
						}
					}
				}
				if (who == "P2") {
					if (piece[id].x == 0 && piece[id].y == 0) {
						check++;
						piece[id].kill();
						if (who.equals("P1") && devilkill!=3) {
							writer.println("플레이어1" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":"
									+ " " + ":" + " ");
							writer.flush();
						} else if(devilkill!=3) {
							writer.println("플레이어2" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":"
									+ " " + ":" + " ");
							writer.flush();
						}
					} else if (piece[id].x == 0 && piece[id].y == 6) {
						check++;
						piece[id].kill();
						if (who.equals("P1") && devilkill!=3) {
							writer.println("플레이어1" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":"
									+ " " + ":" + " ");
							writer.flush();
						} else if(devilkill!=3) {
							writer.println("플레이어2" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":"
									+ " " + ":" + " ");
							writer.flush();
						}
					}
				}
			}
		}
	}

	public void win(int state, int x, int y) {
		switch (state) {
		case 1:
			if (who == "P1") {
				if ((x == 6 && y == 0)) {
					if (who == "P1") {
						for (cc = 0; cc < 7; cc++) {
							if (piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							} else if (piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					} else if (who == "P2") {
						for (cc = 7; cc < 14; cc++) {
							if (piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							} else if (piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					}
					click = 0;
					check = 2;
					break;
				} else if (x == 6 && y == 6) {
					if (who == "P1") {
						for (cc = 0; cc < 7; cc++) {
							if (piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							} else if (piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					} else if (who == "P2") {
						for (cc = 7; cc < 14; cc++) {
							if (piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							} else if (piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					}
					click = 0;
					check = 2;
					break;
				}
			}
			if (who == "P2") {
				if (x == 0 && y == 0) {
					if (who == "P1") {
						for (cc = 0; cc < 7; cc++) {
							if (piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							} else if (piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					} else if (who == "P2") {
						for (cc = 7; cc < 14; cc++) {
							if (piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							} else if (piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					}
					click = 0;
					check = 2;
					break;
				} else if (x == 0 && y == 6) {
					if (who == "P1") {
						for (cc = 0; cc < 7; cc++) {
							if (piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							} else if (piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					} else if (who == "P2") {
						for (cc = 7; cc < 14; cc++) {
							if (piece[cc].state == 1) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
							} else if (piece[cc].state == 0) {
								piecebutton[cc].setIcon(new ImageIcon("image/" + who + ".png"));
							}
						}
					}
					click = 0;
					check = 2;
					break;
				}
			}
		case 0:
			if (who == turn && click != 0) {
				if (who == "P1") {
					if (x == 6 && y == 0) {
						piece[pid].move(x, y);
						check++;
						if (who.equals("P1")) {
							writer.println("플레이어1" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":"
									+ " " + ":" + " ");
							writer.flush();
						} else {
							writer.println("플레이어2" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":"
									+ " " + ":" + " ");
							writer.flush();
						}
					} else if (x == 6 && y == 6) {
						piece[pid].move(x, y);
						check++;
						if (who.equals("P1")) {
							writer.println("플레이어1" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":"
									+ " " + ":" + " ");
							writer.flush();
						} else {
							writer.println("플레이어2" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":"
									+ " " + ":" + " ");
							writer.flush();
						}
					}
				}
				if (who == "P2") {
					if (x == 0 && y == 0) {
						piece[pid].move(x, y);
						check++;
						if (who.equals("P1")) {
							writer.println("플레이어1" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":"
									+ " " + ":" + " ");
							writer.flush();
						} else {
							writer.println("플레이어2" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":"
									+ " " + ":" + " ");
							writer.flush();
						}
					} else if (x == 0 && y == 6) {
						piece[pid].move(x, y);
						check++;
						if (who.equals("P1")) {
							writer.println("플레이어1" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":"
									+ " " + ":" + " ");
							writer.flush();
						} else {
							writer.println("플레이어2" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":"
									+ " " + ":" + " ");
							writer.flush();
						}
					}
				}
			}
		}
	}

	void trans(int id) {
		if (piece[id].state == 0) {

			writer.println(id + ":" + "1" + ":" + " " + ":" + " ");
			writer.flush();
			piecebutton[id].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
		} else if (piece[id].state == 1) {
			writer.println(id + ":" + "0" + ":" + " " + ":" + " ");
			writer.flush();
			piecebutton[id].setIcon(new ImageIcon("image/" + who + ".png"));
		}
	}

	public void killtrans() {
		if (state == 0) {
			if (team.equals("P1")) {
				deathbutton[ii].setIcon(new ImageIcon("image/" + "P1" + ".png"));
			} else {
				deathbutton[ii].setIcon(new ImageIcon("image/" + "P2" + ".png"));
			}
			++ii;
			++angelkill;
			if (angelkill == 4) {
				if (who.equals("P1")) {
					writer.println("플레이어2" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":"
							+ " ");
					writer.flush();
				} else {
					writer.println("플레이어1" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":"
							+ " ");
					writer.flush();
				}
			}
		} else if (state == 1) {
			deathbutton[ii].setIcon(new ImageIcon("image/" + who + "_Devil" + ".png"));
			System.out.println(ii);
			++ii;
			++devilkill;
			if (devilkill == 3) {
				if (who.equals("P1")) {
					writer.println("플레이어2" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":"
							+ " ");
					writer.flush();
				} else {
					writer.println("플레이어1" + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":" + " " + ":"
							+ " ");
					writer.flush();
				}

			}
		}
	}
}