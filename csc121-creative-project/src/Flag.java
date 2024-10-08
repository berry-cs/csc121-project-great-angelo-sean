import java.util.Objects;

import processing.core.PApplet;

/** Represents a flag in the game */
public class Flag{
	Posn pos;
	int width = 20;
	int height = 30;

	Flag(Posn pos) {
		this.pos = pos;
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
		c.rect((int)this.getX(), (int)this.getY(), this.width, this.height);
		return c;
	}

	/** checks if this flag has collided with a player  */
	public boolean collided(Player that) {
		double fTop = this.getY() - (this.height/2);
		double fBottom = this.getY() + (this.height/2);
		double fLeft = this.getX() - (this.width/2);
		double fRight = this.getX() + (this.width/2);

		double pTop = that.getY() - (that.height/2);
		double pBottom = that.getY() + (that.height/2);
		double pLeft = that.getX() - (that.width/2);
		double pRight = that.getX() + (that.width/2);
		return (fLeft <= pRight &&
				fRight >= pLeft &&
				fTop <= pBottom &&
				fBottom >= pTop);
	}

	@Override
	public String toString() {
		return "Flag [pos=" + pos + ", width=" + width + ", height=" + height + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(height, pos, width);
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
		return height == other.height && Objects.equals(pos, other.pos) && width == other.width;
	}
}
