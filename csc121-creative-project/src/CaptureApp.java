import processing.core.*;
import processing.event.*;
import java.awt.Color;

/**
 * Provides the scaffolding to launch a Processing application
 */
 class CaptureApp extends PApplet {	// <----- 1. rename AppTemplate everywhere in this file
    IWorld w;
    public static int gameWidth = 1200;
    public static int gameHeight = 700;
    public static Posn startingBase1 = new Posn(100, 600);
    public static Posn startingBase2 = new Posn(1100, 100);
    
    public void settings() {
        this.size(gameWidth, gameHeight);
    }
    
    public void setup() {
    	w = new SetupWorld(new Base(startingBase1, 100, 100, new Color(255,0,0)),
    					   new Base(startingBase2, 100, 100, new Color(0,0,255)));
    	
    }
    
    public void draw() {
        w = w.update();
        w.draw(this);
    }
    
    @Override
    public void mousePressed(MouseEvent mev) {
        w = w.mousePressed(mev);
    }
    
    @Override
   public void mouseReleased(MouseEvent mev) {
    	w = w.mouseReleased(mev);
    }

    @Override
   public void mouseMoved(MouseEvent mev) {
    	w = w.mouseMoved(mev);
    }
    
    @Override
    public void mouseDragged(MouseEvent mev) {
    	w = w.mouseDragged(mev);
    }
    
    @Override
    public void mouseClicked(MouseEvent mev) {
    	w = w.mouseClicked(mev);
    }

    @Override
    public void mouseEntered(MouseEvent mev) {
    	w = w.mouseEntered(mev);
    }

    @Override
    public void mouseExited(MouseEvent mev) {
    	w = w.mouseExited(mev);
    }
    
    @Override
    public void mouseWheel(MouseEvent mev) {
    	w = w.mouseWheel(mev);
    }

    @Override
    public void keyPressed(KeyEvent kev) {
        w = w.keyPressed(kev);
    }

    @Override
    public void keyReleased(KeyEvent kev) {
        w = w.keyReleased(kev);
    }
    
    @Override
    public void keyTyped(KeyEvent kev) {
        w = w.keyTyped(kev);
    }

    public static void main(String[] args) {
        PApplet.runSketch(new String[] { CaptureApp.class.getName() }, new CaptureApp());
    }
}
