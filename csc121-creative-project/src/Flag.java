import java.util.Objects;

import processing.core.PApplet;

/** Represents a flag in the game */
public class Flag extends AObject{
	
	public static int flagHeight = 30;
	public static int flagWidth = 20;
	
	public Flag(Posn pos, int width, int height) {
		super(pos, width, height);
	}


	/** produces the image of the current game */
	public PApplet draw(PApplet c) {
		c.fill(255, 0, 255);
		c.rectMode(c.CENTER);
		c.rect((int)this.getX(), (int)this.getY(), this.width, this.height);
		return c;
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
	
	/** updates the location of the flag if it has collided with a player */
	public Flag update(Player that) {
		if (this.collided(that)) {
			return new Flag(new Posn(that.getX(), that.getY()), this.width, this.height);
		}
		else {
			return this;
		}
	}
	
	/*
	 * This can either be in the player class or the flag class. If a player collides with a flag,
	 * then the flag's position is updated to follow the player and the player's boolean hasFlag is set to true. 
	 */
	
	/** Resets the position of the flag to a given base */
	public Flag reset(Base b1) {
		return new Flag(new Posn(b1.getX(), b1.getY()), this.width, this.height);
	}
}
