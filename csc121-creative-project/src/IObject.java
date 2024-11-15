
interface IObject {
	/** gets the x of an IObject */
	public double getX();
	
	/** gets the y of an IObject */
	public double getY();
	
	/** returns if an object has collided with another object */
	public boolean collided(AObject that);
	
}
