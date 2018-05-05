package nl.han.ica.SpaceInvaders2018;

public class Level {
    private int levelNumber;
    private int startPositionAliens;

    public Level(int levelNumber, int startPositionAliens) {
        this.levelNumber = levelNumber;
        this.startPositionAliens = startPositionAliens;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public int getStartPositionAliens() {
        return startPositionAliens;
    }
}
