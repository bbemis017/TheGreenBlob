package gameTools;

import aEngine2D.GamePanel;
import aEngine2D.Sprite;
import aEngine2D.block;

public class Elevator extends block {
	
	double vspeedMax = .5;
	double vspeed = 0;
	int MinY;
	boolean Up = false,MinPosition = false;

	public Elevator(int x, int y,int MinY, GamePanel gp) {
		super(x, y, new Sprite(gp, .2, .2, 1, "RocketPlatform/Platform.png","RocketPlatform/B.png","RocketPlatform/C.png","RocketPlatform/D.png"), gp);
		setMask(sprite.width*sprite.xscale, sprite.height*sprite.yscale);
		sprite.freezeFrame = 0;
		sprite.freeze = true;
		sprite.setSpeed(.1);
		this.MinY = MinY;
	}
	
	@Override
	public void Step() {
		super.Step();
		
		if(getY()<= MinY)
			MinPosition = true;
		
		if(Up)
			vspeed-=vspeedMax;
		else
			vspeed = 0;
			
		
		setCoord(getX(), getY() + vspeed );
		
		
	}
	
	@Override
	public void characterCollision() {
		
		boolean[] side = gp.man.collisionSide(this);
		if(side[top] && !MinPosition){
			
			sprite.freeze = false;
			Up = true;
		}
		else{
			Up = false;
			sprite.freeze = true;
		}
		
		super.characterCollision();
	}

}
