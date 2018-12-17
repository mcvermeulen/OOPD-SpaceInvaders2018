package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import processing.core.PGraphics;
import processing.core.PImage;
import static nl.han.ica.SpaceInvaders2018.SpaceInvaders.getGameState;
import static nl.han.ica.SpaceInvaders2018.SpaceInvaders.setGameState;

/**
 * Eindscherm van het spel
 */
public class GameOver extends GameObject{

    private SpaceInvaders world;

    /**
     * Constructor
     * @param world		Referentie naar de hoofdmodule
     */
    public GameOver(SpaceInvaders world) {
        this.world = world;
        this.width = world.getPlayfieldWidth();
        this.height = world.getPlayfieldHeight();
        this.x = (world.getWidth() - width)/2;;
        this.y = (world.getHeight() - height)/2;
    }

    @Override
    public void mousePressed(int x, int y, int button) {
        restartGame();
    }

    private void restartGame() {
        if (getGameState() == GameState.END) {
            world.addGameObject(new Startscreen(world));
            setGameState(GameState.START);
            world.deleteAllDashboards();
            world.createDashboard(world.getWidth(), world.getHeight());
            world.deleteGameObject(this);
        }
    }

    @Override
    public void update() {
    }

    /**
     * Tekent het eindscherm
     */
    @Override
    public void draw(PGraphics g) {
        float xCenter = x+width/2;
        g.textAlign(CENTER, BOTTOM);
        g.textSize(72);
        g.fill(0xFFFF0000);
        g.text("GAME OVER", xCenter, y+height/2);
        if (world.getScore() > world.getHighscore()) {
            g.textSize(36);
            g.fill(0xFF00EE00);
            g.text("You beat the highscore!!", xCenter, y+(height/6)*4);
        }
        g.fill(0xFFFFFFFF);
        g.textSize(18);
        g.text("Try again?", xCenter, this.height);
        world.makeEmptyTileMap();
    }


}

