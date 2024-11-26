import java.util.Objects;
import java.awt.Color;
import processing.core.PApplet;
import processing.event.KeyEvent;

public class EndScreen implements IWorld {
	private Player player1;
	private Player player2;
	
	EndScreen(Player player1, Player player2){
		this.player1 = player1;
		this.player2 = player2;
	}
	
	/** produces the image of the current game */
	public PApplet draw(PApplet c) {
		c.imageMode(c.CENTER);
		c.image(c.loadImage("BackgroundAlpha.png"), CaptureApp.gameWidth/2, CaptureApp.gameHeight/2);
		c.textSize(32);
		c.textAlign(c.CENTER); 
		if (this.player1.getScore() > this.player2.getScore()) {
			c.text("Player 1 Wins!", CaptureApp.gameWidth/2, CaptureApp.gameHeight/2);
		} else {
			c.text("Player 2 Wins!", CaptureApp.gameWidth/2, CaptureApp.gameHeight/2);
		}
		c.text("Press ENTER to restart", CaptureApp.gameWidth/2, CaptureApp.gameHeight/2 + 100);
		c.fill(255);
		return c;
	}
	
	public IWorld keyPressed(KeyEvent kev) {
		if (kev.getKeyCode() == PApplet.ENTER) {
			return new SetupWorld(new Base(CaptureApp.startingBase1, Base.baseWidth, Base.baseHeight, new Color(0, 255, 255)),
					new Base(CaptureApp.startingBase2, Base.baseWidth, Base.baseHeight, new Color(0, 255, 255)));
			
		} else {
			return this;
		}
	}
	
	@Override
	public String toString() {
		return "EndScreen [player1=" + player1 + ", player2=" + player2 + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(player1, player2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EndScreen other = (EndScreen) obj;
		return Objects.equals(player1, other.player1) && Objects.equals(player2, other.player2);
	}
}
