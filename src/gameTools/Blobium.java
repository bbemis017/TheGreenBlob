package gameTools;

import java.awt.Graphics;
import java.util.ArrayList;

import aEngine2D.Thing;
import aEngine2D.GamePanel;
import aEngine2D.Sprite;

public class Blobium extends Thing{
	
	
	public MenuCharacter character;
	Sprite sprite;
	

	

	public Blobium(int x, int y, GamePanel gp) {
		super(x, y, gp);
		sprite = new Sprite(gp, .15, .15, 1, "Blobium.png");
		setMask(sprite.width*sprite.xscale,sprite.height*sprite.yscale);
		
		
	}
	
	public Blobium(int x, int y,MenuCharacter mc, GamePanel gp) {
		super(x, y, gp);
		sprite = new Sprite(gp, .15, .15, 1, "Blobium.png");
		setMask(sprite.width*sprite.xscale,sprite.height*sprite.yscale);
		
		this.character = mc;
		
	}
	
	
	@Override
	public void Step() {
		super.Step();
		super.gravity();
		solidCollisions();
		
		
		if(character == null){
			if( gp.man!=null && gp.man.alive && this.collidesWith(gp.man) ){
				gp.man.addBlobium();
				destroy();
			}
		}
		else{
			if(this.collidesWith(character) ){
				character.addBlobium();
				destroy();
			}
			
		}
		
		
	}
	
	public void destroy(){
		gp.stuff.remove(this);
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
	
	@Override
	public void render(Graphics g) {
		
		sprite.render(g, (int)getBaseX(), (int)getBaseY() );

	}
	
	

}
