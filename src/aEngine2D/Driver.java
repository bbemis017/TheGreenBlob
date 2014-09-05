package aEngine2D;


import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import panels.*;

public class Driver extends JFrame implements KeyListener,MouseMotionListener,MouseListener{
	

   // Created by: Ben Bemis
   // Music from various locations
	
	
	public static int CurrentPanel = 0;
	public static GamePanel[] panel = new GamePanel[5];
	public static int W = 900, H = 650;
	public static Driver game;
	
	public Driver(){
		
		super("The Green Blob");
		
		
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		
		setIconImage(read("OvalMan/A.png"));
		
		//starts first panel
		panel[0] = new Menu(this);
		panel[0].start();
	}
	
	public static void main(String args[] ){
		
		game = new Driver();
		
		game.add(panel[CurrentPanel]);
		panel[CurrentPanel].setVisible(true);
		
		game.setSize(W,H);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setTitle("The Green Blob");
		game.setResizable(false);
		game.setFocusable(true);
		game.setVisible(true);
		game.setLocationRelativeTo(null);
	
	}
	
	
	/**
	 * Starts screen that says You Won
	 */
	public void startYouWon(){
		panel[CurrentPanel].stop();
		
		Driver.panel[CurrentPanel].setVisible(false);
		game.remove(panel[CurrentPanel] );
		
		CurrentPanel = 0;
		
		
		panel[CurrentPanel] = new YouWon(this);
		
		add( panel[CurrentPanel] );
		panel[CurrentPanel].setVisible(true);
		game.setVisible(true);
		
		panel[CurrentPanel].start();
	}
	/**
	 * Starts a splash screen that says game over then returns to menu
	 */
	public void startSplashGameOver(){
		
		panel[CurrentPanel].stop();
		
		Driver.panel[CurrentPanel].setVisible(false);
		game.remove(panel[CurrentPanel] );
		
		CurrentPanel = 0;
		
		
		panel[CurrentPanel] = new Splash(this);
		
		add( panel[CurrentPanel] );
		panel[CurrentPanel].setVisible(true);
		game.setVisible(true);
		
		panel[CurrentPanel].start();
		
	}
	
	/**
	 * Starts a splash screen and starts the indicated panel afterwards
	 */
	public void startSplash(int nextPanel,Man m){
		
		panel[CurrentPanel].stop();
		
		Driver.panel[CurrentPanel].setVisible(false);
		game.remove(panel[CurrentPanel] );
		
		CurrentPanel = nextPanel;
		
		
		panel[CurrentPanel] = new Splash(this,nextPanel,m);
		
		add( panel[CurrentPanel] );
		panel[CurrentPanel].setVisible(true);
		game.setVisible(true);
		
		panel[CurrentPanel].start();
		
	}
	/**
	 * Starts a new panel in the Jframe
	 * @param nextPanel
	 */
	public void startPanel(int nextPanel,Man man){
		
		panel[CurrentPanel].stop();
		
		Driver.panel[CurrentPanel].setVisible(false);
		game.remove(panel[CurrentPanel] );
		
		CurrentPanel = nextPanel;
		initPanel(nextPanel);
		if(man!=null)
			panel[CurrentPanel].setMan(man);
		add( panel[CurrentPanel] );
		panel[CurrentPanel].setVisible(true);
		game.setVisible(true);
		
		panel[CurrentPanel].start();
		
		
	}
	/**
	 * Initializes the nextPanel
	 * @param NextPanel
	 */
	public void initPanel(int NextPanel){
		//Initializes all your panels when you need them
		switch(NextPanel){
		case 0:
			panel[NextPanel] = new Menu(this);
			break;
		case 1:
			panel[NextPanel] = new level1(this);
			break;
		case 2:
			panel[NextPanel] = new level2(this);
			break;
		case 3:
			panel[NextPanel] = new level3(this);
			break;
		
		
		}
		
	}
	
	public void destroy(){
		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
		    

	@Override
	public void keyPressed(KeyEvent e) {

		panel[CurrentPanel].KeyPressed(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	
		panel[CurrentPanel].KeyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		panel[CurrentPanel].keyTyped(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		panel[CurrentPanel].mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		panel[CurrentPanel].mouseMoved(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		panel[CurrentPanel].mouseClicked(e);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		panel[CurrentPanel].mousePressed(e);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		panel[CurrentPanel].mouseReleased(e);
		
	}
	
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

	
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	
	//B017W14B
}
