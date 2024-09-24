import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import processing.core.PApplet;
import processing.event.KeyEvent;
import org.junit.jupiter.api.Test;

class CaptureAppTest {

	Posn posn1 = new Posn(50, 50);
	Posn posn2 = new Posn(0, 0);
	Posn posn3 = new Posn(100, 100);
	
	Player p1 = new Player(posn1, 0);
	Player p2 = new Player(posn2, 1);

	Flag f1 = new Flag(posn3);
	
	CaptureWorld w1 = new CaptureWorld(p1, p2, f1);
	CaptureWorld w2 = new CaptureWorld(p2, p1, f1);
	
	@Test
	void testPosnMove() {
		assertEquals(new Posn(50, 60), posn1.move(new Posn(0, 10)));
		assertEquals(new Posn(0, -10), posn2.move(new Posn(0, -10)));
		assertEquals(new Posn(120, 20), posn3.move(new Posn(20, -80)));
	}
	
	@Test
	void testPlayerMove() {
		assertEquals(new Player(posn1, 0), p1.move(new Posn(0, 0)));
		assertEquals(new Player(new Posn(60, 50), p1.score), p1.move(new Posn(10, 0)));
	}
	
	@Test
	void KeyPressed() {
		assertEquals(w1, w1.keyPressed(new KeyEvent(null, 0, 0, 0, '\0', PApplet.X)));
		assertEquals(new CaptureWorld(new Player(new Posn (50, 60), p1.score), w1.player2, w1.flag), 
				w1.keyPressed(new KeyEvent(null, 0, 0, 0, '\0', PApplet.DOWN)));
		assertEquals(new CaptureWorld(new Player(new Posn (60, 50), p1.score), w1.player2, w1.flag), 
				w1.keyPressed(new KeyEvent(null, 0, 0, 0, '\0', PApplet.RIGHT)));
	}
}
