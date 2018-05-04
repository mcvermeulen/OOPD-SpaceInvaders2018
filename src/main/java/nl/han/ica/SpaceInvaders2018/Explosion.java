package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

public class Explosion extends AnimatedSpriteObject {
    public Explosion() {
        super(new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Explosion.png"), 3);
    }

    @Override
    public void update() {
        nextFrame();
    }
}
