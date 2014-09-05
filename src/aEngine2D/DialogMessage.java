package aEngine2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class DialogMessage {
	
	String[] Message ;
	int Duration,time;
	public int TimesActivated = 0;
	GamePanel gp;
	Sprite sprite;
	Font font = new Font("Rockwell", Font.BOLD, 15);
	int BoxX,BoxY,TextX,TextY;
	private boolean display;
	
	/**
	 * Displays Message in box in center of the screen and freezes the gamepanel
	 * @param Message - String to be displayed
	 * @param Duration - time the message appears measured in steps
	 * @param gp - GamePanel gives ability to freeze gp
	 */
	public DialogMessage( int Duration, GamePanel gp,String... txt){
		this.Message = new String[txt.length];
		for(int bbb = 0; bbb<txt.length; bbb++)
			this.Message[bbb] = txt[bbb];
		this.Duration = Duration;
		this.gp = gp;
		this.time = 0;
		this.sprite = new Sprite(gp,.5, .5, 1, "MessageDialog.png");
		
		this.BoxX = (int)(Driver.W/2 - sprite.width*sprite.xscale/2);
		this.BoxY =  (int)(sprite.width*sprite.yscale/2);
		
		this.TextX = (int)(Driver.W/2 - sprite.width*sprite.xscale/2 + 25);
		this.TextY = (int)(sprite.width*sprite.yscale/2 + 45);
		
		this.display = false;
		
		
		
	}
	
	public void Step(){
		if(display){
			++time;
			if(time>=Duration){
				display = false;
				this.gp.Running = true;
			}
		}
	}
	
	public void display(){
		this.gp.Running = false;
		display = true;
	}
	
	public void render(Graphics g){
		if(display){
			sprite.render(g, BoxX, BoxY );
			g.setColor(Color.black);
			g.setFont(font);
			for(int bbb =0; bbb<Message.length; bbb++)
				g.drawString(Message[bbb], TextX, TextY + (20*bbb) );
		}
	}

}
