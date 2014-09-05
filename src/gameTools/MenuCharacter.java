package gameTools;

import java.awt.Graphics;

import aEngine2D.Enemy;
import aEngine2D.GamePanel;
import aEngine2D.Sprite;

public class MenuCharacter extends Enemy{
	
	Sprite sprite;
	double size = .5;

	public MenuCharacter(int Startx, int Starty, int EndX, int EndY,GamePanel gp) {
		super(Startx, Starty, EndX, EndY, gp);
		
		sprite = new Sprite(gp, size, size, 1, "OvalMan/A.png","OvalMan/B.png","OvalMan/C.png","OvalMan/D.png","OvalMan/E.png");
		sprite.setSpeed(.15);
		
		setMask(sprite.width*size, sprite.height*size);
		gravityOn = true;
		hspeed = hspeedNorm = 3;
		hspeedMax = 8;
		vspeedMax = 15;
		
	}
	
	@Override
	public void Step() {
		super.Step();
		if(hspeed<0)
			sprite.orientation = -1;
		else
			sprite.orientation = 1;
	}
	
	public void addBlobium(){
		if(size+.5<=1.5){
			vy-=sprite.height*.5;
			size+=.5;
			setMask(sprite.width*size,sprite.height*size);
		}
	}
	
	public void removeBlobium(){
		size-=.5;
		setMask(sprite.width*size,sprite.height*size);
	}
	
	@Override
	public void render(Graphics g) {
		sprite.xscale = size; sprite.yscale = size;
		sprite.render(g, (int)getBaseX(), (int)getBaseY() );
	}

}
