import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import processing.core.PApplet;
//import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

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

	SetupWorld s1 = new SetupWorld(b1, b2);
	SetupWorld s2 = new SetupWorld(b2, b1);

	CaptureWorld w1 = new CaptureWorld(p1, p2, f1, f2, b1, b2, 5);
	CaptureWorld w2 = new CaptureWorld(p2, p1, f1, f2, b1, b2, 1);
	CaptureWorld w3 = new CaptureWorld(p1, p2, f1, f2, b2, b1, 3);
	CaptureWorld w4 = new CaptureWorld(p1, p2, f1, f2, b1, b2, 1);
	
	EndScreen es1 = new EndScreen(w4.getPlayer1(), w4.getPlayer2());

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
				w1.getPlayer2(), w1.getFlag1(), w1.getFlag2(), w1.getBase1(), w1.getBase2(), 5), 
				w1.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, 'w', 'w')));
	}

	@Test
	void testPlayerUpdate() {
		assertEquals(new Player(new Posn(100, 96), 30, 50, p3.getVel(), 0, true), p3.update(f1)); // adds the velocity of the player to the player's position
		// and the player hasFlag. 
		assertEquals(new Player(posn1, 30, 50, p1.getVel(), 0, p1.getHasFlag()), p1.update(f1)); //the velocity of the player is 0
		assertEquals(new Player(posn2.move(p2.getVel()).bound(p2.getMinB(), p2.getMaxB()), 30, 50, p2.getVel(), p2.getScore(), p2.getHasFlag()), p2.update(f1));
	}

	@Test
	void testPlayerMove() {
		assertEquals(new Player(p1.pos, 30, 50, p1.getVel().move(posn2).bound(4), p1.getScore()), p1.move(posn2));
		assertEquals( new Player(new Posn(50, 50), 30, 50,  new Posn(4, 0), 0),
				new Player(new Posn(50, 50), 30, 50, 0).move(new Posn(10, 0)) );
		assertEquals( new Player(new Posn(50, 50), 30, 50,  new Posn(2, 0), 0),
				new Player(new Posn(50, 50), 30, 50, 0).move(new Posn(2, 0)) );
		assertEquals( new Player(new Posn(50, 50), 30, 50, new Posn(0, -4), 0),
				new Player(new Posn(50, 50), 30, 50, 0).move(new Posn(0, -7)) );

	}
	@Test
	void testPlayerReset() {
		assertEquals(new Player(new Posn(b1.getX(), b1.getY()), 30, 50, p1.getVel(), p1.getScore()), 
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
		assertEquals(new CaptureWorld(w1.getPlayer1().update(w1.getFlag2()), 
				new Player(posn2.move(p2.getVel()).bound(p2.getMinB(), p2.getMaxB()), 30, 50, p2.getVel(), p2.getScore(), p2.getHasFlag()), 
				w1.getFlag1(), w1.getFlag2(), w1.getBase1(), w1.getBase2(), 5), 
				w1.updateCollisions());
	}

	@Test
	void testGetColor() {
		assertEquals(new Color(255,0,0), b1.getColor());
		assertEquals(new Color(0, 0, 255), b2.getColor());
	}

	@Test
	void testUpdateScores() {
		assertEquals(w1, w1.updateScores()); // flags don't go to rival's base so nothing happens
		assertEquals(new CaptureWorld
				(new Player(p1.pos, 30, 50, 1), p2, w3.getFlag1(),
						new Flag(new Posn(100, 100), 20, 30), b2, b1, 3), w3.updateScores());
	}

	@Test
	void testFlagUpdate() {
		assertEquals(new Flag(new Posn(p3.getX(), p3.getY()), f1.width, f1.height), f1.update(p3) );
		assertEquals(new Flag(posn1, 20, 30), new Flag(new Posn(60, 50), 20, 30).update(p1));
		assertEquals(f1, f1.update(p3));

	}

	@Test
	void testCaptureWorldUpdate() {
		assertEquals(new CaptureWorld(w1.getPlayer1().update(w1.getFlag2()), 
				new Player(posn2.move(p2.getVel()).bound(p2.getMinB(), p2.getMaxB()), 30, 50, p2.getVel(), p2.getScore(), p2.getHasFlag()), 
				w1.getFlag1(), w1.getFlag2(), w1.getBase1(), w1.getBase2(), 5), 
				w1.update());

		assertEquals(new CaptureWorld				// the score and the collisions are updated
				(new Player(p1.pos, 30, 50, 1), new Player(new Posn(15, 25), 30, 50, 1), w3.getFlag1(),
						new Flag(new Posn(100, 100), 20, 30), b2, b1, 3), w3.update());
	}

	@Test
	void testKeyReleased() {
		assertEquals(new CaptureWorld(
				new Player(new Posn(50, 50), 30, 50, new Posn(0, 4), 0),
				w1.getPlayer2(), w1.getFlag1(), w1.getFlag2(), w1.getBase1(), w1.getBase2(), 5), 
				w1.keyReleased(new KeyEvent(null, 0, KeyEvent.RELEASE, 0, 'w', 'w')));
	}

	@Test
	void testGetScore() { 			// gets the score of a player - tests getScore
		assertEquals(0, p1.getScore());
		assertEquals(1, p2.getScore());
	}

	@Test
	void testMoveBaseRestrictions() { 
		// tests the new implementations in our move method for bases
		assertEquals(new Base(new Posn (50, 50), 100, 100, b1.getColor()), b1.move(posn3, posn2, posn1));
	}

	@Test
	void testWithin() {
		assertEquals(true, b1.within(posn3)); // posn3 is within b1
		assertEquals(false, b1.within(new Posn(0, 0)));
	}

	@Test
	void testGetting() {
		assertEquals(p1, w1.getPlayer1());
		assertEquals(p2, w1.getPlayer2());
		assertEquals(b1, w1.getBase1());
		assertEquals(b2, w1.getBase2());
		assertEquals(f1, w1.getFlag1());
		assertEquals(f2, w1.getFlag2());

		assertEquals(false, p1.getHasFlag());
		assertEquals(false, p2.getHasFlag());

		assertEquals(new Posn(0, 0), p1.getVel());
		assertEquals(new Posn(0, -4), p3.getVel());

		assertEquals(new Posn(15, 25), p2.getMinB());
		assertEquals(new Posn(1185, 675), p2.getMaxB());
	}

	@Test
	void testSetupKeyPressed() {
		//		assertEquals(new CaptureWorld(new Player (b1.pos, 30, 50, new Posn(0, 0), 0),
		//				new Player(b2.pos, 30, 50, new Posn(0, 0), 0), new Flag(b1.pos, 20, 30), new Flag(b2.pos, 20, 30), s1.base1, s1.base2)
		//				, s1.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, '\0', PApplet.ENTER)));
		assertEquals(s1, s1.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, '\0', PApplet.UP)));
		//		assertEquals(new ScoreWorld(new CaptureWorld(new Player (b1.pos, 30, 50, new Posn(0, 0), 0),
		//				new Player(b2.pos, 30, 50, new Posn(0, 0), 0), new Flag(b1.pos, 20, 30), new Flag(b2.pos, 20, 30), 
		//				s1.base1, s1.base2)), s1.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, '\0', PApplet.ENTER)));
	}
	
	@Test
	void testCheckWinner() {
		assertEquals(w1, w1.checkWinner());
		assertEquals(es1, w4.checkWinner());
	}
	
	@Test
	void testMouseDragged() {
		assertEquals(s1, s1.mouseDragged(new MouseEvent(null, 1, 0, 0, CaptureApp.gameWidth/2, CaptureApp.gameHeight/2, 0, 1)));	// nothing happens
	}
	
	@Test
	void testEndScreenKeyPressed() {
		assertEquals(es1, es1.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, '\0', PApplet.UP)));
		assertEquals(new SetupWorld(new Base(CaptureApp.startingBase1, Base.baseWidth, Base.baseHeight, new Color(0, 255, 255)),
									new Base(CaptureApp.startingBase2, Base.baseWidth, Base.baseHeight, new Color(0, 255, 255))), 
				es1.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, '\0', PApplet.ENTER)));
	}

}
