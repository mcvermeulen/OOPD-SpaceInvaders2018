package nl.han.ica.SpaceInvaders2018;

import processing.core.PGraphics;
import processing.core.PImage;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class PlayerLives extends TextObject {
	
	private Sprite reserveCannon;
	
	public PlayerLives(String text, int textSize) {
		super(text, textSize);
		reserveCannon = new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Cannon.png");
		reserveCannon.resize(23, 15);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(PGraphics g) {
		g.textAlign(LEFT,TOP);
        g.textSize(textSize);
        g.text(text, getX(), getY());
		drawReserveCannons(g);
	}
	
	private void drawReserveCannons(PGraphics g) {
		int x = Integer.parseInt(text);
		if (x > 0) {
			PImage cannon = reserveCannon.getPImage();
			int width = reserveCannon.getWidth();
			int height = reserveCannon.getHeight();
			for (int i = 0; i < x - 1; i++) {
				g.image(cannon, getX() + width + (width*i), getY() + height/2);

			}
		}
	}

}
