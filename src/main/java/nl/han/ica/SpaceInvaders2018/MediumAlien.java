package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;

public class MediumAlien extends Alien {

    public MediumAlien(SpaceInvaders world, float x, float y, Sound alienKilled) {
        super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/MediumAlien.png"), 2, x, y, 22, 18, world, alienKilled);
        this.value = 20;
    }

}
