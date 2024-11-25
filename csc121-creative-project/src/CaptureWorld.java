import java.io.*;
import java.util.Objects;
import java.util.Scanner;

import processing.core.*;
import processing.event.KeyEvent;

public class CaptureWorld implements IWorld{
	private Player player1;
	private Player player2;
	private Flag flag1;
	private Flag flag2;
	private Base base1;
	private Base base2;
	private int winningScore;


	CaptureWorld(Player player1, Player player2, Flag flag1, Flag flag2, Base base1, Base base2, int winningScore) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.flag1 = flag1;
		this.flag2 = flag2;
		this.base1 = base1;
		this.base2 = base2;
		this.winningScore = winningScore;
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

		// adding images for each object
		c.imageMode(c.CENTER);
		c.image(c.loadImage("BackgroundAlpha.png"), CaptureApp.gameWidth/2, CaptureApp.gameHeight/2);
		c.image(c.loadImage("base1.png"), (float)this.base1.getX(), (float)this.base1.getY());
		c.image(c.loadImage("base2.png"), (float)this.base2.getX(), (float)this.base2.getY());
		c.image(c.loadImage("player1.png"), (float) this.player1.getX(), (float) this.player1.getY());
		c.image(c.loadImage("player2.png"), (float) this.player2.getX(), (float) this.player2.getY());
		c.image(c.loadImage("flag1.png"), (float) this.flag1.getX(), (float) this.flag1.getY());
		c.image(c.loadImage("flag2.png"), (float) this.flag2.getX(), (float) this.flag2.getY());

		
		// Draw the scores of each player on the screen
		c.fill(255, 0, 0);
		c.textFont(c.createFont("RioGrande.ttf", 50));
		c.fill(221, 179, 107);
		c.rect(CaptureApp.gameWidth/2, 43, 100, 45);
		c.fill(130, 24, 24);
		c.text("" + player1.getScore(), CaptureApp.gameWidth/2 - 25, 60);
		c.text("-", CaptureApp.gameWidth/2, 60);
		c.text("" + (player2.getScore()), CaptureApp.gameWidth/2 + 25, 60);
		c.textSize(20);
		c.fill(0, 0, 0);
		c.text("Winning Score - " + this.winningScore, CaptureApp.gameWidth/2, 90);

