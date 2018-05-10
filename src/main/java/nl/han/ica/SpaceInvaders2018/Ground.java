package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;
import java.util.List;

/**
 * Grondlijn klasse
 */
public class Ground extends GameObject implements ICollidableWithGameObjects {
    /**
     * Referentie naar de hoofdmodule
     */
	private SpaceInvaders world;
	/**
	 * Y-positie van de lijn
	 */
	private int yPosition;
	/**
	 * Eerste x-positie van de lijn
	 */
	private int xPosition1;
	/**
	 * Tweede x-positie van de lijn
	 */
	private int xPosition2;

    /**
     * Constructor
     * @param world		Referentie naar de hoofdmodule
     * @param yPosition Hoogte van de lijn
     */
	public Ground (SpaceInvaders world, int yPosition) {
	    this.world = world;
		this.yPosition = yPosition;
		this.xPosition1 = (world.getWidth() - world.getPlayfieldWidth())/2;
		this.xPosition2 = (world.getWidth() + world.getPlayfieldWidth())/2;
		setWidth(xPosition2 - xPosition1);
		setHeight(3);
		setX(xPosition1);
		setY(yPosition);
	}

	@Override
	public void update() {
	}

	/**
	 * Tekent de grondlijn
	 */
	@Override
	public void draw(PGraphics g) {
		g.stroke(204, 102, 0);
		g.strokeWeight(height);
		g.line(xPosition1, yPosition, xPosition2, yPosition);
	}

	/**
	 * Geeft aan wat er gebeurt wanneer de aliens de grondlijn bereiken
	 */
    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
         for (GameObject g:collidedGameObjects) {
            if (g instanceof Alien) {
            	world.endGame();
            }
        }
    }
}
