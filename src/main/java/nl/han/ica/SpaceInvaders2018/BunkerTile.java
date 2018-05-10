package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.Tile;

public abstract class BunkerTile extends Tile {
    protected Sprite sprite4;
    protected Sprite sprite3;
    protected Sprite sprite2;
    protected Sprite sprite1;
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
