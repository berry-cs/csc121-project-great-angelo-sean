import java.awt.Color;
import java.util.Objects;
import processing.core.*;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class SetupWorld implements IWorld{
	private Base base1;
	private Base base2;

	SetupWorld(Base base1, Base base2){
		this.base1 = base1;
		this.base2 = base2;
	}

	/** produces the image of the current game */
	public PApplet draw(PApplet c) {
		c.textFont(c.createFont("RioGrande.ttf", 50));
		c.imageMode(c.CENTER);
		c.image(c.loadImage("BackgroundAlpha.png"), CaptureApp.gameWidth/2, CaptureApp.gameHeight/2);
		c.textSize(32);
		c.textAlign(c.CENTER);
		c.fill(255);
		c.image(c.loadImage("base1.png"), (float)this.base1.getX(), (float)this.base1.getY());
		c.image(c.loadImage("base2.png"), (float)this.base2.getX(), (float)this.base2.getY());
		//this.base2.draw(c);
		
		// draws starting game instructions
		c.fill(221, 179, 107);
		c.rectMode(c.CENTER);
		c.rect(CaptureApp.gameWidth/2, 289, 280, 30, 10);
		c.rect(CaptureApp.gameWidth/2, 339, 353, 30, 10);
		c.fill(0, 0, 0);
		c.text("Place your bases", CaptureApp.gameWidth/2, 300);
		c.text("Press Enter continue", CaptureApp.gameWidth / 2 , 350);
		
		// instructions
		c.fill(147, 32, 33);
		c.text("Press S to save game", CaptureApp.gameWidth/2 + 360, 400);
		c.text("Press L to load saved game", CaptureApp.gameWidth/2 + 360, 430);
				
		return c;
	}

	/** prompts the user for a score */
	public IWorld getScore() {
		try {
			String score = javax.swing.JOptionPane.showInputDialog("What is the max score?");
			if (score == null) {
				return this;
			}
			Integer maxScore = Integer.parseInt(score);
			if (maxScore != null && maxScore > 0) {
				CaptureWorld world = new CaptureWorld(
						new Player (new Posn(this.base1.getX(), this.base1.getY()), Player.playerWidth, Player.playerHeight),
						new Player(new Posn(this.base2.getX(), this.base2.getY()), Player.playerWidth, Player.playerHeight), 
						new Flag(new Posn(this.base1.getX(), this.base1.getY()), Flag.flagWidth, Flag.flagHeight),
						new Flag(new Posn(this.base2.getX(), this.base2.getY()), Flag.flagWidth, Flag.flagHeight),
						new Base(new Posn(this.base1.getX(), this.base1.getY()), Base.baseWidth, Base.baseHeight, this.base1.getColor()), 
						new Base(new Posn(this.base2.getX(), this.base2.getY()), Base.baseWidth, Base.baseHeight, this.base2.getColor()), maxScore);
				return world;
			} else {
				javax.swing.JOptionPane.showMessageDialog(null, "Max Score has to be greater than 0");
				return this;
			}
		} catch (NumberFormatException e){
			javax.swing.JOptionPane.showMessageDialog(null, "Invalid number");
		}
		return this;
		
	}
	
	
	/* Handles the movement of the players positioning their bases
	 * and returns a new CaptureWorld when the bases are set. */
	public IWorld mouseDragged(MouseEvent mev) {
		int x = mev.getX();
		int y = mev.getY();
		Posn p = new Posn(x, y);
		
		if (this.base1.within(p)) {		
			Posn b1 = new Posn(x, y); // position of the first base
			
			Base newBase1 = this.base1.move(p, new Posn(Base.baseWidth/2, Base.baseHeight/2),
							new Posn(CaptureApp.gameWidth/2 - Base.baseWidth/2, CaptureApp.gameHeight - Base.baseHeight/2)); // keeps bases moved inside game window
			return new SetupWorld(newBase1, this.base2); 
		} // new Base (new Posn(x, y), 100, 100, new Color(255, 0, 0))
		else if (this.base2.within(p)) {
			Posn b2 = new Posn(x, y); // position of the first base
			
			Base newBase2 = this.base2.move(p, new Posn(CaptureApp.gameWidth/2 + Base.baseWidth/2, Base.baseHeight/2),
							new Posn(CaptureApp.gameWidth - Base.baseWidth/2, CaptureApp.gameHeight - Base.baseWidth/2)); // keeps bases moved inside game window
			return new SetupWorld(this.base1, newBase2); 
		} // new Base (new Posn(x, y), 100, 100, new Color(0, 0, 255))
		else {return this;}
	}
	
	
	
	public IWorld keyPressed(KeyEvent kev) {
		if (kev.getKey() == 's') {
			// save layout to a file
			return this;
			
		} else if (kev.getKey() == 'l') {
			// load layout from a file
			return this;
			// read in two base information from a file
			// return new SetupWorld( newbase1, newbase2 );
		} else if(kev.getKeyCode() == PApplet.ENTER) {
			return getScore();
		} 
		else {
			return this;
		}
	} 
	

	@Override
	public int hashCode() {
		return Objects.hash(base1, base2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SetupWorld other = (SetupWorld) obj;
		return Objects.equals(base1, other.base1) && Objects.equals(base2, other.base2);
	}

	@Override
	public String toString() {
		return "SetupWorld [b1=" + base1 + ", b2=" + base2 + "]";
	}
}
