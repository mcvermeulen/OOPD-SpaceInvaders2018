package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import processing.core.PGraphics;
import processing.core.PImage;

public class Startscreen extends GameObject{
    private PImage logo;
    private SpaceInvaders world;
    private TextObject scoreTabel, player1, play;

    public Startscreen(SpaceInvaders world) {
        this.logo = world.loadImage("nl/han/ica/SpaceInvaders2018/media/titel.png");
        this.world = world;
        this.width = world.getPlayfieldWidth();
        this.height = world.getPlayfieldHeight();
        this.x = (world.getWidth() - width)/2;;
        this.y = (world.getHeight() - height)/2;
    }

    @Override
    public void mouseClicked(int x, int y, int button) {
        System.out.println("klik");
        if (world.getGameState() == 0) {
            world.newGame();
        }

    }

    @Override
    public void update() {
        if (world.getGameState() != 0) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
    }

    @Override
    public void draw(PGraphics g) {
        g.imageMode(CENTER);
        g.image(logo, x+width/2, y+logo.height);
    }

}
