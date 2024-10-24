import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

//import processing.core.PApplet;
import processing.event.KeyEvent;

//import processing.core.PApplet;
//import processing.event.KeyEvent;
//import org.junit.jupiter.api.Test;

class CaptureAppTest {

	Posn posn1 = new Posn(50, 50);
	Posn posn2 = new Posn(0, 0);
	Posn posn3 = new Posn(100, 100);
	
	Player p1 = new Player(posn1, 30, 50, 0);
	Player p2 = new Player(posn2, 30, 50, 1);
	Player p3 = new Player(posn3, 30, 50, new Posn(0, -4), 0);

	Flag f1 = new Flag(posn3, 20, 30);
	Flag f2 = new Flag(posn2, 20, 30);
	
	Base b1 = new Base(posn3, 100, 100, new Color(255,0,0));
	Base b2 = new Base(posn2, 100, 100, new Color(0, 0, 255));
	
	
	CaptureWorld w1 = new CaptureWorld(p1, p2, f1, f2, b1, b2);
	CaptureWorld w2 = new CaptureWorld(p2, p1, f1, f2, b1, b2);
	CaptureWorld w3 = new CaptureWorld(p1, p2, f1, f2, b2, b1);
	

	@Test
	void testPlayerCollide() {
		assertEquals(true, p1.collided(new Player(new Posn(50, 50), 30, 50, 0)));
		assertEquals(false, p2.collided(p1));
		assertEquals(true, p1.collided(new Player(new Posn(25, 50), 30, 50, 0)));
		assertEquals(true, p2.collided(new Player(new Posn(20, 40), 30, 50, 0)));
	}
	

	@Test
	void testPosnDiff() {
		assertEquals(new Posn(20, 10), new Posn(30, 20).diff(new Posn(10, 10)));
		assertEquals(posn2, posn1.diff(posn1));
	}
	
	@Test
	void posnMove() {
		assertEquals(new Posn(20, 10), new Posn(20, 10).move(posn2));
		assertEquals(posn3, posn1.move(posn1));
	}
	
	@Test
	void testGetXAndY() {
		assertEquals(50, posn1.getX());
		assertEquals(50, p1.getX());
		assertEquals(100, posn3.getX());
		assertEquals(0, posn2.getY());
	}
	
	@Test
	void testFlagCollide() {
		assertEquals(true, f1.collided(new Player(posn3, 30, 50, 0)));
		assertEquals(true, new Flag(new Posn(60, 50), 20, 30).collided(p1));
		assertEquals(false, f1.collided(p2));
	}
	
	@Test
	void testKeyPressed() {
		assertEquals(new CaptureWorld(
				new Player(new Posn(50, 50), 30, 50, new Posn(0, -4), 0),
				w1.player2, w1.flag1, w1.flag2, w1.base1, w1.base2), 
				w1.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, 'w', 'w')));
	}
	
	@Test
	void testPlayerUpdate() {
		assertEquals(new Player(new Posn(100, 96), 30, 50, p3.vel, 0, true), p3.update(f1)); // adds the velocity of the player to the player's position
																					// and the player hasFlag. 
		assertEquals(new Player(posn1, 30, 50, p1.vel, 0, p1.hasFlag), p1.update(f1)); //the velocity of the player is 0
		assertEquals(new Player(posn2.move(p2.vel).bound(p2.minBounds, p2.maxBounds), 30, 50, p2.vel, p2.score, p2.hasFlag), p2.update(f1));
	}
	
	@Test
	void testPlayerMove() {
		assertEquals(new Player(p1.pos, 30, 50, p1.vel.move(posn2).bound(4), p1.score), p1.move(posn2));
		assertEquals( new Player(new Posn(50, 50), 30, 50,  new Posn(4, 0), 0),
					  new Player(new Posn(50, 50), 30, 50, 0).move(new Posn(10, 0)) );
		assertEquals( new Player(new Posn(50, 50), 30, 50,  new Posn(2, 0), 0),
				  new Player(new Posn(50, 50), 30, 50, 0).move(new Posn(2, 0)) );
		assertEquals( new Player(new Posn(50, 50), 30, 50, new Posn(0, -4), 0),
				  new Player(new Posn(50, 50), 30, 50, 0).move(new Posn(0, -7)) );

	}
	@Test
	void testPlayerReset() {
		assertEquals(new Player(new Posn(b1.getX(), b1.getY()), 30, 50, p1.vel, p1.score), 
				p1.reset(b1)); // the player is reset to a given base
	}
	
	// tests a base colliding with a flag
	@Test
	void testBaseCollide() {
		assertEquals(true, b1.collided(f1));
		assertEquals(false, b2.collided(f1));
		assertEquals(true, b1.collided(new Flag(new Posn(100, 105), 20, 30)));
	}
	
	@Test
	void testAddScore() {
		assertEquals(new Player(p1.pos, 30, 50, 1), p1.addScore());
		assertEquals(new Player(p2.pos, 30, 50, 2), p2.addScore());
	}
	
	@Test
	void testFlagReset() {
		assertEquals(f1, f1.reset(b1));
		assertEquals(new Flag(new Posn(f1.getX(), f1.getY()), 20, 30), f1.reset(b1));
	}
	
	@Test
	void testUpdateCollisions() {
		assertEquals(new CaptureWorld(w1.player1.update(w1.flag2), 
				new Player(posn2.move(p2.vel).bound(p2.minBounds, p2.maxBounds), 30, 50, p2.vel, p2.score, p2.hasFlag), 
				w1.flag1, w1.flag2, w1.base1, w1.base2), 
				w1.updateCollisions());
	}
	
	@Test
	void testUpdateScores() {
		assertEquals(w1, w1.updateScores()); // flags don't go to rival's base so nothing happens
		assertEquals(new CaptureWorld
				(new Player(p1.pos, 30, 50, 1), p2, w3.flag1,
						new Flag(new Posn(100, 100), 20, 30), b2, b1), w3.updateScores());
	}
	
	@Test
	void testFlagUpdate() {
		assertEquals(new Flag(new Posn(p3.getX(), p3.getY()), f1.width, f1.height), f1.update(p3) );
		assertEquals(new Flag(posn1, 20, 30), new Flag(new Posn(60, 50), 20, 30).update(p1));
		assertEquals(f1, f1.update(p3));
	
	}
	
	@Test
	void testCaptureWorldUpdate() {
		assertEquals(new CaptureWorld(w1.player1.update(w1.flag2), 
				new Player(posn2.move(p2.vel).bound(p2.minBounds, p2.maxBounds), 30, 50, p2.vel, p2.score, p2.hasFlag), 
				w1.flag1, w1.flag2, w1.base1, w1.base2), 
				w1.update());
		
		assertEquals(new CaptureWorld				// the score and the collisions are updated
				(new Player(p1.pos, 30, 50, 1), new Player(new Posn(15, 25), 30, 50, 1), w3.flag1,
						new Flag(new Posn(100, 100), 20, 30), b2, b1), w3.update());
	}
	
	@Test
	void testKeyReleased() {
		assertEquals(new CaptureWorld(
				new Player(new Posn(50, 50), 30, 50, new Posn(0, 4), 0),
				w1.player2, w1.flag1, w1.flag2, w1.base1, w1.base2), 
				w1.keyReleased(new KeyEvent(null, 0, KeyEvent.RELEASE, 0, 'w', 'w')));
	}
	 
	@Test
	void testGetScore() { 			// gets the score of a player - tests getScore
		assertEquals(0, p1.getScore());
		assertEquals(1, p2.getScore());
	}

}
