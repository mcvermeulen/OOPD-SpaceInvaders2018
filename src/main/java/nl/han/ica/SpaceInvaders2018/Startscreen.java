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
     * Teksten op het startscherm
     */
	private String scoreTabel, play;

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
        this.play = "Click to play";
        this.scoreTabel = "* score advance table *";
    }

    /**
     * Zorgt dat het spel start als er met de muis wordt geklikt
     */
    @Override
    public void mousePressed(int x, int y, int button) {
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
        float xCenter = x+width/2;
        g.imageMode(CENTER);
        g.textAlign(CENTER, CENTER);
        g.image(logo, xCenter, y+logo.height);
        g.textSize(28);
        g.text(play, xCenter, y+height/2);
        drawScoreTabel(g, xCenter, y+height/2+100);
    }

    private void drawScoreTabel(PGraphics g, float center, float y) {
        PImage[] scoreObjecten = {
                world.loadImage("nl/han/ica/SpaceInvaders2018/sprites/Ruimteschip.png"),
                world.loadImage("nl/han/ica/SpaceInvaders2018/sprites/SmallAlien.png"),
                world.loadImage("nl/han/ica/SpaceInvaders2018/sprites/MediumAlien.png"),
                world.loadImage("nl/han/ica/SpaceInvaders2018/sprites/LargeAlien.png")
        };
        String[] scoreTexten = {
                "= ? MYSTERY",
                "= 30 points",
                "= 20 points",
                "= 10 points"
        };
        int imageOffset = 40;
        int lineHeight = 40;
        int indent = 35;
        g.textSize(20);
        g.textAlign(CENTER, CENTER);
        g.text(scoreTabel, center, y);
        g.imageMode(CENTER);
        g.textAlign(LEFT, CENTER);
        for (int i = 1; i <= 4; i++) {
            if (i > 1) {
                g.image(scoreObjecten[i-1].get(0,0,scoreObjecten[i-1].width/2, scoreObjecten[i-1].height), center-imageOffset-indent, y+i*lineHeight);
            } else {
                g.image(scoreObjecten[i-1], center-imageOffset-indent, y+i*lineHeight);
            }
            g.text(scoreTexten[i-1], center-indent, y+i*lineHeight);
        }
    }

}
