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
		if (this.base1.within(p) || this.base2.within(p)) {
			Posn b1 = new Posn(100, 100); // position of the first base
			Posn b2 = new Posn(300, 400); // position of the second base
			
			/* 
			 * Something that handles the logic of the mouse being pressed. 
			 */
			
			return new CaptureWorld(new Player(b1, 30, 50), new Player(b2, 30, 50), 
				   new Flag(b1, 20, 30), new Flag(b2, 20, 30), new Base(b1, 100, 100, 
				   new Color(255,0,0)), new Base(b2, 100, 100, new Color(0,0,255)));
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
