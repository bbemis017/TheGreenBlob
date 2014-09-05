package panels;

import java.awt.Graphics;
import java.awt.Rectangle;

import aEngine2D.*;
import gameTools.*;

public class level1 extends GamePanel{
	
	DialogMessage Dm;
	
	DialogMessage[] Dms = new DialogMessage[5];
	boolean[] InstructDisp = new boolean[5];
	int[] InstructPos = new int[5];
	

	@SuppressWarnings("unchecked")
	public level1(Driver game) {
		super(game);
		
		Dms[0] = new DialogMessage(100, this,"Use The left and Right Arrow Keys to move");
		InstructDisp[0] = true;
		InstructPos[0] = 0;
		Dms[1] = new DialogMessage(100,this,"Use The Up Arrow key to Jump");
		InstructDisp[1] = false;
		InstructPos[1] = 700;
		Dms[2] = new DialogMessage(100,this,"Collect Blobium to grow in size");
		InstructDisp[2] = false;
		InstructPos[2] = 1600;
		Dms[3] = new DialogMessage(250,this,"Some Enemies can attack you with weapons","made of Ice, Avoid their weapons and touch"," them to Absorb them");
		InstructDisp[3] = false;
		InstructPos[3] = 2300;
		Dms[4] = new DialogMessage(150,this,"Enemies made completely of Ice should"," always be avoided");
		InstructDisp[4] = false;
		InstructPos[4] = 3300;
		
		bx = 100;
		man.setCoord(200, 400);
		man.scrollingBoundry = new Rectangle(150, 10,Driver.W - 250,Driver.H - 109);
		showLives = true;
		
		Alarms.add(1, 40);
		AlarmStatus.add(1,true);
//		Alarms.add(1,120);
//		AlarmStatus.add(1,false);
		
		backgroundLayers.add( read("NightSky.png") );
		LayerStartY.add(-200);
		LayerSpeed.add(.1);
		
		solids.add(new block(100, 0, new Sprite(this, 1, 1, 1, "wall.png"), this) );
		
		
		for(int bbb = 0; bbb< 10; bbb++)
			solids.add( new GrassBlock( -100 + (bbb*100), 525, this) );
		
		stuff.add( new DeathArea(900, 625, 200, 50, false, this) );
		
		
		
		for(int bbb = 0; bbb< 35; bbb++)
			solids.add( new GrassBlock( 1100+ (bbb*100), 525, this) );
		
		stuff.add( new Blobium(1700, 515, this) );
		
		for(int bbb =0; bbb<7; bbb++)
			solids.add( new TanBlock(2000+(bbb*100), 485, this) );
		
		for( int bbb = 0; bbb<5; bbb++)
			solids.add( new TanBlock(2100+(100*bbb), 385, this) );
		
		for(int bbb = 0; bbb<3; bbb++)
			solids.add( new GrassBlock(2200+(100*bbb), 285, this) );
		
		stuff.add( new StickFigure(2500, 500, 3000, 500, this) );
		
		stuff.add( new IceCube(3400, 500, 3600, 500, this) );
		
		stuff.add( new ArrowSign(3900, 450, this) );
		
		Sound.music.loop();
	}
	
	@Override
	public void setMan(Man man) {
		super.setMan(man);
		man.setCoord(200, 400);
		man.scrollingBoundry = new Rectangle(150, 10,Driver.W - 250,Driver.H - 109);
	}
	
	
	@Override
	public void Run() {
		super.Run();
		
		InstructionDriver();
	}
	
	@Override
	public void Frozen() {
		super.Frozen();
		for( int bbb = 0; bbb<Dms.length; bbb++)
			Dms[bbb].Step();
//		Dm.Step();
	}
	
	@Override
	public void FrozenPaint(Graphics g) {
		super.FrozenPaint(g);
//		Dm.render(g);
		for( int bbb = 0; bbb<Dms.length; bbb++)
			Dms[bbb].render(g);
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
	}
	
	@Override
	public void AlarmActive(int position) {
		super.AlarmActive(position);
		if(position==1)
			Dms[0].display();
		
	}
	
	@Override
	public void CharacterDeath() {
		super.CharacterDeath();
		
//		game.startPanel(game.CurrentPanel,man);
	}
	
	public void InstructionDriver(){
		
		for( int bbb =0; bbb<InstructDisp.length;bbb++){
			if(Dms[bbb] !=null && InstructPos !=null ){
				
				if(!InstructDisp[bbb] && man.getX() > InstructPos[bbb]){
					InstructDisp[bbb] = true;
					Dms[bbb].display();
				}
			}
		}
	}
	
	@Override
	public void nextPanel() {
		Sound.music.stop();
		Sound.Success.play();
		game.startSplash(2, man);
	}
}