		return c;
	}

	/** saves the current state of the game */
	public void saveGame() {
		try {
			PrintWriter pw = new PrintWriter(new File ("saved_game.txt"));
			this.player1.writeToFile(pw);
			this.player2.writeToFile(pw);
			this.flag1.writeToFile(pw);
			this.flag2.writeToFile(pw);
			this.base1.writeToFile(pw);
			this.base2.writeToFile(pw);
			pw.println(this.winningScore);
			pw.close();
		} catch (IOException exp) {
			System.out.println("Problem saving the game: " + exp.getMessage());
		}
	}

	/** loads the saved game */
	public CaptureWorld loadGame() {
		try {
			Scanner sc = new Scanner(new File("saved_game.txt"));

			while (sc.hasNextInt()) {
				return new CaptureWorld(new Player(sc), new Player(sc), new Flag(sc), new Flag(sc), new Base(sc), new Base(sc), sc.nextInt());
			}

			sc.close();
		} catch (IOException exp) {
			System.out.println("Problem loading game: " + exp.getMessage());
		}
		return this;
	}


	/** Returns a new world of the updated player location */
	public IWorld keyPressed(KeyEvent kev) {
		switch(kev.getKeyCode()) {
		case PApplet.UP:
			return new CaptureWorld(this.player1, this.player2.move(new Posn(0, -4)), this.flag1, this.flag2, this.base1, this.base2, this.winningScore);
			
		case PApplet.DOWN:
			return new CaptureWorld(this.player1, this.player2.move(new Posn(0, 4)), this.flag1, this.flag2, this.base1, this.base2, this.winningScore);
			
		case PApplet.LEFT:
			return new CaptureWorld(this.player1, this.player2.move(new Posn(-4, 0)), this.flag1, this.flag2, this.base1, this.base2, this.winningScore);
			
		case PApplet.RIGHT:
			return new CaptureWorld(this.player1, this.player2.move(new Posn(4, 0)), this.flag1, this.flag2, this.base1, this.base2, this.winningScore);
		}
		
		if (kev.getKey() == 'w') {
			return new CaptureWorld(this.player1.move(new Posn(0, -4)), this.player2, this.flag1, this.flag2, this.base1, this.base2, this.winningScore);
		}
		else if (kev.getKey() == 'a') {
			return new CaptureWorld(this.player1.move(new Posn(-4, 0)), this.player2, this.flag1, this.flag2, this.base1, this.base2, this.winningScore);
		}
		else if (kev.getKey() == 's') {
			return new CaptureWorld(this.player1.move(new Posn(0, 4)), this.player2, this.flag1, this.flag2, this.base1, this.base2, this.winningScore);
		}
		else if (kev.getKey() == 'd') {
			return new CaptureWorld(this.player1.move(new Posn(4, 0)), this.player2, this.flag1, this.flag2, this.base1, this.base2, this.winningScore);
		}
		else if (kev.getKey() == 'S') {
			this.saveGame();
			return this;
		}
		else if (kev.getKey() == 'L') {
			return this.loadGame();
		}
		else {
			return this;
		}
	}
	/** Undoes the keyPressed method */
	public IWorld keyReleased (KeyEvent kev) {
		switch(kev.getKeyCode()) {
		case PApplet.UP:
			return new CaptureWorld(this.player1, this.player2.move(new Posn(0, 4)), this.flag1, this.flag2, this.base1, this.base2, this.winningScore);
			
		case PApplet.DOWN:
			return new CaptureWorld(this.player1, this.player2.move(new Posn(0, -4)), this.flag1, this.flag2, this.base1, this.base2, this.winningScore);
			
		case PApplet.LEFT:
			return new CaptureWorld(this.player1, this.player2.move(new Posn(4, 0)), this.flag1, this.flag2, this.base1, this.base2, this.winningScore);
			
		case PApplet.RIGHT:
			return new CaptureWorld(this.player1, this.player2.move(new Posn(-4, 0)), this.flag1, this.flag2, this.base1, this.base2, this.winningScore);
		}
		
		if (kev.getKey() == 'w') {
			return new CaptureWorld(this.player1.move(new Posn(0, 4)), this.player2, this.flag1, this.flag2, this.base1, this.base2, this.winningScore);
		}
		else if (kev.getKey() == 'a') {
			return new CaptureWorld(this.player1.move(new Posn(4, 0)), this.player2, this.flag1, this.flag2, this.base1, this.base2, this.winningScore);
		}
		else if (kev.getKey() == 's') {
			return new CaptureWorld(this.player1.move(new Posn(0, -4)), this.player2, this.flag1, this.flag2, this.base1, this.base2, this.winningScore);
		}
		else if (kev.getKey() == 'd') {
			return new CaptureWorld(this.player1.move(new Posn(-4, 0)), this.player2, this.flag1, this.flag2, this.base1, this.base2, this.winningScore);
		}
		else {
			return this;
		}
	}

	public CaptureWorld updateCollisions() {
		boolean playerCollision = this.player1.collided(this.player2);

		// Handles player collisions
		if (playerCollision) {
			if (this.player1.getHasFlag() && !this.player2.getHasFlag()) {// if player1 has the flag, and player2 doesn't, player1 can get reset. 
				return new CaptureWorld(this.player1.reset(base1).update(flag2), this.player2.update(flag1), 
						this.flag1.update(player2), this.flag2.update(player1).reset(base2), this.base1, this.base2, this.winningScore);
			}
			else if (this.player2.getX() <= 600|| !this.player1.getHasFlag() && this.player2.getHasFlag()) {				// if they collide in player1's territory, player2 and flag1 gets reset. 
				return new CaptureWorld(this.player1.update(flag2), this.player2.reset(base2).update(flag1), 
						this.flag1.update(player2).reset(base1), this.flag2.update(player1), this.base1, this.base2, this.winningScore);
			}
			else {											// if they collide in player2's territory, player1 and flag2 gets reset.
				return new CaptureWorld(this.player1.reset(base1).update(flag2), this.player2.update(flag1), 
						this.flag1.update(player2), this.flag2.update(player1).reset(base2), this.base1, this.base2, this.winningScore);
			}
		}
		else {
			return new CaptureWorld(this.player1.update(flag2), this.player2.update(flag1), 
					this.flag1.update(player2), this.flag2.update(player1), this.base1, this.base2, this.winningScore);
		}
	}

	// updates the scores of each player
	public CaptureWorld updateScores() {
		if (this.base1.collided(flag2)) {
			return new CaptureWorld(this.player1.addScore(), this.player2, this.flag1, this.flag2.reset(base2), this.base1, this.base2, this.winningScore);
		}
		else if (this.base2.collided(flag1)) {
			return new CaptureWorld(this.player1, this.player2.addScore(), this.flag1.reset(base1), this.flag2, this.base1, this.base2, this.winningScore);
		}
		else {
			return this;
		}
	}
	
	/** checks if there is a winning player */
	public IWorld checkWinner() {
		if ((this.player1.getScore() >= this.winningScore) || (this.player2.getScore() >= this.winningScore)) {
			return new EndScreen(this.player1, this.player2);
		}
		else {
			return this;
		}
	}

	/** gets the base1 of the game */
	public Base getBase1() {
		return this.base1;
	}

	/** gets the base2 of the game */
	public Base getBase2() {
		return this.base2;
	}

	/** Updates the state of the game */ 
	public IWorld update() {
		return this.updateScores().updateCollisions().checkWinner();
	}
	
	
	/** gets the winning score of the world */
	public int getWinningScore() {
		return this.winningScore;
	}
	
	/** gets player 1 */
	public Player getPlayer1() {
		return this.player1;
	}

	/** get player 2 */
	public Player getPlayer2() {
		return this.player2;
	}
	
	/** gets flag 1 */
	public Flag getFlag1() {
		return this.flag1;
	}

	/** gets flag 2 */
	public Flag getFlag2() {
		return this.flag2;
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