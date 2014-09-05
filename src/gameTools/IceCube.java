package gameTools;

import java.awt.Graphics;
import java.awt.Rectangle;

import aEngine2D.Enemy;
import aEngine2D.GamePanel;
import aEngine2D.Sprite;

public class IceCube extends Enemy{
	
	Sprite sprite;
	double size = .1;

	public IceCube(int Startx, int Starty, int EndX, int EndY, GamePanel gp) {
		super(Startx, Starty, EndX, EndY, gp);
		sprite = new Sprite(gp, size, size, 1, "IceCube.png");
		setMask(sprite.width*sprite.xscale, sprite.height*sprite.yscale-5);
		gravityOn = true;
		hspeed = hspeedNorm = 2;
		hspeedMax = 4;
		vspeedMax = 8;
	}
	
	public IceCube(int Startx,int Starty, Rectangle detectionBounds,GamePanel gp){
		super(Startx, Starty, detectionBounds, null, gp);
		sprite = new Sprite(gp,size,size,1,"MadIceCube.png");
		sprite.setSpeed(1);
		
		setMask(sprite.width*size,sprite.height*size-5);
		gravityOn = true;
		hspeed = hspeedNorm = 1;
		hspeedMax =2;
		vspeedMax = 10;
	}
	public IceCube(int Startx,int Starty,int vspeedMax, Rectangle detectionBounds,GamePanel gp){
		super(Startx, Starty, detectionBounds, null, gp);
		sprite = new Sprite(gp,size,size,1,"MadIceCube.png");
		sprite.setSpeed(1);
		
		setMask(sprite.width*size,sprite.height*size-5);
		gravityOn = true;
		this.hspeed = hspeedNorm = 1;
		hspeedMax =2;
		this.vspeedMax = vspeedMax;
	}
	
	@Override
	public void Step() {
		super.Step();
		if(hspeed<0)
			sprite.orientation = 1;
		else
			sprite.orientation = -1;
		
		if( gp.man != null && gp.man.alive){
			if(this.collidesWith(gp.man) ){
				gp.man.removeBlobium();
				this.destroy();
			}
				
		}
	}
	
	
	@Override
	public void render(Graphics g) {
		sprite.render(g, (int)getBaseX(), (int)getBaseY() );
	}
	
	@Override
	public void destroy(){ gp.stuff.remove(this); }
	
	

}
