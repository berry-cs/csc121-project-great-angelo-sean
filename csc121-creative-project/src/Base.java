import java.util.Objects;

import java.awt.Color;

import processing.core.PApplet;

public class Base extends AObject{
	Color color;
	
	

	public Base(Posn pos, int width, int height, Color color) {
		super(pos, width = 100, height = 100);
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



	/** produces the image of the current base */
	public PApplet draw(PApplet c) {
		c.fill(color.getRGB());
		c.rectMode(c.CENTER);
		c.rect((int)this.getX(), (int)this.getY(), this.width, this.height);
		return c;
	}

}
