package panels;

import java.awt.Rectangle;

import aEngine2D.*;
import gameTools.*;


public class level2 extends GamePanel{
	
	@SuppressWarnings("unchecked")
	public level2(Driver game) {
		super(game);
		
		bx = 100;
		man.setCoord(200, 400);
		man.scrollingBoundry = new Rectangle(150, 10,Driver.W - 250,Driver.H - 109);
		showLives = true;
		

		solids.add( new block(100, 0, 10, 8000, false, this) );
		
		background = read("NightSky.png");
		backgroundY = -150;
		
		for(int bbb = 0; bbb< 5; bbb++)
			solids.add( new GrassBlock( -100 + (bbb*100), 575, this) );
		
		stuff.add( new DeathArea(400, 625, 400, 10, false, this) );
		
		for(int bbb = 0;bbb<13; bbb++)
			solids.add(new GrassBlock(800 + (bbb*100),575,this) );
		
		stuff.add( new StickFigure(1000, 550, 2000, 550, this) );
		stuff.add( new StickFigure(1500,550,2100,550,this) );
		
		//first hill
		for(int bbb =0; bbb<10; bbb++)
			solids.add( new TanBlock(2100+(bbb*100), 575, this) );
		for( int bbb = 0; bbb<8; bbb++)
			solids.add( new TanBlock(2200+(100*bbb), 475, this) );
		for(int bbb = 0; bbb<6; bbb++)
			solids.add( new GrassBlock(2300+(100*bbb), 375, this) );
		
		stuff.add( new IceCube(2300, 350, 2800, 350, this) );
		
		for(int bbb = 0;bbb<5; bbb++)
			solids.add(new GrassBlock(3100 + (bbb*100),575,this) );
		
		//TODO;
		stuff.add( new StickFigure(3100, 525, new Rectangle(3100, 0, 500, Driver.H), this) );
		stuff.add( new IceCube(3000, 525, 3900, 525, this) 	);
		
		//2nd hill
		for(int bbb =0; bbb<5; bbb++)
			solids.add( new TanBlock(3600+(bbb*100), 575, this) );
		for(int bbb =0; bbb<5; bbb++)
			solids.add( new GrassBlock(3600+(bbb*100), 475, this) );
		
		for(int bbb = 0;bbb<5; bbb++)
			solids.add(new GrassBlock(4100 + (bbb*100),575,this) );
		
		stuff.add( new DeathArea(4600, 625, 200, 10, false, this) );
		
		stuff.add( new StickFigure(3900, 450, new Rectangle(3900,0,700,Driver.H), this) );
		
		for(int bbb = 0;bbb<15; bbb++)
			solids.add(new GrassBlock(4800 + (bbb*100),575,this) );
		
		stuff.add( new ArrowSign(5300, 500, this) );
		
		Sound.music.loop();
	
	}
	@Override
	public void nextPanel() {
		super.nextPanel();
		Sound.music.stop();
		Sound.Success.play();
		game.startSplash(3, man);
	}
	
	@Override
	public void CharacterDeath() {
		super.CharacterDeath();
	}
	
	@Override
	public void setMan(Man man) {
		super.setMan(man);
		man.setCoord(200, 400);
		man.scrollingBoundry = new Rectangle(150, 10,Driver.W - 250,Driver.H - 109);
	}

}
