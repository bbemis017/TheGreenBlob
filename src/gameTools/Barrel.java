package gameTools;

import java.util.ArrayList;

import aEngine2D.GamePanel;
import aEngine2D.Sprite;
import aEngine2D.Thing;
import aEngine2D.block;

public class Barrel extends block {
	
	
	boolean full = true;

	public Barrel(int x, int y, GamePanel gp) {
		super(x, y, new Sprite(gp, .25, .25, 1, "Barrel.png"), gp);
		setMask(sprite.width*sprite.xscale,sprite.height*sprite.yscale);
	}
	
	@Override
	public void Step() {
		
		super.Step();
		super.gravity();
		solidCollisions();
	}
	
	@Override
	public void characterCollision() {
		
		if(full){
			Blobium bb = new Blobium((int)getX(), (int)getY(), gp);
			bb.setCoord(this.getX(), this.getY()-10);
			bb.hspeed = 3;
			bb.vspeed = -12;
			gp.stuff.add(bb);
			full = false;
		}
	}
	
	@Override
	public void solidCollisions() {
		
		ArrayList solids = gp.solids;
		
		for(int bbb = 0; bbb<solids.size(); bbb++){
			if( ( (Thing)solids.get(bbb) ).collidesWith( this ) ){
				boolean[] leftUp = ( (Thing)solids.get(bbb) ).collisionSide( this );
				if(leftUp[left]){
//					System.out.println("left");
				
					this.vx = ( (Thing)solids.get(bbb) ).getX() + ( (Thing)solids.get(bbb) ).getMask().width + 1;
				
				}
				else if(leftUp[top]){
//					System.out.println("up");
				
					this.vspeed = 0;
					this.vy = ( (Thing)solids.get(bbb) ).getY() +( (Thing)solids.get(bbb) ).getMask().height ;
				}
				else if(leftUp[right]){
//					System.out.println("right");
					this.vx = ( (Thing)solids.get(bbb) ).getX() - this.getMask().width ;
				}
				else if(leftUp[bottom]){
//					System.out.println("down");
				
					this.vspeed = 0;
					this.vy = ( (Thing)solids.get(bbb) ).vy - this.getMask().height;
				}
			
			
			}
		}
	}

}
