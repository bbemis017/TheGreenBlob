package aEngine2D;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sprite {

	final int left = -1, right = 1;

	public BufferedImage[] images;
	public int orientation = 1;
	public GamePanel gp;
	public double xscale, yscale;
	public boolean freeze = false;
	public int freezeFrame = 0;
	public double width, height;

	private int waitTime = 15;
	private int time = 0;

	public int frame;

	public Sprite(GamePanel gp, double xscale, double yscale, int orientation,
			String... img) {
		this.gp = gp;
		images = new BufferedImage[img.length];
		for (int bbb = 0; bbb < images.length; bbb++)
			images[bbb] = gp.read(img[bbb]);

		this.orientation = orientation;
		this.frame = 0;

		this.xscale = xscale;
		this.yscale = yscale;
		
		this.width = images[0].getWidth();
		this.height = images[0].getHeight();

	}

	/**
	 * 
	 * @param duration
	 *            - milliseconds till next Image
	 * 
	 */
	public void setSpeed(double duration) {
		waitTime = (int) (duration / .02) - 1;
	}

	public void render(Graphics g, int x, int y) {

		++time;
		if (time > waitTime) {
			time = 0;
			if(!freeze ||(freeze && frame!=freezeFrame)){
				if (frame < images.length - 1)
					++frame;
				else
					frame = 0;
			}

		}

		if (orientation == right)
			g.drawImage(images[frame], x, y,(int) (xscale * images[frame].getWidth()) + x,(int) (yscale * images[frame].getHeight()) + y, 0, 0,images[frame].getWidth(), images[frame].getHeight(), null);
		else if (orientation == left)
			g.drawImage(images[frame],
					(int) (xscale * images[frame].getWidth()) + x, y, x,
					(int) (yscale * images[frame].getHeight()) + y, 0, 0,
					images[frame].getWidth(), images[frame].getHeight(), null);

	}

	public void render(Graphics g, int x, int y, int frame) {
		if (orientation == right)
			g.drawImage(images[frame], x, y,
					(int) (xscale * images[frame].getWidth()) + x,
					(int) (yscale * images[frame].getHeight()) + y, 0, 0,
					images[frame].getWidth(), images[frame].getHeight(), null);
		else if (orientation == left)
			g.drawImage(images[frame],
					(int) (xscale * images[frame].getWidth()) + x, y, x,
					(int) (yscale * images[frame].getHeight()) + y, 0, 0,
					images[frame].getWidth(), images[frame].getHeight(), null);

	}

}
