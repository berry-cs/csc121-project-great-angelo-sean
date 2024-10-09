import java.util.Objects;

import java.awt.Color;

import processing.core.PApplet;

public class Base {

	Posn pos; // location of base
	int width = 100;
	int height = 100;
	Color color;
	
	

	Base(Posn pos, Color color) {
		super();
		this.pos = pos;
		this.color = color;
	}
	
	
	
	@Override
	public String toString() {
		return "Base [pos=" + pos + ", width=" + width + ", height=" + height + ", color=" + color + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(color, height, pos, width);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Base other = (Base) obj;
		return Objects.equals(color, other.color) && height == other.height && Objects.equals(pos, other.pos)
				&& width == other.width;
	}


	/** gets the y of a base */
	public double getY() {
		return this.pos.getY();
	}
	public double getX() {
		return this.pos.getX();
	}

	/** produces the image of the current base */
	public PApplet draw(PApplet c) {
		c.fill(color.getRGB());
		c.rectMode(c.CENTER);
		c.rect((int)this.getX(), (int)this.getY(), this.width, this.height);
		return c;
	}
	
	/** checks if this base has collided with a flag  */
	public boolean collided(Flag that) {
		double bTop = this.getY() - (this.height/2);
		double bBottom = this.getY() + (this.height/2);
		double bLeft = this.getX() - (this.width/2);
		double bRight = this.getX() + (this.width/2);

		double fTop = that.getY() - (that.height/2);
		double fBottom = that.getY() + (that.height/2);
		double fLeft = that.getX() - (that.width/2);
		double fRight = that.getX() + (that.width/2);
		return (bLeft <= fRight &&
				bRight >= fLeft &&
				bTop <= fBottom &&
				bBottom >= fTop);
	}

}
