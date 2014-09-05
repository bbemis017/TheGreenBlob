package aEngine2D;



import gameTools.Sound;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {

	public ArrayList backgroundLayers = new ArrayList();
	public ArrayList LayerStartY = new ArrayList();
	public ArrayList LayerSpeed = new ArrayList();

	public ArrayList stuff = new ArrayList();
	public ArrayList solids = new ArrayList();

	public ArrayList<Integer> Alarms = new ArrayList<Integer>();
	public ArrayList<Boolean> AlarmStatus = new ArrayList<Boolean>();

	public BufferedImage background;
	public int backgroundY;

	public Driver game;
	public Man man;
	Timer timer;
	public double bx;
	public double by;
	Rectangle Limits;
	
	

	public boolean Running = true, showLives = true;

	public GamePanel(Driver game) {
		Running = true;
		setBackground(Color.white);
		setDoubleBuffered(true);

		this.game = game;

		man = new Man(Driver.W / 2 - 30, Driver.H / 2 - 50, this);

		Alarms.add(60);
		AlarmStatus.add(false);
		
		
		
		timer = new Timer(17, this);

	}

	public void setMan(Man man) {
		this.man = man;
		this.man.hspeed = 0;
		this.man.vspeed = 0;
		this.man.JumpNum = 0;
		this.man.alive = true;
		this.man.deathSprite.frame = 0;
		this.man.countDownCalled = false;
		this.man.setPanel(this);
	}

	/**
	 * Starts the infinite loop for the GamePanel should be the last to be
	 * called from the driver
	 */
	public void start() {
		timer.start();
	}

	/**
	 * Stops the infinite loop for the GamePanel
	 */
	public void stop() {
		timer.stop();
	}

	/**
	 * This is called before every paint and should control ALLL game logic
	 */
	@SuppressWarnings("unchecked")
	public void Run() {
		if (man != null)
			man.Step();

		// if (man.getY() + man.height > 450) {
		// man.setCoord(man.getX(), 450 - man.height);
		// man.vspeed = 0;
		// }

		for (int bbb = 0; bbb < stuff.size(); bbb++)
			((Thing) stuff.get(bbb)).Step();

		for (int bbb = 0; bbb < solids.size(); bbb++) {

			((Thing) solids.get(bbb)).Step();
		}

		for (int bbb = 0; bbb < AlarmStatus.size(); bbb++) {

			if (AlarmStatus.get(bbb)) {
				if (Alarms.get(bbb) > 0)
					Alarms.add(bbb, Alarms.get(bbb) - 1);
				else if (Alarms.get(bbb) <= 0) {
					AlarmStatus.add(bbb, false);
					AlarmActive(bbb);
				}
			}

		}

		//

	}

	/**
	 * This is called when Running is false
	 */
	public void Frozen() {

	}

	/**
	 * this paints the the man, stuff, and solids to the screen and adjust the
	 * scrolling layers SHOULD ONLY RENDER GRAPHICS NEVER HAVE GAME LOGIC HERE
	 * EVER!!!!
	 */
	@Override
	public void paint(Graphics g) {
		if (Running) {
			super.paint(g);
			g.drawRect(0, 0, WIDTH, HEIGHT);
			if (background == null)
				scrollingLayers(g);
			else
				g.drawImage(background, 0, backgroundY, null);

			if (man != null)
				man.render(g);

			for (int bbb = 0; bbb < stuff.size(); bbb++)
				((Thing) stuff.get(bbb)).render(g);

			for (int bbb = 0; bbb < solids.size(); bbb++)
				((Thing) solids.get(bbb)).render(g);

			if (showLives) {
				g.setColor(Color.GREEN);
				g.drawString("Lives: " + man.lives, 30, 30);
			}

			// stuff to help show motion
			g.setColor(Color.black);
		} else if (!Running) {
			FrozenPaint(g);

			
		}

	}

	public void FrozenPaint(Graphics g) {

	}

	/**
	 * Shows the scrolling limits on the screen in red
	 * 
	 * @param g
	 */
	public void showLimits(Graphics g) {
		g.setColor(Color.red);
		g.drawRect((int) (Limits.x - bx), (int) (Limits.y - by), Limits.width,
				Limits.height);
	}

	/**
	 * calls KeyPressed for the man object
	 * 
	 * @param e
	 */
	public void KeyPressed(KeyEvent e) {
		if (man != null)
			man.keyPressed(e);

	}

	/**
	 * calls KeyReleased for the man object
	 * 
	 * @param e
	 */
	public void KeyReleased(KeyEvent e) {
		if (man != null)
			man.keyReleased(e);
	}

	public void keyTyped(KeyEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Should NEVER be altered- controls when Run() and paint() are called
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (Running) {
			Run();

		} else if (!Running) {
			Frozen();

		}
		repaint();
	}

	/**
	 * sets the scrolling limits for the panel
	 * 
	 * @param r
	 */
	public void setLimits(Rectangle r) {
		this.Limits = r;
	}

	/**
	 * 
	 * @param nameAndExtension
	 * @return InputStream for files in the /res/ folder
	 */
	public InputStream resfile(String nameAndExtension) {
		return this.getClass().getResourceAsStream("/res/" + nameAndExtension);
//		return res.Resources.class.getResourceAsStream("/res/" + nameAndExtension );
		
		
	}

	/**
	 * 
	 * @param path
	 * @return BufferedImage located in /res/ folder
	 */
	public BufferedImage read(String path) {
		try {
			return ImageIO.read(resfile(path));
		} catch (IOException e) {
			System.out.println("could not read " + path);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * handles the different background layers that are scrolling
	 * 
	 * @param g
	 */
	public void scrollingLayers(Graphics g) {

		for (int i = 0; i < backgroundLayers.size(); i++) {
			double numFrames = 0;
			double ImageWidth = ((BufferedImage) backgroundLayers.get(i))
					.getWidth();
			double speed = (double) LayerSpeed.get(i);
			if (bx > 0) {

				while (numFrames * ImageWidth < bx + ImageWidth)
					numFrames++;

				--numFrames;

				for (int bbb = 0; bbb < numFrames; bbb++) {
					g.drawImage((BufferedImage) backgroundLayers.get(i),
							(int) ((ImageWidth * bbb) - (bx * speed)),
							(int) ((int) LayerStartY.get(i) - by), null);
					g.drawImage((BufferedImage) backgroundLayers.get(i),
							(int) ((ImageWidth * (bbb + 1)) - (bx * speed)),
							(int) ((int) LayerStartY.get(i) - by), null);
				}

			} else {
				while (numFrames * ImageWidth > bx + ImageWidth)
					numFrames--;

				for (int bbb = 1; bbb > numFrames; bbb--) {
					g.drawImage((BufferedImage) backgroundLayers.get(i),
							(int) ((ImageWidth * bbb) - (bx * speed)),
							(int) ((int) LayerStartY.get(i) - by), null);
					g.drawImage((BufferedImage) backgroundLayers.get(i),
							(int) ((ImageWidth * (bbb - 1)) - (bx * speed)),
							(int) ((int) LayerStartY.get(i) - by), null);
					g.drawImage((BufferedImage) backgroundLayers.get(i),
							(int) ((ImageWidth * (bbb - 2)) - (bx * speed)),
							(int) ((int) LayerStartY.get(i) - by), null);

				}
			}

		}

	}

	public void countDown() {

		AlarmStatus.add(0, true);

	}

	public void CharacterDeath() {

		man.lives -= 1;

		if (man.lives > 0) {
			man.alive = true;
			setMan(man);
			man.size = .5;
			game.startSplash(Driver.CurrentPanel, man);
		} else{
			Sound.music.stop();
			Sound.GameOver.play();
			game.startSplashGameOver();
		}
	}

	public void AlarmActive(int position) {
		switch (position) {
		case 0:

			CharacterDeath();
			break;
		}

	}

	public void nextPanel() {}

}
//B017W14B