package gameTools;

import java.awt.Graphics;
import java.awt.Rectangle;

import aEngine2D.Enemy;
import aEngine2D.GamePanel;
import aEngine2D.Sprite;

public class StickFigure extends Enemy{
	
	Sprite sprite;
	double size =.25;
	
	MenuCharacter character;
	Rectangle sword = new Rectangle(25, 14);

	public StickFigure(int Startx, int Starty, int EndX, int EndY, GamePanel gp) {
		super(Startx, Starty, EndX, EndY, gp);
		
		sprite = new Sprite(gp,size,size,1,"SF_IceKnife/A.png","SF_IceKnife/B.png","SF_IceKnife/C.png");
		sprite.setSpeed(.25);
		
		setMask(sprite.width*size,sprite.height*size);
		gravityOn = true;
		hspeed = hspeedNorm = 2;
		hspeedMax = 3;
		vspeedMax = 8;
		
		
	}
	
	public StickFigure(int Startx,int Starty, Rectangle detectionBounds,GamePanel gp){
		super(Startx, Starty, detectionBounds, null, gp);
		sprite = new Sprite(gp,size,size,1,"SF_IceKnife/A.png","SF_IceKnife/B.png","SF_IceKnife/C.png");
		sprite.setSpeed(.25);
		
		setMask(sprite.width*size,sprite.height*size);
		gravityOn = true;
		hspeed = hspeedNorm = 2;
		hspeedMax = 3;
		vspeedMax = 8;
	}
	
	public StickFigure(int Startx, int Starty, int EndX, int EndY,MenuCharacter character, GamePanel gp) {
		super(Startx, Starty, EndX, EndY, gp);
		
		sprite = new Sprite(gp,size,size,1,"SF_IceKnife/A.png","SF_IceKnife/B.png","SF_IceKnife/C.png");
		sprite.setSpeed(.25);
		
		setMask(sprite.width*size,sprite.height*size);
		gravityOn = true;
		hspeed = hspeedNorm = 3;
		hspeedMax = 5;
		vspeedMax = 7;
		
		this.character = character;
	}
	
	
	@Override
	public void destroy(){
		gp.stuff.remove(this);
	}
	
	public void swordCollisions(){
		if(hspeed<0)
			sword.x = (int) (getX() -1);
		else
			sword.x = (int) (getX() + 15);
		
		sword.y = (int)(getY()+45);
		
		if(gp.man!=null && gp.man.alive && sword.intersects(gp.man.getMask() ) ){
			gp.man.removeBlobium();
			this.destroy();
		}
	}
	
	@Override
	public void Step() {
		super.Step();
		swordCollisions();
		if(chasing)
			sprite.setSpeed(.2);
		if(hspeed<0)
			sprite.orientation = 1;
		else
			sprite.orientation = -1;
		
		if(character == null){
			
			if( gp.man!=null && gp.man.alive && this.collidesWith(gp.man) ){
				
				this.destroy();
			}
		}
		else{
			
			if(this.collidesWith(character) ){
				character.removeBlobium();
				this.destroy();
			}
			
		}
		
		
	}
	
	@Override
	public void followMan() {
		if(detectionBounds.intersects( gp.man.getMask() ) ){
			chasing = true;
			if( this.vx > gp.man.getX() + gp.man.getMask().width/2 ){
				hspeed= -hspeedMax;
				
			}
			else if( this.vx < gp.man.getX() + gp.man.getMask().width/2 ){
				hspeed=hspeedMax;
				
			}
			
			if( gp.man.getMask().getMaxY() < sword.getMinY() )
				Jump();
			
		}
		else{
			chasing = false;
			followPath();
		}
	}
	
	@Override
	public void render(Graphics g) {
		sprite.render(g, (int)getBaseX(), (int)getBaseY() );
		
		
	}

}
