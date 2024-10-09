import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import processing.event.KeyEvent;

/*
import processing.core.PApplet;
import processing.event.KeyEvent;
import org.junit.jupiter.api.Test;
*/
class CaptureAppTest {

	Posn posn1 = new Posn(50, 50);
	Posn posn2 = new Posn(0, 0);
	Posn posn3 = new Posn(100, 100);
	
	Player p1 = new Player(posn1, 0);
	Player p2 = new Player(posn2, 1);

	Flag f1 = new Flag(posn3);
	Flag f2 = new Flag(posn2);
	
	Base b1 = new Base(posn3, new Color(255,0,0));
	Base b2 = new Base(posn2, new Color(0, 0, 255));
	
	
	CaptureWorld w1 = new CaptureWorld(p1, p2, f1, f2, b1, b2);
	CaptureWorld w2 = new CaptureWorld(p2, p1, f1, f2, b1, b2);
	

	@Test
	void testCollisions() {
		assertEquals(true, p1.collided(new Player(new Posn(50, 50), 0)));
		assertEquals(false, p2.collided(p1));
		assertEquals(true, p1.collided(new Player(new Posn(25, 50), 0)));
		assertEquals(true, p2.collided(new Player(new Posn(20, 40), 0)));
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
		assertEquals(true, f1.collided(new Player(posn3, 0)));
		assertEquals(true, new Flag(new Posn(60, 50)).collided(p1));
		assertEquals(false, f1.collided(p2));
	}
	
	////////////////////////////
	@Test
	void testKeyPressed() {
		assertEquals(new CaptureWorld(
				new Player(new Posn(50, 50), new Posn(0, -4), 0),
				w1.player2, w1.flag1, w1.flag2, w1.base1, w1.base2), 
				w1.keyPressed(new KeyEvent(null, 0, KeyEvent.PRESS, 0, 'w', 'w')));
	}

	//////////////////////////////
	@Test
	void testPlayerUpdate() {
		assertEquals(p1, p1.update());
	}
	
	///////////////////////////////
	@Test
	void testPlayerMove() {
		assertEquals(new Player(p1.pos, p1.vel.move(posn2).bound(4), p1.score), p1.move(posn2));
	}
	
	@Test
	void testPlayerReset() {
		assertEquals(p1, p1.reset(p2, b1)); //no collision happens
		assertEquals(new Player(new Posn(b1.getX(), b1.getY()), p1.vel, p1.score), 
				p1.reset(new Player(new Posn(25, 50), 0), b1)); // a collision with a player happens and the player is reset to a given base
	}
}
