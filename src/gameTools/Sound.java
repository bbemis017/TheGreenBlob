package gameTools;

import java.applet.Applet;
import java.applet.AudioClip;



import aEngine2D.GamePanel;

public class Sound {

	public static final AudioClip music = Applet.newAudioClip(GamePanel.class.getResource("/res/background.wav") ); 
	
	public static final AudioClip Success = Applet.newAudioClip(GamePanel.class.getResource("/res/Success.wav") );
	public static final AudioClip LevelComplete = Applet.newAudioClip(GamePanel.class.getResource("/res/LevelComplete.wav") );
	public static final AudioClip GameOver = Applet.newAudioClip(GamePanel.class.getResource("/res/GameOver.wav") );
	//B017W14B
}
