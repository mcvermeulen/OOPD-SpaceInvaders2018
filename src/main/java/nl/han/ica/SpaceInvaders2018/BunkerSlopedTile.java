package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PGraphics;

public class BunkerSlopedTile extends BunkerTile {
	
	private Sprite sprite4;
	private Sprite sprite3;
	private Sprite sprite2;
	private Sprite sprite1;
	
	private int rotationAngle = 0;
	
	
    public BunkerSlopedTile(Sprite sprite) {
        super(sprite);
        sprite4 = sprite;
        sprite3 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile2_3hp.png");
        sprite2 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile2_2hp.png");
        sprite1 = new Sprite("src/main/java/nl/han/ica/SpaceInvaders2018/sprites/BunkerTile2_1hp.png");
    }
    
    public void setRotationAngle(int angle) {
    	this.rotationAngle = angle;
    }
    
    public int getRotationAngle() {
    	return rotationAngle;
    }
    
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
    }
    
    @Override
    public void swapSprite() {
    	if (hitPoints == 4) {
    		setSprite(sprite4);
    		sprite4.resize(20, 20);
    	}
    	else if(hitPoints == 3) {
    		setSprite(sprite3);
    		sprite3.resize(20, 20);
    	}
    	else if(hitPoints == 2) {
    		setSprite(sprite2);
    		sprite2.resize(20, 20);
    	}
    	else if(hitPoints == 1) {
    		setSprite(sprite1);
    		sprite1.resize(20, 20);
    	}
    }

    
}
