package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

public class LargeAlien extends Alien {
	
    public LargeAlien(SpaceInvaders world, float x, float y, Sound alienKilled) {
        super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/LargeAlien.png"), 2, x, y, 24, 18, world, alienKilled);
        this.value = 10;
    }

}
