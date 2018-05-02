package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

public class Ground extends GameObject {
	
	private int yPosition;
	private int xPosition1;
	private int xPosition2;
	
	public Ground (int yPosition, int xPosition1, int xPosition2) {
		this.yPosition = yPosition;
		this.xPosition1 = xPosition1;
		this.xPosition2 = xPosition2;
	}
	
	public int getYPosition() {
		return yPosition;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(PGraphics g) {
		g.stroke(204, 102, 0);
		g.strokeWeight(3);
		g.line(xPosition1, yPosition, xPosition2, yPosition);
	}

}
