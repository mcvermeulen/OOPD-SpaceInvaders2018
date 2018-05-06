package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

/**
 * Grondlijn
 */
public class Ground extends GameObject {
	private int yPosition;
	private int xPosition1;
	private int xPosition2;

    /**
     * Constructor
     * @param yPosition Hoogte van de lijn
     * @param xPosition1 Beginpunt van de lijn
     * @param xPosition2 Einpunt van de lijn
     */
	public Ground (int yPosition, int xPosition1, int xPosition2) {
		this.yPosition = yPosition;
		this.xPosition1 = xPosition1;
		this.xPosition2 = xPosition2;
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
