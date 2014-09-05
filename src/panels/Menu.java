package panels;

import gameTools.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import aEngine2D.*;

public class Menu extends GamePanel{
	
	Font title = new Font("Arial", Font.PLAIN,30);
	Font text = new Font("Arial",Font.ITALIC, 28);
	
	TextArea playArea = new TextArea("Play", new Rectangle(700,250,70, 60), text, false,  Color.ORANGE,Color.WHITE, this);
	TextArea quitArea = new TextArea("Quit", new Rectangle(700,310,70,60), text, false,Color.ORANGE, Color.WHITE, this);
	
	int BlobiumAlarm =0;
	
	
	

	@SuppressWarnings("unchecked")
	public Menu(Driver game) {
		super(game);
		man = null;
		
		
		background = read("NightSky.png");
		backgroundY = -250;
		showLives = false;
		
		for(int bbb = 0; bbb< 9; bbb++)
			solids.add( new GrassBlock( (bbb*100), 525, this) );
		
		solids.add( new TanBlock(600,490,this) );
		solids.add( new TanBlock(700,490,this) );
		solids.add( new TanBlock(800,490,this) );
		solids.add( new TanBlock(800,450,this) );
		
		solids.add( new GrassBlock(600,450,this) );
		solids.add( new GrassBlock(700,450,this) );
		solids.add( new GrassBlock(800,410,this) );
		
		stuff.add(0, new MenuCharacter(50, 50, 725, 500, this) );
		stuff.add(1, new Blobium(500, 425,(MenuCharacter)stuff.get(0), this));
		

		
		Sound.music.loop();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void Run() {
		super.Run();
		bx=0;
		by=0;
		
		++BlobiumAlarm;
		if( BlobiumAlarm >= 700){
			BlobiumAlarm=0;
			stuff.add(1, new Blobium(500,450,(MenuCharacter)stuff.get(0),this) );

			stuff.add(2, new StickFigure(0, 450, 650, 450,(MenuCharacter)stuff.get(0), this) );
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		
		g.setFont(title);
		g.setColor(Color.GREEN);
		g.drawString("The Green Blob", 240, 100);

		playArea.render(g);
		quitArea.render(g);
	
		
	}
	
	@Override
	public void KeyPressed(KeyEvent e) {
	
	}
	
	@Override
	public void KeyReleased(KeyEvent e) {
		
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		
		playArea.mouseMoved(e);
		quitArea.mouseMoved(e);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
		if(playArea.inArea(e.getPoint() ) ){
			Sound.music.stop();
			game.startSplash(1, null);
		}
		else if(quitArea.inArea(e.getPoint() ) ){
			Sound.music.stop();
			game.destroy();
		}
			
	}

}
