package All;

import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
public class Setting extends GUI {


	ArrayList<JButton> checkpieceList = new ArrayList<>();
	
	void Reset() {
	repaint();
		
	}
	void Save() {
		
		for(int i = 0; i< piecebutton.length; i++) {
			checkpieceList.add(piecebutton[i]);
			
			System.out.println(checkpieceList);
		}
		
       
	}
	void Load() {
		ChangeImage ci = new ChangeImage();
		//if(piece.team=="p1" || piece.state < 7)
		for(int i=0; i< 7; i++) {
			
		piecebutton [i] = checkpieceList.get(i);
		
		}
		}
	
	}