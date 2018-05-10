package nl.han.ica.SpaceInvaders2018;

/**
 * Level bepaalt de startpositie van de aliens
 */
public class Level {
    
	/**
     * Levelnummer (1 tot 10)
     */
	private int levelNumber;
	/**
	 * Startpositie van de aliens
	 */
    private int startPositionAliens;

    /**
     * Constructor
     * @param levelNumber			Levelnummer (1 tot 10)
     * @param startPositionAliens	Startpositie van de aliens
     */
    public Level(int levelNumber, int startPositionAliens) {
        this.levelNumber = levelNumber;
        this.startPositionAliens = startPositionAliens;
    }

    /**
     * Geeft het levelnummer
     * @return		levelnummer
     */
    public int getLevelNumber() {
        return levelNumber;
    }

    /**
     * Geeft de statpositie van de aliens
     * @return		startpositie
     */
    public int getStartPositionAliens() {
        return startPositionAliens;
    }
}
