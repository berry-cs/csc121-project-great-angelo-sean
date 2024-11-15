import java.awt.Color;
import java.util.Objects;
import processing.core.*;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class SetupWorld implements IWorld{
	Base base1;
	Base base2;

	SetupWorld(Base base1, Base base2){
		this.base1 = base1;
		this.base2 = base2;
	}

	/** produces the image of the current game */
	public PApplet draw(PApplet c) {
		c.background(45);
		c.textSize(32);
		c.textAlign(c.CENTER);
		c.fill(255);
		this.base1.draw(c);
		this.base2.draw(c);
		return c;
	}

	/* Handles the movement of the players positioning their bases
	 * and returns a new CaptureWorld when the bases are set. */
	public IWorld mouseDragged(MouseEvent mev) {
		int x = mev.getX();
		int y = mev.getY();
		Posn p = new Posn(x, y);
		if (this.base1.within(p)) {		
			Posn b1 = new Posn(x, y); // position of the first base

			return new SetupWorld(new Base (new Posn(x, y), 100, 100, new Color(255, 0, 0)), this.base2); 
		}
		else if (this.base2.within(p)) {
			Posn b2 = new Posn(x, y); // position of the first base

			return new SetupWorld(this.base1, new Base (new Posn(x, y), 100, 100, new Color(0, 0, 255))); 
		}
		else {return this;}
	}
	public IWorld keyPressed(KeyEvent kev) {
		if(kev.getKeyCode() == PApplet.ENTER) {
			return new CaptureWorld(
					new Player (new Posn(this.base1.getX(), this.base1.getY()), 30, 50),
					new Player(new Posn(this.base2.getX(), this.base2.getY()), 30, 50), 
					new Flag(new Posn(this.base1.getX(), this.base1.getY()), 20, 30),
					new Flag(new Posn(this.base2.getX(), this.base2.getY()), 20, 30),
					new Base(new Posn(this.base1.getX(), this.base1.getY()), 100, 100, new Color(255, 0, 0)), 
					new Base(new Posn(this.base2.getX(), this.base2.getY()), 100, 100, new Color(0, 0, 255))); 
			} else {return this;}
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
