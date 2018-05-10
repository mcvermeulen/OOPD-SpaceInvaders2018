package nl.han.ica.SpaceInvaders2018;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.TextObject;
import processing.core.PGraphics;
import processing.core.PImage;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * Klasse voor de levens van de speler
 */
public class PlayerLives extends TextObject {
	
	/**
	 * Afbeelding voor het reservekanon dat als extra leven wordt getekend
	 */
	private Sprite reserveCannon;
	/**
	 * Geeft aan dat het eerste bonusleven is toegekend
	 */
	private boolean firstLifeAwarded;
	/**
	 * Geeft aan dat het tweede bonusleven is toegekend
	 */
	private boolean secondLifeAwarded;
	/**
	 * Referentie naar de hoofdmodule
	 */
	private SpaceInvaders world;
	
	/**
	 * Constructor
	 * @param text		tekst die eventueel bij de levens kan worden gezet
	 * @param textSize	lettergrootte
	 * @param world		referentie naar de hoofdmodule
	 */
	public PlayerLives(String text, int textSize, SpaceInvaders world) {
		super(text, textSize);
		reserveCannon = new Sprite("nl/han/ica/SpaceInvaders2018/sprites/Cannon.png");
		reserveCannon.resize(23, 15);
		this.firstLifeAwarded = false;
		this.secondLifeAwarded = false;
		this.world = world;
	}
	
	@Override
	public void update() {
	}

	/**
	 * Bekijkt of er een extra leven moet worden toegekend, en tekent de levens van de speler
	 */
	@Override
	public void draw(PGraphics g) {
		rewardLives(world.getScore());
		drawReserveCannons(g);
	}
	
	/**
	 * Tekent de levens van de speler
	 * @param g			importeert de tekenfunctie van Processing
	 */
	private void drawReserveCannons(PGraphics g) {
		int x = Integer.parseInt(getText());
		if (x > 0) {
			PImage cannon = reserveCannon.getPImage();
			int width = reserveCannon.getWidth();
			int height = reserveCannon.getHeight();
			for (int i = 0; i < x; i++) {
				g.image(cannon, getX() + (width*i), getY() + height/2);

			}
		}
	}
	
	/**
	 * Deelt extra levens uit na het behalen van een bepaalde score
	 * @param score		de huidige score van de speler
	 */
	private void rewardLives(int score) {
		if (score > 1000 && !firstLifeAwarded) {
			world.increaseLives();
			firstLifeAwarded = true;
			showExtraLifeText();
		}
		else if (score > 2000 && !secondLifeAwarded) {
			world.increaseLives();
			secondLifeAwarded = true;
			showExtraLifeText();
		}
	}
	
    /**
     * Toont mededeling wanneer een extra leven is gehaald
     */
    private void showExtraLifeText() {
    	BonusPointsText bonus = new BonusPointsText("+1 LIFE!", 20, world);
    	bonus.setForeColor(0, 255, 0, 255);
    	bonus.setX(getX());
    	bonus.setY(getY());
    	world.addGameObject(bonus);
    }

}
