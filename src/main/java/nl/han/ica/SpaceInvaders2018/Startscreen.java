package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import processing.core.PGraphics;

public class Startscreen extends GameObject{
    Sprite logo;
    SpaceInvaders world;
    TextObject scoreTabel, player1, play;

    public Startscreen(SpaceInvaders world) {
        this.logo = new Sprite("nl/han/ica/SpaceInvaders2018/media/titel.png");
        this.width = world.getPlayfieldWidth();
        this.height = world.getPlayfieldHeight();
        this.x = (world.getWidth() - width)/2;;
        this.y = (world.getHeight() - height)/2;
    }

    @Override
    public void mouseClicked(int x, int y, int button) {
        System.out.println("klik");
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PGraphics g) {
    }

}
