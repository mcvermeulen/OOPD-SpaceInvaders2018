package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import processing.core.PApplet;

public class SmallAlien extends Alien {

    public SmallAlien() {
        super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/SmallAlien.png"), 2);

        setCurrentFrameIndex(0);
        setDirectionSpeed(direction, 2);
    }

}
