package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class MediumAlien extends Alien {

    public MediumAlien() {
        super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/MediumAlien.png"), 2);

        setCurrentFrameIndex(0);
        setDirectionSpeed(direction, 2);
    }

}
