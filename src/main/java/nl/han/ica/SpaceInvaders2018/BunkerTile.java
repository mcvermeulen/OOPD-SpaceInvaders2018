package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.Tile;

/**
 * Superklasse BunkerTile
 */

public abstract class BunkerTile extends Tile {
    /**
     * Groene sprite, voor als de tile 4 hitpoints heeft (onbeschadigd)
     */
	protected Sprite sprite4;
	/**
	 * Gele sprite, voor als de tile 3 hitpoints heeft (lichtbeschadigd)
	 */
    protected Sprite sprite3;
    /**
     * Oranje sprite, voor als de tile 2 hitpoints heeft (zwaarbeschadigd)
     */
    protected Sprite sprite2;
    /**
     * Rode sprite,  voor als de tile 1 hitpoint heeft kritiek)
     */
    protected Sprite sprite1;
    
    /**
     * Hitpoints van de tile (4 = onbeschadigd, 3 = lichtbeschadigd, 2 = zwaarbeschadigd, 1 = kritiek, 0 of lager = gesloopt)
     */
	protected int hitPoints;

	/**
	 * Constructor
	 * @param sprite		De sprite die in eerste instantie aan dit object wordt meegegeven
	 */
    public BunkerTile(Sprite sprite) {
        super(sprite);
        hitPoints = 4;
    }
    
    /**
     * Set het aantal hitpoints
     * @param hitPoints		Nieuw aantal hitpoints
     */
    public void setHitPoints(int hitPoints) {
    	this.hitPoints=hitPoints;
    }
    
    /**
     * Geeft het huidige aantal hitpoints
     * @return				Huidige hitpoints
     */
    public int getHitPoints(){
    	return hitPoints;
    }
    
    /**
     * Verwisselt de sprites afhankelijk van aantal hitpoints
     */
    public void swapSprite() {
        if (hitPoints == 4) {
            setSprite(sprite4);
        }
        else if(hitPoints == 3) {
            setSprite(sprite3);
        }
        else if(hitPoints == 2) {
            setSprite(sprite2);
        }
        else if(hitPoints == 1) {
            setSprite(sprite1);
        }
    }
}
