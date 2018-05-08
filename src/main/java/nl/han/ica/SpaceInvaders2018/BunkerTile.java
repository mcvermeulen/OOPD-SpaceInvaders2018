package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.Tile;

public abstract class BunkerTile extends Tile {
	
	protected int hitPoints;

    public BunkerTile(Sprite sprite) {
        super(sprite);
        hitPoints = 4;
    }
    
    public void setHitPoints(int hitPoints) {
    	this.hitPoints=hitPoints;
    }
    
    public int getHitPoints(){
    	return hitPoints;
    }
    
    public abstract void swapSprite();
}
