package aEngine2D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Enemy extends Thing{
	
	protected boolean following = false;
	protected boolean chasing = false;
	protected boolean gravityOn = false;
	ArrayList solids;
	Thing charater;
	protected Rectangle detectionBounds;
	public double hspeedMax, hspeedNorm, vspeedMax;
	int EndX,EndY;
	public int JumpNum = 0,MaxJumps =2;
	private int Startx;
	private int Starty;
	

	/**
	 * Constructor for enemies that move in a set path
	 * @param Startx
	 * @param Starty
	 * @param EndX
	 * @param EndY
	 * @param solids
	 * @param gp
	 */
	public Enemy(int Startx, int Starty,int EndX, int EndY, GamePanel gp) {
		super(Startx, Starty, gp);
		setMask(20,20);
		following = false;
		this.EndX = EndX;
		this.EndY = EndY;
		this.Startx = Startx;
		this.Starty = Starty;
		this.solids = gp.solids;
	}
	
	/**
	 * Constructor for enemies that follow the character
	 * @param Startx
	 * @param Starty
	 * @param detectionBounds
	 * @param character
	 * @param solids
	 * @param gp
	 */
	public Enemy(int Startx, int Starty,Rectangle detectionBounds,Thing character, GamePanel gp){
		super(Startx,Starty,gp);
		setMask(20,20);
		this.charater = character;
		following = true;
		this.detectionBounds = detectionBounds;
		this.solids = gp.solids;
		this.Startx = (int)this.detectionBounds.getMinX();
		this.EndX = (int)this.detectionBounds.getMaxX();
		this.EndY = (int)this.detectionBounds.getMinY();
	}
	/**
	 * tells enemy to move back and forth between startX and EndX
	 */
	public void followPath(){
		
		if( vx >EndX){
			hspeed= -hspeedNorm;
		}
		else if( vx < Startx) {
			
			hspeed= hspeedNorm;
			
		}
		
	}
	
	/**
	 * tells enemy to follow the man in gamePanel while the man is in the detectionBounds
	 * And when not in detection bounds he follows the default path
	 */
	public void followMan(){
		if(detectionBounds.intersects( gp.man.getMask() ) ){
			chasing = true;
			if( this.vx > gp.man.vx + gp.man.getMask().width/2 ){
				hspeed= -hspeedMax;
				
			}
			else if( this.vx < gp.man.vx + gp.man.getMask().width/2 ){
				hspeed=hspeedMax;
				
			}
			
			if( gp.man.getMask().getMaxY() < this.getMask().getMinY() )
				Jump();
			
		}
		else{
			chasing = false;
			followPath();
		}
	}
	/**
	 * Handles collisions with all thing objects in the solids array
	 * Also calls the collisionWithMan() method when it intersects with the man from game panel
	 */
	@Override
	public void solidCollisions(){
		if(gp.man != null){
			if( this.collidesWith(gp.man) )
				collisionWithMan();
		}
		
			for(int bbb = 0; bbb<solids.size(); bbb++){
				if( ( (Thing)solids.get(bbb) ).collidesWith( this ) ){
					boolean[] leftUp = ( (Thing)solids.get(bbb) ).collisionSide( this );
					if(leftUp[left]){
//						System.out.println("left");
						if( gravityOn )
							Jump();
					
						this.vx = ( (Thing)solids.get(bbb) ).getX() + ( (Thing)solids.get(bbb) ).getMask().width + 1;
					
					}
					else if(leftUp[top]){
//						System.out.println("up");
					
						this.vspeed = 1;
						this.vy = ( (Thing)solids.get(bbb) ).getY() +( (Thing)solids.get(bbb) ).getMask().height ;
					}
					else if(leftUp[right]){
//						System.out.println("right");
					
						this.vx = ( (Thing)solids.get(bbb) ).getX() - this.getMask().width ;
						if( gravityOn)
							Jump();
					}
					else if(leftUp[bottom]){
//						System.out.println("down");
					
						this.vspeed = 0;
						this.vy = ( (Thing)solids.get(bbb) ).vy - this.getMask().height;
					}
				
				
				}
			}
		
	}
	
	@Override
	public void Step() {
		
		if(!following)
			followPath();
		else
			followMan();
		
		super.Step();
		
		if(solids.size()>0)
			solidCollisions();
		if(vspeed==0)
			JumpNum=0;
		if(gravityOn)
			super.gravity();
		
		
	}
	/**
	 * should be overrided in child classes
	 * so that each enemy can do something different when they intersect
	 */
	public void collisionWithMan(){}
	/**
	 * removes this enemy from the stuff array in game panel
	 */
	public void destroy(){
		gp.stuff.remove(this);
	}
	/**
	 *Makes enemy Jump as long as he is not moving downward
	 *or he is moving downward and the number of Jumps since last time
	 *standing on a solid is less than the MaxJumps
	 */
	public void Jump(){
		if(JumpNum<MaxJumps){
			vspeed = -vspeedMax;
			++JumpNum;
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.drawRect((int)getBaseX(), (int)getBaseY(), 20, 20);
	}
	
	/**
	 * paints the detection boundry to the screen
	 * @param g
	 */
	public void showDetectionBoundry(Graphics g){
		g.setColor(Color.red);
		g.drawRect((int)(detectionBounds.x - gp.bx), (int)(detectionBounds.y-gp.by), detectionBounds.width, detectionBounds.height);
	}
	

}
