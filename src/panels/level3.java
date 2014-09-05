package panels;

import java.awt.Rectangle;

import aEngine2D.*;
import gameTools.*;

public class level3 extends GamePanel{

	@SuppressWarnings("unchecked")
	public level3(Driver game) {
		super(game);
		
		bx = 100;
		setMan(man);
		
		
		
		background = read("NightSky.png");
		backgroundY = -60;
		showLives = true;
		
		solids.add( new block(100, 0, 10, Driver.H, false, this) );
		
		solids.add( new FloatingBlock(200, 160, .5, this) );
		
		stuff.add( new Barrel(250, 67, this) );

		
		for(int bbb = 0; bbb< 5; bbb++)
			solids.add( new GrassBlock( -100 + (bbb*100), 525, this) );
		//JUMP #1
		stuff.add( new DeathArea(400, 625, 150, 10, false, this) );
		stuff.add( new Elevator(400, 530,190, this) );
		
		for(int bbb = 0; bbb< 8; bbb++)
			solids.add( new GrassBlock( 550 + (bbb*100), 550, this) );
		
		stuff.add( new StickFigure(1100, 500, new Rectangle(550, 0, 1200, Driver.H), this) );
		stuff.add( new StickFigure(700, 500, 1350, 500, this) );
		
		//JUMP #2
		stuff.add( new DeathArea(1350, 625, 100, 10, false, this) );
		
		for( int b = 0; b<15; b++)
			solids.add( new IceBlock(1450 + (b*100), 550, this) );
		
		stuff.add( new StickFigure(1450, 300, new Rectangle(1500, 0, 500, Driver.H), this) );
		stuff.add( new IceCube(2600, 300, new Rectangle(1450, 0, 1500, Driver.H), this) );
		stuff.add( new IceCube(2400, 300, new Rectangle(1450, 0, 1500, Driver.H), this) 	);
		
		stuff.add( new DeathArea(2950,625,600,10,false,this) );
		
		solids.add(new FloatingBlock(1500, 450, .3, this) );
		solids.add(new FloatingBlock(1800, 340, .5, this) );
		solids.add(new FloatingBlock(2400, 410, .9, this) );
		solids.add(new FloatingBlock(2900, 290, .7, this) );
		
		//TODO
		
		//HILL #1
		for(int bbb =0; bbb<7; bbb++)
			solids.add( new TanBlock(3400+(bbb*100), 585, this) );
		for( int bbb = 0; bbb<4; bbb++)
			solids.add( new TanBlock(3500+(100*bbb), 485, this) );
		for(int bbb = 0; bbb<2; bbb++)
			solids.add( new GrassBlock(3600+(100*bbb), 385, this) );
		
		stuff.add( new IceCube(3500, 490, 5000, 490, this) );
		
		
		for(int bbb = 0; bbb< 8; bbb++)
			solids.add( new GrassBlock( 3900 + (bbb*100), 550, this) );
		
		stuff.add( new StickFigure(3700, 450, 3900, 450, this) );
		
		stuff.add( new StickFigure(3900, 450, new Rectangle(3900, 0, 800, Driver.H), this) );
		stuff.add( new Blobium(4300, 450, this) );
		
		//HILL#2
		for(int bbb = 0; bbb< 8; bbb++)
			solids.add( new TanBlock( 4700 + (bbb*100), 550, this) );
		for(int bbb = 0; bbb< 8; bbb++)
			solids.add( new TanBlock( 4700 + (bbb*100), 450, this) );
		
		stuff.add( new StickFigure(4700, 400, new Rectangle(4700, 0, 750, Driver.H), this) );
		stuff.add( new StickFigure(5400, 400, new Rectangle(4700, 0, 750, Driver.H), this) );
		stuff.add( new IceCube(4700, 400, 5450, 400, this) );
		
		
		stuff.add(new DeathArea(5500, 625, 4300, 10, false, this));
		
		
		//JUMP#3
		solids.add( new FloatingBlock(5700, 420, .3, this) );
		solids.add( new FloatingBlock(6000,200,.4,this) );
		solids.add( new FloatingBlock(6100, 500, .1, this) );
		solids.add( new FloatingBlock(6400,390,.6,this) );
		stuff.add( new IceCube(6400, 300, 6600, 300, this) );
		solids.add( new FloatingBlock(6800, 300, .4, this) );
		
		for(int bbb =0; bbb<10; bbb++)
			solids.add( new FloatingBlock(7100 + (185*bbb), 180, .5, this) );
		
		stuff.add( new IceCube(7300, 100,5, new Rectangle(7200, 0, 1200, 250), this) );
		for( int b =0; b<5; b++)
			stuff.add( new StickFigure(7500 + (350*b), 100, 7700 + (350*b), 100, this) );
		
		for(int bbb =0; bbb<5; bbb++){
			stuff.add( new IceCube(7100 +(400*bbb), 450, 7380 +(400*bbb), 450, this) );
			solids.add( new FloatingBlock(7100+(400*bbb), 570, .7, this) );
		}
		
		
		
		
		solids.add( new FloatingBlock(9200, 340, 1, this) );
		solids.add( new FloatingBlock(9500,340,1,this) );
		
		stuff.add( new ArrowSign(9450, 260, this) );
		
		
		Sound.music.loop();
	}
	@Override
	public void setMan(Man man) {
		super.setMan(man);
		man.setCoord(200, 400);
		man.scrollingBoundry = new Rectangle(150, 10,Driver.W - 250,Driver.H - 109);
	}

	@Override
	public void nextPanel() {
		super.nextPanel();
		Sound.music.stop();
		Sound.LevelComplete.play();
		game.startYouWon();
	}
}
