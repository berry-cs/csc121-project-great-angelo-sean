import java.util.Objects;


public class Posn {
    int x;
    int y;
    
    public Posn(int x, int y) {
        this.x = x;
        this.y = y;
    }

    
    /** returns the x of a posn*/
    public int getX() {
        return x;
    }

    /** returns the y of a posn*/
    public int getY() {
        return y;
    }
    
    
    /** produces the distance between this and that */
    public double distanceTo(Posn that) {
        return Math.sqrt( Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2) );
    }

	/** produce the difference between that and this posn */
    public Posn diff(Posn that) {
        return new Posn( that.x - this.x,  that.y - this.y );
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Posn other = (Posn) obj;
        return x == other.x && y == other.y;
    }

    @Override
	public String toString() {
		return "Posn [x=" + x + ", y=" + y + "]";
	}
    
    public Posn move(Posn p) {
    	return new Posn(this.x + p.x, this.y + p.y);
    }

}