import java.util.Objects;

import java.awt.Color;

import processing.core.PApplet;

public class Base extends AObject{
	Color color;
	
	Posn minBounds = new Posn(this.width/2, (this.height/2));
	Posn maxBounds = new Posn(CaptureApp.gameWidth - (this.width / 2), CaptureApp.gameHeight - (this.height/2));
	
	

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
	
	/** moves this base towards that posn */
	public Base move(Posn p) {
		
		int BoundaryX = (int) Math.max(minBounds.x, Math.min(maxBounds.x, p.x));
		int BoundaryY = (int) Math.max(minBounds.y, Math.min(maxBounds.y, p.y));
		
		return new Base(new Posn(BoundaryX, BoundaryY), this.width, this.height, this.color); 
		//return new Base (this.pos.move(p), this.width, this.height, this.color);
	}
	
}
