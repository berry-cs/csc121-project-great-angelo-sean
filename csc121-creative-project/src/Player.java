import java.util.Objects;

import processing.core.PApplet;

/** Represents a player in the game */
public class Player {
	Posn pos; // location of player
	Posn vel; // velocity of player
	int score;
	double speed = 4;

	// largest x/y values of the boundaries the player can go
	int width = 30;
	int height = 50;
	boolean hasFlag;
	Posn minBounds = new Posn(this.width/2, (this.height/2));
	Posn maxBounds = new Posn(1200 - (this.width / 2), 700 - (this.height/2));

	Player(Posn pos, int score) {
		this.pos = pos;
		this.vel = new Posn(0, 0);
		this.score = score;
		this.hasFlag = false;
	}

	Player(Posn pos, Posn vel, int score) {
		this.pos = pos;
		this.vel = vel;
		this.score = score;
		this.hasFlag = false;
	}

	Player(Posn pos, Posn vel, int score, boolean hasFlag) {
		this.pos = pos;
		this.vel = vel;
		this.score = score;
		this.hasFlag = hasFlag;
	}

	/** gets the y of a player */
	public double getY() {
		return this.pos.getY();
	}
	public double getX() {
		return this.pos.getX();
	}


	/** produces the image of the current player */
	public PApplet draw(PApplet c) {
		if (hasFlag) {
			c.fill(0, 255, 0);
		} else {
			c.fill(0, 100, 0);
		}
		c.rectMode(c.CENTER);
		c.rect((int)this.getX(), (int)this.getY(), this.width, this.height);
		return c;
	}

	/** updates the location of the player */
	public Player update(Flag flagToCheck) {
		return new Player(this.pos.move(this.vel).bound(minBounds, maxBounds), this.vel, this.score, flagToCheck.collided(this));
	}

	/** Adds the given posn to the velocity of the player */
	public Player move(Posn p) {
		return new Player(this.pos, this.vel.move(p).bound(this.speed), this.score);
	}

	/** checks if this player has collided with that player  */
	public boolean collided(Player that) {
		double p1Top = this.getY() - (this.height/2);
		double p1Bottom = this.getY() + (this.height/2);
		double p1Left = this.getX() - (this.width/2);
		double p1Right = this.getX() + (this.width/2);

		double p2Top = that.getY() - (that.height/2);
		double p2Bottom = that.getY() + (that.height/2);
		double p2Left = that.getX() - (that.width/2);
		double p2Right = that.getX() + (that.width/2);
		return (p1Left <= p2Right &&
				p1Right >= p2Left &&
				p1Top <= p2Bottom &&
				p1Bottom >= p2Top);
	}
	/** Resets the position of the player when the player collides */
	public Player reset(Base b1) {
		return new Player(new Posn(b1.getX(), b1.getY()), this.vel, this.score);
	}
	
	/** Increases the score of the player by 1 */
	public Player addScore() {
		return new Player(this.pos, this.vel, this.score + 1, this.hasFlag);
	}

	@Override
	public int hashCode() {
		return Objects.hash(hasFlag, height, maxBounds, minBounds, pos, score, speed, vel, width);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return hasFlag == other.hasFlag && height == other.height && Objects.equals(maxBounds, other.maxBounds)
				&& Objects.equals(minBounds, other.minBounds) && Objects.equals(pos, other.pos) && score == other.score
				&& Double.doubleToLongBits(speed) == Double.doubleToLongBits(other.speed)
				&& Objects.equals(vel, other.vel) && width == other.width;
	}

	@Override
	public String toString() {
		return "Player [pos=" + pos + ", vel=" + vel + ", score=" + score + ", speed=" + speed + ", width=" + width
				+ ", height=" + height + ", hasFlag=" + hasFlag + ", minBounds=" + minBounds + ", maxBounds="
				+ maxBounds + "]";
	}

}
