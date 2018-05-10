package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PGraphics;

/**
 * Subklasse van BunkerTile, bevat de code voor alle schuinaflopende tiles
 */
public class BunkerSlopedTile extends BunkerTile {

	/**
	 * Draaihoek van de tile
	 */
	private int rotationAngle = 0;

	/**
	 * Constructor
	 * @param sprite		De sprite die in eerste instantie aan dit object wordt meegegeven
	 */
    public BunkerSlopedTile(Sprite sprite) {
        super(sprite);
        sprite4 = sprite;
        sprite3 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile2_3hp.png");
        sprite2 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile2_2hp.png");
        sprite1 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile2_1hp.png");
    }
    
    /**
     * Set een nieuwe draaihoek
     * @param angle			Nieuwe draaihoek
     */
    public void setRotationAngle(int angle) {
    	this.rotationAngle = angle;
    }
    
    /**
     * Haalt de huidige draaihoek op
     * @return				Huidige draaihoek
     */
    public int getRotationAngle() {
    	return rotationAngle;
    }
    
    /**
     * Verwisselt de sprites van het object, afhankelijk van de draaihoek van het object
     */
    public void swapAngleSprites() {
    	if (rotationAngle == 0) {
    		sprite4 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile2_4hp.png");
            sprite3 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile2_3hp.png");
            sprite2 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile2_2hp.png");
            sprite1 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile2_1hp.png");
    	}
    	else if (rotationAngle == 90) {
    		sprite4 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile3_4hp.png");
            sprite3 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile3_3hp.png");
            sprite2 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile3_2hp.png");
            sprite1 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile3_1hp.png");
    	}
    	else if (rotationAngle == 180) {
    		sprite4 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile4_4hp.png");
            sprite3 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile4_3hp.png");
            sprite2 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile4_2hp.png");
            sprite1 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile4_1hp.png");
    	}
    	else if (rotationAngle == 270) {
    		sprite4 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile5_4hp.png");
            sprite3 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile5_3hp.png");
            sprite2 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile5_2hp.png");
            sprite1 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile5_1hp.png");
    	}
    	swapSprite();
    }

}
