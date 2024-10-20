import java.util.Objects;
import processing.core.*;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class CaptureWorld implements IWorld{
	Player player1;
	Player player2;
	Flag flag1;
	Flag flag2;
	Base base1;
	Base base2;



	CaptureWorld(Player player1, Player player2, Flag flag1, Flag flag2, Base base1, Base base2) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.flag1 = flag1;
		this.flag2 = flag2;
		this.base1 = base1;
		this.base2 = base2;
		
	}
	

	/** produces the image of the current game */
	public PApplet draw(PApplet c) {
		c.background(45);
		c.textSize(32);
		c.textAlign(c.CENTER);
		c.fill(255);
		this.base1.draw(c);
		this.base2.draw(c);
		this.player1.draw(c);
		this.player2.draw(c);
		this.flag1.draw(c);
		this.flag2.draw(c);
		
		// Draw the scores of each player on the screen
		c.fill(255, 0, 0);
		c.text("Player 1 Score: " + player1.getScore(), c.width / 3, 60);
		c.fill(0, 0, 255);
		c.text("Player 2 Score: " + (player2.getScore() - 1), (3 * c.width) / 5, 60);
		
		return c;
	}
	
	


	/** Returns a new world of the updated player location */
	public IWorld keyPressed(KeyEvent kev) {
		if (kev.getKeyCode() == PApplet.UP) {
			return new CaptureWorld(this.player1, this.player2.move(new Posn(0, -4)), this.flag1, this.flag2, this.base1, this.base2);
		}
		else if (kev.getKeyCode() == PApplet.DOWN ) {
			return new CaptureWorld(this.player1, this.player2.move(new Posn(0, 4)), this.flag1, this.flag2, this.base1, this.base2);
		}
		else if (kev.getKeyCode() == PApplet.LEFT) {
			return new CaptureWorld(this.player1, this.player2.move(new Posn(-4, 0)), this.flag1, this.flag2, this.base1, this.base2);
		}
		else if (kev.getKeyCode() == PApplet.RIGHT) {
			return new CaptureWorld(this.player1, this.player2.move(new Posn(4, 0)), this.flag1, this.flag2, this.base1, this.base2);
		}
		else if (kev.getKey() == 'w') {
			return new CaptureWorld(this.player1.move(new Posn(0, -4)), this.player2, this.flag1, this.flag2, this.base1, this.base2);
		}
		else if (kev.getKey() == 'a') {
			return new CaptureWorld(this.player1.move(new Posn(-4, 0)), this.player2, this.flag1, this.flag2, this.base1, this.base2);
		}
		else if (kev.getKey() == 's') {
			return new CaptureWorld(this.player1.move(new Posn(0, 4)), this.player2, this.flag1, this.flag2, this.base1, this.base2);
		}
		else if (kev.getKey() == 'd') {
			return new CaptureWorld(this.player1.move(new Posn(4, 0)), this.player2, this.flag1, this.flag2, this.base1, this.base2);
		}
		else {
			return this;
		}
	}
	/** Undoes the keyPressed method */
	public IWorld keyReleased (KeyEvent kev) {
		if (kev.getKeyCode() == PApplet.UP) {
			return new CaptureWorld(this.player1, this.player2.move(new Posn(0, 4)), this.flag1, this.flag2, this.base1, this.base2);
		}
		else if (kev.getKeyCode() == PApplet.DOWN ) {
			return new CaptureWorld(this.player1, this.player2.move(new Posn(0, -4)), this.flag1, this.flag2, this.base1, this.base2);
		}
		else if (kev.getKeyCode() == PApplet.LEFT) {
			return new CaptureWorld(this.player1, this.player2.move(new Posn(4, 0)), this.flag1, this.flag2, this.base1, this.base2);
		}
		else if (kev.getKeyCode() == PApplet.RIGHT) {
			return new CaptureWorld(this.player1, this.player2.move(new Posn(-4, 0)), this.flag1, this.flag2, this.base1, this.base2);
		}
		else if (kev.getKey() == 'w') {
			return new CaptureWorld(this.player1.move(new Posn(0, 4)), this.player2, this.flag1, this.flag2, this.base1, this.base2);
		}
		else if (kev.getKey() == 'a') {
			return new CaptureWorld(this.player1.move(new Posn(4, 0)), this.player2, this.flag1, this.flag2, this.base1, this.base2);
		}
		else if (kev.getKey() == 's') {
			return new CaptureWorld(this.player1.move(new Posn(0, -4)), this.player2, this.flag1, this.flag2, this.base1, this.base2);
		}
		else if (kev.getKey() == 'd') {
			return new CaptureWorld(this.player1.move(new Posn(-4, 0)), this.player2, this.flag1, this.flag2, this.base1, this.base2);
		}
		else {
			return this;
		}
	}

	public IWorld updateCollisions() {
		boolean playerCollision = this.player1.collided(this.player2);

		// Handles player collisions
		if (playerCollision) {
			if (this.player1.hasFlag && !this.player2.hasFlag) {// if player1 has the flag, and player2 doesn't, player1 can get reset. 
				return new CaptureWorld(this.player1.reset(base1).update(flag2), this.player2.update(flag1), 
						this.flag1.update(player2), this.flag2.update(player1).reset(base2), this.base1, this.base2);
			}
			else if (this.player2.getX() <= 600|| !this.player1.hasFlag && this.player2.hasFlag) {				// if they collide in player1's territory, player2 and flag1 gets reset. 
				return new CaptureWorld(this.player1.update(flag2), this.player2.reset(base2).update(flag1), 
						this.flag1.update(player2).reset(base1), this.flag2.update(player1), this.base1, this.base2);
			}
			else {											// if they collide in player2's territory, player1 and flag2 gets reset.
				return new CaptureWorld(this.player1.reset(base1).update(flag2), this.player2.update(flag1), 
						this.flag1.update(player2), this.flag2.update(player1).reset(base2), this.base1, this.base2);
			}
		}
		else {
			return new CaptureWorld(this.player1.update(flag2), this.player2.update(flag1), 
					this.flag1.update(player2), this.flag2.update(player1), this.base1, this.base2);
		}
	}
	
	// updates the scores of each player
	public CaptureWorld updateScores() {
		if (this.base1.collided(flag2)) {
			return new CaptureWorld(this.player1.addScore(), this.player2, this.flag1, this.flag2.reset(base2), this.base1, this.base2);
		}
		else if (this.base2.collided(flag1)) {
			return new CaptureWorld(this.player1, this.player2.addScore(), this.flag1.reset(base1), this.flag2, this.base1, this.base2);
		}
		else {
			return this;
		}
	}

	/** Updates the state of the game */ 
	public IWorld update() {
		return this.updateScores().updateCollisions();
	}

	@Override
	public String toString() {
		return "CaptureWorld [player1=" + player1 + ", player2=" + player2 + ", flag1=" + flag1 + ", flag2=" + flag2
				+ "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(flag1, flag2, player1, player2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaptureWorld other = (CaptureWorld) obj;
		return Objects.equals(flag1, other.flag1) && Objects.equals(flag2, other.flag2)
				&& Objects.equals(player1, other.player1) && Objects.equals(player2, other.player2);
	}
}