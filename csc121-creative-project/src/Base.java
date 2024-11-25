import java.util.Objects;
import java.util.Scanner;
import java.awt.Color;
import java.io.PrintWriter;

import processing.core.PApplet;

public class Base extends AObject{
	private Color color;
	
	private Posn minBounds = new Posn(this.width/2, (this.height/2));
	private Posn maxBounds = new Posn(CaptureApp.gameWidth - (this.width / 2), CaptureApp.gameHeight - (this.height/2));
	
	public static int baseWidth = 100;
	public static int baseHeight = 100;

	public Base(Posn pos, int width, int height, Color color) {
		super(pos, width = 100, height = 100);
		this.color = color;
	}
	/** returns the color of a base */
	public Color getColor() {
		return this.color;
	}
	
	/** Reads from a scanner and creates a Base */
	public Base(Scanner sc) {
		super(new Posn(sc.nextInt(), sc.nextInt()), baseWidth, baseHeight);
		this.color = Color.decode(sc.next());
	}
	
	/**
	 * prints the state of this tile to the given output object
	 */
	public void writeToFile(PrintWriter pw) {
		pw.println(Math.round(this.pos.getX()) + " " + Math.round(this.pos.getY()) + " " + this.color.getRGB());
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
	public Base move(Posn p, Posn min, Posn max) {
		
		int BoundaryX = (int) Math.max(min.getX(), Math.min(max.getX(), p.getX()));
		int BoundaryY = (int) Math.max(min.getY(), Math.min(max.getY(), p.getY()));	// Broke after changing Posn class fields to private
		
		return new Base(new Posn(BoundaryX, BoundaryY), this.width, this.height, this.color); 
		//return new Base (this.pos.move(p), this.width, this.height, this.color);
	}
	
}
