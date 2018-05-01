package nl.han.ica.SpaceInvaders2018;

import processing.core.PGraphics;

public class Score extends TextObject {
	
	Score(String text, int textSize) {
		super(text, textSize);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(PGraphics g) {
		g.textAlign(g.LEFT,g.TOP);
        g.textSize(textSize);
        g.text(text, getX(),getY());
	}

}
