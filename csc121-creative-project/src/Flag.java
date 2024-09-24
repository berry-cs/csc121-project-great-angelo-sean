import java.util.Objects;

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
	public int getX() {
		return this.pos.getX();
	}
	
	/** Gets the y of a flag's posn */
	public int getY() {
		return this.pos.getY();
	}
	
	
}
