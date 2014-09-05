package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import aEngine2D.*;

public class Splash extends GamePanel{
	
	int counter, duration,NextPanel;
	Font font;
	Man m;
	String text;
	
	public Splash(Driver game) {
		super(game);
		man = null;
		showLives = false;
		this.m = null;
		counter = 0;
		duration =  75;
		font = new Font("Rockwell", Font.TRUETYPE_FONT, 25);
		this.NextPanel = 0;
		this.text = "Game Over";
	}
	
	public Splash(Driver game, int NextPanel) {
		super(game);
		man = null;
		showLives = false;
		m = null;
		counter = 0;
		duration =  60;
		font = new Font("Rockwell", Font.TRUETYPE_FONT, 25);
		this.NextPanel = NextPanel;
		this.text = "START!!";
		
		
	}
	
	public Splash(Driver game, int NextPanel,Man m) {
		super(game);
		man = null;
		showLives = false;
		this.m = m;
		counter = 0;
		duration =  60;
		font = new Font("Rockwell", Font.TRUETYPE_FONT, 25);
		this.NextPanel = NextPanel;
		this.text = "START!!";
		
		
	}
	
	
	@Override
	public void Run() {
		super.Run();
		bx = 0;
		by = 0;
		++counter;
		if(counter>=duration){
			
			game.startPanel(NextPanel,m);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Driver.W, Driver.H);
		
		g.setColor(Color.ORANGE);
		g.setFont(font);
		g.drawString(text, Driver.W/2-20, Driver.H/2-20);
	}
	

	//B017W14B
}
