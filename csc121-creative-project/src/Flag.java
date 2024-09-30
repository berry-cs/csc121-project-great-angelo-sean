import java.util.Objects;

import processing.core.PApplet;

/** Represents a flag in the game */
public class Flag {
	Posn pos;

	Flag(Posn pos) {
		super();
		this.pos = pos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flag other = (Flag) obj;
		return Objects.equals(pos, other.pos);
	}

	@Override
	public String toString() {
		return "Flag [pos=" + pos + "]";
	}
	
	/** Gets the x of a flag's posn */
	public double getX() {
		return this.pos.getX();
	}
	
	/** Gets the y of a flag's posn */
	public double getY() {
		return this.pos.getY();
	}
	
	/** produces the image of the current game */
	public PApplet draw(PApplet c) {
		c.fill(0, 0, 255);
		c.rect((int)this.getX(), (int)this.getY(), 20, 30);
		return c;
	}
}
