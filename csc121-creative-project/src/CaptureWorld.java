import java.util.Objects;
import processing.core.*;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class CaptureWorld implements IWorld{
	Player player1;
	Flag flag;
	
	CaptureWorld(Player player1, Flag flag) {
		super();
		this.player1 = player1;
		this.flag = flag;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(flag, player1);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaptureWorld other = (CaptureWorld) obj;
		return Objects.equals(flag, other.flag) && Objects.equals(player1, other.player1);
	}
	@Override
	public String toString() {
		return "CaptureWorld [player=" + player1 + ", flag=" + flag + "]";
	}
	
	/** produces the image of the current game */
	public PApplet draw(PApplet c) {
		c.background(255);
		c.fill(255, 0, 0);
		c.rect(this.flag.getX(), this.flag.getY(), 20, 30);
		c.fill(0, 0, 255);
		c.rect(this.player1.getX(), this.player1.getY(), 30, 50);
		return c;
	}
	
	
	/** Returns a new world of the updated player position */
	public IWorld keyPressed(KeyEvent kev) {
		if (kev.getKeyCode() == PApplet.UP) {
			return new CaptureWorld(this.player1.move(new Posn(0, -10)), this.flag);
		}
		else if (kev.getKeyCode() == PApplet.DOWN ) {
			return new CaptureWorld(this.player1.move(new Posn(0, 10)), this.flag);
		}
		else if (kev.getKeyCode() == PApplet.LEFT) {
			return new CaptureWorld(this.player1.move(new Posn(-10, 0)), this.flag);
		}
		else if (kev.getKeyCode() == PApplet.RIGHT) {
			return new CaptureWorld(this.player1.move(new Posn(10, 0)), this.flag);
		}
		else {
			return this;
		}
	}

}
