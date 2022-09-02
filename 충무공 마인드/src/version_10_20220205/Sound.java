package version_10_20220205;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//게임의 소리 출력 스레드 클래스
public class Sound extends Thread {
	//필드
    boolean isloop;
    public String name;
    FileInputStream fis;
    BufferedInputStream bis;
    File file;
    Clip clip;
    AudioInputStream ais;
    int loopCount;

    //생성자
    public Sound(String filename, boolean isloop, int loopCount) {
        try {
            this.isloop = isloop;
            this.name = filename;
            if(loopCount == 0) {
            	this.loopCount = Clip.LOOP_CONTINUOUSLY;
            }
            else if(loopCount != 0) {
            	this.loopCount = loopCount - 1;
            }
            file = new File(name);
            ais = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.loop(this.loopCount);
            //배경음 재생 시 단순 객체 생성만으로 동작
            clip.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    //음악종료
    public void close() {
        isloop = false;
        clip.close();
        this.interrupt();
    }

    //효과음 재생 시 스레드 생성되어 별도 재생
    @Override
    public void run() {
    	while(isloop) {
    		clip.start();
    		try {
    			sleep(100);
    			break;
    		} catch(Exception e) {
    		}
    	}
    }
}


