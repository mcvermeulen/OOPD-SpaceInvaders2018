package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import processing.core.PApplet;
import processing.core.PImage;

public class SpaceInvaders extends GameEngine {
    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.SpaceInvaders2018.SpaceInvaders"});
    }

    @Override
    public void setupGame() {
        createView(1280, 800);

        Cannon kanon= new Cannon(this);
        addGameObject(kanon, 700, 700);
    }

    @Override
    public void update() {

    }

    private void createView(int viewWidth, int viewHeight) {
        View view = new View(viewWidth, viewHeight);
        PImage backgroundImg = loadImage("nl/han/ica/SpaceInvaders2018/media/background-1280x800-2.jpg");
        view.setBackground(backgroundImg);

        size(viewWidth, viewHeight);
        setView(view);
    }
}
