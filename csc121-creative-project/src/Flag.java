import java.util.Objects;

import processing.core.PApplet;

/** Represents a flag in the game */
public class Flag extends AObject{
	
	public Flag(Posn pos, int width, int height) {
		super(pos, width = 20, height = 30);
	}


	/** produces the image of the current game */
	public PApplet draw(PApplet c) {
		c.fill(255, 0, 255);
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
