package aEngine2D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Thing {
	
	public int left=2,top=3,right=0,bottom=1;
	protected double vx;
	public double vy;
	public double hspeed;
	public double vspeed;
	public double width, height;
	public GamePanel gp;
	public boolean ScrollStop = false;
	public Rectangle scrollingBoundry,Limits;
	public boolean scroll;
	private double oldvy = 0;
	public ArrayList solids;

	public Thing(int x, int y, GamePanel gp) {

		this.vx = x;
		this.vy = y;
		this.gp = gp;
		this.scrollingBoundry = new Rectangle(10, 10,Driver.W - 70,Driver.H - 109);
		this.scroll = false;
		setLimits(new Rectangle(-50, 20, 1000, 500));
	}
	/**
	 * changes the virtual position of the Thing based on it's vspeed and hspeed
	 */
	public void Step() {

		oldvy = vy;

		vx += hspeed;
		
		vy += vspeed;
		
		

	}
	/**
	 * calculates new speed and position for Thing acted on by gravity
	 */
	public void gravity() {

		vspeed += 15 * .02;
		vy += (vspeed * .02) + (15 * .02 * .02);

	}
	/**
	 * handles the scrolling of the virtual map based on the base coordinates
	 */
	public void scrolling() {
		scrollingBoundry.x = (int) (gp.bx + 50);
		scrollingBoundry.y = (int) (gp.by + 60);
		
		
//		if( gp.bx < Limits.x){
//			ScrollStop = true;
//			gp.bx+=.1;
//		}
//		else if( gp.bx +gp.game.W >  Limits.width +Limits.x){
//			ScrollStop = true;
//			gp.bx-=.1;
//		}
//		if( gp.by < Limits.y){
//			ScrollStop = true;
//			gp.by+=.1;
//		}
		
//		else if( gp.by + gp.game.H > Limits.height + Limits.y){
//			ScrollStop = true;
//			gp.by-=.1;
//		}
		
		if ( /*!ScrollStop &&*/ (!scrollingBoundry.contains(vx, vy) || !scrollingBoundry.contains(vx + (width), vy) || !scrollingBoundry.contains(vx, vy + height) ) ) {
				gp.bx += hspeed;
				double diff = vy - oldvy;
				
					
				
//					if(gp.by< Limits.getMinY()  && diff>0 )
//						gp.by += diff;
//					else if( gp.by + gp.game.H>Limits.getMaxY() && diff<0)
//						gp.by+= diff;
					
		}
		
//		if(ScrollStop &&   (scrollingBoundry.contains(vx, vy) && scrollingBoundry.contains(vx + (width), vy) && scrollingBoundry.contains(vx, vy + height) && scrollingBoundry.contains(vx+width, vy+height) ) )
//			ScrollStop= false;
		
	}
	/**
	 * shows the boundary that the main character has to leave in order for it to begin scrolling
	 * Note: boundary is spelled boundry
	 * @param g
	 */
	public void showScrollingBoundry(Graphics g) {
		g.drawRect(scrollingBoundry.x - (int) (gp.bx), scrollingBoundry.y
				- (int) (gp.by), scrollingBoundry.width,
				scrollingBoundry.height);
	}
	
	/**
	 * This Mask represents the area that will be used for all collisions
	 * @param width
	 * @param height
	 */
	public void setMask(double width, double height) {
		this.width = width;
		this.height = height;
	}
	/**
	 * 
	 * @return Rectangle for use in collision detection relative to virtual Map
	 */
	public Rectangle getMask() {
		return new Rectangle((int) (vx), (int) (vy),
				(int) width, (int) height);
	}
	/**
	 * 
	 * @param thing
	 * @return true - if this object collides with the argument thing
	 * @return false- if this thing does not  collide with argument thing
	 */
	public boolean collidesWith(Thing thing){
		if(this.getMask().intersects(thing.getMask() ) )
			return true;
		return false;
	}
	/**
	 * This method is used to determine the sides that are in contact during a collision
	 * boolean[0] = left
	 * boolean[1] = Top
	 * boolean[2] = right
	 * boolean[3] = bottom
	 * @param thing
	 * @return true - this side is in contact
	 * @return false - this side is not in contact
	 */
	public boolean[] collisionSide(Thing thing){
		
		boolean[] leftUp = new boolean[4];
		
		Rectangle[] boxes = new Rectangle[4];
		boxes[0] = new Rectangle(this.getMask().x-1, this.getMask().y, 1, this.getMask().height);
		boxes[1] = new Rectangle(this.getMask().x, this.getMask().y-1, this.getMask().width, 1);
		boxes[2] = new Rectangle(this.getMask().x + this.getMask().width, this.getMask().y, 1, this.getMask().height);
		boxes[3] = new Rectangle(this.getMask().x, this.getMask().y+this.getMask().height, this.getMask().width, 1);
		
		double greatestArea = 0;
		int greatest = 0;

		for( int bbb = 0; bbb<4; bbb++){
			if( getArea( boxes[bbb].createIntersection(thing.getMask() ) ) > greatestArea){
				greatestArea =getArea( boxes[bbb].createIntersection(thing.getMask() ) );
				greatest = bbb;
			}
		}
		
		for(int b=0; b<4; b++)
			leftUp[b] = false;
		
		leftUp[greatest] = true;

		return leftUp;
	}
	
	public double getArea(Rectangle2D r){ return r.getWidth() * r.getHeight(); }
	
	public void solidCollisions(){}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillOval((int) getBaseX(), (int) getBaseY(), (int) width,(int) height);
	}
	/**
	 * resets the virtual coordinates of this Thing
	 * @param x
	 * @param y
	 */
	public void setCoord(double x, double y) {
		this.vx = x;
		this.vy = y;
	}
	/**
	 *@return virtual x
	 */
	public double getX() {
		return vx;
	}
	/**
	 *@return virtual y
	 */
	public double getY() {
		return vy;
	}
	/**
	 * 
	 * @return x relative to the frame
	 */
	public double getBaseX() {
		return vx - gp.bx;
	}
	/**
	 * 
	 * @return y relative to the frame
	 */
	public double getBaseY() {
		return vy - gp.by;
	}

	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}
	
	public void setLimits(Rectangle r){ this.Limits = r; }
	
	/**
	 * shows the scrolling boundry in red
	 * @param g
	 */
	public void showLimits(Graphics g){
		g.setColor(Color.red);
		g.drawRect((int)(Limits.x-gp.bx), (int)(Limits.y-gp.by), Limits.width, Limits.height);
	}
	/**
	 * shows the Mask/collision Area in black
	 * @param g
	 */
	public void showMask(Graphics g){
		g.setColor(Color.black);
		g.drawRect((int)(getMask().x-gp.bx), (int)(getMask().y-gp.by), (getMask().width), (getMask().height) );
	}
	//B017W14B
}
