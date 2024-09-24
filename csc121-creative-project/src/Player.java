import java.util.Objects;

/** Represents a player in the game */
public class Player {
	Posn pos;
	int score;
	
	boolean movingUp;
	boolean movingDown;
	boolean movingRight;
	boolean movingLeft;
	
	Player(Posn pos, int score, boolean movingUp, boolean movingDown, boolean movingRight, boolean movingLeft) {
		super();
		this.pos = pos;
		this.score = score;
		this.movingUp = movingUp;
		this.movingDown = movingDown;
		this.movingRight = movingRight;
		this.movingLeft = movingLeft;
	}
	
	Player(Posn pos, int score){
		this.pos = pos;
		this.score = score;
		this.movingUp = false;
		this.movingDown = false;
		this.movingRight = false;
		this.movingLeft = false;
	}
	@Override
	public int hashCode() {
		return Objects.hash(movingDown, movingLeft, movingRight, movingUp, pos, score);
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
		return movingDown == other.movingDown && movingLeft == other.movingLeft && movingRight == other.movingRight
				&& movingUp == other.movingUp && Objects.equals(pos, other.pos) && score == other.score;
	}
	@Override
	public String toString() {
		return "Player [pos=" + pos + ", score=" + score + ", movingUp=" + movingUp + ", movingDown=" + movingDown
				+ ", movingRight=" + movingRight + ", movingLeft=" + movingLeft + "]";
	}
	
	
	/** gets the x of a player */
	public int getX() {
		return this.pos.getX();
	}
	
	/** gets the y of a player */
	public int getY() {
		return this.pos.getY();
	}
	
	/** Adds a given posn to a player */
	public Player move(Posn pos) {
		return new Player(this.pos.move(pos), this.score, this.movingDown, this.movingLeft, this.movingRight, this.movingUp);
	}
}
