package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import processing.core.PGraphics;
import processing.core.PImage;
import static nl.han.ica.SpaceInvaders2018.SpaceInvaders.getGameState;

/**
 * Startscherm van het spel
 */
public class Startscreen extends GameObject{
	/** 
     * Logo van het spel
     */
	private PImage logo;
	/**
     * Referentie naar de hoofdmodule
     */
	private SpaceInvaders world;
	/**
     * Tekstobject
     */
	private TextObject scoreTabel, player1, play;

	/**
     * Constructor
     * @param world		Referentie naar de hoofdmodule
     */
    public Startscreen(SpaceInvaders world) {
        this.logo = world.loadImage("nl/han/ica/SpaceInvaders2018/media/titel.png");
        this.world = world;
        this.width = world.getPlayfieldWidth();
        this.height = world.getPlayfieldHeight();
        this.x = (world.getWidth() - width)/2;;
        this.y = (world.getHeight() - height)/2;
    }

    /**
     * Zorgt dat het spel start als er met de muis wordt geklikt
     */
    @Override
    public void mouseClicked(int x, int y, int button) {
        System.out.println("klik");
        if (getGameState() == GameState.START) {
            world.newGame();
        }
    }

    /**
     * Verandert het speelscherm als de GameState niet 0 is
     */
    @Override
    public void update() {
        if (getGameState() != GameState.START) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
    }

    /**
     * Tekent het startscherm
     */
    @Override
    public void draw(PGraphics g) {
        g.imageMode(CENTER);
        g.image(logo, x+width/2, y+logo.height);
    }

}
