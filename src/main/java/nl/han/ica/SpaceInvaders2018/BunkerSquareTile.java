package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * Subklasse van BunkerTile, bevat de code voor alle vierkante tiles
 */
public class BunkerSquareTile extends BunkerTile {
	
	/**
	 * Constructor
	 * @param sprite		De sprite die in eerste instantie aan dit object wordt meegegeven
	 */
    public BunkerSquareTile(Sprite sprite) {
        super(sprite);
        sprite3 = sprite;
        sprite2 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile1_2hp.png");
        sprite1 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile1_1hp.png");
    }
}
