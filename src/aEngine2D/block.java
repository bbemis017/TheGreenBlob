package aEngine2D;

import java.awt.Color;
import java.awt.Graphics;

public class block extends Thing {
	
	public boolean visible;
	public Sprite sprite;

	public block(int x, int y,int width,int height,boolean visible, GamePanel gp) {
		super(x, y, gp);
		super.setMask(width, height);
		this.visible = visible;
	}
	public block(int x, int y,Sprite s, GamePanel gp) {
		super(x, y, gp);
		this.sprite = s;
		super.setMask(sprite.width, sprite.height);
		this.visible=true;
		
	}
	
	
	@Override
	public void Step(){
		if(gp.man !=null){
			if( this.collidesWith(gp.man) ){
				characterCollision();
				
			}
		}
		
	}
	
	public void characterCollision(){
		boolean[] leftUp = this.collisionSide(gp.man);//TODO take man objects dimensions into account when reseting position
		if(leftUp[left]){
//			System.out.println("left");
			gp.man.hspeed=-gp.man.hspeed/2;
			gp.man.vx = this.getX() + this.getMask().width ;
		}
		else if(leftUp[top]){
//			System.out.println("up");
			gp.man.vspeed=1;
			gp.man.vy = this.getY() +this.getMask().height ;
		}
		else if(leftUp[right]){
//			System.out.println("right");
			gp.man.hspeed=-gp.man.hspeed/2;
			gp.man.vx = this.getX() - gp.man.getMask().width ;
		}
		else if(leftUp[bottom]){
//			System.out.println("down");
			gp.man.vspeed=0;
			gp.man.vy = this.vy-gp.man.getMask().height;
		}
	}
	
	@Override
	public void render(Graphics g){
		if(visible){
			if(sprite==null){
				g.setColor(Color.white);
				g.fillRect((int)(getBaseX()),(int)(getBaseY()),(int)width,(int)height);
			}
			else
				sprite.render(g, (int)(getBaseX()), (int)(getBaseY()) );
		}
	}

}
