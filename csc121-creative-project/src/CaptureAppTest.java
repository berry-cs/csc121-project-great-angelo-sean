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
	void testCol() {
		assertEquals(true, p1.col(new Player(new Posn(50, 50), 0)));
	}
}
