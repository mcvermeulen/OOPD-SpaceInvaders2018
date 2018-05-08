package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class BunkerSlopedTile2 extends BunkerTile {
	
	private Sprite sprite3;
	private Sprite sprite2;
	private Sprite sprite1;
	
    public BunkerSlopedTile2(Sprite sprite) {
        super(sprite);
        sprite3 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile3_3hp.png");
        sprite2 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile3_2hp.png");
        sprite1 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile3_1hp.png");
    }
    
    @Override
    public void swapSprite() {
    	if(hitPoints == 3) {
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
