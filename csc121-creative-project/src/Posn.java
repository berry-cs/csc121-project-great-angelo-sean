import java.util.Objects;

import processing.core.PApplet;


public class Posn {
   private double x;
   private double y;
    
    public Posn(double x, double y) {
        this.x = x;
        this.y = y;
    }

    
    /** returns the x of a posn*/
    public double getX() {
        return x;
    }

    /** returns the y of a posn*/
    public double getY() {
        return y;
    }
    

	/** produce the difference between this and that posn */
    public Posn diff(Posn that) {
        return new Posn( this.x - that.x,  this.y - that.y );
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
    
    /** Adds the given posn to the posn */
    public Posn move(Posn p) {
    	return new Posn(this.x + p.x, this.y + p.y);
    }
    
    /** returns a posn where the x and y values are bounded by the given min and max */
    public Posn bound(Posn min, Posn max) {
    	return new Posn( Math.max(min.x, Math.min(max.x, this.x)),
			     Math.max(min.y, Math.min(max.y, this.y)) );
    }
    
    /** produces a posn where the magnitude of the x and y are limited to the given bound */
    public Posn bound(double mag) {
    	int signX = (this.x < 0)  ?  -1  : +1; 
    	int signY = (this.y < 0)  ?  -1  : +1; 
    	return new Posn(signX * Math.min(Math.abs(mag), Math.abs(this.x)),
    					signY * Math.min(Math.abs(mag), Math.abs(this.y)));
    }
}