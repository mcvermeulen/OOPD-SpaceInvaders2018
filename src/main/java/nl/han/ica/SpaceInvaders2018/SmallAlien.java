package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

public class SmallAlien extends Alien {

    public SmallAlien(SpaceInvaders world, float x, float y, Sound alienKilled) {
        super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/SmallAlien.png"), 2, x, y, 16, 18, world, alienKilled);
        this.value = 30;
    }

}
