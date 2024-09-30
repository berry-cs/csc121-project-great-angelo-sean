import java.util.Objects;

import processing.core.PApplet;

/** Represents a player in the game */
public class Player {
	Posn pos; // location of player
	Posn vel; // velocity of player
	int score;
	double speed = 10;
	
	// largest x/y values of the boundaries the player can go
	int width = 30;
	int height = 50;
	Posn minBounds = new Posn(this.width/2, (this.height/2));
	Posn maxBounds = new Posn(1000 - (this.width / 2), 600 - (this.height/2));
	int type;
	
	Player(Posn pos, int score) {
		this.pos = pos;
		this.vel = new Posn(0, 0);
		this.score = score;
	}
	
	Player(Posn pos, Posn vel, int score) {
		this.pos = pos;
		this.vel = vel;
		this.score = score;
	}

	/** gets the y of a player */
	public double getY() {
		return this.pos.getY();
	}
	
	public double getX() {
		return this.pos.getX();
	}
	
	
	/** produces the image of the current game */
	public PApplet draw(PApplet c) {
		c.fill(0, 255, 0);
		c.rectMode(c.CENTER);
		c.rect((int)this.getX(), (int)this.getY(), this.width, this.height);
		return c;
	}
	
	/** updates the location of the player */
	public Player update() {
		return new Player(this.pos.move(this.vel).bound(minBounds, maxBounds), this.vel, this.score);
	}
	
	/** Adds the given posn to the velocity of the player */
	public Player move(Posn p) {
		return new Player(this.pos, this.vel.move(p).bound(this.speed), this.score);
	}
	
	/** Stops the player by giving it a velocity of zero both directions */
	public Player stop() {
		return new Player(this.pos, new Posn(0, 0), this.score);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(height, maxBounds, minBounds, pos, score, type, vel, width);
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
		return height == other.height && Objects.equals(maxBounds, other.maxBounds)
				&& Objects.equals(minBounds, other.minBounds) && Objects.equals(pos, other.pos) && score == other.score
				&& type == other.type && Objects.equals(vel, other.vel) && width == other.width;
	}

	@Override
	public String toString() {
		return "Player [pos=" + pos + ", vel=" + vel + ", score=" + score + ", width=" + width + ", height=" + height
				+ ", minBounds=" + minBounds + ", maxBounds=" + maxBounds + ", type=" + type + "]";
	}
}
