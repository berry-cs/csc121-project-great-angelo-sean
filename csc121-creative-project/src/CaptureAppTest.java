import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
	
	CaptureWorld w1 = new CaptureWorld(p1, p2, f1, f2);
	CaptureWorld w2 = new CaptureWorld(p2, p1, f1, f2);
	

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
	
	/*
	@Test
	void testKeyPressed() {
	...
	}
	*/

	/*
	@Test
	void testPlayerUpdate() {
	...
	}
	 */
}
