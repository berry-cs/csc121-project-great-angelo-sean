import java.util.Objects;
import processing.core.*;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class CaptureWorld implements IWorld{
	Player player1;
	Player player2;
	Flag flag;
	
	CaptureWorld(Player player1, Player player2, Flag flag) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.flag = flag;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(flag, player1, player2);
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
		return Objects.equals(flag, other.flag) && Objects.equals(player1, other.player1)
				&& Objects.equals(player2, other.player2);
	}



	@Override
	public String toString() {
		return "CaptureWorld [player1=" + player1 + ", player2=" + player2 + ", flag=" + flag + "]";
	}



	/** produces the image of the current game */
	public PApplet draw(PApplet c) {
		c.background(45);
		this.player1.draw(c);
		this.player2.draw(c);
		this.flag.draw(c);
		return c;
	}
	
	
	/** Returns a new world of the updated player location */
	public IWorld keyPressed(KeyEvent kev) {
		if (kev.getKeyCode() == PApplet.UP) {
			return new CaptureWorld(this.player1.move(new Posn(0, -5)), this.player2, this.flag);
		}
		else if (kev.getKeyCode() == PApplet.DOWN ) {
			return new CaptureWorld(this.player1.move(new Posn(0, 5)), this.player2, this.flag);
		}
		else if (kev.getKeyCode() == PApplet.LEFT) {
			return new CaptureWorld(this.player1.move(new Posn(-5, 0)), this.player2, this.flag);
		}
		else if (kev.getKeyCode() == PApplet.RIGHT) {
			return new CaptureWorld(this.player1.move(new Posn(5, 0)), this.player2, this.flag);
		}
		else if (kev.getKey() == 'w') {
			return new CaptureWorld(this.player1, this.player2.move(new Posn(0, -5)), this.flag);
		}
		else if (kev.getKey() == 'a') {
			return new CaptureWorld(this.player1, this.player2.move(new Posn(-5, 0)), this.flag);
		}
		else if (kev.getKey() == 's') {
			return new CaptureWorld(this.player1, this.player2.move(new Posn(0, 5)), this.flag);
		}
		else if (kev.getKey() == 'd') {
			return new CaptureWorld(this.player1, this.player2.move(new Posn(5, 0)), this.flag);
		}
		else {
			return this;
		}
	}
	/** Undoes the keyPressed method */
	public IWorld keyReleased (KeyEvent kev) {
		if (kev.getKeyCode() == PApplet.UP) {
			return new CaptureWorld(this.player1.move(new Posn(0, 5)), this.player2, this.flag);
		}
		else if (kev.getKeyCode() == PApplet.DOWN ) {
			return new CaptureWorld(this.player1.move(new Posn(0, -5)), this.player2, this.flag);
		}
		else if (kev.getKeyCode() == PApplet.LEFT) {
			return new CaptureWorld(this.player1.move(new Posn(5, 0)), this.player2, this.flag);
		}
		else if (kev.getKeyCode() == PApplet.RIGHT) {
			return new CaptureWorld(this.player1.move(new Posn(-5, 0)), this.player2, this.flag);
		}
		else if (kev.getKey() == 'w') {
			return new CaptureWorld(this.player1, this.player2.move(new Posn(0, 5)), this.flag);
		}
		else if (kev.getKey() == 'a') {
			return new CaptureWorld(this.player1, this.player2.move(new Posn(5, 0)), this.flag);
		}
		else if (kev.getKey() == 's') {
			return new CaptureWorld(this.player1, this.player2.move(new Posn(0, -5)), this.flag);
		}
		else if (kev.getKey() == 'd') {
			return new CaptureWorld(this.player1, this.player2.move(new Posn(-5, 0)), this.flag);
		}
		else {
			return this;
		}
	}

	
	/** Updates the state of the game */ 
	public IWorld update() {
		return new CaptureWorld(this.player1.update(), this.player2.update(), this.flag);
	}
}