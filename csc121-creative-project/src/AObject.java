
abstract class AObject implements IObject{
	Posn pos;
	int width;
	int height;
	
	AObject(Posn pos, int width, int height) {
		super();
		this.pos = pos;
		this.width = width;
		this.height = height;
	}

	/** gets the y of a player */
	public double getY() {
		return this.pos.getY();
	}

	/** gets the y of a player */
	public double getX() {
		return this.pos.getX();
	}

	public boolean collided(AObject that) {
		double o1Top = this.getY() - (this.height/2);
		double o1Bottom = this.getY() + (this.height/2);
		double o1Left = this.getX() - (this.width/2);
		double o1Right = this.getX() + (this.width/2);

		double o2Top = that.getY() - (that.height/2);
		double o2Bottom = that.getY() + (that.height/2);
		double o2Left = that.getX() - (that.width/2);
		double o2Right = that.getX() + (that.width/2);
		return (o1Left <= o2Right &&
				o1Right >= o2Left &&
				o1Top <= o2Bottom &&
				o1Bottom >= o2Top);
	}
	
}
