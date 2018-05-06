package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import processing.core.PGraphics;

import java.util.List;

/**
 * Grondlijn
 */
public class Ground extends GameObject implements ICollidableWithGameObjects {
    SpaceInvaders world;
	private int yPosition;
	private int xPosition1;
	private int xPosition2;

    /**
     * Constructor
     * @param yPosition Hoogte van de lijn
     * @param xPosition1 Beginpunt van de lijn
     * @param xPosition2 Einpunt van de lijn
     */
	public Ground (SpaceInvaders world, int yPosition, int xPosition1, int xPosition2) {
	    this.world = world;
		this.yPosition = yPosition;
		this.xPosition1 = xPosition1;
		this.xPosition2 = xPosition2;
		setWidth(xPosition2 - xPosition1);
		setHeight(3);
		setX(xPosition1);
		setY(yPosition);
	}

	@Override
	public void update() {
	}

	@Override
	public void draw(PGraphics g) {
		g.stroke(204, 102, 0);
		g.strokeWeight(height);
		g.line(xPosition1, yPosition, xPosition2, yPosition);
	}

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
         for (GameObject g:collidedGameObjects) {
            if (g instanceof Alien) {
                System.out.println("Speler heeft verloren");
                world.updateHighscore();
                world.pauseGame();
            }
        }
    }
}
