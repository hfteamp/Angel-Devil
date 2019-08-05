package All;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;
public class Music {
	Clip clip;
	void stop() {
		clip.stop();
		clip.close();
	}
	void Main(){
		File file = new File("Music/main.wav");
        
 try {

            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            
        } catch(Exception e) {
            
            e.printStackTrace();
        }
	}
	void Click(){
		File file2 = new File("Music/click.wav");
 try {
            
            AudioInputStream stream2 = AudioSystem.getAudioInputStream(file2);
            Clip clip2 = AudioSystem.getClip();
            clip2.open(stream2);
            clip2.start();
            
        } catch(Exception e) {
            
            e.printStackTrace();
        }
	}
	
	void Devilcatch(){
		File file3 = new File("Music/Devilcatch.wav");
        
 try {
            
            AudioInputStream stream3 = AudioSystem.getAudioInputStream(file3);
            Clip clip3 = AudioSystem.getClip();
            clip3.open(stream3);
            clip3.start();
            
        } catch(Exception e) {
            
            e.printStackTrace();
        }
	}
	
	void Angelcatch(){
		File file4 = new File("Music/Angelcatch.wav");
        
 try {
            
            AudioInputStream stream4 = AudioSystem.getAudioInputStream(file4);
            Clip clip4 = AudioSystem.getClip();
            clip4.open(stream4);
            clip4.start();
            
        } catch(Exception e) {
            
            e.printStackTrace();
        }
	}
	
	void win(){
		File file5 = new File("Music/win.wav");
        
 try {
            
            AudioInputStream stream5 = AudioSystem.getAudioInputStream(file5);
            Clip clip5 = AudioSystem.getClip();
            clip5.open(stream5);
            clip5.start();
            
        } catch(Exception e) {
            
            e.printStackTrace();
        }
	}

}
