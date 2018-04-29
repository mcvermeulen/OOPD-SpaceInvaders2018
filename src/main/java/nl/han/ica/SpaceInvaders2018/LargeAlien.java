package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PApplet;

public class LargeAlien extends Alien {

    public LargeAlien() {
        super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/LargeAlien.png"), 2);

        setCurrentFrameIndex(0);
        setDirectionSpeed(direction, 2);
    }

}
