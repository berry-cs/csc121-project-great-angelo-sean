import java.util.Objects;

import processing.core.PApplet;

/** Represents a player in the game */
public class Player extends AObject{
	Posn vel; // velocity of player
	int score;
	double speed = 4;

	public static int playerHeight = 50;
	public static int playerWidth = 30;
	
	boolean hasFlag;
	Posn minBounds = new Posn(this.width/2, (this.height/2));
	Posn maxBounds = new Posn(CaptureApp.gameWidth - (this.width / 2), CaptureApp.gameHeight - (this.height/2));

	public Player(Posn pos, int width, int height, int score) {
		super(pos, width, height);
		this.vel = new Posn(0, 0);
		this.score = score;
		this.hasFlag = false;
	}

	public Player(Posn pos, int width, int height, Posn vel, int score) {
		super(pos, width, height);
		this.vel = vel;
		this.score = score;
		this.hasFlag = false;
	}
	
	public Player(Posn pos, int width, int height) {
		super(pos, width, height);
		this.vel = new Posn(0, 0);
		this.score = 0;
		this.hasFlag = false;
	}
	
	// method to gather score for scoreboard
	public int getScore() {
		return this.score;
	}

	public Player(Posn pos, int width, int height, Posn vel, int score, boolean hasFlag) {
		super(pos, width = 30, height = 50);
		this.vel = vel;
		this.score = score;
		this.hasFlag = hasFlag;
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
		return new Player(this.pos.move(this.vel).bound(minBounds, maxBounds), this.width, this.height, this.vel, this.score, flagToCheck.collided(this));
	}

	/** Adds the given posn to the velocity of the player */
	public Player move(Posn p) {
		return new Player(this.pos, this.width, this.height, this.vel.move(p).bound(this.speed) ,this.score);
	}

	/** Resets the position of the player when the player collides */
	public Player reset(Base b1) {
		return new Player(new Posn(b1.getX(), b1.getY()), this.width, this.height, this.vel, this.score);
	}
	
	/** Increases the score of the player by 1 */
	public Player addScore() {
		return new Player(this.pos, this.width, this.height, this.vel, this.score + 1, this.hasFlag);
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
