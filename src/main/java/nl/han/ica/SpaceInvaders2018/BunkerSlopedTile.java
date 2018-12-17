package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PGraphics;

/**
 * Subklasse van BunkerTile, bevat de code voor alle schuinaflopende tiles
 */
public class BunkerSlopedTile extends BunkerTile {

	private int rotationAngle = 0;

	/**
	 * Constructor
	 * @param sprite		De sprite die in eerste instantie aan dit object wordt meegegeven
	 */
    public BunkerSlopedTile(Sprite sprite) {
        super(sprite);
        sprite3 = sprite;
        sprite2 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile2_2hp.png");
        sprite1 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile2_1hp.png");
    }

    public void setRotationAngle(int angle) {
    	this.rotationAngle = angle;
    }

    public int getRotationAngle() {
    	return rotationAngle;
    }
    
    /**
     * Verwisselt de sprites van het object, afhankelijk van de draaihoek van het object
     */
    public void swapAngleSprites() {
    	if (rotationAngle == 0) {
            sprite3 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile2_3hp.png");
            sprite2 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile2_2hp.png");
            sprite1 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile2_1hp.png");
    	}
    	else if (rotationAngle == 90) {
            sprite3 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile3_3hp.png");
            sprite2 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile3_2hp.png");
            sprite1 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile3_1hp.png");
    	}
    	else if (rotationAngle == 180) {
            sprite3 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile4_3hp.png");
            sprite2 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile4_2hp.png");
            sprite1 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile4_1hp.png");
    	}
    	else if (rotationAngle == 270) {
            sprite3 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile5_3hp.png");
            sprite2 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile5_2hp.png");
            sprite1 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile5_1hp.png");
    	}
    	swapSprite();
    }

}
